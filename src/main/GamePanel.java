package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    //screen settings
    final int originalTileSize = 16; // 16x16 tile size
    final int scale = 3; //

    final int tileSize = originalTileSize*scale; //
    final int maxScreenCol = 16; //
    final int maxScreenRow = 12; //
    final int screenWidth = tileSize*maxScreenCol; // 768 pixels
    final int screenHeight = tileSize*maxScreenRow; // 576 pixels


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); //sets panel size
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // does drawing offscreen

    }

}
