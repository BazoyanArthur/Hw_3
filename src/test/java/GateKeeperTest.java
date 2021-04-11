import helpers.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.GateKeeper;
import src.Traveler;


import static org.junit.jupiter.api.Assertions.*;

public class GateKeeperTest {
    final static GateKeeper gateKeeperTest = new GateKeeper(true,0, 0, null);

    @BeforeEach
    public void setup(){

    }

    @Test
    public void testOperationsMood(){
        gateKeeperTest.setMood(false);
        assertFalse(gateKeeperTest.getMood());
    }

    @Test
    void testOperationsDiscount(){
        gateKeeperTest.setDiscount(10);
        assertEquals(10, gateKeeperTest.getDiscount());
    }

    @Test
    void testOperationsPaidStatus(){
        gateKeeperTest.setPaidStatus(false);
        assertFalse(gateKeeperTest.getPaidStatus());
    }

    @Test
    void testGetDayTime(){
        gateKeeperTest.setDayTime(4);
        assertEquals(4, gateKeeperTest.getDayTime());
    }

    @Test
    void testSetPrices(){
        // case 1 when its morning and the mood is bad(false)
        gateKeeperTest.setDiscount(10);
        gateKeeperTest.setMood(false);
        gateKeeperTest.setDayTime(0);
        assertEquals(5, gateKeeperTest.getBasePrice());
        assertEquals(5.5, gateKeeperTest.getFinalPrice());

        // case 2 when its midday and the mood is bad(false)
        gateKeeperTest.setDiscount(10);
        gateKeeperTest.setMood(false);
        gateKeeperTest.setDayTime(1);
        assertEquals(10, gateKeeperTest.getBasePrice());
        assertEquals(11, gateKeeperTest.getFinalPrice());

        // case 3 when its midday but its 4 and the mood is good(true)
        gateKeeperTest.setDiscount(10);
        gateKeeperTest.setMood(true);
        gateKeeperTest.setDayTime(4);
        assertEquals(10, gateKeeperTest.getBasePrice());
        assertEquals(9, gateKeeperTest.getFinalPrice());

        // case 5 when its the end of the day but and the mood is bad(false)
        gateKeeperTest.setDiscount(10);
        gateKeeperTest.setMood(false);
        gateKeeperTest.setDayTime(5);
        assertEquals(20, gateKeeperTest.getBasePrice());
        assertEquals(22, gateKeeperTest.getFinalPrice());
    }

    @Test
    public void testOperationsTraveler(){
        Traveler travelerData = new Traveler(true,20);
        gateKeeperTest.setTraveler(travelerData);
        assertTrue(Assert.assertEquals(travelerData, gateKeeperTest.getTraveler()));
    }

    @Test
    public void testInteractiWithTraveler() {
        // case where the final price is 22 and the traveler has only 4
        Traveler traveler = new Traveler(true, 4);
        gateKeeperTest.setDiscount(10);
        gateKeeperTest.setMood(false);
        gateKeeperTest.setDayTime(5);
        gateKeeperTest.setTraveler(traveler);
        gateKeeperTest.interactWithTraveler();
        assertFalse(gateKeeperTest.getPaidStatus());
        assertFalse(gateKeeperTest.getTraveler().getIsRunning());
        assertFalse(gateKeeperTest.getTraveler().getIsAlive());

        // case where the final price is 22 and the traveler has 25
        traveler = new Traveler(true, 25);
        gateKeeperTest.setTraveler(traveler);
        gateKeeperTest.interactWithTraveler();
        assertTrue(gateKeeperTest.getPaidStatus());
        assertEquals(22, gateKeeperTest.getIncome());
    }




}