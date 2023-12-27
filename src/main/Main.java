package main;

import javax.swing.JFrame;

class Main {
    public static void main(String[] args){
        System.out.println("Hello World");
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Untitled Java Game");
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack(); //sets window prefs to those specified in sub comps i.e. GamePanel
        window.setLocationRelativeTo(null);
        window.setVisible(true);


    }
}