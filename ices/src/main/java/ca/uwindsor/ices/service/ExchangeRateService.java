package ca.uwindsor.ices.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.uwindsor.ices.dto.ExchangeRateDto;
import ca.uwindsor.ices.exception.SystemException;
import ca.uwindsor.ices.jpa.BankAccount;
import ca.uwindsor.ices.jpa.Currency;
import ca.uwindsor.ices.jpa.ExchangeRate;
import ca.uwindsor.ices.repository.BankAccountRepository;
import ca.uwindsor.ices.repository.ExchangeRateRepository;
import ca.uwindsor.ices.util.DateUtil;

@Service
public class ExchangeRateService {

    private BankAccountRepository bankAccountRepository;
    private ExchangeRateRepository exchangeRateRepository;

    @Autowired
    public ExchangeRateService(BankAccountRepository bankAccountRepository,
                               ExchangeRateRepository exchangeRateRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.exchangeRateRepository = exchangeRateRepository;
    }

    @Transactional
    public ExchangeRate retrieveExchangeRate(int idBankAccountSource,
                                             int idBankAccountDestination,
                                             LocalDate date){

        int idCurrencySource = this.bankAccountRepository.findOne(idBankAccountSource)
                                     .map(BankAccount::getCurrency)
                                     .map(Currency::getIdCurrency)
                                     .orElseThrow(() ->
                  new SystemException(String.format("BankAccount not found - idBankAccount: %d",idBankAccountSource)));

        int idCurrencyDestination = this.bankAccountRepository.findOne(idBankAccountDestination)
                                     .map(BankAccount::getCurrency)
                                     .map(Currency::getIdCurrency)
                                     .orElseThrow(() ->
                  new SystemException(String.format("BankAccount not found - idBankAccount: %d",idBankAccountSource)));

        return this.exchangeRateRepository.findOne(idCurrencySource,
                                                   idCurrencyDestination,
                                                   DateUtil.convertToDate(date),
                                                   DateUtil.convertToDate(date.plusDays(1)))
                                           .orElseThrow(() ->
                  new SystemException(String.format("Exchange Rate not found - idBankAccountSource: %d; "
                                                  + "idBankAccountDestination: %d; date: %s", idBankAccountSource,
                                                     idBankAccountDestination, DateUtil.convertToString(date))));
    }

    @Transactional
    public ExchangeRateDto retrieveExchangeRate(int idBankAccountSource,
                                                int idBankAccountDestination,
                                                BigDecimal amountSource,
                                                LocalDate date){

        System.out.printf("as -->%d<--; ad -->%d<--; sa -->%s<--; d -->%s<--%n",idBankAccountSource,idBankAccountDestination,amountSource,date);

        ExchangeRateDto exchangeRateDto = new ExchangeRateDto();

        Optional<BankAccount> optionalBankAccountSource = this.bankAccountRepository.findOne(idBankAccountSource);
        Optional<BankAccount> optionalBankAccountDestination = this.bankAccountRepository.findOne(idBankAccountDestination);

        if (!optionalBankAccountSource.isPresent()) {
            exchangeRateDto.setCode(1);
            exchangeRateDto.setMessage("Source bank account was not found!!!");
        } else if(!optionalBankAccountDestination.isPresent()) {
            exchangeRateDto.setCode(2);
            exchangeRateDto.setMessage("Destination bank account not found!!!");
        } else {
            Optional<ExchangeRate> optionalExchangeRate = this.exchangeRateRepository.findOne(
                                                     optionalBankAccountSource.get().getCurrency().getIdCurrency(),
                                                     optionalBankAccountDestination.get().getCurrency().getIdCurrency(),
                                                     DateUtil.convertToDate(date),
                                                     DateUtil.convertToDate(date.plusDays(1)));

            exchangeRateDto = this.generateExchangeRate(idBankAccountSource,
                                                        idBankAccountDestination,
                                                        optionalBankAccountSource.get().getCurrency().getNameCurrency(),
                                                        optionalBankAccountDestination.get().getCurrency().getNameCurrency(),
                                                        optionalExchangeRate.map(ExchangeRate::getNumbExchangeRate).orElse(BigDecimal.ZERO),
                                                        amountSource,date);

            if (!optionalExchangeRate.isPresent()) {
                exchangeRateDto.setCode(3);
                exchangeRateDto.setMessage("Exchange Rate not found!!!");
            } else {
                exchangeRateDto.setMessage("Success");
            }
        }

        return exchangeRateDto;
    }

    public ExchangeRateDto generateExchangeRate(int idBankAccountSource,
                                                int idBankAccountDestination,
                                                String currencyNameSource,
                                                String currencyNameDestination,
                                                BigDecimal exchangeRate,
                                                BigDecimal amountSource,
                                                LocalDate date){

        ExchangeRateDto exchangeRateDto = new ExchangeRateDto();

        exchangeRateDto.setCode(0);
        exchangeRateDto.setIdBankAccountSource(idBankAccountSource);
        exchangeRateDto.setIdBankAccountDestination(idBankAccountDestination);
        exchangeRateDto.setCurrencyNameSource(currencyNameSource);
        exchangeRateDto.setCurrencyNameDestination(currencyNameDestination);
        exchangeRateDto.setExchangeRate(exchangeRate);
        exchangeRateDto.setAmountSource(amountSource);
        exchangeRateDto.setAmountDestination(amountSource.multiply(exchangeRate));
        exchangeRateDto.setDate(date.toString());

        return exchangeRateDto;
    }
}
