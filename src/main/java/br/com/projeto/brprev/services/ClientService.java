package br.com.projeto.brprev.services;

import br.com.projeto.brprev.interfaces.repositories.IClientRepository;
import br.com.projeto.brprev.interfaces.services.IClientService;
import br.com.projeto.brprev.models.Client;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService implements IClientService {

    final IClientRepository clientRepository;
    public ClientService(IClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client findClientById(long id) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent())
            return client.get();
        return null;
    }

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public void deleteById(long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public Client alter(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public boolean existsById(long id) {
        return clientRepository.existsById(id);
    }

    @Override
    public List<Client> findByNameIgnoreCaseContaining(String name) {
        return clientRepository.findByNameIgnoreCaseContaining(name);
    }


}
