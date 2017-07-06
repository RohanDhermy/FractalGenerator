/**
 * Program that allows the user to select the depth and ratio for fractals to be
 * drawn as a Prickly Pear Cactus plant. The user selects the depth, the ratio 
 * from parent to child, the color of the cactus, and the color of the pear using 
 * the widgets on the GUI and the program will paint the fractal for them. 
 * @author Rohan Dhermy
 * @version (06/05/17)
 */
public class Proj06_Rohan_Dhermy {

    /**
     * Main method creates the GUI object and FractalGenerator to start the 
     * program
     */
    public static void main(String[] args) {
        // TODO code application logic here
        FractalGenerator fracGen = new FractalGenerator(); 
        GUI myGUI = new GUI(fracGen); 
        myGUI.setVisible(true);
    }
    
}
