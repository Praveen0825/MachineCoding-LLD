import java.util.concurrent.ConcurrentHashMap;

public class FixedWindowRateLimiter implements RateLimiter{
    private final int limit;
    private final long windowSize;
    private final ConcurrentHashMap<String, Window> windows;

    private static class Window{
        long windowStart;
        int count;

        public Window(long windowStart) {
            this.windowStart = windowStart;
            this.count = 0;
        }
    }

    public FixedWindowRateLimiter(int limit, long windowSize) {
        this.limit = limit;
        this.windowSize = windowSize;
        this.windows =new ConcurrentHashMap<>();
    }

    @Override
    public synchronized boolean allowRequest(String key) {
        long now=System.nanoTime();
        Window window=windows.computeIfAbsent(key, k-> new Window(now));

        if(now - window.windowStart >= windowSize){
            window.windowStart=now;
            window.count=1;
            return true;
        } else if (window.count < limit) {
            window.count++;
            return true;
        }
        return false;
    }
}
