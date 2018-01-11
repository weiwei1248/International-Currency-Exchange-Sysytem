package ca.uwindsor.ices.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the bank_account database table.
 *
 */
@Entity
@Table(name="bank_account")
@NamedQuery(name="BankAccount.findAll", query="SELECT b FROM BankAccount b")
public class BankAccount implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID_BANK_ACCOUNT", unique=true, nullable=false)
    private int idBankAccount;

    @Column(name="CODE_BANK_TRANSIT", nullable=false)
    private int codeBankTransit;

    @Column(name="CODE_INSTITUTION", nullable=false)
    private int codeInstitution;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="DATE_MODIFI")
    private Date dateModifi;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="DATE_REGIST", nullable=false)
    private Date dateRegist;

    @Column(name="NUMB_ACCOUNT", nullable=false)
    private int numbAccount;

    @Column(nullable=false, length=1)
    private String status;

    @Column(name="USER_MODIFI", length=45)
    private String userModifi;

    @Column(name="USER_REGIST", nullable=false, length=45)
    private String userRegist;

    //bi-directional many-to-one association to Bank
    @ManyToOne
    @JoinColumn(name="ID_BANK", nullable=false)
    private Bank bank;

    //bi-directional many-to-one association to Currency
    @ManyToOne
    @JoinColumn(name="ID_CURRENCY", nullable=false)
    private Currency currency;

    //bi-directional many-to-one association to Customer
    @ManyToOne
    @JoinColumn(name="ID_COSTUMER", nullable=false)
    private Customer customer;

    //bi-directional many-to-one association to ExchangeTransaction
    @OneToMany(mappedBy="bankAccount1")
    private List<ExchangeTransaction> exchangeTransactions1;

    //bi-directional many-to-one association to ExchangeTransaction
    @OneToMany(mappedBy="bankAccount2")
    private List<ExchangeTransaction> exchangeTransactions2;

    public BankAccount() {
    }

    public int getIdBankAccount() {
        return this.idBankAccount;
    }

    public void setIdBankAccount(int idBankAccount) {
        this.idBankAccount = idBankAccount;
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

    public int getNumbAccount() {
        return this.numbAccount;
    }

    public void setNumbAccount(int numbAccount) {
        this.numbAccount = numbAccount;
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

    public Bank getBank() {
        return this.bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public Currency getCurrency() {
        return this.currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<ExchangeTransaction> getExchangeTransactions1() {
        return this.exchangeTransactions1;
    }

    public void setExchangeTransactions1(List<ExchangeTransaction> exchangeTransactions1) {
        this.exchangeTransactions1 = exchangeTransactions1;
    }

    public ExchangeTransaction addExchangeTransactions1(ExchangeTransaction exchangeTransactions1) {
        getExchangeTransactions1().add(exchangeTransactions1);
        exchangeTransactions1.setBankAccount1(this);

        return exchangeTransactions1;
    }

    public ExchangeTransaction removeExchangeTransactions1(ExchangeTransaction exchangeTransactions1) {
        getExchangeTransactions1().remove(exchangeTransactions1);
        exchangeTransactions1.setBankAccount1(null);

        return exchangeTransactions1;
    }

    public List<ExchangeTransaction> getExchangeTransactions2() {
        return this.exchangeTransactions2;
    }

    public void setExchangeTransactions2(List<ExchangeTransaction> exchangeTransactions2) {
        this.exchangeTransactions2 = exchangeTransactions2;
    }

    public ExchangeTransaction addExchangeTransactions2(ExchangeTransaction exchangeTransactions2) {
        getExchangeTransactions2().add(exchangeTransactions2);
        exchangeTransactions2.setBankAccount2(this);

        return exchangeTransactions2;
    }

    public ExchangeTransaction removeExchangeTransactions2(ExchangeTransaction exchangeTransactions2) {
        getExchangeTransactions2().remove(exchangeTransactions2);
        exchangeTransactions2.setBankAccount2(null);

        return exchangeTransactions2;
    }

    @Override
    public String toString() {
        return String.format(
                "BankAccount [idBankAccount=%s, codeBankTransit=%s, codeInstitution=%s, dateModifi=%s, dateRegist=%s, numbAccount=%s, status=%s, userModifi=%s, userRegist=%s, bank=%s, currency=%s, customer=%s, exchangeTransactions1=%s, exchangeTransactions2=%s]",
                idBankAccount, codeBankTransit, codeInstitution, dateModifi, dateRegist, numbAccount, status,
                userModifi, userRegist, bank, currency, customer, exchangeTransactions1, exchangeTransactions2);
    }

}