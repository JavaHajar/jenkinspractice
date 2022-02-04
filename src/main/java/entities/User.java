package entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2284935477344739283L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private int id_user;
	@Column(name = "nom_user")
    private String nom_user;
	@Column(name = "prenom_user")
    private String prenom_user;
	@Column(name = "email")
    private String email;
	@Column(name = "password")
    private String password;
	@OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="user_role_id")
    private Role role;
   
    
    public User(int id_user, String nom_user, String prenom_user, String email, String password) {
		this.id_user = id_user;
		this.nom_user = nom_user;
		this.prenom_user = prenom_user;
		this.email = email;
		this.password = password;
		
	}

	public User() {
	}
	

	public User(int id_user) {
		this.id_user = id_user;
	}

	public User(String nom_user, String prenom_user, String email, String password) {
		this.nom_user = nom_user;
		this.prenom_user = prenom_user;
		this.email = email;
		this.password = password;
		
	}
    
    public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public String getNom_user() {
		return nom_user;
	}

	public void setNom_user(String nom_user) {
		this.nom_user = nom_user;
	}

	public String getPrenom_user() {
		return prenom_user;
	}

	public void setPrenom_user(String prenom_user) {
		this.prenom_user = prenom_user;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [id_user=" + id_user + ", nom_user=" + nom_user + ", prenom_user=" + prenom_user + ", email="
				+ email + ", password=" + password + ", role=" + role + "]";
	}

	
	
    
    
}
