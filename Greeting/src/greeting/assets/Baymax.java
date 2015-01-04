package greeting.assets;

import greeting.Greeting;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.IOException;

public class Baymax implements Shape {
    public int x, y;
    public float scale;
    public static final int DEFAULT_WIDTH = 100;
    public static final int DEFAULT_HEIGHT = 80;
    public static final int DEFAULT_EYE_WIDTH = 11;
    public static final int DEFAULT_EYE_HEIGHT = 10;
    public static final int DEFAULT_BALL_WIDTH = 20;
    public static final int EYE_HEIGHT_RELATIVE_POSITION = 31;
    public static final int EYE_WIDTH_RELATIVE_POSITION = 24;
    
    public Baymax(int x, int y, float scale) {
        this.x = x;
        this.y = y;
        this.scale = scale;
    }
    public Baymax(int x, int y) {
        this.x = x;
        this.y = y;
        this.scale = 1.0f;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public int getWidth() {
        return (int) Math.round(DEFAULT_WIDTH * scale);
    }
    
    public int getHeight() {
        return (int) Math.round(DEFAULT_HEIGHT * scale);
    }
    
    public float mouseOffsetX() {
        try {
            return (float) (Greeting.Main.getMousePosition().getX() - center().getX());
        }
        catch(Exception ex) {
            return 0;
        }
    }
    
    public float mouseOffsetY() {
        try{
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
        float ex = x + EYE_WIDTH_RELATIVE_POSITION * scale;
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
        float ex = x + getWidth() - EYE_WIDTH_RELATIVE_POSITION * scale - getEyeWidth();
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
        float ey = getY() + EYE_HEIGHT_RELATIVE_POSITION * scale + (calls >= OPEN_BLINK ? getEyeHeight() : 0.0f);
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
        return (int) Math.round(DEFAULT_EYE_HEIGHT * scale * (calls >= OPEN_BLINK ? 0.5 : 1.0));
    }
    
    public Oval getFace() {
        return new Oval(x, y, getWidth(), getHeight(), null, Color.WHITE);
    }
    
    public Oval getLeftEye() {
        return new Oval(getLeftEyeX(), getEyeY(), getEyeWidth(), getEyeHeight(), null, Color.BLACK);
    }
    
    public Oval getRightEye() {
        return new Oval(getRightEyeX(), getEyeY(), getEyeWidth(), getEyeHeight(), null, Color.BLACK);
    }
    
    public Rectangle getEyeLine() {
        return new Rectangle(getLeftEyeX(), getEyeY() + getEyeHeight() / 2, getRightEyeX() - getLeftEyeX() + getEyeWidth(), 1, null, Color.BLACK);
    }
    
    public Polygon getHatBase() {
        try {
            if (originalHatBase == null)
                originalHatBase = Polygon.readFromFile("SantaHatBaseRaw.txt");
            Polygon p = new Polygon(originalHatBase);
            p.outline = Color.BLACK;
            p.fill = Color.WHITE;
            p.translate(getX(), getY());
            return p;
        } catch (IOException ex) {
            System.err.println("Error reading 'SantaHatBaseRaw.txt'. Make sure file is valid.");
        }
        return null;
    }
    
    public Polygon getHatBody() {
        try {
            if (originalHatBody == null)
                originalHatBody = Polygon.readFromFile("SantaHatBodyRaw.txt");
            Polygon p = new Polygon(originalHatBody);
            p.outline = Color.BLACK;
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
        return new Circle(mouseOffsetX() >= 0 ? center().x + getWidth() / 3 : center().x - getWidth() / 3 - getHatBallWidth(), getY() - getHeight() * 3 / 4, getHatBallWidth(), Color.BLACK, Color.WHITE);
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
        
        Oval face = getFace();
        Oval leftEye = getLeftEye();
        Oval rightEye = getRightEye();
        Rectangle eyeLine = getEyeLine();
        
        Polygon hatBody = getHatBody();
        Polygon hatBase = getHatBase();
        Circle hatBall = getHatBall();
        
        
        face.draw(g);
        leftEye.draw(g);
        rightEye.draw(g);
        eyeLine.draw(g);
        
        hatBody.draw(g);
        hatBase.draw(g);
        hatBall.draw(g);
    }
    
    int calls = 0;
    static final int OPEN_BLINK = 60;
    static final int CLOSE_BLINK = 5;
    
    @Override
    public void animate() {
    }
}
