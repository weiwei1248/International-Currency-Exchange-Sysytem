package ca.uwindsor.ices.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.commons.lang3.StringUtils;

import ca.uwindsor.ices.dto.TransactionDto;
import ca.uwindsor.ices.jpa.BankAccount;
import ca.uwindsor.ices.jpa.ExchangeRate;
import ca.uwindsor.ices.jpa.ExchangeTransaction;
import ca.uwindsor.ices.repository.BankAccountRepository;
import ca.uwindsor.ices.repository.ExchangeRateRepository;
import ca.uwindsor.ices.repository.ExchangeTransactionRepository;
import ca.uwindsor.ices.util.BankAccountUtil;
import ca.uwindsor.ices.util.DateUtil;

@Service
public class ExchangeTransactionService {

    private BankAccountRepository bankAccountRepository;
    private ExchangeRateRepository exchangeRateRepository;
    private ExchangeTransactionRepository exchangeTransactionRepository;

    @Autowired
    public ExchangeTransactionService(BankAccountRepository bankAccountRepository,
                                      ExchangeRateRepository exchangeRateRepository,
                                      ExchangeTransactionRepository exchangeTransactionRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.exchangeRateRepository = exchangeRateRepository;
        this.exchangeTransactionRepository = exchangeTransactionRepository;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public TransactionDto generateExchangeTransaction(String userName,
                                                       int idBankAccountSource,
                                                       int idBankAccountDestination,
                                                       int idExchangeRate,
                                                       BigDecimal amountSource,
                                                       LocalDateTime dateTime){

        ExchangeRate exchangeRate = this.exchangeRateRepository.findOne(idExchangeRate).get();

        BankAccount bankAccountSource = this.bankAccountRepository.findOne(idBankAccountSource).get();

        BankAccount bankAccountDestination = this.bankAccountRepository.findOne(idBankAccountDestination).get();

        ExchangeTransaction exchangeTransaction = new ExchangeTransaction();

        exchangeTransaction.setExchangeRate(exchangeRate);
        exchangeTransaction.setBankAccount1(bankAccountSource);
        exchangeTransaction.setBankAccount2(bankAccountDestination);
        exchangeTransaction.setNumbAmountTransaction(amountSource);
        exchangeTransaction.setStatusMessage("SUCCESS");
        exchangeTransaction.setStatus("A");
        exchangeTransaction.setUserRegist(StringUtils.upperCase(userName));
        exchangeTransaction.setDateRegist(DateUtil.convertToDate(dateTime));

        exchangeTransaction = this.exchangeTransactionRepository.save(exchangeTransaction);

        return this.generateTransactionDto(exchangeTransaction);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public TransactionDto generateTransactionDto(ExchangeTransaction exchangeTransaction){

        TransactionDto transactionDto = new TransactionDto();

        transactionDto.setIdTransaction(exchangeTransaction.getIdExchangeTransaction());

        transactionDto.setSourceAccount(BankAccountUtil.toViewFormatLong(exchangeTransaction.getBankAccount1()));
        transactionDto.setDestinationAccount(BankAccountUtil.toViewFormatLong(exchangeTransaction.getBankAccount2()));

        transactionDto.setExchangeRate(exchangeTransaction.getExchangeRate().getNumbExchangeRate().toString());
        transactionDto.setSourceAmount(exchangeTransaction.getNumbAmountTransaction().toString());
        transactionDto.setDestinationAmount(exchangeTransaction.getNumbAmountTransaction()
                            .multiply(exchangeTransaction.getExchangeRate().getNumbExchangeRate()).toString());

        return transactionDto;
    }
}
