
package quicksort;

import java.util.Random;

/**
 *
 * @author Justas
 */
public class ArrayFactory
{
    protected int arraySize;
    protected Random random;
    
    public ArrayFactory(int arraySize, long seed)
    {
        this.arraySize = arraySize;
        this.random = new Random(seed);
    }
    
    public ArrayFactory(int arraySize)
    {
        this.arraySize = arraySize;
        this.random = new Random();
    }
    
    public int[] getArray()
    {
       int[] array = new int[this.arraySize];
       for(int i = 0; i < array.length; i++)
        {
            array[i] = random.nextInt(Config.MAX_RANDOM_INTEGER_IN_FACTORY);
        }
       return array;
    }  
}
