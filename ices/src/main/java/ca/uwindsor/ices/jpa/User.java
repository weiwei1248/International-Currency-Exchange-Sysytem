package ca.uwindsor.ices.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the user database table.
 *
 */
@Entity
@Table(name="user")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID_USER", unique=true, nullable=false)
    private int idUser;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="DATE_MODIFI")
    private Date dateModifi;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="DATE_REGIST", nullable=false)
    private Date dateRegist;

    @Column(nullable=false, length=45)
    private String email;

    @Column(nullable=false, length=100)
    private String password;

    @Column(nullable=false, length=1)
    private String status;

    @Column(name="USER_MODIFI", length=45)
    private String userModifi;

    @Column(name="USER_REGIST", nullable=false, length=45)
    private String userRegist;

    //bi-directional many-to-one association to Customer
    @OneToOne
    @JoinColumn(name="ID_CUSTOMER")
    private Customer customer;

    //bi-directional many-to-one association to UserRole
    @OneToMany(mappedBy="user")
    private List<UserRole> userRoles;

    public User() {
    }

    public int getIdUser() {
        return this.idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
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

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<UserRole> getUserRoles() {
        return this.userRoles;
    }

    public void setUserRoles(List<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public UserRole addUserRole(UserRole userRole) {
        getUserRoles().add(userRole);
        userRole.setUser(this);

        return userRole;
    }

    public UserRole removeUserRole(UserRole userRole) {
        getUserRoles().remove(userRole);
        userRole.setUser(null);

        return userRole;
    }

}