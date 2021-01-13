import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class represents the GUI of the game.
 * @author Anastasios Kachrimanis
 * @author Charalampos Georgiadis
 * @version 24/12/2020
 */

public class GUI {
    private JFrame window; // JFrame that holds the main window of the game.
    private JLabel mainLabel; // JLabel that holds the Main Menu screen.
    private Font neonFont; // Font containing a custom font.
    private Game game; // Game Object that grants access to the game functions.
    private boolean statsExist; // Boolean containing whether the stats file exists or not.

    /**
     * Constructor.
     * @param questions Holds the directory of the questions folder.
     * @throws IOException if a file is not found.
     * @throws FontFormatException if a specified font is bad.
     */

    public GUI(File[] questions) throws IOException, FontFormatException {
        //Creates the Main frame of the game.
        window = new JFrame("Buzz");
        window.setSize(970, 550);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setLocationRelativeTo(null);

        //Displays the Main Menu screen.
        mainLabel = new JLabel(new ImageIcon("MainMenu.png"));
        window.add(mainLabel);

        //Adds an exit button to the current screen.
        exitButton(mainLabel,null,null, 698, 75, 140, 140, "MainMenuDark.png", "MainMenu.png");

        //Creates custom font from file.
        neonFont = Font.createFont(Font.TRUETYPE_FONT, new File("neon2.ttf")).deriveFont(60f);

        if (questions != null) {
            game = new Game(questions);//Loads the questions into the game.
            statsExist= game.getPlayerStats().loadPlayerStats();

            //Creates the Start Game button.
            JButton startButton = new JButton("START GAME");
            setButtonParameters(startButton,neonFont.deriveFont(60f),Color.orange,225,205,500,60, mainLabel);
            startButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    if (SwingUtilities.isLeftMouseButton(e))
                        enterNumberOfPlayers();
                }
            });
            //Creates the Leaderboards button.
            JButton leaderboardButton = new JButton("LEADERBOARDS");
            setButtonParameters(leaderboardButton, neonFont.deriveFont(60f), Color.orange,225,295,500,60, mainLabel);
            leaderboardButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    leaderboards(statsExist);
                }
            });
        }
        else{
            //Displays the "QUESTION FILE NOT FOUND" text.
            JTextArea noFileFound=new JTextArea("BUZZ QUESTIONS FILE\n   WAS NOT FOUND");
            setAreaParameters(noFileFound, neonFont.deriveFont(50f), Color.ORANGE,200,250,600,200, mainLabel);
        }
    }

    /**
     * Displays the main leaderboards screen.
     * @param statsExist Boolean containing whether the stats file exists or not.
     */

    public void leaderboards(boolean statsExist) {
        //Displays the Leaderboard screen.
        JLabel mainLeaderboardLabel = new JLabel((new ImageIcon("LeaderBoard.png")));
        changeScene(mainLabel, mainLeaderboardLabel);
        //Adds a "LEADERBOARDS" title to the label.
        JTextArea leaderboardTitle = new JTextArea("LEADERBOARDS");
        setAreaParameters(leaderboardTitle, neonFont.deriveFont(50f), Color.ORANGE, 310, 90, 360, 100, mainLeaderboardLabel);
        if (statsExist) {
            //Adds a "View leaderboards based on" title.
            JTextArea viewLeaderboardsTitle = new JTextArea("VIEW LEADERBOARDS\n       BASED ON:");
            setAreaParameters(viewLeaderboardsTitle, neonFont.deriveFont(40f), Color.yellow, 290, 150, 400, 100, mainLeaderboardLabel);

            //Adds a "HIGHSCORES" button.
            JButton highscoresButton=new JButton("HIGHSCORES");
            setButtonParameters(highscoresButton, neonFont.deriveFont(40f), Color.green, 335, 230, 300, 60, mainLeaderboardLabel);
            highscoresButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    viewSelectedLeaderboards(mainLeaderboardLabel, "HIGHSCORES");
                }
            });
            //Adds a "MULTIPLAYER WINS" button.
            JButton multiplayerWinsButton=new JButton("MULTIPLAYER WINS");
            setButtonParameters(multiplayerWinsButton, neonFont.deriveFont(40f), Color.green, 290, 300, 400, 60, mainLeaderboardLabel);
            multiplayerWinsButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    viewSelectedLeaderboards(mainLeaderboardLabel, "MULTIPLAYER WINS");
                }
            });
        }
        else {
            //Displays the "NO STATS FILE FOUND" text.
            leaderboardTitle.setText("NO STATS FILE\n     FOUND");
        }
        //Adds a Back button to the current screen.
        backButton(mainLeaderboardLabel, mainLabel,null, neonFont.deriveFont(40f), Color.red,90,78,140,50,"LeaderBoard.png","LeaderBoard.png","");
    }

    /**
     * View leaderboards based on selected sorting.
     * @param mainLeaderboardLabel JLabel containing the main leaderboard label.
     * @param chosenSorting String containing the selected sorting.
     */

    public void viewSelectedLeaderboards(JLabel mainLeaderboardLabel, String chosenSorting){
        //Displays the highscore leaderboard screen.
        JLabel currentLeaderboardLabel = new JLabel(new ImageIcon("Leaderboard.png"));
        changeScene(mainLeaderboardLabel,currentLeaderboardLabel);

        //Displays the current Leaderboards title title.
        JTextArea currentLeaderboardTitle = new JTextArea();
        setAreaParameters(currentLeaderboardTitle, neonFont.deriveFont(50f), Color.ORANGE, 320, 90, 360, 50, currentLeaderboardLabel);

        //Displays players usernames based on chosen sorting.
        JTextArea currentLeaderboardArea=new JTextArea();
        setAreaParameters(currentLeaderboardArea, neonFont.deriveFont(40f), Color.cyan, 310, 150, 534, 260, currentLeaderboardLabel);

        switch(chosenSorting){
            case "HIGHSCORES":
                currentLeaderboardTitle.setText("HIGHSCORES");
                game.getPlayerStats().sortStatsByPoints();
                for (int i = 0; i < game.getPlayerStats().getUsernames().size(); i++){
                    currentLeaderboardArea.setText(currentLeaderboardArea.getText() + " "+game.getPlayerStats().getUsernames().get(i) + "  "+game.getPlayerStats().getHighScores().get(i));
                    if (i != game.getPlayerStats().getHighScores().size()-1)
                        currentLeaderboardArea.setText(currentLeaderboardArea.getText() + "\n");
                }
                break;
            case "MULTIPLAYER WINS":
                currentLeaderboardTitle.setText("MULTIPLAYER WINS");
                currentLeaderboardTitle.setBounds(260, 90, 440, 50);
                game.getPlayerStats().sortStatsByMultiplayerWins();
                for (int i = 0; i < game.getPlayerStats().getUsernames().size(); i++){
                    currentLeaderboardArea.setText(currentLeaderboardArea.getText() + " " + game.getPlayerStats().getUsernames().get(i) + "  " + game.getPlayerStats().getMultiplayerWins().get(i));
                    if (i != game.getPlayerStats().getHighScores().size()-1)
                        currentLeaderboardArea.setText(currentLeaderboardArea.getText() + "\n");
                }
                break;
        }
        //Adds a scroll bar to the stats screen, if needed.
        JScrollPane scroll = new JScrollPane(currentLeaderboardArea);
        setScrollPaneParameters(scroll, 310, 150, 534, 260, currentLeaderboardLabel);
        //Sets the scroll bar at the top of the window.
        SwingUtilities.invokeLater(() -> scroll.getViewport().setViewPosition(new Point(0, 0)));

        //Adds a Back button to the current screen.
        backButton(currentLeaderboardLabel,mainLeaderboardLabel,null,neonFont.deriveFont(40f),Color.red,90,78,140,50,"LeaderBoard.png","LeaderBoard.png","");
    }

    /**
     * Displays the "ENTER NUMBER OF PLAYERS" screen.
     */

    public void enterNumberOfPlayers(){
        //Displays the "ENTER NUMBER OF PLAYERS" screen.
        JLabel choosePlayersLabel = new JLabel((new ImageIcon("SetControls.png")));
        changeScene(mainLabel,choosePlayersLabel);

        //Displays the "ENTER NUMBER OF PLAYERS" title text.
        JTextArea choosePlayersTitle = new JTextArea(" ENTER NUMBER" +"\n   OF PLAYERS");
        setAreaParameters(choosePlayersTitle,neonFont.deriveFont(40f),Color.ORANGE,300,150,350,80, choosePlayersLabel);

        //Displays the "MAX PLAYERS" text.
        JTextArea maxPlayersArea=new JTextArea(" (MAX PLAYERS: 2)");
        setAreaParameters(maxPlayersArea,neonFont.deriveFont(30f),Color.ORANGE,310,220,350,80, choosePlayersLabel);

        //Adds a Back button to the current screen.
        backButton(choosePlayersLabel,mainLabel,null,null,null,698,75,140,140,"SetControlsDark.png","SetControls.png","ALL");

        //Creates the TextField where the player(s) enter their chosen number of players.
        JTextField chooseNumberField= new JTextField();
        setFieldParameters(chooseNumberField,neonFont.deriveFont(50f),Color.cyan,400,300,110,80, choosePlayersLabel);
        chooseNumberField.requestFocusInWindow(); // Makes the cursor appear instantly at the textField.

        //Creates an Area which will display the message "Invalid Number" if a player enters an invalid number of players.
        JTextArea invalidNumber = new JTextArea("");
        setAreaParameters(invalidNumber,neonFont.deriveFont(30f),Color.RED,250,260,400,40,choosePlayersLabel);
        chooseNumberField.addActionListener(e -> {
            switch(chooseNumberField.getText()) {
                case "1":
                    chooseNumberField.setText("");
                    invalidNumber.setText("");
                    enterUsername(1, choosePlayersLabel,chooseNumberField);
                    break;
                case "2":
                    chooseNumberField.setText("");
                    invalidNumber.setText("");
                    enterUsername(2, choosePlayersLabel,chooseNumberField);
                    break;
                default:
                    chooseNumberField.setText("");
                    invalidNumber.setText("Invalid Number of Players");
            }
        });
    }

    /**
     * Displays the "Enter Username" screen.
     * @param numberOfPlayers Integer containing the total number of players.
     * @param currentLabel JLabel of the previous screen.
     * @param chooseNumberField TextField of the previous screen so that the back button, if pressed, can restore its focus.
     */

    public void enterUsername(int numberOfPlayers, JLabel currentLabel,JTextField chooseNumberField){
        final int[] currentPlayer = {1};
        //Displays the "Enter Username" screen.
        JLabel usernameLabel= new JLabel(new ImageIcon("EnterUsername.png"));
        changeScene(currentLabel,usernameLabel);

        //Creates the "Enter Username For.." title text.
        JTextArea enterUsernameTitle=new JTextArea("ENTER USERNAME" +"\n        FOR:"+"\n      PLAYER "+ currentPlayer[0]+"\n");
        setAreaParameters(enterUsernameTitle,neonFont.deriveFont(40f),Color.ORANGE,290,150,350,100, usernameLabel);

        //Creates the TextField where the player(s) enter their chosen usernames.
        JTextField enterUsernameText=new JTextField();
        setFieldParameters(enterUsernameText,neonFont.deriveFont(40f),Color.cyan,290,345,320,85, usernameLabel);
        enterUsernameText.requestFocusInWindow(); // Makes the cursor appear instantly at the textField.

        //Adds a Back button to the current screen.
        backButton(usernameLabel,currentLabel,chooseNumberField,null,null,698,75,140,140,"EnterUsernameDark.png","EnterUsername.png","ALL");

        //Creates the Area where "(Max characters: 14)" will be displayed.
        JTextArea maxCharactersArea=new JTextArea("(Max characters: 14)");
        setAreaParameters(maxCharactersArea,neonFont.deriveFont(30f),Color.ORANGE,290,250,350,45, usernameLabel);

        //Creates the Area where "Invalid username" will be displayed.
        JTextArea invalidUsername=new JTextArea();
        setAreaParameters(invalidUsername,neonFont.deriveFont(30f),Color.RED,250,300,450,45, usernameLabel);

        enterUsernameText.addActionListener(e -> {
            switch (game.enterUsernames(enterUsernameText.getText(), currentPlayer[0]-1)) {
                case -1:
                    enterUsernameText.setText("");
                    invalidUsername.setText(" USERNAME ALREADY CHOSEN");
                    break;
                case 0:
                    enterUsernameText.setText("");
                    invalidUsername.setText("       INVALID USERNAME");
                    break;
                case 1:
                    enterUsernameText.setText("");
                    invalidUsername.setText("");
                    currentPlayer[0]++;
                    enterUsernameTitle.setText("ENTER USERNAME" + "\n        FOR:" + "\n      PLAYER " + currentPlayer[0] + "\n");
                    if (currentPlayer[0] > numberOfPlayers) {
                        currentPlayer[0]=1;
                        enterUsernameTitle.setText("ENTER USERNAME" + "\n        FOR:" + "\n      PLAYER " + currentPlayer[0] + "\n");
                        setControls(usernameLabel,enterUsernameText);
                    }
                    break;
            }
        });
    }

    /**
     * Displays the "Set Controls" screen.
     * @param currentLabel JLabel of the previous screen.
     * @param enterUsernameText TextField of the previous screen so that the back button, if pressed, can restore its focus.
     */

    public void setControls(JLabel currentLabel,JTextField enterUsernameText){
        //Displays the "Set Controls" screen.
        JLabel setControlsLabel=new JLabel(new ImageIcon("SetControls.png"));
        changeScene(currentLabel,setControlsLabel);

        //Creates the "Set Controls For" title text.
        JTextArea setControlArea= new JTextArea(" SET CONTROLS\n        FOR ");
        setAreaParameters(setControlArea,neonFont.deriveFont(40f),Color.ORANGE,300,150,300,70, setControlsLabel);

        //Displays the username of the player who is currently setting their controls.
        JTextField usernameField= new JTextField(game.getPlayers().get(0).getUsername());
        setFieldParameters(usernameField,neonFont.deriveFont(40f),Color.CYAN,200,220,500,50,setControlsLabel);
        usernameField.setEditable(false);

        //Creates the TextField where the player(s) enter their chosen controls.
        JTextField setControlField= new JTextField();
        setFieldParameters(setControlField,neonFont.deriveFont(50f),Color.cyan,400,300,110,80, setControlsLabel);
        setControlField.requestFocusInWindow(); // Makes the cursor appear instantly at the textField.

        //Adds a Back button to the current screen.
        backButton(setControlsLabel,currentLabel,enterUsernameText,null,null,698,75,140,140,"SetControlsDark.png","SetControls.png","ALL");

        //Displays the control that is currently set by the player
        JTextArea currentControlArea=new JTextArea("ANSWER A:");
        final int[] currentControlNumber = {0};
        final int[] currentPlayer = {0};
        setAreaParameters(currentControlArea,neonFont.deriveFont(40f),Color.orange,170,320,220,80, setControlsLabel);

        /*Creates an Area which will display the message "Invalid Control" if a player enters an invalid control
          or "Control already bound" if the chosen control has already been chosen.*/
        JTextArea invalidControl = new JTextArea("");
        setAreaParameters(invalidControl,neonFont.deriveFont(30f),Color.RED,250,270,450,40, setControlsLabel);

        setControlField.addActionListener(e -> {
            switch(game.setCurrentControl(setControlField.getText().toUpperCase(), currentPlayer[0],currentControlNumber[0])) {
                case -1:
                    setControlField.setText("");
                    invalidControl.setText("  Control already bound");
                    break;
                case 0:
                    setControlField.setText("");
                    invalidControl.setText("        Invalid Control");
                    break;
                case 1:
                    invalidControl.setText("");
                    setControlField.setText("");
                    currentControlNumber[0]++;
                    switch (currentControlNumber[0]) {
                        case 1:
                            currentControlArea.setText("ANSWER B:");
                            break;
                        case 2:
                            currentControlArea.setText("ANSWER C:");
                            break;
                        case 3:
                            currentControlArea.setText("ANSWER D:");
                            break;
                        default:
                            currentControlNumber[0]=0;
                            currentPlayer[0]++;
                            if(currentPlayer[0]==game.getPlayers().size()) {
                                currentPlayer[0]=0;
                                youCanView(setControlsLabel,setControlField);
                            }
                            usernameField.setText(game.getPlayers().get(currentPlayer[0]).getUsername());
                            currentControlArea.setText("ANSWER A:");
                            break;
                    }
            }
        });
    }

    /**
     * Displays the "You can view each player's controls by clicking:.." screen.
     * @param currentLabel JLabel of the previous screen.
     * @param setControlField TextField of the previous screen so that the back button, if pressed, can restore its focus.
     */

    public void youCanView(JLabel currentLabel,JTextField setControlField){
        //Displays the "You can view each player's controls by clicking:.." screen.
        JLabel youCanViewLabel= new JLabel(new ImageIcon("YouCanView.png"));
        changeScene(currentLabel, youCanViewLabel);
        requestFocusIfClicked(youCanViewLabel,youCanViewLabel);

        //Displays the area where "You can view each player's controls by clicking:.." is displayed.
        JTextArea youCanViewArea=new JTextArea("         You can view\n each player's controls\n           by clicking:");
        setAreaParameters(youCanViewArea,neonFont.deriveFont(50f),Color.ORANGE,150,100,700,150, youCanViewLabel);
        requestFocusIfClicked(youCanViewLabel,youCanViewArea);

        //Displays the "PRESS ENTER TO CONTINUE" text.
        JTextArea pressEnterArea=new JTextArea(" Press enter to continue");
        setAreaParameters(pressEnterArea,neonFont.deriveFont(50f),Color.yellow,150,370,640,60,youCanViewLabel);
        requestFocusIfClicked(youCanViewLabel,pressEnterArea);
        youCanViewLabel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyTyped(e);
                if (e.getKeyCode()==KeyEvent.VK_ENTER)
                    chooseCategory(youCanViewLabel);
            }
        });

        //Adds a Back button to the current screen.
        backButton(youCanViewLabel,currentLabel,setControlField,null,null,0,0,100,100,"YouCanViewDarkBack.png","YouCanView.png","CONTROLS");

        //Adds an exit button to the current screen.
        exitButton(youCanViewLabel,null,null,855,0,100,100, "YouCanViewDarkX.png","YouCanView.png" );
    }

    /**
     * Displays the "Choose Category" screen.
     * @param currentLabel JLabel of the previous screen.
     */

    public void chooseCategory(JLabel currentLabel) {
        if (game.getPlayers().size()>1)
            game.getRound().addMultiplayerRounds();
        //Displays the "Choose Category" screen.
        JLabel chooseCategoryLabel = new JLabel(new ImageIcon("ChooseCategory.png"));
        changeScene(currentLabel, chooseCategoryLabel);
        requestFocusIfClicked(chooseCategoryLabel,chooseCategoryLabel);

        //Add a CONTROLS button.
        controlsButton(chooseCategoryLabel,null,null,0,0,100,100,"ChooseCategoryDarkC.png","ChooseCategory.png");

        //Adds an exit button to the current screen.
        exitButton(chooseCategoryLabel, null,null,855, 0, 100, 100, "ChooseCategoryDarkX.png", "ChooseCategory.png");
        //final String[][] randomCategories = {game.randomCategories()};
        final ArrayList<String>[] randomCategories = new ArrayList[]{game.randomCategories()};

        //Creates the field where "Player x choose a category" will be displayed.
        JTextField chooseCategoryField = new JTextField();
        setFieldParameters(chooseCategoryField, neonFont.deriveFont(35f), Color.ORANGE, 80, 247, 800, 50, chooseCategoryLabel);
        chooseCategoryField.setEditable(false);
        requestFocusIfClicked(chooseCategoryLabel,chooseCategoryField);

        Random rand = new Random();
        final int[] randPlayer = {rand.nextInt(game.getPlayers().size())};
        chooseCategoryField.setText(game.getPlayers().get(randPlayer[0]).getUsername() + " choose a category");

        //Creates TextFields for the 4 categories that will be displayed.
        JTextField category1=new JTextField();
        setFieldParameters(category1, neonFont.deriveFont(40f), Color.yellow, 15, 323, 420, 60, chooseCategoryLabel);
        category1.setEditable(false);
        requestFocusIfClicked(chooseCategoryLabel,category1);

        JTextField category2=new JTextField();
        setFieldParameters(category2, neonFont.deriveFont(40f), Color.cyan, 520, 323, 420, 60, chooseCategoryLabel);
        category2.setEditable(false);
        requestFocusIfClicked(chooseCategoryLabel,category2);

        JTextField category3=new JTextField();
        setFieldParameters(category3, neonFont.deriveFont(40f), Color.blue, 15, 433, 420, 60, chooseCategoryLabel);
        category3.setEditable(false);
        requestFocusIfClicked(chooseCategoryLabel,category3);

        JTextField category4=new JTextField();
        setFieldParameters(category4, neonFont.deriveFont(40f), Color.magenta, 520, 433, 420, 60, chooseCategoryLabel);
        category4.setEditable(false);
        requestFocusIfClicked(chooseCategoryLabel,category4);
        //Adds the names of the 4 randomly chosen categories to their respective boxes.
        displayRandomCategories(chooseCategoryLabel,category1,category2,category3,category4, randomCategories[0]);
        final boolean[] flag = {false};
        chooseCategoryLabel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if (Character.toUpperCase(e.getKeyChar()) == game.getPlayers().get(randPlayer[0]).getControl(0).charAt(0)){
                    flag[0] = true;
                    proceedToRound(chooseCategoryLabel, randomCategories[0].get(0));
                }
                else if (Character.toUpperCase(e.getKeyChar()) == game.getPlayers().get(randPlayer[0]).getControl(1).charAt(0) && !randomCategories[0].get(1).equals("")){
                    flag[0] = true;
                    proceedToRound(chooseCategoryLabel, randomCategories[0].get(1));
                }
                else if (Character.toUpperCase(e.getKeyChar()) == game.getPlayers().get(randPlayer[0]).getControl(2).charAt(0) && !randomCategories[0].get(2).equals("")){
                    flag[0] = true;
                    proceedToRound(chooseCategoryLabel, randomCategories[0].get(2));
                }
                else if (Character.toUpperCase(e.getKeyChar()) == game.getPlayers().get(randPlayer[0]).getControl(3).charAt(0) && !randomCategories[0].get(3).equals("")){
                    flag[0] = true;
                    proceedToRound(chooseCategoryLabel, randomCategories[0].get(3));
                }
                if (flag[0]) {
                    flag[0] = false;
                    randPlayer[0] = rand.nextInt(game.getPlayers().size());
                    chooseCategoryField.setText(game.getPlayers().get(randPlayer[0]).getUsername() + " choose a category");
                    randomCategories[0] = game.randomCategories();
                    displayRandomCategories(chooseCategoryLabel, category1, category2, category3, category4, randomCategories[0]);
                }
            }
        });
    }

    /**
     * Displays each round's description and proceeds to play that round.
     * @param currentLabel JLabel of the previous screen.
     * @param chosenCategory String containing the chosen category.
     */

    public void proceedToRound(JLabel currentLabel, String chosenCategory) {
        //Displays the "Current Round" screen.
        JLabel currentRoundLabel = new JLabel(new ImageIcon("CurrentRound.png"));
        changeScene(currentLabel, currentRoundLabel);

        //Displays the "Current Round" title.
        JTextField currentRoundField = new JTextField("");
        setFieldParameters(currentRoundField, neonFont.deriveFont(70f), Color.ORANGE, 140, 0, 700, 150, currentRoundLabel);
        currentRoundField.setEditable(false);

        //Displays the description of the current round type.
        JTextArea descriptionArea = new JTextArea();
        Font descriptionFont=new Font("Arial",Font.PLAIN, 40);
        setAreaParameters(descriptionArea,descriptionFont,Color.orange,170, 150,700,500,currentRoundLabel);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);

        //Adds an exit button to the current screen.
        exitButton(currentRoundLabel, null,null,855, 0, 100, 100, "CurrentRoundDarkX.png", "CurrentRound.png");

        switch (game.getRound().getRoundTypes().get(0)) {
            case "RIGHT ANSWER":
                currentRoundField.setText("RIGHT ANSWER");
                descriptionArea.setText(game.getRound().getRoundDescription("RIGHT ANSWER"));
                delayRoundType("RIGHT ANSWER", currentRoundLabel, chosenCategory, currentLabel);
                break;
            case "BETTING":
                currentRoundField.setText("BETTING");
                descriptionArea.setText(game.getRound().getRoundDescription("BETTING"));
                delayRoundType("BETTING", currentRoundLabel, chosenCategory, currentLabel);
                break;
            case "COUNTDOWN":
                currentRoundField.setText("COUNTDOWN");
                descriptionArea.setText(game.getRound().getRoundDescription("COUNTDOWN"));
                delayRoundType("COUNTDOWN", currentRoundLabel, chosenCategory, currentLabel);
                break;
            case "FASTEST FINGER":
                currentRoundField.setText("FASTEST FINGER");
                descriptionArea.setText(game.getRound().getRoundDescription("FASTEST FINGER"));
                delayRoundType("FASTEST FINGER", currentRoundLabel, chosenCategory, currentLabel);
                break;
            case "THERMOMETER":
                currentRoundField.setText("THERMOMETER");
                descriptionArea.setText(game.getRound().getRoundDescription("THERMOMETER"));
                delayRoundType("THERMOMETER", currentRoundLabel, chosenCategory, currentLabel);
                break;
        }
    }

    /**
     * Begins every round type.
     * @param currentRound String containing the name of the current round.
     * @param currentLabel JLabel of the previous screen.
     * @param chosenCategory String containing the chosen category.
     * @param chooseCategoryLabel JLabel containing the choose category screen.
     */

    public void startRound(String currentRound, JLabel currentLabel, String chosenCategory,JLabel chooseCategoryLabel){
        //Displays the "Questions" screen.
        JLabel questionsLabel=new JLabel(new ImageIcon("Questions.png"));
        changeScene(currentLabel,questionsLabel);
        requestFocusIfClicked(questionsLabel,questionsLabel);

        //Adds an exit button to the current screen.
        exitButton(questionsLabel,null,null,855,0,100,100, "QuestionsDark.png","Questions.png" );

        Font questionFont=new Font("Arial",Font.PLAIN, 20);
        //Displays the questions
        JTextField questionText=new JTextField();
        setFieldParameters(questionText, questionFont,Color.red,80,245,805,50, questionsLabel);
        questionText.setEditable(false);
        requestFocusIfClicked(questionsLabel,questionText);

        //Displays the possible answers.
        JTextField answer1=new JTextField();
        setFieldParameters(answer1,questionFont, Color.yellow, 15, 323, 420, 60, questionsLabel);
        answer1.setEditable(false);
        requestFocusIfClicked(questionsLabel,answer1);

        JTextField answer2=new JTextField();
        setFieldParameters(answer2,questionFont, Color.cyan, 520, 323, 420, 60, questionsLabel);
        answer2.setEditable(false);
        requestFocusIfClicked(questionsLabel,answer2);

        JTextField answer3=new JTextField();
        setFieldParameters(answer3,questionFont, Color.blue, 15, 433, 420, 60, questionsLabel);
        answer3.setEditable(false);
        requestFocusIfClicked(questionsLabel,answer3);

        JTextField answer4=new JTextField();
        setFieldParameters(answer4,questionFont, Color.magenta, 520, 433, 420, 60, questionsLabel);
        answer4.setEditable(false);
        requestFocusIfClicked(questionsLabel,answer4);

        //Displays the chosen category.
        JTextField chosenCategoryField=new JTextField(chosenCategory);
        setFieldParameters(chosenCategoryField,neonFont.deriveFont(40f),Color.ORANGE,130,-10,700,80, questionsLabel);
        chosenCategoryField.setEditable(false);
        requestFocusIfClicked(questionsLabel,chosenCategoryField);

        //Displays the "Players Answered title"
        JTextField playersAnsweredTitle=new JTextField(" Players Answered");
        setFieldParameters(playersAnsweredTitle,neonFont.deriveFont(27f),Color.yellow,0,-10,280,80,questionsLabel);
        playersAnsweredTitle.setEditable(false);
        requestFocusIfClicked(questionsLabel,playersAnsweredTitle);

        //Displays the players who have already answered, except from the last one;
        JTextArea playersAnsweredArea =new JTextArea();
        setAreaParameters(playersAnsweredArea,neonFont.deriveFont(25f),Color.cyan,10,50,400,200,questionsLabel);
        requestFocusIfClicked(questionsLabel,playersAnsweredArea);
        if (game.getPlayers().size()==1) {
            playersAnsweredTitle.setVisible(false);
            playersAnsweredArea.setVisible(false);
        }
        //Creates the area where an image of a question,if it exists, will be displayed.
        JButton imageQuestion=new JButton();
        setButtonParameters(imageQuestion,null,null,280,45,400,185,questionsLabel);
        requestFocusIfClicked(questionsLabel,imageQuestion);
        final int[] currentQuestion = {1};
        final int[] playersAnswered = {0};
        switch (currentRound) {
            case "BETTING":
                Question[] randomQuestion = {game.getRandomQuestion(chosenCategory)};
                displayBettingOptions(questionText,answer1,answer2,answer3,answer4);
                questionsLabel.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        super.keyTyped(e);
                        switch (currentQuestion[0]%2){
                            case 0:
                                for (Player p:game.getPlayers()){
                                    for (int i=0;i<4;i++){
                                        if (Character.toUpperCase(e.getKeyChar())==p.getControl(i).charAt(0) && !playersAnsweredArea.getText().contains(p.getUsername()))
                                            playersAnsweredArea.append(p.getUsername()+"\n");
                                    }
                                }
                                playersAnswered[0] += game.correctAnswer(e.getKeyChar(), randomQuestion[0], currentRound,0);
                                if (playersAnswered[0]==game.getPlayers().size()) {
                                    imageQuestion.setIcon(null);
                                    playersAnsweredArea.setText("");
                                    game.resetHaveAnswered();
                                    playersAnswered[0] = 0;
                                    currentQuestion[0]++;
                                    if (currentQuestion[0] == 11) {
                                        game.getRound().getRoundTypes().remove(currentRound);
                                        if (game.getAvailableQuestions().size()!=0 && game.getRound().getRoundTypes().size()!=0) {
                                            changeScene(questionsLabel, chooseCategoryLabel);
                                            resultScreen(chooseCategoryLabel, randomQuestion[0].getCorrectAnswer(), false, false);
                                        }
                                        else
                                            resultScreen(questionsLabel, randomQuestion[0].getCorrectAnswer(), true,false);
                                    } else {
                                        if (game.getAvailableQuestions().size()!=0) {
                                            resultScreen(questionsLabel, randomQuestion[0].getCorrectAnswer(), false,false);
                                            randomQuestion[0] = game.getRandomQuestion(chosenCategory);
                                        }
                                        else
                                            resultScreen(questionsLabel, randomQuestion[0].getCorrectAnswer(), true,false);
                                    }
                                    displayBettingOptions(questionText, answer1, answer2, answer3, answer4);
                                }
                                break;
                            case 1:
                                for (Player p:game.getPlayers()){
                                    for (int i=0;i<4;i++){
                                        if (Character.toUpperCase(e.getKeyChar())==p.getControl(i).charAt(0) && !playersAnsweredArea.getText().contains(p.getUsername()))
                                            playersAnsweredArea.setText(p.getUsername()+"\n");
                                    }
                                }
                                playersAnswered[0] += game.hasBet(e.getKeyChar());
                                if (playersAnswered[0]==game.getPlayers().size()) {
                                    playersAnsweredArea.setText("");
                                    game.resetHaveAnswered();
                                    playersAnswered[0] = 0;
                                    currentQuestion[0]++;
                                    displayQuestionAndAnswers(questionText, answer1, answer2, answer3, answer4, randomQuestion[0]);
                                    //If a questions is accompanied by an image, it is displayed.
                                    imageQuestion.setIcon(new ImageIcon("Buzz Questions Directory\\"+randomQuestion[0].getMedia()));
                                }
                        }
                    }
                });
                break;
            case "COUNTDOWN":
                randomQuestion = new Question[]{game.getRandomQuestion(chosenCategory)};
                //Displays the timer.
                JTextField timerField= new JTextField();
                setFieldParameters(timerField,questionFont.deriveFont(60f),Color.red,860,80,100,100, questionsLabel);
                timerField.setEditable(false);
                requestFocusIfClicked(questionsLabel,timerField);

                displayQuestionAndAnswers(questionText,answer1,answer2,answer3,answer4, randomQuestion[0]);
                //If a questions is accompanied by an image, it is displayed.
                imageQuestion.setIcon(new ImageIcon("Buzz Questions Directory\\"+randomQuestion[0].getMedia()));
                final int[] milliseconds = {5000};
                Timer timer = new Timer(100, t -> {
                    milliseconds[0] -= 100;
                    timerField.setText(String.valueOf(milliseconds[0] / 1000.0));
                    if (milliseconds[0]==0){
                        game.resetHaveAnswered();
                        playersAnsweredArea.setText("");
                        playersAnswered[0] = 0;
                        currentQuestion[0]++;
                        if (currentQuestion[0] != 6) {
                            if (game.getAvailableQuestions().size() != 0) {
                                resultScreen(questionsLabel, randomQuestion[0].getCorrectAnswer(), false, false);
                                randomQuestion[0] = game.getRandomQuestion(chosenCategory);
                                displayQuestionAndAnswers(questionText, answer1, answer2, answer3, answer4, randomQuestion[0]);
                                //If a questions is accompanied by an image, it is displayed.
                                imageQuestion.setIcon(new ImageIcon("Buzz Questions Directory\\" + randomQuestion[0].getMedia()));
                                milliseconds[0] = 8500;
                                ((Timer)t.getSource()).restart();
                            } else {
                                game.getRound().getRoundTypes().remove(currentRound);
                                resultScreen(questionsLabel, randomQuestion[0].getCorrectAnswer(), true, false);
                                ((Timer)t.getSource()).stop();
                            }
                        } else {
                            ((Timer)t.getSource()).stop();
                            game.getRound().getRoundTypes().remove(currentRound);
                            if (game.getAvailableQuestions().size() != 0 && game.getRound().getRoundTypes().size() != 0) {
                                changeScene(questionsLabel, chooseCategoryLabel);
                                resultScreen(chooseCategoryLabel, randomQuestion[0].getCorrectAnswer(), false, false);
                            } else
                                resultScreen(questionsLabel, randomQuestion[0].getCorrectAnswer(), true, false);
                        }
                    }
                });
                timer.start();
                questionsLabel.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        super.keyTyped(e);
                        for (Player p : game.getPlayers()) {
                            for (int i = 0; i < 4; i++) {
                                if (Character.toUpperCase(e.getKeyChar()) == p.getControl(i).charAt(0) && !playersAnsweredArea.getText().contains(p.getUsername()))
                                    playersAnsweredArea.append(p.getUsername() + "\n");
                            }
                        }
                        playersAnswered[0] += game.correctAnswer(e.getKeyChar(), randomQuestion[0], currentRound, milliseconds[0]);
                        if (playersAnswered[0] == game.getPlayers().size()) {
                            game.resetHaveAnswered();
                            playersAnsweredArea.setText("");
                            playersAnswered[0] = 0;
                            currentQuestion[0]++;
                            if (currentQuestion[0] != 6) {
                                if (game.getAvailableQuestions().size() != 0) {
                                    resultScreen(questionsLabel, randomQuestion[0].getCorrectAnswer(), false, false);
                                    randomQuestion[0] = game.getRandomQuestion(chosenCategory);
                                    displayQuestionAndAnswers(questionText, answer1, answer2, answer3, answer4, randomQuestion[0]);
                                    //If a questions is accompanied by an image, it is displayed.
                                    imageQuestion.setIcon(new ImageIcon("Buzz Questions Directory\\" + randomQuestion[0].getMedia()));
                                    milliseconds[0] = 8500;
                                    timer.restart();
                                } else {
                                    game.getRound().getRoundTypes().remove(currentRound);
                                    resultScreen(questionsLabel, randomQuestion[0].getCorrectAnswer(), true, false);
                                    timer.stop();
                                }
                            } else {
                                game.getRound().getRoundTypes().remove(currentRound);
                                timer.stop();
                                if (game.getAvailableQuestions().size() != 0 && game.getRound().getRoundTypes().size() != 0) {
                                    changeScene(questionsLabel, chooseCategoryLabel);
                                    resultScreen(chooseCategoryLabel, randomQuestion[0].getCorrectAnswer(), false, false);
                                } else
                                    resultScreen(questionsLabel, randomQuestion[0].getCorrectAnswer(), true, false);

                            }
                        }
                    }
                });
                break;
            case "THERMOMETER":
                game.getRound().getRoundTypes().remove(currentRound);
                randomQuestion = new Question[]{game.getRandomQuestion(null)};
                chosenCategoryField.setText(randomQuestion[0].getCategory());
                displayQuestionAndAnswers(questionText,answer1,answer2,answer3,answer4, randomQuestion[0]);
                //If a questions is accompanied by an image, it is displayed.
                imageQuestion.setIcon(new ImageIcon("Buzz Questions Directory\\"+randomQuestion[0].getMedia()));
                questionsLabel.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        super.keyTyped(e);
                        for (Player p : game.getPlayers()) {
                            for (int i = 0; i < 4; i++) {
                                if (Character.toUpperCase(e.getKeyChar()) == p.getControl(i).charAt(0) && !playersAnsweredArea.getText().contains(p.getUsername()))
                                    playersAnsweredArea.append(p.getUsername() + "\n");
                            }
                        }
                        playersAnswered[0] += game.correctAnswer(e.getKeyChar(), randomQuestion[0], currentRound, 0);
                        for (Player p : game.getPlayers())
                            if (p.getThermometerCorrectAnswers() == 5)
                                resultScreen(questionsLabel, randomQuestion[0].getCorrectAnswer(), true, true);
                        if (playersAnswered[0] == game.getPlayers().size()) {
                            if (game.getAvailableQuestions().size() != 0) {
                                playersAnsweredArea.setText("");
                                game.resetHaveAnswered();
                                playersAnswered[0] = 0;
                                resultScreen(questionsLabel, randomQuestion[0].getCorrectAnswer(), game.getAvailableQuestions().size() == 0, true);
                                randomQuestion[0] = game.getRandomQuestion(null);
                                chosenCategoryField.setText(randomQuestion[0].getCategory());
                                displayQuestionAndAnswers(questionText, answer1, answer2, answer3, answer4, randomQuestion[0]);
                                //If a questions is accompanied by an image, it is displayed.
                                imageQuestion.setIcon(new ImageIcon("Buzz Questions Directory\\" + randomQuestion[0].getMedia()));
                            } else {
                                int playerWon = 0;
                                for (int i = 0; i < game.getPlayers().size() - 1; i++) {
                                    for (int j = i + 1; j < game.getPlayers().size(); j++) {
                                        if (game.getPlayers().get(i).getThermometerCorrectAnswers() > game.getPlayers().get(j).getThermometerCorrectAnswers())
                                            playerWon = i;
                                        else
                                            playerWon = j;
                                    }
                                }
                                for (int i=0;i<game.getPlayers().size()-1;i++){
                                    if (game.getPlayers().get(i).getThermometerCorrectAnswers()>game.getPlayers().get(i+1).getThermometerCorrectAnswers()) {
                                        game.getPlayers().get(playerWon).setPoints(game.getPlayers().get(playerWon).getPoints() + 5000);
                                        break;
                                    }
                                }
                                resultScreen(questionsLabel, randomQuestion[0].getCorrectAnswer(), true, true);
                            }
                        }
                    }
                });
                break;
            default://"RIGHT ANSWER" OR "FASTEST FINGER".
                randomQuestion = new Question[]{game.getRandomQuestion(chosenCategory)};
                displayQuestionAndAnswers(questionText,answer1,answer2,answer3,answer4, randomQuestion[0]);
                //If a questions is accompanied by an image, it is displayed.
                imageQuestion.setIcon(new ImageIcon("Buzz Questions Directory\\"+randomQuestion[0].getMedia()));
                questionsLabel.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        super.keyTyped(e);
                        for (Player p:game.getPlayers()){
                            for (int i=0;i<4;i++){
                                if (Character.toUpperCase(e.getKeyChar())==p.getControl(i).charAt(0) && !playersAnsweredArea.getText().contains(p.getUsername()))
                                    playersAnsweredArea.append(p.getUsername()+"\n");
                            }
                        }
                        playersAnswered[0] += game.correctAnswer(e.getKeyChar(), randomQuestion[0], currentRound,0);
                        if (playersAnswered[0]==game.getPlayers().size()) {
                            playersAnsweredArea.setText("");
                            game.resetHaveAnswered();
                            playersAnswered[0] = 0;
                            currentQuestion[0]++;
                            if (currentQuestion[0] != 6) {
                                resultScreen(questionsLabel, randomQuestion[0].getCorrectAnswer(), game.getAvailableQuestions().size() == 0,false); //Simplified if statement.
                                randomQuestion[0] = game.getRandomQuestion(chosenCategory);
                                displayQuestionAndAnswers(questionText, answer1, answer2, answer3, answer4, randomQuestion[0]);
                                //If a questions is accompanied by an image, it is displayed.
                                imageQuestion.setIcon(new ImageIcon("Buzz Questions Directory\\"+randomQuestion[0].getMedia()));
                            } else {
                                game.getRound().getRoundTypes().remove(currentRound);
                                if (game.getAvailableQuestions().size()!=0 && game.getRound().getRoundTypes().size()!=0) {
                                    changeScene(questionsLabel, chooseCategoryLabel);
                                    resultScreen(chooseCategoryLabel, randomQuestion[0].getCorrectAnswer(), false, false);
                                }
                                else if(game.getAvailableQuestions().size()==0 || game.getRound().getRoundTypes().size()==0)
                                    resultScreen(questionsLabel, randomQuestion[0].getCorrectAnswer(), true,false);
                            }
                        }
                    }
                });
        }
    }

    /**
     * Displays each player's controls.
     * @param currentLabel JLabel of the previous screen.
     */

    public void viewControls(JLabel currentLabel){
        //Displays the "View Controls" screen.
        JLabel viewControlsLabel=new JLabel(new ImageIcon("LeaderBoard.png"));
        changeScene(currentLabel, viewControlsLabel);

        //Displays the "CONTROLS" title.
        JTextArea viewControlsTitle = new JTextArea("CONTROLS");
        setAreaParameters(viewControlsTitle,neonFont.deriveFont(50f),Color.ORANGE,350,90,360,50, viewControlsLabel);

        //Adds the Area where the controls will be displayed.
        JTextArea controlsArea = new JTextArea("    ");
        setAreaParameters(controlsArea,neonFont.deriveFont(50f),Color.cyan,110,150,720,275, viewControlsLabel);

        for (int i=1; i<game.getPlayers().size()+1; i++){
            if (i<game.getPlayers().size())
                controlsArea.append("Player "+i+"    ");
            else
                controlsArea.append("Player "+i);
        }
        controlsArea.append("\nA:      ");
        int count=1;
        for (Player p:game.getPlayers()){
            if (count==game.getPlayers().size())
                controlsArea.append(p.getControl(0)+"    ");
            else
                controlsArea.append(p.getControl(0)+"              ");
            count++;
        }
        count=1;
        controlsArea.append("\nB:      ");
        for (Player p:game.getPlayers()){
            if (count==game.getPlayers().size())
                controlsArea.append(p.getControl(1)+"    ");
            else
                controlsArea.append(p.getControl(1)+"              ");
            count++;
        }
        count=1;
        controlsArea.append("\nC:      ");
        for (Player p:game.getPlayers()){
            if (count==game.getPlayers().size())
                controlsArea.append(p.getControl(2)+"    ");
            else
                controlsArea.append(p.getControl(2)+"              ");
            count++;
        }
        count=1;
        controlsArea.append("\nD:      ");
        for (Player p:game.getPlayers()){
            if (count==game.getPlayers().size())
                controlsArea.append(p.getControl(3)+"    ");
            else
                controlsArea.append(p.getControl(3)+"              ");
            count++;
        }
        //Adds Scrolling function in the leaderboard display area.
        JScrollPane scroll=new JScrollPane(controlsArea);
        setScrollPaneParameters(scroll,110,150,720,280, viewControlsLabel);

        //Adds a Back button to the current screen.
        backButton(viewControlsLabel,currentLabel,null,neonFont.deriveFont(40f),Color.red,60,78,200,50,"LeaderBoard.png","LeaderBoard.png","");
    }

    /**
     * Displays the result of the current question.
     * If the player(s) is/are not playing THERMOMETER their username(s) and points are displayed. Otherwise their username(s), points and THERMOMETER wins are displayed.
     * @param currentLabel JLabel of the previous screen.
     * @param correctAnswer String containing a questions correct answer.
     * @param hasEnded Boolean containing whether the game has ended or not.
     * @param thermometer Boolean containing whether players are playing THERMOMETER or not.
     */

    public void resultScreen(JLabel currentLabel, String correctAnswer,boolean hasEnded, boolean thermometer){
        //Displays the current question's result screen.
        JLabel resultScreenLabel=new JLabel(new ImageIcon("CurrentRound.png"));
        changeScene(currentLabel,resultScreenLabel);

        //Displays the "The Correct Answer is:" title.
        JTextArea resultArea = new JTextArea("  The Correct Answer is\n");
        setAreaParameters(resultArea,neonFont.deriveFont(50f),Color.ORANGE,150,80,700,150,resultScreenLabel);

        //Displays the correct answer.
        JTextField correctAnswerField=new JTextField(" "+correctAnswer);
        setFieldParameters(correctAnswerField,neonFont.deriveFont(45f),Color.GREEN,130,120,700,150,resultScreenLabel);
        correctAnswerField.setEditable(false);

        //Adds an exit button to the current screen.
        exitButton(resultScreenLabel,null,null,855,0,100,100, "CurrentRoundDarkX.png","CurrentRound.png" );

        //Displays Player Username and Points.
        switch(game.getPlayers().size()){
            case 1:
                JTextField playerUsername=new JTextField(" " +game.getPlayers().get(0).getUsername());
                setFieldParameters(playerUsername,neonFont.deriveFont(50f),Color.CYAN,155,250,650,60,resultScreenLabel);
                playerUsername.setEditable(false);

                JTextField playerPoints=new JTextField(" "+game.getPlayers().get(0).getPoints());
                setFieldParameters(playerPoints,neonFont.deriveFont(50f),Color.CYAN,155,300,650,60,resultScreenLabel);
                playerPoints.setEditable(false);
                break;
            case 2:
                if(!thermometer)
                    for (int i=0; i<game.getPlayers().size();i++){
                        playerUsername=new JTextField(" " +game.getPlayers().get(i).getUsername());
                        setFieldParameters(playerUsername,neonFont.deriveFont(35f),Color.CYAN,460*i,250,500,60,resultScreenLabel);
                        playerUsername.setEditable(false);

                        playerPoints=new JTextField(" "+game.getPlayers().get(i).getPoints());
                        setFieldParameters(playerPoints,neonFont.deriveFont(35f),Color.CYAN,460*i,300,500,60,resultScreenLabel);
                        playerPoints.setEditable(false);
                    }
                else
                    for (int i=0; i<game.getPlayers().size();i++) {
                        playerUsername = new JTextField(" " + game.getPlayers().get(i).getUsername());
                        setFieldParameters(playerUsername, neonFont.deriveFont(35f), Color.CYAN, 460 * i, 250, 500, 60, resultScreenLabel);
                        playerUsername.setEditable(false);

                        playerPoints = new JTextField(" " + game.getPlayers().get(i).getThermometerCorrectAnswers());
                        setFieldParameters(playerPoints, neonFont.deriveFont(35f), Color.CYAN, 460 * i, 300, 500, 60, resultScreenLabel);
                        playerPoints.setEditable(false);
                    }
                break;
        }
        delayResults(resultScreenLabel,currentLabel,hasEnded);
    }

    /**
     * Four second delay between the screen that displays the question results and the next screen.
     * @param currentLabel JLabel of the previous screen.
     * @param newLabel JLabel of the screen that will displayed after the delay.
     * @param hasEnded Boolean containing whether the game has ended or not.
     */

    public void delayResults(JLabel currentLabel, JLabel newLabel, boolean hasEnded){
        Timer timer = new Timer(4000, e -> {
            if (!hasEnded)
                if (game.getRound().getRoundTypes().size()>0){
                    if (!game.getRound().getRoundTypes().get(0).equals("THERMOMETER"))
                        changeScene(currentLabel, newLabel);
                    else
                        proceedToRound(currentLabel, null);
                }
                else
                    changeScene(currentLabel, newLabel);
            else {
                endgameScreen(currentLabel);
            }
        });
        timer.setRepeats(false) ;
        timer.start();
    }

    /**
     * Five second delay between the screen that displays the description of the current round and the screen that displays the question and answers.
     * @param currentRound String containing the name of the current round.
     * @param currentLabel JLabel of the previous screen.
     * @param chosenCategory String containing the chosen category.
     * @param chooseCategoryLabel JLabel containing the choose category screen.
     */

    public void delayRoundType(String currentRound, JLabel currentLabel, String chosenCategory, JLabel chooseCategoryLabel){
        int delay = 5000;
        Timer timer = new Timer( delay, e -> startRound(currentRound, currentLabel, chosenCategory, chooseCategoryLabel));
        timer.setRepeats( false );
        timer.start();
    }

    /**
     * Displays the Endgame screen.
     * @param currentLabel JLabel of the previous screen.
     */

    public void endgameScreen(JLabel currentLabel){
        //Displays the endgame Screen.
        JLabel endScreenLabel=new JLabel (new ImageIcon("Endgame.png"));
        changeScene(currentLabel,endScreenLabel);

        //Displays "Game Finished" title.
        JTextArea gameFinishedArea =new JTextArea("Game Finished");
        setAreaParameters(gameFinishedArea,neonFont.deriveFont(60f),Color.orange,270,150,700,60,endScreenLabel);

        //Displays players points in ranking order.
        switch(game.getPlayers().size()){
            case 1:
                JTextField playerUsername=new JTextField(" " +game.getPlayers().get(0).getUsername());
                setFieldParameters(playerUsername,neonFont.deriveFont(50f),Color.CYAN,155,200,650,60,endScreenLabel);
                playerUsername.setEditable(false);

                JTextField playerPoints=new JTextField(" "+game.getPlayers().get(0).getPoints());
                setFieldParameters(playerPoints,neonFont.deriveFont(50f),Color.CYAN,155,250,650,60,endScreenLabel);
                playerPoints.setEditable(false);
                break;
            case 2:
                game.sortPlayersByPoints();
                if(game.getPlayers().get(0).getPoints()>game.getPlayers().get(1).getPoints())
                    game.getPlayers().get(0).setMultiplayerWins(game.getPlayers().get(0).getMultiplayerWins() + 1);
                for (int i=0; i<game.getPlayers().size();i++){
                    playerUsername=new JTextField(" " +game.getPlayers().get(i).getUsername());
                    setFieldParameters(playerUsername,neonFont.deriveFont(50f-10*i),Color.CYAN,155,200+100*i,650,50-10*i,endScreenLabel);
                    playerUsername.setEditable(false);

                    playerPoints=new JTextField(" "+game.getPlayers().get(i).getPoints());
                    setFieldParameters(playerPoints,neonFont.deriveFont(50f-10*i),Color.CYAN,155,200+(50-10*i)+100*i,650,50-10*i,endScreenLabel);
                    playerPoints.setEditable(false);
                }
                break;
        }
        try {
            game.addStats();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Creates a "EXIT" button.
        exitButton(endScreenLabel,neonFont.deriveFont(50f),Color.green,650,100,250,100,"Endgame.png","Endgame.png");
    }

    /**
     * Displays a question and its answers.
     * @param question JTextField displaying a question.
     * @param answer1 JTextField containing the field where the first answer is displayed.
     * @param answer2 JTextField containing the field where the second answer is displayed.
     * @param answer3 JTextField containing the field where the third answer is displayed.
     * @param answer4 JTextField containing the field where the fourth answer is displayed.
     * @param currentQuestion Questions Object that contains a random question.
     */

    public void displayQuestionAndAnswers(JTextField question, JTextField answer1, JTextField answer2, JTextField answer3, JTextField answer4, Question currentQuestion){
        question.setText(currentQuestion.getQuestion());
        answer1.setText("A: "+currentQuestion.getAnswers().get(0));
        answer2.setText("B: "+currentQuestion.getAnswers().get(1));
        answer3.setText("C: "+currentQuestion.getAnswers().get(2));
        answer4.setText("D: "+currentQuestion.getAnswers().get(3));
    }

    /**
     * Displays the betting options.
     * @param question JTextField displaying the BET POINTS FOR THE NEXT QUESTION text.
     * @param answer1 JTextField containing the field where the first betting option is displayed.
     * @param answer2 JTextField containing the field where the second betting option is displayed
     * @param answer3 JTextField containing the field where the third betting option is displayed.
     * @param answer4 JTextField containing the field where the fourth betting option is displayed.
     */

    public void displayBettingOptions(JTextField question, JTextField answer1, JTextField answer2, JTextField answer3, JTextField answer4){
        question.setText("BET POINTS FOR THE NEXT QUESTION");
        answer1.setText("A: 250");
        answer2.setText("B: 500");
        answer3.setText("C: 750");
        answer4.setText("D: 1000");
    }

    /**
     * Displays the random categories.
     * @param chooseCategoryLabel JLabel containing the choose category screen.
     * @param category1 JTextField containing the field where the first category is displayed.
     * @param category2 JTextField containing the field where the second category is displayed.
     * @param category3 JTextField containing the field where the third category is displayed.
     * @param category4 JTextField containing the field where the fourth category is displayed.
     * @param randomCategories ArrayList of Strings containing up to 4 categories.
     */

    public void displayRandomCategories(JLabel chooseCategoryLabel, JTextField category1, JTextField category2, JTextField category3, JTextField category4, ArrayList<String> randomCategories){
        if (randomCategories.get(0).equals("")) {
            endgameScreen(chooseCategoryLabel);
        }
        else {
            category1.setText("A: " + randomCategories.get(0));
            category2.setText("B: " + randomCategories.get(1));
            category3.setText("C: " + randomCategories.get(2));
            category4.setText("D: " + randomCategories.get(3));
        }
    }

    /**
     * Creates a controls button at the current label.
     * @param currentLabel JLabel containing the current button's label.
     * @param font Font containing the current button's font.
     * @param color Color containing the current button's color.
     * @param x Integer containing the current button's x-axis coordinates.
     * @param y Integer containing the current button's y-axis coordinates.
     * @param width Integer containing the current scroll's width.
     * @param height Integer containing the current button's height.
     * @param buttonDark String containing the label's icon when the mouse is over the button.
     * @param buttonNotDark String containing the label's icon when the mouse is not over the button.
     */

    public void controlsButton(JLabel currentLabel,Font font,Color color,int x,int y,int width, int height,String buttonDark, String buttonNotDark) {
        JButton controlsButton = new JButton();
        setButtonParameters(controlsButton, font, color, x, y, width, height, currentLabel);
        controlsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (SwingUtilities.isLeftMouseButton(e)) {
                    currentLabel.setIcon(new ImageIcon(buttonNotDark));
                    viewControls(currentLabel);
                }
            }
            //Adds hovering effect to the Controls button.
            public void mouseEntered(MouseEvent e) { currentLabel.setIcon(new ImageIcon(buttonDark)); }
            public void mouseExited(MouseEvent e) { currentLabel.setIcon(new ImageIcon(buttonNotDark)); }
        });
    }

    /**
     * Creates an exit button at the current label.
     * @param currentLabel JLabel containing the current button's label.
     * @param font Font containing the current button's font.
     * @param color Color containing the current button's color.
     * @param x Integer containing the current button's x-axis coordinates.
     * @param y Integer containing the current button's y-axis coordinates.
     * @param width Integer containing the current scroll's width.
     * @param height Integer containing the current button's height.
     * @param buttonDark String containing the label's icon when the mouse is over the button.
     * @param buttonNotDark String containing the label's icon when the mouse is not over the button.
     */

    public void exitButton(JLabel currentLabel,Font font, Color color,int x, int y, int width, int height, String buttonDark, String buttonNotDark ){
        JButton exitButton = new JButton();
        if (font!=null)
            exitButton.setText("EXIT");
        setButtonParameters(exitButton,font,color,x,y,width,height,currentLabel);
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (SwingUtilities.isLeftMouseButton(e))
                    System.exit(0);
            }
            //Adds hovering effect to the Exit button.
            public void mouseEntered(MouseEvent e){
                currentLabel.setIcon(new ImageIcon(buttonDark));
            }
            public void mouseExited(MouseEvent e){
                currentLabel.setIcon(new ImageIcon(buttonNotDark));
            }
        });
    }

    /**
     * Creates a back button at the current label.
     * @param currentLabel JLabel containing the current button's label.
     * @param previousLabel JLabel containing the previous label.
     * @param previousField JTextField of the previous screen so that the back button, if pressed, can restore its focus.
     * @param font Font containing the current button's font.
     * @param color Color containing the current button's color.
     * @param x Integer containing the current button's x-axis coordinates.
     * @param y Integer containing the current button's y-axis coordinates.
     * @param width Integer containing the current scroll's width.
     * @param height Integer containing the current button's height.
     * @param buttonDark String containing the label's icon when the mouse is over the button.
     * @param buttonNotDark String containing the label's icon when the mouse is not over the button.
     * @param toClear String containing whether going back will clear only the player controls or all the players entirely.
     */

    public void backButton(JLabel currentLabel, JLabel previousLabel, JTextField previousField,Font font, Color color, int x, int y, int width, int height, String buttonDark, String buttonNotDark, String toClear){
        JButton backButton = new JButton();
        if (font!=null)
            backButton.setText("BACK");
        setButtonParameters(backButton,font,color,x,y,width,height,currentLabel);
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (SwingUtilities.isLeftMouseButton(e))
                    switch (toClear){
                        case "ALL":
                            game.getPlayers().clear();
                            break;
                        case "CONTROLS":
                            for (Player p:game.getPlayers())
                                p.clearControls();
                            break;
                    }
                {
                    changeScene(currentLabel, previousLabel);
                    if (previousField!=null)
                        previousField.requestFocusInWindow();
                }
            }
            //Adds hovering effect to the Back button.
            public void mouseEntered (MouseEvent e){ currentLabel.setIcon(new ImageIcon(buttonDark)); }
            public void mouseExited (MouseEvent e){ currentLabel.setIcon(new ImageIcon(buttonNotDark)); }
        });
    }

    /**
     * Restores focus to the current label if one of its components is clicked on.
     * @param currentLabel Label containing the current button's label.
     * @param clickedComponent JComponent containing the clicked component.
     */

    public void requestFocusIfClicked(JLabel currentLabel, JComponent clickedComponent){
        clickedComponent.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                currentLabel.requestFocus();
            }
        });
    }

    /**
     * Sets essential parameters for a JScrollPane.
     * @param scroll JScrollPane containing the current scroll.
     * @param x Integer containing the current scroll's x-axis coordinates.
     * @param y Integer containing the current scroll's y-axis coordinates.
     * @param width Integer containing the current scroll's width.
     * @param height Integer containing the current scroll's height.
     * @param currentLabel Label containing the current scroll's label.
     */

    public void setScrollPaneParameters(JScrollPane scroll,int x, int y, int width,int height,JLabel currentLabel){
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(x, y, width, height);
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(null);
        scroll.setOpaque(false);
        currentLabel.add(scroll);
    }

    /**
     * Sets essential parameters for a JButton.
     * @param currentButton JButton containing the current button.
     * @param font Font containing the current button's font.
     * @param color Color containing the current button's color.
     * @param x Integer containing the current button's x-axis coordinates.
     * @param y Integer containing the current button's y-axis coordinates.
     * @param width Integer containing the current button's width.
     * @param height Integer containing the current button's height.
     * @param currentLabel Label containing the current button's label.
     */

    public void setButtonParameters(JButton currentButton,Font font, Color color,int x,int y,int width,int height, JLabel currentLabel){
        setMainComponentParameters(currentButton,font,color,x,y,width,height,currentLabel);
        currentButton.setContentAreaFilled(false);
        currentButton.setBorderPainted(false);
        currentButton.setHorizontalAlignment(SwingConstants.CENTER);
    }

    /**
     * Sets essential parameters for a JTextField.
     * @param currentField JTextField containing the current field.
     * @param font Font containing the current field's font.
     * @param color Color containing the current field's color.
     * @param x Integer containing the current field's x-axis coordinates.
     * @param y Integer containing the current field's y-axis coordinates.
     * @param width Integer containing the current field's width.
     * @param height Integer containing the current field's height.
     * @param currentLabel Label containing the current field's label.
     */

    public void setFieldParameters(JTextField currentField, Font font, Color color,int x,int y,int width,int height, JLabel currentLabel){
        setMainComponentParameters(currentField,font,color,x,y,width,height,currentLabel);
        currentField.setBorder(null);
        currentField.setHorizontalAlignment(JTextField.CENTER);
    }

    /**
     * Sets essential parameters for a JTextArea.
     * @param currentArea TextArea containing the current area.
     * @param font Font containing the current area's font.
     * @param color Color containing the current area's color.
     * @param x Integer containing the current area's x-axis coordinates.
     * @param y Integer containing the current area's y-axis coordinates.
     * @param width Integer containing the current area's width.
     * @param height Integer containing the current area's height.
     * @param currentLabel Label containing the current area's label.
     */

    public void setAreaParameters(JTextArea currentArea, Font font,Color color, int x, int y, int width, int height, JLabel currentLabel){
        setMainComponentParameters(currentArea,font,color,x,y,width,height,currentLabel);
        currentArea.setEditable(false);
    }

    /**
     * Sets essential parameters for a JComponent.
     * @param currentComponent JComponent containing the current component.
     * @param font Font containing the current component's font.
     * @param color Color containing the current component's color.
     * @param x Integer containing the current component's x-axis coordinates.
     * @param y Integer containing the current component's y-axis coordinates.
     * @param width Integer containing the current component's width.
     * @param height Integer containing the current component's height.
     * @param currentLabel Label containing the current component's label.
     */

    public void setMainComponentParameters(JComponent currentComponent,Font font,Color color, int x, int y, int width, int height, JLabel currentLabel){
        currentComponent.setFont(font);
        currentComponent.setForeground(color);
        currentComponent.setBounds(x,y,width,height);
        currentComponent.setOpaque(false);
        currentLabel.add(currentComponent);
    }

    /**
     * Changes the screen.
     * @param currentLabel JLabel of the current screen.
     * @param newLabel JLabel of the new screen.
     */

    public void changeScene(JLabel currentLabel, JLabel newLabel){
        window.remove(currentLabel);
        window.add(newLabel);
        window.revalidate();
        window.repaint();
        newLabel.requestFocus();
    }

    /**
     * Opens the main game window.
     */

    public void startGUI() {
        window.setVisible(true);
    }
}