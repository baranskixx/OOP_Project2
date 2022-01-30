package proj2.CSV;

import com.opencsv.CSVWriter;
import proj2.Leaderboard;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CsvWriter {
    private static final String relLeaderboardFilepath = "\\src\\main\\resources\\leaderboard.csv";
    private static final String leaderboardFilepath = new File("").getAbsolutePath().concat(relLeaderboardFilepath);

    /**
     * Save new records in leaderboard to csv file. Run during the program shutdown.
     * @param leaderboard Leaderboard which newly added records are taken from.
     */
    public static void saveNewLeaderboardRecordsCsv(Leaderboard leaderboard){
        CSVWriter leaderboardWriter;
        try {
            leaderboardWriter = new CSVWriter(new FileWriter(leaderboardFilepath, true),
                    CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
            leaderboardWriter.writeAll(leaderboard.getNewRecords());
            leaderboardWriter.close();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }

}
