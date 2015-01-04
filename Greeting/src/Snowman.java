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
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Snowman implements Shape {
    
    public Color outline = Color.BLACK, fill = new Color(1f, 1f, 1f, 0.95f);
    
    public int x, y;
    public float scale;
    public static final int DEFAULT_WIDTH = 100;
    public static final int DEFAULT_HEIGHT = 80;
    public static final int DEFAULT_EYE_WIDTH = 11;
    public static final int DEFAULT_EYE_HEIGHT = 10;
    public static final int DEFAULT_BALL_WIDTH = 20;
    public static final int EYE_HEIGHT_RELATIVE_POSITION = 31;
    public static final int EYE_WIDTH_RELATIVE_POSITION = 24;
    
    public Snowman(int x, int y, float scale) {
        this.x = x;
        this.y = y;
        this.scale = scale;
    }
    public Snowman(int x, int y) {
        this.x = x;
        this.y = y;
        this.scale = 1.0f;
    }
    
    public int getX() {
        return x;
    }
    
    double FLOAT_ALTITUDE = 10;
    double FLOAT_FREQUENCY = 0.002;
    
    int shakeDirection = 0;
    int shakeOffset = 5;
    
    public int getY() {
        return y + (int) Math.round(FLOAT_ALTITUDE * Math.sin(FLOAT_FREQUENCY * System.currentTimeMillis()));
    }
    
    public int getWidth() {
        return (int) Math.round(DEFAULT_WIDTH * scale);
    }
    
    public int getHeight() {
        return (int) Math.round(DEFAULT_HEIGHT * scale);
    }
    
    public float mouseOffsetX() {
        try {
            if (isShaking())
                return 100 * shakeDirection;
            return (float) (Greeting.Main.getMousePosition().getX() - center().getX());
        }
        catch(Exception ex) {
            return 0;
        }
    }
    
    public float mouseOffsetY() {
        try{
            if (shakeDirection != 0)
                return 0;
            return (float)(Greeting.Main.getMousePosition().getY() - center().getY());
        }
        catch(Exception ex) {
            return 0;
        }
    }
    
    public float getMouseAngle() {
        return (float) (Math.atan2(mouseOffsetY(), mouseOffsetX()));
    }
    
    public static final float MOUSE_LOOK_SCALE = 5f;
    
    public int getLeftEyeX() {
        float ex = getX() + EYE_WIDTH_RELATIVE_POSITION * scale;
        try{
            float dx = (float) Math.cos(getMouseAngle()) * MOUSE_LOOK_SCALE;
            if (dx > 0) dx *= 2;
            // if (!Greeting.Main.isAnimating())
            //     dx = 0;
            return Math.round(ex + dx);
        }
        catch (Exception e) {
            return Math.round(ex);
        }
    }
    
    public int getRightEyeX() {
        float ex = getX() + getWidth() - EYE_WIDTH_RELATIVE_POSITION * scale - getEyeWidth();
        try {
            float dx = (float) Math.cos(getMouseAngle()) * MOUSE_LOOK_SCALE;
            if (dx < 0) dx *= 2;
            // if (!Greeting.Main.isAnimating())
            //     dx = 0;
            return Math.round(ex + dx);
        }
        catch (Exception e) {
            return Math.round(ex);
        }
    }
    
    public int getEyeY() {
        float ey = getY() + EYE_HEIGHT_RELATIVE_POSITION * scale + (isBlinking() ? getEyeHeight() : 0.0f);
        try {
            float dy = (float) Math.sin(getMouseAngle()) * MOUSE_LOOK_SCALE;
            if (dy > 0) dy *= 2;
            // if (!Greeting.Main.isAnimating())
            //     dy = 0;
            return (int) Math.round(ey + dy);
        }
        catch (Exception e) {
            return (int) Math.round(ey); 
        }
    }
    
    public int getEyeWidth() {
        return (int) Math.round(DEFAULT_EYE_WIDTH * scale);
    }
    
    public int getEyeHeight() {
        return (int) Math.round(DEFAULT_EYE_HEIGHT * scale * (isBlinking() ? 0.5 : 1.0));
    }
    
    public Oval getFace() {
        return new Oval(getX(), getY(), getWidth(), getHeight(), null, fill);
    }
    
    public Oval getLeftEye() {
        return new Oval(getLeftEyeX(), getEyeY(), getEyeWidth(), getEyeHeight(), null, outline);
    }
    
    public Oval getRightEye() {
        return new Oval(getRightEyeX(), getEyeY(), getEyeWidth(), getEyeHeight(), null, outline);
    }
    
    public Rectangle getEyeLine() {
        return new Rectangle(getLeftEyeX(), getEyeY() + getEyeHeight() / 2, getRightEyeX() - getLeftEyeX() + getEyeWidth(), 1, null, outline);
    }
    
    public Polygon getHatBase() {
        try {
            if (originalHatBase == null)
                originalHatBase = Polygon.readFromFile("SantaHatBaseRaw.txt");
            Polygon p = new Polygon(originalHatBase);
            p.outline = outline;
            p.fill = Color.WHITE;
            p.translate(getX(), getY());
            return p;
        } catch (IOException ex) {
            System.err.println("Error reading 'SantaHatBaseRaw.txt'. Make sure file is valid.");
        }
        return null;
    }
        
    public Oval getTopBody() {
        Oval o = new Oval(center().x - 50 + shakeOffset * shakeDirection, getY() + getHeight() - 10, 100, 75, null, fill);
        return o;
    }
    
    public Oval getBottomBody() {
        Oval o = getTopBody();
        o.y += o.height - 30;
        o.x -= 15 + 2 * shakeOffset * shakeDirection;
        o.width += 30;
        o.height += 30;
        return o;
    }
    
    public Polygon getHatBody() {
        try {
            if (originalHatBody == null)
                originalHatBody = Polygon.readFromFile("SantaHatBodyRaw.txt");
            Polygon p = new Polygon(originalHatBody);
            p.outline = outline;
            p.fill = Color.RED;
            p.translate(getX(), getY());
            if (mouseOffsetX() < 0)
                p.flipAlongX((int) center().x);
            return p;
        } catch (IOException ex) {
            System.err.println("Error reading 'SantaHatBodyRaw.txt'. Make sure file is valid.");
        }
        return null;
    }
    
    public int getHatBallWidth() {
        return (int) Math.round(DEFAULT_BALL_WIDTH * scale);
    }
    
    public Circle getHatBall() {
        return new Circle(mouseOffsetX() >= 0 ? center().x + getWidth() / 3 : center().x - getWidth() / 3 - getHatBallWidth(), getY() - getHeight() * 3 / 4, getHatBallWidth(), outline, fill);
    }
    
    public Point center() {
        return new Point(getX() + getWidth() / 2, getY() + getHeight() / 2);
    }
    
    Polygon originalHatBody = null;
    Polygon originalHatBase = null;
    
    @Override
    public void draw(Graphics g) {
        calls++;
        if (calls > OPEN_BLINK + CLOSE_BLINK)
            calls = 0;
        
        
        Oval topBody = getTopBody();
        Oval bottomBody = getBottomBody();
        
        
        Oval face = getFace();
        Oval leftEye = getLeftEye();
        Oval rightEye = getRightEye();
        // Rectangle eyeLine = getEyeLine();
        
        Polygon hatBody = getHatBody();
        Polygon hatBase = getHatBase();
        Circle hatBall = getHatBall();
                
        topBody.draw(g);
        bottomBody.draw(g);
        
        face.draw(g);
        leftEye.draw(g);
        rightEye.draw(g);
        // eyeLine.draw(g);
        
        hatBody.draw(g);
        hatBase.draw(g);
        hatBall.draw(g);
        
        
    }
    
    int calls = 0;
    int OPEN_BLINK = 60;
    int CLOSE_BLINK = 5;
    
    
    double time = System.currentTimeMillis() / 1000.0;
    double speed = 1;
    
    @Override
    public void animate() {
        double ntime = System.currentTimeMillis() / 1000.0;
        double deltaTime = ntime - time;
        double need = this.mouseOffsetX();
        double addend = need * Math.min(1, deltaTime * speed);
        if (!isShaking()) this.x += addend;
        time = ntime;
    }
    
    public boolean isShaking() {
        return shakeDirection != 0;
    }
    
    public boolean isBlinking() {
        return calls >= OPEN_BLINK || isShaking();
    }
    
    public void shake() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new Shaker(), 500, 500);
    }
    
    class Shaker extends TimerTask {

        int count = 0;
        
        @Override
        public void run() {
            if (!Greeting.Main.animateButton.isSelected()) return;
            if (count < 4) {
                shakeDirection = ((count & 1) == 0 ? -1 : 1);
                Greeting.Main.canvas.addShape(new MusicalNote(center().x + shakeDirection * shakeOffset * 6, getY()));
                count++;
            }
            else {
                shakeDirection = 0;
                this.cancel();
            }
        }
        
    }
}
