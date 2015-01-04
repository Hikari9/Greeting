package greeting.assets;

import java.awt.Canvas;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
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
    
    public void removeShape(Shape s) {
        shapes.remove(s);
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    // overridden methods
    
    @Override
    public void paint(Graphics g) {
        for (Shape s : shapes) {
            s.draw(g);
        }
    }

    // animation methods (updates per frame)
	
    public void animateAll() {
        for (Shape s : shapes) {
            s.animate();
        }
            repaint();
    }
	
}
