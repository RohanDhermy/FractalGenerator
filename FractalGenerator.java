/**
 * Class creates a FractalGenerator object. This takes the data from the GUI
 * class and uses that data to generate the fractal pieces. 
 * @author Rohan Dhermy
 * @version (06/05/17)
 */
import java.awt.Color;
import javax.swing.JPanel; 

public class FractalGenerator implements Subject {
    private ArrayList<FractalPart> fractalParts; 
    private ArrayList<Observer>observers; 
    private int depth; 
    private int ratio; 
    private Color cactusColor; 
    private Color pearColor;
    
    /**
     * Constructor that initializes the fields 
     */
    public FractalGenerator(){
        depth = 2; 
        ratio = 50; 
        cactusColor = Color.GREEN; 
        pearColor = Color.PINK; 
        fractalParts = new ArrayList<>(); 
        observers = new ArrayList<>(); 
    }
    
    /**
     * Method that sets the data field we will need to create the fractal(s)
     * Also calls notifyObservers() to let display/GUI class know about the change
     * in data 
     * @param depth
     * @param ratio
     * @param cactusColor
     * @param pearColor 
     */
    public void setData(int depth, int ratio, Color cactusColor, Color pearColor){
        this.depth = depth; 
        this.ratio = ratio; 
        this.cactusColor = cactusColor; 
        this.pearColor = pearColor; 
        notifyObservers();
    }
    
    /**
     * Method that calls private method that does the math to create each fractal 
     * piece and store it is a ArrayList of FractalPart objects. 
     */
    public void generateFractal(){
        generateFractal(90, depth, 350, 400, 150, cactusColor); 
    }
    
    /**
     * Math to generate each FractalPart and store in an ArrayList. Uses Trig to 
     * solve where to draw parent and its children FractalParts 
     * @param rotation
     * @param depth
     * @param x
     * @param y
     * @param size
     * @param color 
     */
    private void generateFractal(int rotation, int depth, int x, int y, int size, Color color){   
        double sizeRatio = ratio; 
        if(depth == 0){
                
        }
        else{
            if(depth == 1){
                FractalPart fractalPart = new FractalPart(x, y, size, pearColor);
                this.fractalParts.add(fractalPart);
            }
            else{
                FractalPart fractalPart = new FractalPart(x, y, size, color);
                this.fractalParts.add(fractalPart);
            }
             int earSize = (int)(size*((ratio/100.00))); 
             int hypotenuse = (int)((size/2.0) + (earSize/2.0)); 
             int yShift = (int)(Math.sin(Math.toRadians(rotation+45))*(hypotenuse));
             int xShift = (int)(Math.cos(Math.toRadians(rotation+45))*(hypotenuse));
             generateFractal(rotation+45, depth-1, x-xShift, y-yShift, earSize, color);
             yShift = (int)(Math.sin(Math.toRadians(rotation-45))*(hypotenuse));
             xShift = (int)(Math.cos(Math.toRadians(rotation-45))*(hypotenuse));
             generateFractal(rotation-45, depth-1, x-xShift, y-yShift, earSize, color); 
        }
    }
    
    /**
     * Method called by update() in display to get the updated fractal data 
     * to be able to draw it 
     * @return 
     */
    public ArrayList<FractalPart> getData(){
        fractalParts.clear();
        generateFractal();
        return this.fractalParts; 
    }
    
    /**
     * Registers/Subscribes the observers (display) 
     * @param observer 
     */
    @Override
    public void registerObserver(Observer observer) {
        this.observers.add(observer);
    }
    
    /**
     * Can un-subscribe the observer 
     * @param index 
     */
    @Override
    public void removeObserver(int index) {
        this.observers.remove(index); 
    }
    
    /**
     * Update the observers 
     */
    @Override
    public void notifyObservers() {
        for(int idx = 0; idx < observers.size(); idx++){
            observers.get(idx).update();
        }
    }
}
