package lotto.view;

public class OutputView {
    private static final String PROMPT_LOTTO_COUNT = "개를 구매했습니다.";

    public static void printLottoCount(int lottoCount) {
        System.out.println("\n" + lottoCount + PROMPT_LOTTO_COUNT);
    }
}
