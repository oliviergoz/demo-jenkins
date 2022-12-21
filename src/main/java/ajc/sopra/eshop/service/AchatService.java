package ajc.sopra.eshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ajc.sopra.eshop.model.Achat;
import ajc.sopra.eshop.repository.AchatRepository;

@Service
public class AchatService {

	@Autowired
	private AchatRepository achatRepo;

	public Achat save(Achat achat) {
		return achatRepo.save(achat);
	}

	public List<Achat> saveAll(List<Achat> achats) {
		return achatRepo.saveAll(achats);
	}

	public List<Achat> findAll() {
		return achatRepo.findAll();
	}
}
