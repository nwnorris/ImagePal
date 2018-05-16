/**
 * Makes it easy to benchmark code performance. Auto-starts on init, stopped at will.
 * Author: nwnorris
 */
public class BenchmarkTimer {

    //Instance variables
    private long start;
    private String name;

    /**
     * Constructor, starts the timer.
     * @param name The name of whatever function or code block is being timed.
     */
    public BenchmarkTimer(String name){
        start = System.currentTimeMillis();
        this.name = name;
    }

    /**
     * Stops the timer and calculates the total time.
     * @return The total time (in ms) the timer was running for.
     */
    public long stop(){
        return System.currentTimeMillis() - start;
    }

    /**
     * Stops the timer, but instead of returning a value, prints out the time spent in debug fashion.
     */
    public void stopLazy(){
        System.out.println(name + ": " + (System.currentTimeMillis() - start) + " ms");
    }
}
