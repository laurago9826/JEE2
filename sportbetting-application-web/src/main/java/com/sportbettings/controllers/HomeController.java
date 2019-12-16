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

import com.sportbettings.model.RowData;
import com.sportbettings.model.TableData;
import com.sportsbettings.ISportsBettingService;
import com.sportsbettings.domain.Currency;
import com.sportsbettings.domain.Player;
import com.sportsbettings.domain.Wager;

@Controller
public class HomeController {

	@Autowired
	private ISportsBettingService service;

	private TableData tableData;

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String logout() {
		service.logoutPlayer();
		return "redirect:/login";
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
		// player.setId(Integer.parseInt(id));
		service.savePlayer(player);
		service.saveTestWagers();
		service.calculateResults();
		return "redirect:/home";
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home() {
		Player player = service.findPlayer();
		ModelAndView mv = new ModelAndView("homepage", "player", player);
		mv.addObject("id", player.getId());
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
				d.setBetType(w.getOdd().getOutcome().getBet().getBetType().name().toLowerCase());
				d.setOutcome(w.getOdd().getOutcome().getDescription());
				d.setOutcomeOdd("1:" + w.getOdd().getOddValue().intValue());
				d.setWagerAmount(w.getAmount().intValue() + " " + w.getPlayer().getCurrency());
				if (d.isRemoveIsVisible()) {
					d.setWin("unknown");
					d.setProcessed("unknown");
				}
				else {
					d.setWin(String.valueOf(w.isWin()));
					d.setProcessed(String.valueOf(w.isProcessed()));
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
}
