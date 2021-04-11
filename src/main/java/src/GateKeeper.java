package src;

import java.util.Random;

public class GateKeeper {
    // false is bad mood, true is good mood
    private boolean mood;

    // if is true, lets through, if false does not let through
    private boolean paidStatus;

    // depending on the mood how much will the gatekeeper change the price for passing through the gate
    private double discount;

    // time of the day. changes the price at which the gateKeeper will let through the gates
    private double dayTime;

    // base price unmodified by mood or day time
    private double basePrice;

    // the price at which the gate keeper will let the traveler through
    // also changes depending on the time of day
    private double finalPrice;

    // the person that needs to pass the gate
    private Traveler traveler;

    // for keeping track of how much money he has made
    private double income;


    public GateKeeper( boolean mood, double discount, double dayTime, Traveler traveler){
        setMood(mood);
        paidStatus = false;
        this.discount = discount;
        setDayTime(dayTime);
        this.traveler = traveler;
        this.income = 0;

    }

    // setters start

    private void setBasePrice() {
        if(dayTime < 1){
            basePrice = 5;
        }
        if(dayTime >= 1 && dayTime <= 4){
            basePrice = 10;
        }
        if(dayTime > 4){
            basePrice = 20;
        }
        setFinalPrice();
    }

    private void setFinalPrice() {
        double sign = 1;
        if(mood){
            sign = -1;
        }
        finalPrice = basePrice + (sign * (discount/100 * basePrice));
    }

    public void setDiscount(double discount){
        this.discount = discount;
    }

    public void setMood(boolean mood){
        this.mood = mood;
    }

    public void setPaidStatus(boolean paidStatus){
        this.paidStatus = paidStatus;
    }

    public void setDayTime(double dayTime){
        this.dayTime = dayTime;
        setBasePrice();
    }

    public void setTraveler(Traveler traveler){
        this.traveler = traveler;
    }

    // setters end

    // getters start

    public boolean getMood(){
        return mood;
    }

    public boolean getPaidStatus(){
        return paidStatus;
    }

    public double getDiscount(){
        return discount;
    }

    public double getDayTime(){
        return dayTime;
    }

    public double getBasePrice(){
        return basePrice;
    }

    public double getFinalPrice(){
        return finalPrice;
    }

    public Traveler getTraveler(){
        return traveler;
    }

    public double getIncome(){
        return income;
    }

    // getters end

    // additional start
    public void addIncome(double money){
        income += money;
    }

    // additional end

    // functional
    public void interactWithTraveler(){
        if(traveler.attemptForPaying(finalPrice)){
            paidStatus = true;
            addIncome(finalPrice);
            return;
        }
        // if the traveler does not have enough money, well, he dies.
        paidStatus = false;
        traveler.setIsRunning(false);
        traveler.setIsAlive(false);
    }
    // functional
}
