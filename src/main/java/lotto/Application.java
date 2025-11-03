package lotto;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        int purchaseAmount = Integer.parseInt(InputView.readPurchaseAmount());

        LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator();
        LottoService lottoService = new LottoService(lottoNumberGenerator);

        List<Lotto> lottos = lottoService.purchaseLottos(purchaseAmount);

        OutputView.printLottoCount(lottos.size());
        for (Lotto lotto : lottos) {
            OutputView.printLotto(lotto);
        }

        Lotto winningNumbers = new Lotto(toInteger(InputView.readWinningNumbers()));
        Integer bonusNumber = Integer.parseInt(InputView.readBonusNumber());
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);

        Map<LottoRank, Integer> rankCounts = lottoService.calculateResults(lottos, winningLotto);

        calculateProfitRate(rankCounts, purchaseAmount);
    }

    public static List<Integer> toInteger(String winningNumbers) {
        List<String> splittedNumbers = Arrays.asList(winningNumbers.split(","));

        List<Integer> numbers = splittedNumbers.stream()
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        return numbers;
    }

    public static void calculateProfitRate(Map<LottoRank, Integer> rankCounts, int purchaseAmount) {

        long totalPrize = rankCounts.entrySet().stream()
                .mapToLong(entry -> (long) entry.getKey().getPrizeMoney() * entry.getValue())
                .sum();

        double profitRate = 0.0;
        if (totalPrize > 0) {
            profitRate = ((double) totalPrize / purchaseAmount) * 100.0;
        }

        OutputView.printProfitRate(rankCounts, profitRate);
    }
}
