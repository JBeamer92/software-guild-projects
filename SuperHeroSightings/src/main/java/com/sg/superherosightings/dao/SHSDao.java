/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Hero;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.Power;
import com.sg.superherosightings.model.Sighting;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author jared
 */
public interface SHSDao {
    
    //QUERIES
    List<Sighting> getMostRecentSightings();
    
    // HEROES
    void addHero(Hero hero);
    Hero getHeroById(int id);
    List<Hero> getAllHeroes();
    void updateHero(Hero hero);
    void removeHero(Hero hero);
    
    // POWERS
    void addPower(Power power);
    Power getPowerById(int id);
    List<Power> getAllPowers();
    void updatePower(Power power);
    void removePower(Power power);
    
    // ORGANIZATIONS
    void addOrg(Organization org);
    Organization getOrgById(int id);
    List<Organization> getAllOrgs();
    void updateOrg(Organization org);
    void removeOrg(Organization org);
    
    // LOCATIONS
    void addLocation(Location loc);
    Location getLocationById(int id);
    List<Location> getAllLocations();
    void updateLocation(Location loc);
    void removeLocation(Location loc);
    
    // SIGHTINGS
    void addSighting(Sighting sight);
    Sighting getSightingById(int id);
    List<Sighting> getAllSightings();
    void updateSighting(Sighting sight);
    void removeSighting(Sighting sight);
}
