package ca.uwindsor.ices.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ca.uwindsor.ices.dto.BaseDto;
import ca.uwindsor.ices.exception.SystemException;
import ca.uwindsor.ices.jpa.BankAccount;
import ca.uwindsor.ices.jpa.Customer;
import ca.uwindsor.ices.jpa.MasterBankAccount;
import ca.uwindsor.ices.jpa.User;
import ca.uwindsor.ices.repository.BankAccountRepository;
import ca.uwindsor.ices.repository.MasterBankAccountRepository;

@Service
public class MasterBankAccountService {

    private BankAccountRepository bankAccountRepository;
    private MasterBankAccountRepository masterBankAccountRepository;

    @Autowired
    MasterBankAccountService(BankAccountRepository bankAccountRepository,
                             MasterBankAccountRepository masterBankAccountRepository) {
        super();
        this.bankAccountRepository = bankAccountRepository;
        this.masterBankAccountRepository = masterBankAccountRepository;
    }

    @Transactional(propagation=Propagation.REQUIRED)
    public BaseDto transferFunds(String userName,
                                  int idBankAccountSource,
                                  int idBankAccountDestination,
                                  BigDecimal amountSource,
                                  BigDecimal amountDestination){

        BaseDto response = new BaseDto();

        MasterBankAccount masterBankAccountSource = this.retrieveMasterBankAccount(userName, idBankAccountSource);
        MasterBankAccount masterBankAccountDestination = this.retrieveMasterBankAccount(userName, idBankAccountDestination);

        if (masterBankAccountSource.getNumbAccountBalance().compareTo(amountSource)<0) {
            response = new BaseDto(1,String.format("BankAccount does not have enough Funds"));
        } else {
            masterBankAccountSource
             .setNumbAccountBalance(masterBankAccountSource.getNumbAccountBalance().subtract(amountSource));
            masterBankAccountDestination
             .setNumbAccountBalance(masterBankAccountDestination.getNumbAccountBalance().add(amountDestination));
        }

        return response;
    }

    private MasterBankAccount retrieveMasterBankAccount(String userName, int idBankAccount) {

        BankAccount bankAccount = this.bankAccountRepository.findOne(idBankAccount)
         .orElseThrow(()-> new SystemException(String.format("BankAccount not found - idBankAccount: %d",idBankAccount)));

        Optional.ofNullable(bankAccount.getCustomer())
                 .map(Customer::getUser)
                 .map(User::getEmail)
                 .filter(userName::equalsIgnoreCase)
                 .orElseThrow(() -> new SystemException(
                       String.format("BankAccount and User are not Associated - idBankAccount: %d; userName: %s", idBankAccount, userName)));

        if (!"A".equalsIgnoreCase(bankAccount.getStatus())) {
            new SystemException(String.format("BankAccount is not Active - idBankAccount: %d", idBankAccount));
        }

        MasterBankAccount masterBankAccount = this.masterBankAccountRepository
         .findByAccountNumber(bankAccount.getCodeBankTransit(),bankAccount.getCodeInstitution(),bankAccount.getNumbAccount())
         .orElseThrow(()-> new SystemException(String.format("MasterBankAccount not found - idBankAccount: %d",idBankAccount)));

        if (!"A".equalsIgnoreCase(masterBankAccount.getStatus())) {
            new SystemException(String.format("MasterBankAccount is not Active - idBankAccount: %d", idBankAccount));
        }

        return masterBankAccount;
    }
}