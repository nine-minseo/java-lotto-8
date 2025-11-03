package lotto;

import java.util.Map;

public class LottoResult {

    private final Map<LottoRank, Integer> rankCounts;

    public LottoResult(Map<LottoRank, Integer> rankCounts) {
        this.rankCounts = rankCounts;
    }

    public Map<LottoRank, Integer> getRankCounts() {
        return rankCounts;
    }

    public long calculateTotalPrize() {
        return rankCounts.entrySet().stream()
                .mapToLong(entry -> (long) entry.getKey().getPrizeMoney() * entry.getValue())
                .sum();
    }

    public double calculateProfitRate(int purchaseAmount) {
        long totalPrize = calculateTotalPrize();

        double profitRate = 0.0;
        if (totalPrize > 0) {
            profitRate = ((double) totalPrize / purchaseAmount) * 100.0;
        }

        return profitRate;
    }
}