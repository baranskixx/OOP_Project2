package proj2;

/**
 * Class holding all stats of players kingdom during the game.
 */
public class Kingdom {
//  basic attributes of Kingdom
    private int armyLevel = 50;
    private int goldLevel = 50;
    private int foodLevel = 50;
    private int techLevel = 50;

//  kingdom attributes max level
    public final int MAX_ATTRIBUTE_LVL = 100;

    /**
     * Class constructor.
     */
    public Kingdom(){
        ;
    }

    /**
     * Increase army level by parameter value. Kingdoms army level cannot be bigger than 100 and less than 0.
     * @param val value of army level change (can be negative)
     */
    public void addArmy(int val){
        this.armyLevel = Math.max(0, Math.min(MAX_ATTRIBUTE_LVL, this.armyLevel + val));
    }

    /**
     * Increase gold level by parameter value. Kingdoms gold level cannot be bigger than 100 and less than 0.
     * @param val value of gold level change (can be negative)
     */
    public void addGold(int val){
        this.goldLevel = Math.max(0, Math.min(MAX_ATTRIBUTE_LVL, this.goldLevel + val));
    }

    /**
     * Increase food level by parameter value. Kingdoms food level cannot be bigger than 100 and less than 0.
     * @param val value of food level change (can be negative)
     */
    public void addFood(int val){
        this.foodLevel = Math.max(0, Math.min(MAX_ATTRIBUTE_LVL, this.foodLevel + val));
    }
    /**
     * Increase technics level by parameter value. Kingdoms technics level cannot be bigger than 100 and less than 0.
     * @param val value of technics level change (can be negative)
     */
    public void addTech(int val){
        this.techLevel = Math.max(0, Math.min(MAX_ATTRIBUTE_LVL, this.techLevel + val));
    }

    /**
     * Check if kingdom has fallen (any of four statistics level fell to 0).
     * @return boolean value
     */
    public boolean kingdomFallen(){
        return !(this.armyLevel > 0 && this.foodLevel > 0 && this.goldLevel > 0 && this.techLevel > 0);
    }

    /**
     * Reset kingdoms statistics to default levels (50).
     */
    public void resetKingdom(){
        armyLevel = 50;
        goldLevel = 50;
        foodLevel = 50;
        techLevel = 50;
    }

    /*
    Bunch of kingdom statistics getters.
     */

    public int getArmyLevel() {
        return armyLevel;
    }

    public int getGoldLevel() {
        return goldLevel;
    }

    public int getFoodLevel() {
        return foodLevel;
    }

    public int getTechLevel() {
        return techLevel;
    }

    public String toString(){
        return ((Integer)armyLevel).toString() + " ; " +
                ((Integer)goldLevel).toString() + " ; " +
                ((Integer)foodLevel).toString() + " ; " +
                ((Integer)techLevel).toString();
    }
}
