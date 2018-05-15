public class BenchmarkTimer {

    private long start;
    private String name;

    public BenchmarkTimer(String name){
        start = System.currentTimeMillis();
        this.name = name;
    }

    public void stop(){
        System.out.println(name + ": " + (System.currentTimeMillis() - start) + " ms");
    }
}
