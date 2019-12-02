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
		service.logoutPlayer();
		return "redirect:/";
	}

	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String remove(@ModelAttribute("tableData") TableData tableDat) {
		RowData toRemove = this.tableData.getTableData().stream()
				.filter(x -> tableDat.getRowToDelete() == x.getWagerId()).findAny().get();
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
		service.calculateResults();
		return "redirect:/home";
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home() {
		Player player = service.findPlayer();
		if (player == null) {
			return new ModelAndView("redirect:/");
		}
		ModelAndView mv = new ModelAndView("homepage", "player", player);
		mv.addObject("id", player.getId());
		Map<String, String> curopts = new HashMap<>();
		for (var x : Currency.values()) {
			curopts.put(x.name(), x.name());
		}
		mv.addObject("curopts", curopts);
		addTableContent(mv);
		localizeHomePage(mv);
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
				d.setEventType(
						getEnglishStringp(w.getOdd().getOutcome().getBet().getSportEvent().getClass().getName(), null));
				d.setBetType(w.getOdd().getOutcome().getBet().getDescription());
				d.setOutcome(w.getOdd().getOutcome().getDescription());
				d.setOutcomeOdd("1:" + w.getOdd().getOddValue().intValue());
				d.setWagerAmount(w.getAmount().intValue() + " " + w.getPlayer().getCurrency());
				if (d.isRemoveIsVisible()) {
					d.setWin(getEnglishStringp("unknown", null));
					d.setProcessed(getEnglishStringp("unknown", null));
				}
				else {
					d.setWin(getEnglishStringp(String.valueOf(w.isWin()), null));
					d.setProcessed(getEnglishStringp(String.valueOf(w.isProcessed()), null));
				}
				d.setClassNameOnButton(d.isRemoveIsVisible() ? "visible-remove" : "hide-remove");
				d.setWagerId(w.getId());
				counter++;
				rowData.add(d);
			}
		}
		tableData = new TableData();
		tableData.setTableData(rowData);
		mv.addObject("tableData", tableData);
	}

	private void localizeHomePage(ModelAndView mv) {
		mv.addObject("sportsbetting", getEnglishStringp("sportsBetting", null));
		mv.addObject("home", getEnglishStringp("home", null));
		mv.addObject("events", getEnglishStringp("events", null));
		mv.addObject("language", getEnglishStringp("language", null));
		mv.addObject("english", getEnglishStringp("english", null));
		mv.addObject("hungarian", getEnglishStringp("hungarian", null));
		mv.addObject("playerDetails", getEnglishStringp("playerDetails", null));
		mv.addObject("namelbl", getEnglishStringp("name", null));
		mv.addObject("birthlbl", getEnglishStringp("birth", null));
		mv.addObject("accountNumberlbl", getEnglishStringp("accountNumber", null));
		mv.addObject("currency", getEnglishStringp("currency", null));
		mv.addObject("balancelbl", getEnglishStringp("balance", null));
		mv.addObject("save", getEnglishStringp("save", null));
		mv.addObject("wagers", getEnglishStringp("wagers", null));
		mv.addObject("remove", getEnglishStringp("remove", null));
		mv.addObject("eventTitle", getEnglishStringp("eventTitle", null));
		mv.addObject("eventType", getEnglishStringp("eventType", null));
		mv.addObject("betType", getEnglishStringp("betType", null));
		mv.addObject("outcomeValue", getEnglishStringp("outcomeValue", null));
		mv.addObject("outcomeOdd", getEnglishStringp("outcomeOdd", null));
		mv.addObject("wagerAmount", getEnglishStringp("wagerAmount", null));
		mv.addObject("true", getEnglishStringp("true", null));
		mv.addObject("false", getEnglishStringp("false", null));
		mv.addObject("unknown", getEnglishStringp("unknown", null));
		mv.addObject("logout", getEnglishStringp("logout", null));
		mv.addObject("winner", getEnglishStringp("winner", null));
		mv.addObject("processedd", getEnglishStringp("processed", null));
	}

	private String getEnglishStringp(String key, Object param) {
		return messageSource.getMessage(key, new Object[] { param }, Locale.ENGLISH);
	}

}
