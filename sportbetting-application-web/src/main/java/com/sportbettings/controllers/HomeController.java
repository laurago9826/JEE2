package com.sportbettings.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sportbettings.model.RowData;
import com.sportbettings.model.TableData;
import com.sportsbettings.ISportsBettingService;
import com.sportsbettings.domain.Currency;
import com.sportsbettings.domain.Player;
import com.sportsbettings.domain.Wager;

@Controller
public class HomeController {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private ISportsBettingService service;

	private TableData tableData;

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String logout() {
		return "redirect:/";
	}

	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String remove(@ModelAttribute("tableData") TableData tableDat) {
		RowData toRemove = this.tableData.getTableData().stream()
				.filter(x -> tableDat.getRowToDelete() == x.getWagerId())
				.findAny().get();
		List<RowData> newTable = tableData.getTableData();
		newTable.remove(toRemove);
		tableData.setTableData(newTable);
		service.deleteWager(toRemove.getWagerId());
		return "redirect:/home";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("player") Player player, @RequestParam(value = "id") String id) {
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
		List<RowData> rowData = new ArrayList<RowData>();
		List<Wager> wagers = service.findAllWagers();
		Player player = service.findPlayer();
		int counter = 1;
		for (Wager w : wagers) {
			if (player.equals(w.getPlayer())) {
				RowData d = new RowData();
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
				d.setWagerId(w.getId());
				counter++;
				rowData.add(d);
			}
		}
		tableData = new TableData();
		tableData.setTableData(rowData);
		mv.addObject("tableData", tableData);
	}

	private String getEnglishStringp(String key, Object param) {
		return messageSource.getMessage(key, new Object[] { param }, Locale.ENGLISH);
	}

	private String getEnglishString(String key, Object... params) {
		return messageSource.getMessage(key, params, Locale.ENGLISH);
	}

}
