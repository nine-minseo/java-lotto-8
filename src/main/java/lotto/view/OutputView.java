package lotto.view;

import java.util.stream.Collectors;
import lotto.Lotto;

public class OutputView {
    public static void printLottoCount(int num) {
        System.out.println("\n" + num + "개를 구매했습니다.");
    }

    public static void printLotto(Lotto lotto) {
        String result = lotto.getNumbers().stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", ", "[", "]"));

        System.out.println(result);
    }

    public static void printWinningStatisticsTitle() {
        System.out.println("\n당첨 통계");
        System.out.println("---");
    }

    public static void printProfitRate(double profitRate) {
        System.out.printf("총 수익률은 %.1f%%입니다.", profitRate);
    }
}
