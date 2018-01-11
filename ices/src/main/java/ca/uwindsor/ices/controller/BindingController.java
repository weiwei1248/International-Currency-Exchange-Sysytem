package ca.uwindsor.ices.controller;

import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ca.uwindsor.ices.jpa.Bank;
import ca.uwindsor.ices.jpa.BankAccount;
import ca.uwindsor.ices.jpa.Currency;
import ca.uwindsor.ices.jpa.Customer;
import ca.uwindsor.ices.jpa.MasterBankAccount;
import ca.uwindsor.ices.jpa.User;
import ca.uwindsor.ices.repository.BankAccountRepository;
import ca.uwindsor.ices.repository.BankRepository;
import ca.uwindsor.ices.repository.CurrencyRepository;
import ca.uwindsor.ices.repository.MasterBankAccountRepository;
import ca.uwindsor.ices.repository.UserRepository;
import ca.uwindsor.ices.service.BankAccountService;
import ca.uwindsor.ices.service.BindingService;

@Controller
public class BindingController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository usrRepo;

    @Autowired
    private BankAccountRepository bankacRepo;

    @Autowired
    private CurrencyRepository currRepo;

    @Autowired
    private BankRepository bankRepo;

    @Autowired
    private MasterBankAccountRepository masterRepo;

    @Autowired
    private BCryptPasswordEncoder bcpe;

    private BindingService bindingService;
    private BankAccountService bankAccountService;

    @Autowired
    BindingController(BindingService bindingService,
                      BankAccountService bankAccountService){
        super();
        this.bindingService = bindingService;
        this.bankAccountService = bankAccountService;
    }

    @GetMapping("/binding")
    public ModelAndView showPage(@ModelAttribute("userName") String userName) {
        logger.debug("(GET) /binding : ({})", userName);
        return new ModelAndView(bankAccountService.hasTwoOrMoreBankAccounts(userName)?"binding":"redirect:/binding?error",
                                                "bankAccounts",bankAccountService.listActiveBankAccountsSimpleView(userName));
    }

    @RequestMapping(value = "/binding", method = RequestMethod.POST)
    public String postBind(@ModelAttribute("userName") String userName, HttpServletRequest request) {

        User usr = usrRepo.findByEmail(userName).get();
        Customer cust = usr.getCustomer();

        //check if the bank account has already existed in the MasterbankAcccount
        String pass1 = bcpe.encode(request.getParameter("password"));
        Optional<MasterBankAccount> tmp = masterRepo.findByCodeBankTransitAndCodeInstitutionAndNumbAccount(
                Integer.parseInt(request.getParameter("account1")),
                Integer.parseInt(request.getParameter("account2")),
                Integer.parseInt(request.getParameter("account3")));

        if (tmp.isPresent()) {
            String pass2 = tmp.get().getPassword();
            if (pass1.equals(pass2)){
                Optional<Bank> bank = bankRepo.findByNameBank(request.getParameter("bank"));
                Optional<Currency> currency = currRepo.findByNameCurrency(request.getParameter("currency"));

                BankAccount account = new BankAccount();
                account.setNumbAccount(Integer.parseInt(request.getParameter("account3")));
                account.setCodeInstitution(Integer.parseInt(request.getParameter("account2")));
                account.setCodeBankTransit(Integer.parseInt(request.getParameter("account1")));
                account.setBank(bank.get());
                account.setCurrency(currency.get());
                account.setCustomer(cust);
                account.setDateRegist(new Date(System.currentTimeMillis()));
                account.setStatus("A");
                account.setUserRegist("SYSTEM");
                bankacRepo.save(account);
            }
            else {
                return "redirect:/binding?error";
            }
        }
        else {
            return "redirect:/binding?error";
        }
        return "bind-confirmation";
    }
}