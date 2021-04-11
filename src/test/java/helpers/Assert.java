package helpers;
import src.Traveler;

public class Assert{
    public static boolean assertEquals(Traveler expected, Traveler actual) {
        if(expected.getHasMoney() != actual.getHasMoney()
                || expected.getIsAlive() != actual.getIsAlive()
                || expected.getIsRunning() != actual.getIsRunning()
                || expected.getPassedGate() != actual.getPassedGate()
                || expected.getMoney() != actual.getMoney()
        ){
            return false;
        }
        return true;
    }
}
