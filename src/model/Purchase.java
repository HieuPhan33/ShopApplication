package model;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Purchase 
{
    private String   rID;
    private String   cID;
    private String   time;
    private double[] amount;
    
    private static int count = 1;
    public  static final int CATEGORIES = 5;
    
    public Purchase (String cID,String time, double[] amount)
    {
        this.rID  = "R" + count++;
        this.cID  = cID;
        this.time = time;
        this.amount = new double[CATEGORIES];
        System.arraycopy(amount, 0, this.amount, 0, CATEGORIES);
    }
    
    public String[] getCategory(int i){
        //Category and its amount
        String category[] = new String[2];
        switch(i){
            case 0: category[0] = "meat";break;
            case 1: category[0] = "decoration";break;
            case 2: category[0] = "dairy";break;
            case 3: category[0] = "vegetables";break;
            default: category[0] = "clothes";break;   
        }
        category[1] = Double.toString(amount[i]);
        return category;
    }
    
    public void setCID(String cID){
        this.cID = cID;
    }
    
    public double getValue ()
    {
        double value = 0;
        for (int i = 0; i < amount.length; i++)
            value += amount[i];
        return value;
    }
    public String getRID(){
        return rID;
    }
    public String getCID(){
        return cID;
    }
    public String getTime(){
        return time;
    }
    public String eachAmount(){
        String text = Double.toString(amount[0]);
        for(int i = 1 ; i < 5; i++){
            text += ","+amount[i];
        }
        return text;
    }
    public LocalDateTime getDate(){
        return LocalDateTime.parse(time, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
    
}
