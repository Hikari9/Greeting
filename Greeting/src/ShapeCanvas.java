/*
 *  CS 21b Midterm Project Greeting (c) 2015
 *  by Rico Tiongson
 *  Submitted to Prof. John Boaz Lee on January 4, 2015
 */


/**
 *
 * @author Rico Tiongson
 */
import java.awt.Canvas;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;

// extend JComponent instead to avoid flicker
public class ShapeCanvas extends JComponent {
    
// public class ShapeCanvas extends Canvas {
    // attributes
	
    ArrayList<Shape> shapes = new ArrayList<>();
	
    // attribute changer methods

    public void addShape(Shape s) {
        shapes.add(s);
        repaint();
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    // overridden methods
    
    @Override
    public void paint(Graphics g) {
        // for (Shape s : shapes)
        //    s.draw(g);
        
        // avoid concurrent exception thingy
        for (int i = 0, I = shapes.size(); i < I; ++i)
            shapes.get(i).draw(g);
    }

    // animation methods (updates per frame)
	
    public void animateAll() {
        // for (Shape s : shapes)
        //    s.animate();
        
        // avoid concurrent exception thingy
        for (int i = 0, I = shapes.size(); i < I; ++i)
            shapes.get(i).animate();
        
        repaint();
    }
	
}
