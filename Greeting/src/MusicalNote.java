/*
 *  CS 21b Midterm Project Greeting (c) 2015
 *  by Rico Tiongson
 *  Submitted to Prof. John Boaz Lee on January 4, 2015
 */


/**
 *
 * @author Rico Tiongson
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class MusicalNote implements Shape {

    public int x, y, displacement, FALL = 2;
    public Color outline = Color.BLACK, fill = Color.BLACK;
   
    
    public MusicalNote(int x, int y) {
        this.x = x;
        this.y = y;
        this.displacement = 50;
    }
    
    public MusicalNote(Point p) {
        this(p.x, p.y);
    }
    
    public Rectangle getNeck() {
        return new Rectangle(x, y, 1, 30, outline, fill);
    }
    
    public Oval getHead() {
        return new Oval(x - 9, y + getNeck().height - 5, 10, 6, outline, fill);
    }
    
    @Override
    public void draw(Graphics g) {
        getNeck().draw(g);
        getHead().draw(g);
    }

    
    
    @Override
    public void animate() {
        if (displacement > 0) {
            y += FALL;
            displacement -= FALL;
        }
        else {
            outline = null;
            fill = null;
        }
    }
    
}
