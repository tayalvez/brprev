package br.com.projeto.brprev.controllers;

import br.com.projeto.brprev.errors.ResourceNotFoundException;
import br.com.projeto.brprev.interfaces.services.IClientService;
import br.com.projeto.brprev.models.Client;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.    annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("v1")
public class ClientController {

    private final IClientService clientService;

    @Autowired
    public ClientController(IClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping(path = "consumer/clients")
    @ApiOperation(value = "Return a list  with all clients", response = Client[].class)
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "Authorization",
                    value = "Bearer token",
                    required = true,
                    dataType = "string",
                    paramType = "header")
    })
    public ResponseEntity<?> findAll(){
        List<Client> clients = clientService.findAll();
        return new ResponseEntity<>(clients,HttpStatus.OK);
    }

    @GetMapping(path="consumer/clients/{id}")
    @ApiOperation(value = "Returns a client by id", response = Client.class)
    public ResponseEntity<?> getClientById(@PathVariable("id") Long id){
        verifyIfClientExists(id);
        Client client = clientService.findClientById(id);
        return new ResponseEntity<>(client,HttpStatus.OK);
    }

    @GetMapping(path = "consumer/clients/findByName/{name}")
    @ApiOperation(value = "Return a Client by name", response = Client.class)
    public ResponseEntity<?> findClientByName(@PathVariable String name){
        return new ResponseEntity<>(clientService.findByNameIgnoreCaseContaining(name), HttpStatus.OK);
    }

    @PostMapping(path = "admin/clients")
    @Transactional(rollbackOn = Exception.class)
    @ApiOperation(value = "Create a new Client", response = Client.class)
    public ResponseEntity<?> save(@Valid @RequestBody Client client){
        return new ResponseEntity<>(clientService.save(client),HttpStatus.CREATED);
    }

    @PutMapping(path = "admin/clients")
    @ApiOperation(value = "Update a Client", response = Client.class)
    public ResponseEntity<?> update(@RequestBody Client client){
        verifyIfClientExists(client.getId());
        return new ResponseEntity<>(clientService.alter(client), HttpStatus.OK);
    }

    @DeleteMapping(path = "admin/clients/{id}")
    //@PreAuthorize("hasRole ('ROLE_ADMIN')")
    @ApiOperation(value = "Delete a Client")
    public ResponseEntity delete(@PathVariable("id") Long id){
        verifyIfClientExists(id);
        clientService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    private void verifyIfClientExists(Long id){
        Client client = clientService.findClientById(id);
        if(client == null)
            throw new ResourceNotFoundException("Client not found for ID:" + id);
    }
}
