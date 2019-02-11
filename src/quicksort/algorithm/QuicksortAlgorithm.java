
package quicksort.algorithm;

/**
 *
 * @author Justas
 */
public abstract class QuicksortAlgorithm
{
    protected int[] array;
    
    public abstract int [] sort();
    
    public QuicksortAlgorithm(int[] array)
    {
        this.array = array;
    }
    
    protected int partition(int start, int end)
    {
        int pivot = array[end];
        int pointer = start - 1;
        
        for(int i = start; i < end; i++)
        {
            if(array[i] > pivot)
            {
                pointer++;
                int tempElem = array[pointer];
                array[pointer] = array[i];
                array[i] = tempElem;
            }
        }
        
        int tempElem = array[pointer+1];
        array[pointer+1] = array[end];
        array[end] = tempElem;
        
        return pointer +1;
    }
}
