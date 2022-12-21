package ajc.sopra.eshop.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@DiscriminatorValue("supplier")
public class Fournisseur extends Personne {

	@JsonView({JsonViews.Common.class})
	@Column(name = "company", length = 25)
	private String societe;

	@JsonView(JsonViews.FournisseurWithProduit.class)
	@OneToMany(mappedBy = "fournisseur")
	private List<Produit> produits;

	public Fournisseur() {
	}

	public Fournisseur(String nom, String prenom, String societe) {
		super(nom, prenom);
		this.societe = societe;
	}

	public Fournisseur(Integer id, String nom, String prenom, Adresse adresse, String societe) {
		super(id, nom, prenom, adresse);
		this.societe = societe;
	}

	public String getSociete() {
		return societe;
	}

	public void setSociete(String societe) {
		this.societe = societe;
	}

	public List<Produit> getProduits() {
		return produits;
	}

	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}

	@Override
	public String toString() {
		return "Fournisseur [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", societe="
				+ societe + "]";
	}

	public String getInfos() {
		return id + " " + prenom + " " + nom + " " + societe != null ? societe : "";
	}

}
