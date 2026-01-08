package lotto.domain;

import java.util.EnumMap;
import java.util.Map;

public class LottoResult {
    private final Map<Rank, Integer> results;

    public LottoResult() {
        results = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            results.put(rank, 0);
        }
    }

    public void add(Rank rank) {
        results.put(rank, results.get(rank) + 1);
    }

    public int getCount(Rank rank) {
        return results.get(rank);
    }

    public double calculateProfitRate(int purchaseAmount) {
        double totalPrize = 0;
        for (Rank rank : results.keySet()) {
            totalPrize += (double) rank.getWinningMoney() * results.get(rank);
        }

        return (totalPrize / purchaseAmount) * 100;
    }
}
