import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.Random;


public class GUI {
    private JFrame window;
    private ImageIcon mainMenu;
    private JLabel mainLabel;
    private Font neonFont;
    private Game game;

    public GUI(File[] questions) throws IOException, FontFormatException {

        //Creates the Main frame of the game.
        window = new JFrame("Buzz");
        window.setSize(970, 550);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        //Displays the Main Menu screen.
        mainMenu = new ImageIcon("MainMenu.png");
        mainLabel = new JLabel(mainMenu);
        window.add(mainLabel);

        //Creates Exit button.
        exitButton(mainLabel, 698, 75, 140, 140, "MainMenuDark.png", "MainMenu.png");

        //Adds custom font from file.
        neonFont = Font.createFont(Font.TRUETYPE_FONT, new File("neon2.ttf")).deriveFont(60f);
        GraphicsEnvironment g = GraphicsEnvironment.getLocalGraphicsEnvironment();
        g.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("neon2.ttf")));

        if (questions != null) {
            game = new Game(questions);//Loads the questions into the game.

            //Creates the Start Game button.
            JButton startButton = new JButton("START GAME");
            setButtonParameters(startButton,neonFont.deriveFont(60f),Color.orange,225,205,500,60, mainLabel);
            startButton.addMouseListener(new MouseAdapter() { //exits the app when pressed
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    if (SwingUtilities.isLeftMouseButton(e))
                        chooseNumberOfPlayers();
                }
            });

            //Creates the Leaderboards button.
            JButton leaderboardButton = new JButton("LEADERBOARDS");
            setButtonParameters(leaderboardButton,neonFont.deriveFont(60f),Color.orange,225,295,500,60, mainLabel);
            leaderboardButton.addMouseListener(new MouseAdapter() { //exits the app when pressed
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    try {
                        if (SwingUtilities.isLeftMouseButton(e))
                            viewLeaderboards();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            });
        }
        else{
            JTextArea noFileFound=new JTextArea("BUZZ QUESTIONS FILE\n   WAS NOT FOUND");
            setAreaParameters(noFileFound,neonFont.deriveFont(50f),Color.ORANGE,200,250,600,200, mainLabel);
        }
    }

    public void viewLeaderboards() throws IOException {
        //Displays the Leaderboard screen.
        JLabel leaderboardBackground = new JLabel((new ImageIcon("LeaderBoard.png")));
        changeScene(mainLabel,leaderboardBackground);

        //Adds a "LEADERBOARDS" title to the label.
        JTextArea leaderboardTitle = new JTextArea("LEADERBOARDS");
        setAreaParameters(leaderboardTitle,neonFont.deriveFont(50f),Color.ORANGE,310,90,360,50, leaderboardBackground);

        //Adds the Area where the leaderboards will be displayed.
        JTextArea leaderboardArea = new JTextArea();
        setAreaParameters(leaderboardArea,neonFont.deriveFont(30f),Color.cyan,110,150,735,279, leaderboardBackground);

        try {
            BufferedReader stats = new BufferedReader(new FileReader("PLAYER STATS.txt"));
            leaderboardArea.read(stats, null);
            stats.close();
            leaderboardArea.requestFocus();
        }
        catch(FileNotFoundException s){
            leaderboardArea.setText("\n                   STATS FILE NOT FOUND\n");
        }

        //Adds Scrolling function in the leaderboard display area.
        JScrollPane scroll=new JScrollPane(leaderboardArea);
        setScrollPaneParameters(scroll,110,150,735,279);
        leaderboardBackground.add(scroll);

        //Adds a Back button in the Leaderboard screen.
        JButton backButton = new JButton("BACK");
        setButtonParameters(backButton,neonFont.deriveFont(40f),Color.red,90,78,140,50,leaderboardBackground);
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (SwingUtilities.isLeftMouseButton(e)) {
                    window.remove(leaderboardTitle);
                    changeScene(leaderboardBackground,mainLabel);
                }
            }
        });
    }

    public void chooseNumberOfPlayers(){
        //Displays the "Choose Number of Players" screen.
        JLabel choosePlayersLabel = new JLabel((new ImageIcon("SetControls.png")));

        //Displays the "Choose Number of Players" title text.
        JTextArea choosePlayersTitle = new JTextArea("CHOOSE NUMBER" +"\n    OF PLAYERS");
        setAreaParameters(choosePlayersTitle,neonFont.deriveFont(40f),Color.ORANGE,290,150,350,80, choosePlayersLabel);

        //Adds a Back button in the "Choose Number of Players" screen.

        JButton backButton = new JButton();
        setButtonParameters(backButton,null,null,698,75,140,140, choosePlayersLabel);
        backButton.addMouseListener(new MouseAdapter() { //exits the app when pressed
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (SwingUtilities.isLeftMouseButton(e)) {
                    for (Player p:game.getPlayers())
                        p.clearControls();
                    changeScene(choosePlayersLabel,mainLabel);
                }
            }
            public void mouseEntered(MouseEvent e){
                choosePlayersLabel.setIcon(new ImageIcon("SetControlsDark.png"));
            }
            public void mouseExited(MouseEvent e){
                choosePlayersLabel.setIcon(new ImageIcon("SetControls.png"));
            }
        });

        changeScene(mainLabel,choosePlayersLabel);

        //Creates the TextField where the player(s) enter their chosen number of players.
        JTextField chooseNumber= new JTextField();
        setFieldParameters(chooseNumber,neonFont.deriveFont(50f),Color.cyan,400,300,110,80, choosePlayersLabel);
        chooseNumber.requestFocusInWindow(); // Makes the cursor appear instantly at the textField.

        //Creates an Area which will display the message "Invalid Number" if a player enters an invalid number of players.
        JTextArea invalidNumber = new JTextArea("");
        setAreaParameters(invalidNumber,neonFont.deriveFont(30f),Color.ORANGE,250,270,400,40,choosePlayersLabel);
        chooseNumber.addActionListener(e -> {
            switch(chooseNumber.getText()) {
                case "1":
                    chooseNumber.setText("");
                    invalidNumber.setText("");
                    enterUsername(1, choosePlayersLabel);
                    break;
                case "2":
                    chooseNumber.setText("");
                    invalidNumber.setText("");
                    enterUsername(2, choosePlayersLabel);
                    break;
                default:
                    chooseNumber.setText("");
                    invalidNumber.setText("Invalid Number of Players");
            }
        });
    }


    public void enterUsername(int numberOfPlayers, JLabel currentLabel){
        final int[] currentPlayer = {1};
        //Displays the "Enter Username" screen.
        JLabel usernameLabel= new JLabel(new ImageIcon("EnterUsername.png"));

        JButton backButton = new JButton();
        setButtonParameters(backButton,null,null,698,75,140,140, usernameLabel);
        backButton.addMouseListener(new MouseAdapter() { //exits the app when pressed
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (SwingUtilities.isLeftMouseButton(e)) {
                    game.clearPlayers();
                    changeScene(usernameLabel, currentLabel);
                }
            }
            public void mouseEntered(MouseEvent e){
                usernameLabel.setIcon(new ImageIcon("EnterUsernameDark.png"));
            }
            public void mouseExited(MouseEvent e){
                usernameLabel.setIcon(new ImageIcon("EnterUsername.png"));
            }
        });

        //Creates the "Enter Username For.." title text.
        JTextArea enterUsernameTitle=new JTextArea("ENTER USERNAME" +"\n        FOR:"+"\n      PLAYER "+ currentPlayer[0]+"\n");
        setAreaParameters(enterUsernameTitle,neonFont.deriveFont(40f),Color.ORANGE,290,150,350,100, usernameLabel);

        changeScene(currentLabel,usernameLabel);

        //Creates the TextField where the player(s) enter their chosen usernames.
        JTextField enterUsername=new JTextField();
        setFieldParameters(enterUsername,neonFont.deriveFont(40f),Color.cyan,290,345,320,85, usernameLabel);
        enterUsername.requestFocusInWindow(); // Makes the cursor appear instantly at the textField.

        //Creates the Area where "(Max characters: 14)" will be displayed.
        JTextArea maxCharactersArea=new JTextArea("(Max characters: 14)");
        setAreaParameters(maxCharactersArea,neonFont.deriveFont(30f),Color.ORANGE,290,250,350,45, usernameLabel);

        //Creates the Area where "Invalid username" will be displayed.
        JTextArea invalidUsername=new JTextArea("");
        setAreaParameters(invalidUsername,neonFont.deriveFont(30f),Color.orange,250,300,450,45, usernameLabel);

        enterUsername.addActionListener(e -> {
            switch (game.enterUsernames(enterUsername.getText(), currentPlayer[0]-1)) {
                case -1:
                    enterUsername.setText("");
                    invalidUsername.setText(" USERNAME ALREADY CHOSEN");
                    break;
                case 0:
                    enterUsername.setText("");
                    invalidUsername.setText("       INVALID USERNAME");
                    break;
                case 1:
                    enterUsername.setText("");
                    invalidUsername.setText("");
                    currentPlayer[0]++;
                    enterUsernameTitle.setText("ENTER USERNAME" + "\n        FOR:" + "\n      PLAYER " + currentPlayer[0] + "\n");
                    if (currentPlayer[0] > numberOfPlayers) {
                        currentPlayer[0]=1;
                        enterUsernameTitle.setText("ENTER USERNAME" + "\n        FOR:" + "\n      PLAYER " + currentPlayer[0] + "\n");
                        setControls(usernameLabel);
                    }
            }
        });
    }

    public void setControls(JLabel currentLabel){
        //Displays the "Set Controls" screen.
        JLabel setControlsLabel=new JLabel(new ImageIcon("SetControls.png"));
        changeScene(currentLabel,setControlsLabel);

        //Creates the "Set Controls For.." title text.
        JTextArea setControlArea= new JTextArea(" Set control\n       for");
        setAreaParameters(setControlArea,neonFont.deriveFont(40f),Color.ORANGE,320,150,300,70, setControlsLabel);

        //Displays the username of the player who is currently setting their controls.
        JTextField usernameField= new JTextField(game.getPlayers().get(0).getUsername());
        setFieldParameters(usernameField,neonFont.deriveFont(40f),Color.orange,200,230,500,50,setControlsLabel);
        usernameField.setBorder(javax.swing.BorderFactory.createEmptyBorder());//Removes the JTextField's borders.
        usernameField.setEditable(false);
        setControlsLabel.add(usernameField);

        //Creates the TextField where the player(s) enter their chosen controls.
        JTextField setControlField= new JTextField();
        setFieldParameters(setControlField,neonFont.deriveFont(50f),Color.cyan,400,300,110,80, setControlsLabel);
        setControlField.requestFocusInWindow(); // Makes the cursor appear instantly at the textField.

        //Adds a Back button in the "Set Controls" screen.
        JButton backButton = new JButton();
        setButtonParameters(backButton,null,null,698,75,140,140, setControlsLabel);
        backButton.addMouseListener(new MouseAdapter() { //exits the app when pressed
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (SwingUtilities.isLeftMouseButton(e)) {
                    game.clearPlayers();
                    changeScene(setControlsLabel, currentLabel);
                }
            }
            public void mouseEntered(MouseEvent e){
                setControlsLabel.setIcon(new ImageIcon("SetControlsDark.png"));
            }
            public void mouseExited(MouseEvent e){
                setControlsLabel.setIcon(new ImageIcon("SetControls.png"));
            }
        });

        //Displays the control that is currently set by the player
        JTextArea currentControlArea=new JTextArea("A:");
        final int[] currentControlNumber = {0};
        final int[] currentPlayer = {0};
        setAreaParameters(currentControlArea,neonFont.deriveFont(50f),Color.orange,330,320,80,80, setControlsLabel);

        /*Creates an Area which will display the message "Invalid Control" if a player enters an invalid control
          or "Control already bound" if the chosen control has already been chosen.*/
        JTextArea invalidControl = new JTextArea("");
        setAreaParameters(invalidControl,neonFont.deriveFont(30f),Color.ORANGE,250,270,450,40, setControlsLabel);

        setControlField.addActionListener(e -> {
            switch(game.setControls(setControlField.getText(), currentPlayer[0],currentControlNumber[0])) {
                case -1:
                    setControlField.setText("");
                    invalidControl.setText("    Control already bound");
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
                        case 0:
                            currentControlArea.setText("A:");
                            break;
                        case 1:
                            currentControlArea.setText("B:");
                            break;
                        case 2:
                            currentControlArea.setText("C:");
                            break;
                        case 3:
                            currentControlArea.setText("D:");
                            break;
                        default:
                            currentControlNumber[0]=0;
                            currentPlayer[0]++;
                            if(currentPlayer[0]==game.getPlayers().size()) {
                                currentControlArea.setText("A:");
                                currentPlayer[0]=0;
                                usernameField.setText(game.getPlayers().get(currentPlayer[0]).getUsername());
                                youCanView(setControlsLabel);
                            }
                            else{
                                usernameField.setText(game.getPlayers().get(currentPlayer[0]).getUsername());
                                currentControlArea.setText("A:");
                            }
                            break;
                    }
            }
        });
    }

    public void youCanView(JLabel currentLabel){
        //Display the "You can view each player's controls by clicking:.." screen.
        JLabel youCanViewLabel= new JLabel(new ImageIcon("YouCanView.png"));
        changeScene(currentLabel, youCanViewLabel);

        //Display the area where "You can view each player's controls by clicking:.." is displayed.
        JTextArea youCanViewArea=new JTextArea("         You can view\n each player's controls\n           by clicking:");
        setAreaParameters(youCanViewArea,neonFont.deriveFont(50f),Color.ORANGE,150,100,700,150, youCanViewLabel);

        //Adds the "OK" button which progresses the game to the next screen.
        JButton nextButton= new JButton("OK");
        setButtonParameters(nextButton,neonFont.deriveFont(50f),Color.ORANGE,410,370,140,60, youCanViewLabel);
        nextButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (SwingUtilities.isLeftMouseButton(e)) {
                    chooseCategory(youCanViewLabel);
                }
            }
        });

        //Adds a back button to this screen.
        JButton backButton = new JButton();
        setButtonParameters(backButton,null,null,0,0,100,100, youCanViewLabel);
        backButton.addMouseListener(new MouseAdapter() { //exits the app when pressed
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (SwingUtilities.isLeftMouseButton(e)) {
                    for (Player p:game.getPlayers()) {
                        p.clearControls();
                    }
                    changeScene(youCanViewLabel, currentLabel);
                }
            }
            public void mouseEntered(MouseEvent e){
                youCanViewLabel.setIcon(new ImageIcon("YouCanViewDarkBack.png"));
            }
            public void mouseExited(MouseEvent e){
                youCanViewLabel.setIcon(new ImageIcon("YouCanView.png"));
            }
        });

        //Adds an exit button to this screen.
        exitButton(youCanViewLabel,855,0,100,100, "YouCanViewDarkX.png","YouCanView.png" );
    }



    public void chooseCategory(JLabel currentLabel){
        //Displays the "Choose Category" screen.
        JLabel chooseCategoryLabel=new JLabel(new ImageIcon("ChooseCategory.png"));
        changeScene(currentLabel,chooseCategoryLabel);

        //Add the button that allows the player(s) to view their controls.
        JButton controlsButton = new JButton();
        setButtonParameters(controlsButton,null,null,0,0,100,100, chooseCategoryLabel);
        controlsButton.addMouseListener(new MouseAdapter() { //exits the app when pressed
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (SwingUtilities.isLeftMouseButton(e)) {
                    chooseCategoryLabel.setIcon(new ImageIcon("ChooseCategory.png"));
                    viewControls(chooseCategoryLabel);
                }
            }
            public void mouseEntered(MouseEvent e){
                chooseCategoryLabel.setIcon(new ImageIcon("ChooseCategoryDarkC.png"));
            }
            public void mouseExited(MouseEvent e){
                chooseCategoryLabel.setIcon(new ImageIcon("ChooseCategory.png"));
            }
        });

        //Adds an exit button to this screen.
        exitButton(chooseCategoryLabel,855,0,100,100, "ChooseCategoryDarkX.png","ChooseCategory.png" );
        String randomCategories[]=game.randomCategories();

        //Creates the field where "Player x choose a category" will be displayed.
        JTextField chooseCategoryField= new JTextField();
        setFieldParameters(chooseCategoryField, neonFont.deriveFont(35f),Color.ORANGE,80,247,800,50, chooseCategoryLabel);
        chooseCategoryField.setEditable(false);
        Random rand= new Random();
        int randPlayer=rand.nextInt(game.getPlayers().size());
        chooseCategoryField.setText(game.getPlayers().get(randPlayer).getUsername()+" choose a category");

        for (int i=0; i<4;i++){
            JButton category = new JButton(randomCategories[i]);
            switch (i){
                case 0:
                    setButtonParameters(category, neonFont.deriveFont(40f),Color.yellow,15,323,420,60, chooseCategoryLabel);
                    break;
                case 1:
                    setButtonParameters(category, neonFont.deriveFont(40f),Color.cyan,520,323,420,60, chooseCategoryLabel);
                    break;
                case 2:
                    setButtonParameters(category, neonFont.deriveFont(40f),Color.blue,15,433,420,60, chooseCategoryLabel);
                    break;
                case 3:
                    setButtonParameters(category, neonFont.deriveFont(40f),Color.magenta,520,433,420,60, chooseCategoryLabel);
                    break;
            }
        }

    }

    public void viewControls(JLabel currentLabel){
        //Displays the "View Controls" screen.
        JLabel viewControlsLabel=new JLabel(new ImageIcon("LeaderBoard.png"));
        changeScene(currentLabel, viewControlsLabel);

        //Displays the "CONTROLS" title.
        JTextArea viewControlsTitle = new JTextArea("CONTROLS");
        setAreaParameters(viewControlsTitle,neonFont.deriveFont(50f),Color.ORANGE,310,90,360,50, viewControlsLabel);

        //Adds the Area where the controls will be displayed.
        JTextArea controlsArea = new JTextArea("    ");
        setAreaParameters(controlsArea,neonFont.deriveFont(50f),Color.cyan,110,150,720,275, viewControlsLabel);

        for (int i=1; i<game.getPlayers().size()+1; i++){
            if (i<game.getPlayers().size()+1)
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
        setScrollPaneParameters(scroll,110,150,720,280);
        viewControlsLabel.add(scroll);

        //Adds a Back button in the Controls screen.
        JButton backButton = new JButton("BACK");
        setButtonParameters(backButton,neonFont.deriveFont(40f),Color.red,60,78,200,50, viewControlsLabel);
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (SwingUtilities.isLeftMouseButton(e)) {
                    window.remove(viewControlsLabel);
                    changeScene(viewControlsLabel,currentLabel);
                }
            }
        });
    }

    public void exitButton(JLabel currentLabel,int x, int y, int width, int height, String buttonDark, String buttonNotDark ){
        JButton exitButton = new JButton();
        setButtonParameters(exitButton,null,null,x,y,width,height,currentLabel);
        exitButton.addMouseListener(new MouseAdapter() { //exits the app when pressed
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (SwingUtilities.isLeftMouseButton(e))
                    window.dispose();
            }
            //Adds hovering effect to the Exit button.
            public void mouseEntered(MouseEvent e){
                currentLabel.setIcon(new ImageIcon(buttonDark));
            }
            public void mouseExited(MouseEvent e){
                currentLabel.setIcon(new ImageIcon(buttonNotDark));
            }
        });
        currentLabel.add(exitButton);
    }

    public void setScrollPaneParameters(JScrollPane scroll,int x, int y, int width,int height){
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(x, y, width, height);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(null);
    }

    public void setButtonParameters(JButton currentButton,Font font, Color color,int x,int y,int width,int height, JLabel currentLabel){
        currentButton.setFont(font);
        currentButton.setForeground(color);
        currentButton.setBounds(x,y,width,height);
        currentButton.setContentAreaFilled(false);
        currentButton.setBorderPainted(false);
        currentButton.setHorizontalAlignment(SwingConstants.CENTER);
        currentLabel.add(currentButton);

    }

    public void setFieldParameters(JTextField currentField, Font font, Color color,int x,int y,int width,int height, JLabel currentLabel){
        currentField.setFont(font);
        currentField.setForeground(color);
        currentField.setBounds(x,y,width,height);
        currentField.setBorder(null);
        currentField.setHorizontalAlignment(JTextField.CENTER);
        currentField.setOpaque(false);
        currentLabel.add(currentField);
    }

    public void setAreaParameters(JTextArea currentArea, Font font,Color color, int x, int y, int width, int height, JLabel currentLabel){
        currentArea.setFont(font);
        currentArea.setForeground(color);
        currentArea.setBounds(x, y, width, height);
        currentArea.setOpaque(false);
        currentArea.setEditable(false);
        currentLabel.add(currentArea);
    }

    public void changeScene(JLabel currentLabel, JLabel newLabel){
        window.remove(currentLabel);
        window.add(newLabel);
        window.revalidate();
        window.repaint();
    }

    public void startGUI() {
        window.setVisible(true);
    }
}
