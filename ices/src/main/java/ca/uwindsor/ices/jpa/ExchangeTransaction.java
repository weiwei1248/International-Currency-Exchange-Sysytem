package ca.uwindsor.ices.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the exchange_transaction database table.
 *
 */
@Entity
@Table(name="exchange_transaction")
@NamedQuery(name="ExchangeTransaction.findAll", query="SELECT e FROM ExchangeTransaction e")
public class ExchangeTransaction implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID_EXCHANGE_TRANSACTION", unique=true, nullable=false)
    private int idExchangeTransaction;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="DATE_MODIFI")
    private Date dateModifi;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="DATE_REGIST", nullable=false)
    private Date dateRegist;

    @Column(name="NUMB_AMOUNT_TRANSACTION", nullable=false, precision=10, scale=5)
    private BigDecimal numbAmountTransaction;

    @Column(nullable=false, length=1)
    private String status;

    @Column(name="STATUS_MESSAGE", length=45)
    private String statusMessage;

    @Column(name="USER_MODIFI", length=45)
    private String userModifi;

    @Column(name="USER_REGIST", nullable=false, length=45)
    private String userRegist;

    //bi-directional many-to-one association to BankAccount
    @ManyToOne
    @JoinColumn(name="ID_BANK_ACCOUNT_DESTINA", nullable=false)
    private BankAccount bankAccount1;

    //bi-directional many-to-one association to BankAccount
    @ManyToOne
    @JoinColumn(name="ID_BANK_ACCOUNT_SOURCE", nullable=false)
    private BankAccount bankAccount2;

    //bi-directional many-to-one association to ExchangeRate
    @ManyToOne
    @JoinColumn(name="ID_EXCHANGE_RATE", nullable=false)
    private ExchangeRate exchangeRate;

    public ExchangeTransaction() {
    }

    public int getIdExchangeTransaction() {
        return this.idExchangeTransaction;
    }

    public void setIdExchangeTransaction(int idExchangeTransaction) {
        this.idExchangeTransaction = idExchangeTransaction;
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

    public BigDecimal getNumbAmountTransaction() {
        return this.numbAmountTransaction;
    }

    public void setNumbAmountTransaction(BigDecimal numbAmountTransaction) {
        this.numbAmountTransaction = numbAmountTransaction;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusMessage() {
        return this.statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
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

    public BankAccount getBankAccount1() {
        return this.bankAccount1;
    }

    public void setBankAccount1(BankAccount bankAccount1) {
        this.bankAccount1 = bankAccount1;
    }

    public BankAccount getBankAccount2() {
        return this.bankAccount2;
    }

    public void setBankAccount2(BankAccount bankAccount2) {
        this.bankAccount2 = bankAccount2;
    }

    public ExchangeRate getExchangeRate() {
        return this.exchangeRate;
    }

    public void setExchangeRate(ExchangeRate exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    @Override
    public String toString() {
        return String.format(
                "ExchangeTransaction [idExchangeTransaction=%s, dateModifi=%s, dateRegist=%s, numbAmountTransaction=%s, status=%s, statusMessage=%s, userModifi=%s, userRegist=%s, bankAccount1=%s, bankAccount2=%s, exchangeRate=%s]",
                idExchangeTransaction, dateModifi, dateRegist, numbAmountTransaction, status, statusMessage, userModifi,
                userRegist, bankAccount1, bankAccount2, exchangeRate);
    }


}