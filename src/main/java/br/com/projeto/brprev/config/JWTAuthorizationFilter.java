package br.com.projeto.brprev.config;

import br.com.projeto.brprev.services.UserDetailService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static br.com.projeto.brprev.config.SecurityConstants.*;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
    private final UserDetailService userDetailService;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, UserDetailService userDetailService) {
        super(authenticationManager);
        this.userDetailService = userDetailService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(HEADER_STRING);
        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(request);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request) throws MalformedJwtException {
        String token = request.getHeader(HEADER_STRING);
        if (token == null || !token.contains(TOKEN_PREFIX)) return null;
        try{
            String username = Jwts.parser().setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();

        UserDetails userDetails = userDetailService.loadUserByUsername(username);
        return username != null ?
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()) : null;

        }catch (MalformedJwtException exception){
            return null;
        }
    }
}