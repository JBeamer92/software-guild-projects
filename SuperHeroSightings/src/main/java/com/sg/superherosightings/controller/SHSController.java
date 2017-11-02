/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.model.Hero;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.Power;
import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.service.SHSService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author jared
 */
@Controller
public class SHSController {

    private SHSService service;

    @Inject
    public SHSController(SHSService service) {
        this.service = service;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displayHomePage(Model model) {
        List<Sighting> recentSights = service.getMostRecentSightings();
        model.addAttribute("sights", recentSights);
        return "index";
    }

    @RequestMapping(value = "/heroes", method = RequestMethod.GET)
    public String displayHeroesPage(Model model) {

        List<Hero> heroes = service.getAllHeroes();
        List<Power> powers = service.getAllPowers();
        model.addAttribute("heroes", heroes);
        model.addAttribute("powers", powers);
        return "heroes";
    }

    @RequestMapping(value = "/displayEditPowers", method = RequestMethod.GET)
    public String displayPowersPage(Model model) {
        List<Power> powers = service.getAllPowers();
        model.addAttribute("powers", powers);
        return "powers";
    }

    @RequestMapping(value = "/orgs", method = RequestMethod.GET)
    public String displayOrgsPage(Model model) {
        List<Organization> orgs = service.getAllOrgs();
        List<Location> locs = service.getAllLocations();
        List<Hero> heroes = service.getAllHeroes();
        model.addAttribute("orgs", orgs);
        model.addAttribute("locs", locs);
        model.addAttribute("heroes", heroes);
        return "orgs";
    }

    @RequestMapping(value = "/locations", method = RequestMethod.GET)
    public String displayLocationsPage(Model model) {
        List<Location> locs = service.getAllLocations();
        model.addAttribute("locs", locs);
        return "locations";
    }

    @RequestMapping(value = "/sightings", method = RequestMethod.GET)
    public String displaySightingsPage(Model model) {
        List<Sighting> sights = service.getAllSightings();
        List<Location> locs = service.getAllLocations();
        List<Hero> heroes = service.getAllHeroes();
        model.addAttribute("sights", sights);
        model.addAttribute("locs", locs);
        model.addAttribute("heroes", heroes);

        return "sightings";
    }

    @RequestMapping(value = "/createHero", method = RequestMethod.POST)
    public String createHero(HttpServletRequest request, Model model) {
        String heroName = request.getParameter("heroName");
        String heroDesc = request.getParameter("heroDescription");
//        String[] heroPowerNames = request.getParameterValues("heroPowers");
        String[] heroPowerIds = request.getParameterValues("heroPowers");
        List<Power> allPowers = service.getAllPowers();
        List<Power> heroPowers = new ArrayList<>();

        for (String powerIdStr : heroPowerIds) {
            int powerId = 0;
            try {
                powerId = Integer.parseInt(powerIdStr);
                heroPowers.add(service.getPowerById(powerId));

            } catch (NumberFormatException ex) {
                System.out.println("Not a valid number.");
            }
        }
        Hero newHero = new Hero();
        newHero.setName(heroName);
        newHero.setDescription(heroDesc);
        newHero.setPowers(heroPowers);

        service.addHero(newHero);

        List<Hero> allHeroes = service.getAllHeroes();
        model.addAttribute("heroes", allHeroes);
        model.addAttribute("powers", allPowers);

        return "heroes";
    }

    @RequestMapping(value = "/deleteHero", method = RequestMethod.GET)
    public String deleteHero(HttpServletRequest request, Model model) {

        try {
            int heroId = Integer.parseInt(request.getParameter("heroId"));
            Hero hero = service.getHeroById(heroId);
            service.deleteHero(hero);
        } catch (NumberFormatException ex) {
            System.out.println("Not a valid number.");
        }

        List<Power> powers = service.getAllPowers();
        List<Hero> heroes = service.getAllHeroes();
        model.addAttribute("heroes", heroes);
        model.addAttribute("powers", powers);
        return "heroes";
    }

    @RequestMapping(value = "/displayEditHero", method = RequestMethod.GET)
    public String displayEditHeroPage(HttpServletRequest request, Model model) {

        try {
            int heroId = Integer.parseInt(request.getParameter("heroId"));
            Hero hero = service.getHeroById(heroId);
            List<Power> powers = service.getAllPowers();
            model.addAttribute("hero", hero);
            model.addAttribute("powers", powers);
        } catch (NumberFormatException ex) {
            System.out.println("Not a valid number.");
        }
        return "editHero";
    }

    @RequestMapping(value = "/editHero", method = RequestMethod.POST)
    public String editHero(HttpServletRequest request, Model model) {
        int heroId = 0;

        try {
            heroId = Integer.parseInt(request.getParameter("heroId"));
            String heroName = request.getParameter("heroName");
            String heroDesc = request.getParameter("heroDescription");
            String[] heroPowerIds = request.getParameterValues("heroPowers");

            List<Power> heroPowers = new ArrayList<>();

            for (String powerIdStr : heroPowerIds) {
                int powerId = 0;
                try {
                    powerId = Integer.parseInt(powerIdStr);
                    heroPowers.add(service.getPowerById(powerId));
                } catch (NumberFormatException ex) {
                    System.out.println("Not a valid number.");
                }
            }

            Hero editedHero = new Hero();
            editedHero.setId(heroId);
            editedHero.setName(heroName);
            editedHero.setDescription(heroDesc);
            editedHero.setPowers(heroPowers);

            service.editHero(editedHero);
        } catch (NumberFormatException ex) {
            System.out.println("Not a valid number.");
        }

        List<Hero> allHeroes = service.getAllHeroes();
        List<Power> allPowers = service.getAllPowers();
        model.addAttribute("heroes", allHeroes);
        model.addAttribute("powers", allPowers);

        return "heroes";
    }

    @RequestMapping(value = "/createOrg", method = RequestMethod.POST)
    public String createOrg(HttpServletRequest request, Model model) {

        String orgName = request.getParameter("orgName");
        String orgDesc = request.getParameter("orgDesc");
        int locId = 0;
        try {
            locId = Integer.parseInt(request.getParameter("orgLocation"));
            Location orgLoc = service.getLocById(locId);
            String orgPhoneType = request.getParameter("orgPhoneType");
            String orgPhoneNum = request.getParameter("orgPhoneNum");
            String[] heroIdStrings = request.getParameterValues("orgHeroes");
            List<Hero> orgHeroes = new ArrayList<>();

            for (String idString : heroIdStrings) {
                int heroId = Integer.parseInt(idString);
                orgHeroes.add(service.getHeroById(heroId));
            }

            Organization newOrg = new Organization();
            newOrg.setName(orgName);
            newOrg.setDescription(orgDesc);
            newOrg.setLocation(orgLoc);
            newOrg.setPhoneType(orgPhoneType);
            newOrg.setPhoneNumber(orgPhoneNum);
            newOrg.setHeroes(orgHeroes);

            service.addOrg(newOrg);
        } catch (NumberFormatException ex) {
            System.out.println("Not a valid number.");
        }

        List<Organization> orgs = service.getAllOrgs();
        List<Location> locs = service.getAllLocations();
        List<Hero> heroes = service.getAllHeroes();

        model.addAttribute("orgs", orgs);
        model.addAttribute("locs", locs);
        model.addAttribute("heroes", heroes);
        return "orgs";
    }

    @RequestMapping(value = "/deleteOrg", method = RequestMethod.GET)
    public String deleteOrg(HttpServletRequest request, Model model) {

        try {
            int orgId = Integer.parseInt(request.getParameter("orgId"));

            Organization toDelete = service.getOrgById(orgId);
            service.deleteOrg(toDelete);
        } catch (NumberFormatException ex) {
            System.out.println("Not a valid number.");
        }

        List<Organization> orgs = service.getAllOrgs();
        List<Location> locs = service.getAllLocations();
        List<Hero> heroes = service.getAllHeroes();

        model.addAttribute("orgs", orgs);
        model.addAttribute("locs", locs);
        model.addAttribute("heroes", heroes);
        return "orgs";
    }

    @RequestMapping(value = "/editOrgForm", method = RequestMethod.GET)
    public String displayEditOrgForm(HttpServletRequest request, Model model) {
        try {
            int orgId = Integer.parseInt(request.getParameter("orgId"));
            Organization toEdit = service.getOrgById(orgId);
            model.addAttribute("org", toEdit);
        } catch (NumberFormatException ex) {
            System.out.println("Not a valid number.");
        }

        List<Location> locs = service.getAllLocations();
        List<Hero> heroes = service.getAllHeroes();
        model.addAttribute("locs", locs);
        model.addAttribute("heroes", heroes);
        return "editOrg";
    }

    @RequestMapping(value = "/editOrg", method = RequestMethod.POST)
    public String editOrg(HttpServletRequest request, Model model) {
        try {
            int orgId = Integer.parseInt(request.getParameter("orgId"));
            String orgName = request.getParameter("orgName");
            String orgDesc = request.getParameter("orgDesc");
            int locId = Integer.parseInt(request.getParameter("orgLocation"));
            Location orgLoc = service.getLocById(locId);
            String orgPhoneType = request.getParameter("orgPhoneType");
            String orgPhoneNum = request.getParameter("orgPhoneNum");
            String[] heroIdStrings = request.getParameterValues("orgHeroes");
            List<Hero> orgHeroes = new ArrayList<>();

            for (String idString : heroIdStrings) {
                int heroId = Integer.parseInt(idString);
                orgHeroes.add(service.getHeroById(heroId));
            }

            Organization toEdit = new Organization();
            toEdit.setId(orgId);
            toEdit.setName(orgName);
            toEdit.setDescription(orgDesc);
            toEdit.setLocation(orgLoc);
            toEdit.setPhoneType(orgPhoneType);
            toEdit.setPhoneNumber(orgPhoneNum);
            toEdit.setHeroes(orgHeroes);

            service.editOrg(toEdit);
        } catch (NumberFormatException ex) {
            System.out.println("Not a valid number.");
        }

        List<Organization> orgs = service.getAllOrgs();
        List<Location> locs = service.getAllLocations();
        List<Hero> heroes = service.getAllHeroes();

        model.addAttribute("orgs", orgs);
        model.addAttribute("locs", locs);
        model.addAttribute("heroes", heroes);
        return "orgs";
    }

    @RequestMapping(value = "/createLocation", method = RequestMethod.POST)
    public String addLocation(HttpServletRequest request, Model model) {
        String locName = request.getParameter("locName");
        String locDesc = request.getParameter("locDesc");
        String locAddr = request.getParameter("locAddress");
        String locCity = request.getParameter("locCity");
        String locRegion = request.getParameter("locRegion");
        String locCountry = request.getParameter("locCountry");
        BigDecimal locLat = new BigDecimal(request.getParameter("locLat"));
        BigDecimal locLong = new BigDecimal(request.getParameter("locLong"));

        Location loc = new Location();
        loc.setName(locName);
        loc.setDescription(locDesc);
        loc.setAddress(locAddr);
        loc.setCity(locCity);
        loc.setRegion(locRegion);
        loc.setCountry(locCountry);
        loc.setLatitude(locLat);
        loc.setLongitude(locLong);

        service.addLocation(loc);

        List<Location> locs = service.getAllLocations();
        model.addAttribute("locs", locs);
        return "locations";
    }

    @RequestMapping(value = "/editLocationForm", method = RequestMethod.GET)
    public String displayEditLocForm(HttpServletRequest request, Model model) {
        try {
            int locId = Integer.parseInt(request.getParameter("locId"));
            Location loc = service.getLocById(locId);

            model.addAttribute("loc", loc);
        } catch (NumberFormatException ex) {
            System.out.println("Not a valid number.");
        }

        return "editLocation";
    }

    @RequestMapping(value = "/editLocation", method = RequestMethod.POST)
    public String editLocation(HttpServletRequest request, Model model) {
        try {
            int locId = Integer.parseInt(request.getParameter("locId"));
            String locName = request.getParameter("locName");
            String locDesc = request.getParameter("locDesc");
            String locAddr = request.getParameter("locAddress");
            String locCity = request.getParameter("locCity");
            String locRegion = request.getParameter("locRegion");
            String locCountry = request.getParameter("locCountry");
            BigDecimal locLat = new BigDecimal(request.getParameter("locLat"));
            BigDecimal locLong = new BigDecimal(request.getParameter("locLong"));

            Location loc = new Location();
            loc.setId(locId);
            loc.setName(locName);
            loc.setDescription(locDesc);
            loc.setAddress(locAddr);
            loc.setCity(locCity);
            loc.setRegion(locRegion);
            loc.setCountry(locCountry);
            loc.setLatitude(locLat);
            loc.setLongitude(locLong);

            service.editLocation(loc);
        } catch (NumberFormatException ex) {
            System.out.println("Not a valid number.");
        }

        List<Location> locs = service.getAllLocations();
        model.addAttribute("locs", locs);
        return "locations";
    }

    @RequestMapping(value = "/deleteLocation", method = RequestMethod.GET)
    public String deleteLocation(HttpServletRequest request, Model model) {
        try {
            int locId = Integer.parseInt(request.getParameter("locId"));
            Location loc = service.getLocById(locId);

            service.deleteLocation(loc);
        } catch (NumberFormatException ex) {
            System.out.println("Not a valid number.");
        }

        List<Location> locs = service.getAllLocations();
        model.addAttribute("locs", locs);
        return "locations";
    }

    @RequestMapping(value = "/createSighting", method = RequestMethod.POST)
    public String createSighting(HttpServletRequest request, Model model) {
        try {
            LocalDate sightDate = LocalDate.parse(request.getParameter("sightDate"));
            int sightLocId = Integer.parseInt(request.getParameter("sightLocation"));
            Location sightLoc = service.getLocById(sightLocId);
            String[] heroIdStrings = request.getParameterValues("sightHeroes");
            List<Hero> sightHeroes = new ArrayList<>();

            for (String idString : heroIdStrings) {
                int heroId = Integer.parseInt(idString);
                sightHeroes.add(service.getHeroById(heroId));
            }

            Sighting sight = new Sighting();
            sight.setDate(sightDate);
            sight.setLocation(sightLoc);
            sight.setHeroes(sightHeroes);

            service.addSighting(sight);
        } catch (NumberFormatException ex) {
            System.out.println("Not a valid number.");
        }

        List<Sighting> sights = service.getAllSightings();
        List<Location> locs = service.getAllLocations();
        List<Hero> heroes = service.getAllHeroes();
        model.addAttribute("sights", sights);
        model.addAttribute("locs", locs);
        model.addAttribute("heroes", heroes);

        return "sightings";
    }

    @RequestMapping(value = "/editSightForm", method = RequestMethod.GET)
    public String displayEditSightForm(HttpServletRequest request, Model model) {
        try {
            int sightId = Integer.parseInt(request.getParameter("sightId"));

            Sighting sight = service.getSightingById(sightId);
            model.addAttribute("sight", sight);
        } catch (NumberFormatException ex) {
            System.out.println("Not a valid number.");
        }

        List<Location> locs = service.getAllLocations();
        List<Hero> heroes = service.getAllHeroes();

        model.addAttribute("locs", locs);
        model.addAttribute("heroes", heroes);
        return "editSighting";
    }

    @RequestMapping(value = "/editSighting", method = RequestMethod.POST)
    public String editSighting(HttpServletRequest request, Model model) {
        try {
            int sightId = Integer.parseInt(request.getParameter("sightId"));
            LocalDate sightDate = LocalDate.parse(request.getParameter("sightDate"));
            int sightLocId = Integer.parseInt(request.getParameter("sightLocation"));
            Location sightLoc = service.getLocById(sightLocId);
            String[] heroIdStrings = request.getParameterValues("sightHeroes");
            List<Hero> sightHeroes = new ArrayList<>();

            for (String idString : heroIdStrings) {
                int heroId = Integer.parseInt(idString);
                sightHeroes.add(service.getHeroById(heroId));
            }

            Sighting sight = new Sighting();
            sight.setId(sightId);
            sight.setDate(sightDate);
            sight.setLocation(sightLoc);
            sight.setHeroes(sightHeroes);

            service.editSighting(sight);
        } catch (NumberFormatException ex) {
            System.out.println("Not a valid number.");
        }

        List<Sighting> sights = service.getAllSightings();
        List<Location> locs = service.getAllLocations();
        List<Hero> heroes = service.getAllHeroes();
        model.addAttribute("sights", sights);
        model.addAttribute("locs", locs);
        model.addAttribute("heroes", heroes);

        return "sightings";
    }

    @RequestMapping(value = "/deleteSighting", method = RequestMethod.GET)
    public String deleteSighting(HttpServletRequest request, Model model) {
        try {
            int sightId = Integer.parseInt(request.getParameter("sightId"));
            Sighting sight = service.getSightingById(sightId);
            service.deleteSighting(sight);
        } catch (NumberFormatException ex) {
            System.out.println("Not a valid number.");
        }

        List<Sighting> sights = service.getAllSightings();
        List<Location> locs = service.getAllLocations();
        List<Hero> heroes = service.getAllHeroes();
        model.addAttribute("sights", sights);
        model.addAttribute("locs", locs);
        model.addAttribute("heroes", heroes);

        return "sightings";
    }

    @RequestMapping(value = "/createPower", method = RequestMethod.POST)
    public String createPower(HttpServletRequest request, Model model) {
        String powerName = request.getParameter("powerName");
        String powerDesc = request.getParameter("powerDesc");

        Power power = new Power();
        power.setName(powerName);
        power.setDescription(powerDesc);

        service.addPower(power);

        List<Power> powers = service.getAllPowers();
        model.addAttribute("powers", powers);
        return "powers";
    }

    @RequestMapping(value = "/editPower", method = RequestMethod.POST)
    public String editPower(HttpServletRequest request, Model model) {
        try {
            String powerName = request.getParameter("powerName");
            String powerDesc = request.getParameter("powerDesc");
            int powerId = Integer.parseInt(request.getParameter("powerId"));

            Power power = new Power();
            power.setId(powerId);
            power.setName(powerName);
            power.setDescription(powerDesc);

            service.editPower(power);
        } catch (NumberFormatException ex) {
            System.out.println("Not a valid number.");
        }

        List<Power> powers = service.getAllPowers();
        model.addAttribute("powers", powers);
        return "powers";
    }

    @RequestMapping(value = "/deletePower", method = RequestMethod.GET)
    public String deletePower(HttpServletRequest request, Model model) {
        try {
            int powerId = Integer.parseInt(request.getParameter("powerId"));
            Power toDelete = service.getPowerById(powerId);
            service.deletePower(toDelete);
        } catch (NumberFormatException ex) {
            System.out.println("Not a valid number.");
        }

        List<Power> powers = service.getAllPowers();
        model.addAttribute("powers", powers);
        return "powers";
    }

}
