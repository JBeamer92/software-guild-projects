/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Power;
import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.service.SHSService;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author jared
 */
@CrossOrigin
@Controller
public class SHS_RESTController {
    
    private SHSService service;
    
    @Inject
    public SHS_RESTController(SHSService service) {
        this.service = service;
    }
    
//    @RequestMapping(value="/getHero", method=RequestMethod.GET)
//    @ResponseBody
//    public Hero getHero(HttpServletRequest request) {
//        int heroId = Integer.parseInt(request.getParameter("heroId"));
//        return service.getHeroById(heroId);
//    }
    @RequestMapping(value="/getPower", method=RequestMethod.GET)
    @ResponseBody
    public Power getPower(HttpServletRequest request) {
        try {
            int powerId = Integer.parseInt(request.getParameter("powerId"));
            return service.getPowerById(powerId);
        } catch (NumberFormatException ex) {
            return null;
        }
    }
    
    @RequestMapping(value="/recentSightings", method=RequestMethod.GET)
    @ResponseBody
    public List<Sighting> getMostRecentSightings() {
        return service.getMostRecentSightings();
    }
    
//    @RequestMapping(value="/location", method=RequestMethod.POST)
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void addLocation(@RequestBody Location loc) {
//        service.addLocation(loc);
//    }
    
//    @RequestMapping(value="/allLocations", method=RequestMethod.GET)
//    @ResponseBody
//    public List<Location> getLocations() {
//        List<Location> locs = service.getAllLocations();
//        return locs;
//    }
    
}
