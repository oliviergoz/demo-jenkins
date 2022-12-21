package ajc.sopra.eshop.restcontroller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

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

import ajc.sopra.eshop.model.Adresse;
import ajc.sopra.eshop.model.Fournisseur;
import ajc.sopra.eshop.model.JsonViews;
import ajc.sopra.eshop.service.FournisseurService;

@RestController
@RequestMapping("/api/fournisseur")
@CrossOrigin(origins = {"*"})
public class FournisseurRestController {

	@Autowired
	private FournisseurService fournisseurSrv;

	@GetMapping("")
	@JsonView(JsonViews.Common.class)
	public List<Fournisseur> findAll() {
		return fournisseurSrv.findAll();
	}

	@JsonView(JsonViews.Common.class)
	@GetMapping("/{id}")
	public Fournisseur findById(@PathVariable Integer id) {
		return fournisseurSrv.findById(id);
	}

	@JsonView(JsonViews.FournisseurWithProduit.class)
	@GetMapping("/{id}/produit")
	public Fournisseur findByIdWithProduit(@PathVariable Integer id) {
		return fournisseurSrv.findByIdFetchProduits(id);
	}

	@PostMapping("")
	@JsonView(JsonViews.Common.class)
	@ResponseStatus(code = HttpStatus.CREATED)
	public Fournisseur create(@Valid @RequestBody Fournisseur fournisseur, BindingResult br) {
		if (br.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		return fournisseurSrv.save(fournisseur);
	}

	@PutMapping("/{id}")
	@JsonView(JsonViews.Common.class)
	public Fournisseur update(@Valid @RequestBody Fournisseur fournisseur, BindingResult br, @PathVariable Integer id) {
		if (br.hasErrors() && fournisseurSrv.findById(id) == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		return fournisseurSrv.save(fournisseur);
	}

	@PatchMapping("/{id}")
	@JsonView(JsonViews.Common.class)
	public Fournisseur patch(@RequestBody Map<String, Object> fields, @PathVariable Integer id) {
		Fournisseur fournisseur = fournisseurSrv.findById(id);
		fields.forEach((k, v) -> {
			if (k.equals("adresse")) {
				Map<String, Object> mapAdresse = (Map<String, Object>) v;
				mapAdresse.forEach((kAdresse, vAdresse) -> {
					Field fieldAdresse = ReflectionUtils.findField(Adresse.class, kAdresse);
					ReflectionUtils.makeAccessible(fieldAdresse);
					ReflectionUtils.setField(fieldAdresse, fournisseur.getAdresse(), vAdresse);
				});
			} else {
				Field field = ReflectionUtils.findField(Fournisseur.class, k);
				ReflectionUtils.makeAccessible(field);
				ReflectionUtils.setField(field, fournisseur, v);
			}
		});
		return fournisseurSrv.save(fournisseur);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Integer id) {
		fournisseurSrv.deleteById(id);
	}
}
