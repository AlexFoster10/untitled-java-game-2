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

    Thread gameThread; //repeats process again and again; clock

    //gamePanel constructor
    public GamePanel(){

        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); //sets panel size
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); //does drawing offscreen
    }

    public void startGameThread(){

        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {//when we start the gameThread, it will automatically call this run method

        while(gameThread != null){

            //update info i.e. player stats
            update();
            //draw screen each tick i.e. draw screen
            repaint();
        }
    }

    public void update(){

    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g; //like a paintbrush with more functionality

        g2.setColor(Color.white);

        g2.fillRect(100,100,tileSize,tileSize);

        g2.dispose(); //good practice to save memory
    }
}
