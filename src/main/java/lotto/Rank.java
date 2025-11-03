package lotto;

public enum Rank {

    FIRST(6, false, 2_000_000_000, "6개 일치 (2,000,000,000원)"),
    SECOND(5, true, 30_000_000, "5개 일치, 보너스 볼 일치 (30,000,000원)"),
    THIRD(5, false, 1_500_000, "5개 일치 (1,500,000원)"),
    FOURTH(4, false, 50_000, "4개 일치 (50,000원)"),
    FIFTH(3, false, 5_000, "3개 일치 (5,000원)"),
    MISS(0, false, 0, "낙첨");

    private final int matchCount;
    private final boolean matchBonus;
    private final int prizeMoney;
    private final String message;

    Rank(int matchCount, boolean matchBonus, int prizeMoney, String message) {
        this.matchCount = matchCount;
        this.matchBonus = matchBonus;
        this.prizeMoney = prizeMoney;
        this.message = message;
    }


    public static Rank valueOf(int matchCount, boolean matchBonus) {
        if (matchCount < 3) {
            return MISS;
        }

        if (matchCount == 6) {
            return FIRST;
        }

        if (matchCount == 5) {
            return findRankForFiveMatches(matchBonus);
        }

        if (matchCount == 4) {
            return FOURTH;
        }

        return FIFTH;
    }

    private static Rank findRankForFiveMatches(boolean matchBonus) {
        if (matchBonus) {
            return SECOND;
        }
        return THIRD;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public String getMessage() {
        return message;
    }
}
