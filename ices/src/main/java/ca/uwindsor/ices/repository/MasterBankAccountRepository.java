package ca.uwindsor.ices.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ca.uwindsor.ices.jpa.MasterBankAccount;

@Repository
public interface MasterBankAccountRepository extends BaseRepository<MasterBankAccount, Integer> {

    public Optional<MasterBankAccount> findByCodeBankTransitAndCodeInstitutionAndNumbAccount(int codeBankTransit, int codeInstitution, int numbAccount);

    @Query("SELECT a FROM MasterBankAccount a WHERE a.codeBankTransit = :codeBankTransit AND "
                                                 + "a.codeInstitution = :codeInstitution AND "
                                                 + "a.numbAccount = :numbAccount")
    public Optional<MasterBankAccount> findByAccountNumber(@Param("codeBankTransit") Integer codeBankTransit,
                                                           @Param("codeInstitution") Integer codeInstitution,
                                                           @Param("numbAccount") Integer numbAccount);

}
