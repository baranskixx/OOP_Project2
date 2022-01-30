package proj2;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import proj2.CSV.CsvLoader;
import proj2.CSV.CsvWriter;

import java.util.List;

public class App<pritvate> extends Application {

    /*
    Basic program information.
     */
    private final String  GAMETITLE   = "Which king are you?";
    private final Image   ICON        = new Image("king.png");

    /*
    Leaderboard.
     */
    private final Leaderboard       leaderboard         = new Leaderboard();
    private ListView<String>        leaderboardList     = new ListView<>();

    /*
    History.
     */
    private final History                 history         = new History();
    private ListView<String>        historyList     = new ListView<>();
    private final Label             historyLabel    = new Label("Twoje zagrania: ");

    /*
    Fonts.
     */
    private final Font descriptionFont    = new Font("Cambria", 18);
    private final Font scoreFont          = new Font("Georgia", 30);
    private final Font cardStatsFont      = new Font("Georgia", 18);
    private final Font titleFont          = new Font("Georgia", 40);
    private final Font leaderboardFont    = new Font("Georgia", 24);
    private final Font playerFont         = new Font("Georgia", 18);
    private final Font gameOverFont       = new Font("Georgia", 60);
    private final Font gameOverScoreFont  = new Font("Georgia", 20);
    private final Font historyFont        = new Font("Georgia", 24);

    /*
    Start stage elements and properties are declared below.
     */
    private final CheckBox    easyCB              = new CheckBox("Easy mode?");
    private final Label       titleLabel          = new Label(GAMETITLE);
    private final Label       leaderboardLabel    = new Label("Najlepsi Gracze");
    private final Button      startBtn            = new Button("Zaczynajmy!");
    private VBox              startVBox           = new VBox(titleLabel, leaderboardLabel, leaderboardList, startBtn, easyCB);
    private final Stage       startStage          = new Stage();
    private final Scene       startScene          = new Scene(startVBox);
    private final int         startPxlWidth       = 400;
    private final int         startPxlHeight      = 400;


    private final Kingdom   kingdom = new Kingdom();
    private GameEngine      engine;
    private boolean         easyMode;

    /*
    In-game stage elements.
     */
    private final Scene ingameScene = new Scene(new VBox());
    private final Stage ingameStage = new Stage();

    private int ingamePxlWidth  = 800;
    private int ingamePxlHeight = 600;

    private final Image armyIcon = new Image("armyIcon.png");
    private final Image goldIcon = new Image("goldIcon.png");
    private final Image foodIcon = new Image("foodIcon.png");
    private final Image techIcon = new Image("techIcon.png");

    private final ImageView armyImg = new ImageView(armyIcon);
    private final ImageView goldImg = new ImageView(goldIcon);
    private final ImageView foodImg = new ImageView(foodIcon);
    private final ImageView techImg = new ImageView(techIcon);

    private Label armyLabel;
    private Label goldLabel;
    private Label foodLabel;
    private Label techLabel;
    private Label scoreLabel;

    private final Button noBtn  = new Button("Nie!");
    private final Button yesBtn = new Button("Tak!");
    private final HBox chooseHBox = new HBox(noBtn, yesBtn);

    private final VBox ingameMainVBox = new VBox();
    private HBox scoreHBox;
    private HBox armyHBox;
    private HBox goldHBox;
    private HBox foodHBox;
    private HBox techHBox;
    private HBox statsHBox;

    /*
    Card GUI.
     */
    private final VBox       cardVBox           = new VBox();
    private final TextArea   cardDescription    = new TextArea();
    private final Background cardBg             = new Background(new BackgroundFill(Color.LIGHTGRAY,
            new CornerRadii(2.0),Insets.EMPTY));

    private final String      armyEffects     = "???/???";
    private final ImageView   armyCardImg     = new ImageView(armyIcon);
    private final Label       armyCardLabel   = new Label(armyEffects);
    private final HBox        armyCardHBox    = new HBox(armyCardImg, armyCardLabel);

    private final String      goldEffects     = "???/???";
    private final ImageView   goldCardImg     = new ImageView(goldIcon);
    private final Label       goldCardLabel   = new Label(goldEffects);
    private final HBox        goldCardHBox    = new HBox(goldCardImg, goldCardLabel);

    private final String      foodEffects     = "???/???";
    private final ImageView   foodCardImg     = new ImageView(foodIcon);
    private final Label       foodCardLabel   = new Label(foodEffects);
    private final HBox        foodCardHBox    = new HBox(foodCardImg, foodCardLabel);

    private final String      techEffects     = "???/???";
    private final ImageView   techCardImg     = new ImageView(techIcon);
    private final Label       techCardLabel   = new Label(techEffects);
    private final HBox        techCardHBox    = new HBox(techCardImg, techCardLabel);


    /*
    Score save stage elements.
     */
    private int                 saveScoreWidth      = 300;
    private int                 saveScoreHeight     = 300;
    private final Stage         saveScoreStage      = new Stage();
    private Scene               saveScoreScene;
    private VBox                saveScoreVBox       = new VBox();
    private final TextField     playerNameTextField = new TextField();
    private final Label         playerNameLabel     = new Label("Podaj swoje imię:");
    private final Button        saveBtn             = new Button("Zapisz.");


    /*
    Game over stage elements and properties are declared below.
     */
    private final Button    tryAgainBtn = new Button("Powrót do ekranu startowego.");
    private final Label     gameOverLabel = new Label("Game Over!");
    private Label           gameOverScoreLabel;
    private VBox            gameOverVBox;
    private Scene           gameOverScene;
    private Stage           gameOverStage = new Stage();
    private int             gameOverPxlWidth  =  400;
    private int             gameOverPxlHeight =  640;

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println(javafx.scene.text.Font.getFamilies());
        // loading deck and leaderboard from file
        List<Card> deck = CsvLoader.loadCardsCsv();
        // initializing engine
        engine = new GameEngine(kingdom, deck);

        // Start screen settings.
        startStage.setResizable(false);
        startStage.setTitle(GAMETITLE);
        startStage.getIcons().add(ICON);      // King icon created by IdeaGrafc from Flaticon.com
        startStage.setWidth(startPxlWidth);
        startStage.setHeight(startPxlHeight);
        startStage.setScene(startScene);
        easyCB.setAlignment(Pos.BOTTOM_LEFT);
        startVBox.setAlignment(Pos.TOP_CENTER);
        titleLabel.setFont(titleFont);
        leaderboardLabel.setFont(leaderboardFont);
        updateLeaderboardView();


        // in-game stage settings
        ingameStage.setWidth(ingamePxlWidth);
        ingameStage.setHeight(ingamePxlHeight);
        ingameStage.setResizable(false);
        ingameStage.setTitle(GAMETITLE);
        ingameStage.getIcons().add(ICON);

        armyImg.setFitWidth(40);
        armyImg.setFitHeight(40);
        goldImg.setFitWidth(40);
        goldImg.setFitHeight(40);
        foodImg.setFitWidth(40);
        foodImg.setFitHeight(40);
        techImg.setFitWidth(40);
        techImg.setFitHeight(40);

        armyCardImg.setFitWidth(32);
        armyCardImg.setFitHeight(32);
        goldCardImg.setFitWidth(32);
        goldCardImg.setFitHeight(32);
        foodCardImg.setFitWidth(32);
        foodCardImg.setFitHeight(32);
        techCardImg.setFitWidth(32);
        techCardImg.setFitHeight(32);

        chooseHBox.setAlignment(Pos.TOP_CENTER);
        chooseHBox.setAlignment(Pos.TOP_CENTER);
        chooseHBox.setSpacing(80);

        ingameMainVBox.setSpacing(20);
        ingameMainVBox.setAlignment(Pos.CENTER);


        //Card GUI settings
        cardVBox.setMaxSize(300, 400);
        cardVBox.setPrefSize(300, 400);
        cardVBox.setAlignment(Pos.TOP_CENTER);
        cardVBox.setBackground(cardBg);

        cardDescription.setWrapText(true);
        cardDescription.setEditable(false);
        cardDescription.setFocusTraversable(false);
        cardDescription.setBackground(cardBg);
        cardDescription.setDisable(true);
        cardDescription.setStyle("-fx-text-fill: black; -fx-text-align: justify");
        cardDescription.setFont(descriptionFont);
        cardDescription.setMaxSize(240, 200);

        armyCardHBox.setAlignment(Pos.CENTER);
        armyCardHBox.setSpacing(8);
        goldCardHBox.setAlignment(Pos.CENTER);
        goldCardHBox.setSpacing(8);
        foodCardHBox.setAlignment(Pos.CENTER);
        foodCardHBox.setSpacing(8);
        techCardHBox.setAlignment(Pos.CENTER);
        techCardHBox.setSpacing(8);

        armyCardLabel.setFont(cardStatsFont);
        goldCardLabel.setFont(cardStatsFont);
        foodCardLabel.setFont(cardStatsFont);
        techCardLabel.setFont(cardStatsFont);


        // Name input stage settings

        saveScoreStage.setHeight(saveScoreHeight);
        saveScoreStage.setWidth(saveScoreWidth);
        saveScoreStage.setResizable(false);
        saveScoreStage.setTitle(GAMETITLE);
        saveScoreStage.getIcons().add(ICON);

        saveScoreVBox.setAlignment(Pos.TOP_CENTER);
        saveScoreVBox.setSpacing(40);
        playerNameLabel.setFont(playerFont);

        // game over stage settings

        historyLabel.setFont(historyFont);
        gameOverLabel.setFont(gameOverFont);
        gameOverStage.setWidth(gameOverPxlWidth);
        gameOverStage.setHeight(gameOverPxlHeight);
        gameOverStage.setTitle(GAMETITLE);
        gameOverStage.getIcons().add(ICON);

        // what happens when you click 'x' button on start screen
        startStage.setOnCloseRequest(event -> closeProgram());

        ingameStage.setOnCloseRequest(event -> closeProgram());

        saveScoreStage.setOnCloseRequest(event -> closeProgram());

        // what happens when you click 'x' button on game over screen
        gameOverStage.setOnCloseRequest(event -> closeProgram());

        // what happens when button Start Game! clicked
        startBtn.setOnAction(event -> {
            startStage.close();
            easyMode = easyCB.isSelected();
            play();
        });

        // what happens when Try again! button clicked
        tryAgainBtn.setOnAction(event -> {
            gameOverStage.close();
            displayMenu();
        });

        yesBtn.setOnAction(event -> {
            engine.applyCardEffectAccept();
            history.addHistoryElement(engine.getHeldCard(), true);
            applyMove();
        });

        noBtn.setOnAction(event -> {
            engine.applyCardEffectDecline();
            history.addHistoryElement(engine.getHeldCard(), false);
            applyMove();
        });

        saveBtn.setOnAction(event -> {
            saveScoreStage.close();
            leaderboard.addNewRecord(playerNameTextField.getText(), engine.getScore());
            displayGameOver();
        });

        displayMenu();
    }

    public void play(){
        kingdom.resetKingdom();
        history.clearHistory();
        engine.setScore(0);
        displayGame();
    }

    public void applyMove(){
        if (kingdom.kingdomFallen()){
            ingameStage.close();
            if(engine.getScore() > 0){
                displayNameInput();
            }
            else {
                displayGameOver();
            }
        }
        else {
            engine.updateHeldCard();
            engine.increaseScore();
            displayGame();
        }
    }

    public void displayNameInput(){
        saveScoreVBox = new VBox();
        saveScoreVBox.getChildren().addAll(playerNameLabel, playerNameTextField, saveBtn);
        saveScoreVBox.setAlignment(Pos.CENTER);
        saveScoreVBox.setSpacing(20);

        saveScoreScene = new Scene(saveScoreVBox);
        saveScoreStage.setScene(saveScoreScene);
        saveScoreStage.show();
    }

    public void displayMenu(){
        // showing the start screen
        startVBox = new VBox(titleLabel, leaderboardLabel, leaderboardList, startBtn, easyCB);
        startVBox.setAlignment(Pos.CENTER);
        startVBox.setSpacing(20);
        startScene.setRoot(startVBox);
        startStage.setScene(startScene);
        startStage.show();
    }

    public void displayGameOver(){
        updateLeaderboardView();
        generateHistoryView();
        gameOverScoreLabel = new Label("Wynik: " + engine.getScore());
        gameOverScoreLabel.setFont(gameOverScoreFont);
        gameOverVBox = new VBox(gameOverLabel, gameOverScoreLabel, leaderboardList, historyLabel, historyList, tryAgainBtn);
        gameOverVBox.setSpacing(20);
        gameOverVBox.setAlignment(Pos.TOP_CENTER);
        gameOverScene = new Scene(gameOverVBox);
        gameOverStage.setScene(gameOverScene);
        gameOverStage.show();
    }

    public void displayGame(){
        updateStats();
        updateIngameMainVBox();
        updateCard();
//        System.out.println(engine.getHeldCard());

        ingameScene.setRoot(ingameMainVBox);
        ingameStage.setScene(ingameScene);
        ingameStage.show();
    }

    private void updateStats(){
        StringBuilder scoreStr = new StringBuilder(((Integer) engine.getScore()).toString());
        while (scoreStr.length() < 5){
            scoreStr.insert(0, "0");
        }

        scoreLabel = new Label("Score: " + scoreStr);
        scoreLabel.setFont(scoreFont);
        armyLabel = new Label(((Integer) kingdom.getArmyLevel()).toString());
        armyLabel.setFont(scoreFont);
        armyLabel.setTextFill(Color.RED);
        goldLabel = new Label(((Integer) kingdom.getGoldLevel()).toString());
        goldLabel.setFont(scoreFont);
        goldLabel.setTextFill(Color.RED);
        foodLabel = new Label(((Integer) kingdom.getFoodLevel()).toString());
        foodLabel.setFont(scoreFont);
        foodLabel.setTextFill(Color.RED);
        techLabel = new Label(((Integer) kingdom.getTechLevel()).toString());
        techLabel.setFont(scoreFont);
        techLabel.setTextFill(Color.RED);

        scoreHBox = new HBox(scoreLabel);

        armyHBox = new HBox(armyImg, armyLabel);
        armyHBox.setSpacing(20);
        goldHBox = new HBox(goldImg, goldLabel);
        goldHBox.setSpacing(20);
        foodHBox = new HBox(foodImg, foodLabel);
        foodHBox.setSpacing(20);
        techHBox = new HBox(techImg, techLabel);
        techHBox.setSpacing(20);

        scoreHBox.setAlignment(Pos.CENTER);
        armyHBox.setAlignment(Pos.CENTER);
        goldHBox.setAlignment(Pos.CENTER);
        foodHBox.setAlignment(Pos.CENTER);
        techHBox.setAlignment(Pos.CENTER);

        statsHBox = new HBox(scoreHBox, armyHBox, goldHBox, foodHBox, techHBox);
        statsHBox.setPrefSize(800, 60);
        statsHBox.setAlignment(Pos.CENTER);
        statsHBox.setSpacing(40);
    }

    private void updateIngameMainVBox(){
        ingameMainVBox.getChildren().clear();
        ingameMainVBox.getChildren().addAll(statsHBox, cardVBox, chooseHBox);
    }

    private void updateCard(){
        Card c = engine.getHeldCard();
        if (easyMode){
            armyCardLabel.setText(c.getArmyNo() + "/" + c.getArmyYes());
            goldCardLabel.setText(c.getGoldNo() + "/" + c.getGoldYes());
            foodCardLabel.setText(c.getFoodNo() + "/" + c.getFoodYes());
            techCardLabel.setText(c.getTechNo() + "/" + c.getTechYes());
        }

        cardVBox.getChildren().clear();
        cardDescription.setText(engine.getHeldCard().getDescription());
        cardDescription.setStyle("-fx-text-fill: black; -fx-text-align: justify");
        cardVBox.getChildren().addAll(cardDescription, armyCardHBox, goldCardHBox, foodCardHBox, techCardHBox);
        cardVBox.setAlignment(Pos.TOP_CENTER);
        cardVBox.setSpacing(12);
    }

    public void updateLeaderboardView(){
        leaderboardList = new ListView<>();
        ObservableList<String> leaderboardRecords = FXCollections.observableArrayList(leaderboard.getFormattedLeaderboardRecords());
        leaderboardList.setItems(leaderboardRecords);
        leaderboardList.setPrefHeight(160);
        leaderboardList.setPrefWidth(240);
    }

    private void generateHistoryView(){
        historyList = new ListView<>();
        ObservableList<String> historyRecords = FXCollections.observableArrayList(history.generateHistoryString());
        historyList.setItems(historyRecords);
        historyList.setPrefWidth(240);
        historyList.setPrefHeight(160);
    }

    private void closeProgram(){
        CsvWriter.saveNewLeaderboardRecordsCsv(leaderboard);
        Platform.exit();
        System.exit(0);
    }
}
