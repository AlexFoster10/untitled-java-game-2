package main;

import entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    //screen settings
    final int originalTileSize = 16; // 16x16 tile size
    final int scale = 3; //

    public final int tileSize = originalTileSize*scale; //
    final int maxScreenCol = 16; //
    final int maxScreenRow = 12; //
    final int screenWidth = tileSize*maxScreenCol; // 768 pixels
    final int screenHeight = tileSize*maxScreenRow; // 576 pixels

    //FPS
    int FPS = 60;

    //declare objects
    KeyHandler keyH = new KeyHandler();
    Thread gameThread; //repeats process again and again; clock
    Player player = new Player(this,keyH);

    //set player defPos
    int playerX =100 , playerY = 100, playerSpeed = 4;
    //gamePanel constructor
    public GamePanel(){

        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); //sets panel size
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); //does drawing offscreen
        this.addKeyListener(keyH);
        setFocusable(true); //allows gamePanel to be focused to reciece key input
    }

    public void startGameThread(){

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {//when we start the gameThread, it will automatically call this run method

        double drawInterval = 1000000000 / FPS; //60th of a second
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval; //elapsed time divided by 60
            timer += currentTime -lastTime; //ads elapsed time to timer
            lastTime = currentTime;

            if(delta>=1){
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000){
                System.out.println("FPS: "+drawCount);
                timer = 0;
                drawCount = 0;
            }
        }
    }


    public void update(){

        player.update();
    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g; //like a paintbrush with more functionality

        player.draw(g2);

        g2.dispose(); //good practice to save memory
    }
}
