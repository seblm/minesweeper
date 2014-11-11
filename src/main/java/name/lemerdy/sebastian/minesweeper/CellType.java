package name.lemerdy.sebastian.minesweeper;

enum CellType {
    MINE('*', null),
    HEIGHT('8', null),
    SEVEN('7', HEIGHT),
    SIX('6', SEVEN),
    FIVE('5', SIX),
    FOUR('4', FIVE),
    THREE('3', FOUR),
    TWO('2', THREE),
    ONE('1', TWO),
    EMPTY(' ', ONE);

    private final char toChar;
    private final CellType next;

    CellType(char toChar, CellType next) {
        this.toChar = toChar;
        this.next = next;
    }

    CellType increment() {
        return next;
    }

    @Override
    public String toString() {
        return String.valueOf(toChar);
    }
}
