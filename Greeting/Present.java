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

public class Present extends Square {
    
    Color ribbonfill = Color.RED;
    
    public Present(int x, int y, int sideLength) { super(x, y, sideLength); }
    public Present(int x, int y, int sideLength, Color outline, Color fill, Color ribbonfill) {
        super(x, y, sideLength, outline, fill);
        this.ribbonfill = ribbonfill;
    }
    
    public Rectangle getRibbonRectangle() {
        return new Rectangle(this.x + (int) Math.ceil(this.width / 3), this.y, this.width / 3, this.height, outline, ribbonfill);
    }
    
    @Override
    public void draw(Graphics g) {
        super.draw(g);
        getRibbonRectangle().draw(g);
    }
    
    public boolean isJumping = false;
    int flickers = 0, jx, jy, dx;
    double startTime, jumpTime = 0.3, gravity = 0.3;
    
    int jumpHeight() {
        return height / 3;
    }
    
    int jumpLength() {
        return width / 4;
    }
    
    @Override
    public void animate() {
        if (isJumping) {
            double deltaTime = System.currentTimeMillis() / 1000.0 - startTime;
            if (deltaTime >= jumpTime) {
                this.x = jx + jumpLength() * dx;
                this.y = jy;
                isJumping = false;
            }
            else {
                this.x = jx + (int) Math.round(jumpLength() * deltaTime / jumpTime) * dx;
                this.y = jy - (int) Math.round(gravity / 2 * (deltaTime - jumpTime / 2) * (deltaTime - jumpTime / 2) + jumpHeight());
            }
        }
        else {
            flickers++;
            if (flickers >= 1 && Math.random() < 0.4) {
                flickers = 0;
                isJumping = true;
                jx = x;
                jy = y;
                try{
                    dx = (x >= Greeting.Main.getMousePosition().x ? -1 : 1);
                }
                catch (Exception ex) {
                    dx = 0;
                }
                startTime = System.currentTimeMillis() / 1000.0;
            }
        }
    }
    
    
}
