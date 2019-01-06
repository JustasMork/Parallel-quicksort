/*
 */
package quicksort.algorithm;

/**
 *
 * @author Justas
 */
public class QuicksortSequential extends QuicksortAlgorithm
{

    public QuicksortSequential(int[] array)
    {
        super(array);
    }

    @Override
    public int[] sort()
    {
        runSortAlgorithm(0, array.length-1);
        return array;
    }
    
    protected void runSortAlgorithm(int start, int end)
    {
        if(start < end)
        {
            int mid = partition(start, end);
            
            runSortAlgorithm(start, mid-1);
            runSortAlgorithm(mid+1, end);
        }
    }  

}
