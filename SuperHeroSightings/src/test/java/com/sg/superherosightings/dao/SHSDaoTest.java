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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.Assert;

/**
 *
 * @author jared
 */
public class SHSDaoTest {

    private SHSDao dao;
    
    private Power power1;
    private Power power2;
    private Power power3;
    private Power testPower;
    private Hero hero1;
    private Hero hero2;
    private Hero testHero;
    private Organization org1;
    private Organization testOrg;
    private Location loc1;
    private Location loc2;
    private Location testLoc;
    private Sighting sight1;
    private Sighting sight2;
    private Sighting testSight;

    public SHSDaoTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                "test-applicationContext.xml");
        dao = ctx.getBean("testDao", SHSDao.class);
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        
        List<Power> powersDb = dao.getAllPowers();
        List<Hero> heroesDb = dao.getAllHeroes();
        List<Organization> orgsDb = dao.getAllOrgs();
        List<Location> locsDb = dao.getAllLocations();
        List<Sighting> sightsDb = dao.getAllSightings();
        
        for (Power p : powersDb) {
            dao.removePower(p);
        }
        for (Hero h : heroesDb) {
            dao.removeHero(h);
        }
        for (Organization o : orgsDb) {
            dao.removeOrg(o);
        }
        for (Location l : locsDb) {
            dao.removeLocation(l);
        }
        for (Sighting s : sightsDb) {
            dao.removeSighting(s);
        }
        
        power1 = new Power(1,"Burrow","Not exactly helpful to any bystanders.");
        power2 = new Power(2,"Lazer Beam Vision","Don't look now! No seriously, please don't.");
        power3 = new Power(3,"Killer Quads","Really great when using public restrooms.");
        
        List<Power> hero1pwrs = new ArrayList<>();
        hero1pwrs.add(power1);
        hero1 = new Hero(1,"Worm Man","Half worm, half man.",hero1pwrs);
        List<Power> hero2pwrs = new ArrayList<>();
        hero2pwrs.add(power3);
        hero2 = new Hero(2,"Leg Day Man","He puts the LEG in legendary.",hero2pwrs);
        
        loc1 = new Location(1,"ALH Lair", "Leg Day Man's mom's basement.",
                "520 Main St, Apt 5A","Atlantic City","NJ","USA", 
                new BigDecimal("55.5555"), new BigDecimal("150.5555"));
        loc2 = new Location(2,"Office Depot", "I hear they have a sale on paper.",
                "6940 Mass Ave","Indianapolis","IN","USA",
                new BigDecimal("66.6666"), new BigDecimal("160.6666"));
        
        List<Hero> org1heroes = new ArrayList<>();
        org1heroes.add(hero1);
        org1heroes.add(hero2);
        org1 = new Organization(1,"Association of Lame Heroes",
                "Not even the Island of Misfit Toys would take them.",loc1,
                "Office","1-800-555-5885",org1heroes);
        
        List<Hero> sight1heroes = new ArrayList<>();
        sight1heroes.add(hero1);
        sight1 = new Sighting(1,loc2,LocalDate.parse("2017-05-21"),sight1heroes);
        
        List<Hero> sight2heroes = new ArrayList<>();
        sight2heroes.add(hero1);
        sight2heroes.add(hero2);
        sight2 = new Sighting(2,loc1,LocalDate.parse("2016-10-25"),sight2heroes);
        
        dao.addPower(power1);
        dao.addPower(power2);
        dao.addPower(power3);
        dao.addHero(hero1);
        dao.addHero(hero2);
        dao.addLocation(loc1);
        dao.addLocation(loc2);
        dao.addOrg(org1);
        dao.addSighting(sight1);
        dao.addSighting(sight2);
        
        // TEST ITEMS!
        List<Power> hero3powers = new ArrayList<>();
        hero3powers.add(power2);
        testHero = new Hero(3,"Uncontrollable Lazer Eyes Girl",
                "Optometrists HATE her!",hero3powers);
        
        testPower = new Power(4,"Toxic Flatulence","Silent or not, it sure is deadly!");
        
        testLoc = new Location(3,"A Van Down by the River","I think I smell government cheese.",
                "Uhh... By the river?","Brooklyn","NY","USA", 
                new BigDecimal("77.7777"), new BigDecimal("170.7777"));
        
        List<Hero> testOrgHeroes = new ArrayList<>();
        testOrgHeroes.add(hero1);
        testOrg = new Organization(2,"Legion of Gloom","A depressing bunch.",
                loc2,"Office","1-800-444-4644",testOrgHeroes);
        
        List<Hero> testSightHeroes = new ArrayList<>();
        testSightHeroes.add(hero2);
        testSight = new Sighting(3,loc1,
                LocalDate.parse("2017-08-08"),testSightHeroes);
        
    }

    @After
    public void tearDown() {
        List<Power> powersDb = dao.getAllPowers();
        List<Hero> heroesDb = dao.getAllHeroes();
        List<Organization> orgsDb = dao.getAllOrgs();
        List<Location> locsDb = dao.getAllLocations();
        List<Sighting> sightsDb = dao.getAllSightings();
        
        for (Power p : powersDb) {
            dao.removePower(p);
        }
        for (Hero h : heroesDb) {
            dao.removeHero(h);
        }
        for (Organization o : orgsDb) {
            dao.removeOrg(o);
        }
        for (Location l : locsDb) {
            dao.removeLocation(l);
        }
        for (Sighting s : sightsDb) {
            dao.removeSighting(s);
        }
    }

    // HEROES
    @Test
    public void testAddGetHero() {
        dao.addHero(testHero);
        Hero fromDb = dao.getHeroById(testHero.getId());
        Assert.assertEquals(fromDb, testHero);
    }

    @Test
    public void testGetAllHeroes() {
        Assert.assertEquals(2, dao.getAllHeroes().size());
        dao.addHero(testHero);
        Assert.assertEquals(3, dao.getAllHeroes().size());
    }

    @Test
    public void testRemoveHero() {
        Assert.assertEquals(2, dao.getAllHeroes().size());
        dao.removeHero(hero1);
        Assert.assertNull(dao.getHeroById(hero1.getId()));
        Assert.assertEquals(1, dao.getAllHeroes().size());
    }
    
    @Test
    public void testUpdateHero() {
        hero1.setName("NewName");
        hero1.setDescription("NewDesc");
        List<Power> newPowers = new ArrayList<>();
        newPowers.add(power2);
        newPowers.add(power3);
        hero1.setPowers(newPowers);
        dao.updateHero(hero1);
        Hero fromDb = dao.getHeroById(hero1.getId());
        Assert.assertEquals("NewName", fromDb.getName());
        Assert.assertEquals("NewDesc", fromDb.getDescription());
        Assert.assertEquals(newPowers, fromDb.getPowers());
    }

    // POWERS
    @Test
    public void testAddGetPower() {
        dao.addPower(testPower);
        Power fromDb = dao.getPowerById(testPower.getId());
        Assert.assertEquals(fromDb, testPower);
    }

    @Test
    public void testGetAllPowers() {
        Assert.assertEquals(3, dao.getAllPowers().size());
        dao.addPower(testPower);
        Assert.assertEquals(4, dao.getAllPowers().size());
    }
    
    @Test
    public void testRemovePower() {
        Assert.assertEquals(3, dao.getAllPowers().size());
        Power toRemove = dao.getPowerById(power1.getId());
        dao.removePower(toRemove);
        Assert.assertEquals(2, dao.getAllPowers().size());
    }
    
    @Test
    public void testUpdatePower() {
        String newName = "Am I changed?";
        String newDesc = "New Description";
        power1.setName(newName);
        power1.setDescription(newDesc);
        dao.updatePower(power1);
        Power fromDb = dao.getPowerById(power1.getId());
        Assert.assertEquals(newName, fromDb.getName());
        Assert.assertEquals(newDesc, fromDb.getDescription());
    }
    
    // ORGANIZATIONS
    @Test
    public void testAddGetOrg() {
        dao.addOrg(testOrg);
        
        Organization fromDb = dao.getOrgById(testOrg.getId());
        Assert.assertEquals(fromDb, testOrg);
    }

    @Test
    public void testGetAllOrgs() {
        Assert.assertEquals(1, dao.getAllOrgs().size());
        dao.addOrg(testOrg);
        Assert.assertEquals(2, dao.getAllOrgs().size());
    }

    @Test
    public void testRemoveOrg() {
        Assert.assertEquals(1, dao.getAllOrgs().size());
        dao.removeOrg(org1);
        Assert.assertEquals(0, dao.getAllOrgs().size());
    }
    
    @Test
    public void testUpdateOrg() {
        String changed = "Changed";
        String chngPhone = "1-800-000-0000";
        org1.setName(changed);
        org1.setDescription(changed);
        org1.setLocation(loc2);
        org1.setPhoneType(changed);
        org1.setPhoneNumber(chngPhone);
        dao.updateOrg(org1);
        Organization fromDb = dao.getOrgById(org1.getId());
        Assert.assertEquals(changed, fromDb.getName());
        Assert.assertEquals(changed, fromDb.getDescription());
        Assert.assertEquals(loc2, fromDb.getLocation());
        Assert.assertEquals(changed, fromDb.getPhoneType());
        Assert.assertEquals(chngPhone, fromDb.getPhoneNumber());
    }
    
    // LOCATIONS

    @Test
    public void testGetAllLocations() {
        Assert.assertEquals(2, dao.getAllLocations().size());
        dao.addLocation(testLoc);
        Assert.assertEquals(3, dao.getAllLocations().size());
    }
    
    @Test
    public void testAddGetLocation() {
        dao.addLocation(testLoc);
        Location fromDb = dao.getLocationById(testLoc.getId());
        Assert.assertEquals(fromDb, testLoc);
    }
    
    @Test
    public void testRemoveLocation() {
        Assert.assertEquals(2, dao.getAllLocations().size());
        Assert.assertEquals(2, dao.getAllSightings().size());
        Location toRemove = dao.getLocationById(loc1.getId());
        dao.removeLocation(toRemove);
        Assert.assertEquals("Unknown.",
                dao.getOrgById(org1.getId()).getLocation().getName());
        Assert.assertEquals("Location should be removed.", 1,
                dao.getAllLocations().size());
        Assert.assertEquals("Sighting should be removed.", 1,
                dao.getAllSightings().size());
    }
    
    @Test
    public void testUpdateLocation() {
        String changed = "Changed";
        loc1.setName(changed);
        loc1.setDescription(changed);
        loc1.setAddress(changed);
        loc1.setCity(changed);
        loc1.setRegion(changed);
        loc1.setCountry(changed);
        BigDecimal newLat = new BigDecimal("44.4444");
        loc1.setLatitude(newLat);
        BigDecimal newLon = new BigDecimal("140.4444");
        loc1.setLongitude(newLon);
        dao.updateLocation(loc1);
        Location fromDb = dao.getLocationById(loc1.getId());
        Assert.assertEquals(changed, fromDb.getName());
        Assert.assertEquals(changed, fromDb.getDescription());
        Assert.assertEquals(changed, fromDb.getAddress());
        Assert.assertEquals(changed, fromDb.getCity());
        Assert.assertEquals(changed, fromDb.getRegion());
        Assert.assertEquals(changed, fromDb.getCountry());
    }

    // SIGHTINGS
    @Test
    public void testAddGetSighting() {
        dao.addSighting(testSight);
        Sighting fromDb = dao.getSightingById(testSight.getId());
        Assert.assertEquals(fromDb, testSight);
    }

    @Test
    public void testGetAllSightings() {
        Assert.assertEquals(2, dao.getAllSightings().size());
        dao.addSighting(testSight);
        Assert.assertEquals(3, dao.getAllSightings().size());
    }

    @Test
    public void testRemoveSighting() {
        Assert.assertEquals(2, dao.getAllSightings().size());
        dao.removeSighting(sight1);
        Assert.assertEquals(1, dao.getAllSightings().size());
    }
    
    @Test
    public void testUpdateSighting() {
        sight1.setLocation(loc1);
        sight1.setDate(LocalDate.parse("2017-04-20"));
        List<Hero> heroes = new ArrayList<>();
        heroes.add(hero2);
        sight1.setHeroes(heroes);
        dao.updateSighting(sight1);
        Sighting fromDb = dao.getSightingById(sight1.getId());
        Assert.assertEquals(loc1, fromDb.getLocation());
        Assert.assertEquals(LocalDate.parse("2017-04-20"), fromDb.getDate());
        Assert.assertEquals(heroes, fromDb.getHeroes());
    }
}
