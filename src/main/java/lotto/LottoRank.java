package lotto;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;

public enum LottoRank {
    THREE_MATCH(3, 5_000, "3개 일치"),
    FOUR_MATCH(4, 50_000, "4개 일치"),
    FIVE_MATCH(5, 1_500_000, "5개 일치"),
    FIVE_BONUS_MATCH(5, 30_000_000, "5개 일치, 보너스 볼 일치"),
    SIX_MATCH(6, 2_000_000_000, "6개 일치");

    private final int matchCount;
    private final int prizeMoney;
    private final String description;

    LottoRank(int matchCount, int prizeMoney, String description) {
        this.matchCount = matchCount;
        this.prizeMoney = prizeMoney;
        this.description = description;
    }

    public String getFormattedPrize() {
        return NumberFormat.getInstance(Locale.KOREA).format(this.prizeMoney) + "원";
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public String getDescription() {
        return description;
    }

    public static Optional<LottoRank> match(int matchCount, boolean isMatchBonusNumber) {
        if (matchCount == 5) {
            if (isMatchBonusNumber) {
                return Optional.of(FIVE_BONUS_MATCH);
            }
            return Optional.of(FIVE_MATCH);
        }

        return Arrays.stream(LottoRank.values())
                .filter(rank -> rank != FIVE_BONUS_MATCH)
                .filter(rank -> rank.getMatchCount() == matchCount)
                .findFirst();
    }
}