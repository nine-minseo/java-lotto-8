package lotto.controller;

import lotto.Lotto;
import lotto.LottoNumberGenerator;
import lotto.LottoResult;
import lotto.WinningLotto;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoController {

    private final LottoService lottoService;

    public LottoController() {
        this.lottoService = new LottoService(new LottoNumberGenerator());
    }

    public void run() {
        int purchaseAmount = getValidPurchaseAmount();
        List<Lotto> lottos = lottoService.purchaseLottos(purchaseAmount);

        OutputView.printLottoCount(lottos.size());
        for (Lotto lotto : lottos) {
            OutputView.printLotto(lotto);
        }

        WinningLotto winningLotto = getValidWinningLotto();

        LottoResult lottoResult = lottoService.calculateResults(lottos, winningLotto);
        OutputView.printProfitRate(lottoResult, purchaseAmount);
    }

    private int getValidPurchaseAmount() {
        while (true) {
            try {
                int purchaseAmount = InputView.readPurchaseAmount();
                lottoService.getLottoCount(purchaseAmount);
                return purchaseAmount;
            } catch (IllegalArgumentException e) {
                OutputView.printError(e.getMessage());
            }
        }
    }

    private WinningLotto getValidWinningLotto() {
        Lotto winningNumbers = getValidWinningNumbers();

        while (true) {
            try {
                int bonusNumber = InputView.readBonusNumber();
                return new WinningLotto(winningNumbers, bonusNumber);
            } catch (IllegalArgumentException e) {
                OutputView.printError(e.getMessage());
            }
        }
    }

    private Lotto getValidWinningNumbers() {
        while (true) {
            try {
                return new Lotto(InputView.readWinningNumbers());
            } catch (IllegalArgumentException e) {
                OutputView.printError(e.getMessage());
            }
        }
    }
}