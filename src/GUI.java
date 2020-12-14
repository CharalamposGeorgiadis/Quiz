import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.io.File;
import java.io.IOException;


public class GUI {
    private JPanel panelMain;
    private JFrame window;
    private ImageIcon mainMenu;
    private JLabel mainLabel;
    private JButton exitButton;
    private JButton startButton;
    private JButton leaderboardButton;
    private Font neonFont;

    public GUI() {

        try{
            neonFont = Font.createFont(Font.TRUETYPE_FONT,new File("neon2.ttf")).deriveFont(60f);
            GraphicsEnvironment g = GraphicsEnvironment.getLocalGraphicsEnvironment();
            g.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("neon2.ttf")));
        }
        catch (IOException|FontFormatException e){
        }

        mainMenu = new ImageIcon("MainMenu.png");
        window = new JFrame("Buzz");
        window.setSize(970,550);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainLabel = new JLabel(mainMenu);
        mainLabel.setSize(970,550);
        window.add(mainLabel);
        JButton exitButton = new JButton();
        exitButton.setBounds(714,95,105,105);
        //make button area invisible
        exitButton.setContentAreaFilled(false);
        exitButton.setBorderPainted(false);
        exitButton.addMouseListener(new MouseAdapter() { //exits the app when pressed
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                window.dispose();
            }
        });
        JButton startButton = new JButton("START GAME");
        startButton.setBounds(225,205,500,60);
        startButton.setContentAreaFilled(false);
        startButton.setBorderPainted(false);
        startButton.setOpaque(false);

        startButton.setFont(neonFont);
        startButton.setForeground(Color.orange);




        JButton leaderboardButton = new JButton("LEADERBOARDS");
        leaderboardButton.setBounds(225,295,500,60);
        leaderboardButton.setContentAreaFilled(false);
        leaderboardButton.setBorderPainted(false);

        leaderboardButton.setFont(neonFont);
        leaderboardButton.setForeground(Color.orange);

        mainLabel.add(exitButton);
        mainLabel.add(startButton);
        mainLabel.add(leaderboardButton);

    }
    public void startGUI() {
        window.setVisible(true);


    }
}