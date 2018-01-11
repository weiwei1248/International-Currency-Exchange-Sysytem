package ca.uwindsor.ices.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ca.uwindsor.ices.jpa.ExchangeTransaction;

@Repository
public interface ExchangeTransactionRepository extends BaseRepository<ExchangeTransaction, Integer> {

	@Query(value="SELECT a FROM ExchangeTransaction a WHERE a.dateRegist = ?1")
	List<ExchangeTransaction> findByDateRegist(Date dateRegist);
	
	@Query(value = "SELECT a FROM ExchangeTransaction a")
	List<ExchangeTransaction> findAll();
	
}
