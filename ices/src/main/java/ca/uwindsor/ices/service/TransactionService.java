package ca.uwindsor.ices.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ca.uwindsor.ices.dto.BaseDto;
import ca.uwindsor.ices.jpa.ExchangeRate;

@Service
public class TransactionService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private ExchangeRateService exchangeRateService;
    private MasterBankAccountService masterBankAccountService;
    private ExchangeTransactionService exchangeTransactionService;

    @Autowired
    public TransactionService(ExchangeRateService exchangeRateService,
                              MasterBankAccountService masterBankAccountService,
                              ExchangeTransactionService exchangeTransactionService) {
        super();
        this.exchangeRateService = exchangeRateService;
        this.masterBankAccountService = masterBankAccountService;
        this.exchangeTransactionService = exchangeTransactionService;
    }

    @Transactional(propagation=Propagation.REQUIRED)
    public BaseDto createTransaction(String userName,
                                      int idBankAccountSource,
                                      int idBankAccountDestination,
                                      BigDecimal amountSource){

        LocalDateTime now = LocalDateTime.now();

        ExchangeRate exchangeRate = this.exchangeRateService.retrieveExchangeRate(idBankAccountSource,
                                                                                  idBankAccountDestination,
                                                                                  now.toLocalDate());

        BaseDto response = this.masterBankAccountService.transferFunds(userName,
                                                                       idBankAccountSource,
                                                                       idBankAccountDestination,
                                                                       amountSource,
                                                                       amountSource.multiply(exchangeRate.getNumbExchangeRate()));

        if (response.getCode() == 0) {
            response = this.exchangeTransactionService.generateExchangeTransaction(userName,
                                                                                   idBankAccountSource,
                                                                                   idBankAccountDestination,
                                                                                   exchangeRate.getIdExchangeRate(),
                                                                                   amountSource,
                                                                                   now);
        }

        return response;
    }
}
