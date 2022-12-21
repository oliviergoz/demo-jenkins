package ajc.sopra.eshop.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "compte")
public class Compte implements UserDetails{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(JsonViews.Common.class)
	private Long id;
	@NotBlank
	@Email
	@Column(name = "email", length = 255, nullable = false, unique = true)
	@JsonView(JsonViews.Common.class)
	private String email;
	@Column(name = "password", length = 255, nullable = false)
	private String password;
	@OneToOne(mappedBy = "compte")
	@JsonView(JsonViews.CompteWithClient.class)
	private Client client;

	public Compte() {

	}

	public Compte(String email, String password, Client client) {
		super();
		this.email = email;
		this.password = password;
		this.client = client;
	}

	public Compte(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
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
		Compte other = (Compte) obj;
		return Objects.equals(id, other.id);
	}

	@JsonView(JsonViews.CompteWithClient.class)
	public String getRole() {
		return getAuthorities().stream().findFirst().get().toString();
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		String role=null;
		if(client==null) {
			role="ROLE_ADMIN";
		}else {
			role="ROLE_CLIENT";
		}
		return Arrays.asList(new SimpleGrantedAuthority(role));
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
