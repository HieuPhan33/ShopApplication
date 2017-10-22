package model;

public class BasicCard extends NameCard {

    public BasicCard(int count, String name, String email) {
        super(count, name, email);
    }

    public void addPurchase(double amount) {
        if (balance < 500) {
            points += amount * 0.015;
        } else {
            points += amount * 0.02;
        }
        balance += amount;
    }
    
    public void setBalance(double balance){
        this.balance = balance;
    }
    
    public void setPoints(int points){
        this.points = points;
    }
    public String getType() {
        return "basic";
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public double getBalance() {
        return balance;
    }
    public String toString(){
        return "Basic Card " + id +" of " +name + "\nhas " + points +" points ";
    }
}
