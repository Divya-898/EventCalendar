package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.EventModel;
import com.example.demo.service.EventService;

@Controller
public class EventController {
//display list of event
	@Autowired
	private EventService eventservice;
	@GetMapping("/")
	public String ViewHomePage(Model model) {
		//model.addAttribute("listEvent",eventservice.getAllEvent());
		return findPaginated(1, "EventName", "asc", model);
		
	}
	@GetMapping("/newEventForm")
	public String newEventForm(Model model) {
		EventModel event=new EventModel();
		model.addAttribute("event", event);
		return "new_event";
	}
	
    @PostMapping("/saveEvent")
	public String saveEvent(@ModelAttribute("event") EventModel event) {
		eventservice.saveEvent(event);
		return "redirect:/";
	}
    @GetMapping("/formForDelete/{id}")
    public String formForDelete(@PathVariable (value="id") long id) {
    	this.eventservice.formForDelete(id);
    	return "redirect:/";
    }
    @GetMapping("page/{pageno}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<EventModel> page = eventservice.findpaginated(pageNo, pageSize, sortField, sortDir);
		List<EventModel> listEvent = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
    	model.addAttribute("listEvent",listEvent);
    	return "index";
    	
    }
}
