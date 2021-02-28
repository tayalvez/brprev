package br.com.projeto.brprev.services;

import br.com.projeto.brprev.interfaces.repositories.IUserRepository;
import br.com.projeto.brprev.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDetailService implements UserDetailsService {

    private final IUserRepository userRepository;

    @Autowired
    public UserDetailService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

   @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = Optional.ofNullable(userRepository.findByUsername(username))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        List<GrantedAuthority> authorityListAdmin = AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN");
        List<GrantedAuthority> authorityListUser = AuthorityUtils.createAuthorityList("ROLE_USER");
        return new org.springframework.security.core.userdetails.User
                (user.getUsername(), user.getPassword(), user.isAdmin() ? authorityListAdmin : authorityListUser);

    }
}
