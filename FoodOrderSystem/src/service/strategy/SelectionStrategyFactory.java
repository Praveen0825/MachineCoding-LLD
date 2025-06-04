package service.strategy;

import enums.SelectionStrategyType;

public class SelectionStrategyFactory {
    public static SelectionStrategy getStrategy(SelectionStrategyType type) {
        return switch (type) {
            case LOWEST_COST -> new LowestCostStrategy();
            case HIGHEST_RATING -> new HighestRatingStrategy();
            case MAX_REMAINING_CAPACITY -> new MaxRemainingCapacityStrategy();
        };
    }
}
