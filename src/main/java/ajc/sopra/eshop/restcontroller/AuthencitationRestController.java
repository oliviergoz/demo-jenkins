package ajc.sopra.eshop.restcontroller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import ajc.sopra.eshop.model.Compte;
import ajc.sopra.eshop.model.JsonViews;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/auth")
public class AuthencitationRestController {

	@GetMapping("")
	@JsonView(JsonViews.CompteWithClient.class)
	public Compte authentication(@AuthenticationPrincipal Compte compte) {
		return compte;
	}
}
