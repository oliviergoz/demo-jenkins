package ajc.sopra.eshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ajc.sopra.eshop.model.Client;

public interface ClientRepository extends JpaRepository<Client, Integer>{

}
