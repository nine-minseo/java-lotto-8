package lotto;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        int purchaseAmount = InputView.readPurchaseAmount();
        int lottoCount = purchaseAmount / 1000;

        OutputView.printLottoCount(lottoCount);

        List<String> winningNumbers = InputView.readWinningNumbers();
        int bonusNum = InputView.readBonusNumber();

        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6)
                    .stream()
                    .sorted()
                    .toList();
            lottos.add(new Lotto(numbers));
        }

    }
}
