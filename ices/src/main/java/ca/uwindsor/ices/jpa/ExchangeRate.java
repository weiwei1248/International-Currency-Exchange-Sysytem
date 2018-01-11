package ca.uwindsor.ices.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the exchange_rate database table.
 *
 */
@Entity
@Table(name="exchange_rate")
@NamedQuery(name="ExchangeRate.findAll", query="SELECT e FROM ExchangeRate e")
public class ExchangeRate implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID_EXCHANGE_RATE", unique=true, nullable=false)
    private int idExchangeRate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="DATE_EXCHANGE_RATE", nullable=false)
    private Date dateExchangeRate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="DATE_MODIFI")
    private Date dateModifi;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="DATE_REGIST", nullable=false)
    private Date dateRegist;

    @Column(name="NUMB_EXCHANGE_RATE", nullable=false, precision=10, scale=5)
    private BigDecimal numbExchangeRate;

    @Column(nullable=false, length=1)
    private String status;

    @Column(name="USER_MODIFI", length=45)
    private String userModifi;

    @Column(name="USER_REGIST", nullable=false, length=45)
    private String userRegist;

    //bi-directional many-to-one association to Currency
    @ManyToOne
    @JoinColumn(name="ID_CURRENCY_SOURCE", nullable=false)
    private Currency currency1;

    //bi-directional many-to-one association to Currency
    @ManyToOne
    @JoinColumn(name="ID_CURRENCY_DESTINA", nullable=false)
    private Currency currency2;

    //bi-directional many-to-one association to ExchangeTransaction
    @OneToMany(mappedBy="exchangeRate")
    private List<ExchangeTransaction> exchangeTransactions;

    public ExchangeRate() {
    }

    public int getIdExchangeRate() {
        return this.idExchangeRate;
    }

    public void setIdExchangeRate(int idExchangeRate) {
        this.idExchangeRate = idExchangeRate;
    }

    public Date getDateExchangeRate() {
        return this.dateExchangeRate;
    }

    public void setDateExchangeRate(Date dateExchangeRate) {
        this.dateExchangeRate = dateExchangeRate;
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

    public BigDecimal getNumbExchangeRate() {
        return this.numbExchangeRate;
    }

    public void setNumbExchangeRate(BigDecimal numbExchangeRate) {
        this.numbExchangeRate = numbExchangeRate;
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

    public Currency getCurrency1() {
        return this.currency1;
    }

    public void setCurrency1(Currency currency1) {
        this.currency1 = currency1;
    }

    public Currency getCurrency2() {
        return this.currency2;
    }

    public void setCurrency2(Currency currency2) {
        this.currency2 = currency2;
    }

    public List<ExchangeTransaction> getExchangeTransactions() {
        return this.exchangeTransactions;
    }

    public void setExchangeTransactions(List<ExchangeTransaction> exchangeTransactions) {
        this.exchangeTransactions = exchangeTransactions;
    }

    public ExchangeTransaction addExchangeTransaction(ExchangeTransaction exchangeTransaction) {
        getExchangeTransactions().add(exchangeTransaction);
        exchangeTransaction.setExchangeRate(this);

        return exchangeTransaction;
    }

    public ExchangeTransaction removeExchangeTransaction(ExchangeTransaction exchangeTransaction) {
        getExchangeTransactions().remove(exchangeTransaction);
        exchangeTransaction.setExchangeRate(null);

        return exchangeTransaction;
    }

    @Override
    public String toString() {
        return String.format(
                "ExchangeRate [idExchangeRate=%s, dateExchangeRate=%s, dateModifi=%s, dateRegist=%s, numbExchangeRate=%s, status=%s, userModifi=%s, userRegist=%s, currency1=%s, currency2=%s, exchangeTransactions=%s]",
                idExchangeRate, dateExchangeRate, dateModifi, dateRegist, numbExchangeRate, status, userModifi,
                userRegist, currency1, currency2, exchangeTransactions);
    }

}