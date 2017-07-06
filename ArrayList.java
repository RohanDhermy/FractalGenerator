import java.io.Serializable;
import java.util.Arrays;
import java.lang.IllegalArgumentException; 
import java.util.NoSuchElementException; 
import java.lang.Iterable; 
import java.util.Iterator; 

/**
 * ArrayList class that creates an generic-type ArrayList from scratch 
 * It also implements a iterator to iterate through objects in the Array
 * @author Rohan Dhermy
 * @version (04/21/2017)
 */
public class ArrayList<E> implements Iterable, Serializable{
    private E[] elementArray; 
    private int size; 
    private int capacity; 
    private static final int DEFAULT_CAPACITY = 50;
    
    /**
     * Constructor for generic-type ArrayList with parameter for initial capacity
     * @param capacity for array we will be creating 
     */
    @SuppressWarnings("unchecked")
    public ArrayList(int capacity){
        this.elementArray = (E[])new Object[capacity]; 
        this.size = 0; 
        this.capacity = capacity; 
    }
    /**
     * ArrayList constructor with no parameters and sets capacity to a default 50
     * Calls Constructor with parameter to set default capacity and set size too
     */
    public ArrayList(){
        this(DEFAULT_CAPACITY); 
    }
    
    /**
     * Method to add an element to the array. Calls ensure capacity to see if
     * we need to increase the capacity of the array
     * @param element generic-type element to be added to array
     * @return true after element has been successfully added 
     */
    public boolean add(E element){
        ensureCapacity(size + 1); 
        elementArray[size] = element; 
        size++; 
        return true; 
    }
    
    /**
     * Method to add generic-type element to array a a specific index. Index has
     * to be more than or equal to 0 and less than the size of the array
     * @param element generic type array 
     * @param index where you would like to add the array 
     */
    public void add(E element, int index){
        if(index < 0 || index > size){
            throw new IllegalArgumentException("Index must be more than or equal to "
                    + "0 and less than the Array size: " + this.size); 
        }
        //Shift array to the right
        ensureCapacity(size + 1); 
        for(int idx = size; idx > index; idx--){
            elementArray[idx] = elementArray[idx - 1]; 
        }
        //insert element at index 
        elementArray[index] = element; 
        size++;
    }
    
    public E remove(int index){
        checkIndex(index); 
        E returnElement = elementArray[index]; 
        elementArray[index] = null; 
        for(int idx = index; idx < size - 1; idx++){
            elementArray[idx] = elementArray[idx + 1]; 
        }
        size--; 
        return returnElement; 
    }
    
    public void clear(){
        for(int idx = 0; idx < size; idx++){
            elementArray[idx] = null; 
        }
        this.size = 0; 
    }
    
    /**
     * Method that returns the size of the array (number of elements in array)
     * @return 
     */
    public int size(){
        return this.size; 
    }
    
    /**
     * Method that returns the capacity of the array 
     * @return 
     */
    public int capacity(){
        return this.capacity; 
    }
    
    /**
     * Returns the index of the element passed in 
     * @param element index we are looking for 
     * @return the index of element 
     */
    public int indexOf(E element) {
        for (int idx = 0; idx < size; idx++) {
            if (elementArray[idx].equals(element)) {
                return idx;
            }
        }
        return -1;
    }
    
    /**
     * Returns if an element is in the ArrayList or not 
     * @param element we are looking for 
     * @return true or false 
     */
    public boolean contains(E element) {
        return ((indexOf(element) >= 0) ? true : false);
    }
    
    /**
     * Returns true if ArrayList is empty 
     * @return 
     */
    public boolean isEmpty() {
        return ((size == 0) ? true : false);
    }
    
    /**
     * Returns the element in ArrayList by index 
     * @param index 
     * @return the element by index 
     */
    public E get(int index) {
        checkIndex(index);
        return elementArray[index];
    }
    
    /**
     * Checks if index is not out of bounds and safe to call 
     * @param index 
     */
    private void checkIndex(int index){
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("Index must be more than or equal to "
                    + "0 and less than the Array size: " + this.size); 
        }
    }
    
    /**
     * Returns String representation of the ArrayList 
     * @return ArrayList 
     */
    public String toString() {
        if (size == 0) {
            return "[]";
        } else {
            String result = "[" + elementArray[0];
            for (int idx = 1; idx < size; idx++) {
                result += ", \n" + elementArray[idx];
            }
            result += "]";
            return result;
        }
    }
    
    /**
     * Makes sure ArrayList capacity is enough to add another element. If there 
     * is not enough space, it will increase the size by 50%. 
     * @param desiredCapacity 
     */
    private void ensureCapacity(int desiredCapacity){
        if(desiredCapacity > capacity){
            int tempCapacity = (int)Math.max((capacity * 1.5), desiredCapacity);
            elementArray = Arrays.copyOf(elementArray, tempCapacity); 
            capacity = tempCapacity; 
        }
    }
    
    /**
     * Method to create ArrayListIterator 
     * @return iterator 
     */
    public Iterator<E> iterator(){
        return new ArrayListIterator(); 
    }
    
    /**
     * Class that creates a ArrayListIterator 
     */
    private class ArrayListIterator implements Iterator<E>{
        private int position; 
        //removeOk because first we have to call next and then we are able to remove
        private boolean removeOK; 
        
        /**
         * Constructor for ArrayListIterator 
         */
        public ArrayListIterator(){
            position = 0; 
            removeOK = false; 
        }
        
        /**
         * Return true if ArrayList has another element 
         * @return 
         */
        public boolean hasNext(){
            return position < size(); 
        }
        
        /**
         * Method that consumes the element the iterator is positioned at and 
         * returns it 
         * @return the element consumed 
         */
        public E next() { 
            if(!hasNext()){
                throw new NoSuchElementException("No more elements"); 
            }
            E result = elementArray[position]; 
            position++; 
            //next called so Ok to remove element 
            removeOK = true; 
            return result; 
        }
        
        /**
         * Method to remove element iterator is positioned at 
         * Checks if next has been called previously and is ok to remove 
         */
        public void remove(){
            if(!removeOK){
                throw new IllegalStateException("Attempted remove before next!"); 
            }
            ArrayList.this.remove(position - 1); 
            position--; 
            removeOK = false; 
        }
    }
}

