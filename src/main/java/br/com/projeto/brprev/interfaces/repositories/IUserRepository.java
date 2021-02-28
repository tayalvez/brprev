package br.com.projeto.brprev.interfaces.repositories;

import br.com.projeto.brprev.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
