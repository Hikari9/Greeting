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
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

public final class Layers extends ShapeCanvas {
        
    int treecenter = 210;
    
    public Layers() {
        addBackground();
        addTree();
        
        addMerryChristmas();
        addSnowman();
        addSnowball();
        // addFallingPresents();
    }
    
    void addSnowman() {
        final Snowman snowman = new Snowman(0, 100, 0.7f);
        final StringShape clickme = new StringShape("Click me!", 0, 0, 16) {
            @Override
            public void animate() {
                this.x = snowman.getX() + 2;
                this.y = snowman.getY() + 150 + (int) Math.round(5 * Math.sin(System.currentTimeMillis() / 400.0));
            }
        };
        
        MouseListener ml = new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (!Greeting.Main.animateButton.isSelected()) return;
                if (snowman.isShaking()) return;
                Point mouse = e.getPoint();
                if (Oval.pointInOval(mouse, snowman.getBottomBody()) || Oval.pointInOval(mouse, snowman.getTopBody()) || Oval.pointInOval(mouse, snowman.getFace()))
                    snowman.shake();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
            
        };
        this.addMouseListener(ml);
        
        
        
        addShape(snowman);
        addShape(clickme);
    }
    
    void addMerryChristmas() {
        
        final int padding = 20;
        final StringShape merry = new StringShape("Merry Christmas ^_^ (fr Rico)", 310, 360, 19) {
            @Override
            public void animate() {
            }
        };
        
        addShape(merry);
        
    }
    
    BrickWall brickwall, brickwall2;
    
    void addBackground() {
        Rectangle ceiling = new Rectangle(0, 0, Greeting.Main.getWidth(), Greeting.Main.getHeight() / 2, null, new Color(0x006000));
		Rectangle floor = new Rectangle(0, ceiling.height, ceiling.width, ceiling.height, Color.BLACK, new Color(0xFFFFF0));
        
        brickwall = new BrickWall(0, treecenter - 10, 0, 350, 30, 300, 20, 5, Color.WHITE, new Color(230, 118, 70));
        brickwall2 = new BrickWall(brickwall.right, Greeting.Main.getWidth(), brickwall.rightTop, brickwall.rightBottom, 10, 325, 20, 6, brickwall.outline, brickwall.fill);
        // Brick b = new Brick("")
        addShape(ceiling);
		addShape(floor);
        addShape(brickwall);
        addShape(brickwall2);
    }
    
    void addTree() {
        addShape (new Tree(treecenter));
    }
    
    void addFallingPresents() {
        Greeting.Main.addTimer(new Timer(3000, new PresentRandomizer()));
    }
    
    void addSnowball() {
        Greeting.Main.addTimer(new Timer(50, new SnowballRandomizer()));
    }
    
    
    
    class PresentRandomizer implements ActionListener {
        int randomInt(int start, int end) {
            int r = (int) (Math.random() * (end - start)) + start;
            return r;
        }
        Color randomColor() {
            return new Color(randomInt(0, 255), randomInt(0, 255), randomInt(0, 255));
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                Present present = new Present(randomInt(0, Greeting.Main.getWidth()), -99, randomInt(20, 60), Color.BLACK, randomColor(), randomColor()) {
                    int targetY = -1;
                    boolean isFalling = true;
                    
                    @Override
                    public void animate() {
                        if (isFalling)
                            fall();
                        else
                            super.animate();
                        
                    }
                    
                    protected void fall() {
                        if (targetY == -1) {
                            Line lower = null;
                            if (x <= brickwall.right) 
                                lower = brickwall.getLine(brickwall.rows);
                            else
                                lower = brickwall2.getLine(brickwall.rows);
                            targetY = randomInt((int) Math.ceil(lower.yValue(x)), Greeting.Main.getHeight() - height);
                        }
                        else if (y <= targetY) {
                            y += 5;
                        }
                        else
                            isFalling = false;
                        
                    }
                };
                addShape(present);
            } catch (Exception ex) {
                Logger.getLogger(Layers.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
        
    class SnowballRandomizer implements ActionListener {
        final int[] colorChanger = {0, 1, 2, 10}; 

        int randomInt(int start, int end) {
            int r = (int) (Math.random() * (end - start)) + start;
            return r;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Snowball snowball = new Snowball(randomInt(0, Greeting.Main.getWidth()), null, new Color(randomInt(200, 255), randomInt(200, 255), randomInt(200, 255), 250), colorChanger) {
                    
                    int targetY = -1;
                    
                    @Override
                    protected void fall() {
                        if (targetY == -1) {
                            Line lower = null;
                            if (x <= brickwall.right) 
                                lower = brickwall.getLine(brickwall.rows);
                            else
                                lower = brickwall2.getLine(brickwall.rows);
                            targetY = randomInt((int) Math.ceil(lower.yValue(x)), Greeting.Main.getHeight());
                        }
                        else if (y <= targetY) {
                            y += FALL_PIXELS_PER_FRAME;
                        }
                        else
                            vanish(50000);
                    }
                };
                addShape(snowball);
            } catch (Exception ex) {
                Logger.getLogger(Layers.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
}