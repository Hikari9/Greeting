package greeting.assets;

import greeting.*;
import java.awt.Color;

public class Snowball extends Circle {
    
    int[] currentColor;
    int[] colorChanger;
    
    public Snowball (int x, Color outline, Color fill, int[] colorChanger) throws Exception {
        super(x, -5, 10, outline, fill);
        this.currentColor = new int[]{fill.getRed(), fill.getGreen(), fill.getBlue(), fill.getAlpha()};
        this.colorChanger = colorChanger;
        if (this.colorChanger.length != 3 && this.colorChanger.length != 4) {
            throw new Exception ("Error in snowball constructor. Variable colorChanger[] should have length 3 or 4.");
        }
    }
    
    @Override
    public void animate() {
        super.animate();
        changeGradient();
        fall();
    }
    
    protected void changeGradient() {
        for (int i = 0; i < colorChanger.length; ++i) {
            int newColor = currentColor[i] + colorChanger[i];
            if (newColor < (i == 3 ? 0 : 200) || newColor >= 0xFF) {
                colorChanger[i] *= -1;
                newColor = currentColor[i] + colorChanger[i];
            }
            currentColor[i] = newColor;
        }
        this.fill = new Color(currentColor[0], currentColor[1], currentColor[2], currentColor[3]);
    }
    
    protected static int FALL_PIXELS_PER_FRAME = 3;
    
    protected void fall() {
        if (this.y < Greeting.Main.getWidth()) {
            this.y += FALL_PIXELS_PER_FRAME;
        }
    }
    
}
