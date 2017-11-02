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
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jared
 */
public class SHSDaoJdbcImpl implements SHSDao {

    private JdbcTemplate jdbc;
    // ALL CAPS
    private final Location UNKNOWN_LOCATION = new Location(0, "Unknown.", 
            "Unknown.", "Unknown.", "Unknown.", "Unknown.", "Unknown.", 
            new BigDecimal(00.0000), new BigDecimal(000.0000));
    private final String SQL_LAST_ID = "SELECT LAST_INSERT_ID()";

    public SHSDaoJdbcImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }
    /*
         _______  __   __  _______  ______    ___   _______  _______ 
        |       ||  | |  ||       ||    _ |  |   | |       ||       |
        |   _   ||  | |  ||    ___||   | ||  |   | |    ___||  _____|
        |  | |  ||  |_|  ||   |___ |   |_||_ |   | |   |___ | |_____ 
        |  |_|  ||       ||    ___||    __  ||   | |    ___||_____  |
        |      | |       ||   |___ |   |  | ||   | |   |___  _____| |
        |____||_||_______||_______||___|  |_||___| |_______||_______|
    */
    
    private static final String SQL_SELECT_RECENT_SIGHT_IDS
            ="SELECT * FROM Sightings "
            + "ORDER BY SightingDate DESC LIMIT 10";
    
    
    public List<Sighting> getMostRecentSightings() {
        List<Sighting> sights = jdbc.query(SQL_SELECT_RECENT_SIGHT_IDS, new SightingMapper());
        for (Sighting sight : sights) {
            sight.setHeroes(getHeroesBySighting(sight));
            sight.setLocation(getLocOfSighting(sight));
        }
        return sights;
    }

    /*
         __   __  _______  ___      _______  _______  ______    _______ 
        |  | |  ||       ||   |    |       ||       ||    _ |  |       |
        |  |_|  ||    ___||   |    |    _  ||    ___||   | ||  |  _____|
        |       ||   |___ |   |    |   |_| ||   |___ |   |_||_ | |_____ 
        |       ||    ___||   |___ |    ___||    ___||    __  ||_____  |
        |   _   ||   |___ |       ||   |    |   |___ |   |  | | _____| |
        |__| |__||_______||_______||___|    |_______||___|  |_||_______|
    */

    private static final String SELECT_POWERS_BY_HERO
            = "SELECT p.powerID, p.PowerName, p.Description "
            + "FROM HeroPowers hp "
            + "JOIN Powers p ON hp.PowerID = p.PowerID "
            + "JOIN Heroes h ON hp.HeroID = h.HeroID WHERE h.HeroID = ?";

    private List<Power> getPowersByHero(Hero hero) {
        return jdbc.query(SELECT_POWERS_BY_HERO,
                new PowerMapper(),
                hero.getId());
    }

    private static final String SELECT_HERO_IDS_BY_ORG
            = "SELECT HeroID FROM HeroesInOrganizations WHERE OrgID = ?";

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    private List<Hero> getHeroesByOrg(Organization org) {
        List<Integer> heroIds = jdbc.queryForList(SELECT_HERO_IDS_BY_ORG,
                Integer.class,
                org.getId());

        List<Hero> heroes = new ArrayList<>();

        for (Integer id : heroIds) {
            Hero hero = getHeroById(id);
            heroes.add(hero);
        }
        return heroes;
    }

    private static final String SELECT_HERO_IDS_BY_SIGHTING
            = "SELECT HeroID FROM HeroSightings WHERE SightingID = ?";

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    private List<Hero> getHeroesBySighting(Sighting sight) {
        List<Integer> heroIds = jdbc.queryForList(SELECT_HERO_IDS_BY_SIGHTING,
                Integer.class,
                sight.getId());

        List<Hero> heroes = new ArrayList<>();

        for (Integer id : heroIds) {
            Hero hero = getHeroById(id);
            heroes.add(hero);
        }
        return heroes;
    }

    private static final String SELECT_LOC_ID_OF_ORG
            = "SELECT LocationID FROM Organizations WHERE OrgID = ?";

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    private Location getLocOfOrg(Organization org) {
        try {
            Integer locId = jdbc.queryForObject(SELECT_LOC_ID_OF_ORG,
                    Integer.class,
                    org.getId());
            return this.getLocationById(locId);
        } catch (NullPointerException ex) {
            return UNKNOWN_LOCATION;
        }
    }

    private static final String SELECT_LOC_ID_OF_SIGHTING
            = "SELECT LocationID FROM Sightings WHERE SightingID = ?";

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    private Location getLocOfSighting(Sighting sight) {
        Integer locId = jdbc.queryForObject(SELECT_LOC_ID_OF_SIGHTING,
                Integer.class,
                sight.getId());
        return this.getLocationById(locId);
    }
    
    private static final String SQL_INSERT_HERO_POWERS_BY_HERO
            = "INSERT INTO HeroPowers (HeroID, PowerID) "
            + "VALUES (?,?)";
    private void insertHeroPowers(Hero hero) {
        final int heroId = hero.getId();
        final List<Power> powers = hero.getPowers();
        
        for (Power power : powers) {
            jdbc.update(SQL_INSERT_HERO_POWERS_BY_HERO,
                        heroId,
                        power.getId());
        }
    }
    
    private static final String SQL_INSERT_ORG_HEROES_BY_ORG
            = "INSERT INTO HeroesInOrganizations (HeroID, OrgID) "
            + "VALUES (?,?)";
    private void insertHeroesInOrg(Organization org) {
        final int orgId = org.getId();
        final List<Hero> heroes = org.getHeroes();
        
        for (Hero hero : heroes) {
            jdbc.update(SQL_INSERT_ORG_HEROES_BY_ORG,
                        hero.getId(),
                        orgId);
        }
    }
    
    private static final String SQL_INSERT_HERO_SIGHTS_BY_SIGHT
            = "INSERT INTO HeroSightings (HeroID, SightingID) "
            + "VALUES (?,?)";
    private void insertHeroSightings(Sighting sight) {
        final int sightId = sight.getId();
        final List<Hero> heroes = sight.getHeroes();
        
        for (Hero hero : heroes) {
            jdbc.update(SQL_INSERT_HERO_SIGHTS_BY_SIGHT,
                        hero.getId(),
                        sightId);
        }
    }
    
    /*
         __   __  _______  ______    _______  _______  _______ 
        |  | |  ||       ||    _ |  |       ||       ||       |
        |  |_|  ||    ___||   | ||  |   _   ||    ___||  _____|
        |       ||   |___ |   |_||_ |  | |  ||   |___ | |_____ 
        |       ||    ___||    __  ||  |_|  ||    ___||_____  |
        |   _   ||   |___ |   |  | ||       ||   |___  _____| |
        |__| |__||_______||___|  |_||_______||_______||_______|
     */

    private static final String SQL_INSERT_HERO
            = "INSERT INTO Heroes(HeroName, Description) "
            + "VALUES (?,?)";
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addHero(Hero hero) {
        jdbc.update(SQL_INSERT_HERO,
                    hero.getName(),
                    hero.getDescription());
        hero.setId(jdbc.queryForObject(SQL_LAST_ID, Integer.class));
        insertHeroPowers(hero);
    }

    private static final String SQL_SELECT_HERO_BY_ID
            = "SELECT * FROM Heroes WHERE HeroID = ?";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Hero getHeroById(int id) {
        try {
            Hero hero = jdbc.queryForObject(SQL_SELECT_HERO_BY_ID,
                    new HeroMapper(),
                    id);
            hero.setPowers(getPowersByHero(hero));
            return hero;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    private static final String SQL_SELECT_ALL_HEROES
            = "SELECT * FROM Heroes";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<Hero> getAllHeroes() {
        List<Hero> heroes = jdbc.query(SQL_SELECT_ALL_HEROES,
                new HeroMapper());
        for (Hero hero : heroes) {
            hero.setPowers(getPowersByHero(hero));
        }
        return heroes;
    }
    
    private static final String SQL_UPDATE_HERO
            = "UPDATE Heroes SET HeroName = ?, Description = ? "
            + "WHERE HeroID = ?";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateHero(Hero hero) {
        jdbc.update(SQL_UPDATE_HERO,
                    hero.getName(),
                    hero.getDescription(),
                    hero.getId());
        jdbc.update(SQL_DELETE_HERO_POWERS_BY_HERO, hero.getId());
        insertHeroPowers(hero);
    }

    private static final String SQL_DELETE_HERO_SIGHTS_BY_HERO
            = "DELETE FROM HeroSightings WHERE HeroID = ?";
    private static final String SQL_DELETE_HERO_POWERS_BY_HERO
            = "DELETE FROM HeroPowers WHERE HeroID = ?";
    private static final String SQL_DELETE_HEROES_IN_ORG_BY_HERO
            = "DELETE FROM HeroesInOrganizations WHERE HeroID = ?";
    private static final String SQL_DELETE_HERO
            = "DELETE FROM Heroes WHERE HeroID = ?";
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void removeHero(Hero hero) {
        jdbc.update(SQL_DELETE_HERO_SIGHTS_BY_HERO, 
                    hero.getId());
        jdbc.update(SQL_DELETE_HERO_POWERS_BY_HERO, 
                    hero.getId());
        jdbc.update(SQL_DELETE_HEROES_IN_ORG_BY_HERO, 
                    hero.getId());
        jdbc.update(SQL_DELETE_HERO, 
                    hero.getId());
    }

    /*
         _______  _______  _     _  _______  ______    _______ 
        |       ||       || | _ | ||       ||    _ |  |       |
        |    _  ||   _   || || || ||    ___||   | ||  |  _____|
        |   |_| ||  | |  ||       ||   |___ |   |_||_ | |_____ 
        |    ___||  |_|  ||       ||    ___||    __  ||_____  |
        |   |    |       ||   _   ||   |___ |   |  | | _____| |
        |___|    |_______||__| |__||_______||___|  |_||_______|
     */
    private static final String SQL_INSERT_POWER
            = "INSERT INTO Powers (PowerName, Description) "
            + "VALUES (?,?)";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addPower(Power power) {
        jdbc.update(SQL_INSERT_POWER,
                power.getName(),
                power.getDescription());
        power.setId(jdbc.queryForObject(SQL_LAST_ID, Integer.class));
    }

    private static final String SQL_SELECT_POWER_BY_ID
            = "SELECT * FROM Powers WHERE PowerID = ?";

    @Override
    public Power getPowerById(int id) {
        try {
            return jdbc.queryForObject(SQL_SELECT_POWER_BY_ID,
                    new PowerMapper(),
                    id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    private static final String SQL_SELECT_ALL_POWERS
            = "SELECT * FROM Powers";

    @Override
    public List<Power> getAllPowers() {
        return jdbc.query(SQL_SELECT_ALL_POWERS,
                new PowerMapper());
    }
    
    private static final String SQL_UPDATE_POWER
            = "UPDATE Powers SET PowerName = ?, Description = ? "
            + "WHERE PowerID = ?";

    @Override
    public void updatePower(Power power) {
        jdbc.update(SQL_UPDATE_POWER,
                    power.getName(),
                    power.getDescription(),
                    power.getId());
    }

    private static final String SQL_DELETE_POWER
            = "DELETE FROM Powers WHERE PowerID = ?";
    private static final String SQL_DELETE_HERO_POWERS_BY_POWER
            = "DELETE FROM HeroPowers WHERE PowerID = ?";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void removePower(Power power) {
        jdbc.update(SQL_DELETE_HERO_POWERS_BY_POWER, power.getId());
        jdbc.update(SQL_DELETE_POWER, power.getId());
    }

    /*
         _______  ______    _______  _______ 
        |       ||    _ |  |       ||       |
        |   _   ||   | ||  |    ___||  _____|
        |  | |  ||   |_||_ |   | __ | |_____ 
        |  |_|  ||    __  ||   ||  ||_____  |
        |       ||   |  | ||   |_| | _____| |
        |_______||___|  |_||_______||_______|
     */
    
    private static final String SQL_INSERT_ORGANIZATION
            = "INSERT INTO Organizations (OrgName, Description, LocationID, "
            + "PhoneType, PhoneNumber) "
            + "VALUES (?,?,?,?,?)";
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addOrg(Organization org) {
        // if unknown location, set to null for DB
        // FIX THIS ISH
        if (org.getLocation().getId() == 0) {
            org.setLocation(null);
        }
        jdbc.update(SQL_INSERT_ORGANIZATION,
                    org.getName(),
                    org.getDescription(),
                    org.getLocation().getId(),
                    org.getPhoneType(),
                    org.getPhoneNumber());
        org.setId(jdbc.queryForObject(SQL_LAST_ID, Integer.class));
        insertHeroesInOrg(org);
    }

    private static final String SQL_SELECT_ORG_BY_ID
            = "SELECT * FROM Organizations WHERE OrgID = ?";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Organization getOrgById(int id) {
        try {
            Organization org = jdbc.queryForObject(SQL_SELECT_ORG_BY_ID,
                    new OrgMapper(),
                    id);
            org.setHeroes(getHeroesByOrg(org));
            org.setLocation(getLocOfOrg(org));
            return org;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    private static final String SQL_SELECT_ALL_ORGS
            = "SELECT * FROM Organizations";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<Organization> getAllOrgs() {
        List<Organization> orgs = jdbc.query(SQL_SELECT_ALL_ORGS,
                new OrgMapper());
        for (Organization org : orgs) {
            org.setHeroes(getHeroesByOrg(org));
            org.setLocation(getLocOfOrg(org));
        }
        return orgs;
    }
    
    private static final String SQL_UPDATE_ORG
            = "UPDATE Organizations SET OrgName = ?, Description = ?, "
            + "LocationID = ?, PhoneType = ?, PhoneNumber = ? WHERE OrgID = ?";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateOrg(Organization org) {
        // if unknown location, set to null for DB
        if (org.getLocation().getId() == 0) {
            org.setLocation(null);
        }
        jdbc.update(SQL_UPDATE_ORG,
                    org.getName(),
                    org.getDescription(),
                    org.getLocation().getId(),
                    org.getPhoneType(),
                    org.getPhoneNumber(),
                    org.getId());
        jdbc.update(SQL_DELETE_HEROES_IN_ORG_BY_ORG, org.getId());
        insertHeroesInOrg(org);
    }

    private static final String SQL_DELETE_HEROES_IN_ORG_BY_ORG
            = "DELETE FROM HeroesInOrganizations WHERE OrgID = ?";
    private static final String SQL_DELETE_ORG
            = "DELETE FROM Organizations WHERE OrgID = ?";
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void removeOrg(Organization org) {
        jdbc.update(SQL_DELETE_HEROES_IN_ORG_BY_ORG,
                    org.getId());
        jdbc.update(SQL_DELETE_ORG,
                    org.getId());
    }

    /*
         ___      _______  _______  _______ 
        |   |    |       ||       ||       |
        |   |    |   _   ||       ||  _____|
        |   |    |  | |  ||       || |_____ 
        |   |___ |  |_|  ||      _||_____  |
        |       ||       ||     |_  _____| |
        |_______||_______||_______||_______|
     */
    private static final String SQL_INSERT_LOC
            = "INSERT INTO Locations(LocationName, Description, Address, "
            + "City, Region, Country, Latitude, Longitude) "
            + "VALUES (?,?,?,?,?,?,?,?)";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addLocation(Location loc) {
        jdbc.update(SQL_INSERT_LOC,
                loc.getName(),
                loc.getDescription(),
                loc.getAddress(),
                loc.getCity(),
                loc.getRegion(),
                loc.getCountry(),
                loc.getLatitude().setScale(4, RoundingMode.DOWN),
                loc.getLongitude().setScale(4, RoundingMode.DOWN));
        loc.setId(jdbc.queryForObject(SQL_LAST_ID, Integer.class));
    }

    private static final String SQL_SELECT_LOCATION_BY_ID
            = "SELECT * FROM Locations WHERE LocationID = ?";

    @Override
    public Location getLocationById(int id) {
        try {
            return jdbc.queryForObject(SQL_SELECT_LOCATION_BY_ID,
                    new LocationMapper(),
                    id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    private static final String SQL_SELECT_ALL_LOCATIONS
            = "SELECT * FROM Locations";

    @Override
    public List<Location> getAllLocations() {
        return jdbc.query(SQL_SELECT_ALL_LOCATIONS,
                new LocationMapper());
    }
    
    private static final String SQL_UPDATE_LOC
            = "UPDATE Locations SET LocationName = ?, Description = ?, Address = ?, "
            + "City = ?, Region = ?, Country = ?, Latitude = ?, "
            + "Longitude = ? WHERE LocationID = ?";

    @Override
    public void updateLocation(Location loc) {
        jdbc.update(SQL_UPDATE_LOC,
                    loc.getName(),
                    loc.getDescription(),
                    loc.getAddress(),
                    loc.getCity(),
                    loc.getRegion(),
                    loc.getCountry(),
                    loc.getLatitude().setScale(4),
                    loc.getLongitude().setScale(4),
                    loc.getId());
    }

    private static final String SQL_UPDATE_ORG_LOC_TO_NULL_BY_LOC
            = "UPDATE Organizations SET LocationID = null WHERE LocationID = ?";
    private static final String SQL_SELECT_SIGHT_IDS_TO_DELETE_BY_LOC
            = "SELECT SightingID FROM Sightings WHERE LocationID = ?";
    private static final String SQL_DELETE_HERO_SIGHTS_BY_SIGHT_ID
            = "DELETE FROM HeroSightings WHERE SightingID = ?";
    private static final String SQL_DELETE_SIGHTINGS_BY_LOC
            = "DELETE FROM Sightings WHERE LocationID = ?";
    private static final String SQL_DELETE_LOC
            = "DELETE FROM Locations where LocationID = ?";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void removeLocation(Location loc) {
        jdbc.update(SQL_UPDATE_ORG_LOC_TO_NULL_BY_LOC, loc.getId());
        
        List<Integer> sightIds = jdbc.queryForList(SQL_SELECT_SIGHT_IDS_TO_DELETE_BY_LOC,
                Integer.class,
                loc.getId());
        
        for (Integer id : sightIds) {
            jdbc.update(SQL_DELETE_HERO_SIGHTS_BY_SIGHT_ID,
                    id);
        }
        
        jdbc.update(SQL_DELETE_SIGHTINGS_BY_LOC, loc.getId());
        jdbc.update(SQL_DELETE_LOC, loc.getId());
    }

    /*
         _______  ___   _______  __   __  _______  _______ 
        |       ||   | |       ||  | |  ||       ||       |
        |  _____||   | |    ___||  |_|  ||_     _||  _____|
        | |_____ |   | |   | __ |       |  |   |  | |_____ 
        |_____  ||   | |   ||  ||       |  |   |  |_____  |
         _____| ||   | |   |_| ||   _   |  |   |   _____| |
        |_______||___| |_______||__| |__|  |___|  |_______|
     */
    private static final String SQL_INSERT_SIGHT
            = "INSERT INTO Sightings (LocationID, SightingDate) "
            + "VALUES (?,?)";
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addSighting(Sighting sight) {
        jdbc.update(SQL_INSERT_SIGHT,
                    sight.getLocation().getId(),
                    Date.valueOf(sight.getDate()));
        sight.setId(jdbc.queryForObject(SQL_LAST_ID, Integer.class));
        insertHeroSightings(sight);
    }

    private static final String SQL_GET_SIGHTING_BY_ID
            = "SELECT * FROM Sightings WHERE SightingID = ?";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Sighting getSightingById(int id) {
        try {
            Sighting sight = jdbc.queryForObject(SQL_GET_SIGHTING_BY_ID,
                    new SightingMapper(),
                    id);
            sight.setHeroes(getHeroesBySighting(sight));
            sight.setLocation(getLocOfSighting(sight));
            return sight;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    private static final String SQL_GET_ALL_SIGHTINGS
            = "SELECT * FROM Sightings ORDER BY SightingDate DESC";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<Sighting> getAllSightings() {
        List<Sighting> sights = jdbc.query(SQL_GET_ALL_SIGHTINGS,
                new SightingMapper());

        for (Sighting sight : sights) {
            sight.setHeroes(getHeroesBySighting(sight));
            sight.setLocation(getLocOfSighting(sight));
        }
        return sights;
    }
    
    private static final String SQL_UPDATE_SIGHT
            = "UPDATE Sightings SET LocationID = ?, SightingDate = ? "
            + "WHERE SightingID = ?";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateSighting(Sighting sight) {
        jdbc.update(SQL_UPDATE_SIGHT,
                    sight.getLocation().getId(),
                    Date.valueOf(sight.getDate()),
                    sight.getId());
        jdbc.update(SQL_DELETE_HERO_SIGHTS_BY_SIGHT,
                    sight.getId());
        insertHeroSightings(sight);
    }
    
    private static final String SQL_DELETE_HERO_SIGHTS_BY_SIGHT
            = "DELETE FROM HeroSightings WHERE SightingID = ?";
    private static final String SQL_DELETE_SIGHT
            = "DELETE FROM Sightings WHERE SightingID = ?";
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void removeSighting(Sighting sight) {
        jdbc.update(SQL_DELETE_HERO_SIGHTS_BY_SIGHT,
                    sight.getId());
        jdbc.update(SQL_DELETE_SIGHT,
                    sight.getId());
    }

    /*
         __   __  _______  _______  _______  _______  ______    _______ 
        |  |_|  ||   _   ||       ||       ||       ||    _ |  |       |
        |       ||  |_|  ||    _  ||    _  ||    ___||   | ||  |  _____|
        |       ||       ||   |_| ||   |_| ||   |___ |   |_||_ | |_____ 
        |       ||       ||    ___||    ___||    ___||    __  ||_____  |
        | ||_|| ||   _   ||   |    |   |    |   |___ |   |  | | _____| |
        |_|   |_||__| |__||___|    |___|    |_______||___|  |_||_______|
    */
    private static class HeroMapper implements RowMapper<Hero> {

        @Override
        public Hero mapRow(ResultSet rs, int i) throws SQLException {
            Hero hero = new Hero();
            hero.setId(rs.getInt("HeroID"));
            hero.setName(rs.getString("HeroName"));
            hero.setDescription(rs.getString("Description"));
            return hero;
        }
    }

    private static class PowerMapper implements RowMapper<Power> {

        @Override
        public Power mapRow(ResultSet rs, int i) throws SQLException {
            Power power = new Power();
            power.setId(rs.getInt("PowerID"));
            power.setName(rs.getString("PowerName"));
            power.setDescription(rs.getString("Description"));
            return power;
        }
    }

    private static class OrgMapper implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int i) throws SQLException {
            Organization org = new Organization();
            org.setId(rs.getInt("OrgID"));
            org.setName(rs.getString("OrgName"));
            org.setDescription(rs.getString("Description"));
            org.setPhoneType(rs.getString("PhoneType"));
            org.setPhoneNumber(rs.getString("PhoneNumber"));
            return org;
        }
    }

    private static class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int i) throws SQLException {
            Location loc = new Location();
            loc.setId(rs.getInt("LocationID"));
            loc.setName(rs.getString("LocationName"));
            loc.setDescription(rs.getString("Description"));
            loc.setAddress(rs.getString("Address"));
            loc.setCity(rs.getString("City"));
            loc.setRegion(rs.getString("Region"));
            loc.setCountry(rs.getString("Country"));
            loc.setLatitude(rs.getBigDecimal("Latitude").setScale(4));
            loc.setLongitude(rs.getBigDecimal("Longitude").setScale(4));
            return loc;
        }
    }

    private static class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int i) throws SQLException {
            Sighting sight = new Sighting();
            sight.setId(rs.getInt("SightingID"));
            sight.setDate((rs.getDate("SightingDate").toLocalDate()));
            return sight;
        }
    }

}
