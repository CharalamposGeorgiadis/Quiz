import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;


public class GUI {
    private JFrame window;
    private ImageIcon mainMenu;
    private JLabel mainLabel;
    private JButton exitButton;
    private JButton startButton;
    private JButton leaderboardButton;
    private Font neonFont;

    public GUI() throws IOException, FontFormatException {

        neonFont = Font.createFont(Font.TRUETYPE_FONT,new File("neon2.ttf")).deriveFont(60f);
        GraphicsEnvironment g = GraphicsEnvironment.getLocalGraphicsEnvironment();
        g.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("neon2.ttf")));

        window = new JFrame("Buzz");
        window.setSize(970,550);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        mainMenu = new ImageIcon("MainMenu.png");
        mainLabel = new JLabel(mainMenu);
        mainLabel.setSize(970,550);
        window.add(mainLabel);

        JButton exitButton = new JButton();
        exitButton.setBounds(698,75,140,140);
        //make button area invisible
        exitButton.setContentAreaFilled(false);
        exitButton.setBorderPainted(false);
        exitButton.setOpaque(false);
        exitButton.addMouseListener(new MouseAdapter() { //exits the app when pressed
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (SwingUtilities.isLeftMouseButton(e))
                    window.dispose();
            }
        });
        JButton startButton = new JButton("START GAME");
        startButton.setBounds(225,205,500,60);
        startButton.setContentAreaFilled(false);
        startButton.setBorderPainted(false);
        startButton.setFont(neonFont);
        startButton.setForeground(Color.orange);
        startButton.addMouseListener(new MouseAdapter() { //exits the app when pressed
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (SwingUtilities.isLeftMouseButton(e))
                    startGame();
            }
        });

        JButton leaderboardButton = new JButton("LEADERBOARDS");
        leaderboardButton.setBounds(225,295,500,60);
        leaderboardButton.setContentAreaFilled(false);
        leaderboardButton.setBorderPainted(false);
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

        leaderboardButton.setFont(neonFont);
        leaderboardButton.setForeground(Color.orange);

        mainLabel.add(exitButton);
        mainLabel.add(startButton);
        mainLabel.add(leaderboardButton);

    }


    public void viewLeaderboards() throws IOException {
        window.remove(mainLabel);

        JTextArea leaderboardTitle = new JTextArea("LEADERBOARDS");
        leaderboardTitle.setOpaque(false);
        leaderboardTitle.setFont(neonFont.deriveFont(50f));
        leaderboardTitle.setForeground(Color.orange);
        leaderboardTitle.setBounds(310, 90, 350, 50);
        leaderboardTitle.setEditable(false);
        window.add(leaderboardTitle);

        JTextArea leaderboardArea = new JTextArea();
        leaderboardArea.setOpaque(false);
        leaderboardArea.setFont(neonFont.deriveFont(30f));
        leaderboardArea.setForeground(Color.cyan);
        leaderboardArea.setBounds(110, 150, 720, 275);
        leaderboardArea.setEditable(false);

        JScrollPane scroll=new JScrollPane(leaderboardArea);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(110, 150, 720, 280);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);



        try {
            BufferedReader stats = new BufferedReader(new FileReader("PLAYER STATS.txt"));
            leaderboardArea.read(stats, null);
            stats.close();
            leaderboardArea.requestFocus();
        }
        catch(FileNotFoundException s){
            leaderboardArea.setText("\n                   STATS FILE NOT FOUND\n");
            leaderboardArea.append((" Please check if your game folder contains:\n"));
            leaderboardArea.append("\n                      PLAYER STATS.TXT\n");
        }


        JLabel leaderboardBackground = new JLabel((new ImageIcon("LeaderBoard.png")));
        leaderboardBackground.add(scroll);


        window.add(leaderboardBackground);
        window.revalidate();
        window.repaint();

        JButton backButton = new JButton("BACK");
        backButton.setBounds(90, 78, 140, 50);
        backButton.setFont(neonFont.deriveFont(40f));
        backButton.setForeground(Color.red);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        leaderboardBackground.add(backButton);
        backButton.addMouseListener(new MouseAdapter() { //exits the app when pressed
            @Override
            public void mouseClicked(MouseEvent e) {
                window.remove(leaderboardBackground);
                window.remove(leaderboardTitle);
                window.add(mainLabel);
                window.revalidate();
                window.repaint();
            }
        });
    }

    public void startGame(){
        window.remove(mainLabel);
        JLabel choosePlayersLabel = new JLabel((new ImageIcon("MainMenu.png")));
        JTextArea choosePlayersTitle = new JTextArea("CHOOSE NUMBER" +"\n    OF PLAYERS");
        choosePlayersTitle.setOpaque(false);
        choosePlayersTitle.setFont(neonFont.deriveFont(40f));
        choosePlayersTitle.setForeground(Color.orange);
        choosePlayersTitle.setBounds(290, 150, 350, 80);
        choosePlayersTitle.setEditable(false);
        window.add(choosePlayersTitle);

        window.add(choosePlayersLabel);
        window.revalidate();
        window.repaint();

    }





    public void startGUI() {
        window.setVisible(true);
    }
}