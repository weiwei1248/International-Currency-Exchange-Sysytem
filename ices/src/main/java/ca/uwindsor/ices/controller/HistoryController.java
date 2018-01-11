package ca.uwindsor.ices.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import ca.uwindsor.ices.jpa.ExchangeTransaction;
import ca.uwindsor.ices.service.HistoryService;

@Controller
public class HistoryController {
	@Autowired
	private HistoryService historyService;
	
	@GetMapping("/history")
	public String pageHistory() {
		return "history";
	}
	
	@RequestMapping(value = "/history", method = RequestMethod.POST)
	public ModelAndView postHistory(HttpServletRequest request, @ModelAttribute("userName") String userName) {
		String datestr = request.getParameter("datesearch");
		try {
			DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");
			Date date = fmt.parse(datestr);
			List<ExchangeTransaction> result = historyService.getExchangeTransactionHistory(date);
			ModelAndView mav = new ModelAndView("history");
			if(result != null && result.size() > 0) {
				mav.addObject("histories", result);
			}
			return mav;			
		} catch(Exception e) {
			return new ModelAndView(new RedirectView("/error"));
		}
	}
	
	
}