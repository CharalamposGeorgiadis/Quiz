import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class represents the GUI of the game.
 * @author Anastasios Kachrimanis.
 * @author Charalampos Georgiadis.
 * @version 24/12/2020.
 */

public class GUI {
    final JFrame window; // JFrame that holds the main window of the game.
    final JLabel mainLabel; // JLabel that holds the Main Menu screen.
    final Font neonFont; // Font containing a custom font.
    private Game game; // Game Object that grants access to the game functions.

    /**
     * Constructor.
     * @param questions Holds the directory of the questions' folder.
     * @throws IOException if a file is not found.
     * @throws FontFormatException if a specified font is bad.
     */

    public GUI(File[] questions) throws IOException, FontFormatException {
        // Creates custom font from file.
        neonFont = Font.createFont(Font.TRUETYPE_FONT, new File("neon2.ttf")).deriveFont(60f);

        // Creates the Main frame of the game.
        window = new JFrame("Buzz");
        window.setSize(1366, 768);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setLocationRelativeTo(null);

        // Displays the Main Menu screen.
        mainLabel = new JLabel();
        window.add(mainLabel);
        resizeImage(mainLabel,"Backgrounds/MainMenu.png");

        // Adds an exit button to the current screen.
        exitButton(mainLabel,null,null, 960, 80, 256 , 260,
                "Backgrounds/MainMenuDark.png", "Backgrounds/MainMenu.png");

        if (questions != null) {
            game = new Game(questions); // Loads the questions into the game.

            // Creates the Start Game button.
            JButton startButton = new JButton("START GAME");
            setButtonParameters(startButton,neonFont.deriveFont(100f),Color.orange,310,250,650,100,
                    mainLabel);
            startButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    if (SwingUtilities.isLeftMouseButton(e))
                        chooseGameMode();
                }
            });
            // Creates the Leaderboards button.
            JButton leaderboardButton = new JButton("LEADERBOARDS");
            setButtonParameters(leaderboardButton, neonFont.deriveFont(100f), Color.orange,266,400,750,
                    100, mainLabel);
            leaderboardButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        try {
                            leaderboards();
                        } catch (FileNotFoundException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            });
        }
        else{
            // Displays the "QUESTION FILE NOT FOUND" text.
            JTextArea noFileFound = new JTextArea("BUZZ QUESTIONS FILE\n   WAS NOT FOUND");
            setAreaParameters(noFileFound, neonFont.deriveFont(50f), Color.ORANGE,200,250,600,200,
                    mainLabel);
        }
    }

    /**
     * Displays the main leaderboards screen.
     * @throws FileNotFoundException if a file is not found.
     */

    public void leaderboards() throws FileNotFoundException {
        boolean statsExist; // Boolean containing whether the stats file exists or not.
        statsExist = game.getStats().loadStats();
        // Displays the Leaderboard screen.
        JLabel mainLeaderboardLabel = new JLabel();
        resizeImage(mainLeaderboardLabel,"Backgrounds/LeaderBoard.png");
        changeScene(mainLabel, mainLeaderboardLabel);
        // Adds a "LEADERBOARDS" title to the label.
        JTextArea leaderboardTitle = new JTextArea("LEADERBOARDS");
        setAreaParameters(leaderboardTitle, neonFont.deriveFont(90f), Color.ORANGE, 365, 140, 700,
                100, mainLeaderboardLabel);
        if (statsExist) {
            // Adds a "View leaderboards based on" title.
            JTextArea viewLeaderboardsTitle = new JTextArea("VIEW LEADERBOARDS\n       BASED ON:");
            setAreaParameters(viewLeaderboardsTitle, neonFont.deriveFont(50f), Color.yellow, 435, 225, 700,
                    100, mainLeaderboardLabel);

            // Adds a "HIGHSCORES" button.
            JButton highscoresButton = new JButton("PLAYER HIGHSCORES");
            setButtonParameters(highscoresButton, neonFont.deriveFont(50f), Color.green, 390, 300, 600,
                    75, mainLeaderboardLabel);
            highscoresButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    if (SwingUtilities.isLeftMouseButton(e))
                        viewSelectedLeaderboards(mainLeaderboardLabel, "PLAYER HIGHSCORES");
                }
            });
            // Adds a "MULTIPLAYER WINS" button.
            JButton multiplayerWinsButton = new JButton("MULTIPLAYER WINS");
            setButtonParameters(multiplayerWinsButton, neonFont.deriveFont(50f), Color.green, 390, 370, 600,
                    75, mainLeaderboardLabel);
            multiplayerWinsButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    if (SwingUtilities.isLeftMouseButton(e))
                        viewSelectedLeaderboards(mainLeaderboardLabel, "MULTIPLAYER WINS");
                }
            });
            // Adds a "TEAM HIGHSCORES" button.
            JButton teamPointsButton = new JButton("TEAM HIGHSCORES");
            setButtonParameters(teamPointsButton, neonFont.deriveFont(50f), Color.green, 390, 440, 600,
                    75, mainLeaderboardLabel);
            teamPointsButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    if (SwingUtilities.isLeftMouseButton(e))
                        viewSelectedLeaderboards(mainLeaderboardLabel, " TEAM HIGHSCORES");
                }
            });
            // Adds a "TEAM WINS" button.
            JButton teamWinsButton = new JButton("TEAM WINS");
            setButtonParameters(teamWinsButton, neonFont.deriveFont(50f), Color.green, 390, 510, 600,
                    75, mainLeaderboardLabel);
            teamWinsButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    if (SwingUtilities.isLeftMouseButton(e))
                        viewSelectedLeaderboards(mainLeaderboardLabel, " TEAM WINS");
                }
            });
        }
        else {
            // Displays the "NO STATS FILE FOUND" text.
            leaderboardTitle.setText("NO STATS FILE\n     FOUND");
        }
        // Adds a Back button to the current screen.
        backButton(mainLeaderboardLabel, mainLabel,null, neonFont.deriveFont(60f), Color.red,156,120,
                200,50,"Backgrounds/LeaderBoard.png","Backgrounds/LeaderBoard.png"
                ,"");
        exitButton(mainLeaderboardLabel,neonFont.deriveFont(60f), Color.red,1000,120,200,50,
                "Backgrounds/LeaderBoard.png","Backgrounds/LeaderBoard.png");
    }

    /**
     * View leaderboards based on selected sorting.
     * @param mainLeaderboardLabel JLabel containing the main leaderboard label.
     * @param chosenSorting String containing the selected sorting.
     */

    public void viewSelectedLeaderboards(JLabel mainLeaderboardLabel, String chosenSorting){
        // Displays the highscore leaderboard screen.
        JLabel leaderboardLabel = new JLabel();
        resizeImage(leaderboardLabel,"Backgrounds/LeaderBoard.png");
        changeScene(mainLeaderboardLabel,leaderboardLabel);

        // Displays the current Leaderboards title.
        JTextArea leaderboardTitle = new JTextArea(chosenSorting);
        setAreaParameters(leaderboardTitle, neonFont.deriveFont(70f), Color.ORANGE, 358, 140, 700,
                100, leaderboardLabel);

        // Displays players usernames based on chosen sorting.
        JTextArea leaderboardArea = new JTextArea();
        setAreaParameters(leaderboardArea, neonFont.deriveFont(60f), Color.cyan, 400, 220, 800,
                350, leaderboardLabel);

        switch(chosenSorting){
            case "PLAYER HIGHSCORES":
                game.getStats().sortStats("PLAYER HIGHSCORES");
                for (int i = 0; i < game.getStats().getUsernames().size(); i++){
                    leaderboardArea.setText(leaderboardArea.getText() + " " +
                            game.getStats().getUsernames().get(i) +
                            "  " + game.getStats().getPlayerHighScores().get(i));
                    if (i != game.getStats().getPlayerHighScores().size() - 1)
                        leaderboardArea.setText(leaderboardArea.getText() + "\n");
                }
                break;
            case "MULTIPLAYER WINS":
                game.getStats().sortStats("MULTIPLAYER");
                leaderboardTitle.setBounds(384, 140, 700, 100);
                for (int i = 0; i < game.getStats().getUsernames().size(); i++){
                    leaderboardArea.setText(leaderboardArea.getText() + " " +
                            game.getStats().getUsernames().get(i) + "  " +
                            game.getStats().getMultiplayerWins().get(i));
                    if (i != game.getStats().getPlayerHighScores().size() - 1)
                        leaderboardArea.setText(leaderboardArea.getText() + "\n");
                }
                break;
            case " TEAM HIGHSCORES":
                game.getStats().sortStats("TEAM HIGHSCORES");
                leaderboardTitle.setBounds(350, 140, 700, 100);
                for (int i = 0; i < game.getStats().getTeamNames().size(); i++) {
                    leaderboardArea.setText(leaderboardArea.getText() + " " +
                            game.getStats().getTeamNames().get(i) + "  " +
                            game.getStats().getTeamHighScores().get(i));
                    if (i != game.getStats().getTeamHighScores().size() - 1)
                        leaderboardArea.setText(leaderboardArea.getText() + "\n");
                }
                break;
            case " TEAM WINS":
                game.getStats().sortStats("TEAM WINS");
                leaderboardTitle.setBounds(480, 140, 500, 100);
                for (int i = 0; i < game.getStats().getTeamNames().size(); i++) {
                    leaderboardArea.setText(leaderboardArea.getText() + " " +
                            game.getStats().getTeamNames().get(i) + "  " +
                            game.getStats().getTeamWins().get(i));
                    if (i != game.getStats().getTeamHighScores().size() - 1)
                        leaderboardArea.setText(leaderboardArea.getText() + "\n");
                }
                break;
        }
        // Adds a scroll bar to the stats screen, if needed.
        JScrollPane scroll = new JScrollPane(leaderboardArea);
        setScrollPaneParameters(scroll, 400, 220, 800, 350, leaderboardLabel);
        // Sets the scroll bar at the top of the window.
        SwingUtilities.invokeLater(() -> scroll.getViewport().setViewPosition(new Point(0, 0)));

        // Adds a Back button to the current screen.
        backButton(leaderboardLabel,mainLeaderboardLabel,null,neonFont.deriveFont(60f), Color.red,
                156,120,200,50,"Backgrounds/LeaderBoard.png",
                "Backgrounds/LeaderBoard.png","");
        // Adds an exit button to the current screen.
        exitButton(leaderboardLabel,neonFont.deriveFont(60f), Color.red,1000,120,200,50,
                "Backgrounds/LeaderBoard.png","Backgrounds/LeaderBoard.png");
    }

    /**
     * Choose the game mode.
     */

    public void chooseGameMode(){
        // Displays the "Choose game mode" screen.
        JLabel chooseGameModeLabel=new JLabel();
        resizeImage(chooseGameModeLabel,"Backgrounds/SecondaryMenu.png");
        changeScene(mainLabel,chooseGameModeLabel);

        // Displays the " CHOOSE GAME MODE" title text.
        JTextArea chooseGameModeTitle = new JTextArea(" CHOOSE GAME \n       MODE");
        setAreaParameters(chooseGameModeTitle,neonFont.deriveFont(75f),Color.ORANGE,330,180,600,120,
                chooseGameModeLabel);

        // Displays the game mode buttons.
        // Single-player button.
        JButton singlePlayerButton = new JButton("SOLO");
        UIManager.put("ToolTip.background",Color.BLACK);
        UIManager.put("ToolTip.foreground",Color.RED);
        UIManager.put("ToolTip.font",new Font("Arial",Font.BOLD,18));
        singlePlayerButton.setToolTipText("Try to reach a highscore of 15000 points by playing 3 single-player rounds.");
        setButtonParameters(singlePlayerButton,neonFont.deriveFont(60f),Color.GREEN,280,320,300,80,
                chooseGameModeLabel);
        singlePlayerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (SwingUtilities.isLeftMouseButton(e))
                    enterUsername(false, 1,chooseGameModeLabel);
            }
        });
        // 2-player mode button.
        JButton twoPlayerButton = new JButton("DUEL");
        twoPlayerButton.setToolTipText("Play against another player and find out who will prevail.");
        setButtonParameters(twoPlayerButton,neonFont.deriveFont(60f),Color.MAGENTA,660,320,300,80,
                chooseGameModeLabel);
        twoPlayerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (SwingUtilities.isLeftMouseButton(e))
                    enterUsername(false, 2,chooseGameModeLabel);
            }
        });
        // 3-Player mode button.
        JButton threePlayerButton = new JButton("3 FOR ALL");
        threePlayerButton.setToolTipText("3 players compete in an intense free-for-all game.");
        setButtonParameters(threePlayerButton,neonFont.deriveFont(60f),Color.CYAN,280,420,350,80,
                chooseGameModeLabel);
        threePlayerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (SwingUtilities.isLeftMouseButton(e))
                    enterUsername(false, 3,chooseGameModeLabel);
            }
        });
        // 4-Player mode button.
        JButton fourPlayerButton = new JButton("FREE 4 ALL");
        fourPlayerButton.setToolTipText("Crown the ultimate victor in a fierce free-for-all battle of 4 players.");
        setButtonParameters(fourPlayerButton,neonFont.deriveFont(60f),Color.RED,660,420,350,80,
                chooseGameModeLabel);
        fourPlayerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (SwingUtilities.isLeftMouseButton(e))
                    enterUsername(false, 4,chooseGameModeLabel);
            }
        });
        // Teams of 2 mode button.
        JButton teamsButton = new JButton("TEAMS OF 2");
        teamsButton.setToolTipText("Two teams of 2 compete in order to crown the ultimate duo.");
        setButtonParameters(teamsButton,neonFont.deriveFont(60f),Color.YELLOW,430,520,400,80,
                chooseGameModeLabel);
        teamsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (SwingUtilities.isLeftMouseButton(e))
                    enterUsername(true,4, chooseGameModeLabel);
            }
        });
        game.createTeams(2);
        //Adds a Back button to the current screen.
        backButton(chooseGameModeLabel,mainLabel,null,null,null,960, 80, 256 ,
                260,"Backgrounds/SecondaryMenuDark.png","Backgrounds/SecondaryMenu.png",
                "");
    }

    /**
     * Displays the "Enter Username" screen.
     * @param teams Boolean that determines whether a team mode has been selected or not.
     * @param numberOfPlayers Integer containing the total number of players.
     * @param currentLabel JLabel of the previous screen.
     */

    public void enterUsername(boolean teams, int numberOfPlayers, JLabel currentLabel){
        final int[] currentPlayer = {1};
        // Displays the "Enter Username" screen.
        JLabel usernameLabel= new JLabel();
        resizeImage(usernameLabel,"Backgrounds/EnterUsername.png");
        changeScene(currentLabel,usernameLabel);

        // Creates the "Enter Username For..." title text.
        JTextArea enterUsernameTitle=new JTextArea(" ENTER USERNAME" +"\n          FOR:" + "\n       PLAYER " +
                currentPlayer[0]+"\n");
        setAreaParameters(enterUsernameTitle,neonFont.deriveFont(70f),Color.ORANGE,330,180,600,300,
                usernameLabel);

        // Creates the TextField where the player(s) enter their chosen usernames.
        JTextField enterUsernameText=new JTextField();
        setFieldParameters(enterUsernameText,neonFont.deriveFont(60f),Color.cyan,420,510,480,85,
                usernameLabel);
        enterUsernameText.requestFocusInWindow(); // Makes the cursor appear instantly at the textField.

        // Creates the Area where "(Max characters: 12)" will be displayed.
        JTextArea maxCharactersArea=new JTextArea(" (Max characters: 12)");
        setAreaParameters(maxCharactersArea,neonFont.deriveFont(40f),Color.ORANGE,430,380,460,50,
                usernameLabel);

        // Creates the Area where "Invalid username" will be displayed.
        JTextArea invalidUsername=new JTextArea();
        setAreaParameters(invalidUsername,neonFont.deriveFont(40f),Color.RED,380,430,550,45,
                usernameLabel);

        requestFocusIfClicked(enterUsernameText, enterUsernameTitle);
        requestFocusIfClicked(enterUsernameText, usernameLabel);
        requestFocusIfClicked(enterUsernameText,maxCharactersArea);
        requestFocusIfClicked(enterUsernameText,invalidUsername);

        enterUsernameText.addActionListener(e -> {
            switch (game.enterUsernames(enterUsernameText.getText(), currentPlayer[0]-1)) {
                case -1:
                    enterUsernameText.setText("");
                    invalidUsername.setText(" USERNAME ALREADY CHOSEN");
                    break;
                case 0:
                    enterUsernameText.setText("");
                    invalidUsername.setText("        INVALID USERNAME");
                    break;
                case 1:
                    enterUsernameText.setText("");
                    invalidUsername.setText("");
                    currentPlayer[0]++;
                    enterUsernameTitle.setText(" ENTER USERNAME" +"\n          FOR:"+"\n       PLAYER " +
                            currentPlayer[0] + "\n");
                    if (currentPlayer[0] > numberOfPlayers) {
                        currentPlayer[0]=1;
                        enterUsernameTitle.setText(" ENTER USERNAME" +"\n          FOR:"+"\n       PLAYER " +
                                currentPlayer[0] + "\n");
                        if (!teams)
                            setControls(usernameLabel,enterUsernameText, false);
                        else
                            setUpTeams(usernameLabel, enterUsernameText);
                    }
                    break;
            }
        });
        // Adds a Back button to the current screen.
        backButton(usernameLabel,currentLabel,null,null,null,960, 80, 256 , 260,
                "Backgrounds/EnterUsernameDark.png","Backgrounds/EnterUsername.png","ALL");
    }

    /**
     * The 4 players create their 2 teams and decide the name for each team.
     * @param currentLabel JLabel of the previous screen.
     * @param enterUsernameField JTextField containing the JTextField of the "ENTER USERNAME" screen.
     */
    public void setUpTeams(JLabel currentLabel, JTextField enterUsernameField){
        // Displays the "SET UP TEAMS" screen.
        JLabel setUpTeamsLabel= new JLabel();
        resizeImage(setUpTeamsLabel,"Backgrounds/SecondaryMenu.png");
        changeScene(currentLabel,setUpTeamsLabel);

        // Displays the "SET UP TEAMS" title text.
        JTextArea setUpTeamsTitle=new JTextArea(" SET UP TEAMS");
        setAreaParameters(setUpTeamsTitle,neonFont.deriveFont(70f),Color.ORANGE,400,180,600,100,
                setUpTeamsLabel);

        JTextArea maxCharactersArea=new JTextArea(" (Max characters: 12)");
        setAreaParameters(maxCharactersArea,neonFont.deriveFont(40f),Color.ORANGE,425,260,460,50,
                setUpTeamsLabel);

        // Creates the Area where a message will be displayed if a team name is invalid.
        JTextArea invalidTeamName=new JTextArea();
        setAreaParameters(invalidTeamName,neonFont.deriveFont(40f),Color.RED,440,300,550,45,
                setUpTeamsLabel);
        invalidTeamName.setVisible(false);

        // Adds a "NEXT" button in order to proceed to the "SET CONTROLS" screen.
        JButton nextButton=new JButton("NEXT");
        setButtonParameters(nextButton,neonFont.deriveFont(50f),Color.GREEN,595,350,150,60,
                setUpTeamsLabel);
        nextButton.setVisible(false);

        // Displays the name of each team.
        JTextField[] teamFields = new JTextField[2];
        for (int i = 0; i < 2; i++){
            teamFields[i] = new JTextField(game.getTeams().get(i).getName());
            setFieldParameters(teamFields[i], neonFont.deriveFont(50f), Color.MAGENTA, 240 + 560 * i, 360, 300,
                    40, setUpTeamsLabel);
            teamFields[i].setBorder(new LineBorder(Color.MAGENTA, 1));
            int finalI = i;
            teamFields[i].addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    super.keyReleased(e);
                    String tempString=teamFields[finalI].getText().toUpperCase();
                    if (!tempString.trim().isEmpty())
                        tempString=tempString.replace(" ","_");
                    else
                        tempString=tempString.trim();
                    teamFields[finalI].setText(tempString);
                    game.getTeams().get(finalI).setName(tempString);
                }
            });
            teamFields[i].addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    if (teamFields[finalI].getText().equals("TEAM_" + (finalI + 1)))
                            teamFields[finalI].setText("");
                }
                @Override
                public void focusLost(FocusEvent e) {
                    if (teamFields[finalI].getText().isEmpty())
                        teamFields[finalI].setText("TEAM_" + (finalI + 1));
                }
            });
        }
        // Displays the usernames of each player.
        int [] usernamePosition = new int[game.getPlayers().size()];
        // Displays arrow buttons for each player.
        JButton [][] arrowButtons=new JButton[4][2];
        for (int i = 0; i < game.getPlayers().size(); i++){
            arrowButtons[i][0] = new JButton();
            arrowButtons[i][0].setIcon(new ImageIcon("Backgrounds/Arrow_Left.png"));
            setButtonParameters(arrowButtons[i][0],null,null,381,420+50*i,40,40,
                    setUpTeamsLabel);
            arrowButtons[i][1] = new JButton();
            arrowButtons[i][1].setIcon(new ImageIcon("Backgrounds/Arrow_Right.png"));
            setButtonParameters(arrowButtons[i][1],null,null,939,420 + 50 * i,40,40,
                    setUpTeamsLabel);
        }
        // Displays each player's usernames.
        JTextField [] usernames=new JTextField[4];
        for (int i = 0; i < 4; i++){
            usernames[i] = new JTextField();
            usernames[i].setText(game.getPlayers().get(i).getUsername());
            setFieldParameters(usernames[i],neonFont.deriveFont(40f),Color.CYAN,420,420 + 50 * i,520,40,
                    setUpTeamsLabel);
            usernames[i].setEditable(false);
        }

        for (int i = 0; i < game.getPlayers().size(); i++){
            final int position = i;
            arrowButtons[i][0].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        usernamePosition[position]--;
                        if (usernamePosition[position] == -1) {
                            game.getTeams().get(0).getPlayers().add(game.getPlayers().get(position));
                            if (game.getTeams().get(0).checkIfTeamIsFilled(2) &&
                                    game.getTeams().get(1).checkIfTeamIsFilled(2))
                                nextButton.setVisible(true);
                            arrowButtons[position][0].setVisible(false);
                            arrowButtons[position][1].setVisible(true);
                            arrowButtons[position][1].setBounds(719, 420 + 50 * position, 40, 40);
                            usernames[position].setBounds(200, 420 + 50 * position, 520, 40);
                            if (game.getTeams().get(0).checkIfTeamIsFilled(2)) {
                                for (int j = 0; j < 4; j++) {
                                    if (arrowButtons[j][0].getBounds().x == 381)
                                        arrowButtons[j][0].setVisible(false);
                                }
                            }
                        } else if (usernamePosition[position] == 0) {
                            game.getTeams().get(1).getPlayers().remove(game.getPlayers().get(position));
                            nextButton.setVisible(false);
                            arrowButtons[position][1].setBounds(939, 420 + 50 * position, 40, 40);
                            usernames[position].setBounds(420, 420 + 50 * position, 520, 40);
                            arrowButtons[position][0].setBounds(381, 420 + 50 * position, 40, 40);
                            for (int j = 0; j < 4; j++) {
                                if (arrowButtons[j][0].getBounds().x == 381)
                                    arrowButtons[j][1].setVisible(true);
                            }
                            if (game.getTeams().get(0).checkIfTeamIsFilled(2)) {
                                for (int j = 0; j < 4; j++) {
                                    if (arrowButtons[j][0].getBounds().x == 381)
                                        arrowButtons[j][0].setVisible(false);
                                }
                            }
                        }
                    }
                }
            });
            arrowButtons[i][1].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        usernamePosition[position]++;
                        if (usernamePosition[position] == 1) {
                            game.getTeams().get(1).getPlayers().add(game.getPlayers().get(position));
                            if (game.getTeams().get(0).checkIfTeamIsFilled(2) &&
                                    game.getTeams().get(1).checkIfTeamIsFilled(2))
                                nextButton.setVisible(true);
                            arrowButtons[position][1].setVisible(false);
                            arrowButtons[position][0].setVisible(true);
                            arrowButtons[position][0].setBounds(581, 420 + 50 * position, 40, 40);
                            usernames[position].setBounds(620, 420 + 50 * position, 520, 40);
                            if (game.getTeams().get(1).checkIfTeamIsFilled(2)) {
                                for (int j = 0; j < 4; j++) {
                                    if (arrowButtons[j][1].getBounds().x == 939)
                                        arrowButtons[j][1].setVisible(false);
                                }
                            }
                        } else if (usernamePosition[position] == 0) {
                            game.getTeams().get(0).getPlayers().remove(game.getPlayers().get(position));
                            nextButton.setVisible(false);
                            arrowButtons[position][0].setBounds(381, 420 + 50 * position, 40, 40);
                            arrowButtons[position][1].setBounds(939, 420 + 50 * position, 40, 40);
                            usernames[position].setBounds(420, 420 + 50 * position, 520, 40);
                            for (int j = 0; j < 4; j++) {
                                if (arrowButtons[j][1].getBounds().x == 939)
                                    arrowButtons[j][0].setVisible(true);
                            }
                            if (game.getTeams().get(1).checkIfTeamIsFilled(2)) {
                                for (int j = 0; j < 4; j++) {
                                    if (arrowButtons[j][1].getBounds().x == 939)
                                        arrowButtons[j][1].setVisible(false);
                                }
                            }
                        }
                    }
                }
            });
        }
        nextButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (SwingUtilities.isLeftMouseButton(e)) {
                    if (teamFields[0].getText().equalsIgnoreCase(teamFields[1].getText().toUpperCase())) {
                        invalidTeamName.setText(" TEAMS HAVE SAME NAMES");
                        invalidTeamName.setBounds(400, 300, 550, 45);
                        invalidTeamName.setVisible(true);
                    } else if (teamFields[0].getText().toUpperCase().length() < 1 || teamFields[0].getText().toUpperCase().length() > 14) {
                        invalidTeamName.setText(" INVALID TEAM 1 NAME");
                        invalidTeamName.setBounds(440, 300, 550, 45);
                        invalidTeamName.setVisible(true);
                    } else if (teamFields[1].getText().toUpperCase().length() < 1 || teamFields[1].getText().toUpperCase().length() > 14) {
                        invalidTeamName.setText(" INVALID TEAM 2 NAME");
                        invalidTeamName.setBounds(440, 300, 550, 45);
                        invalidTeamName.setVisible(true);
                    } else {
                        invalidTeamName.setVisible(false);
                        nextButton.setVisible(false);
                        for (int i = 0; i < game.getPlayers().size(); i++) {
                            usernames[i].setBounds(420, 420 + 50 * i, 520, 40);
                            arrowButtons[i][0].setBounds(381, 420 + 50 * i, 40, 40);
                            arrowButtons[i][1].setBounds(939, 420 + 50 * i, 40, 40);
                            arrowButtons[i][0].setVisible(true);
                            arrowButtons[i][1].setVisible(true);
                            usernamePosition[i] = 0;
                        }
                        setControls(setUpTeamsLabel, null, true);
                    }
                }
            }
        });
        // Adds a Back button to the current screen.
        backButton(setUpTeamsLabel,currentLabel,enterUsernameField,null,null,970, 80, 256 ,
                260,"Backgrounds/SecondaryMenuDark.png","Backgrounds/SecondaryMenu.png",
                "TEAM_ALL");
    }

    /**
     * Displays the "Set Controls" screen.
     * @param teams Boolean that determines whether a team mode has been selected or not.
     * @param currentLabel JLabel of the previous screen.
     * @param enterUsernameText TextField of the previous screen so that the back button, if pressed, can restore its
     *                          focus.
     */

    public void setControls(JLabel currentLabel,JTextField enterUsernameText, boolean teams){
        // Displays the "Set Controls" screen.
        JLabel setControlsLabel=new JLabel();
        resizeImage(setControlsLabel,"Backgrounds/SetControls.png");
        changeScene(currentLabel,setControlsLabel);

        // Creates the "Set Controls For" title text.
        JTextArea setControlArea= new JTextArea(" SET CONTROLS\n        FOR ");
        setAreaParameters(setControlArea,neonFont.deriveFont(70f),Color.ORANGE,380,180,600,150,
                setControlsLabel);

        // Displays the username of the player who is currently setting their controls.
        JTextField usernameField= new JTextField(game.getPlayers().get(0).getUsername());
        setFieldParameters(usernameField,neonFont.deriveFont(60f),Color.CYAN,270,310,750,50,
                setControlsLabel);
        usernameField.setEditable(false);

        // Creates the TextField where the player(s) enter their chosen controls.
        JTextField setControlField= new JTextField();
        setFieldParameters(setControlField,neonFont.deriveFont(60f),Color.cyan,560,430,165,100,
                setControlsLabel);
        setControlField.requestFocusInWindow(); // Makes the cursor appear instantly at the textField.

        // Displays the control that is currently set by the player
        JTextArea currentControlArea=new JTextArea("ANSWER A:");
        final int[] currentControlNumber = {0};
        final int[] currentPlayer = {0};
        setAreaParameters(currentControlArea,neonFont.deriveFont(60f),Color.orange,240,460,500,80,
                setControlsLabel);

        /* Creates an Area which will display the message "Invalid Control" if a player enters an invalid control
          or "Control already bound" if the chosen control has already been chosen. */
        JTextArea invalidControl = new JTextArea("");
        setAreaParameters(invalidControl,neonFont.deriveFont(40f),Color.RED,380,380,750,60,
                setControlsLabel);

        setControlField.addActionListener(e -> {
            switch(game.setCurrentControl(setControlField.getText().toUpperCase(), currentPlayer[0],
                    currentControlNumber[0])) {
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
                            currentControlNumber[0] = 0;
                            currentPlayer[0]++;
                            if(currentPlayer[0] == game.getPlayers().size()) {
                                currentPlayer[0] = 0;
                                youCanView(setControlsLabel,setControlField);
                            }
                            usernameField.setText(game.getPlayers().get(currentPlayer[0]).getUsername());
                            currentControlArea.setText("ANSWER A:");
                            break;
                    }
            }
        });
        // Adds a Back button to the current screen.
        if (teams)
            backButton(setControlsLabel, currentLabel, enterUsernameText, null, null, 960, 80,
                    256, 260, "Backgrounds/SetControlsDark.png",
                    "Backgrounds/SetControls.png", "TEAMS");
        else
            backButton(setControlsLabel,currentLabel,enterUsernameText,null,null,960, 80, 256,
                    260,"Backgrounds/SetControlsDark.png","Backgrounds/SetControls.png",
                    "ALL");

    }

    /**
     * Displays the "You can view each player's controls by clicking:..." screen.
     * @param currentLabel JLabel of the previous screen.
     * @param setControlField TextField of the previous screen so that the back button, if pressed, can restore its
     *                        focus.
     */

    public void youCanView(JLabel currentLabel,JTextField setControlField){
        // Displays the "You can view each player's controls by clicking:..." screen.
        JLabel youCanViewLabel = new JLabel();
        resizeImage(youCanViewLabel,"Backgrounds/YouCanView.png");
        changeScene(currentLabel, youCanViewLabel);
        requestFocusIfClicked(youCanViewLabel,youCanViewLabel);

        // Displays the area where "You can view each player's controls by clicking:..." is displayed.
        JTextArea youCanViewArea = new JTextArea("         You can view\n each player's controls" +
                "\n           by clicking:");
        setAreaParameters(youCanViewArea,neonFont.deriveFont(80f),Color.ORANGE,150,100,1200,250,
                youCanViewLabel);
        requestFocusIfClicked(youCanViewLabel,youCanViewArea);

        // Displays the "PRESS ENTER TO CONTINUE" text.
        JTextArea pressEnterArea = new JTextArea(" Press enter to continue");
        setAreaParameters(pressEnterArea,neonFont.deriveFont(70f),Color.yellow,240,550,1000,80,
                youCanViewLabel);
        requestFocusIfClicked(youCanViewLabel,pressEnterArea);
        youCanViewLabel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyTyped(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                    chooseCategory(youCanViewLabel);
            }
        });

        // Adds a Back button to the current screen.
        backButton(youCanViewLabel,currentLabel,setControlField,null,null,0,0,130,120,
                "Backgrounds/YouCanViewDarkBack.png","Backgrounds/YouCanView.png",
                "CONTROLS");

        // Adds an exit button to the current screen.
        exitButton(youCanViewLabel,null,null,1230,0,120,120,
                "Backgrounds/YouCanViewDarkX.png","Backgrounds/YouCanView.png" );
    }

    /**
     * Displays the "Choose Category" screen.
     * @param currentLabel JLabel of the previous screen.
     */

    public void chooseCategory(JLabel currentLabel) {
        if (game.getPlayers().size() > 1)
            game.getRound().addMultiplayerRounds(game.getPlayers().size());
            if (game.getTeams().get(0).getPlayers().size() != 0)
                game.getRound().addTeamRounds();

        // Displays the "Choose Category" screen.
        JLabel chooseCategoryLabel = new JLabel();
        resizeImage(chooseCategoryLabel,"Backgrounds/ChooseCategory.png");
        changeScene(currentLabel, chooseCategoryLabel);
        requestFocusIfClicked(chooseCategoryLabel,chooseCategoryLabel);

        // Adds a CONTROLS button.
        controlsButton(chooseCategoryLabel,null,null,0,0,130,120,
                "Backgrounds/ChooseCategoryDarkC.png","Backgrounds/ChooseCategory.png");

        // Adds an exit button to the current screen.
        exitButton(chooseCategoryLabel, null,null,1230,0,120,120,
                "Backgrounds/ChooseCategoryDarkX.png", "Backgrounds/ChooseCategory.png");

        final ArrayList<String>[] randomCategories = new ArrayList[]{game.randomCategories()};

        // Creates the field where "Player "X" choose a category" will be displayed.
        JTextField chooseCategoryField = new JTextField();
        setFieldParameters(chooseCategoryField, neonFont.deriveFont(55f), Color.ORANGE, 85, 360, 1200,
                60, chooseCategoryLabel);
        chooseCategoryField.setEditable(false);
        requestFocusIfClicked(chooseCategoryLabel,chooseCategoryField);

        Random rand = new Random();
        final int[] randPlayer = {rand.nextInt(game.getPlayers().size())};
        chooseCategoryField.setText(game.getPlayers().get(randPlayer[0]).getUsername() + " choose a category");

    // Creates TextFields for the 4 categories that will be displayed.
        JTextField category1 = new JTextField();
        setFieldParameters(category1, neonFont.deriveFont(60f), Color.yellow, 20, 480, 600, 70,
                chooseCategoryLabel);
        category1.setEditable(false);
        requestFocusIfClicked(chooseCategoryLabel,category1);

        JTextField category2 = new JTextField();
        setFieldParameters(category2, neonFont.deriveFont(60f), Color.cyan, 750, 480, 600, 70,
                chooseCategoryLabel);
        category2.setEditable(false);
        requestFocusIfClicked(chooseCategoryLabel,category2);

        JTextField category3 = new JTextField();
        setFieldParameters(category3, neonFont.deriveFont(60f), Color.blue, 20, 640, 600, 70,
                chooseCategoryLabel);
        category3.setEditable(false);
        requestFocusIfClicked(chooseCategoryLabel,category3);

        JTextField category4 = new JTextField();
        setFieldParameters(category4, neonFont.deriveFont(60f), Color.magenta, 750, 640, 600, 70,
                chooseCategoryLabel);
        category4.setEditable(false);
        requestFocusIfClicked(chooseCategoryLabel,category4);

        // Adds the names of the 4 randomly chosen categories to their respective boxes.
        displayRandomCategories(chooseCategoryLabel, category1, category2, category3, category4, randomCategories[0]);
        final boolean[] flag = {false};
        chooseCategoryLabel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if (Character.toUpperCase(e.getKeyChar()) == game.getPlayers().get(randPlayer[0]).
                        getControl(0).charAt(0)){
                    flag[0] = true;
                    proceedToRound(chooseCategoryLabel, randomCategories[0].get(0));
                }
                else if (Character.toUpperCase(e.getKeyChar()) == game.getPlayers().get(randPlayer[0]).
                        getControl(1).charAt(0) && !randomCategories[0].get(1).equals("")){
                    flag[0] = true;
                    proceedToRound(chooseCategoryLabel, randomCategories[0].get(1));
                }
                else if (Character.toUpperCase(e.getKeyChar()) == game.getPlayers().get(randPlayer[0]).
                        getControl(2).charAt(0) && !randomCategories[0].get(2).equals("")){
                    flag[0] = true;
                    proceedToRound(chooseCategoryLabel, randomCategories[0].get(2));
                }
                else if (Character.toUpperCase(e.getKeyChar()) == game.getPlayers().get(randPlayer[0]).
                        getControl(3).charAt(0) && !randomCategories[0].get(3).equals("")){
                    flag[0] = true;
                    proceedToRound(chooseCategoryLabel, randomCategories[0].get(3));
                }
                if (flag[0]) {
                    flag[0] = false;
                    randPlayer[0] = rand.nextInt(game.getPlayers().size());
                    chooseCategoryField.setText(game.getPlayers().get(randPlayer[0]).getUsername() +
                            " choose a category");
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
        // Displays the "Current Round" screen.
        JLabel currentRoundLabel = new JLabel();
        resizeImage(currentRoundLabel,"Backgrounds/CurrentRound.png");
        changeScene(currentLabel, currentRoundLabel);

        // Displays the "Current Round" title.
        JTextField currentRoundField = new JTextField("");
        setFieldParameters(currentRoundField, neonFont.deriveFont(80f), Color.ORANGE, 300, 0, 800,
                150, currentRoundLabel);
        currentRoundField.setEditable(false);

        // Displays the description of the current round type.
        JTextArea descriptionArea = new JTextArea();
        Font descriptionFont=new Font("Arial",Font.PLAIN, 60);
        setAreaParameters(descriptionArea,descriptionFont,Color.orange,320, 150,800,800,
                currentRoundLabel);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);

        // Adds an exit button to the current screen.
        exitButton(currentRoundLabel, null,null,1230,0,120,120,
                "Backgrounds/CurrentRoundDarkX.png", "Backgrounds/CurrentRound.png");

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
            case "VOTING":
                currentRoundField.setText("VOTING");
                descriptionArea.setText(game.getRound().getRoundDescription("VOTING"));
                delayRoundType("VOTING", currentRoundLabel, chosenCategory, currentLabel);
                break;
        }
    }

    /**
     * Begins every round type.
     * @param currentRound String containing the name of the current round.
     * @param currentLabel JLabel of the previous screen.
     * @param chosenCategory String containing the chosen category.
     * @param chooseCategoryLabel JLabel containing the "Choose Category" screen.
     */

    public void startRound(String currentRound, JLabel currentLabel, String chosenCategory,JLabel chooseCategoryLabel){
        // Displays the "Questions" screen.
        JLabel questionsLabel = new JLabel();
        resizeImage(questionsLabel,"Backgrounds/Questions.png");
        changeScene(currentLabel,questionsLabel);
        requestFocusIfClicked(questionsLabel,questionsLabel);

        // Adds an exit button to the current screen.
        exitButton(questionsLabel,null,null,1230,0,120,120,
                "Backgrounds/QuestionsDark.png","Backgrounds/Questions.png" );

        Font questionFont = new Font("Arial",Font.PLAIN, 27);
        // Displays the questions
        JTextField questionText=new JTextField();
        setFieldParameters(questionText, questionFont,Color.red,100,360,1170,50, questionsLabel);
        questionText.setEditable(false);
        requestFocusIfClicked(questionsLabel, questionText);

        // Displays the possible answers.
        JTextField answer1 = new JTextField();
        setFieldParameters(answer1,questionFont, Color.yellow, 20, 480, 600, 70, questionsLabel);
        answer1.setEditable(false);
        requestFocusIfClicked(questionsLabel, answer1);

        JTextField answer2 = new JTextField();
        setFieldParameters(answer2,questionFont, Color.cyan, 750, 480, 600, 70, questionsLabel);
        answer2.setEditable(false);
        requestFocusIfClicked(questionsLabel, answer2);

        JTextField answer3 = new JTextField();
        setFieldParameters(answer3,questionFont, Color.blue, 20, 640, 600, 70, questionsLabel);
        answer3.setEditable(false);
        requestFocusIfClicked(questionsLabel, answer3);

        JTextField answer4 = new JTextField();
        setFieldParameters(answer4,questionFont, Color.magenta, 750, 640, 600, 70, questionsLabel);
        answer4.setEditable(false);
        requestFocusIfClicked(questionsLabel, answer4);

        // Displays the chosen category.
        JTextField chosenCategoryField = new JTextField(chosenCategory);
        setFieldParameters(chosenCategoryField,neonFont.deriveFont(80f),Color.ORANGE,270, 0, 800, 90,
                questionsLabel);
        chosenCategoryField.setEditable(false);
        requestFocusIfClicked(questionsLabel, chosenCategoryField);

        // Displays the "Players Answered title"
        JTextField playersAnsweredTitle = new JTextField(" Players Answered");
        setFieldParameters(playersAnsweredTitle,neonFont.deriveFont(40f),Color.yellow,0,0,380,80,
                questionsLabel);
        playersAnsweredTitle.setEditable(false);
        requestFocusIfClicked(questionsLabel, playersAnsweredTitle);

        // Displays the players who have already answered, except from the last one.
        JTextArea playersAnsweredArea = new JTextArea("");
        setAreaParameters(playersAnsweredArea, neonFont.deriveFont(35f), Color.cyan,0,55,520,200,
                questionsLabel);
        requestFocusIfClicked(questionsLabel,playersAnsweredArea);
        if (game.getPlayers().size()==1){
            playersAnsweredTitle.setVisible(false);
            playersAnsweredArea.setVisible(false);
        }
        JTextArea[] voteArea = new JTextArea[4];
        if (currentRound.equals("VOTING")){
            voteArea[0] = new JTextArea("0");
            setAreaParameters(voteArea[0], neonFont.deriveFont(50f), Color.RED,25,470,50,50,
                    questionsLabel);
            voteArea[1] = new JTextArea("0");
            setAreaParameters(voteArea[1], neonFont.deriveFont(50f), Color.RED,753,470,50,50,
                    questionsLabel);
            voteArea[2] = new JTextArea("0");
            setAreaParameters(voteArea[2], neonFont.deriveFont(50f), Color.RED,25,635,50,50,
                    questionsLabel);
            voteArea[3] = new JTextArea("0");
            setAreaParameters(voteArea[3], neonFont.deriveFont(50f), Color.RED,753,635,50,50,
                    questionsLabel);
            for (int i = 0; i < 4; i++){
                voteArea[i].setVisible(false);
            }
        }
        // Creates the area where an image of a question,if it exists, will be displayed.
        JButton imageQuestion = new JButton();
        setButtonParameters(imageQuestion,null,null,420,85,480,240,questionsLabel);
        requestFocusIfClicked(questionsLabel, imageQuestion);
        final int[] currentQuestion = {1};
        final int[] playersAnswered = {0};
        switch (currentRound){
            case "BETTING":
                Question[] randomQuestion = {game.getRandomQuestion(chosenCategory)};
                displayBettingOptions(questionText, answer1, answer2, answer3, answer4);
                questionsLabel.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        super.keyTyped(e);
                        switch (currentQuestion[0] % 2){
                            case 0:
                                for (Player p : game.getPlayers()){
                                    for (int i = 0; i < 4;i++){
                                        if (Character.toUpperCase(e.getKeyChar()) == p.getControl(i).charAt(0) &&
                                                !playersAnsweredArea.getText().contains(p.getUsername()))
                                            playersAnsweredArea.append(" " + p.getUsername() + "\n");
                                    }
                                }
                                playersAnswered[0] += game.correctAnswer(e.getKeyChar(), randomQuestion[0],
                                        currentRound,0);
                                if (playersAnswered[0] == game.getPlayers().size()) {
                                    imageQuestion.setIcon(null);
                                    playersAnsweredArea.setText("");
                                    game.resetHaveAnswered();
                                    playersAnswered[0] = 0;
                                    currentQuestion[0]++;
                                    if (currentQuestion[0] == 11) {
                                        game.getRound().getRoundTypes().remove(currentRound);
                                        if (game.getAvailableQuestions().size()!=0 && game.getRound().
                                                getRoundTypes().size() != 0) {
                                            changeScene(questionsLabel, chooseCategoryLabel);
                                            resultScreen(chooseCategoryLabel, randomQuestion[0].getCorrectAnswer(),
                                                    false, false);
                                        }
                                        else
                                            resultScreen(questionsLabel, randomQuestion[0].getCorrectAnswer(),
                                                    true,false);
                                    } else {
                                        if (game.getAvailableQuestions().size() !=0 ) {
                                            resultScreen(questionsLabel, randomQuestion[0].getCorrectAnswer(),
                                                    false,false);
                                            randomQuestion[0] = game.getRandomQuestion(chosenCategory);
                                        }
                                        else
                                            resultScreen(questionsLabel, randomQuestion[0].getCorrectAnswer(),
                                                    true,false);
                                    }
                                    displayBettingOptions(questionText, answer1, answer2, answer3, answer4);
                                }
                                break;
                            case 1:
                                for (Player p : game.getPlayers()){
                                    for (int i = 0; i < 4; i++){
                                        if (Character.toUpperCase(e.getKeyChar()) == p.getControl(i).charAt(0) &&
                                                !playersAnsweredArea.getText().contains(p.getUsername()))
                                            playersAnsweredArea.append(p.getUsername() + "\n");
                                    }
                                }
                                playersAnswered[0] += game.hasBet(e.getKeyChar());
                                if (playersAnswered[0] == game.getPlayers().size()) {
                                    playersAnsweredArea.setText("");
                                    game.resetHaveAnswered();
                                    playersAnswered[0] = 0;
                                    currentQuestion[0]++;
                                    displayQuestionAndAnswers(questionText, answer1, answer2, answer3, answer4,
                                            randomQuestion[0]);
                                    //If a questions is accompanied by an image, it is displayed.
                                    imageQuestion.setIcon(new ImageIcon("Buzz Questions Directory\\" +
                                            randomQuestion[0].getMedia()));
                                }
                        }
                    }
                });
                break;
            case "COUNTDOWN":
                randomQuestion = new Question[]{game.getRandomQuestion(chosenCategory)};
                //Displays the timer.
                JTextField timerField= new JTextField();
                setFieldParameters(timerField, questionFont.deriveFont(60f), Color.red,1240,120,100,
                        100, questionsLabel);
                timerField.setEditable(false);
                requestFocusIfClicked(questionsLabel, timerField);
                displayQuestionAndAnswers(questionText, answer1, answer2, answer3, answer4, randomQuestion[0]);
                // If a questions is accompanied by an image, it is displayed.
                imageQuestion.setIcon(new ImageIcon("Buzz Questions Directory\\" +
                        randomQuestion[0].getMedia()));
                final int[] milliseconds = {7000};
                Timer timer = new Timer(100, t -> {
                    milliseconds[0] -= 100;
                    timerField.setText(String.valueOf(milliseconds[0] / 1000.0));
                    if (milliseconds[0] == 0){
                        game.resetHaveAnswered();
                        playersAnsweredArea.setText("");
                        playersAnswered[0] = 0;
                        currentQuestion[0]++;
                        if (currentQuestion[0] != 6) {
                            if (game.getAvailableQuestions().size() != 0) {
                                resultScreen(questionsLabel, randomQuestion[0].getCorrectAnswer(), false,
                                        false);
                                randomQuestion[0] = game.getRandomQuestion(chosenCategory);
                                displayQuestionAndAnswers(questionText, answer1, answer2, answer3, answer4,
                                        randomQuestion[0]);
                                //If a questions is accompanied by an image, it is displayed.
                                imageQuestion.setIcon(new ImageIcon("Buzz Questions Directory\\" +
                                        randomQuestion[0].getMedia()));
                                milliseconds[0] = 11500;
                                ((Timer)t.getSource()).restart();
                            } else {
                                game.getRound().getRoundTypes().remove(currentRound);
                                resultScreen(questionsLabel, randomQuestion[0].getCorrectAnswer(), true,
                                        false);
                                ((Timer)t.getSource()).stop();
                            }
                        } else {
                            ((Timer)t.getSource()).stop();
                            game.getRound().getRoundTypes().remove(currentRound);
                            if (game.getAvailableQuestions().size() != 0 && game.getRound().getRoundTypes().size() != 0)
                            {
                                changeScene(questionsLabel, chooseCategoryLabel);
                                resultScreen(chooseCategoryLabel, randomQuestion[0].getCorrectAnswer(), false,
                                        false);
                            } else
                                resultScreen(questionsLabel, randomQuestion[0].getCorrectAnswer(), true,
                                        false);
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
                                if (Character.toUpperCase(e.getKeyChar()) == p.getControl(i).charAt(0) &&
                                        !playersAnsweredArea.getText().contains(p.getUsername()))
                                    playersAnsweredArea.append(" " + p.getUsername() + "\n");
                            }
                        }
                        playersAnswered[0] += game.correctAnswer(e.getKeyChar(), randomQuestion[0], currentRound,
                                milliseconds[0]);
                        if (playersAnswered[0] == game.getPlayers().size()) {
                            game.resetHaveAnswered();
                            playersAnsweredArea.setText("");
                            playersAnswered[0] = 0;
                            currentQuestion[0]++;
                            if (currentQuestion[0] != 6) {
                                if (game.getAvailableQuestions().size() != 0) {
                                    resultScreen(questionsLabel, randomQuestion[0].getCorrectAnswer(), false,
                                            false);
                                    randomQuestion[0] = game.getRandomQuestion(chosenCategory);
                                    displayQuestionAndAnswers(questionText, answer1, answer2, answer3, answer4,
                                            randomQuestion[0]);
                                    // If a questions is accompanied by an image, it is displayed.
                                    imageQuestion.setIcon(new ImageIcon("Buzz Questions Directory\\" +
                                            randomQuestion[0].getMedia()));
                                    milliseconds[0] = 11500;
                                    timer.restart();
                                } else {
                                    game.getRound().getRoundTypes().remove(currentRound);
                                    resultScreen(questionsLabel, randomQuestion[0].getCorrectAnswer(), true,
                                            false);
                                    timer.stop();
                                }
                            } else {
                                game.getRound().getRoundTypes().remove(currentRound);
                                timer.stop();
                                if (game.getAvailableQuestions().size() != 0 &&
                                        game.getRound().getRoundTypes().size() != 0) {
                                    changeScene(questionsLabel, chooseCategoryLabel);
                                    resultScreen(chooseCategoryLabel, randomQuestion[0].getCorrectAnswer(),
                                            false, false);
                                } else
                                    resultScreen(questionsLabel, randomQuestion[0].getCorrectAnswer(), true,
                                            false);

                            }
                        }
                    }
                });
                break;
            case "THERMOMETER":
                game.getRound().getRoundTypes().remove(currentRound);
                randomQuestion = new Question[]{game.getRandomQuestion(null)};
                chosenCategoryField.setText(randomQuestion[0].getCategory());
                displayQuestionAndAnswers(questionText, answer1, answer2, answer3, answer4, randomQuestion[0]);
                // If a questions is accompanied by an image, it is displayed.
                imageQuestion.setIcon(new ImageIcon("Buzz Questions Directory\\"+randomQuestion[0].getMedia()));
                questionsLabel.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        super.keyTyped(e);
                        for (Player p : game.getPlayers()) {
                            for (int i = 0; i < 4; i++) {
                                if (Character.toUpperCase(e.getKeyChar()) == p.getControl(i).charAt(0) &&
                                        !playersAnsweredArea.getText().contains(p.getUsername()))
                                    playersAnsweredArea.append(" " + p.getUsername() + "\n");
                            }
                        }
                        playersAnswered[0] += game.correctAnswer(e.getKeyChar(), randomQuestion[0], currentRound,
                                0);
                        for (Player p : game.getPlayers())
                            if (p.getThermometerCorrectAnswers() == 5)
                                resultScreen(questionsLabel, randomQuestion[0].getCorrectAnswer(), true,
                                        true);
                        for (Team t : game.getTeams()){
                            int temp = 0;
                            for (Player p : t.getPlayers()){
                                temp += p.getThermometerCorrectAnswers();
                            }
                            if (temp == 5){
                                t.getPlayers().get(0).setPoints(t.getPlayers().get(0).getPoints() + 5000);
                                resultScreen(questionsLabel, randomQuestion[0].getCorrectAnswer(), true,
                                        true);
                            }
                        }
                        if (playersAnswered[0] == game.getPlayers().size()) {
                            if (game.getAvailableQuestions().size() != 0) {
                                playersAnsweredArea.setText("");
                                game.resetHaveAnswered();
                                playersAnswered[0] = 0;
                                resultScreen(questionsLabel, randomQuestion[0].getCorrectAnswer(),
                                        game.getAvailableQuestions().size() == 0, true);
                                randomQuestion[0] = game.getRandomQuestion(null);
                                chosenCategoryField.setText(randomQuestion[0].getCategory());
                                displayQuestionAndAnswers(questionText, answer1, answer2, answer3, answer4,
                                        randomQuestion[0]);
                                // If a questions is accompanied by an image, it is displayed.
                                imageQuestion.setIcon(new ImageIcon("Buzz Questions Directory\\" +
                                        randomQuestion[0].getMedia()));
                            } else {
                                int playerWon = 0;
                                for (int i = 0; i < game.getPlayers().size() - 1; i++) {
                                    for (int j = i + 1; j < game.getPlayers().size(); j++) {
                                        if (game.getPlayers().get(i).getThermometerCorrectAnswers() >
                                                game.getPlayers().get(j).getThermometerCorrectAnswers())
                                            playerWon = i;
                                        else
                                            playerWon = j;
                                    }
                                }
                                for (int  i= 0; i < game.getPlayers().size() - 1; i++){
                                    if (game.getPlayers().get(i).getThermometerCorrectAnswers() >
                                            game.getPlayers().get(i + 1).getThermometerCorrectAnswers()) {
                                        game.getPlayers().get(playerWon).setPoints(game.getPlayers().get(playerWon).
                                                getPoints() + 5000);
                                        break;
                                    }
                                }
                                resultScreen(questionsLabel, randomQuestion[0].getCorrectAnswer(), true,
                                        true);
                            }
                        }
                    }
                });
                break;
            default: // "RIGHT ANSWER" OR "FASTEST FINGER" OR "VOTING".
                randomQuestion = new Question[]{game.getRandomQuestion(chosenCategory)};
                displayQuestionAndAnswers(questionText, answer1, answer2, answer3, answer4, randomQuestion[0]);
                // If a questions is accompanied by an image, it is displayed.
                imageQuestion.setIcon(new ImageIcon("Buzz Questions Directory\\"+randomQuestion[0].getMedia()));
                questionsLabel.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        super.keyTyped(e);
                        for (Player p:game.getPlayers()){
                            for (int i = 0; i < 4; i++){
                                if (Character.toUpperCase(e.getKeyChar()) == p.getControl(i).charAt(0) &&
                                        !playersAnsweredArea.getText().contains(p.getUsername())) {
                                    if (currentRound.equals("VOTING")) {
                                        voteArea[i].setVisible(true);
                                        voteArea[i].setText(" " + (Integer.parseInt(voteArea[i].getText().trim()) + 1));
                                        playersAnsweredArea.setVisible(false);
                                    }
                                    playersAnsweredArea.append(" " + p.getUsername() + "\n");
                                }
                            }
                        }
                        playersAnswered[0] += game.correctAnswer(e.getKeyChar(), randomQuestion[0], currentRound,
                                0);
                        if (playersAnswered[0] == game.getPlayers().size()) {
                            playersAnsweredArea.setText("");
                            game.resetHaveAnswered();
                            playersAnswered[0] = 0;
                            currentQuestion[0]++;
                            if (currentQuestion[0] != 6) {
                                for (int i = 0; i < 4; i ++){
                                    voteArea[i].setText("0");
                                    voteArea[i].setVisible(false);
                                }
                                resultScreen(questionsLabel, randomQuestion[0].getCorrectAnswer(),
                                        game.getAvailableQuestions().size() == 0,false);
                                randomQuestion[0] = game.getRandomQuestion(chosenCategory);
                                displayQuestionAndAnswers(questionText, answer1, answer2, answer3, answer4,
                                        randomQuestion[0]);
                                //If a questions is accompanied by an image, it is displayed.
                                imageQuestion.setIcon(new ImageIcon("Buzz Questions Directory\\" +
                                        randomQuestion[0].getMedia()));
                            } else {
                                game.getRound().getRoundTypes().remove(currentRound);
                                if (game.getAvailableQuestions().size() != 0 && game.getRound().getRoundTypes().size()!=0)
                                {
                                    changeScene(questionsLabel, chooseCategoryLabel);
                                    resultScreen(chooseCategoryLabel, randomQuestion[0].getCorrectAnswer(),
                                            false, false);
                                }
                                else if(game.getAvailableQuestions().size()==0 ||
                                        game.getRound().getRoundTypes().size() == 0)
                                    resultScreen(questionsLabel, randomQuestion[0].getCorrectAnswer(), true,
                                            false);
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
        resizeImage(currentLabel,"Backgrounds/ChooseCategory.png");
        // Displays the "View Controls" screen.
        JLabel viewControlsLabel = new JLabel();
        resizeImage(viewControlsLabel,"Backgrounds/LeaderBoard.png");
        changeScene(currentLabel, viewControlsLabel);

        // Displays the "CONTROLS" title.
        JTextArea viewControlsTitle = new JTextArea("CONTROLS");
        setAreaParameters(viewControlsTitle,neonFont.deriveFont(80f),Color.ORANGE,490,130,500,80,
                viewControlsLabel);

        // Adds the Area where the controls will be displayed.
        JTextArea controlsArea = new JTextArea("   ");
        setAreaParameters(controlsArea,neonFont.deriveFont(50f),Color.cyan,165,250,1300,500,
                viewControlsLabel);

        // Adds Scrolling function in the leaderboard display area.
        JScrollPane scroll = new JScrollPane(controlsArea);
        setScrollPaneParameters(scroll,165,250,1300,500, viewControlsLabel);

        for (int i = 1; i < game.getPlayers().size() + 1; i++){
            if (i < game.getPlayers().size())
                controlsArea.append("Player " + i + "  ");
            else
                controlsArea.append("Player " + i);
        }
        controlsArea.append("\nA:     ");
        int count = 1;
        for (Player p : game.getPlayers()){
            if (count == game.getPlayers().size())
                controlsArea.append(p.getControl(0) + "  ");
            else
                controlsArea.append(p.getControl(0) + "            ");
            count++;
        }
        count = 1;
        controlsArea.append("\nB:     ");
        for (Player p : game.getPlayers()){
            if (count == game.getPlayers().size())
                controlsArea.append(p.getControl(1) + "  ");
            else
                controlsArea.append(p.getControl(1) + "            ");
            count++;
        }
        count = 1;
        controlsArea.append("\nC:     ");
        for (Player p : game.getPlayers()){
            if (count == game.getPlayers().size())
                controlsArea.append(p.getControl(2) + "  ");
            else
                controlsArea.append(p.getControl(2) + "            ");
            count++;
        }
        count = 1;
        controlsArea.append("\nD:     ");
        for (Player p : game.getPlayers()){
            if (count == game.getPlayers().size())
                controlsArea.append(p.getControl(3) + "  ");
            else
                controlsArea.append(p.getControl(3)+ "            ");
            count++;
        }

        // Adds a Back button to the current screen.
        backButton(viewControlsLabel, currentLabel,null, neonFont.deriveFont(70f), Color.red,150,120,
                220,70,"Backgrounds/LeaderBoard.png","Backgrounds/LeaderBoard.png",
                "");
    }

    /**
     * Displays the result of the current question.
     * If the player(s) is/are not playing THERMOMETER their username(s) and points are displayed. Otherwise, their
     * username(s), points and THERMOMETER wins are displayed.
     * @param currentLabel JLabel of the previous screen.
     * @param correctAnswer String containing a questions correct answer.
     * @param hasEnded Boolean containing whether the game has ended or not.
     * @param thermometer Boolean containing whether players are playing THERMOMETER or not.
     */

    public void resultScreen(JLabel currentLabel, String correctAnswer, boolean hasEnded, boolean thermometer){
        // Displays the current question's result screen.
        JLabel resultScreenLabel = new JLabel();
        resizeImage(resultScreenLabel,"Backgrounds/CurrentRound.png");
        changeScene(currentLabel, resultScreenLabel);

        // Displays the "The Correct Answer is:" title.
        JTextArea resultArea = new JTextArea("  The Correct Answer \n                is:\n");
        setAreaParameters(resultArea, neonFont.deriveFont(70f), Color.ORANGE,250, 20, 800, 120,
                resultScreenLabel);

        // Displays the correct answer.
        JTextField correctAnswerField = new JTextField(" "+correctAnswer);
        setFieldParameters(correctAnswerField, neonFont.deriveFont(65f), Color.GREEN,0,120,1340,150,
                resultScreenLabel);
        correctAnswerField.setEditable(false);

        // Adds an exit button to the current screen.
        exitButton(resultScreenLabel,null,null,1230,0,120,120,
                "Backgrounds/CurrentRoundDarkX.png","Backgrounds/CurrentRound.png" );

        // Displays Player Username and Points.
        switch(game.getPlayers().size()){
            case 1:
                JTextField playerUsername = new JTextField(" "  + game.getPlayers().get(0).getUsername());
                setFieldParameters(playerUsername, neonFont.deriveFont(60f), Color.CYAN,340,280,650,60,
                        resultScreenLabel);
                playerUsername.setEditable(false);

                JTextField playerPoints = new JTextField(" " + game.getPlayers().get(0).getPoints());
                setFieldParameters(playerPoints, neonFont.deriveFont(55f), Color.ORANGE,340,350,650,60,
                        resultScreenLabel);
                playerPoints.setEditable(false);
                break;
            case 2:
                for (int i = 0; i < game.getPlayers().size(); i++){
                    playerUsername = new JTextField(" "  + game.getPlayers().get(i).getUsername());
                    setFieldParameters(playerUsername, neonFont.deriveFont(55f), Color.CYAN,700*i,300,650,
                                60, resultScreenLabel);
                    playerUsername.setEditable(false);

                    if (!thermometer){
                        playerPoints = new JTextField(" " + game.getPlayers().get(i).getPoints());
                    }
                    else{
                        playerPoints = new JTextField(" " + game.getPlayers().get(i).getThermometerCorrectAnswers());
                    }
                    setFieldParameters(playerPoints, neonFont.deriveFont(50f), Color.ORANGE, 700 * i, 350,
                            650, 60, resultScreenLabel);
                    playerPoints.setEditable(false);
                }
                break;
            case 3:
                for (int i = 0; i < game.getPlayers().size(); i++){
                    playerUsername = new JTextField(" " + game.getPlayers().get(i).getUsername());
                    setFieldParameters(playerUsername, neonFont.deriveFont(55f), Color.CYAN,700 * i / 2 ,
                            300 + 150 * (i % 2),650,60,resultScreenLabel);
                    playerUsername.setEditable(false);
                    if (!thermometer) {
                        playerPoints = new JTextField(" " + game.getPlayers().get(i).getPoints());
                    }
                    else{
                        playerPoints = new JTextField(" " + game.getPlayers().get(i).getThermometerCorrectAnswers());
                    }
                    setFieldParameters(playerPoints, neonFont.deriveFont(50f), Color.ORANGE, 700 * i / 2,
                                350 + 150 * (i % 2), 650, 60, resultScreenLabel);
                    playerPoints.setEditable(false);
                }
                break;
            case 4:
                if (game.getTeams().get(0).getPlayers().isEmpty()) {
                    if (!thermometer){
                        for (int i = 0; i < game.getPlayers().size() / 2; i++) {
                            playerUsername = new JTextField(" " + game.getPlayers().get(i).getUsername());
                            setFieldParameters(playerUsername, neonFont.deriveFont(55f), Color.CYAN, 700 * i, 300,
                                    650, 60, resultScreenLabel);
                            playerUsername.setEditable(false);

                            playerPoints = new JTextField(" " + game.getPlayers().get(i).getPoints());
                            setFieldParameters(playerPoints, neonFont.deriveFont(50f), Color.ORANGE, 700 * i, 350,
                                    650, 60, resultScreenLabel);
                            playerPoints.setEditable(false);
                        }
                        for (int i = game.getPlayers().size() / 2; i < game.getPlayers().size(); i++) {
                            playerUsername = new JTextField(" " + game.getPlayers().get(i).getUsername());
                            setFieldParameters(playerUsername, neonFont.deriveFont(55f), Color.CYAN, 700 * (i - 2),
                                    500, 650, 60, resultScreenLabel);
                            playerUsername.setEditable(false);

                            playerPoints = new JTextField(" " + game.getPlayers().get(i).getPoints());
                            setFieldParameters(playerPoints, neonFont.deriveFont(50f), Color.ORANGE, 700 * (i - 2),
                                    550, 650, 60, resultScreenLabel);
                            playerPoints.setEditable(false);
                        }
                    } else {
                        for (int i = 0; i < game.getPlayers().size() / 2; i++) {
                            playerUsername = new JTextField(" " + game.getPlayers().get(i).getUsername());
                            setFieldParameters(playerUsername, neonFont.deriveFont(55f), Color.CYAN, 700 * i, 300,
                                    650, 60, resultScreenLabel);
                            playerUsername.setEditable(false);

                            playerPoints = new JTextField(" " + game.getPlayers().get(i).getThermometerCorrectAnswers());
                            setFieldParameters(playerPoints, neonFont.deriveFont(50f), Color.ORANGE, 700 * i, 350,
                                    650, 60, resultScreenLabel);
                            playerPoints.setEditable(false);
                        }
                        for (int i = game.getPlayers().size() / 2; i < game.getPlayers().size(); i++) {
                            playerUsername = new JTextField(" " + game.getPlayers().get(i).getUsername());
                            setFieldParameters(playerUsername, neonFont.deriveFont(55f), Color.CYAN, 700 * (i - 2),
                                    450, 650, 60, resultScreenLabel);
                            playerUsername.setEditable(false);

                            playerPoints = new JTextField(" " + game.getPlayers().get(i).getThermometerCorrectAnswers());
                            setFieldParameters(playerPoints, neonFont.deriveFont(50f), Color.ORANGE, 700 * (i - 2),
                                    500, 650, 60, resultScreenLabel);
                            playerPoints.setEditable(false);
                        }
                    }
                }
                else{ // Team mode
                    for (int i = 0; i < 2; i++){
                        JTextField teamName = new JTextField(" " + game.getTeams().get(i).getName());
                        setFieldParameters(teamName ,neonFont.deriveFont(55f),Color.CYAN,700*i,300,650,
                                    60, resultScreenLabel);
                        teamName .setEditable(false);
                        game.getTeams().get(i).setPoints(game.getTeams().get(i).getPlayers().get(0).getPoints() +
                                game.getTeams().get(i).getPlayers().get(1).getPoints());
                        if (!thermometer) {
                            JTextField teamPoints = new JTextField(" " + (game.getTeams().get(i).getPoints()));
                            setFieldParameters(teamPoints, neonFont.deriveFont(50f), Color.ORANGE, 700 * i, 350, 650,
                                        60, resultScreenLabel);
                            teamPoints.setEditable(false);
                        }
                        else{
                            int temp = 0;
                            for (Player p : game.getTeams().get(i).getPlayers())
                                temp += p.getThermometerCorrectAnswers();
                            JTextField teamPoints = new JTextField(" " + temp);
                            setFieldParameters(teamPoints, neonFont.deriveFont(50f), Color.ORANGE, 700 * i, 350,
                                        650, 60, resultScreenLabel);
                            teamPoints.setEditable(false);
                        }
                    }
                }
                break;
        }
        delayResults(resultScreenLabel, currentLabel, hasEnded);
    }

    /**
     * Four-second delay between the screen that displays the question results and the next screen.
     * @param currentLabel JLabel of the previous screen.
     * @param newLabel JLabel of the screen that will be displayed after the delay.
     * @param hasEnded Boolean containing whether the game has ended or not.
     */

    public void delayResults(JLabel currentLabel, JLabel newLabel, boolean hasEnded){
        Timer timer = new Timer(5000, e -> {
            if (!hasEnded)
                if (game.getRound().getRoundTypes().size() > 0){
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
     * Five-second delay between the screen that displays the description of the current round and the screen that
     * displays the question and answers.
     * @param currentRound String containing the name of the current round.
     * @param currentLabel JLabel of the previous screen.
     * @param chosenCategory String containing the chosen category.
     * @param chooseCategoryLabel JLabel containing the "Choose Category" screen.
     */

    public void delayRoundType(String currentRound, JLabel currentLabel, String chosenCategory,
                               JLabel chooseCategoryLabel){
        int delay = 5000;
        Timer timer = new Timer( delay, e -> startRound(currentRound, currentLabel, chosenCategory,
                chooseCategoryLabel));
        timer.setRepeats( false );
        timer.start();
    }

    /**
     * Displays the Endgame screen.
     * @param currentLabel JLabel of the previous screen.
     */

    public void endgameScreen(JLabel currentLabel){
        // Displays the endgame Screen.
        JLabel endScreenLabel = new JLabel();
        resizeImage(endScreenLabel,"Backgrounds/Endgame.png");
        changeScene(currentLabel, endScreenLabel);

        // Displays "Game Finished" title.
        JTextArea gameFinishedArea = new JTextArea("Game Finished");
        setAreaParameters(gameFinishedArea, neonFont.deriveFont(80f), Color.orange,415,220,700,60,
                endScreenLabel);

        // Displays each player's or team's points in ranking order.
        if (game.getTeams().get(0).getPlayers().isEmpty()) {
            if (game.getPlayers().size() == 1) {
                JTextField playerUsername = new JTextField(" " + game.getPlayers().get(0).getUsername());
                setFieldParameters(playerUsername, neonFont.deriveFont(50f), Color.CYAN, 270, 280, 800, 60,
                        endScreenLabel);
                playerUsername.setEditable(false);

                JTextField playerPoints = new JTextField(" " + game.getPlayers().get(0).getPoints());
                setFieldParameters(playerPoints, neonFont.deriveFont(50f), Color.CYAN, 270, 320, 800, 60,
                        endScreenLabel);
                playerPoints.setEditable(false);
            }
            else{
                game.sortPlayersByPoints();
                if (game.getPlayers().get(0).getPoints() > game.getPlayers().get(1).getPoints())
                    game.getPlayers().get(0).setMultiplayerWins(game.getPlayers().get(0).getMultiplayerWins() + 1);
                for (int i = 0; i < game.getPlayers().size(); i++) {
                    JTextField playerUsername = new JTextField(" " + game.getPlayers().get(i).getUsername());
                    setFieldParameters(playerUsername, neonFont.deriveFont(50f - 5 * i), Color.CYAN, 270, 280 + 80 * i,
                            800, 60, endScreenLabel);
                    playerUsername.setEditable(false);

                    JTextField playerPoints = new JTextField(" " + game.getPlayers().get(i).getPoints());
                    setFieldParameters(playerPoints, neonFont.deriveFont(50f - 5 * i), Color.ORANGE, 270, 320 + 80 * i,
                            800, 60, endScreenLabel);
                    playerPoints.setEditable(false);
                }
            }
        }
        else{
            game.sortTeamsByPoints();
            if (game.getTeams().get(0).getPoints() > game.getTeams().get(1).getPoints())
                game.getTeams().get(0).setWins(game.getTeams().get(0).getWins() + 1);
            for (int i = 0; i < game.getTeams().size(); i++) {
                JTextField teamName = new JTextField(" " + game.getTeams().get(i).getName());
                setFieldParameters(teamName, neonFont.deriveFont(50f - 5 * i), Color.CYAN, 270, 280 + 80 * i,
                        800, 60, endScreenLabel);
                teamName.setEditable(false);

                JTextField teamPoints = new JTextField(" " + game.getTeams().get(i).getPoints());
                setFieldParameters(teamPoints, neonFont.deriveFont(50f - 5 * i), Color.ORANGE, 270, 320 + 80 * i,
                        800, 60, endScreenLabel);
                teamPoints.setEditable(false);
            }
        }
        try{
            if (game.getTeams().get(0).getPlayers().isEmpty())
                game.getStats().addPlayerStats(game.getPlayers());
            else
                game.getStats().addTeamStats(game.getTeams());
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Creates an "EXIT" button.
        exitButton(endScreenLabel, neonFont.deriveFont(60f), Color.green,965,155,250,100,
                "Backgrounds/Endgame.png","Backgrounds/Endgame.png");

        // Creates an "MENU" button that returns to the main menu screen.
        returnToMainMenuButton(endScreenLabel, neonFont.deriveFont(60f), Color.green,165,155,250,100,
                "Backgrounds/Endgame.png","Backgrounds/Endgame.png");
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

    public void displayQuestionAndAnswers(JTextField question, JTextField answer1, JTextField answer2,
                                          JTextField answer3, JTextField answer4, Question currentQuestion){
        question.setText(currentQuestion.getQuestion());
        answer1.setText("A: " + currentQuestion.getAnswers().get(0));
        answer2.setText("B: " + currentQuestion.getAnswers().get(1));
        answer3.setText("C: " + currentQuestion.getAnswers().get(2));
        answer4.setText("D: " + currentQuestion.getAnswers().get(3));
    }

    /**
     * Displays the betting options.
     * @param question JTextField displaying the BET POINTS FOR THE NEXT QUESTION text.
     * @param betOption1 JTextField containing the field where the first betting option is displayed.
     * @param betOption2 JTextField containing the field where the second betting option is displayed
     * @param betOption3 JTextField containing the field where the third betting option is displayed.
     * @param betOption4 JTextField containing the field where the fourth betting option is displayed.
     */

    public void displayBettingOptions(JTextField question, JTextField betOption1, JTextField betOption2,
                                      JTextField betOption3, JTextField betOption4){
        question.setText("BET POINTS FOR THE NEXT QUESTION");
        betOption1.setText("A: 250");
        betOption2.setText("B: 500");
        betOption3.setText("C: 750");
        betOption4.setText("D: 1000");
    }

    /**
     * Displays the random categories.
     * @param chooseCategoryLabel JLabel containing the "Choose Category" screen.
     * @param category1 JTextField containing the field where the first category is displayed.
     * @param category2 JTextField containing the field where the second category is displayed.
     * @param category3 JTextField containing the field where the third category is displayed.
     * @param category4 JTextField containing the field where the fourth category is displayed.
     * @param randomCategories ArrayList of Strings containing up to 4 categories.
     */

    public void displayRandomCategories(JLabel chooseCategoryLabel, JTextField category1, JTextField category2,
                                        JTextField category3, JTextField category4, ArrayList<String> randomCategories){
        if (randomCategories.get(0).equals("")){
            endgameScreen(chooseCategoryLabel);
        }
        else{
            category1.setText("A: " + randomCategories.get(0));
            category2.setText("B: " + randomCategories.get(1));
            category3.setText("C: " + randomCategories.get(2));
            category4.setText("D: " + randomCategories.get(3));
        }
    }

    /**
     * Creates a "Controls" button at the current label.
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

    public void controlsButton(JLabel currentLabel, Font font, Color color, int x, int y, int width, int height,
                               String buttonDark, String buttonNotDark) {
        JButton controlsButton = new JButton();
        setButtonParameters(controlsButton, font, color, x, y, width, height, currentLabel);
        controlsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (SwingUtilities.isLeftMouseButton(e)) {
                    currentLabel.setIcon(new ImageIcon(buttonNotDark));
                    resizeImage(currentLabel,buttonDark);
                    viewControls(currentLabel);
                }
            }
            // Adds hovering effect to the Controls button.
            public void mouseEntered (MouseEvent e){ resizeImage(currentLabel,buttonDark);}
            public void mouseExited (MouseEvent e){ resizeImage(currentLabel,buttonNotDark);}
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

    public void exitButton(JLabel currentLabel, Font font, Color color, int x, int y, int width, int height,
                           String buttonDark, String buttonNotDark ){
        JButton exitButton = new JButton();
        if (font != null)
            exitButton.setText("EXIT");
        setButtonParameters(exitButton, font, color, x, y, width, height, currentLabel);
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (SwingUtilities.isLeftMouseButton(e))
                    System.exit(0);
            }
            // Adds hovering effect to the Exit button.
            public void mouseEntered(MouseEvent e){
                resizeImage(currentLabel,buttonDark);
            }
            public void mouseExited(MouseEvent e){
                resizeImage(currentLabel,buttonNotDark);
            }
        });
    }

    /**
     * Creates a back button at the current label.
     * @param currentLabel JLabel containing the current button's label.
     * @param previousLabel JLabel containing the previous label.
     * @param previousField JTextField of the previous scene so that the back button, if pressed, can restore its
     *                      focus.
     * @param font Font containing the current button's font.
     * @param color Color containing the current button's color.
     * @param x Integer containing the current button's x-axis coordinates.
     * @param y Integer containing the current button's y-axis coordinates.
     * @param width Integer containing the current button's width.
     * @param height Integer containing the current button's height.
     * @param buttonDark String containing the label's icon when the mouse is over the button.
     * @param buttonNotDark String containing the label's icon when the mouse is not over the button.
     * @param toClear String containing whether going back will clear only the player controls or all the players
     *                entirely.
     */

    public void backButton(JLabel currentLabel, JLabel previousLabel, JTextField previousField, Font font, Color color,
                           int x, int y, int width, int height, String buttonDark, String buttonNotDark, String toClear)
    {
        JButton backButton = new JButton();
        if (font != null)
            backButton.setText("BACK");
        setButtonParameters(backButton, font, color, x, y, width, height, currentLabel);
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (SwingUtilities.isLeftMouseButton(e)){
                    switch (toClear){
                        case "ALL":
                            game.getPlayers().clear();
                            break;
                        case "CONTROLS":
                            for (Player p : game.getPlayers())
                                p.clearControls();
                            break;
                        case "TEAMS":
                            for (Team t : game.getTeams())
                                t.getPlayers().clear();
                            for (Player p : game.getPlayers())
                                p.clearControls();
                            break;
                        case "TEAM_ALL":
                            for (Team t : game.getTeams())
                                t.getPlayers().clear();
                            game.getPlayers().clear();
                            break;
                    }
                    changeScene(currentLabel, previousLabel);
                    if (previousField != null)
                        previousField.requestFocusInWindow();
                }
            }
            // Adds hovering effect to the Back button.
            public void mouseEntered (MouseEvent e){ resizeImage(currentLabel,buttonDark);}
            public void mouseExited (MouseEvent e){ resizeImage(currentLabel,buttonNotDark);}
        });
    }
    /**
     * Creates an "MENU" button that returns to the main menu screen at the current label.
     * @param currentLabel JLabel containing the current button's label.
     * @param font Font containing the current button's font.
     * @param color Color containing the current button's color.
     * @param x Integer containing the current button's x-axis coordinates.
     * @param y Integer containing the current button's y-axis coordinates.
     * @param width Integer containing the current button's width.
     * @param height Integer containing the current button's height.
     * @param buttonDark String containing the label's icon when the mouse is over the button.
     * @param buttonNotDark String containing the label's icon when the mouse is not over the button.
     */

    public void returnToMainMenuButton(JLabel currentLabel, Font font, Color color,
                           int x, int y, int width, int height, String buttonDark, String buttonNotDark)
    {
        JButton returnButton = new JButton();
        if (font != null)
            returnButton.setText("MENU");
        setButtonParameters(returnButton, font, color, x, y, width, height, currentLabel);
        returnButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (SwingUtilities.isLeftMouseButton(e)) {
                    game.getPlayers().clear();
                    game.getTeams().clear();
                    changeScene(currentLabel, mainLabel);
                }
            }
            // Adds hovering effect to the "MENU" button.
            public void mouseEntered (MouseEvent e){ resizeImage(currentLabel, buttonDark);}
            public void mouseExited (MouseEvent e){ resizeImage(currentLabel, buttonNotDark);}
        });
    }

    /**
     * Restores focus to the current label if one of its components is clicked on.
     * @param currentComponent JComponent containing the component that requests focus if the user click elsewhere.
     * @param clickedComponent JComponent containing the clicked component.
     */

    public void requestFocusIfClicked(JComponent currentComponent, JComponent clickedComponent){
        clickedComponent.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                currentComponent.requestFocus();
            }
        });
    }

    /**
     * Sets the size of the background image equal to the screen size.
     * @param currentLabel JLabel containing the current label.
     * @param imageName String containing the name of the current image file.
     */

    public void resizeImage(JLabel currentLabel, String imageName){
        ImageIcon a = new ImageIcon(imageName);
        Image image = a.getImage();
        Image mainImage = image.getScaledInstance(window.getWidth(), window.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon finalImage = new ImageIcon(mainImage);
        currentLabel.setIcon(finalImage);
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

    public void setButtonParameters(JButton currentButton, Font font, Color color, int x, int y, int width, int height,
                                    JLabel currentLabel){
        setMainComponentParameters(currentButton, font, color, x, y, width, height, currentLabel);
        currentButton.setContentAreaFilled(false);
        currentButton.setBorderPainted(false);
        currentButton.setFocusPainted(false);
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

    public void setFieldParameters(JTextField currentField, Font font, Color color, int x, int y, int width, int height,
                                   JLabel currentLabel){
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

    public void setAreaParameters(JTextArea currentArea, Font font, Color color, int x, int y, int width, int height,
                                  JLabel currentLabel){
        setMainComponentParameters(currentArea, font, color, x, y, width, height, currentLabel);
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

    public void setMainComponentParameters(JComponent currentComponent, Font font, Color color, int x, int y, int width,
                                           int height, JLabel currentLabel){
        currentComponent.setFont(font);
        currentComponent.setForeground(color);
        currentComponent.setBounds(x, y, width, height);
        currentComponent.setOpaque(false);
        currentLabel.add(currentComponent);
    }

    /**
     * Changes from one scene into another.
     * @param currentLabel JLabel of the current scene.
     * @param newLabel JLabel of the new scene.
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