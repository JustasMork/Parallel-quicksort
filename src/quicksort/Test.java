/*
 */
package quicksort;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import quicksort.algorithm.QuicksortAlgorithm;
import quicksort.algorithm.QuicksortParallel;
import quicksort.algorithm.QuicksortSequential;

/**
 *
 * @author Justas
 */
public class Test
{

    protected PrintWriter writer;
    protected long sequentialExecutionTime;
    protected int currentArraySize;
    protected ArrayFactory arrayFactory;

    public Test() throws IOException
    {
        sequentialExecutionTime = 0;
        currentArraySize = Config.INITIAL_ARRAY_SIZE;
        File file = new File(Config.RESULTS_FILE_NAME);
        file.setWritable(true);
        file.setReadable(true);
        file.setExecutable(true);
        writer = new PrintWriter(file);
    }

    public void runTest() throws IOException
    {
        try{
            while (sequentialExecutionTime < Config.MAX_EXECUTION_TIME) {
                arrayFactory = new ArrayFactory(currentArraySize, Config.RANDOM_SEED);

                //Sequential test
                long t0 = System.currentTimeMillis();
                runSequentialTest(arrayFactory);
                sequentialExecutionTime = System.currentTimeMillis() - t0;

                //Parallel test
                t0 = System.currentTimeMillis();
                runParallelTest(arrayFactory);
                long parallelExecutionTime = System.currentTimeMillis() - t0;

                writeTestResultsToConsole(sequentialExecutionTime, parallelExecutionTime);
                writeTestResultsToFile(sequentialExecutionTime, parallelExecutionTime);
                currentArraySize += Config.ARRAY_INTERVAL;
            }
            writer.close();
        
        }catch(OutOfMemoryError | StackOverflowError ex){
            this.handleTestExceptions(ex); 
        }
    }
    
    protected void handleTestExceptions(Throwable ex)
    {
        writer.write(ex.getMessage());   
        writer.close();
        
        if(Config.WRITE_RESULTS_TO_CONSOLE)
            System.out.println(ex.getMessage());
    }

    protected void runSequentialTest(ArrayFactory arrayFactory)
    {
        QuicksortAlgorithm quicksortAlgorithm = new QuicksortSequential(arrayFactory.getArray());
        quicksortAlgorithm.sort();
    }

    protected void runParallelTest(ArrayFactory arrayFactory)
    {
        QuicksortAlgorithm quicksortAlgorithm = new QuicksortParallel(arrayFactory.getArray());
        quicksortAlgorithm.sort();
    }

    protected void writeTestResultsToConsole(long timeSequential, long timeParallel)
    {
        if (Config.WRITE_RESULTS_TO_CONSOLE) {
            System.out.println(String.format("Array size: %10d, Time sequential: %10d, Time parallel: %10d", currentArraySize, timeSequential, timeParallel));
        }
    }

    protected void writeTestResultsToFile(long timeSequential, long timeParallel) throws IOException
    {
        writer.write(currentArraySize + "," + timeSequential + "," + timeParallel + "\n");

    }

}
