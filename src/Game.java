import java.awt.*;

public class Game extends GameEngine{
    //group:23
    //member:22009158 Wenyuan Tian
    //member:22009155 Xinjie Zhang
    //member:22009156 Zihan Zhao
    
    public static void main(String[] args) {

        HomePage homePage = new HomePage();
        homePage.start(); // Set the framerate for HomePage

    }
    public void init() {
        super.init();
        setupWindow(1000, 660);
    }

    @Override
    public void update(double dt) {

    }

    @Override
    public void paintComponent() {

    }
}