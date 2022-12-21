package ajc.sopra.eshop;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import ajc.sopra.eshop.model.Produit;
import ajc.sopra.eshop.service.ProduitService;

@SpringBootTest
@Transactional
//par defaut rollback a la fin de chaque transaction
class ProduitServiceTest {

	@Autowired
	ProduitService produitSrv;

	@Test
	void InjectionProduitServicetest() {
		assertNotNull(produitSrv);
	}
	
	@Test
	@Commit
	@Disabled
	void initProduit() {
		produitSrv.create(new Produit("velo", 100, null));
		produitSrv.create(new Produit("voiture", 200, null));
	}

	@Test
	void creationProduitTest() {
		Produit produit = new Produit("aaa", 1.5, null);
		produit = produitSrv.create(produit);
		assertNotNull(produit.getId());
		assertNotNull(produitSrv.findById(produit.getId()));
	}
	
}
