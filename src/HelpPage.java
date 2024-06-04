import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import javax.swing.JButton;
import javax.swing.JPanel;
public class HelpPage extends GameEngine{
    //group:23
    //member:22009158 Wenyuan Tian
    //member:22009155 Xinjie Zhang
    //member:22009156 Zihan Zhao
    Point2D monster = new Point2D.Double();
    Image play_background;
    int currentFrame_monster;
    double animTime;
    Image[] frames_monster;

    Image monster_image;
    Image spine;
    Image knife;
    AudioClip background;

    public HelpPage(){
        mPanel = new GameEngine.GamePanel();
    }
    public void init() {
        super.init();
        setWindowSize(1000,660);//50,33
        background = loadAudio("src/Music/backgroundmusic.wav");
        startAudioLoop(background);
        play_background= loadImage("src/Image/help.png");
        monster_image = loadImage("src/Image/monster.png");
        frames_monster = new Image[4];
        for(int i = 0;i<4;i++){
            frames_monster[i] = subImage(monster_image,91*i,0,91,82);
        }
        spine = loadImage("src/Image/spine.jpg");
        knife = loadImage("src/Image/knife.png");
    }
    @Override
    public void update(double dt) {
        animTime += dt;
        currentFrame_monster = getFrame(0.5,4);
        createButton();
    }

    @Override
    public void paintComponent() {
        changeBackgroundColor(white);
        clearBackground(width(), height());
        changeColor(Color.ORANGE);
        drawImage(play_background, 0, 0, 1000, 660);

        drawBoldText(400,100,"Rules of the game","ff",24);
        drawBoldText(200,150,"1. We are divided into three levels, and the difficulty of the game increases.","ff",15);
        drawBoldText(200,200,"2. Use the up and down left and right arrows to control the movement of the character.","ff",15);
        drawBoldText(220,220,"When you encounter a ladder, press the space bar to climb the ladder.","ff",15);
        drawBoldText(200,270,"3. Control the movement of the character and get as much gold as possible without ","ff",15);
        drawBoldText(220,290,"touching spikes, bayonets and small monsters.","ff",15);

        drawImage(spine,300,300,30,20);
        drawImage(knife,370,300,30,30);
        drawImage(frames_monster[currentFrame_monster], monster.getX()+470, monster.getY()+300, 30, 30);

        drawBoldText(200,340,"4. Encounter a small monster, health reduced by 2, that is, a whole heart;","ff",15);
        drawBoldText(220,360,"Encounter a bayonet or spike, Health -1, which is half a heart.","ff",15);

        drawBoldText(200,410,"5. The treasure chest and Silver chest need the corresponding key to open," ,"ff",15);
        drawBoldText(220,430,"and the corresponding number of gold coins will appear after opening." ,"ff",15);

        drawBoldText(200,480,"6. Once the health is less than 0, the game fails, you can restart the game.","ff",15);
        drawBoldText(200,530,"7. If you pass three levels in a row, the game is successful.","ff",15);

    }
    private void createButton() {

        JButton backButton = new JButton("BACK");
        JButton playButton = new JButton("NOW PLAY");
        setButton(backButton);
        setButton(playButton);
        JPanel buttonPanel = new JPanel();
        backButton.addActionListener(e -> {
            HomePage homePage = new HomePage();
            homePage.start();
            stopAudioLoop(background);
            mFrame.dispose();
//            homePage.create();
//            mFrame.dispose();
        });


        playButton.addActionListener(e -> {

            PlayPage playPage = new PlayPage();
            playPage.start();
            stopAudioLoop(background);
            mFrame.dispose();

        });
        mPanel.setLayout(null);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBounds(800,300,100,250);
        buttonPanel.add(backButton);
        buttonPanel.add(playButton);
        buttonPanel.setOpaque(false);

        mPanel.setPreferredSize(new Dimension(100,50));
        mPanel.add(buttonPanel);
        mPanel.setVisible(true);


    }
    Font buttonfont = new Font("Times New Roman",Font.BOLD,15);
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
    public void start() {createGame(this, 15);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    public void keyTyped(KeyEvent e){

    }
    public int getFrame(double d, int num_frames) {
        return (int)Math.floor(((animTime % d) / d) * num_frames);
    }
    public void openStartPanel() {
//        mFrame.remove(help);
        mFrame.add(mPanel);
        mFrame.revalidate();
        mFrame.repaint();
    }
}
