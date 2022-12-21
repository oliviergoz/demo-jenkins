package ajc.sopra.eshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ajc.sopra.eshop.model.Personne;

public interface PersonneRepository extends JpaRepository<Personne, Integer> {

}
