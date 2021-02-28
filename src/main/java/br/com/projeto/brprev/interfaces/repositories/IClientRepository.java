package br.com.projeto.brprev.interfaces.repositories;

import br.com.projeto.brprev.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IClientRepository extends JpaRepository<Client, Long> {

    List<Client> findByNameIgnoreCaseContaining(String name);
}
