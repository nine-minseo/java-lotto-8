package lotto.domain;

import java.util.Arrays;

public enum Rank {
    SIX_MATCH(6, 2_000_000_000),
    FIVE_BONUS_MATCH(5, 30_000_000),
    FIVE_MATCH(5, 1_500_000),
    FOUR_MATCH(4, 50_000),
    THREE_MATCH(3, 5_000),
    MISS(0, 0);

    private final int countOfMatch;
    private final int winningMoney;

    Rank(int countOfMatch, int winningMoney) {
        this.countOfMatch = countOfMatch;
        this.winningMoney = winningMoney;
    }

    public int getWinningMoney() {
        return winningMoney;
    }

    public static Rank valueOf(int countOfMatch, boolean matchBonus) {
        if (countOfMatch < 3) {
            return MISS;
        }

        if (FIVE_BONUS_MATCH.matchCount(countOfMatch) && matchBonus) {
            return FIVE_BONUS_MATCH;
        }

        return Arrays.stream(values())
                .filter(rank -> rank.matchCount(countOfMatch) && rank != FIVE_BONUS_MATCH)
                .findFirst()
                .orElse(MISS);
    }

    private boolean matchCount(int countOfMatch) {
        return this.countOfMatch == countOfMatch;
    }
}
