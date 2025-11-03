package lotto.service;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lotto.Lotto;
import lotto.LottoNumberGenerator;
import lotto.LottoRank;
import lotto.WinningLotto;

public class LottoService {
    private final LottoNumberGenerator lottoNumberGenerator;

    public LottoService(LottoNumberGenerator lottoNumberGenerator) {
        this.lottoNumberGenerator = lottoNumberGenerator;
    }

    public List<Lotto> purchaseLottos(int purchaseAmount) {
        int lottoCount = getLottoCount(purchaseAmount);

        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            lottos.add(new Lotto(lottoNumberGenerator.generate()));
        }
        return lottos;
    }

    public int getLottoCount(int purchaseAmount) {
        if (purchaseAmount % 1000 != 0) {
            throw new IllegalArgumentException("[ERROR] 로또 구입 금액은 1,000원 단위여야 합니다.");
        }
        return purchaseAmount / 1000;
    }

    public Map<LottoRank, Integer> calculateResults(List<Lotto> lottos, WinningLotto winningLotto) {
        Map<LottoRank, Integer> rankCounts = new EnumMap(LottoRank.class);

        for (Lotto lotto : lottos) {
            Optional<LottoRank> optionalRank = winningLotto.match(lotto);

            optionalRank.ifPresent(rank -> {
                rankCounts.put(rank, rankCounts.getOrDefault(rank, 0) + 1);
            });
        }

        return rankCounts;
    }
}
