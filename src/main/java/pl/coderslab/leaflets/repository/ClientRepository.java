package pl.coderslab.leaflets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.leaflets.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {

    Client findByEmail(String email);

}
