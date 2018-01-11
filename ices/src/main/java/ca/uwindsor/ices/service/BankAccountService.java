package ca.uwindsor.ices.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.uwindsor.ices.dto.BankAccountDto;
import ca.uwindsor.ices.dto.OptionDto;
import ca.uwindsor.ices.jpa.BankAccount;
import ca.uwindsor.ices.jpa.Currency;
import ca.uwindsor.ices.jpa.Customer;
import ca.uwindsor.ices.jpa.User;
import ca.uwindsor.ices.repository.UserRepository;
import ca.uwindsor.ices.util.BankAccountUtil;

@Service
public class BankAccountService {

    private UserRepository userRepository;

    @Autowired
    BankAccountService(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Transactional(readOnly=true)
    List<BankAccount> listBankAccounts(String userName) {
        return this.userRepository.findByEmail(userName)
                         .map(User::getCustomer)
                         .map(Customer::getBankAccounts)
                         .orElse(new ArrayList<>());
    }

    @Transactional(readOnly=true)
    List<BankAccount> listActiveBankAccounts(String userName) {
        return this.listBankAccounts(userName).stream()
                     .filter(a ->"A".equalsIgnoreCase(a.getStatus()))
                     .collect(Collectors.toList());
    }

    /**
     * This method checks whether the user has two or more active bank accounts of
     * different currency.
     *
     * @param userName
     * @return boolean value
     */
    @Transactional(readOnly=true)
    public boolean hasTwoOrMoreBankAccounts(String userName) {
        return this.listActiveBankAccounts(userName).stream()
                                   .map(BankAccount::getCurrency)
                                   .map(Currency::getIdCurrency)
                                   .distinct()
                                   .count() > 1;
    }

//  boolean hasTwoOrMoreBankAccounts(String userName) {
//  return this.listActiveBankAccounts(userName).stream().collect(
//          Collectors.groupingBy(a -> a.getCurrency().getIdCurrency(), Collectors.counting()))
//                      .values().stream().anyMatch(v -> v>1);
//}
    @Transactional(readOnly=true)
    public List<OptionDto<Integer>> listActiveBankAccountsOptionView(String userName) {
        return this.listActiveBankAccounts(userName).stream()
                      .map(this::generateBankAccountOptionView).collect(Collectors.toList());
    }

    OptionDto<Integer> generateBankAccountOptionView(BankAccount bankAccount) {
        return new OptionDto<>(bankAccount.getIdBankAccount(),BankAccountUtil.toViewFormatLong(bankAccount));
    }

    @Transactional(readOnly=true)
    public List<BankAccountDto> listActiveBankAccountsSimpleView(String userName) {
        return this.listActiveBankAccounts(userName).stream()
                .map(this::generateBankAccountSimpleView).collect(Collectors.toList());
    }

    BankAccountDto generateBankAccountSimpleView(BankAccount bankAccount) {
        return new BankAccountDto(bankAccount.getCurrency().getNameCurrency(),
                BankAccountUtil.toViewFormatShort(bankAccount),bankAccount.getBank().getNameBank());
    }
}