package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    //screen settings
    final int originalTileSize = 16; // 16x16 tile size
    final int scale = 3; //

    final int tileSize = originalTileSize*scale; //
    final int maxScreenCol = 16; //
    final int maxScreenRow = 12; //
    final int screenWidth = tileSize*maxScreenCol; // 768 pixels
    final int screenHeight = tileSize*maxScreenRow; // 576 pixels

    //FPS
    int FPS = 60;
    KeyHandler keyH = new KeyHandler();
    Thread gameThread; //repeats process again and again; clock


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

        double drawInterval = 1000000000/FPS; //60th of a second
        double nextDrawTime = System.nanoTime()+drawInterval; //preps next frame

        while(gameThread != null){

            long CurrentTime = System.nanoTime();
            System.out.println("Current System Time: " + CurrentTime);
            //update info i.e. player stats
            update();
            //draw screen each tick i.e. draw screen
            repaint();

            try {
                double remainingTime = nextDrawTime-System.nanoTime(); //time till next frame
                remainingTime = remainingTime/1000000; //converts nano secs to mil secs

                if(remainingTime < 0){ //safety thing
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime); //sleep for time till next frame

                nextDrawTime += drawInterval; //gets next frame time
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update(){

        if(keyH.upPressed == true){
            playerY -= playerSpeed;
        }
        if(keyH.downPressed == true){
            playerY += playerSpeed;
        }
        if(keyH.leftPressed == true){
            playerX -= playerSpeed;
        }
        if(keyH.rightPressed == true){
            playerX += playerSpeed;
        }
    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g; //like a paintbrush with more functionality

        g2.setColor(Color.white);

        g2.fillRect(playerX,playerY,tileSize,tileSize);

        g2.dispose(); //good practice to save memory
    }
}
