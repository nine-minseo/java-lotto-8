package lotto;

import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        int purchaseAmount = Integer.parseInt(InputView.getPurchaseAmount());
        int lottoCount = getLottoCount(purchaseAmount);
        OutputView.printLottoCount(lottoCount);
    }

    public static int getLottoCount(int purchaseAmount) {
        return purchaseAmount / 1000;
    }
}
