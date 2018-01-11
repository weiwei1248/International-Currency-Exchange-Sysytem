package ca.uwindsor.ices.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the master_bank_account database table.
 * 
 */
@Entity
@Table(name="master_bank_account")
@NamedQuery(name="MasterBankAccount.findAll", query="SELECT m FROM MasterBankAccount m")
public class MasterBankAccount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_MASTER_BANK_ACCOUNT", unique=true, nullable=false)
	private int idMasterBankAccount;

	@Column(name="CODE_BANK_TRANSIT", nullable=false)
	private int codeBankTransit;

	@Column(name="CODE_INSTITUTION", nullable=false)
	private int codeInstitution;

	@Column(name="NUMB_ACCOUNT", nullable=false)
	private int numbAccount;

	@Column(name="NUMB_ACCOUNT_BALANCE", nullable=false, precision=10, scale=5)
	private BigDecimal numbAccountBalance;

	@Column(nullable=false, length=100)
	private String password;

	@Column(nullable=false, length=1)
	private String status;

	//bi-directional many-to-one association to MasterBank
	@ManyToOne
	@JoinColumn(name="ID_MASTER_BANK", nullable=false)
	private MasterBank masterBank;

	//bi-directional many-to-one association to MasterCurrency
	@ManyToOne
	@JoinColumn(name="ID_MASTER_CURRENCY", nullable=false)
	private MasterCurrency masterCurrency;

	public MasterBankAccount() {
	}

	public int getIdMasterBankAccount() {
		return this.idMasterBankAccount;
	}

	public void setIdMasterBankAccount(int idMasterBankAccount) {
		this.idMasterBankAccount = idMasterBankAccount;
	}

	public int getCodeBankTransit() {
		return this.codeBankTransit;
	}

	public void setCodeBankTransit(int codeBankTransit) {
		this.codeBankTransit = codeBankTransit;
	}

	public int getCodeInstitution() {
		return this.codeInstitution;
	}

	public void setCodeInstitution(int codeInstitution) {
		this.codeInstitution = codeInstitution;
	}

	public int getNumbAccount() {
		return this.numbAccount;
	}

	public void setNumbAccount(int numbAccount) {
		this.numbAccount = numbAccount;
	}

	public BigDecimal getNumbAccountBalance() {
		return this.numbAccountBalance;
	}

	public void setNumbAccountBalance(BigDecimal numbAccountBalance) {
		this.numbAccountBalance = numbAccountBalance;
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

	public MasterBank getMasterBank() {
		return this.masterBank;
	}

	public void setMasterBank(MasterBank masterBank) {
		this.masterBank = masterBank;
	}

	public MasterCurrency getMasterCurrency() {
		return this.masterCurrency;
	}

	public void setMasterCurrency(MasterCurrency masterCurrency) {
		this.masterCurrency = masterCurrency;
	}

}