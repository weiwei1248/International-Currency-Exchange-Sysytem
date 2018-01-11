package ca.uwindsor.ices.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ca.uwindsor.ices.jpa.Bank;

public interface BankRepository extends BaseRepository<Bank, Integer>{

	Optional<Bank> findByNameBank(String nameBank);

}
