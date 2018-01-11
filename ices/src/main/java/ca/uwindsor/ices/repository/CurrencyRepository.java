package ca.uwindsor.ices.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ca.uwindsor.ices.jpa.Currency;

public interface CurrencyRepository extends BaseRepository<Currency, Integer>{

	Optional<Currency> findByNameCurrency(String nameCurrency);

}
