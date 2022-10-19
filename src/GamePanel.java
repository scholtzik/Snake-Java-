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
    static final int DElAY = 50;
    final int x[]= new int[UNIT_SIZE];
    final int y[]= new int[UNIT_SIZE];
    int bodyParts=6;
    int applesEaten=0;
    private int appleX;
    private int appleY;
    SnakeDirection direction = SnakeDirection.EAST;
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
        draw(g);
    }
    //draws grid for better visualization
    public void draw(Graphics g){
        if(running) {
//            for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
//                g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
//                g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
//            }
            g.setColor(Color.red);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

            for (int i = 0; i < bodyParts; i++) {
                //draws head of the snake
                if (i == 0) {
                    g.setColor(Color.green);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
                //draws rest of the body
                else {
//                    g.setColor(new Color(45, 180, 0));
                    g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }
            drawApplesEaten(g);
        }else {
            gameOver(g);
        }
    }


    private void drawApplesEaten(Graphics g) {
        g.setColor(Color.red);
        g.setFont(new Font("Times New Roman",Font.BOLD,40));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Score "+applesEaten,(SCREEN_WIDTH - metrics.stringWidth("Score "+applesEaten))/2 , g.getFont().getSize());
    }

    public void newApple(){
        appleX =random.nextInt((int)(SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        appleY =random.nextInt((int)(SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
    }
    public void move(){
        for(int i = bodyParts; i>0 ;i--){
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        switch (direction){
            case NORTH : y[0] = y[0] - UNIT_SIZE; break;
            case SOUTH: y[0] = y[0] + UNIT_SIZE; break;
            case WEST: x[0] = x[0] - UNIT_SIZE; break;
            case EAST: x[0] = x[0] + UNIT_SIZE; break;
        }

    }
    public void checkApple(){
        if((x[0] == appleX) && y[0] == appleY){
            bodyParts++;
            applesEaten++;
            newApple();
        }
    }
    public void checkCollisions(){
        for(int i = bodyParts; i> 0; i--){
            if ((x[0] == x[i] && y[0] == y[i])) {
                this.running = false;
                break;
            }
        }
        if(x[0]< 0){
            this.running=false;
        }
        if(x[0] > SCREEN_WIDTH){
            this.running=false;
        }
        if(y[0]< 0){
            this.running=false;
        }
        if(y[0] > SCREEN_HEIGHT){
            this.running=false;
        }
    }
    public void gameOver(Graphics g){
        drawApplesEaten(g);
        g.setColor(Color.red);
        g.setFont(new Font("Times New Roman",Font.BOLD,75));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Game over",(SCREEN_WIDTH - metrics.stringWidth("Game over"))/2 , SCREEN_HEIGHT/2);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(running){
            move();
            checkApple();
            checkCollisions();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            switch(e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    if(direction!= SnakeDirection.EAST){
                        direction=SnakeDirection.WEST;
                    }
                    break;
                case KeyEvent.VK_UP:
                    if(direction!= SnakeDirection.SOUTH) {
                        direction = SnakeDirection.NORTH;
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(direction!= SnakeDirection.WEST) {
                        direction = SnakeDirection.EAST;
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(direction!= SnakeDirection.NORTH) {
                        direction = SnakeDirection.SOUTH;
                    }
                    break;
            }
        }
    }
}
