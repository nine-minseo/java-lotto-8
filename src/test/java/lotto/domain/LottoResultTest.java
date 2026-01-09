package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LottoResultTest {

    @Test
    void 수익률을_정확하게_계산한다() {
        // given
        LottoResult lottoResult = new LottoResult();
        lottoResult.add(Rank.THREE_MATCH); // 5,000원 당첨
        int purchaseAmount = 5000;

        // when
        double profitRate = lottoResult.calculateProfitRate(purchaseAmount);

        // then
        assertThat(profitRate).isEqualTo(100.0);
    }
}