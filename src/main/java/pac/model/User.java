package pac.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;


@Entity(name="users")
public class User {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	
	@ManyToOne()
	@JoinTable(
			name = "user_roles",
			joinColumns = {@JoinColumn(name="USER_ID", referencedColumnName = "ID")},
			inverseJoinColumns = {@JoinColumn(name="ROLE_ID", referencedColumnName = "ID")})
	private List<Role> roles = new ArrayList<>();
	


}
