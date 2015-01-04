/*
 *  CS 21b Midterm Project Greeting (c) 2015
 *  by Rico Tiongson
 *  Submitted to Prof. John Boaz Lee on January 4, 2015
 */


/**
 *
 * @author Rico Tiongson
 */
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class Greeting extends JFrame {
    public static Greeting Main = null;
    public int FPS;
    public ShapeCanvas canvas;
    List<Timer> timers;
    
    public JToggleButton animateButton;
    
    public static void main(String[] args) {
        new Greeting(600, 425, 24).setVisible(true);
    }
    
    public void addTimer(Timer t) {
        if (timers == null) timers = new ArrayList<>();
        timers.add(t);
        if (animateButton.isSelected())
            t.start();
        else
            t.stop();
    }
    
    public Greeting(int width, int height, int FPS) {
        super();
        this.FPS = FPS;
        Main = this;
        this.setSize(width, height);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        timers = new ArrayList<>();
        animateButton = new JToggleButton("Animate");
        animateButton.addActionListener(
            new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    JToggleButton button = (JToggleButton) e.getSource();
                    if (timers == null) return;
                    if (button.isSelected()) {
                        for (int i = 0, I = timers.size(); i < I; ++i)
                            timers.get(i).start();
                        button.setText("Pause");
                    }
                    else {
                        for (int i = 0, I = timers.size(); i < I; ++i)
                            timers.get(i).start();
                        button.setText("Animate");
                    }
                }
                    
            }
        );
        
        this.addTimer(new Timer(1000 / FPS, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.animateAll();
            }
        }));
        
        
        
        canvas = new Layers();
        this.add(canvas);
        this.add(animateButton, BorderLayout.SOUTH);
    }
}
