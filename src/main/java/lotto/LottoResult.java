package lotto;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LottoResult {

    private final Map<Rank, Integer> statistics;

    public LottoResult(LottoTickets lottoTickets, WinningLotto winningLotto) {
        this.statistics = calculateStatistics(lottoTickets.getLottos(), winningLotto);
    }

    private Map<Rank, Integer> calculateStatistics(List<Lotto> lottoTickets, WinningLotto winningLotto) {
        Map<Rank, Integer> stats = initializeStatistics();

        lottoTickets.forEach(lotto -> {
            Rank rank = getRankOfLotto(lotto, winningLotto);
            if (rank != Rank.MISS) {
                stats.put(rank, stats.getOrDefault(rank, 0) + 1);
            }
        });

        return stats;
    }

    private Map<Rank, Integer> initializeStatistics() {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank != Rank.MISS)
                .collect(Collectors.toMap(
                        Function.identity(),
                        rank -> 0,
                        (a, b) -> b,
                        () -> new EnumMap<>(Rank.class)
                ));
    }

    private Rank getRankOfLotto(Lotto lotto, WinningLotto winningLotto) {
        int matchCount = countMatchingNumbers(lotto, winningLotto.getLotto());
        boolean matchBonus = lotto.getNumbers().contains(winningLotto.getBonusNumber());

        return Rank.valueOf(matchCount, matchBonus);
    }

    private int countMatchingNumbers(Lotto purchasedLotto, Lotto winningLotto) {
        return (int) purchasedLotto.getNumbers().stream()
                .filter(winningLotto.getNumbers()::contains)
                .count();
    }

    private long calculateTotalPrize() {
        return statistics.entrySet().stream()
                .mapToLong(entry -> (long) entry.getKey().getPrizeMoney() * entry.getValue())
                .sum();
    }

    public double calculateYield(int purchaseAmount) {
        long totalPrize = calculateTotalPrize();
        double yield = (double) totalPrize / purchaseAmount * 100;

        return Math.round(yield * 10.0) / 10.0;
    }

    public Map<Rank, Integer> getStatistics() {
        return Collections.unmodifiableMap(statistics);
    }
}
