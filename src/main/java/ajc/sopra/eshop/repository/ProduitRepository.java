package ajc.sopra.eshop.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ajc.sopra.eshop.model.Fournisseur;
import ajc.sopra.eshop.model.Produit;

public interface ProduitRepository extends JpaRepository<Produit, Integer> {
	List<Produit> findByLibelleContaining(String libelle);

	@Transactional
	@Modifying
	@Query("delete from Produit p where p.fournisseur=:fournisseur")
	int deleteByFournisseur(@Param("fournisseur") Fournisseur fournisseur);
}
