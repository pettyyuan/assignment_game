import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

public class PlayPage2 extends GameEngine{
    //group:23
    //member:22009158 Wenyuan Tian
    //member:22009155 Xinjie Zhang
    //member:22009156 Zihan Zhao
    int currentFrame_walk;
    int currentFrame_jump;
    int currentFrame_gold;
    int currentFrame_knife1,currentFrame_knife2;
    int currentFrame_climb;
    int score;
    int life = 10;
    int whole_heart = 7;
    int half_heart = 0;
    int gold_key_count=0;
    int silver_key_count=0;
    int gold_count=0;
    int second = 5;
    boolean game_start = true;
    boolean game_over = false;
    Point2D pos = new Point2D.Double();
    Point2D floor = new Point2D.Double();
    Point2D box = new Point2D.Double();


    ArrayList<Point2D> floor_array = new ArrayList<>();
    ArrayList<Point2D> floor1_array = new ArrayList<>();
    ArrayList<Point2D>gold_array = new ArrayList<>();
    ArrayList<Point2D> ladder_array = new ArrayList<>();
    ArrayList<Point2D> spine_array1=new ArrayList<>();
    ArrayList<Point2D> spine_array2=new ArrayList<>();
    ArrayList<Point2D> key_array_gold = new ArrayList<>();
    ArrayList<Point2D> key_array_sliver= new ArrayList<>();
    ArrayList<Point2D> chest_array_gold = new ArrayList<>();
    ArrayList<Point2D> chest_array_sliver = new ArrayList<>();
    ArrayList<Point2D> floor_elevator = new ArrayList<>();
    ArrayList<Point2D> knife_array1 = new ArrayList<>();
    ArrayList<Point2D> knife_array2 = new ArrayList<>();

    double animTime ;
    boolean is_moving = false;
    boolean is_left = false;
    boolean is_jump = false;
    boolean is_collision = true;
    boolean in_ground = true;
    boolean in_floor = false;
    boolean is_climb =false;
    boolean is_on_ladder=false;
    boolean isOpen_sliver = false;
    boolean isOpen_gold = false;
    boolean elevator_left=true;
    boolean is_elevator=false;
    boolean knife1_left=true;
    boolean knife2_right=true;
    boolean is_draw=true;

    double speed_y = 5;
    double elevator_speed=5;


    Image[] frames_walk;
    Image[] frames_jump;
    Image[] frames_climb;
    Image[] frames_gold;
    Image [] monster;
    Image [] heart;
    Image[] frames_knife;
    Image[] star_calculate;
    Image game_restart;
    Image game_next;
    Image go_home;
    Image tip,door,house;
    Image chest_image;
    Image chest_open_gold;
    Image chest_open_sliver;
    Image chest_block_gold;
    Image chest_block_sliver;
    Image chest_key_gold;
    Image chest_key_silver;
    Image game_calculate;
    Image game_complete;
    Image game_fail;
    Image heart_image;
    Image sheet_walk1;
    Image sheet_walk2;
    Image sheet_jump;
    Image sheet_climb;
    Image play_background;
    Image ground;
    Image gold ;
    Image ladder;
    Image spine;
    Image box_image;

    Image elevator_image;
    Image star;
    Image game_button;

    double ground_width;
    double ground_height;
    double floor_width;
    Random random = new Random();
    Image floor_image;
    AudioClip eat_gold;
    AudioClip eat_key;
    AudioClip play_music;


    public void start(){
        createGame(this, 15);
    }
    public void init() {
        super.init();
        setWindowSize(1000, 660);//50,33
        play_music = loadAudio("src/Music/play_music.wav");
        startAudioLoop(play_music);
        eat_gold = loadAudio("src/Music/button.wav");
        eat_key =loadAudio("src/Music/key_music.wav");
        play_background= loadImage("src/Image/level2.jpg");
        ground= loadImage("src/Image/ground.png");
        floor_image = loadImage("src/Image/floor.png");
        ladder=loadImage("src/Image/ladder.png");
        tip=loadImage("src/Image/箭头.png");
        door=loadImage("src/Image/door.png");
        house=loadImage("src/Image/house.png");
        ground_width = 50;
        ground_height = 60;
        frames_walk = new Image[4];
        frames_jump = new Image[3];
        frames_gold = new Image[6];
        frames_climb=new Image[4];
        monster = new Image[4];
        heart = new Image[9];
        frames_knife=new Image[4];
        star_calculate = new Image[4];
        star = loadImage("src/Image/star.png");
        go_home = loadImage("src/Image/home.png");
        game_button = loadImage("src/Image/game_button.png");
        game_restart = loadImage("src/Image/restart.png");
        game_next = loadImage("src/Image/next.png");
        sheet_climb=loadImage("src/Image/climb.png");
        sheet_walk1 = loadImage("src/Image/walk1.png");
        sheet_walk2 = loadImage("src/Image/walk2.png");
        sheet_jump = loadImage("src/Image/jump.png");
        gold = loadImage("src/Image/gold.png");
        spine = loadImage("src/Image/spine.png");
        box_image = loadImage("src/Image/box.png");
        heart_image = loadImage("src/Image/heart.png");
        chest_image = loadImage("src/Image/chest.png");
        elevator_image = loadImage("src/Image/elevator.png");
        frames_knife[0]=loadImage("src/Image/刀片1.png");
        frames_knife[1]=loadImage("src/Image/刀片2.png");
        frames_knife[2]=loadImage("src/Image/刀片3.png");
        frames_knife[3]=loadImage("src/Image/刀片4.png");
        game_calculate = loadImage("src/Image/game_calculate.png");
        game_fail =subImage(loadImage("src/Image/game_status.png"),0,0,400,65);
        game_complete = subImage(loadImage("src/Image/game_status.png"),0,65,400,65);
        for (int i = 0; i < 5; i++) {
            ladder_array.add(new Point2D.Double(260, 660 - 100 - i * 40));
        }
        for (int i = 0; i < 4; i++) {
            ladder_array.add(new Point2D.Double(400, 660 - 280 - i * 40));
        }
        //ladder
        for (int i = 0; i < 2; i++) {
            frames_walk[i] = subImage(sheet_walk1,25*i,0,25,25);
        }
        for (int i = 2; i < 4; i++) {
            frames_walk[i] = subImage(sheet_walk2,25*(i-2),0,25,25);
        }
        for (int i=0;i<4;i++){
            frames_climb[i]=subImage(sheet_climb,30*i,0,25,25);
        }
        for(int i = 0;i<3;i++){
            frames_jump[i] = subImage(sheet_jump,28*i,0,28,25);
        }
        for(int i = 0;i<6;i++){
            frames_gold[i] = subImage(gold,165*i,0,165,150);
        }
        for (int i=0;i<9;i++){
            heart[i] = subImage(heart_image, 166 * i, 0, 166, 132);
        }
        for(int i = 0;i<4;i++){
            star_calculate[i] = subImage(star,263*i,0,263,135);
        }
        //about gold
        gold_array.add(new Point2D.Double(200,460));
        gold_array.add(new Point2D.Double(400,460));
        gold_array.add(new Point2D.Double(800,460));
        gold_array.add(new Point2D.Double(300,320));
        gold_array.add(new Point2D.Double(100,320));
        gold_array.add(new Point2D.Double(600,320));
        gold_array.add(new Point2D.Double(200,140));
        //spine
        spine_array2.add(new Point2D.Double(720,150));
        for(int i=0;i<2;i++){
            spine_array1.add(new Point2D.Double(480+90*i,600));
        }
        //ground
        floor_array.add(new Point2D.Double(0,400));
        floor_array.add(new Point2D.Double(600,150));
        floor1_array.add(new Point2D.Double(0,260));
        floor_array.add(new Point2D.Double(800,400));
        pos.setLocation(60,660-ground_height-20*2);
        box.setLocation(800,660-ground_height-20*2);
        //About Elevators for Left and Right Movement
        floor_elevator.add(new Point2D.Double(540,400));
        //About the bayonet that moves left and right
        knife_array1.add(new Point2D.Double(200,210));
        knife_array2.add(new Point2D.Double(800,350));
        //About chest and key
        key_array_gold.add(new Point2D.Double(150,350));
        key_array_sliver.add(new Point2D.Double(80,220));

        chest_open_gold = subImage(chest_image, 0, 170, 170, 130);
        chest_open_sliver = subImage(chest_image,0,0,170,130);

        chest_block_gold = subImage(chest_image,170,170,170,125);
        chest_block_sliver = subImage(chest_image,170,0,170,130);
        chest_key_gold = subImage(chest_image,340,175,165,125);
        chest_key_silver = subImage(chest_image,340,10,165,125);

        chest_array_gold.add(new Point2D.Double(900,355));
        chest_array_sliver.add(new Point2D.Double(640,105));
    }
    @Override
    public void update(double dt) {
        if (game_start && !game_over) {
            animTime += dt;
            currentFrame_gold = getFrame(0.5, 6);
            currentFrame_knife1 = getFrame(0.5, 3);
            currentFrame_knife2 = getFrame(0.5, 3);
            if (life > 0) {
                if (life % 2 == 0) {
                    whole_heart = life / 2;
                    half_heart = 0;
                } else {
                    half_heart = 1;
                    whole_heart = life / 2;
                }
            }else {
                whole_heart = 0;
                half_heart = 0;
                game_over = true;
            }
            //change life
            for (int i = 0; i < spine_array1.size(); i++) {
                if (second == 16) {
                    if (distance(pos.getX() + 30, pos.getY() + 20, spine_array1.get(i).getX() + 65, spine_array1.get(i).getY() + 20) <= 65) {
                        life--;
                        second--;
                    }
                } else {
                    if (second % 4 == 0) {
                        is_draw = true;
                    } else {
                        is_draw = false;
                    }

                    if (second == 0) {
                        second = 16;
                    } else {
                        second--;
                    }
                }
            }
            for (int i = 0; i < spine_array2.size(); i++) {
                if (second == 16) {
                    if (distance(pos.getX() + 30, pos.getY() + 20, spine_array2.get(i).getX() + 35, spine_array2.get(i).getY() + 20) <= 60) {
                        life--;
                        second--;
                    }
                } else {
                    if (second % 4 == 0) {
                        is_draw = true;
                    } else {
                        is_draw = false;
                    }

                    if (second == 0) {
                        second = 16;
                    } else {
                        second--;
                    }
                }
            }
            //Stepping on spine
            for (int i = 0; i < knife_array1.size(); i++) {
                if (second == 16) {
                    if (distance(pos.getX() + 30, pos.getY() + 20, knife_array1.get(i).getX() + 60, knife_array1.get(i).getY() + 20) < 40) {
                        life--;
                        second--;
                    }
                } else {
                    if (second % 4 == 0) {
                        is_draw = true;
                    } else {
                        is_draw = false;
                    }

                    if (second == 0) {
                        second = 16;
                    } else {
                        second--;
                    }
                }
            }
            for (int i = 0; i < knife_array2.size(); i++) {
                if (second == 16) {
                    if (distance(pos.getX() + 30, pos.getY() + 20, knife_array2.get(i).getX() + 60, knife_array2.get(i).getY() + 20) < 40) {
                        life--;
                        second--;
                    }
                } else {
                    if (second % 4 == 0) {
                        is_draw = true;
                    } else {
                        is_draw = false;
                    }

                    if (second == 0) {
                        second = 16;
                    } else {
                        second--;
                    }
                }
            }
            if (gold_array.size() != 0) {
                for (int i = 0; i < gold_array.size(); i++) {
                    if (distance(gold_array.get(i).getX() + 20, gold_array.get(i).getY() + 20, pos.getX() + 20, pos.getY() + 20) <= 40) {
                        gold_array.remove(i);
                        playAudio(eat_gold);
                        gold_count++;
                        i--;
                    }
                }
            }//eat gold
            if (!is_collision) {
                for (int i = 0; i < floor_elevator.size(); i++) {
                    Collision(pos, floor_elevator.get(i), 60);
                    if (is_collision) {
                        break;
                    }
                }
            }

            //knife move left and right
            for (int i = 0; i < knife_array1.size(); i++) {
                if (knife1_left) {
                    if (knife_array1.get(i).getX() <= 20) {
                        knife1_left = false;
                    } else {
                        knife_array1.get(i).setLocation(knife_array1.get(i).getX() - 10, knife_array1.get(i).getY());
                    }
                } else {
                    if (knife_array1.get(i).getX() >= 340) {
                        knife1_left = true;
                    } else {
                        knife_array1.get(i).setLocation(knife_array1.get(i).getX() + 10, knife_array1.get(i).getY());
                    }
                }
            }

            for (int i = 0; i < knife_array2.size(); i++) {
                if (knife2_right) {
                    if (knife_array2.get(i).getX() >= 960) {
                        knife2_right = false;
                    } else {
                        knife_array2.get(i).setLocation(knife_array2.get(i).getX() + 10, knife_array2.get(i).getY());
                    }
                } else {
                    if (knife_array2.get(i).getX() <= 800) {
                        knife2_right = true;
                    } else {
                        knife_array2.get(i).setLocation(knife_array2.get(i).getX() - 10, knife_array2.get(i).getY());
                    }
                }
            }

            //elevator move left and right
            for (int i = 0; i < floor_elevator.size(); i++) {
                if (elevator_left) {
                    if (floor_elevator.get(i).getX() <= 500) {
                        elevator_left = false; // change direction
                    } else {
                        floor_elevator.get(i).setLocation(floor_elevator.get(i).getX() - elevator_speed, floor_elevator.get(i).getY());
                        if (is_elevator) {
                            pos.setLocation(pos.getX() - elevator_speed, pos.getY());
                        }

                    }
                } else {
                    if (floor_elevator.get(i).getX() >= 740) {
                        elevator_left = true; // 改变方向
                    } else {
                        floor_elevator.get(i).setLocation(floor_elevator.get(i).getX() + elevator_speed, floor_elevator.get(i).getY());
                        if (is_elevator) {
                            pos.setLocation(pos.getX() + elevator_speed, pos.getY());
                        }

                    }
                }
            }

            //check if pos on the elevator
            for (int i = 0; i < floor_elevator.size(); i++) {
                if (pos.getY() >= floor_elevator.get(i).getY() - 40 && pos.getY() < floor_elevator.get(i).getY()) {
                    if (pos.getX() >= floor_elevator.get(i).getX() && pos.getX() < floor_elevator.get(i).getX() + 60) {
                        is_elevator = true;//pos on the elevator
                        break;
                    } else {
                        is_elevator = false;
                    }
                } else {
                    is_elevator = false;
                }
            }

            if (distance(box.getX(), box.getY(), pos.getX(), pos.getY()) <= 40) {
                if (box.getY() == pos.getY()) {
                    if (box.getX() > pos.getX()) {
                        if (is_moving) {
                            if (!is_left) {
                                box.setLocation(box.getX() + 20, box.getY());

                            }
                        }
                        if (box.getX() >= 1000 - 40) {
                            box.setLocation(960, box.getY());
                        }
                    } else {
                        if (is_moving) {
                            if (is_left) {
                                box.setLocation(box.getX() - 20, box.getY());
                            }
                        }
                        if (box.getX() <= 0) {
                            box.setLocation(0, box.getY());
                        }
                    }
                } else if (pos.getX() >= box.getX() && pos.getX() < box.getX() + 40) {
                    if (pos.getY() > box.getY()) {
                        Collision(pos, box, 40);
                    }
                }
            }


            if (key_array_gold.size() != 0) {
                for (int i = 0; i < key_array_gold.size(); i++) {
                    if (distance(key_array_gold.get(i).getX() + 20, key_array_gold.get(i).getY() + 20, pos.getX() + 20, pos.getY() + 20) <= 40) {
                        playAudio(eat_key);
                        key_array_gold.remove(i);
                        gold_key_count++;
                        i--;
                    }
                }
            }
            if (key_array_sliver.size() != 0) {
                for (int i = 0; i < key_array_sliver.size(); i++) {
                    if (distance(key_array_sliver.get(i).getX() + 20, key_array_sliver.get(i).getY() + 20, pos.getX() + 20, pos.getY() + 20) <= 40) {
                        playAudio(eat_key);
                        key_array_sliver.remove(i);
                        silver_key_count++;
                        i--;
                    }
                }
            }
            //eat key
            if (is_moving) {
                if (is_left) {
                    pos.setLocation(pos.getX() - 20, pos.getY());
                } else {
                    pos.setLocation(pos.getX() + 20, pos.getY());
                }
                if (pos.getY() == box.getY()) {
                    if (pos.getX() < box.getX()) {
                        if (!is_left) {
                            if (pos.getX() >= box.getX() - 40) {
                                pos.setLocation(box.getX() - 40, pos.getY());
                            }
                        }
                    } else {
                        if (is_left) {
                            if (pos.getX() <= box.getX() + 40) {
                                pos.setLocation(box.getX() + 40, pos.getY());
                            }
                        }
                    }
                }
                if (pos.getX() < 0) {
                    pos.setLocation(0, pos.getY());
                }
                if (pos.getX() > 1000 - 20 * 2) {
                    pos.setLocation(1000 - 20 * 2, pos.getY());
                }
                if (pos.getY() <= 0) {
                    pos.setLocation(pos.getX(), 0);
                }

                currentFrame_walk = getFrame(0.3, 4);
            } else {
                currentFrame_walk = 0;
            }
            //move left and right
            if (is_collision && in_floor) {
                if (pos.getX() < floor.getX() || pos.getX() >= floor.getX() + floor_width) {
                    is_collision = false;
                    speed_y = 0;
                }
            }
            //judge in which floor
            if (!is_collision) {
                for (int i = 0; i < floor1_array.size(); i++) {
                    Collision(pos, floor1_array.get(i), 400);
                    if (is_collision) {
                        break;
                    }

                }
            }
            if (!is_collision) {
                for (int i = 0; i < floor_array.size(); i++) {
                    Collision(pos, floor_array.get(i), 500);
                    if (is_collision) {
                        break;
                    }

                }
            }
            if (!is_collision) {
                for (int i = 0; i < floor1_array.size(); i++) {
                    Collision(pos, floor1_array.get(i), 540);
                    if (is_collision) {
                        break;
                    }

                }
            }

            if (is_jump) {
                Collision(pos, floor, floor_width);
                if (speed_y < 0) {
                    pos.setLocation(pos.getX(), pos.getY() - 28);
                    if (pos.getY() > 0) {
                        speed_y += 1;
                        currentFrame_jump = getFrame(0.3, 3);
                    } else {
                        pos.setLocation(pos.getX(), 0);
                        speed_y = 0;
                    }
                } else {
                    if (in_ground) {
                        if (pos.getY() < 660 - ground_height - 20 * 2) {
                            pos.setLocation(pos.getX(), pos.getY() + 40);
                            speed_y += 1;
                            currentFrame_jump = 2;
                        } else {//or on ground
                            {
                                pos.setLocation(pos.getX(), 660 - ground_height - 20 * 2);
                                speed_y = 5;
                                is_collision = true;
                                is_jump = false;
                            }
                        }
                    } else {//pos on floor
                        pos.setLocation(pos.getX(), floor.getY() - 20 * 2);
                        speed_y = 5;
                        is_jump = false;
                        is_collision = true;
                    }

                }
            }
            if (is_climb) {
                for (int i = 0; i < ladder_array.size(); i++) {
                    if (pos.getY() >= ladder_array.get(i).getY() && pos.getY() < ladder_array.get(i).getY() + 40) {
                        if (pos.getX() >= ladder_array.get(i).getX() && pos.getX() < ladder_array.get(i).getX() + 40) {
                            is_on_ladder = true;
                            in_floor = false;
                            in_ground = false;
                            break;
                        } else {
                            is_on_ladder = false;
                            in_ground = true;
                            in_floor = true;
                        }
                    } else {
                        is_on_ladder = false;
                        in_floor = true;
                        in_ground = true;
                    }
                }
                //judge whether  on ladder
                if (is_on_ladder) {
                    if (speed_y <= 5) {
                        speed_y += 0.5;
                    }
                    pos.setLocation(pos.getX(), pos.getY() - speed_y);
                    currentFrame_climb = getFrame(0.5, 4);
                }

            }
            if(distance(pos.getX()+20,pos.getY()+20,950,105)<=40){
                game_start = false;
            }
        }
    }


    public int getFrame(double d, int num_frames) {
        return (int)Math.floor(((animTime % d) / d) * num_frames);
    }
    public void Collision(Point2D pos, Point2D floor1, double floor_width1){
        if(pos.getX()>=floor1.getX()&&pos.getX()<floor1.getX()+floor_width1) {//pos on the floor
            if (pos.getY() >= floor1.getY() && pos.getY() - floor1.getY() <= 50) {
                if (speed_y > 0) {
                    is_collision = true;
                    in_ground = false;
                    speed_y = 5;
                    floor = floor1;
                    floor_width = floor_width1;
                    in_floor = true;
                }else {
                    speed_y = 0;
                    in_ground = true;
                    is_collision = true;
                }
            } else {
                in_ground = true;
                is_collision = false;
                in_floor = false;
            }
        }else {
            in_floor = false;
            is_jump = true;
            in_ground = true;
            is_collision = false;
        }
    }
    @Override
    public void paintComponent() {
        changeBackgroundColor(white);
        clearBackground(width(), height());
        changeColor(pink);
        drawImage(play_background, 0, 0, 1000, 660);
        drawImage(tip,300,530,60,80);
        drawImage(tip,340,330,60,80);
        drawImage(door,20,510,100,100);
        drawImage(house,900,55,100,100);
        for(int i = 0;i<whole_heart;i++){
            drawImage(heart[i],20+i*40,30,40,40);
        }
        if(half_heart==1){
            drawImage(heart[7],20+whole_heart*40,30,40,40);
        }
        if(whole_heart+half_heart<7){
            for(int i = 0;i<5-whole_heart-half_heart;i++){
                drawImage(heart[8],20+(whole_heart+half_heart+i)*40,30,40,40);
            }
        }
        //life

        for(int i = 0;i<20;i++){
            drawImage(ground,i*ground_width,height()-ground_height,ground_width,ground_height);
        }

        drawImage(box_image,box.getX(),box.getY(),40,40);
        if(gold_array.size()!=0) {
            for (int i = 0; i < gold_array.size(); i++) {
                drawImage(frames_gold[currentFrame_gold], gold_array.get(i).getX(), gold_array.get(i).getY(), 40, 40);
            }
        }

        if (knife_array1.size()!=0){
            for (int i = 0; i < knife_array1.size(); i++) {
                drawImage(frames_knife[currentFrame_knife1], knife_array1.get(i).getX(), knife_array1.get(i).getY(), 80, 80);
            }
        }
        if (knife_array2.size()!=0){
            for (int i = 0; i < knife_array2.size(); i++) {
                drawImage(frames_knife[currentFrame_knife2], knife_array2.get(i).getX(), knife_array2.get(i).getY(), 80, 80);
            }
        }
        //Bayonet

        for(int i =0 ;i<floor_array.size();i++){
            drawImage(floor_image,floor_array.get(i).getX(),floor_array.get(i).getY(),500,30);
        }
        for(int i =0 ;i<floor_array.size();i++){
            drawImage(floor_image,floor_array.get(i).getX(),floor_array.get(i).getY(),100,30);
        }
        for(int i =0 ;i<floor_array.size();i++){
            drawImage(floor_image,floor_array.get(i).getX(),floor_array.get(i).getY(),200,30);
        }
        for (int i=0;i<floor1_array.size();i++){
            drawImage(floor_image,floor1_array.get(i).getX(),floor1_array.get(i).getY(),540,30);
        }

        for (int i = 0; i < ladder_array.size(); i++) {
            drawImage(ladder, ladder_array.get(i).getX(), ladder_array.get(i).getY(), 40, 40);
        }
        for (int i = 0; i < ladder_array.size(); i++) {
            drawImage(ladder, ladder_array.get(i).getX(), ladder_array.get(i).getY(), 40, 40);
        }
        //ladder
        for (int i = 0; i < spine_array1.size(); i++){
            drawImage(spine, spine_array1.get(i).getX(), spine_array1.get(i).getY() - 20, 100, 20);
        }
        for (int i = 0; i < spine_array2.size(); i++){
            drawImage(spine, spine_array2.get(i).getX(), spine_array2.get(i).getY() - 20, 60, 20);
        }
        //spine

        for(int i =0 ;i<floor_elevator.size();i++){
            drawImage(elevator_image,floor_elevator.get(i).getX(),floor_elevator.get(i).getY(),60,30);
        }
        //elevator
        if(key_array_gold.size()!=0) {
            for (int i = 0; i < key_array_gold.size(); i++) {
                drawImage(chest_key_gold, key_array_gold.get(i).getX(), key_array_gold.get(i).getY(), 30, 30);
            }
        }
        if(key_array_sliver.size()!=0) {
            for (int i = 0; i < key_array_sliver.size(); i++) {
                drawImage(chest_key_silver, key_array_sliver.get(i).getX(), key_array_sliver.get(i).getY(), 30, 30);
            }
        }
        //chest
        if(!isOpen_gold) {
            if (chest_array_gold.size() != 0) {
                for (int i = 0; i < chest_array_gold.size(); i++) {
                    drawImage(chest_open_gold, chest_array_gold.get(i).getX(), chest_array_gold.get(i).getY(), 50, 50);
                }
            }
        }
        if(!isOpen_sliver)
        {
            if(chest_array_sliver.size()!=0) {
                for (int i = 0; i < chest_array_sliver.size(); i++) {
                    drawImage(chest_open_sliver, chest_array_sliver.get(i).getX(), chest_array_sliver.get(i).getY(), 50, 50);
                }
            }
        }

        //calculate
        for(int i = 0;i<2;i++){
            drawImage(frames_gold[currentFrame_gold], 700, 20, 40, 40);
            drawText(750,45,"x"+gold_count,"arial",20);
            drawImage(chest_key_gold,800,20,30,30);
            drawText(850,45,"x"+gold_key_count,"arial",20);
            drawImage(chest_key_silver,900,20,30,30);
            drawText(950,45,"x"+silver_key_count,"arial",20);

        }
        //open gold chest
        if(gold_key_count!=0) {
            for (int i = 0; i < chest_array_gold.size(); i++) {
                if(distance(chest_array_gold.get(i).getX(),chest_array_gold.get(i).getY(),pos.getX()+20,pos.getY()+20)<=50){
                    drawImage(chest_block_gold,chest_array_gold.get(i).getX(),chest_array_gold.get(i).getY(),50,50);
                    isOpen_gold=true;
                    gold_key_count--;
                }
                if(isOpen_gold){
                    for(int j=0;j<15;j++){
                        gold_array.add(new Point2D.Double(random.nextInt(50)*20,chest_array_gold.get(i).getY()));
                    }
                    if(gold_array.size()!=0) {
                        for (int k= 0; k < gold_array.size(); k++) {
                            if(distance(gold_array.get(k).getX()+20,gold_array.get(k).getY()+20,pos.getX()+20,pos.getY()+20)<=40){
                                gold_array.remove(k);
                                k--;
                                gold_count++;
                            }
                        }
                    }
                }

            }
        }
//open sliver chest
        if(silver_key_count!=0) {
            for (int i = 0; i < chest_array_sliver.size(); i++) {
                if(distance(chest_array_sliver.get(i).getX(),chest_array_sliver.get(i).getY(),pos.getX()+20,pos.getY()+20)<=50){
                    drawImage(chest_block_sliver,chest_array_sliver.get(i).getX(),chest_array_sliver.get(i).getY(),50,50);
                    isOpen_sliver=true;
                }if(isOpen_sliver){
                    silver_key_count--;
                    for(int j=0;j<7;j++){
                        gold_array.add(new Point2D.Double(random.nextInt(50)*20,chest_array_sliver.get(i).getY()));
                    }
                    if(gold_array.size()!=0) {
                        for (int k= 0; k < gold_array.size(); k++) {
                            if(distance(gold_array.get(k).getX()+20,gold_array.get(k).getY()+20,pos.getX()+20,pos.getY()+20)<=40){
                                gold_array.remove(k);
                                k--;
                                gold_count++;
                            }
                        }
                    }
                }

            }
        }
        if(is_draw) {
            if (is_left) {
                if (is_jump) {
                    if (speed_y <= 0) {
                        drawImage(frames_jump[currentFrame_jump], pos.getX() + 30, pos.getY() - 20, -20 * 2, 20 * 2);
                    } else {
                        drawImage(frames_jump[currentFrame_jump], pos.getX() + 30, pos.getY() - 60, -20 * 2, 20 * 2);
                    }
                } else if (is_climb && is_on_ladder) {
                    drawImage(frames_climb[currentFrame_climb], pos.getX() + 30, pos.getY(), -20 * 2, 20 * 2);
                } else {
                    drawImage(frames_walk[currentFrame_walk], pos.getX() + 20, pos.getY(), -20 * 2, 20 * 2);
                }
            } else {
                if (is_jump) {
                    if (speed_y <= 0) {
                        drawImage(frames_jump[currentFrame_jump], pos.getX()-10, pos.getY() - 20, 20 * 2, 20 * 2);
                    } else {
                        drawImage(frames_jump[currentFrame_jump], pos.getX()-10, pos.getY() - 60, 20 * 2, 20 * 2);
                    }
                } else if (is_climb && is_on_ladder) {
                    drawImage(frames_climb[currentFrame_climb], pos.getX() - 10, pos.getY(), 20 * 2, 20 * 2);
                } else {
                    drawImage(frames_walk[currentFrame_walk], pos.getX() - 10, pos.getY(), 20 * 2, 20 * 2);
                }
            }
        }
        if(!game_start){
            //next level
            CalculatePage(life,gold_count);

        }
        if(game_over){
            //game over
            Game_overPage();
        }
    }
    public void CalculatePage(int life,int gold_count ){//37 gold
        drawImage(game_calculate,200,160,560,380);
        drawImage(game_complete,380,210,200,60);
        score = gold_count*5-(10-life)*10;
        if(score>=29*5*0.8){
            drawImage(star_calculate[0],350,270,250,100);
        }else if(score>=29*5*0.5){
            drawImage(star_calculate[1],350,270,250,100);
        }else {
            drawImage(star_calculate[2],350,270,250,100);
        }

        drawImage(game_restart,320,380,70,70);
        drawImage(go_home,445,380,70,70);
        drawImage(game_next,565,380,70,70);
    }
    public void Game_overPage(){
        drawImage(game_calculate,200,160,560,380);
        drawImage(game_fail,390,210,200,60);
        drawImage(star_calculate[3],350,270,250,100);
        drawImage(game_restart,350,380,70,70);
        drawImage(go_home,525,380,70,70);
    }
    @Override
    public void keyPressed(KeyEvent e) {

        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
        {

            is_moving=true;
            is_left = false;
            is_climb=false;

        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            is_moving=true;
            is_left = true;
            is_climb=false;
        }
        else if(e.getKeyCode()==KeyEvent.VK_SPACE){
            is_climb=true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            is_moving = false;
            is_climb=false;

        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            is_moving = false;
            is_climb=false;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP)
        {
            if(speed_y>=5) {
                speed_y = -5;
                is_jump = true;
            }
            if (is_on_ladder) {
                is_climb = false;
            }


        }


    }
    // Called whenever a mouse button is pressed
    public void mousePressed(MouseEvent e) {
    }
    // Called whenever a mouse button is released
    public void mouseReleased(MouseEvent e) {}
    // Called whenever a mouse button is clicked
    public void mouseClicked(MouseEvent e) {
        if (game_over) {

            if (distance(e.getX(), e.getY(), 385, 415) <= 50) {
                stopAudioLoop(play_music);
                PlayPage2 playPage2 = new PlayPage2();
                playPage2.start();
                mFrame.dispose();
            }//restart
            if (distance(e.getX(), e.getY(), 560, 415) <= 50) {
                stopAudioLoop(play_music);
                HomePage homePage = new HomePage();
                homePage.start();
                mFrame.dispose();
            }//go home
        }
        if(!game_start){
            if (distance(e.getX(), e.getY(), 355, 415) <= 50) {
                stopAudioLoop(play_music);
                PlayPage playPage = new PlayPage();
                playPage.start();
                mFrame.dispose();
            }//restart
            if (distance(e.getX(), e.getY(), 480, 415) <= 50) {
                stopAudioLoop(play_music);
                HomePage homePage = new HomePage();
                homePage.start();
                mFrame.dispose();
            }//go home
            if (distance(e.getX(), e.getY(), 600, 415) <= 50) {
                stopAudioLoop(play_music);
                PlayPage1 playPage1 = new PlayPage1();
                playPage1.start();
                mFrame.dispose();
            }
        }
    }
    // Called whenever the mouse cursor enters the game panel
    public void mouseEntered(MouseEvent e) {}
    // Called whenever the mouse cursor exits the game panel
    public void mouseExited(MouseEvent e) {}
}
