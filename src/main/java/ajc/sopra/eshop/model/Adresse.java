package ajc.sopra.eshop.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonView;

@Embeddable
public class Adresse {
	@JsonView(JsonViews.Common.class)
	@Column(length = 10)
	private String numero;
	@JsonView(JsonViews.Common.class)
	@Column(name = "way", length = 25)
	private String voie;
	@JsonView(JsonViews.Common.class)
	@Column(name = "city", length = 25)
	private String ville;
	@JsonView(JsonViews.Common.class)
	@Column(name = "pc", length = 10)
	private String cp;

	public Adresse() {
	}

	public Adresse(String numero, String voie, String ville, String cp) {
		this.numero = numero;
		this.voie = voie;
		this.ville = ville;
		this.cp = cp;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getVoie() {
		return voie;
	}

	public void setVoie(String voie) {
		this.voie = voie;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	@Override
	public String toString() {
		return "Adresse [numero=" + numero + ", voie=" + voie + ", ville=" + ville + ", cp=" + cp + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(cp, numero, ville, voie);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Adresse other = (Adresse) obj;
		return Objects.equals(cp, other.cp) && Objects.equals(numero, other.numero)
				&& Objects.equals(ville, other.ville) && Objects.equals(voie, other.voie);
	}

}
