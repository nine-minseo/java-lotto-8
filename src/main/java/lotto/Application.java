package lotto;

import java.util.List;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        int purchaseAmount = InputView.readPurchaseAmount();

        LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator();
        LottoService lottoService = new LottoService(lottoNumberGenerator);
        List<Lotto> lottos = lottoService.purchaseLottos(purchaseAmount);

        OutputView.printLottoCount(lottos.size());
        for (Lotto lotto : lottos) {
            OutputView.printLotto(lotto);
        }

        Lotto winningNumbers = new Lotto(InputView.readWinningNumbers());
        Integer bonusNumber = InputView.readBonusNumber();
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);

        LottoResult lottoResult = lottoService.calculateResults(lottos, winningLotto);
        printStatistics(lottoResult, purchaseAmount);
    }

    public static void printStatistics(LottoResult lottoResult, int purchaseAmount) {
        OutputView.printProfitRate(lottoResult, purchaseAmount);
    }
}
