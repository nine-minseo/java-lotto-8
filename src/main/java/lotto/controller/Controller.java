package lotto.controller;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import lotto.Lotto;
import lotto.domain.LottoResult;
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

        LottoResult lottoResult = new LottoResult();

        for (Lotto lotto : lottos) {
            Rank rank = winningLotto.match(lotto);
            lottoResult.add(rank);
        }

        OutputView.printWinningStatisticsTitle();

        for (Rank rank : Rank.values()) {
            if (rank != Rank.MISS) {
                System.out.println(rank.getMessage() + " - " + lottoResult.getCount(rank) + "개");
            }
        }

        double profitRate = lottoResult.calculateProfitRate(purchaseAmount);

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
