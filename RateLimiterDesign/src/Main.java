import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, Object> config = new HashMap<>();
        config.put("maxTokens", 10);
        config.put("refillRatePerSecond", 5.0);

        RateLimiter rateLimiter = RateLimiterFactory.getRateLimiter(RateLimiterType.TOKEN_BUCKET, config);

        String user = "user_1";
        for (int i = 0; i < 15; i++) {
            if (rateLimiter.allowRequest(user)) {
                System.out.println("Request " + i + " allowed.");
            } else {
                System.out.println("Request " + i + " denied.");
            }

            try {
                Thread.sleep(100); // simulate time delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
