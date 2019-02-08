/*
 */
package quicksort.algorithm;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Justas
 */
public class QuicksortParallel extends QuicksortAlgorithm
{

    protected int numberOfCores;
    protected Thread[] threads;
    protected int threadCounter;
    protected Semaphore sem;

    public QuicksortParallel(int[] array)
    {
        super(array);
        numberOfCores = Runtime.getRuntime().availableProcessors();
        threads = new Thread[numberOfCores];
        threadCounter = 0;
        sem = new Semaphore(0);
    }

    @Override
    public int[] sort()
    {
        
        (new runSortAlgorythmClass(0, array.length-1, 0)).run();
        try {           
            sem.acquire();
            for(int i = 0; i < threads.length; i++)
            {
                try {
                    threads[i].join();
                } catch (InterruptedException ex) {
                   ex.printStackTrace();
                }
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(QuicksortParallel.class.getName()).log(Level.SEVERE, null, ex);
        }
       
          
        return array;
    }

    class runSortAlgorythmClass implements Runnable
    {

        protected int start;
        protected int end;
        protected int recursionCounter;

        public runSortAlgorythmClass(int start, int end, int recursionCounter)
        {
            this.start = start;
            this.end = end;
            this.recursionCounter = recursionCounter;
            
        }

        @Override
        public void run()
        {
            runSortAlgorithm(start, end, recursionCounter);
        }

        protected void runSortAlgorithm(int start, int end, int recCounter)
        {
            if (start < end) {
                int mid = partition(start, end);
                recCounter += 2;
                if (recCounter != numberOfCores) {
                    runSortAlgorithm(start, mid - 1, recCounter);
                    runSortAlgorithm(mid + 1, end, recCounter);
                } else if(threadCounter <= numberOfCores-2) {
                    threads[threadCounter] = new Thread((new runSortAlgorythmClass(start, mid - 1, recCounter)));
                    threads[threadCounter++].start();

                    threads[threadCounter] = new Thread((new runSortAlgorythmClass(mid+1, end, recCounter)));
                    threads[threadCounter++].start();  
                    if(threadCounter >= numberOfCores)
                        sem.release();
                }
            }
        }

    }

}
