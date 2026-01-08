package lotto.controller;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import lotto.Lotto;
import lotto.domain.Rank;
import lotto.domain.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Controller {
    public void run() {
        int purchaseAmount = retryUntilValid(() -> InputView.readPurchaseAmount());

        List<Lotto> lottos = buyLottos(purchaseAmount);

        OutputView.printLottoCount(lottos.size());

        for (Lotto lotto : lottos) {
            OutputView.printLotto(lotto);
        }

        Lotto winningNumbers = retryUntilValid(() -> InputView.readWinningLotto());
        int bonusNum = retryUntilValid(() -> InputView.readBonusNumber(winningNumbers));
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNum);

        Map<Rank, Integer> result = new HashMap<>();
        for (Rank rank : Rank.values()) {
            result.put(rank, 0);
        }

        for (Lotto lotto : lottos) {
            Rank rank = winningLotto.match(lotto);
            result.put(rank, result.get(rank) + 1);
        }

        OutputView.printWinningStatisticsTitle();

        for (Rank rank : Rank.values()) {
            if (rank != Rank.MISS) {
                System.out.println(rank.getMessage() + " - " + result.get(rank) + "개");
            }
        }

        double totalPrize = 0;
        for (Rank rank : result.keySet()) {
            totalPrize += (double) rank.getWinningMoney() * result.get(rank);
        }

        double profitRate = (totalPrize / purchaseAmount) * 100;

        OutputView.printProfitRate(profitRate);
    }

    private List<Lotto> buyLottos(int purchaseAmount) {
        int lottoCount = purchaseAmount / 1000;

        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            lottos.add(generateLotto());
        }

        return lottos;
    }

    private Lotto generateLotto() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6)
                .stream()
                .sorted()
                .toList();

        return new Lotto(numbers);
    }

    private static <T> T retryUntilValid(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
