package lotto;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import lotto.domain.Rank;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        int purchaseAmount = retryUntilValid(() -> InputView.readPurchaseAmount());
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

        Lotto winningLotto = retryUntilValid(() -> InputView.readWinningLotto());
        int bonusNum = retryUntilValid(() -> InputView.readBonusNumber(winningLotto));

        Map<Rank, Integer> result = new HashMap<>();
        for (Rank rank : Rank.values()) {
            result.put(rank, 0);
        }

        for (Lotto lotto : lottos) {
            int matchCount = lotto.countMatch(winningLotto);
            boolean isMatchBonus = lotto.contains(bonusNum);

            Rank rank = Rank.valueOf(matchCount, isMatchBonus);

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
            totalPrize += (double)rank.getWinningMoney() * result.get(rank);
        }

        double profitRate = (totalPrize / purchaseAmount) * 100;

        OutputView.printProfitRate(profitRate);
    }

    private static <T> T retryUntilValid(Supplier <T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
