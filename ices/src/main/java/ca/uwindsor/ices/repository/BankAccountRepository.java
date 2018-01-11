package ca.uwindsor.ices.repository;

import org.springframework.stereotype.Repository;

import ca.uwindsor.ices.jpa.BankAccount;

@Repository
public interface BankAccountRepository extends BaseRepository<BankAccount, Integer> {

}
