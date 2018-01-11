package ca.uwindsor.ices.controller;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ca.uwindsor.ices.dto.BaseDto;
import ca.uwindsor.ices.dto.ExchangeRateDto;
import ca.uwindsor.ices.service.BankAccountService;
import ca.uwindsor.ices.service.ExchangeRateService;
import ca.uwindsor.ices.service.TransactionService;

@Controller
public class TransactionController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private BankAccountService bankAccountService;
    private TransactionService transactionService;
    private ExchangeRateService exchangeRateService;

    @Autowired
    public TransactionController(BankAccountService bankAccountService,
                                 TransactionService transactionService,
                                 ExchangeRateService exchangeRateService) {
        super();
        this.bankAccountService = bankAccountService;
        this.transactionService = transactionService;
        this.exchangeRateService = exchangeRateService;
    }

    @GetMapping("/transaction")
    public ModelAndView showPage(@ModelAttribute("userName") String userName) {

        logger.debug("(GET) /transaction : ({})", userName);

        return new ModelAndView(
                this.bankAccountService.hasTwoOrMoreBankAccounts(userName)?"transaction":"redirect:/binding?error",
               "bankAccounts",this.bankAccountService.listActiveBankAccountsOptionView(userName));
    }

    @PostMapping("/transaction")
    public ModelAndView createTransaction(@ModelAttribute("userName") String userName,
                                          @RequestParam("sourceAccount") Integer idBankAccountSource,
                                          @RequestParam("destinationAccount") Integer idBankAccountDestination,
                                          @RequestParam("sourceAmount") BigDecimal amountSource) {

        logger.debug("(POST) /transaction : ({},{},{},{})",userName,idBankAccountSource,idBankAccountDestination,amountSource);

        BaseDto response = this.transactionService.createTransaction(userName,idBankAccountSource,idBankAccountDestination,amountSource);

        return new ModelAndView((response.getCode()==0)?"transaction_success":
                          String.format("redirect:/transaction?error=%s",response.getMessage()),"transaction",response);
    }

    @ResponseBody
    @GetMapping("/transaction/exchangeRate")
    public  ExchangeRateDto retrieveExchangeRate(@RequestParam("sourceAccount") Integer idBankAccountSource,
                                                  @RequestParam("destinationAccount") Integer idBankAccountDestination,
                                                  @RequestParam("sourceAmount") BigDecimal amountSource) {

        logger.debug("(GET) /transaction/exchangerate : ({},{},{})", idBankAccountSource, idBankAccountDestination, amountSource);

        return this.exchangeRateService.retrieveExchangeRate(idBankAccountSource, idBankAccountDestination, amountSource, LocalDate.now());
    }
}
