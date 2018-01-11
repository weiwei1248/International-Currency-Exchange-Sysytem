package ca.uwindsor.ices.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the currency database table.
 * 
 */
@Entity
@Table(name="currency")
@NamedQuery(name="Currency.findAll", query="SELECT c FROM Currency c")
public class Currency implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_CURRENCY", unique=true, nullable=false)
	private int idCurrency;

	@Column(name="CODE_CURRENCY", nullable=false, length=3)
	private String codeCurrency;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_MODIFI")
	private Date dateModifi;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_REGIST", nullable=false)
	private Date dateRegist;

	@Column(name="NAME_CURRENCY", nullable=false, length=45)
	private String nameCurrency;

	@Column(name="NAME_SYMBOL", nullable=false, length=10)
	private String nameSymbol;

	@Column(nullable=false, length=1)
	private String status;

	@Column(name="USER_MODIFI", length=45)
	private String userModifi;

	@Column(name="USER_REGIST", nullable=false, length=45)
	private String userRegist;

	//bi-directional many-to-one association to BankAccount
	@OneToMany(mappedBy="currency")
	private List<BankAccount> bankAccounts;

	//bi-directional many-to-one association to ExchangeRate
	@OneToMany(mappedBy="currency1")
	private List<ExchangeRate> exchangeRates1;

	//bi-directional many-to-one association to ExchangeRate
	@OneToMany(mappedBy="currency2")
	private List<ExchangeRate> exchangeRates2;

	public Currency() {
	}

	public int getIdCurrency() {
		return this.idCurrency;
	}

	public void setIdCurrency(int idCurrency) {
		this.idCurrency = idCurrency;
	}

	public String getCodeCurrency() {
		return this.codeCurrency;
	}

	public void setCodeCurrency(String codeCurrency) {
		this.codeCurrency = codeCurrency;
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

	public String getNameCurrency() {
		return this.nameCurrency;
	}

	public void setNameCurrency(String nameCurrency) {
		this.nameCurrency = nameCurrency;
	}

	public String getNameSymbol() {
		return this.nameSymbol;
	}

	public void setNameSymbol(String nameSymbol) {
		this.nameSymbol = nameSymbol;
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
		bankAccount.setCurrency(this);

		return bankAccount;
	}

	public BankAccount removeBankAccount(BankAccount bankAccount) {
		getBankAccounts().remove(bankAccount);
		bankAccount.setCurrency(null);

		return bankAccount;
	}

	public List<ExchangeRate> getExchangeRates1() {
		return this.exchangeRates1;
	}

	public void setExchangeRates1(List<ExchangeRate> exchangeRates1) {
		this.exchangeRates1 = exchangeRates1;
	}

	public ExchangeRate addExchangeRates1(ExchangeRate exchangeRates1) {
		getExchangeRates1().add(exchangeRates1);
		exchangeRates1.setCurrency1(this);

		return exchangeRates1;
	}

	public ExchangeRate removeExchangeRates1(ExchangeRate exchangeRates1) {
		getExchangeRates1().remove(exchangeRates1);
		exchangeRates1.setCurrency1(null);

		return exchangeRates1;
	}

	public List<ExchangeRate> getExchangeRates2() {
		return this.exchangeRates2;
	}

	public void setExchangeRates2(List<ExchangeRate> exchangeRates2) {
		this.exchangeRates2 = exchangeRates2;
	}

	public ExchangeRate addExchangeRates2(ExchangeRate exchangeRates2) {
		getExchangeRates2().add(exchangeRates2);
		exchangeRates2.setCurrency2(this);

		return exchangeRates2;
	}

	public ExchangeRate removeExchangeRates2(ExchangeRate exchangeRates2) {
		getExchangeRates2().remove(exchangeRates2);
		exchangeRates2.setCurrency2(null);

		return exchangeRates2;
	}

}