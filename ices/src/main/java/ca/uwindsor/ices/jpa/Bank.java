package ca.uwindsor.ices.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the bank database table.
 * 
 */
@Entity
@Table(name="bank")
@NamedQuery(name="Bank.findAll", query="SELECT b FROM Bank b")
public class Bank implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_BANK", unique=true, nullable=false)
	private int idBank;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_MODIFI")
	private Date dateModifi;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_REGIST", nullable=false)
	private Date dateRegist;

	@Column(name="DESC_BANK", length=45)
	private String descBank;

	@Column(name="NAME_BANK", nullable=false, length=20)
	private String nameBank;

	@Column(nullable=false, length=1)
	private String status;

	@Column(name="USER_MODIFI", length=45)
	private String userModifi;

	@Column(name="USER_REGIST", nullable=false, length=45)
	private String userRegist;

	//bi-directional many-to-one association to BankAccount
	@OneToMany(mappedBy="bank")
	private List<BankAccount> bankAccounts;

	public Bank() {
	}

	public int getIdBank() {
		return this.idBank;
	}

	public void setIdBank(int idBank) {
		this.idBank = idBank;
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

	public String getDescBank() {
		return this.descBank;
	}

	public void setDescBank(String descBank) {
		this.descBank = descBank;
	}

	public String getNameBank() {
		return this.nameBank;
	}

	public void setNameBank(String nameBank) {
		this.nameBank = nameBank;
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

	public List<BankAccount> getBankAccounts() {
		return this.bankAccounts;
	}

	public void setBankAccounts(List<BankAccount> bankAccounts) {
		this.bankAccounts = bankAccounts;
	}

	public BankAccount addBankAccount(BankAccount bankAccount) {
		getBankAccounts().add(bankAccount);
		bankAccount.setBank(this);

		return bankAccount;
	}

	public BankAccount removeBankAccount(BankAccount bankAccount) {
		getBankAccounts().remove(bankAccount);
		bankAccount.setBank(null);

		return bankAccount;
	}

}