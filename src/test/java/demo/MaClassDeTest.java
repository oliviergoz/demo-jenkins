package demo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import demoJavaAvecGit.FactorielException;
import demoJavaAvecGit.FonctionMathematique;

class MaClassDeTest {

	FonctionMathematique fct;
	
	@BeforeEach
	void avantChaque() {
		fct=new FonctionMathematique();
	}
	
	@AfterEach
	void apresChaqueTest() {
		fct=null;
	}
	
	@BeforeAll
	static void avantLePremierTest() {
		//execute 1 fois avant le premier test
	}
	
	@AfterAll
	static void apresLeDernierTest() {
		//execute 1 fois apres le dernier test
	}
	
	
	@Test
	void factorielTest() {
		//aaa
		assertEquals(6, fct.factoriel(3));
	}
	
	@Test
	void factorielExceptioTest() {
		assertThrows(FactorielException.class,()->{
			fct.factoriel(0);
		});
		
	}

	@Test
	void additionTest() {
		assertEquals(10, fct.addition(2, 8));
		//assertEquals(15, fct.addition(10, 6));
		assertEquals(15, fct.addition(10, 5));
	}

}
