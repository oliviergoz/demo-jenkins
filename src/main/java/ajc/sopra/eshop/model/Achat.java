package ajc.sopra.eshop.model;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Achat {

	@JsonView(JsonViews.Common.class)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@JsonView(JsonViews.Common.class)
	private int quantite;

	@JsonView(JsonViews.Achat.class)
	@ManyToOne
	@JoinColumn(name = "acheteur", nullable = false)
	private Client acheteur;
	
	@JsonView(JsonViews.Achat.class)
	@ManyToOne
	@JoinColumn(name = "produit", nullable = false)
	private Produit produit;

	public Achat() {
	}

	public Achat(Client acheteur, Produit produit) {
		this.acheteur = acheteur;
		this.produit = produit;
	}

	public Achat(int quantite, Client acheteur, Produit produit) {
		super();
		this.quantite = quantite;
		this.acheteur = acheteur;
		this.produit = produit;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Client getAcheteur() {
		return acheteur;
	}

	public void setAcheteur(Client acheteur) {
		this.acheteur = acheteur;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	@Override
	public String toString() {
		return "Achat [id=" + id + ", acheteur=" + acheteur + ", produit=" + produit + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Achat other = (Achat) obj;
		return Objects.equals(id, other.id);
	}

}
