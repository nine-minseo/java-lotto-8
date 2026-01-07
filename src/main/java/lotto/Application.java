package lotto;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lotto.domain.Rank;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        int purchaseAmount = InputView.readPurchaseAmount();
        int lottoCount = purchaseAmount / 1000;

        OutputView.printLottoCount(lottoCount);

        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6)
                    .stream()
                    .sorted()
                    .toList();
            lottos.add(new Lotto(numbers));
        }

        for (Lotto lotto : lottos) {
            OutputView.printLotto(lotto);
        }

        List<Integer> winningNumbers = InputView.readWinningNumbers();
        int bonusNum = InputView.readBonusNumber();

        Map<Rank, Integer> result = new HashMap<>();
        for (Rank rank : Rank.values()) {
            result.put(rank, 0);
        }

        for (Lotto lotto : lottos) {
            int matchCount = lotto.countMatch(winningNumbers);
            boolean isMatchBonus = lotto.contains(bonusNum);

            Rank rank = Rank.valueOf(matchCount, isMatchBonus);

            result.put(rank, result.get(rank) + 1);
        }
    }
}
