package model;

public abstract class Card
{
    protected String id;
    protected int points;
    
    public Card (int count)
    {
        this.id = "C" + count++;
        this.points = 0;
    }
    
    public String getID ()
    {
        return id;
    }
    
    public int getPoints ()
    {
        return points;
    }
    
    public void print ()
    {
        System.out.println ("ID: " + id + "  Points: " + points);
    }
    
    public String toString()
    {
        return "Card "+id+" has "+points+" points";
    }
    
    public abstract String getType();
    public abstract String getName();
    public abstract String getEmail();
    public abstract double getBalance();
    public abstract void addPurchase (double amount);
}
