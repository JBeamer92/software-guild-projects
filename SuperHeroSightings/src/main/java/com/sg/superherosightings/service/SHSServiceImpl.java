/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.dao.SHSDao;
import com.sg.superherosightings.model.Hero;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.Power;
import com.sg.superherosightings.model.Sighting;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author jared
 */
public class SHSServiceImpl implements SHSService {
    
    private SHSDao dao;
    
    @Inject
    public SHSServiceImpl(SHSDao dao) {
        this.dao = dao;
    }
    
    @Override
    public List<Sighting> getMostRecentSightings() {
        return dao.getMostRecentSightings();
    }

    @Override
    public List<Hero> getAllHeroes() {
        return dao.getAllHeroes();
    }

    @Override
    public Hero getHeroById(int heroId) {
        return dao.getHeroById(heroId);
    }

    @Override
    public void addHero(Hero hero) {
        dao.addHero(hero);
    }

    @Override
    public void deleteHero(Hero hero) {
        dao.removeHero(hero);
    }

    @Override
    public void editHero(Hero hero) {
        dao.updateHero(hero);
    }

    @Override
    public List<Power> getAllPowers() {
        return dao.getAllPowers();
    }

    @Override
    public Power getPowerById(int powerId) {
        return dao.getPowerById(powerId);
    }

    @Override
    public void addPower(Power power) {
        dao.addPower(power);
    }

    @Override
    public void deletePower(Power power) {
        dao.removePower(power);
    }

    @Override
    public void editPower(Power power) {
        dao.updatePower(power);
    }

    @Override
    public List<Organization> getAllOrgs() {
        return dao.getAllOrgs();
    }

    @Override
    public Organization getOrgById(int orgId) {
        return dao.getOrgById(orgId);
    }

    @Override
    public void addOrg(Organization org) {
        dao.addOrg(org);
    }

    @Override
    public void deleteOrg(Organization org) {
        dao.removeOrg(org);
    }

    @Override
    public void editOrg(Organization org) {
        dao.updateOrg(org);
    }

    @Override
    public List<Location> getAllLocations() {
        return dao.getAllLocations();
    }

    @Override
    public Location getLocById(int locId) {
        return dao.getLocationById(locId);
    }

    @Override
    public void addLocation(Location loc) {
        dao.addLocation(loc);
    }

    @Override
    public void deleteLocation(Location loc) {
        dao.removeLocation(loc);
    }

    @Override
    public void editLocation(Location loc) {
        dao.updateLocation(loc);
    }

    @Override
    public List<Sighting> getAllSightings() {
        return dao.getAllSightings();
    }

    @Override
    public Sighting getSightingById(int sightId) {
        return dao.getSightingById(sightId);
    }

    @Override
    public void addSighting(Sighting sight) {
        dao.addSighting(sight);
    }

    @Override
    public void deleteSighting(Sighting sight) {
        dao.removeSighting(sight);
    }

    @Override
    public void editSighting(Sighting sight) {
        dao.updateSighting(sight);
    }
    
    
    
}
