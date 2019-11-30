package com.sportbettings.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sportbettings.model.TableData;
import com.sportsbettings.ISportsBettingService;
import com.sportsbettings.domain.Currency;
import com.sportsbettings.domain.Player;
import com.sportsbettings.domain.Wager;

@Controller
public class HomeController {

	@Autowired
	private ISportsBettingService service;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String home(@ModelAttribute("player") Player player, @RequestParam(value = "id") String id) {
		player.setId(Integer.parseInt(id));
		service.savePlayer(player);
		service.createTestData(); // hogy lássunk is valamit
		return "redirect:/home";
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home() {
		Player player = service.findPlayer();
		ModelAndView mv = new ModelAndView("homepage", "player", player);
		mv.addObject("id", player.getId());
		mv.addObject("namelbl", "Name");
		mv.addObject("dateofblbl", "Date of birth");
		mv.addObject("accnumlbl", "Account number");
		mv.addObject("currencylbl", "Currency");
		mv.addObject("balancelbl", "Balance");
		Map<String, String> curopts = new HashMap<>();
		for (var x : Currency.values()) {
			curopts.put(x.name(), x.name());
		}
		mv.addObject("curopts", curopts);
		addTableContent(mv);
		return mv;
	}

	private void addTableContent(ModelAndView mv) {
		List<TableData> rowData = new ArrayList<TableData>();
		List<Wager> wagers = service.findAllWagers();
		Player player = service.findPlayer();
		int counter = 1;
		for (Wager w : wagers) {
			if (player.equals(w.getPlayer())) {
				TableData d = new TableData();
				d.setRemoveIsVisible(service.eventNotStarted(w));
				d.setIndex(counter);
				d.setEventTitle(w.getOdd().getOutcome().getBet().getSportEvent().getTitle());
				d.setEventType(w.getOdd().getOutcome().getBet().getSportEvent().getClass().getName());
				d.setBetType(w.getOdd().getOutcome().getBet().getDescription());
				d.setOutcome(w.getOdd().getOutcome().getDescription());
				d.setOutcomeOdd("1:" + w.getOdd().getOddValue());
				d.setWagerAmount(w.getAmount() + " " + w.getPlayer().getCurrency());
				d.setWin(w.isWin());
				d.setProcessed(w.isProcessed());
				counter++;
				rowData.add(d);
			}
		}
		mv.addObject("tableData", rowData);
	}

}
