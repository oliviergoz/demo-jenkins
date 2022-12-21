package ajc.sopra.eshop.restcontroller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;

import ajc.sopra.eshop.model.JsonViews;
import ajc.sopra.eshop.model.Produit;
import ajc.sopra.eshop.repository.ProduitRepository;
import ajc.sopra.eshop.service.FournisseurService;
import ajc.sopra.eshop.service.ProduitService;

@RestController
@RequestMapping("/api/produit")
@CrossOrigin(origins = {"*"})
public class ProduitRestController {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(ProduitRestController.class);

	@Autowired
	private ProduitService produitSrv;
	@Autowired
	private FournisseurService fournisseurSrv;

	
	@GetMapping("/{id}/exists")
	public boolean exists(@PathVariable Integer id) {
		return produitSrv.exists(id);
	}

	// recuperation Get
	@JsonView(JsonViews.ProduitWithFournisseur.class)
	@GetMapping("/{id}")
	public Produit findById(@PathVariable Integer id) {
		return produitSrv.findById(id);
	}

	@GetMapping("")
	@JsonView(JsonViews.ProduitWithFournisseur.class)
	public List<Produit> findAll() {
		return produitSrv.findAll();
	}

	// creation Post
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("")
	@JsonView(JsonViews.ProduitWithFournisseur.class)
	public Produit create(@Valid @RequestBody Produit produit, BindingResult br) {
		LOGGER.debug("debut create");
		if (br.hasErrors()) {
			LOGGER.debug(br.toString());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "données incorrectes");
		}
		if (produit.getFournisseur() != null && produit.getFournisseur().getId() != null) {
			produit.setFournisseur(fournisseurSrv.findById(produit.getFournisseur().getId()));
		}
//		produit = produitSrv.create(produit);
//		return produitSrv.findById(produit.getId());
		LOGGER.debug("before create");
		return produitSrv.create(produit);
	}

	// suppression=>delete
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Integer id) {
		try {
			produitSrv.deleteId(id);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id inconnu");
		}
	}

	// update=>put
	// replacer
	@PutMapping("/{id}")
	@JsonView(JsonViews.ProduitWithFournisseur.class)
	public Produit update(@Valid @RequestBody Produit produit, BindingResult br, @PathVariable Integer id) {
		if (br.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "données incorrectes");
		}
		produit.setId(id);
		return produitSrv.update(produit);
	}

	// mise à jour partielle
	@PatchMapping("/{id}")
	@JsonView(JsonViews.ProduitWithFournisseur.class)
	public Produit update(@RequestBody Map<String, Object> fields, @PathVariable Integer id) {
		Produit produit = produitSrv.findById(id);
//		if (fields.get("libelle") != null) {
//			produit.setLibelle(fields.get("libelle").toString());
//		}
//		if (fields.get("prix") != null) {
//			produit.setPrix(Double.parseDouble(fields.get("prix").toString()));
//		}

		// la meme chose avec Spring
		fields.forEach((k, v) -> {
			if (k.equals("fournisseur")) {
				Map<String, Object> map = (Map<String, Object>) v;
				produit.setFournisseur(fournisseurSrv.findById(Integer.parseInt(map.get("id").toString())));
			} else {
				Field field = ReflectionUtils.findField(Produit.class, k);
				ReflectionUtils.makeAccessible(field);
				ReflectionUtils.setField(field, produit, v);
			}
		});

		return produitSrv.update(produit);
	}

}
