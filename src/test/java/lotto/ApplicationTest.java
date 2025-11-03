package lotto;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomUniqueNumbersInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

class ApplicationTest extends NsTest {
    private static final String ERROR_MESSAGE = "[ERROR]";

    @Test
    void 기능_테스트() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    run("8000", "1,2,3,4,5,6", "7");
                    assertThat(output()).contains(
                            "8개를 구매했습니다.",
                            "[8, 21, 23, 41, 42, 43]",
                            "[3, 5, 11, 16, 32, 38]",
                            "[7, 11, 16, 35, 36, 44]",
                            "[1, 8, 11, 31, 41, 42]",
                            "[13, 14, 16, 38, 42, 45]",
                            "[7, 11, 30, 40, 42, 43]",
                            "[2, 13, 22, 32, 38, 45]",
                            "[1, 3, 5, 14, 22, 45]",
                            "3개 일치 (5,000원) - 1개",
                            "4개 일치 (50,000원) - 0개",
                            "5개 일치 (1,500,000원) - 0개",
                            "5개 일치, 보너스 볼 일치 (30,000,000원) - 0개",
                            "6개 일치 (2,000,000,000원) - 0개",
                            "총 수익률은 62.5%입니다."
                    );
                },
                List.of(8, 21, 23, 41, 42, 43),
                List.of(3, 5, 11, 16, 32, 38),
                List.of(7, 11, 16, 35, 36, 44),
                List.of(1, 8, 11, 31, 41, 42),
                List.of(13, 14, 16, 38, 42, 45),
                List.of(7, 11, 30, 40, 42, 43),
                List.of(2, 13, 22, 32, 38, 45),
                List.of(1, 3, 5, 14, 22, 45)
        );
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() -> {
            runException("1000j");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }

    @Test
    void 구입금액_1000원_단위_예외_처리_후_재입력_테스트() {
        assertSimpleTest(() -> {
            run("1500", "1000", "1,2,3,4,5,6", "7");
            assertThat(output()).contains(
                    "[ERROR] 로또 구입 금액은 1,000원 단위여야 합니다.",
                    "1개를 구매했습니다."
            );
        });
    }

    @Test
    void 당첨번호_숫자아님_예외_처리_후_재입력_테스트() {
        assertSimpleTest(() -> {
            run("1000", "1,2,3,4,5,a", "1,2,3,4,5,6", "7");
            assertThat(output()).contains(
                    "[ERROR] 당첨 번호는 숫자로만 구성되어야 합니다.",
                    "당첨 통계"
            );
        });
    }

    @Test
    void 당첨번호_개수초과_예외_처리_후_재입력_테스트() {
        assertSimpleTest(() -> {
            run("1000", "1,2,3,4,5,6,7", "1,2,3,4,5,6", "7");
            assertThat(output()).contains(
                    "[ERROR] 1부터 45 사이의 중복되지 않은 로또 번호 6개를 입력해야 합니다.",
                    "당첨 통계"
            );
        });
    }

    @Test
    void 당첨번호_중복_예외_처리_후_재입력_테스트() {
        assertSimpleTest(() -> {
            run("1000", "1,2,3,4,5,5", "1,2,3,4,5,6", "7");
            assertThat(output()).contains(
                    "[ERROR] 1부터 45 사이의 중복되지 않은 로또 번호 6개를 입력해야 합니다.",
                    "당첨 통계"
            );
        });
    }

    @Test
    void 보너스번호_중복_예외_처리_후_재입력_테스트() {
        assertSimpleTest(() -> {
            run("1000", "1,2,3,4,5,6", "6", "7");
            assertThat(output()).contains(
                    "[ERROR] 보너스 번호는 당첨 번호와 중복되지 않는 1부터 45 사이의 숫자여야 합니다.",
                    "당첨 통계"
            );
        });
    }

    @Test
    void 구입금액_빈값_예외_처리_후_재입력_테스트() {
        assertSimpleTest(() -> {
            run("", "1000", "1,2,3,4,5,6", "7");
            assertThat(output()).contains(
                    "[ERROR] 1,000원 단위로 로또 구입 금액을 입력해야 합니다.",
                    "1개를 구매했습니다."
            );
        });
    }

    @Test
    void 구입금액_공백_예외_처리_후_재입력_테스트() {
        assertSimpleTest(() -> {
            run("1 000", "1000", "1,2,3,4,5,6", "7");
            assertThat(output()).contains(
                    "[ERROR] 로또 구입 금액에 공백이 없어야 합니다.",
                    "1개를 구매했습니다."
            );
        });
    }

    @Test
    void 당첨번호_빈값_예외_처리_후_재입력_테스트() {
        assertSimpleTest(() -> {
            run("1000", "", "1,2,3,4,5,6", "7");
            assertThat(output()).contains(
                    "[ERROR] 1부터 45 사이의 로또 번호 6개를 입력해야 합니다.",
                    "당첨 통계"
            );
        });
    }

    @Test
    void 당첨번호_개수미만_예외_처리_후_재입력_테스트() {
        assertSimpleTest(() -> {
            run("1000", "1,2,3,4,5", "1,2,3,4,5,6", "7");
            assertThat(output()).contains(
                    "[ERROR] 1부터 45 사이의 중복되지 않은 로또 번호 6개를 입력해야 합니다.",
                    "당첨 통계"
            );
        });
    }

    @Test
    void 당첨번호_범위초과_예외_처리_후_재입력_테스트() {
        assertSimpleTest(() -> {
            run("1000", "1,2,3,4,5,46", "1,2,3,4,5,6", "7");
            assertThat(output()).contains(
                    "[ERROR] 1부터 45 사이의 중복되지 않은 로또 번호 6개를 입력해야 합니다.",
                    "당첨 통계"
            );
        });
    }

    @Test
    void 당첨번호_구분자_예외_처리_후_재입력_테스트() {
        assertSimpleTest(() -> {
            run("1000", "1;2;3;4;5;6", "1,2,3,4,5,6", "7");
            assertThat(output()).contains(
                    "[ERROR] 콤마(,)로 구분한 로또 번호를 입력해야 합니다.",
                    "당첨 통계"
            );
        });
    }

    @Test
    void 보너스번호_빈값_예외_처리_후_재입력_테스트() {
        assertSimpleTest(() -> {
            run("1000", "1,2,3,4,5,6", "", "7");
            assertThat(output()).contains(
                    "[ERROR] 보너스 번호 1개를 입력해야 합니다.",
                    "당첨 통계"
            );
        });
    }

    @Test
    void 보너스번호_숫자아님_예외_처리_후_재입력_테스트() {
        assertSimpleTest(() -> {
            run("1000", "1,2,3,4,5,6", "a", "7");
            assertThat(output()).contains(
                    "[ERROR] 보너스 번호는 문자가 아닌 1부터 45 사이의 숫자여야 합니다.",
                    "당첨 통계"
            );
        });
    }

    @Test
    void 보너스번호_범위초과_예외_처리_후_재입력_테스트() {
        assertSimpleTest(() -> {
            run("1000", "1,2,3,4,5,6", "46", "7");
            assertThat(output()).contains(
                    "[ERROR] 보너스 번호는 1부터 45 사이의 숫자여야 합니다.",
                    "당첨 통계"
            );
        });
    }
}
