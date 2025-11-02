package lotto;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        int purchaseAmount = Integer.parseInt(InputView.getPurchaseAmount());
        int lottoCount = getLottoCount(purchaseAmount);
        OutputView.printLottoCount(lottoCount);

        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            Lotto lotto = new Lotto(makeNumbers());
            lottos.add(lotto);
            OutputView.printLotto(lotto);
        }
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
}
