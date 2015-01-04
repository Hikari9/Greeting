/*
 *  CS 21b Midterm Project Greeting (c) 2015
 *  by Rico Tiongson
 *  Submitted to Prof. John Boaz Lee on January 4, 2015
 */


/**
 *
 * @author Rico Tiongson
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Greeting extends JFrame {
    public static Greeting Main = null;
    public int FPS;
    public ShapeCanvas canvas;
    public static void main(String[] args) {
        new Greeting(600, 400, 24).setVisible(true);
    }
    public Greeting(int width, int height, int FPS) {
        super();
        this.FPS = FPS;
        Main = this;
        this.setSize(width, height);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        canvas = new Layers();
        
        new Timer(1000 / FPS, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.animateAll();
            }
        }).start();
        
        this.add(canvas);
    }
}
