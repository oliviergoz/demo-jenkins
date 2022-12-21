package ajc.sopra.eshop.restcontroller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import ajc.sopra.eshop.model.Achat;
import ajc.sopra.eshop.model.Compte;
import ajc.sopra.eshop.model.JsonViews;
import ajc.sopra.eshop.service.AchatService;

@RestController
@RequestMapping("/api/achat")
@CrossOrigin(origins = {"*"})
public class AchatRestController {
	private static final Logger LOGGER=LoggerFactory.getLogger(AchatRestController.class);

	@Autowired
	private AchatService achatService;
	
	@JsonView(JsonViews.Achat.class)
	@PostMapping("")
	public Achat create(@RequestBody Achat achat, @AuthenticationPrincipal Compte compte) {
		//controles
		achat.setAcheteur(compte.getClient());
		return achatService.save(achat);
	}
	
	@JsonView(JsonViews.Achat.class)
	@PostMapping("/list")
	public List<Achat> create(@RequestBody List<Achat> achats,@AuthenticationPrincipal Compte compte) {
		//controles
		LOGGER.debug(compte.getClient().toString());
		achats.forEach(a->{
			a.setAcheteur(compte.getClient());
		});
		return achatService.saveAll(achats);
	}
	
	@JsonView(JsonViews.Achat.class)
	@GetMapping("")
	public List<Achat> findAll(){
		return achatService.findAll();
	}
}


