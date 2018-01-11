package ca.uwindsor.ices.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.uwindsor.ices.jpa.ExchangeTransaction;
import ca.uwindsor.ices.repository.ExchangeTransactionRepository;

@Service
public class HistoryService {
	
	@Autowired
	private ExchangeTransactionRepository etpo;
	
	public List<ExchangeTransaction> getExchangeTransactionHistory(Date date) {
		return etpo.findByDateRegist(date);
	}
	
	public List<ExchangeTransaction> getAll() {
		return etpo.findAll();
	} 
}
