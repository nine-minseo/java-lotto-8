package lotto.view;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lotto.Lotto;
import lotto.LottoRank;
import lotto.LottoResult;

public class OutputView {
    private static final String PROMPT_LOTTO_COUNT = "개를 구매했습니다.";
    private static final String DELIMITER = ", ";
    private static final String PREFIX = "[";
    private static final String SUFFIX = "]";
    private static final String STATISTICS_TITLE = "\n당첨 통계";
    private static final String STATISTICS_SEPARATOR = "---";

    public static void printLottoCount(int lottoCount) {
        System.out.println("\n" + lottoCount + PROMPT_LOTTO_COUNT);
    }

    public static void printLotto(Lotto lotto) {
        List<Integer> numbers = lotto.getNumbers();

        String formattedLottoNumbers = numbers.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(DELIMITER, PREFIX, SUFFIX));

        System.out.println(formattedLottoNumbers);
    }

    public static void printProfitRate(LottoResult lottoResult, int purchaseAmount) {
        System.out.println(STATISTICS_TITLE);
        System.out.println(STATISTICS_SEPARATOR);

        Map<LottoRank, Integer> rankCounts = lottoResult.getRankCounts();
        double profitRate = lottoResult.calculateProfitRate(purchaseAmount);

        for (LottoRank rank : LottoRank.values()) {
            int count = rankCounts.getOrDefault(rank, 0);

            System.out.printf("%s (%s) - %d개%n",
                    rank.getDescription(),
                    rank.getFormattedPrize(),
                    count
            );
        }

        System.out.printf("총 수익률은 %.1f%%입니다.%n", profitRate);
    }

    public static void printError(String message) {
        System.out.println(message);
    }
}
