package lotto;

import java.util.List;
import lotto.view.InputView;

public class Application {
    public static void main(String[] args) {
        String purchaseAmount = InputView.readPurchaseAmount();
        List<String> winningNumbers = InputView.readWinningNumbers();
    }
}
