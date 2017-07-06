/**
 * Interface used by FractalGenerator. It is the Subject that lets the Observer
 * (GUI) subscribe to the FractalGenrator 
 * @author Rohan Dhermy
 * @version (06/05/17)
 */

public interface Subject {
    public void registerObserver(Observer observer); 
    
    public void removeObserver(int index); 
    
    public void notifyObservers(); 
    
    public ArrayList<FractalPart> getData(); 
}
