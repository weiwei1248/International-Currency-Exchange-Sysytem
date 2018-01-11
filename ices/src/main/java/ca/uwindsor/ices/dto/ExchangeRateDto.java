package ca.uwindsor.ices.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ExchangeRateDto extends BaseDto{
    int idBankAccountSource;
    int idBankAccountDestination;
    String currencyNameSource;
    String currencyNameDestination;
    BigDecimal exchangeRate;
    BigDecimal amountSource;
    BigDecimal amountDestination;
    String date;
}
