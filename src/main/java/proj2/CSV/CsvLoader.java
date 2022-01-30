package proj2.CSV;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import proj2.Card;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CsvLoader {
    private static final String relCardsFilepath = "\\src\\main\\resources\\cards.csv";
    private static final String relLeaderboardFilepath = "\\src\\main\\resources\\leaderboard.csv";
    private static final String cardsFilepath = new File("").getAbsolutePath().concat(relCardsFilepath);
    private static final String leaderboardFilepath = new File("").getAbsolutePath().concat(relLeaderboardFilepath);

    /**
     * Load cards from CSV file.
     * @return List of cards.
     */
    public static List<Card> loadCardsCsv(){
        try {
            return new CsvToBeanBuilder<Card>(new FileReader(cardsFilepath, StandardCharsets.UTF_8))
                    .withType(Card.class).build().parse();
        }
        catch (Exception ex){
            ex.printStackTrace();
            System.exit(888);
        }
        return null;
    }

    /**
     * Load leaderboard from CSV file.
     * @return  List of String arrays. Each array consists of players name and score.
     */
    public static List<String[]> loadLeaderboard(){
        try {
            return new CSVReader(new FileReader(leaderboardFilepath)).readAll();
        }
        catch (Exception ex){
            ex.printStackTrace();
            System.exit(888);
        }
        return null;
    }
}
