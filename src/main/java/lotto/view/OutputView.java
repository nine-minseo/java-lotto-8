package lotto.view;

import java.util.List;
import java.util.stream.Collectors;
import lotto.Lotto;

public class OutputView {
    private static final String PROMPT_LOTTO_COUNT = "개를 구매했습니다.";
    private static final String DELIMITER = ", ";
    private static final String PREFIX = "[";
    private static final String SUFFIX = "]";

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
}
