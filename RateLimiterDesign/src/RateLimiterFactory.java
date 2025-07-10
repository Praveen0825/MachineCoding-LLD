import java.util.Map;

public class RateLimiterFactory {
    public static RateLimiter getRateLimiter(RateLimiterType type, Map<String, Object> config) {
        switch (type) {
            case TOKEN_BUCKET:
                int maxTokens = (int) config.getOrDefault("maxTokens", 10);
                double refillRate = (double) config.getOrDefault("refillRatePerSecond", 5.0);
                return new TokenBucketRateLimiter(maxTokens, refillRate);

            // Future extensibility:
            // case LEAKY_BUCKET:
            //     return new LeakyBucketRateLimiter(...);

            default:
                throw new UnsupportedOperationException("Rate limiter type not supported.");
        }
    }
}
