package model;

public abstract class NameCard extends Card
{
    protected String name;
    protected String email;
    protected double balance;
    
    public NameCard (int count, String name, String email)
    {
        super (count);
        this.name = name;
        this.email = email;
        this.balance = 0;
    }
    public abstract String getType();
}
