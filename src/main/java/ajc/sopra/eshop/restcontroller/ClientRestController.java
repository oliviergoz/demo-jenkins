package ajc.sopra.eshop.restcontroller;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;

import ajc.sopra.eshop.model.Adresse;
import ajc.sopra.eshop.model.Client;
import ajc.sopra.eshop.model.Client;
import ajc.sopra.eshop.model.JsonViews;
import ajc.sopra.eshop.service.ClientService;

@RestController
@RequestMapping("/api/client")
@CrossOrigin(origins = {"*"})
public class ClientRestController {

	@Autowired
	private ClientService clientSrv;
	
	@GetMapping("/check/email/{email}")
	public boolean checkEmailExists(@PathVariable String email) {
		return clientSrv.checkEmailExists(email);
	}

	@PostMapping("/inscription")
	@JsonView(JsonViews.ClientWithCompte.class)
	public Client inscription(@Valid @RequestBody Client client, BindingResult br) {
		if (br.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		return clientSrv.save(client);
	}

	@JsonView(JsonViews.ClientWithCompte.class)
	@GetMapping("/{id}")
	public Client findById(@PathVariable Integer id) {
		return clientSrv.findById(id);
	}

	@JsonView(JsonViews.ClientWithCompte.class)
	@GetMapping("")
	public List<Client> findAll() {
		return clientSrv.findAll();
	}
	
	@PatchMapping("/{id}")
	@JsonView(JsonViews.Common.class)
	public Client patch(@RequestBody Map<String, Object> fields, @PathVariable Integer id) {
		Client client = clientSrv.findById(id);
		fields.forEach((k, v) -> {
			if (k.equals("adresse")) {
				Map<String, Object> mapAdresse = (Map<String, Object>) v;
				mapAdresse.forEach((kAdresse, vAdresse) -> {
					Field fieldAdresse = ReflectionUtils.findField(Adresse.class, kAdresse);
					ReflectionUtils.makeAccessible(fieldAdresse);
					ReflectionUtils.setField(fieldAdresse, client.getAdresse(), vAdresse);
				});
			}else if(k.equals("naissance")) {
				client.setNaissance(LocalDate.parse(v.toString()));
			} else {
				Field field = ReflectionUtils.findField(Client.class, k);
				ReflectionUtils.makeAccessible(field);
				ReflectionUtils.setField(field, client, v);
			}
		});
		return clientSrv.save(client);
	}
}
