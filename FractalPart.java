
/**
 * FractalPart object that is each circle. It is passed it's x and y values to be 
 * drawn later 
 * @author Rohan Dhermy
 * @version (06/05/17)
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.JPanel; 
import java.awt.Graphics2D;

public class FractalPart extends JPanel{
    private int x; 
    private int y; 
    private int size; 
    private Color color; 
    private Toolkit toolkit;
    
    /**
     * Constructor for FractalPart, circle with it's position, size, and color 
     * to be drawn by the display/GUI class 
     * @param x
     * @param y
     * @param size
     * @param color 
     */
    public FractalPart(int x, int y, int size, Color color){
        this.x = x-(size/2); 
        this.y = y-(size/2); 
        this.size = size; 
        this.color = color; 
    }
    
    /**
     * Method that draws the fractal at it's specified position
     * @param g 
     */
    public void draw(Graphics g){
        g.setColor(color);
        g.fillOval(x, y, size, size);
    }
   
}
