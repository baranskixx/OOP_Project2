package proj2;

import java.util.ArrayList;

public class History {
    private final ArrayList<Card>    cardsHistory       = new ArrayList<>();
    private final ArrayList<Boolean> decisionHistory    = new ArrayList<>();

    /**
     * Default constructor.
     */
    public History(){ }

    /**
     * Add new record to history ; add new card to cardHistory and new boolean value to decisionHistory.
     * @param c Card added to history.
     * @param applied Decision made for this card.
     */
    public void addHistoryElement(Card c, boolean applied){
        cardsHistory.add(c);
        decisionHistory.add(applied);
    }

    /**
     *
     * @return History of decisions as array of strings.
     */
    public String[] generateHistoryString(){
        String[] history = new String[cardsHistory.size()];

        for (int i=0; i<cardsHistory.size(); i++){
            Card c = cardsHistory.get(i);
            history[i] = (i+1) + ". " + c.getArmyNo() + "/" + c.getGoldNo() + "/" + c.getFoodNo() + "/" + c.getTechNo() +
                    " <-- | --> " + c.getArmyYes() + "/" + c.getGoldYes() + "/" + c.getFoodYes() + "/" + c.getTechYes() +
                    " Decyzja: " + (decisionHistory.get(i) ? "Tak" : "Nie");
        }
        return history;
    }

    /**
     * Delete all elements of cardsHistory and decisionHistory.
     */
    public void clearHistory(){
        cardsHistory.clear();
        decisionHistory.clear();
    }

}
