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
    void 기능_테스트_8000원_구매후_당첨확인() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    // given: 키보드 입력 시나리오 (금액, 당첨번호, 보너스번호)
                    run("8000", "1,2,3,4,5,6", "7");

                    // then: 출력 결과에 포함되어야 하는 문자열 확인
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
                // Randoms가 뽑을 숫자를 강제로 지정 (위에서부터 순서대로)
                List.of(8, 21, 23, 41, 42, 43), // 첫 번째 로또
                List.of(3, 5, 11, 16, 32, 38),  // 두 번째 로또
                List.of(7, 11, 16, 35, 36, 44),
                List.of(1, 8, 11, 31, 41, 42),
                List.of(13, 14, 16, 38, 42, 45),
                List.of(7, 11, 30, 40, 42, 43),
                List.of(2, 13, 22, 32, 38, 45),
                List.of(1, 3, 5, 14, 22, 45)   // 여덟 번째 로또 (3개 일치 당첨)
        );
    }

    @Test
    void 예외_테스트_잘못된_입력후_에러메시지_출력() {
        assertSimpleTest(() -> {
            // given: 잘못된 입력 (1000원 단위 아님, 문자 포함됨)
            runException("1500j");

            // then: [ERROR] 메시지가 출력되는지 확인
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
