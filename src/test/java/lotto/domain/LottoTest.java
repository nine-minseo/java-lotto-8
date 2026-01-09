package lotto.domain;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {

    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또_번호가_범위를_벗어나면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 46)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또_번호와_당첨_번호의_일치_개수를_정확히_반환한다() {
        // given
        Lotto myLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 7, 8, 9));

        // when
        int matchCount = myLotto.countMatch(winningLotto);

        // then
        assertThat(matchCount).isEqualTo(3);
    }

    @Test
    void 로또_번호에_특정_숫자가_포함되어_있는지_확인한다() {
        // given
        Lotto myLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        // when, then
        assertThat(myLotto.contains(3)).isTrue();
        assertThat(myLotto.contains(7)).isFalse();
    }
}