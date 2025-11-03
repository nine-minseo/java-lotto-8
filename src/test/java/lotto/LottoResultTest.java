package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.EnumMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class LottoResultTest {

    @DisplayName("총 당첨금을 계산한다.")
    @Test
    void 총_당첨금을_계산한다() {
        Map<LottoRank, Integer> rankCounts = new EnumMap<>(LottoRank.class);
        rankCounts.put(LottoRank.THREE_MATCH, 2);
        rankCounts.put(LottoRank.FOUR_MATCH, 1);
        LottoResult result = new LottoResult(rankCounts);

        long totalPrize = result.calculateTotalPrize();

        assertThat(totalPrize).isEqualTo(60_000L);
    }

    @DisplayName("당첨금이 없을 때 총 당첨금은 0을 반환한다.")
    @Test
    void 당첨금이_없을_때_총_당첨금은_0을_반환한다() {
        Map<LottoRank, Integer> rankCounts = Map.of();
        LottoResult result = new LottoResult(rankCounts);

        long totalPrize = result.calculateTotalPrize();

        assertThat(totalPrize).isEqualTo(0L);
    }

    @DisplayName("당첨금이 0일 때 수익률은 0.0을 반환한다.")
    @Test
    void calculateProfitRateWithZeroPrize() {
        Map<LottoRank, Integer> rankCounts = Map.of();
        LottoResult result = new LottoResult(rankCounts);
        int purchaseAmount = 1000;

        double profitRate = result.calculateProfitRate(purchaseAmount);

        assertThat(profitRate).isEqualTo(0.0);
    }

    @DisplayName("총 수익률을 소수점 둘째 자리에서 반올림한다.")
    @Test
    void 총_수익률을_소수점_둘째_자리에서_반올림한다() {
        Map<LottoRank, Integer> rankCounts = Map.of(LottoRank.THREE_MATCH, 1);
        LottoResult result = new LottoResult(rankCounts);
        int purchaseAmount = 8000;

        double profitRate = result.calculateProfitRate(purchaseAmount);

        double expectedRate = (5000.0 / 8000.0) * 100.0;
        assertThat(profitRate).isEqualTo(expectedRate);
    }
}