import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import src.Traveler;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests for the Traveler Class")
public class TravelerTest {
    static Traveler travelerTest;

    @BeforeAll
    public static void setup(){
        travelerTest = new Traveler(false, 0);
    }

    @Test
    @DisplayName("Operations with isAlive boolean")
    public void testOperationsIsAlive() {
        travelerTest.setIsAlive(true);
        assertTrue(travelerTest.getIsAlive());

        travelerTest.setIsAlive(false);
        assertFalse(travelerTest.getIsAlive());
    }

    @Test
    @DisplayName("Operations with money status and quantity setting and getting")
    public void testOperationsHasMoney(){
        // case where the traveler has money
        travelerTest.setHasMoney(true, 20);
        assertTrue(travelerTest.getHasMoney());
        assertEquals(20, travelerTest.getMoney());

        // case where traveler does not have money
        travelerTest.setHasMoney(false, 0);
        assertFalse(travelerTest.getHasMoney());
        assertEquals(0, travelerTest.getMoney());

        // case where the hasMoney boolean does not sync with the amount of money
        try{
            travelerTest.setHasMoney(false, 1);
        } catch (Exception e) {
            assertEquals("If the traveler does not have money, he cannot have money", e.getMessage());
        }

        // case where the value of money is negative
        try{
            travelerTest.setHasMoney(true, -1);
        } catch (Exception e) {
            assertEquals("The value of variable 'Money' cannot be negative", e.getMessage());
        }
    }

    @Test
    @DisplayName("Operations with hasPassedGate boolean")
    public void testOperationsHasPassedGate(){
        travelerTest.setHasPassedGate(true);
        assertTrue(travelerTest.getPassedGate());

        travelerTest.setHasPassedGate(false);
        assertFalse(travelerTest.getPassedGate());
    }

    @Test
    @DisplayName("Operations with isRunning boolean")
    public void testOperationsIsRunning(){
        travelerTest.setIsRunning(true);
        assertTrue(travelerTest.getIsRunning());

        travelerTest.setIsRunning(false);
        assertFalse(travelerTest.getIsRunning());
    }

    @Test
    @DisplayName("Interactions")
    public void testAttemptForPaying(){
        // case 1 when the traveler has enough money to pass
        travelerTest.setHasMoney(true, 40);
        boolean result = travelerTest.attemptForPaying(20);
        assertTrue(result, "The traveler should be able to pass with 40 money when the price is 20");
        assertTrue(travelerTest.getPassedGate());
        assertEquals(20, travelerTest.getMoney());

        // case 2 when the traveler does not have enough money
        travelerTest.setHasMoney(true, 19);
        result = travelerTest.attemptForPaying(20);
        assertFalse(result);
        assertTrue(travelerTest.getIsRunning());
    }
}