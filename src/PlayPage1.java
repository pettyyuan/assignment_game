
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

public class PlayPage1 extends GameEngine{
    //group:23
    //member:22009158 Wenyuan Tian
    //member:22009155 Xinjie Zhang
    //member:22009156 Zihan Zhao
    int currentFrame_walk;
    int currentFrame_jump;
    int currentFrame_gold;
    int currentFrame_spring;
    int currentFrame_monster;
    int life = 10;
    int gold_key_count=0;
    int silver_key_count=0;
    int gold_count=0;
    int second=24;
    int whole_heart=7;
    int half_heart = 0;
    Point2D pos = new Point2D.Double();
    Point2D floor = new Point2D.Double();
    Point2D box = new Point2D.Double();
    Point2D monster = new Point2D.Double();
    ArrayList<Point2D> floor_array = new ArrayList<>();
    ArrayList<Point2D> floor1_array = new ArrayList<>();
    ArrayList<Point2D> floor_elevator = new ArrayList<>();
    ArrayList<Point2D> floor_spring = new ArrayList<>();
    ArrayList<Point2D> floor_move_platform = new ArrayList<>();
    ArrayList<Point2D> gold_array = new ArrayList<>();
    ArrayList<Point2D> key_array_gold = new ArrayList<>();
    ArrayList<Point2D> key_array_sliver= new ArrayList<>();
    ArrayList<Point2D> chest_array_gold = new ArrayList<>();
    ArrayList<Point2D> chest_array_sliver = new ArrayList<>();
    ArrayList<Point2D> spine_array = new ArrayList<>();

    double animTime ;
    boolean is_moving = false;
    boolean is_left = false;
    boolean is_jump = false;
    boolean is_collision = true;
    boolean in_ground = true;
    boolean in_floor = false;
    boolean isOpen_gold = false;
    boolean isOpen_sliver = false;
    boolean game_start = true;
    boolean game_over = false;

    boolean isUP = true;
    boolean isOn = false;
    boolean isRight = true;
    boolean monster_left = false;
    boolean is_draw = true;
    double speed_y = 5;
    Image[] frames_walk;
    Image[] frames_jump;
    Image[] frames_gold;
    Image [] heart;
    Image[] frames_monster;
    Image [] chest;
    Image [] spring;
    Image heart_image;
    Image sheet_walk1;
    Image sheet_walk2;
    Image sheet_jump;
    Image play_background;
    Image ground;
    Image gold ;
    Image spine;
    Image box_image;
    Image monster_image;
    Image chest_image;
    Image chest_open_gold;
    Image chest_open_sliver;
    Image chest_block_gold;
    Image chest_block_sliver;
    Image chest_key_gold;
    Image chest_key_silver;
    Image elevator;
    Image spring_image;
    Image spring_original;
    Image platform_image;

    Image room;
    Image door;
    double ground_width;
    double ground_height;
    double floor_width;
    Random random = new Random();
    Image floor_image;
    Image star;
    Image[] star_calculate;
    Image game_calculate;
    Image game_fail;
    Image game_button;
    Image game_complete;
    Image game_restart;
    Image game_next;
    Image go_home;
    int score;
    AudioClip eat_gold;
    AudioClip eat_key;
    AudioClip play_music;
    public void start(){
        createGame(this, 15);
    }
    public void init() {
        super.init();
        setWindowSize(1000, 660);//50,33
        eat_gold = loadAudio("src/Music/button.wav");
        play_music = loadAudio("src/Music/play_music.wav");
        startAudioLoop(play_music);
        eat_key =loadAudio("src/Music/key_music.wav");
        play_background= loadImage("src/Image/Background2.png");
        ground= loadImage("src/Image/ground.png");
        floor_image = loadImage("src/Image/floor.png");
        elevator = loadImage("src/Image/elevator.png");
        ground_width = 50;
        ground_height = 60;
        frames_walk = new Image[4];
        frames_jump = new Image[3];
        frames_gold = new Image[6];
        frames_monster = new Image[4];
        heart = new Image[9];
        chest = new Image[6];
        spring = new Image[5];
        star_calculate = new Image[4];
        star = loadImage("src/Image/star.png");
        go_home = loadImage("src/Image/home.png");
        game_button = loadImage("src/Image/game_button.png");
        game_restart = loadImage("src/Image/restart.png");
        game_next = loadImage("src/Image/next.png");
        game_calculate = loadImage("src/Image/game_calculate.png");
        game_fail =subImage(loadImage("src/Image/game_status.png"),0,0,400,65);
        game_complete = subImage(loadImage("src/Image/game_status.png"),0,65,400,65);
        sheet_walk1 = loadImage("src/Image/walk1.png");
        sheet_walk2 = loadImage("src/Image/walk2.png");
        sheet_jump = loadImage("src/Image/jump.png");
        gold = loadImage("src/Image/gold.png");
        spine = loadImage("src/Image/spine.jpg");
        box_image = loadImage("src/Image/box.png");
        heart_image = loadImage("src/Image/heart.png");
        monster_image = loadImage("src/Image/monster.png");
        chest_image = loadImage("src/Image/chest.png");
        spring_image = loadImage("src/Image/spring.png");
        spring_original = loadImage("src/Image/spring11.png");
        platform_image = loadImage("src/Image/platform.png");
        room = loadImage("src/Image/room.png");
        door = loadImage("src/Image/door.png");
        for (int i = 0; i < 2; i++) {
            frames_walk[i] = subImage(sheet_walk1,25*i,0,25,25);
        }
        for (int i = 2; i < 4; i++) {
            frames_walk[i] = subImage(sheet_walk2,25*(i-2),0,25,25);
        }
        for(int i = 0;i<3;i++){
            frames_jump[i] = subImage(sheet_jump,28*i,0,28,25);
        }
        for(int i = 0;i<6;i++){
            frames_gold[i] = subImage(gold,165*i,0,165,150);
        }
        for(int i = 0;i<9;i++){
            heart[i] = subImage(heart_image,166*i,0,166,132);
        }
        for(int i = 0;i<4;i++){
            frames_monster[i] = subImage(monster_image,91*i,0,91,82);
        }
        for(int i=0;i<5;i++){
            spring[i] = subImage(spring_image,356*i,0,356,105);
        }
        for(int i = 0;i<4;i++){
            star_calculate[i] = subImage(star,263*i,0,263,135);
        }
//        gold_array.add(new Point2D.Double(200,460));

        floor_array.add(new Point2D.Double(100,515));
        floor_array.add(new Point2D.Double(200,450));
        floor_array.add(new Point2D.Double(300,350));
        floor_array.add(new Point2D.Double(400,475));
        floor_array.add(new Point2D.Double(800,475));
        floor_array.add(new Point2D.Double(100,120));
        floor_array.add(new Point2D.Double(920,100));

        floor1_array.add(new Point2D.Double(450,350));
        floor1_array.add(new Point2D.Double(525,230));
        floor1_array.add(new Point2D.Double(600,100));
        floor1_array.add(new Point2D.Double(675,230));
        floor1_array.add(new Point2D.Double(750,350));

        pos.setLocation(60,660-ground_height-20*2);
        // box.setLocation(500,660-ground_height-20*2);

        monster.setLocation(500, 525);

        key_array_gold.add(new Point2D.Double(460,320));
        key_array_sliver.add(new Point2D.Double(120,80));

        chest_open_gold = subImage(chest_image, 0, 170, 170, 130);
        chest_open_sliver = subImage(chest_image,0,0,170,130);

        chest_block_gold = subImage(chest_image,170,170,170,125);
        chest_block_sliver = subImage(chest_image,170,0,170,130);
        chest_key_gold = subImage(chest_image,340,175,165,125);
        chest_key_silver = subImage(chest_image,340,10,165,125);

        chest_array_gold.add(new Point2D.Double(750,290));
        chest_array_sliver.add(new Point2D.Double(600,45));

        floor_elevator.add(new Point2D.Double(30,525));

        floor_spring.add(new Point2D.Double(900,550));

        floor_move_platform.add(new Point2D.Double(500,475));

        spine_array.add(new Point2D.Double(460,560));
        spine_array.add(new Point2D.Double(560,300));
    }

    @Override
    public void update(double dt) {
        if (game_start && !game_over) {
            animTime += dt;
            currentFrame_gold = getFrame(0.5, 6);
            currentFrame_monster = getFrame(0.5, 4);
            currentFrame_spring = getFrame(0.5, 5);
            if (gold_array.size() != 0) {
                for (int i = 0; i < gold_array.size(); i++) {
                    if (distance(gold_array.get(i).getX() + 20, gold_array.get(i).getY() + 20, pos.getX() + 20, pos.getY() + 20) <= 40) {
                        gold_array.remove(i);
                        playAudio(eat_gold);
                        gold_count++;
                        i--;
                    }
                }
            }
            //move monster
            if (monster_left) {
                monster.setLocation(monster.getX() - 3, monster.getY());
            } else {
                monster.setLocation(monster.getX() + 3, monster.getY());
            }
            if (monster.getX() >= 1000 - 60) {
                monster_left = true;
            } else if (monster.getX() <= 0) {
                monster_left = false;
            }

            //collision with monster
            if (second == 24) {
                if (distance(monster.getX() + 30, monster.getY() + 40, pos.getX() + 20, pos.getY() + 20) <= 50) {
                    life = life - 2;
                    second--;
                }
            } else {

                if (second % 3 == 0) {
                    is_draw = true;
                } else {
                    is_draw = false;

                }

                if (second == 0) {
                    second = 24;
                } else {
                    second--;
                }

            }
            //改变爱心
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

            //被刺扎到
            for (int i = 0; i < spine_array.size(); i++) {
                if (second == 24) {
                    if (distance(pos.getX() + 20, pos.getY() + 20, spine_array.get(i).getX() + 20, spine_array.get(i).getY() + 20) < 40) {
                        life--;
                        second--;
                    }
                }
            }


            if (key_array_gold.size() != 0) {
                for (int i = 0; i < key_array_gold.size(); i++) {
                    if (distance(key_array_gold.get(i).getX() + 15, key_array_gold.get(i).getY() + 15, pos.getX() + 20, pos.getY() + 20) <= 35) {
                        playAudio(eat_key);
                        key_array_gold.remove(i);
                        gold_key_count++;
                        i--;
                    }
                }
            }
            if (key_array_sliver.size() != 0) {
                for (int i = 0; i < key_array_sliver.size(); i++) {
                    if (distance(key_array_sliver.get(i).getX() + 15, key_array_sliver.get(i).getY() + 15, pos.getX() + 20, pos.getY() + 20) <= 35) {
                        playAudio(eat_key);
                        key_array_sliver.remove(i);
                        silver_key_count++;
                        i--;
                    }
                }
            }

            //currentFrame = (currentFrame + 1) % 3;
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
                } else {
                    if (pos.getX() < 0) {
                        pos.setLocation(0, pos.getY());
                    }
                    if (pos.getX() > 1000 - 20 * 2) {
                        pos.setLocation(1000 - 20 * 2, pos.getY());
                    }
                    if (pos.getY() <= 0) {
                        pos.setLocation(pos.getX(), 0);
                    }
                }
                currentFrame_walk = getFrame(0.3, 4);
            } else {
                currentFrame_walk = 0;
            }
            if (is_collision && in_floor) {
                if (pos.getX() < floor.getX() || pos.getX() >= floor.getX() + floor_width) {
                    is_collision = false;
                    speed_y = 0;
                }
            }
            if (!is_collision) {
                for (int i = 0; i < floor1_array.size(); i++) {
                    Collision(pos, floor1_array.get(i), 50);
                    if (is_collision) {
                        break;
                    }

                }
            }
            if (!is_collision) {
                for (int i = 0; i < floor_array.size(); i++) {
                    Collision(pos, floor_array.get(i), 80);
                    if (is_collision) {
                        break;
                    }

                }
            }
            if (!is_collision) {
                for (int i = 0; i < floor_elevator.size(); i++) {
                    Collision(pos, floor_elevator.get(i), 80);
                    if (is_collision) {
                        break;
                    }

                }

            }
            if (!is_collision) {
                for (int i = 0; i < floor_spring.size(); i++) {
                    Collision(pos, floor_spring.get(i), 80);
                    if (is_collision) {
                        break;
                    }

                }
            }
            if (!is_collision) {
                for (int i = 0; i < floor_move_platform.size(); i++) {
                    Collision(pos, floor_move_platform.get(i), 60);
                    if (is_collision) {
                        break;
                    }

                }
            }//in which floor

            //elevator
            for (int i = 0; i < floor_elevator.size(); i++) {

                if (floor_elevator.get(i).equals(floor)) {

                    if (floor_elevator.get(i).getY() > 120 && isUP) {
                        floor_elevator.get(i).setLocation(floor.getX(), floor.getY() - 10);
                        if (is_collision && !in_ground) {
                            pos.setLocation(pos.getX(), pos.getY() - 10);
                        }
                    } else {
                        if (isUP) {
                            isUP = false;
                        }
                        if (!isUP && floor_elevator.get(i).getY() <= 525) {
                            floor_elevator.get(i).setLocation(floor.getX(), floor.getY() + 10);
                            if (is_collision && !in_ground) {
                                pos.setLocation(pos.getX(), pos.getY() + 10);
                            }
                            if (floor_elevator.get(i).getY() == 525) {
                                isUP = true;
                            }
                        }
                    }

                } else {
                    if (floor_elevator.get(i).getY() > 120 && isUP) {
                        floor_elevator.get(i).setLocation(floor_elevator.get(i).getX(), floor_elevator.get(i).getY() - 10);
                    } else {
                        if (isUP) {
                            isUP = false;
                        }
                        if (!isUP && floor_elevator.get(i).getY() <= 525) {
                            floor_elevator.get(i).setLocation(floor_elevator.get(i).getX(), floor_elevator.get(i).getY() + 10);
                            if (floor_elevator.get(i).getY() == 525) {
                                isUP = true;
                            }
                        }
                    }
                }


            }
            //spring
            for (int i = 0; i < floor_spring.size(); i++) {

                if (floor_spring.get(i).equals(floor)) {
                    isOn = true;
                    if (is_collision && !in_ground) {
                        pos.setLocation(pos.getX(), pos.getY() - 80);
                        speed_y = -15;
                        is_jump = true;
                    }
                    if (!is_collision) {
                        isOn = false;
                    }
                }
            }

            //Horizontal movement
            for (int i = 0; i < floor_move_platform.size(); i++) {

                if (floor_move_platform.get(i).equals(floor)) {

                    if (floor_move_platform.get(i).getX() > 480 && isRight && floor_move_platform.get(i).getX() < 720) {
                        floor_move_platform.get(i).setLocation(floor.getX() + 10, floor.getY());
                        if (is_collision && !in_ground) {
                            pos.setLocation(pos.getX() + 10, pos.getY());
                        }
                    } else {
                        if (floor_move_platform.get(i).getX() == 720) {
                            isRight = false;
                        }
                        if (!isRight) {
                            floor_move_platform.get(i).setLocation(floor.getX() - 10, floor.getY());
                            if (is_collision && !in_ground) {
                                pos.setLocation(pos.getX() - 10, pos.getY());
                            }
                            if (floor_move_platform.get(i).getX() == 480) {
                                isRight = true;
                                floor_move_platform.get(i).setLocation(floor.getX() + 10, floor.getY());
                                if (is_collision && !in_ground) {
                                    pos.setLocation(pos.getX() + 10, pos.getY());
                                }
                            }
                        }
                    }

                } else {
                    if (floor_move_platform.get(i).getX() > 480 && isRight && floor_move_platform.get(i).getX() < 720) {
                        floor_move_platform.get(i).setLocation(floor_move_platform.get(i).getX() + 10, floor_move_platform.get(i).getY());
                    } else {
                        if (floor_move_platform.get(i).getX() == 720) {
                            isRight = false;
                        }
                        if (!isRight) {
                            floor_move_platform.get(i).setLocation(floor_move_platform.get(i).getX() - 10, floor_move_platform.get(i).getY());
                            if (floor_move_platform.get(i).getX() == 480) {
                                isRight = true;
                                floor_move_platform.get(i).setLocation(floor_move_platform.get(i).getX() + 10, floor_move_platform.get(i).getY());
                            }
                        }
                    }
                }


            }
            if (is_jump) {
                Collision(pos, floor, floor_width);
                if (speed_y < 0) {
                    pos.setLocation(pos.getX(), pos.getY() - 30);
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
                        } else {
                            {
                                pos.setLocation(pos.getX(), 660 - ground_height - 20 * 2);
                                speed_y = 5;
                                is_collision = true;
                                is_jump = false;
                            }
                        }
                    } else {//in floor
                        pos.setLocation(pos.getX(), floor.getY() - 20 * 2);
                        speed_y = 5;
                        is_jump = false;
                        is_collision = true;
                    }

                }
            }
            if(distance(pos.getX()+20,pos.getY()+20,970,70)<=40){
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
        drawImage(door,20,550,50,50);
        drawImage(room,940,40,60,60);
        //life
        for(int i = 0;i<whole_heart;i++){
            drawImage(heart[i],20+i*40,30,40,40);
        }
        if(half_heart==1){
            drawImage(heart[7],20+whole_heart*40,30,40,40);
        }
        if(whole_heart+half_heart<5){
            for(int i = 0;i<5-whole_heart-half_heart;i++){
                drawImage(heart[8],20+(whole_heart+half_heart+i)*40,30,40,40);
            }
        }
        for(int i = 0;i<20;i++){
            drawImage(ground,i*ground_width,height()-ground_height,ground_width,ground_height);
        }
        for(int i = 0;i<spine_array.size();i++) {
            drawImage(spine, spine_array.get(i).getX(), spine_array.get(i).getY(), 40, 40);
        }

        if(gold_array.size()!=0) {
            for (int i = 0; i < gold_array.size(); i++) {
                drawImage(frames_gold[currentFrame_gold], gold_array.get(i).getX(), gold_array.get(i).getY(), 40, 40);
            }
        }


        for(int i =0 ;i<floor_array.size();i++){
            drawImage(floor_image,floor_array.get(i).getX(),floor_array.get(i).getY(),80,30);
        }

        for(int i =0 ;i<floor1_array.size();i++){
            drawImage(floor_image,floor1_array.get(i).getX(),floor1_array.get(i).getY(),50,30);
        }

        //elevator
        for(int i =0 ;i<floor_elevator.size();i++){
            drawImage(elevator,floor_elevator.get(i).getX(),floor_elevator.get(i).getY(),70,30);
        }

        //spring
        for(int i =0 ;i<floor_spring.size();i++){
            if(!isOn){
                drawImage(spring_original,floor_spring.get(i).getX(),floor_spring.get(i).getY(),80,50);
            }
        }
        for (int j = 0; j < floor_spring.size(); j++) {
            if(isOn){
                drawImage(spring[currentFrame_spring], floor_spring.get(j).getX(), floor_spring.get(j).getY(), 80, 50);
            }

        }
        //moving floor
        for(int i =0 ;i<floor_move_platform.size();i++){
            drawImage(platform_image,floor_move_platform.get(i).getX(),floor_move_platform.get(i).getY(),60,30);
        }

        //monster
        if(monster_left) {
            drawImage(frames_monster[currentFrame_monster], monster.getX()+40, monster.getY(), -60, 80);
        }else {
            drawImage(frames_monster[currentFrame_monster], monster.getX(), monster.getY(), 60, 80);
        }
        if(is_draw) {
            if (is_left) {
                if (is_jump) {
                    if (speed_y <= 0) {
                        drawImage(frames_jump[currentFrame_jump], pos.getX() + 20, pos.getY() - 20, -20 * 2, 20 * 2);
                    } else {
                        drawImage(frames_jump[currentFrame_jump], pos.getX() + 20, pos.getY() - 60, -20 * 2, 20 * 2);
                    }
                } else {
                    drawImage(frames_walk[currentFrame_walk], pos.getX() + 30, pos.getY(), -20 * 2, 20 * 2);
                }
            } else {
                if (is_jump) {
                    if (speed_y <= 0) {
                        drawImage(frames_jump[currentFrame_jump], pos.getX()-20, pos.getY() - 20, 20 * 2, 20 * 2);
                    } else {
                        drawImage(frames_jump[currentFrame_jump], pos.getX()-20, pos.getY() - 60, 20 * 2, 20 * 2);
                    }
                } else {
                    drawImage(frames_walk[currentFrame_walk], pos.getX() , pos.getY(), 20 * 2, 20 * 2);
                }
            }
        }

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
        drawImage(frames_gold[currentFrame_gold], 700, 20, 40, 40);
        drawText(750,45,"x"+gold_count,"arial",20);
        drawImage(chest_key_gold,800,20,30,30);
        drawText(850,45,"x"+gold_key_count,"arial",20);
        drawImage(chest_key_silver,900,20,30,30);
        drawText(950,45,"x"+silver_key_count,"arial",20);

        //open gold chest
        if(gold_key_count!=0) {
            for (int i = 0; i < chest_array_gold.size(); i++) {
                if(distance(chest_array_gold.get(i).getX()+25,chest_array_gold.get(i).getY()+25,pos.getX()+20,pos.getY()+20)<=45){
                    drawImage(chest_block_gold,chest_array_gold.get(i).getX(),chest_array_gold.get(i).getY(),50,50);
                    isOpen_gold=true;
                    gold_key_count--;
                }
                if(isOpen_gold){
                    for(int j=0;j<15;j++){
                        gold_array.add(new Point2D.Double(random.nextInt(300)+600,chest_array_gold.get(i).getY()));
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
                if(distance(chest_array_sliver.get(i).getX()+25,chest_array_sliver.get(i).getY()+25,pos.getX()+20,pos.getY()+20)<=45){
                    drawImage(chest_block_sliver,chest_array_sliver.get(i).getX(),chest_array_sliver.get(i).getY(),50,50);
                    isOpen_sliver=true;
                }if(isOpen_sliver){
                    silver_key_count--;
                    for(int j=0;j<7;j++){
                        gold_array.add(new Point2D.Double(random.nextInt(200)+500,chest_array_sliver.get(i).getY()));
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

        drawImage(game_restart,350,380,70,70);
        drawImage(go_home,525,380,70,70);
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
            is_moving = true;
            is_left = false;

        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            is_moving = true;
            is_left = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            is_moving = false;

        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            is_moving = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP)
        {
            if(speed_y>=5) {
                speed_y = -5;
                is_jump = true;
            }

        }

    }
    public void keyTyped(KeyEvent e){

    }
    public void mousePressed(MouseEvent e) {
    }
    // Called whenever a mouse button is released
    public void mouseReleased(MouseEvent e) {}
    // Called whenever a mouse button is clicked
    public void mouseClicked(MouseEvent e) {
        if (game_over) {

            if (distance(e.getX(), e.getY(), 385, 415) <= 50) {
                stopAudioLoop(play_music);
                PlayPage1 playPage = new PlayPage1();
                playPage.start();
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
            if (distance(e.getX(), e.getY(), 385, 415) <= 50) {
                stopAudioLoop(play_music);
                PlayPage playPage = new PlayPage();
                playPage.start();
                mFrame.dispose();
            }//restart
            if (distance(e.getX(), e.getY(), 560, 415) <= 50) {
                stopAudioLoop(play_music);
                HomePage homePage = new HomePage();
                homePage.start();
                mFrame.dispose();
            }//go home
        }
    }
    // Called whenever the mouse cursor enters the game panel
    public void mouseEntered(MouseEvent e) {}
    // Called whenever the mouse cursor exits the game panel
    public void mouseExited(MouseEvent e) {}
}
