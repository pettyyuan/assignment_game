import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;

public class HomePage extends GameEngine {
    //group:23
    //member:22009158 Wenyuan Tian
    //member:22009155 Xinjie Zhang
    //member:22009156 Zihan Zhao

    Image home;
    Font buttonfont = new Font("Times New Roman",Font.BOLD,20);
    AudioClip background;
    public HomePage() {
        mPanel = new GameEngine.GamePanel();
        createButton();

    }


    public void init() {
        super.init();
        setWindowSize(1000, 660);
        background = loadAudio("src/Music/backgroundmusic.wav");
        startAudioLoop(background);
        home = loadImage("src/Image/background4.jpg");
    }



    public void update(double dt) {
        createButton();

    }

    public void paintComponent() {
        clearBackground(1000,660);
        drawImage(home, 0, 0, 1000,660);
        changeColor(white);
        drawBoldText(220,200,"JumpQuest","Times New Roman",120);

    }

    public void start() {
        createGame(this, 50);
    }
    private void createButton() {

            JButton start1Button = new JButton("Play");
            JButton exitButton = new JButton("Quit");
            JButton helpButton = new JButton("Help");
            setButton(start1Button);
            setButton(helpButton);
            setButton(exitButton);
            JPanel buttonPanel = new JPanel();
            start1Button.addActionListener(e -> {
                PlayPage playPage = new PlayPage();
                playPage.start();
                mFrame.dispose();
                stopAudioLoop(background);
            });

            exitButton.addActionListener(e ->
                    System.exit(0));

            helpButton.addActionListener(e -> {
                HelpPage helpPage = new HelpPage();
                helpPage.start();
                mFrame.dispose();
                stopAudioLoop(background);
                //game rules
            });
            mPanel.setLayout(null);
            buttonPanel.setLayout(null);
            buttonPanel.setLayout(new GridLayout(3, 1, 0, 30));
            buttonPanel.setBounds(450, 300, 100, 250);
                buttonPanel.add(start1Button);
                buttonPanel.add(helpButton);
                buttonPanel.add(exitButton);
            buttonPanel.setOpaque(false);



            mPanel.setPreferredSize(new Dimension(100, 50));
            mPanel.add(buttonPanel);
            mPanel.setVisible(true);
        }



    public void setButton(JButton button){
        button.setPreferredSize(new Dimension(100,50));
        button.setContentAreaFilled(false);
        button.setFont(buttonfont);
        button.setFocusable(false);
        button.setForeground(yellow);
        button.setBorder(null);
        button.setBorder(BorderFactory.createRaisedBevelBorder());
        button.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBorder(BorderFactory.createLoweredBevelBorder());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBorder(BorderFactory.createRaisedBevelBorder());
            }
        });
    }
    public void openStartPanel() {
        //   mFrame.remove(mPanel);
        mFrame.add(mPanel);

        mFrame.revalidate();
        mFrame.repaint();
    }

}
