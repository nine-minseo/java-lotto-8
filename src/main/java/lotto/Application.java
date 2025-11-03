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

        LottoResult lottoResult = lottoService.calculateResults(lottos, winningLotto);
        printStatistics(lottoResult, purchaseAmount);
    }

    public static List<Integer> toInteger(String winningNumbers) {
        List<String> splittedNumbers = Arrays.asList(winningNumbers.split(","));

        List<Integer> numbers = splittedNumbers.stream()
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        return numbers;
    }

    public static void printStatistics(LottoResult lottoResult, int purchaseAmount) {
        Map<LottoRank, Integer> rankCounts = lottoResult.getRankCounts();
        double profitRate = lottoResult.calculateProfitRate(purchaseAmount);

        OutputView.printProfitRate(rankCounts, profitRate);
    }
}
