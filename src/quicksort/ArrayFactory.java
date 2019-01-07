
package quicksort;

import java.util.Random;

/**
 *
 * @author Justas
 */
public class ArrayFactory
{
    protected int[] array;
    protected Random random;
    
    public ArrayFactory(int arraySize)
    {
        this.array = new int[arraySize];
        this.random = new Random();
        fillArray();     
        //fillArrayWithAscending();
    }
    
    private void fillArray()
    {
        for(int i = 0; i < array.length; i++)
        {
            this.array[i] = random.nextInt(Config.MAX_RANDOM_INTEGER_IN_FACTORY);
        }
    }
    
    private void fillArrayWithAscending()
    {
        for(int i = 0; i < array.length; i++)
        {
            this.array[i] = i;
        }
    }
    
    private void fillArrayWithDescending()
    {
        for (int i = 0; i < array.length; i++) {
            array[i] = array.length - i;   
        }
    }
    
    public int[] getArray()
    {
        return array.clone();
    }  
}
