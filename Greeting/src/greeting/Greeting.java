/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greeting;

import greeting.assets.ShapeCanvas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Rico
 */
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
