package ca.uwindsor.ices.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ca.uwindsor.ices.jpa.ExchangeRate;

@Repository
public interface ExchangeRateRepository extends BaseRepository<ExchangeRate, Integer> {

    @Query(value="SELECT * FROM EXCHANGE_RATE WHERE ID_CURRENCY_SOURCE = :idCurrencySource AND "
                                            + "ID_CURRENCY_DESTINA = :idCurrencyDestination AND "
                                            + "DATE_EXCHANGE_RATE >= :dateIni AND "
                                            + "DATE_EXCHANGE_RATE <= :dateFin "
                                            + "ORDER BY DATE_EXCHANGE_RATE DESC LIMIT 1", nativeQuery=true)
    public Optional<ExchangeRate> findOne(@Param("idCurrencySource") Integer idCurrencySource,
                                          @Param("idCurrencyDestination") Integer idCurrencyDestination,
                                          @Param("dateIni") Date dateIni,
                                          @Param("dateFin") Date dateFin);
    
}