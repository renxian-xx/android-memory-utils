package andrioid.memory.utils;

public class Result<T extends Number> {
    private long address;
    private T value;
    private DataType dataType;

    public Result(long address, T value, DataType dataType) {
        this.address = address;
        this.value = value;
        this.dataType = dataType;
    }

    public long getAddress() {
        return address;
    }

    public void setAddress(long address) {
        this.address = address;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    @Override
    public String toString() {
        return Long.toString(address, 16) + " " + value + " " + dataType;
    }
}
