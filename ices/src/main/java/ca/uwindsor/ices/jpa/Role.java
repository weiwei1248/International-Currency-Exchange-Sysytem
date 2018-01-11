package ca.uwindsor.ices.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the role database table.
 * 
 */
@Entity
@Table(name="role")
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_ROLE", unique=true, nullable=false)
	private int idRole;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_MODIFI")
	private Date dateModifi;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_REGIST", nullable=false)
	private Date dateRegist;

	@Column(name="DESC_ROLE", nullable=false, length=60)
	private String descRole;

	@Column(name="NAME_ROLE", nullable=false, length=20)
	private String nameRole;

	@Column(nullable=false, length=1)
	private String status;

	@Column(name="USER_MODIFI", length=45)
	private String userModifi;

	@Column(name="USER_REGIST", nullable=false, length=45)
	private String userRegist;

	//bi-directional many-to-one association to UserRole
	@OneToMany(mappedBy="role")
	private List<UserRole> userRoles;

	public Role() {
	}

	public int getIdRole() {
		return this.idRole;
	}

	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}

	public Date getDateModifi() {
		return this.dateModifi;
	}

	public void setDateModifi(Date dateModifi) {
		this.dateModifi = dateModifi;
	}

	public Date getDateRegist() {
		return this.dateRegist;
	}

	public void setDateRegist(Date dateRegist) {
		this.dateRegist = dateRegist;
	}

	public String getDescRole() {
		return this.descRole;
	}

	public void setDescRole(String descRole) {
		this.descRole = descRole;
	}

	public String getNameRole() {
		return this.nameRole;
	}

	public void setNameRole(String nameRole) {
		this.nameRole = nameRole;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserModifi() {
		return this.userModifi;
	}

	public void setUserModifi(String userModifi) {
		this.userModifi = userModifi;
	}

	public String getUserRegist() {
		return this.userRegist;
	}

	public void setUserRegist(String userRegist) {
		this.userRegist = userRegist;
	}

	public List<UserRole> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public UserRole addUserRole(UserRole userRole) {
		getUserRoles().add(userRole);
		userRole.setRole(this);

		return userRole;
	}

	public UserRole removeUserRole(UserRole userRole) {
		getUserRoles().remove(userRole);
		userRole.setRole(null);

		return userRole;
	}

}