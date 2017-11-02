/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.model.Hero;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.Power;
import com.sg.superherosightings.model.Sighting;
import java.util.List;

/**
 *
 * @author jared
 */
public interface SHSService {
    
    // QUERIES
    List<Sighting> getMostRecentSightings();
    
    // HEROES
    List<Hero> getAllHeroes();
    Hero getHeroById(int heroId);
    void addHero(Hero hero);
    void deleteHero(Hero hero);
    void editHero(Hero hero);
    
    // POWERS
    List<Power> getAllPowers();
    Power getPowerById(int powerId);
    void addPower(Power power);
    void deletePower(Power power);
    void editPower(Power power);
    
    // ORGS
    List<Organization> getAllOrgs();
    Organization getOrgById(int orgId);
    void addOrg(Organization org);
    void deleteOrg(Organization org);
    void editOrg(Organization org);
    
    // LOCATIONS
    List<Location> getAllLocations();
    Location getLocById(int locId);
    void addLocation(Location loc);
    void deleteLocation(Location loc);
    void editLocation(Location loc);
    
    // SIGHTINGS
    List<Sighting> getAllSightings();
    Sighting getSightingById(int sightId);
    void addSighting(Sighting sight);
    void deleteSighting(Sighting sight);
    void editSighting(Sighting sight);
    
}
