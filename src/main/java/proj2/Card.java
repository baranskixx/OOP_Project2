package proj2;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

/**
 * Class used to represent one card. No constructor - cards are loaded directly from CSV file using OpenCSV beans.
 */
public class Card {
    @CsvBindByName
    private int armyYes;
    @CsvBindByName
    private int goldYes;
    @CsvBindByName
    private int foodYes;
    @CsvBindByName
    private int techYes;
    @CsvBindByName
    private int armyNo;
    @CsvBindByName
    private int goldNo;
    @CsvBindByName
    private int foodNo;
    @CsvBindByName
    private int techNo;
    @CsvBindByName
    private String description;

//  Bunch of getters below.

    public int getArmyYes() {
        return armyYes;
    }

    public int getGoldYes() {
        return goldYes;
    }

    public int getFoodYes() {
        return foodYes;
    }

    public int getTechYes() {
        return techYes;
    }

    public int getArmyNo() {
        return armyNo;
    }

    public int getGoldNo() {
        return goldNo;
    }

    public int getFoodNo() {
        return foodNo;
    }

    public int getTechNo() {
        return techNo;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Get String representation of a card.
     * @return String containing part of description of a card and information about its effects.
     */
    public String toString(){
        return "Opis: " + description.substring(0, 30) + "...\n" +
            "Army: " + armyYes + "/" + armyNo + " Gold: " + goldYes + "/" + goldNo +
            " Food:" + foodYes + "/" + foodNo + " Tech: " + techYes + "/" + techNo + "\n-----------";
    }

    /**
     * Check equality between two cards (cards are equal when their descriptions are the same).
     * @param other Card to be compared to.
     * @return Boolean value informing about equality between two cards.
     */
    public boolean equals(Card other){
        return this.description.equals(other.description);
    }
}
