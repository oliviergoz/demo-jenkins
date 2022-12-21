package ajc.sopra.eshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ajc.sopra.eshop.exception.IdException;
import ajc.sopra.eshop.model.Client;
import ajc.sopra.eshop.model.Compte;
import ajc.sopra.eshop.repository.ClientRepository;
import ajc.sopra.eshop.repository.CompteRepository;

@Service
public class ClientService {
	@Autowired
	private ClientRepository clientRepo;
	@Autowired
	private CompteRepository compteRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public List<Client> findAll() {
		return clientRepo.findAll();
	}

	public Client findById(Integer id) {
		return clientRepo.findById(id).orElseThrow(IdException::new);
	}

	public Client save(Client client) {
		Compte compte=client.getCompte();
		compte.setPassword(passwordEncoder.encode(compte.getPassword()));
		compteRepo.save(compte);
		return clientRepo.save(client);
	}
	
	public boolean checkEmailExists(String email) {
		return compteRepo.findByEmail(email).isPresent();
	}
}
