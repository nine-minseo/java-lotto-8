package lotto;

import java.util.List;
import lotto.view.InputView;

public class Application {
    public static void main(String[] args) {
        int purchaseAmount = InputView.readPurchaseAmount();
        List<String> winningNumbers = InputView.readWinningNumbers();
        int bonusNum = InputView.readBonusNumber();

        int lottoCount = purchaseAmount / 1000;

    }
}
