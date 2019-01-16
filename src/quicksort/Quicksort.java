
package quicksort;

import java.io.IOException;
import quicksort.algorithm.QuicksortAlgorithm;
import java.util.Arrays;

/**
 *
 * @author Justas
 */
public class Quicksort
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
      try{
          UserPrompt.programRunningTypePrompt();
      }catch(IOException ex){
          errorHandler(ex); 
      }
    }
    
    public static void runProgram()
    {
        QuicksortAlgorithm sortAlgorythm = UserPrompt.sortTypePrompt();
        
        System.out.print("Sorting execution time: ");
        long t0 = System.currentTimeMillis();
        int[] sortedArray = sortAlgorythm.sort();
        System.out.print(System.currentTimeMillis()-t0 +"\n");
        
        if(sortedArray.length <= Config.MAX_PRINTABLE_ARRAY_SIZE)
        {
            System.out.println("Sorted array:");
            System.out.println(Arrays.toString(sortedArray));
        }
    }
    
    public static void runTest() throws IOException
    {
        Test test = new Test();
        try{
            test.runTest();
        } catch (IOException ex) {
            test.closeWriter();
        }
    }

    public static void errorHandler(Throwable ex)
    {
        System.err.println(ex.getMessage());
        
        if(Config.DEBUG)
        {
            System.err.println("--- Stack trace ---");
            ex.printStackTrace();
        } 
    }
    
    
    
}
