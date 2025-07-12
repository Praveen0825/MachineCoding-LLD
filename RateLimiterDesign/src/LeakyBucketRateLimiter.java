import java.util.concurrent.ConcurrentHashMap;

public class LeakyBucketRateLimiter implements RateLimiter{
    private final int capacity;
    private final double leakRatePerSecond;
    private final ConcurrentHashMap<String,Bucket> buckets;

    private static class Bucket{
        double amount;
        long lastUpdated;

        public Bucket(double amount, long lastUpdated) {
            this.amount = amount;
            this.lastUpdated = lastUpdated;
        }
    }

    public LeakyBucketRateLimiter(int capacity, double leakRatePerSecond) {
        this.capacity = capacity;
        this.leakRatePerSecond = leakRatePerSecond;
        this.buckets=new ConcurrentHashMap<>();
    }

    @Override
    public boolean allowRequest(String key) {
        long now=System.nanoTime();
        Bucket bucket=buckets.computeIfAbsent(key,k-> new Bucket(0,now));

        synchronized (bucket){
            double secondPassed= (now -bucket.lastUpdated)/1000000000.0;
            double leaked =secondPassed*leakRatePerSecond;
            bucket.amount=Math.max(0,bucket.amount-leaked);
            bucket.lastUpdated=now;
            if(bucket.amount <capacity){
                bucket.amount+=1;
                return true;
            }
            return false;
        }
    }
}
