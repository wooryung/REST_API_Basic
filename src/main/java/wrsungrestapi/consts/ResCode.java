package wrsungrestapi.consts;

public enum ResCode {
    SUCCESS(0), NO_SUCH_DATA(-1), DUPLICATE_KEY(-2), NULL_VALUE(-3), UNKNOWN(-99);

    private final int value;

    ResCode(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
