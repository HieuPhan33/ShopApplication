package model;

public class AnonCard extends Card {

    public AnonCard(int count) {
        super(count);
    }

    public void addPurchase(double amount) {
        points += amount / 100;
    }

    public String getType() {
        return "anon";
    }

    public String getName() {
        return "";
    }

    public String getEmail() {
        return "";
    }

    public double getBalance() {
        return 0;
    }

    public void setPoints(int pts) {
        points = pts;
    }
    
    public String toString(){
        return "Anon Card "+ id + " has " + points +" points ";
    }
}
