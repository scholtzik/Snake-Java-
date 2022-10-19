import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 25;
    //to calculate how many game units we can fit
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
    static final int DElAY = 100;
    final int x[]= new int[UNIT_SIZE];
    final int y[]= new int[UNIT_SIZE];
    int bodyParts=6;
    int applesEaten=0;
    SnakeDirection direction = SnakeDirection.WEST;
    boolean running;
    Timer timer;
    Random random;
    GamePanel(){
        this.random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }
    public void startGame(){
        newApple();
        running=true;
        timer = new Timer(DElAY,this);
        timer.start();

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }
    public void draw(Graphics g){

    }
    public void newApple(){

    }
    public void move(){

    }
    public void checkApple(){

    }
    public void checkCollisions(){

    }
    public void gameOver(){

    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){

        }
    }
}
