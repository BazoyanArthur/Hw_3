package src;
public class Traveler {
    private boolean hasMoney;
    private double money;
    private boolean isAlive;
    private boolean hasPassedGate;
    private boolean isRunning;

    public Traveler(boolean hasMoney, double money){
        this.hasMoney = hasMoney;
        setMoney(money);
        this.isAlive = true;
        this.hasPassedGate = false;
        this.isRunning = false;
    }

    // setters start here

    public void setHasMoney(boolean hasMoney, double money){
        this.hasMoney = hasMoney;
        setMoney(money);
    }

    public void setMoney(double money){
        if(checkMoney(money)){
            this.money = money;
        }
    }

    public void setIsRunning(boolean isRunning){
        this.isRunning = isRunning;
    }

    public void setIsAlive(boolean isAlive){
        this.isAlive = isAlive;
    }

    public void setHasPassedGate(boolean hasPassedGate){
        this.hasPassedGate = hasPassedGate;
    }

    // setters end here


    // getters start here

    public boolean getIsAlive(){
        return isAlive;
    }

    public boolean getHasMoney(){
        return hasMoney;
    }

    public double getMoney(){
        return money;
    }

    public boolean getPassedGate(){
        return hasPassedGate;
    }

    public boolean getIsRunning(){
        return isRunning;
    }

    // getters end here

    // additional functions

    public boolean checkMoney(double money){
        if(!hasMoney && money != 0){
            throw new IllegalArgumentException("If the traveler does not have money, he cannot have money");
        }
        if(money < 0 ){
            throw new IllegalArgumentException("The value of variable 'Money' cannot be negative");
        }
        return true;
    }

    // additional functions end

    public boolean attemptForPaying(double price){
        if(!hasMoney || price > money){
            isRunning = true;
            return false;
        }

        money = money - price;
        hasPassedGate = true;
        return true;
    }
}
