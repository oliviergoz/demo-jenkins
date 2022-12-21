package ajc.sopra.eshop.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ajc.sopra.eshop.exception.FournisseurException;
import ajc.sopra.eshop.exception.IdException;
import ajc.sopra.eshop.model.Fournisseur;
import ajc.sopra.eshop.repository.FournisseurRepository;
import ajc.sopra.eshop.repository.ProduitRepository;

@Service
public class FournisseurService {

	@Autowired
	private FournisseurRepository fournisseurRepo;
	@Autowired
	private ProduitRepository produitRepo;

	public List<Fournisseur> findAll() {
		return fournisseurRepo.findAll();

	}

	public Fournisseur findByIdFetchProduits(Integer id) {
		return fournisseurRepo.findByIdFetchingProduits(id).orElseThrow(IdException::new);
	}

	public Fournisseur findById(Integer id) {
		return fournisseurRepo.findById(id).orElseThrow(IdException::new);
	}

	public Fournisseur save(Fournisseur fournisseur) {
		if (fournisseur.getNom() == null || fournisseur.getNom().isBlank()) {
			throw new FournisseurException();
		}
		if (fournisseur.getPrenom() == null || fournisseur.getPrenom().isBlank()) {
			throw new FournisseurException();
		}

		return fournisseurRepo.save(fournisseur);
	}

	public void deleteById(Integer id) {
		produitRepo.deleteByFournisseur(findById(id));
		fournisseurRepo.deleteById(id);
	}
}
