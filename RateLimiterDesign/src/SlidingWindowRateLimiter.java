import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

public class SlidingWindowRateLimiter implements RateLimiter{
    private final int limit;
    private final long windowSizeMs;
    private final ConcurrentHashMap<String, Deque<Long>> requestTimestamps = new ConcurrentHashMap<>();

    public SlidingWindowRateLimiter(int limit, long windowSizeMs) {
        this.limit = limit;
        this.windowSizeMs = windowSizeMs;
    }

    @Override
    public synchronized boolean allowRequest(String key) {
        long now = System.currentTimeMillis();
        Deque<Long> timestamps = requestTimestamps.computeIfAbsent(key, k -> new LinkedList<>());

        while (!timestamps.isEmpty() && now - timestamps.peekFirst() >= windowSizeMs) {
            timestamps.pollFirst();
        }

        if (timestamps.size() < limit) {
            timestamps.addLast(now);
            return true;
        }
        return false;
    }
}
