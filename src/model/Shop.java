package model;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Shop {

    private static LinkedHashMap<String, Card> cards = new LinkedHashMap<>();
    private static ArrayList<Purchase> purchases = new ArrayList<>();

    // Report customers with < 10 points, < 50 points and >= 50 points
    //
    // Warning: the labels and threshold array must be the same length.
    public LinkedHashMap<String, Card> getCards() {
        return cards;
    }

    public int totalCards() {
        return cards.size();
    }

    public int getCardsPoints() {
        int points = 0;
        for (Map.Entry<String, Card> cardEntry : cards.entrySet()) {
            points += cardEntry.getValue().getPoints();
        }
        return points;
    }

    public double getCardsBalance() {
        double balance = 0;
        for (Map.Entry<String, Card> cardEntry : cards.entrySet()) {
            balance += cardEntry.getValue().getBalance();
        }
        return balance;
    }

    public boolean containCard(String cID) {
        return cards.containsKey(cID);
    }

    public void addCard(Card c) {
        cards.put(c.getID(), c);
    }

    public Card getCard(String cID) {
        return cards.get(cID);
    }

    public void deleteCard(String cID) {
        cards.remove(cID);
    }

    public void clearCards() {
        cards.clear();
    }

    public void addPurchase(Purchase pur) {
        purchases.add(pur);
    }

    public ArrayList<Purchase> getPurchases() {
        return purchases;
    }
    
    public String getRecentPurchase(){
        return purchases.get(purchases.size()-1).getCID();
    }

    public int totalPurchases() {
        return purchases.size();
    }

    public double getValuePurchases() {
        double total = 0;
        for (Purchase pur : purchases) {
            total += pur.getValue();
        }
        return total;
    }

    public void deletePurchase(String cID) {
        for (Purchase p : purchases) {
            if (p.getCID().equals(cID)) {
                p.setCID("CASH");
            }
        }
    }
    
    //Use to HashMap to do aggregation
    public HashMap<String, Double> getAggregation() {
        HashMap<String, Double> aggregation = new HashMap<>();
        //Declare total purchases and total value
        double total = 0;
        double totalValue = 0;
        for (Purchase pur : purchases) {
            //Accumlate total purchases and total value
            total++;
            totalValue += pur.getValue();
            //Do aggregating by categories
            for (int i = 0; i < 5; i++) {
                String category[] = pur.getCategory(i);
                if (!aggregation.containsKey(category[0])) {
                    aggregation.put(category[0], Double.parseDouble(category[1]));
                } else {
                    aggregation.put(category[0], aggregation.get(category[0]) + Double.parseDouble(category[1]));
                }
            }
        }
        //Store total purchases and total value
        aggregation.put("total", total);
        aggregation.put("totalValue", totalValue);
        return aggregation;
    }

    public HashMap<String, Double> getAggregationByDate(String startDateStr, String endDateStr) {
        HashMap<String, Double> aggregation = new HashMap<>();
        //Declare total purchases and total value
        double totalPur = 0;
        double totalVal = 0;
        startDateStr = startDateStr + "T00:00:00";
        LocalDateTime startDate = LocalDateTime.parse(startDateStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        endDateStr = endDateStr + "T00:00:00";
        LocalDateTime endDate = LocalDateTime.parse(endDateStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        endDate = LocalDateTime.from(endDate).plusDays(1);
        for (Purchase pur : purchases) {
            LocalDateTime purDate = pur.getDate();
            //Filter date
            if (purDate.compareTo(startDate) >= 0 && purDate.compareTo(endDate) < 0) {
                //Accumlate total purchases and total value
                totalPur++;
                totalVal += pur.getValue();
                //Do aggregating by categories
                for (int i = 0; i < 5; i++) {
                    String category[] = pur.getCategory(i);
                    if (!aggregation.containsKey(category[0])) {
                        aggregation.put(category[0], Double.parseDouble(category[1]));
                    } else {
                        aggregation.put(category[0], aggregation.get(category[0]) + Double.parseDouble(category[1]));
                    }
                }
            }
        }
        aggregation.put("total", totalPur);
        aggregation.put("totalValue", totalVal);
        return aggregation;
    }

    public HashMap<String, Double> getAggregationByDay(String startDateStr, String endDateStr, int dayVal) {
        HashMap<String, Double> aggregation = new HashMap<>();
        //Declare total purchases and total value
        double totalPur = 0;
        double totalVal = 0;
        startDateStr = startDateStr + "T00:00:00";
        LocalDateTime startDate = LocalDateTime.parse(startDateStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        endDateStr = endDateStr + "T00:00:00";
        LocalDateTime endDate = LocalDateTime.parse(endDateStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        endDate = LocalDateTime.from(endDate).plusDays(1);
        for (Purchase pur : purchases) {
            LocalDateTime purDate = pur.getDate();
            //Filter date and day of week
            if (purDate.compareTo(startDate) >= 0 && purDate.compareTo(endDate) < 0
                    && purDate.getDayOfWeek() == DayOfWeek.of(dayVal)) {
                //Accumlate total purchases and total value
                totalPur++;
                totalVal += pur.getValue();
                //Do aggregating by categories
                for (int i = 0; i < 5; i++) {
                    String category[] = pur.getCategory(i);
                    if (!aggregation.containsKey(category[0])) {
                        aggregation.put(category[0], Double.parseDouble(category[1]));
                    } else {
                        aggregation.put(category[0], aggregation.get(category[0]) + Double.parseDouble(category[1]));
                    }
                }
            }
        }
        aggregation.put("total", totalPur);
        aggregation.put("totalValue", totalVal);
        return aggregation;
    }

    public HashMap<String, Double> getAggregationByTime(String startDateStr, String endDateStr, int time) {
        HashMap<String, Double> aggregation = new HashMap<>();
        //Declare total purchases and total value
        double totalPur = 0;
        double totalVal = 0;
        startDateStr = startDateStr + "T00:00:00";
        LocalDateTime startDate = LocalDateTime.parse(startDateStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        endDateStr = endDateStr + "T00:00:00";
        LocalDateTime endDate = LocalDateTime.parse(endDateStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        endDate = LocalDateTime.from(endDate).plusDays(1);
        for (Purchase pur : purchases) {
            LocalDateTime purDate = pur.getDate();
            //Filter date and day of week
            if (purDate.compareTo(startDate) >= 0 && purDate.compareTo(endDate) < 0
                    && purDate.getHour() == time) {
                //Accumlate total purchases and total value
                totalPur++;
                totalVal += pur.getValue();
                //Do aggregating by categories
                for (int i = 0; i < 5; i++) {
                    String category[] = pur.getCategory(i);
                    if (!aggregation.containsKey(category[0])) {
                        aggregation.put(category[0], Double.parseDouble(category[1]));
                    } else {
                        aggregation.put(category[0], aggregation.get(category[0]) + Double.parseDouble(category[1]));
                    }
                }
            }
        }
        aggregation.put("total", totalPur);
        aggregation.put("totalValue", totalVal);
        return aggregation;
    }

    public HashMap<String, Double> getAggregationByDayTime(String startDateStr, String endDateStr, int dayVal, int time) {
        HashMap<String, Double> aggregation = new HashMap<>();
        //Declare total purchases and total value
        double totalPur = 0;
        double totalVal = 0;
        startDateStr = startDateStr + "T00:00:00";
        LocalDateTime startDate = LocalDateTime.parse(startDateStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        endDateStr = endDateStr + "T00:00:00";
        LocalDateTime endDate = LocalDateTime.parse(endDateStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        endDate = LocalDateTime.from(endDate).plusDays(1);
        for (Purchase pur : purchases) {
            LocalDateTime purDate = pur.getDate();
            //Filter date and day of week
            if (purDate.compareTo(startDate) >= 0 && purDate.compareTo(endDate) < 0
                    && purDate.getDayOfWeek() == DayOfWeek.of(dayVal) && purDate.getHour() == time) {
                //Accumlate total purchases and total value
                totalPur++;
                totalVal += pur.getValue();
                //Do aggregating by categories
                for (int i = 0; i < 5; i++) {
                    String category[] = pur.getCategory(i);
                    if (!aggregation.containsKey(category[0])) {
                        aggregation.put(category[0], Double.parseDouble(category[1]));
                    } else {
                        aggregation.put(category[0], aggregation.get(category[0]) + Double.parseDouble(category[1]));
                    }
                }
            }
        }
        aggregation.put("total", totalPur);
        aggregation.put("totalValue", totalVal);
        return aggregation;
    }

    public void clearPurchases() {
        purchases.clear();
    }

    public void makePurchase(Card card, String time, double[] amounts) {
        if (card != null) {
            Purchase p = new Purchase(card.getID(), time, amounts);
            card.addPurchase(p.getValue());
            purchases.add(p);
        } else {
            Purchase p = new Purchase("CASH", time, amounts);
            purchases.add(p);
        }
    }
    


}
