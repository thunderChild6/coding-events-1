package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventData;
import org.launchcode.codingevents.models.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Chris Bay
 */
@Controller
@RequestMapping("events")
public class EventController {

    @GetMapping
    public String displayAllEvents(Model model) {
        model.addAttribute("title", "All Events");
        model.addAttribute("events", EventData.getAll());
        return "events/index";
    }

    @GetMapping("create")
    public String displayCreateEventForm(Model model) {
        model.addAttribute("title", "Create Event");
        System.out.println("Create method reached--get");
        return "events/create";
    }

    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute Event newEvent) {
        EventData.add(newEvent);
        System.out.println("create method reached--post");
        return "redirect:";
    }

    @GetMapping("delete")
    public String displayDeleteEventForm(Model model) {
        model.addAttribute("title", "Delete Events");
        model.addAttribute("events", EventData.getAll());
        System.out.println("delete method reached--get");
        return "events/delete";
    }

    @PostMapping("delete")
    public String processDeleteEventsForm(@RequestParam(required = false) int[] eventIds) {
        if (eventIds != null) {
            for (int id : eventIds) {
                EventData.remove(id);
            }
        }
        System.out.println("delete method reached--post");
        return "redirect:";
    }

    @GetMapping("edit/{eventId}")
    public String displayEditForm(Model model, @PathVariable int eventId) {
        model.addAttribute("title", "Edit Event " + EventData.getById(eventId).getName() + " (id=" + eventId + ")");
        model.addAttribute("events", EventData.getById(eventId));
        System.out.println("displayEditForm method reached");
        return "events/edit";
    }

    @PostMapping("edit")
    public String processEditForm(int eventId, String name, String description) {
        System.out.println("processEditForm method reached");
        EventData.getById(eventId).setName(name);
        EventData.getById(eventId).setDescription(description);
        return "redirect:";
    }

    //Ch 11 Exercises
//    private static HashMap<String, String> events = new HashMap<>();
//
//    @GetMapping
//    public String displayAllEvents(Model model) {
//        model.addAttribute("title", "All Events");
//        model.addAttribute("events", events);
//        return "events/index";
//    }
//
//    @GetMapping("create")
//    public String displayCreateEventForm(Model model) {
//        model.addAttribute("title", "Create Event");
//        return "events/create";
//    }
//
//    @PostMapping("create")
//    public String processCreateEventForm(@RequestParam String eventName, String eventInfo) {
//        events.put(eventName, eventInfo);
//        return "redirect:";
//    }

}
