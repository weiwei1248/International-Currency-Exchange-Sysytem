package ca.uwindsor.ices.util;

import org.apache.commons.lang3.StringUtils;

import ca.uwindsor.ices.jpa.BankAccount;

public class BankAccountUtil {

    public BankAccountUtil() {
    }

    public static String toViewFormatLong(BankAccount bankAccount) {
        return String.format("%s | %s %s %s | %s",
                bankAccount.getCurrency().getCodeCurrency(),
                StringUtils.leftPad(String.valueOf(bankAccount.getCodeBankTransit()), 3, "0"),
                StringUtils.leftPad(String.valueOf(bankAccount.getCodeInstitution()), 3, "0"),
                StringUtils.leftPad(String.valueOf(bankAccount.getNumbAccount()), 8, "0"),
                bankAccount.getBank().getNameBank());
    }

    public static String toViewFormatShort(BankAccount bankAccount) {
        return String.format("%s %s %s",
                StringUtils.leftPad(String.valueOf(bankAccount.getCodeBankTransit()), 3, "0"),
                StringUtils.leftPad(String.valueOf(bankAccount.getCodeInstitution()), 3, "0"),
                StringUtils.leftPad(String.valueOf(bankAccount.getNumbAccount()), 8, "0"));
    }

}
