import java.util.Map;

public class RateLimiterFactory {
    public static RateLimiter getRateLimiter(RateLimiterType type, Map<String, Object> config) {
        switch (type) {
            case TOKEN_BUCKET:
                int maxTokens = (int) config.getOrDefault("maxTokens", 10);
                double refillRate = (double) config.getOrDefault("refillRatePerSecond", 5.0);
                return new TokenBucketRateLimiter(maxTokens, refillRate);

            case FIXED_WINDOW:
                int limit = (int) config.getOrDefault("limit", 10);
                long windowSizeMs = (long) config.getOrDefault("windowSizeMs", 1000L);
                return new FixedWindowRateLimiter(limit, windowSizeMs);

            case SLIDING_WINDOW:
                int slidingLimit = (int) config.getOrDefault("limit", 10);
                long slidingWindowSizeMs = (long) config.getOrDefault("windowSizeMs", 1000L);
                return new SlidingWindowRateLimiter(slidingLimit, slidingWindowSizeMs);

            case LEAKY_BUCKET:
                int capacity = (int) config.getOrDefault("capacity", 10);
                double leakRatePerSec = (double) config.getOrDefault("leakRatePerSecond", 1.0);
                return new LeakyBucketRateLimiter(capacity, leakRatePerSec);
            default:
                throw new UnsupportedOperationException("Rate limiter type not supported.");
        }
    }
}
