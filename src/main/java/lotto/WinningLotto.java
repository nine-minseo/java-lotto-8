package lotto;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class WinningLotto {
    private final Lotto winningNumbers;
    private final int bonusNumber;
    private final Set<Integer> winningSet;

    public WinningLotto(Lotto winningNumbers, int bonusNumber) {
        validateDuplication(winningNumbers, bonusNumber);

        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
        this.winningSet = new HashSet<>(winningNumbers.getNumbers());
    }

    public void validateDuplication(Lotto winningNumbers, int bonusNumber) {
        if (winningNumbers.getNumbers().contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복되지 않는 1부터 45 사이의 숫자여야 합니다.");
        }
    }

    public Optional<LottoRank> match(Lotto userLotto) {
        int matchCount = Math.toIntExact(userLotto.getNumbers().stream()
                .filter(this.winningSet::contains)
                .count());

        boolean isMatchBonusNumber = userLotto.getNumbers().contains(bonusNumber);

        return LottoRank.match(matchCount, isMatchBonusNumber);
    }
}
