package proj2;

import proj2.CSV.CsvLoader;

import java.util.*;

public class Leaderboard {
    private final Map<Integer, ArrayList<String>> records = new TreeMap<>();
    private final List<String[]> newRecords = new ArrayList<>();
    private ArrayList<String> formattedRecords;

    private boolean formattedRecordsNeedUpdate = true;

    /**
     * Constructor.
     * Loads leaderboard records from leaderboard.csv using CsvLoader.loadLeaderboard() static method.
     * Also updates the formattedRecords list.
     */
    public Leaderboard(){
        List<String[]> rawLeaderboard = CsvLoader.loadLeaderboard();
        for(String[] line : rawLeaderboard){
            Integer score = Integer.parseInt(line[1]);
            String name = line[0];

            // list of players with exact score
            ArrayList<String> players;

            if(records.containsKey(score)){ players = records.get(score); }
            else{ players = new ArrayList<>(); }

            players.add(name);
            records.put(score, players);
        }
        updateFormattedRecords();
        System.out.println("Leaderboard initialized correctly!");
    }

    /**
     * Add new player to leaderboard.
     * @param playerName Name of the new player.
     * @param playerScore Score of the new player. Must be a positive number.
     */
    public void addNewRecord(String playerName, Integer playerScore){
        assert playerScore > 0;

        ArrayList<String> players;
        if(records.containsKey(playerScore)){ players = records.get(playerScore); }
        else { players = new ArrayList<>(); }
        players.add(playerName);

        records.put(playerScore, players);
        newRecords.add(new String[]{playerName, playerScore.toString()});
        formattedRecordsNeedUpdate = true;
    }

    /**
     * Get list of new players in leaderboard (added during the program lifetime).
     * @return List of String arrays - new players in leaderboard.
     */
    public List<String[]> getNewRecords(){
        return newRecords;
    }

    /**
     * Update the formatted record list.
     */
    private void updateFormattedRecords(){
        formattedRecords = new ArrayList<>();
        for (Integer score : records.keySet()){
            StringBuilder scoreStr = new StringBuilder(score.toString());
            while(scoreStr.length() < 5) scoreStr.insert(0, "0");
            ArrayList<String> players = records.get(score);

            for (String p : players){
                formattedRecords.add(scoreStr + " : " + p);
            }
        }
        Collections.reverse(formattedRecords);
        formattedRecordsNeedUpdate = false;
    }

    /**
     * Get list of leaderboard records. Each record is formatted to "XXXXX : Name"
     * XXXXX is 5-digit number - score of the player.
     * If the formatted list is not up-to-date with actual leaderboard then update is performed.
     * @return ArrayList of Strings.
     */
    public ArrayList<String> getFormattedLeaderboardRecords(){
        if (formattedRecordsNeedUpdate){
            updateFormattedRecords();
        }
        return formattedRecords;
    }
}
