package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {

    @Test
    void 번호_5개와_보너스_번호가_일치하면_2등이다() {
        // given
        int matchCount = 5;
        boolean matchBonus = true;

        // when
        Rank rank = Rank.valueOf(matchCount, matchBonus);

        // then
        assertThat(rank).isEqualTo(Rank.FIVE_BONUS_MATCH);
    }

    @Test
    void 번호_5개가_일치하고_보너스_번호가_다르면_3등이다() {
        // given
        int matchCount = 5;
        boolean matchBonus = false;

        // when
        Rank rank = Rank.valueOf(matchCount, matchBonus);

        // then
        assertThat(rank).isEqualTo(Rank.FIVE_MATCH);
    }

    @Test
    void 번호_3개_미만으로_일치하면_당첨_없음이다() {
        // given
        int matchCount = 2;
        boolean matchBonus = false;

        // when
        Rank rank = Rank.valueOf(matchCount, matchBonus);

        // then
        assertThat(rank).isEqualTo(Rank.MISS);
    }
}