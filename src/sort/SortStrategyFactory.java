package sort;

public final class SortStrategyFactory {
    /**
     * identify strategy
     */
    public static SortStrategy createStrategy(String strategyType) {
        if (strategyType.equals("GREEN")) {
            return new SortGreenStrategy();
        }
        if (strategyType.equals("PRICE")) {
            return new SortPriceStrategy();
        }
        if (strategyType.equals("QUANTITY")) {
            return new SortQuantityStrategy();
        } else {
            return null;
        }
    }
}
