package lotto.domain;

import java.util.Arrays;

public enum Rank {
    MISS(0, 0, ""),
    THREE_MATCH(3, 5_000, "3개 일치 (5,000원)"),
    FOUR_MATCH(4, 50_000, "4개 일치 (50,000원)"),
    FIVE_MATCH(5, 1_500_000, "5개 일치 (1,500,000원)"),
    FIVE_BONUS_MATCH(5, 30_000_000, "5개 일치, 보너스 볼 일치 (30,000,000원)"),
    SIX_MATCH(6, 2_000_000_000, "6개 일치 (2,000,000,000원)");

    private final int countOfMatch;
    private final int winningMoney;
    private final String message;

    Rank(int countOfMatch, int winningMoney, String message) {
        this.countOfMatch = countOfMatch;
        this.winningMoney = winningMoney;
        this.message = message;
    }

    public int getWinningMoney() {
        return winningMoney;
    }

    public String getMessage() {
        return message;
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
