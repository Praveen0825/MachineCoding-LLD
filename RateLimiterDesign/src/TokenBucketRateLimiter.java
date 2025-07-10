import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class TokenBucketRateLimiter implements RateLimiter{
    private final int maxTokens;
    private final double refillRatePerSecond;
    private final ConcurrentHashMap<String, Bucket> buckets = new ConcurrentHashMap<>();

    private static class Bucket {
        double tokens;
        long lastRefillTimestamp;
        final ReentrantLock lock = new ReentrantLock();

        Bucket(int capacity) {
            this.tokens = capacity;
            this.lastRefillTimestamp = System.nanoTime();
        }
    }

    public TokenBucketRateLimiter(int maxTokens, double refillRatePerSecond) {
        this.maxTokens = maxTokens;
        this.refillRatePerSecond = refillRatePerSecond;
    }

    @Override
    public boolean allowRequest(String key) {
        Bucket bucket = buckets.computeIfAbsent(key, k -> new Bucket(maxTokens));

        bucket.lock.lock();
        try {
            refill(bucket);
            if (bucket.tokens >= 1) {
                bucket.tokens -= 1;
                return true;
            }
            return false;
        } finally {
            bucket.lock.unlock();
        }
    }

    private void refill(Bucket bucket) {
        long now = System.nanoTime();
        double secondsPassed = (now - bucket.lastRefillTimestamp) / 1_000_000_000.0;
        double tokensToAdd = secondsPassed * refillRatePerSecond;
        if (tokensToAdd > 0) {
            bucket.tokens = Math.min(maxTokens, bucket.tokens + tokensToAdd);
            bucket.lastRefillTimestamp = now;
        }
    }
}
