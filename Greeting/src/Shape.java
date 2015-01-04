/*
 *  CS 21b Midterm Project Greeting (c) 2015
 *  by Rico Tiongson
 *  Submitted to Prof. John Boaz Lee on January 4, 2015
 */


/**
 *
 * @author Rico Tiongson
 */
import java.awt.Graphics;
import javax.swing.JComponent;

public interface Shape {
    public void draw(Graphics g);
    public void animate();
}
