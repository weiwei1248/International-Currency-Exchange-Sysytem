package ca.uwindsor.ices.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the master_bank database table.
 * 
 */
@Entity
@Table(name="master_bank")
@NamedQuery(name="MasterBank.findAll", query="SELECT m FROM MasterBank m")
public class MasterBank implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_MASTER_BANK", unique=true, nullable=false)
	private int idMasterBank;

	@Column(name="DESC_BANK", length=45)
	private String descBank;

	@Column(name="NAME_BANK", nullable=false, length=20)
	private String nameBank;

	@Column(nullable=false, length=1)
	private String status;

	//bi-directional many-to-one association to MasterBankAccount
	@OneToMany(mappedBy="masterBank")
	private List<MasterBankAccount> masterBankAccounts;

	public MasterBank() {
	}

	public int getIdMasterBank() {
		return this.idMasterBank;
	}

	public void setIdMasterBank(int idMasterBank) {
		this.idMasterBank = idMasterBank;
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

	public List<MasterBankAccount> getMasterBankAccounts() {
		return this.masterBankAccounts;
	}

	public void setMasterBankAccounts(List<MasterBankAccount> masterBankAccounts) {
		this.masterBankAccounts = masterBankAccounts;
	}

	public MasterBankAccount addMasterBankAccount(MasterBankAccount masterBankAccount) {
		getMasterBankAccounts().add(masterBankAccount);
		masterBankAccount.setMasterBank(this);

		return masterBankAccount;
	}

	public MasterBankAccount removeMasterBankAccount(MasterBankAccount masterBankAccount) {
		getMasterBankAccounts().remove(masterBankAccount);
		masterBankAccount.setMasterBank(null);

		return masterBankAccount;
	}

}