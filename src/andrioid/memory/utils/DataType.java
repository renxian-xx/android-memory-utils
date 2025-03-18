package andrioid.memory.utils;

public enum DataType {
    Byte(1),
    Word(2),
    DWord(4),
    QWord(32),
    Float(16),
    Double(64),
    Xor(8),
    Auto(127);

    private final int value;

    DataType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
