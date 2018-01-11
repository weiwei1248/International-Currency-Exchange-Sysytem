package ca.uwindsor.ices.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the customer database table.
 *
 */
@Entity
@Table(name="customer")
@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c")
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID_CUSTOMER", unique=true, nullable=false)
    private int idCustomer;

    @Column(name="CODE_COUNTRY", nullable=false, length=2)
    private String codeCountry;

    @Column(name="CODE_POSTAL", nullable=false, length=10)
    private String codePostal;

    @Column(name="CODE_PROVINCE", nullable=false, length=2)
    private String codeProvince;

    @Temporal(TemporalType.DATE)
    @Column(name="DATE_BIRTH", nullable=false)
    private Date dateBirth;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="DATE_MODIFI")
    private Date dateModifi;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="DATE_REGIST", nullable=false)
    private Date dateRegist;

    @Column(name="NAME_ADDRESS", nullable=false, length=45)
    private String nameAddress;

    @Column(name="NAME_CITY", nullable=false, length=20)
    private String nameCity;

    @Column(name="NAME_FIRST", nullable=false, length=45)
    private String nameFirst;

    @Column(name="NAME_LAST", nullable=false, length=45)
    private String nameLast;

    @Column(name="NAME_MIDDLE", length=45)
    private String nameMiddle;

    @Column(name="NUMB_PHONE", nullable=false, length=20)
    private String numbPhone;

    @Column(nullable=false, length=1)
    private String status;

    @Column(name="TYPE_PHONE", nullable=false, length=4)
    private String typePhone;

    @Column(name="USER_MODIFI", length=45)
    private String userModifi;

    @Column(name="USER_REGIST", nullable=false, length=45)
    private String userRegist;

    //bi-directional one-to-one association to User
    @OneToOne(mappedBy="customer")
    private User user;

    //bi-directional many-to-one association to BankAccount
    @OneToMany(mappedBy="customer")
    private List<BankAccount> bankAccounts;

    public Customer() {
    }

    public int getIdCustomer() {
        return this.idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getCodeCountry() {
        return this.codeCountry;
    }

    public void setCodeCountry(String codeCountry) {
        this.codeCountry = codeCountry;
    }

    public String getCodePostal() {
        return this.codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getCodeProvince() {
        return this.codeProvince;
    }

    public void setCodeProvince(String codeProvince) {
        this.codeProvince = codeProvince;
    }

    public Date getDateBirth() {
        return this.dateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
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

    public String getNameAddress() {
        return this.nameAddress;
    }

    public void setNameAddress(String nameAddress) {
        this.nameAddress = nameAddress;
    }

    public String getNameCity() {
        return this.nameCity;
    }

    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
    }

    public String getNameFirst() {
        return this.nameFirst;
    }

    public void setNameFirst(String nameFirst) {
        this.nameFirst = nameFirst;
    }

    public String getNameLast() {
        return this.nameLast;
    }

    public void setNameLast(String nameLast) {
        this.nameLast = nameLast;
    }

    public String getNameMiddle() {
        return this.nameMiddle;
    }

    public void setNameMiddle(String nameMiddle) {
        this.nameMiddle = nameMiddle;
    }

    public String getNumbPhone() {
        return this.numbPhone;
    }

    public void setNumbPhone(String numbPhone) {
        this.numbPhone = numbPhone;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTypePhone() {
        return this.typePhone;
    }

    public void setTypePhone(String typePhone) {
        this.typePhone = typePhone;
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

    public List<BankAccount> getBankAccounts() {
        return this.bankAccounts;
    }

    public void setBankAccounts(List<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public BankAccount addBankAccount(BankAccount bankAccount) {
        getBankAccounts().add(bankAccount);
        bankAccount.setCustomer(this);

        return bankAccount;
    }

    public BankAccount removeBankAccount(BankAccount bankAccount) {
        getBankAccounts().remove(bankAccount);
        bankAccount.setCustomer(null);

        return bankAccount;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}