package model;

public class PremCard extends NameCard {

    public PremCard(int count, String name, String email) {
        super(count, name, email);
    }

    public void addPurchase(double amount) {
        if (amount < 40 && balance < 1000) {
            points += amount * 0.025;
        } else {
            points += amount * 0.03;
        }
        balance += amount;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public double getBalance() {
        return balance;
    }

    public String getType() {
        return "premium";
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String toString() {
        return "Premium Card " + id + " of " + name + "\nhas " + points + " points";
    }
}
