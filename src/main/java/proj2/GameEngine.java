package proj2;

import java.util.List;
import java.util.Random;

/**
 * Responsible for applying card effects to kingdom.
 */
public class GameEngine {

    private final Kingdom       kingdom;
    private final List<Card>    deck;
    private Card                heldCard;

//  player's score stats
    private int score = 0;

    /**
     * Class constructor.
     * @param kingdom Kingdom that will be connected to game engine.
     * @param deck Cards collection available during the game.
     */
    public GameEngine(Kingdom kingdom, List<Card> deck){
        this.kingdom = kingdom;
        this.deck = deck;
        heldCard = deck.get(new Random().nextInt(deck.size()));
    }

    /**
     * Pick random card from the deck and set is as held.
     */
    public void updateHeldCard(){
        Card newCard = heldCard;
        while(newCard.equals(heldCard)){
            newCard = deck.get(new Random().nextInt(deck.size()));
        }
        heldCard = newCard;
    }

    /**
     * Apply effects of accepting the card to player's kingdom.
     */
    public void applyCardEffectAccept(){
        this.kingdom.addArmy(heldCard.getArmyYes());
        this.kingdom.addGold(heldCard.getGoldYes());
        this.kingdom.addFood(heldCard.getFoodYes());
        this.kingdom.addTech(heldCard.getTechYes());
    }
    /**
     * Apply effects of declining the card to player's kingdom.
     */
    public void applyCardEffectDecline(){
        this.kingdom.addArmy(heldCard.getArmyNo());
        this.kingdom.addGold(heldCard.getGoldNo());
        this.kingdom.addFood(heldCard.getFoodNo());
        this.kingdom.addTech(heldCard.getTechNo());
    }

    /**
     * Score getter.
     * @return Score - int value.
     */
    public int getScore() {
        return score;
    }

    /**
     * Score setter.
     * @param score New score to be set.
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Get card actually held by player.
     * @return Card object.
     */
    public Card getHeldCard(){
        return heldCard;
    }

    public void increaseScore(){ score++; }
}
