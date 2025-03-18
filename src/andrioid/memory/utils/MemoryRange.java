package andrioid.memory.utils;

public enum MemoryRange {
    Jh(2),
    Ch(1),
    Ca(4),
    Cd(8),
    Cb(16),
    PS(262144),
    A(32),
    J(65536),
    S(64),
    As(524288),
    V(1048576),
    O(-2080896),
    B(131072),
    Xs(16384),
    Xa(32768),;

    private final int value;

    MemoryRange(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
