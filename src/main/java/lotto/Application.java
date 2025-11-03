package lotto;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        int purchaseAmount = Integer.parseInt(InputView.readPurchaseAmount());
        int lottoCount = getLottoCount(purchaseAmount);
        OutputView.printLottoCount(lottoCount);

        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            Lotto lotto = new Lotto(makeNumbers());
            lottos.add(lotto);
            OutputView.printLotto(lotto);
        }

        Lotto winningNumbers = new Lotto(toInteger(InputView.readWinningNumbers()));
        Integer bonusNumber = Integer.parseInt(InputView.readBonusNumber());
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);

        calculateProfitRate(lottos, winningLotto);
    }

    public static int getLottoCount(int purchaseAmount) {
        return purchaseAmount / 1000;
    }

    public static List<Integer> makeNumbers() {
        return Randoms.pickUniqueNumbersInRange(1, 45, 6)
                .stream()
                .sorted()
                .collect(Collectors.toList());
    }

    public static List<Integer> toInteger(String winningNumbers) {
        List<String> splittedNumbers = Arrays.asList(winningNumbers.split(","));

        List<Integer> numbers = splittedNumbers.stream()
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        return numbers;
    }

    public static void calculateProfitRate(List<Lotto> lottos, WinningLotto winningLotto) {
        Map<LottoRank, Integer> rankCounts = new EnumMap(LottoRank.class);

        for (Lotto lotto : lottos) {
            Optional<LottoRank> optionalRank = winningLotto.match(lotto);

            optionalRank.ifPresent(rank -> {
                rankCounts.put(rank, rankCounts.getOrDefault(rank, 0) + 1);
            });
        }
        long totalPrize = rankCounts.entrySet().stream()
                .mapToLong(entry -> (long) entry.getKey().getPrizeMoney() * entry.getValue())
                .sum();

        int totalSpent = lottos.size() * 1000;
        double profitRate = (totalPrize == 0) ? 0.0 : ((double) totalPrize / totalSpent) * 100.0;

        OutputView.printProfitRate(rankCounts, profitRate);
    }
}
