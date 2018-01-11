package ca.uwindsor.ices.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class TransactionDto extends BaseDto{
    int idTransaction;
    String sourceAccount;
    String destinationAccount;
    String exchangeRate;
    String sourceAmount;
    String destinationAmount;
}
