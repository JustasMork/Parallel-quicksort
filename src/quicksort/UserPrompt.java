
package quicksort;

import java.io.IOException;
import java.util.Arrays;
import quicksort.algorithm.QuicksortAlgorithm;
import java.util.Scanner;
import quicksort.algorithm.QuicksortParallel;
import quicksort.algorithm.QuicksortSequential;

/**
 *
 * @author Justas
 */
public class UserPrompt
{
    private static final Scanner sc = new Scanner(System.in);
    
    public static void programRunningTypePrompt() throws IOException
    {
        System.out.println("Select program running type ( 1-User settings, 2-Run test )");
        String input = sc.nextLine();
        
        switch(input.trim())
        {
            case "1":
                Quicksort.runProgram();
                break;
            case "2":
                Quicksort.runTest();
                break;
            default:
                throw new UnsupportedOperationException("Operation doesn't exists");
                   
        }
    }
    
    public static QuicksortAlgorithm sortTypePrompt()
    {
        System.out.println("Select sorting type ( 1-Sequential, 2-Parallel ):");
        String input = sc.nextLine();
        QuicksortAlgorithm quicksort = null;
        
        int arraySize = UserPrompt.arraySizePrompt(); 
        ArrayFactory arrayFactory = new ArrayFactory(arraySize);
       
        switch(input.trim())
        {
            case "1":
                 
                 if(arraySize <= Config.MAX_PRINTABLE_ARRAY_SIZE)
                 {
                    System.out.println("Unsorted array: ");
                    System.out.println(Arrays.toString(arrayFactory.getArray()));
                 }
                 quicksort = new QuicksortSequential(arrayFactory.getArray());
                break;
            case "2":
                 if(arraySize <= Config.MAX_PRINTABLE_ARRAY_SIZE)
                 {
                    System.out.println("Unsorted array: ");
                    System.out.println(Arrays.toString(arrayFactory.getArray()));
                 }
                 quicksort = new QuicksortParallel(arrayFactory.getArray());
                break;
            default:
                throw new UnsupportedOperationException("Operation doesn't exists");
        }
        
        return quicksort;
    }
    
    public static int arraySizePrompt()
    {
        System.out.println("Enter number of elements in array:");
        String input = sc.nextLine();
        try{
            return Integer.parseInt(input.trim());
        } catch (Throwable ex) {
            throw new NumberFormatException("Unable to convert \""+input+"\" to integer");
        }
    }
}
