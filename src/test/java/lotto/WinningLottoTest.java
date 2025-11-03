package lotto;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class WinningLottoTest {
    @DisplayName("보너스 번호가 당첨 번호와 중복되면 예외가 발생한다.")
    @Test
    void 보너스_번호가_당첨_번호와_중복되면_예외가_발생한다() {
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 6;

        assertThatThrownBy(() -> new WinningLotto(winningNumbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("당첨 번호와 보너스 번호가 정상적으로 생성된다.")
    @Test
    void 당첨_번호와_보너스_번호가_정상적으로_생성된다() {
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 7;

        org.junit.jupiter.api.Assertions.assertDoesNotThrow(
                () -> new WinningLotto(winningNumbers, bonusNumber)
        );
    }

    private WinningLotto winningLotto;

    @BeforeEach
    void setUp() {
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 7;
        winningLotto = new WinningLotto(winningNumbers, bonusNumber);
    }

    @DisplayName("6개가 일치하는지 판별한다.")
    @Test
    void matchRank1() {
        Lotto userLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Optional<LottoRank> result = winningLotto.match(userLotto);
        assertThat(result).isEqualTo(Optional.of(LottoRank.SIX_MATCH));
    }

    @DisplayName("5개와 보너스 번호가 일치하는지 판별한다.")
    @Test
    void matchRank2() {
        Lotto userLotto = new Lotto(List.of(1, 2, 3, 4, 5, 7));
        Optional<LottoRank> result = winningLotto.match(userLotto);
        assertThat(result).isEqualTo(Optional.of(LottoRank.FIVE_BONUS_MATCH));
    }

    @DisplayName("5개가 일치하는지 판별한다.")
    @Test
    void matchRank3() {
        Lotto userLotto = new Lotto(List.of(1, 2, 3, 4, 5, 8));
        Optional<LottoRank> result = winningLotto.match(userLotto);
        assertThat(result).isEqualTo(Optional.of(LottoRank.FIVE_MATCH));
    }

    @DisplayName("4개가 일치하는지 판별한다.")
    @Test
    void matchRank4() {
        Lotto userLotto = new Lotto(List.of(1, 2, 3, 4, 8, 9));
        Optional<LottoRank> result = winningLotto.match(userLotto);
        assertThat(result).isEqualTo(Optional.of(LottoRank.FOUR_MATCH));
    }

    @DisplayName("3개가 일치하는지 판별한다.")
    @Test
    void matchRank5() {
        Lotto userLotto = new Lotto(List.of(1, 2, 3, 8, 9, 10));
        Optional<LottoRank> result = winningLotto.match(userLotto);
        assertThat(result).isEqualTo(Optional.of(LottoRank.THREE_MATCH));
    }

    @DisplayName("2개 이하로 일치하면 Optional.empty()를 반환한다.")
    @Test
    void matchMiss() {
        Lotto userLotto = new Lotto(List.of(1, 2, 8, 9, 10, 11));
        Optional<LottoRank> result = winningLotto.match(userLotto);
        assertThat(result).isEmpty();
    }
}
