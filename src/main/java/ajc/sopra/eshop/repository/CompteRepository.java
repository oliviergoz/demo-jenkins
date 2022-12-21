package ajc.sopra.eshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ajc.sopra.eshop.model.Compte;

public interface CompteRepository extends JpaRepository<Compte, Long> {
	Optional<Compte> findByEmail(String email);
}
