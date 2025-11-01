package lotto;

import lotto.view.InputView;

public class Application {
    public static void main(String[] args) {
        int purchaseAmount = Integer.parseInt(InputView.getPurchaseAmount());
        int lottoCount = getLottoCount(purchaseAmount);
    }

    public static int getLottoCount(int purchaseAmount) {
        return purchaseAmount / 1000;
    }
}
