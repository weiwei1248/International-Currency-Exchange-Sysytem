package ca.uwindsor.ices.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the master_currency database table.
 * 
 */
@Entity
@Table(name="master_currency")
@NamedQuery(name="MasterCurrency.findAll", query="SELECT m FROM MasterCurrency m")
public class MasterCurrency implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_MASTER_CURRENCY", unique=true, nullable=false)
	private int idMasterCurrency;

	@Column(name="NAME_CURRENCY", nullable=false, length=45)
	private String nameCurrency;

	@Column(nullable=false, length=1)
	private String status;

	//bi-directional many-to-one association to MasterBankAccount
	@OneToMany(mappedBy="masterCurrency")
	private List<MasterBankAccount> masterBankAccounts;

	public MasterCurrency() {
	}

	public int getIdMasterCurrency() {
		return this.idMasterCurrency;
	}

	public void setIdMasterCurrency(int idMasterCurrency) {
		this.idMasterCurrency = idMasterCurrency;
	}

	public String getNameCurrency() {
		return this.nameCurrency;
	}

	public void setNameCurrency(String nameCurrency) {
		this.nameCurrency = nameCurrency;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<MasterBankAccount> getMasterBankAccounts() {
		return this.masterBankAccounts;
	}

	public void setMasterBankAccounts(List<MasterBankAccount> masterBankAccounts) {
		this.masterBankAccounts = masterBankAccounts;
	}

	public MasterBankAccount addMasterBankAccount(MasterBankAccount masterBankAccount) {
		getMasterBankAccounts().add(masterBankAccount);
		masterBankAccount.setMasterCurrency(this);

		return masterBankAccount;
	}

	public MasterBankAccount removeMasterBankAccount(MasterBankAccount masterBankAccount) {
		getMasterBankAccounts().remove(masterBankAccount);
		masterBankAccount.setMasterCurrency(null);

		return masterBankAccount;
	}

}