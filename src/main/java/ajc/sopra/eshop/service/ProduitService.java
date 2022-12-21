package ajc.sopra.eshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ajc.sopra.eshop.exception.IdException;
import ajc.sopra.eshop.exception.ProduitException;
import ajc.sopra.eshop.model.Produit;
import ajc.sopra.eshop.repository.ProduitRepository;

@Service
public class ProduitService {

	@Autowired
	private ProduitRepository produitRepo;

	public List<Produit> findAll() {
		return produitRepo.findAll();
	}

	public Produit findById(Integer id) {
//		return produitRepo.findById(id).orElseThrow(()->{
//			throw new IdException();
//		});
		return produitRepo.findById(id).orElseThrow(IdException::new);
	}

	public List<Produit> findByLibelle(String libelle) {
		return produitRepo.findByLibelleContaining(libelle);
	}

	public Produit create(Produit produit) {
		if (produit.getId() != null) {
			throw new ProduitException("produit deja dans la base");
		}
		return save(produit);

	}

	public Produit update(Produit produit) {
		if (produit.getId() == null || !produitRepo.existsById(produit.getId())) {
			throw new IdException();
		}
		return save(produit);
	}

	private Produit save(Produit produit) {
		if (produit.getLibelle() == null || produit.getLibelle().isBlank() || produit.getLibelle().length() > 30) {
			throw new ProduitException("probleme libelle");
		}
		if (produit.getPrix() <= 0) {
			throw new ProduitException("probleme prix");
		}
		return produitRepo.save(produit);
	}

	public void delete(Produit produit) {
		produitRepo.delete(produit);
	}

	public void deleteId(Integer id) {
		produitRepo.deleteById(id);
	}
	
	public boolean exists(Integer id) {
		return produitRepo.existsById(id);
	}
}
