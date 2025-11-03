package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoTickets {

    private final List<Lotto> lottos;

    public LottoTickets(int count, LottoGenerator lottoGenerator) {
        this.lottos = makeLottos(count, lottoGenerator);
    }

    private List<Lotto> makeLottos(int count, LottoGenerator lottoGenerator) {
        List<Lotto> madeLottos = new ArrayList<>();
        for (int lo = 0; lo < count; lo++) {
            madeLottos.add(lottoGenerator.lottoGenerator());
        }
        return madeLottos;
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }


}
