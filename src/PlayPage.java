import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

public class PlayPage extends GameEngine {
    //group:23
    //member:22009158 Wenyuan Tian
    //member:22009155 Xinjie Zhang
    //member:22009156 Zihan Zhao
    int currentFrame_walk;
    int currentFrame_jump;
    int currentFrame_gold;
    int currentFrame_monster;
    int score;
    int second = 16;//Time after collision
    int gold_key_count=0;
    int silver_key_count=0;
    int gold_count=0;
    int life = 10;
    int whole_heart = 7;
    int half_heart = 0;
    Point2D pos = new Point2D.Double();
    Point2D floor = new Point2D.Double();
    Point2D monster_red = new Point2D.Double();
    Point2D Room = new Point2D.Double();

    ArrayList<Point2D> floor_array = new ArrayList<>();
    ArrayList<Point2D> floor1_array = new ArrayList<>();
    ArrayList<Point2D> floor2_array = new ArrayList<>();
    ArrayList<Point2D> gold_array = new ArrayList<>();
    ArrayList<Point2D> spine_array = new ArrayList<>();
    ArrayList<Point2D> box = new ArrayList<>();
    ArrayList<Point2D> key_array_gold = new ArrayList<>();
    ArrayList<Point2D> key_array_sliver= new ArrayList<>();
    ArrayList<Point2D> chest_array_gold = new ArrayList<>();
    ArrayList<Point2D> chest_array_sliver = new ArrayList<>();
    double animTime;
    boolean is_moving = false;
    boolean is_left = false;
    boolean is_jump = false;
    boolean is_collision = true;
    boolean in_ground = true;
    boolean in_floor = false;
    boolean is_draw = true;
    boolean monster_red_left = false;
    boolean game_start = true;
    boolean game_over = false;

    boolean isOpen_gold = false;
    boolean isOpen_sliver = false;
    ArrayList<Boolean> box_stop = new ArrayList<>();
    ArrayList<Boolean> box_stop_left = new ArrayList<>();
    ArrayList<Boolean> box_stop_right = new ArrayList<>();
    double speed_y = 5;
    Image[] frames_walk ;
    Image[] frames_jump;
    Image[] frames_gold;
    Image[] heart;
    Image[] frames_monster;
    Image[] star_calculate;
    Image heart_image ;
    Image room;
    Image sheet_walk1;
    Image sheet_walk2;
    Image sheet_jump;
    Image play_background;
    Image ground;
    Image gold;
    Image spine;
    Image box_image;
    Image monster_image;
    Image chest_open_gold;
    Image chest_open_sliver;
    Image chest_block_gold;
    Image chest_block_sliver;
    Image chest_key_gold;
    Image chest_key_silver;
    Image chest_image;
    Image game_calculate;
    Image game_fail;
    Image game_button;
    Image game_complete;
    Image game_restart;
    Image game_next;
    Image go_home;

    Image star ;
    double ground_width;
    double ground_height;
    double floor_width;
    Image door;
    Random random = new Random();
    Image floor_image;
    AudioClip eat_gold;

    AudioClip eat_key;

    AudioClip play_music;
    public  void start() {
        createGame(this, 15); // Set the frame rate of the game
    }

    public void init() {
        super.init();
        setWindowSize(1000, 660);//50,33
        door = loadImage("src/Image/door.png");
        eat_gold = loadAudio("src/Music/button.wav");
        play_music = loadAudio("src/Music/play_music.wav");
        startAudioLoop(play_music);
        eat_key =loadAudio("src/Music/key_music.wav");
        play_background = loadImage("src/Image/Background2.png");
        ground = loadImage("src/Image/ground.png");
        floor_image = loadImage("src/Image/floor.png");
        ground_width = 50;
        ground_height = 60;
        frames_walk = new Image[4];
        frames_jump = new Image[3];
        frames_gold = new Image[6];
        frames_monster = new Image[4];
        heart = new Image[9];
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
        room = loadImage("src/Image/room.png");

        for (int i = 0; i < 2; i++) {
            frames_walk[i] = subImage(sheet_walk1, 25 * i, 0, 25, 25);
        }
        for (int i = 2; i < 4; i++) {
            frames_walk[i] = subImage(sheet_walk2, 25 * (i - 2), 0, 25, 25);
        }
        for (int i = 0; i < 3; i++) {
            frames_jump[i] = subImage(sheet_jump, 28 * i, 0, 28, 25);
        }
        for (int i = 0; i < 6; i++) {
            frames_gold[i] = subImage(gold, 165 * i, 0, 165, 150);
        }
        for (int i = 0; i < 9; i++) {
            heart[i] = subImage(heart_image, 166 * i, 0, 166, 132);
        }
        for (int i = 0; i < 4; i++) {
            frames_monster[i] = subImage(monster_image, 91 * i, 0, 91, 82);
        }
        for(int i = 0;i<4;i++){
            star_calculate[i] = subImage(star,263*i,0,263,135);
        }
        chest_open_gold = subImage(chest_image, 0, 170, 170, 130);
        chest_open_sliver = subImage(chest_image,0,0,170,130);

        chest_block_gold = subImage(chest_image,170,170,170,125);
        chest_block_sliver = subImage(chest_image,170,0,170,130);

        chest_key_gold = subImage(chest_image,340,175,165,125);
        chest_key_silver = subImage(chest_image,340,30,165,125);

        key_array_gold.add(new Point2D.Double(900,550));
        key_array_sliver.add(new Point2D.Double(300,430));

        chest_array_gold.add(new Point2D.Double(900,150));
        chest_array_sliver.add(new Point2D.Double(30,110));

        //floor  150
        gold_array.add(new Point2D.Double(85, 360));
        gold_array.add(new Point2D.Double(135,360));
        gold_array.add(new Point2D.Double(185,360));
        floor_array.add(new Point2D.Double(80, 400));

        floor_array.add(new Point2D.Double(0, 160));

        gold_array.add(new Point2D.Double(280, 300));
        gold_array.add(new Point2D.Double(330,300));
        gold_array.add(new Point2D.Double(380,300));
        floor_array.add(new Point2D.Double(280, 340));

        gold_array.add(new Point2D.Double(510, 300));

        gold_array.add(new Point2D.Double(610,300));
        floor_array.add(new Point2D.Double(500, 340));

        gold_array.add(new Point2D.Double(710, 360));
        gold_array.add(new Point2D.Double(760,360));
        gold_array.add(new Point2D.Double(810,360));
        floor_array.add(new Point2D.Double(700, 400));



        //floor1  300
        floor1_array.add(new Point2D.Double(700,200));
        floor1_array.add(new Point2D.Double(300, 120));
        //floor2  70
        gold_array.add(new Point2D.Double(190,200));
        floor2_array.add(new Point2D.Double(180,240));
        gold_array.add(new Point2D.Double(450,400));
        floor2_array.add(new Point2D.Double(440,440));
        gold_array.add(new Point2D.Double(470,160));
        floor2_array.add(new Point2D.Double(460,200));
        gold_array.add(new Point2D.Double(910,290));
        floor2_array.add(new Point2D.Double(900,330));
        spine_array.add(new Point2D.Double(460,560));
        spine_array.add(new Point2D.Double(560,310));
        spine_array.add(new Point2D.Double(800,170));
        pos.setLocation(60, 560);
        Room.setLocation(430,25);
        box.add(new Point2D.Double(550,560));
        box.add(new Point2D.Double(300,560));
        for(int i = 0;i<box.size();i++){
            box_stop.add(false);
            box_stop_left.add(false);
            box_stop_right.add(false);
        }
        monster_red.setLocation(600, 525);
    }

    @Override
    public void update(double dt) {
        if (game_start&&!game_over) {
            animTime += dt;
            currentFrame_gold = getFrame(0.7, 6);
            currentFrame_monster = getFrame(0.5, 4);
            //monster move
            if (monster_red_left) {
                monster_red.setLocation(monster_red.getX() - 5, monster_red.getY());
            } else {
                monster_red.setLocation(monster_red.getX() + 5, monster_red.getY());
            }
            if (monster_red.getX() >= 1000 - 60) {
                monster_red_left = true;
            }
            for (int i = 0; i < box.size(); i++) {
                if (distance(monster_red.getX() + 30, monster_red.getY() + 40, box.get(i).getX() + 20, box.get(i).getY() + 20) <= 40) {
                    monster_red_left = !monster_red_left;
                }
            }
            for (int i = 0; i < spine_array.size(); i++) {
                if (distance(monster_red.getX() + 30, monster_red.getY() + 40, spine_array.get(i).getX() + 20, spine_array.get(i).getY() + 20) <= 40) {
                    monster_red_left = !monster_red_left;
                }
            }

            //collection with monster
            if (second == 16) {
                if (distance(monster_red.getX() + 30, monster_red.getY() + 40, pos.getX() + 20, pos.getY() + 20) <= 40) {
                    life -= 2;
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
            //change life
            if (life >= 0) {
                if (life % 2 == 0) {
                    whole_heart = life / 2;
                    half_heart = 0;
                } else {
                    half_heart = 1;
                    whole_heart = life / 2;
                }
            }else {
                game_over = true;
                whole_heart = 0;
                half_heart = 0;
                is_draw = true;
                paintComponent();

            }

            //eat gold
            if (gold_array.size() != 0) {
                for (int i = 0; i < gold_array.size(); i++) {
                    if (distance(gold_array.get(i).getX() + 20, gold_array.get(i).getY() + 20, pos.getX() + 20, pos.getY() + 20) <= 40) {
                        playAudio(eat_gold);
                        gold_array.remove(i);
                        gold_count++;
                        i--;
                    }
                }
            }
            //push box
            for (int i = 0; i < box.size(); i++) {
                for (int j = 0; j < spine_array.size(); j++) {
                    if (!box_stop.get(i)) {
                        if (distance(box.get(i).getX(), box.get(i).getY(), pos.getX(), pos.getY()) <= 40) {
                            if (box.get(i).getY() == pos.getY()) {
                                if (box.get(i).getX() > pos.getX()) {
                                    if (is_moving) {
                                        if (!is_left) {
                                            box.get(i).setLocation(box.get(i).getX() + 20, box.get(i).getY());
                                        }
                                    }

                                } else {
                                    if (is_moving) {
                                        if (is_left) {
                                            box.get(i).setLocation(box.get(i).getX() - 20, box.get(i).getY());
                                        }
                                    }

                                }
                            }
                        }
                    }
                    if (box.get(i).getY() == spine_array.get(j).getY()) {
                        if (box.get(i).getX() >= 1000 - 40) {
                            box.get(i).setLocation(960, box.get(i).getY());
                            box_stop.add(i, true);
                            box_stop.remove(i + 1);
                            box_stop_right.add(i, true);
                            box_stop_right.remove(i + 1);

                        } else if (box.get(i).getX() <= 0) {
                            box.get(i).setLocation(0, box.get(i).getY());
                            box_stop.add(i, true);
                            box_stop.remove(i + 1);
                            box_stop_left.add(i, true);
                            box_stop_left.remove(i + 1);
                        } else if (distance(box.get(i).getX() + 20, box.get(i).getY() + 20, spine_array.get(j).getX() + 20, spine_array.get(j).getY() + 20) <= 40) {
                            if (box.get(i).getX() <= spine_array.get(j).getX()) {
                                box.get(i).setLocation(spine_array.get(j).getX() - 40, box.get(i).getY());
                                box_stop.add(i, true);
                                box_stop.remove(i + 1);
                                box_stop_right.add(i, true);
                                box_stop_right.remove(i + 1);
                            }
                            if (box.get(i).getX() + 40 >= spine_array.get(j).getX() + 40) {
                                box.get(i).setLocation(spine_array.get(j).getX() + 40, box.get(i).getY());
                                box_stop.add(i, true);
                                box_stop.remove(i + 1);
                                box_stop_left.add(i, true);
                                box_stop_left.remove(i + 1);

                            }

                        } else {
                            box_stop.add(i, false);
                            box_stop.remove(i + 1);
                            box_stop_right.add(i, false);
                            box_stop_right.remove(i + 1);
                            box_stop_left.add(i, false);
                            box_stop_left.remove(i + 1);
                        }


                    }
                }
            }
            //pos and spine collection
            for (int i = 0; i < spine_array.size(); i++) {
                if (second == 16) {
                    if (distance(pos.getX() + 20, pos.getY() + 20, spine_array.get(i).getX() + 20, spine_array.get(i).getY()+20) < 40) {
                        life--;
                        second--;
                    }
                }
            }
            //get keys
            //gold_key
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
            //sliver_key
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
            //pos move left and right
            if (is_moving) {

                if (is_left) {
                    pos.setLocation(pos.getX() - 20, pos.getY());
                } else {
                    pos.setLocation(pos.getX() + 20, pos.getY());
                }
                for (int i = 0; i < box.size(); i++) {
                    if (pos.getY() == box.get(i).getY()) {
                        if (!box_stop.get(i)) {
                            if (pos.getX() < box.get(i).getX()) {
                                if (!is_left) {
                                    if (pos.getX() >= box.get(i).getX() - 40) {
                                        pos.setLocation(box.get(i).getX() - 40, pos.getY());
                                    }
                                } else {
                                    if (pos.getX() <= 0)
                                        pos.setLocation(0, pos.getY());
                                }
                            } else {
                                if (is_left) {
                                    if (pos.getX() <= box.get(i).getX() + 40) {
                                        pos.setLocation(box.get(i).getX() + 40, pos.getY());
                                    }
                                } else {
                                    if (pos.getX() >= 1000 - 20 * 2) {
                                        pos.setLocation(1000 - 20 * 2, pos.getY());
                                    }

                                }
                            }
                        } else {
                            if (pos.getX() < box.get(i).getX()) {
                                if (box_stop_right.get(i)) {
                                    if (!is_left) {
                                        if (distance(pos.getX(), pos.getY(), box.get(i).getX(), box.get(i).getY()) <= 40) {
                                            pos.setLocation(box.get(i).getX() - 40, pos.getY());
                                        }
                                    }
                                }
                                if (box_stop_left.get(i)) {
                                    if (!is_left) {
                                        box_stop.add(i, false);
                                        box_stop.remove(i + 1);
                                        box_stop_left.add(i, false);
                                        box_stop_left.remove(i + 1);
                                    }
                                }
                            }
                            if (pos.getX() > box.get(i).getX()) {
                                if (box_stop_left.get(i)) {
                                    if (is_left) {
                                        if (distance(pos.getX(), pos.getY(), box.get(i).getX() + 40, box.get(i).getY()) <= 40) {
                                            pos.setLocation(box.get(i).getX() + 40, pos.getY());
                                        }
                                    }
                                }
                                if (box_stop_right.get(i)) {
                                    if (is_left) {
                                        box_stop.add(i, false);
                                        box_stop.remove(i + 1);
                                        box_stop_right.add(i, false);
                                        box_stop_right.remove(i + 1);
                                    }
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
                }
                currentFrame_walk = getFrame(0.3, 4);
            } else {
                currentFrame_walk = 0;
            }

            //while pos down floor
            if (is_collision && in_floor) {
                if (pos.getX() < floor.getX() || pos.getX() >= floor.getX() + floor_width) {
                    speed_y = 0;
                    is_collision = false;
                }
            }

            //judge in which floor
            if (!is_collision) {
                for (int i = 0; i < box.size(); i++) {
                    Collision(pos, box.get(i), 40);
                }
                for (int i = 0; i < floor_array.size(); i++) {
                    Collision(pos, floor_array.get(i), 150);
                    if (is_collision) {
                        break;
                    }
                }
                for (int i = 0; i < floor1_array.size(); i++) {
                    Collision(pos, floor1_array.get(i), 300);
                    if (is_collision) {
                        break;
                    }
                }
                for (int i = 0; i < floor2_array.size(); i++) {
                    Collision(pos, floor2_array.get(i), 50);
                    if (is_collision) {
                        break;
                    }
                }
            }
            // pos jump
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
                            pos.setLocation(pos.getX(), pos.getY() + 30);
                            speed_y += 1;
                            currentFrame_jump = 2;
                        } else {

                            pos.setLocation(pos.getX(), 660 - ground_height - 20 * 2);
                            speed_y = 5;
                            is_collision = true;
                            is_jump = false;
                            floor = new Point2D.Double();

                        }
                    } else {//in floor
                        pos.setLocation(pos.getX(), floor.getY() - 20 * 2);
                        speed_y = 5;
                        is_jump = false;
                        is_collision = true;
                    }

                }
            }
            if(distance(pos.getX()+20,pos.getY()+20,Room.getX()+50,Room.getY()+50)<=40){
                game_start = false;


            }
        }
    }

    public int getFrame(double d, int num_frames) {
        return (int) Math.floor(((animTime % d) / d) * num_frames);
    }

    public void Collision(Point2D pos, Point2D floor1, double floor_width1) {
        if(pos.getX()>=floor1.getX()&&pos.getX()<floor1.getX()+floor_width1) {//pos on the floor
            if (pos.getY() >= floor1.getY() && pos.getY() - floor1.getY() < 40) {
                if (speed_y > 0) {
                    is_collision = true;
                    in_ground = false;
                    speed_y = 5;
                    floor = floor1;
                    floor_width = floor_width1;
                    in_floor = true;
                }else {
                    if(pos.getY()<=floor1.getY()+30) {
                        speed_y = 0;
                        in_ground = true;
                        is_collision = true;
                    }
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
            //background
            drawImage(play_background, 0, 0, 1000, 660);
            for (int i = 0; i < spine_array.size(); i++) {
                drawImage(spine, spine_array.get(i).getX(), spine_array.get(i).getY(), 40, 40);
            }
            drawImage(door,40,520,80,100);
            //life
            for (int i = 0; i < whole_heart; i++) {
                drawImage(heart[i], 20 + i * 40, 30, 40, 40);
            }
            if (half_heart == 1) {
                drawImage(heart[7], 20 + whole_heart * 40, 30, 40, 40);
            }
            if (whole_heart + half_heart < 7) {
                for (int i = 0; i < 5 - whole_heart - half_heart; i++) {
                    drawImage(heart[8], 20 + (whole_heart + half_heart + i) * 40, 30, 40, 40);
                }
            }
            //ground
            for (int i = 0; i < 20; i++) {
                drawImage(ground, i * ground_width, height() - ground_height, ground_width, ground_height);
            }
            //box
            for (int i = 0; i < box.size(); i++) {
                drawImage(box_image, box.get(i).getX(), box.get(i).getY(), 40, 40);
            }
            //gold
            if (gold_array.size() != 0) {
                for (int i = 0; i < gold_array.size(); i++) {
                    drawImage(frames_gold[currentFrame_gold], gold_array.get(i).getX(), gold_array.get(i).getY(), 40, 40);
                }
            }
            //gold key
            if (key_array_gold.size() != 0) {
                for (int i = 0; i < key_array_gold.size(); i++) {
                    drawImage(chest_key_gold, key_array_gold.get(i).getX(), key_array_gold.get(i).getY(), 40, 40);
                }
            }
            //sliver key
            if (key_array_sliver.size() != 0) {
                for (int i = 0; i < key_array_sliver.size(); i++) {
                    drawImage(chest_key_silver, key_array_sliver.get(i).getX(), key_array_sliver.get(i).getY(), 40, 40);
                }
            }
            //chest
            if (!isOpen_gold) {
                if (chest_array_gold.size() != 0) {
                    for (int i = 0; i < chest_array_gold.size(); i++) {
                        drawImage(chest_open_gold, chest_array_gold.get(i).getX(), chest_array_gold.get(i).getY(), 50, 50);
                    }
                }
            }

            if (!isOpen_sliver) {
                if (chest_array_sliver.size() != 0) {
                    for (int i = 0; i < chest_array_sliver.size(); i++) {
                        drawImage(chest_open_sliver, chest_array_sliver.get(i).getX(), chest_array_sliver.get(i).getY(), 50, 50);
                    }
                }
            }

            //calculate count
            drawImage(frames_gold[0], 700, 20, 40, 40);
            drawText(750, 45, "x" + gold_count, "arial", 20);
            drawImage(chest_key_gold, 800, 20, 30, 30);
            drawText(850, 45, "x" + gold_key_count, "arial", 20);
            drawImage(chest_key_silver, 900, 30, 30, 30);
            drawText(950, 45, "x" + silver_key_count, "arial", 20);


            //open gold chest
            if (gold_key_count != 0) {
                for (int i = 0; i < chest_array_gold.size(); i++) {
                    if (distance(chest_array_gold.get(i).getX(), chest_array_gold.get(i).getY(), pos.getX() + 20, pos.getY() + 20) <= 50) {
                        drawImage(chest_block_gold, chest_array_gold.get(i).getX(), chest_array_gold.get(i).getY(), 50, 50);
                        isOpen_gold = true;
                        gold_key_count--;
                    }
                    if (isOpen_gold) {
                        for (int j = 0; j < 15; j++) {
                            gold_array.add(new Point2D.Double(random.nextInt(15) * 20 + 700, chest_array_gold.get(i).getY() - 20));
                        }
                    }

                }
            }
//open sliver chest
            if (silver_key_count != 0) {
                for (int i = 0; i < chest_array_sliver.size(); i++) {
                    if (distance(chest_array_sliver.get(i).getX(), chest_array_sliver.get(i).getY(), pos.getX() + 20, pos.getY() + 20) <= 50) {
                        drawImage(chest_block_sliver, chest_array_sliver.get(i).getX(), chest_array_sliver.get(i).getY(), 50, 50);
                        isOpen_sliver = true;
                    }
                    if (isOpen_sliver) {
                        silver_key_count--;
                        for (int j = 0; j < 7; j++) {
                            gold_array.add(new Point2D.Double(random.nextInt(10) * 14, chest_array_sliver.get(i).getY()));
                        }
                    }

                }
            }
            //draw floor
            for (int i = 0; i < floor_array.size(); i++) {
                drawImage(floor_image, floor_array.get(i).getX(), floor_array.get(i).getY(), 150, 30);
            }
            for (int i = 0; i < floor1_array.size(); i++) {
                drawImage(floor_image, floor1_array.get(i).getX(), floor1_array.get(i).getY(), 300, 30);
            }
            for (int i = 0; i < floor2_array.size(); i++) {
                drawImage(floor_image, floor2_array.get(i).getX(), floor2_array.get(i).getY(), 50, 30);
            }
            //final room
            drawImage(room, Room.getX(), Room.getY(), 100, 100);
            //monster
            if (monster_red_left) {
                drawImage(frames_monster[currentFrame_monster], monster_red.getX() + 60, monster_red.getY(), -60, 80);
            } else {
                drawImage(frames_monster[currentFrame_monster], monster_red.getX(), monster_red.getY(), 60, 80);
            }


            //draw pos
            if (is_draw) {
                if (is_left) {
                    if (is_jump) {
                        if (speed_y <= 0) {
                            drawImage(frames_jump[currentFrame_jump], pos.getX() + 30, pos.getY(), -20 * 2, 20 * 2);
                        } else {
                            drawImage(frames_jump[currentFrame_jump], pos.getX() + 30, pos.getY() - 50, -20 * 2, 20 * 2);
                        }
                    } else {
                        drawImage(frames_walk[currentFrame_walk], pos.getX() + 30, pos.getY(), -20 * 2, 20 * 2);
                    }
                } else {
                    if (is_jump) {
                        if (speed_y <= 0) {
                            drawImage(frames_jump[currentFrame_jump], pos.getX() - 10, pos.getY(), 20 * 2, 20 * 2);
                        } else {
                            drawImage(frames_jump[currentFrame_jump], pos.getX() - 10, pos.getY() - 50, 20 * 2, 20 * 2);
                        }
                    } else {
                        drawImage(frames_walk[currentFrame_walk], pos.getX(), pos.getY(), 20 * 2, 20 * 2);
                    }
                }
            }
            if(!game_start){
                //go to next level
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
        if(score>=37*5*0.8){
            drawImage(star_calculate[0],350,270,250,100);
        }else if(score>=37*5*0.5){
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
                is_collision = false;
            }

        }

    }
    public void keyTyped(KeyEvent e){
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
                PlayPage2 playPage2 = new PlayPage2();
                playPage2.start();
                mFrame.dispose();
            }
        }
    }
    // Called whenever the mouse cursor enters the game panel
    public void mouseEntered(MouseEvent e) {}
    // Called whenever the mouse cursor exits the game panel
    public void mouseExited(MouseEvent e) {}
}
