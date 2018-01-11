package ca.uwindsor.ices.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountDto {
    String currency;
    String accountNumber;
    String bankName;
}
