package chess.domain;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public enum CoordinateY {
    RANK_1("1", 7),
    RANK_2("2", 6),
    RANK_3("3", 5),
    RANK_4("4", 4),
    RANK_5("5", 3),
    RANK_6("6", 2),
    RANK_7("7", 1),
    RANK_8("8", 0);

    private String symbol;
    private int index;

    CoordinateY(String symbol, int index) {
        this.symbol = symbol;
        this.index = index;
    }

    public Optional<CoordinateY> move(int n) {
        return valueOf(index - n);
    }

    public static Optional<CoordinateY> valueOf(int index) {
        return Arrays.stream(values())
            .filter(coord -> coord.index == index)
            .findFirst();
    }

    public String getSymbol() {
        return symbol;
    }

    public int getIndex() {
        return index;
    }

    public static List<CoordinateY> allAscendingCoordinates() {
        return Arrays.stream(values())
            .sorted(Comparator.comparingInt(CoordinateY::getIndex))
            .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return String.format("CoordinateY { symbol: '%s', index: %d }", symbol, index);
    }
}