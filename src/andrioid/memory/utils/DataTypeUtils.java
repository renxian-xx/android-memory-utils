package andrioid.memory.utils;

public class DataTypeUtils {

    public static <T> T bytesToNumber(byte[] bytes, DataType dataType) {
        if (dataType == DataType.Byte) {
            return (T) Byte.valueOf(bytes[0]);
        } else if (dataType == DataType.Word) {
            return (T) Short.valueOf(bytesToShort(bytes));
        } else if (dataType == DataType.DWord) {
            return (T) Integer.valueOf(bytesToInt(bytes));
        } else if (dataType == DataType.QWord) {
            return (T) Long.valueOf(bytesToLong(bytes));
        } else if (dataType == DataType.Float) {
            return (T) Float.valueOf(bytesToFloat(bytes));
        } else if (dataType == DataType.Double) {
            return (T) Double.valueOf(bytesToDouble(bytes));
        }
        return null;
    }

    public static byte[] numberToBytes(Number number, DataType dataType) {
        if (dataType == DataType.Byte) {
            return new byte[]{number.byteValue()};
        } else if (dataType == DataType.Word) {
            return shortToBytes(number.shortValue());
        } else if (dataType == DataType.DWord) {
            return intToBytes(number.intValue());
        } else if (dataType == DataType.QWord) {
            return longToBytes(number.longValue());
        } else if (dataType == DataType.Float) {
            return floatToBytes(number.floatValue());
        } else if (dataType == DataType.Double) {
            return doubleToBytes(number.doubleValue());
        }
        return null;
    }

    //byte数组转short
    public static short bytesToShort(byte[] bytes) {
        short addr = (short) (bytes[0] & 0xFF);
        addr |= (short) ((bytes[1] & 0xFF) << 8);
        return addr;
    }

    //byte数组转char
    public static char bytesToChar(byte[] bytes) {
        return (char) bytesToShort(bytes);
    }


    //byte数组转int
    public static int bytesToInt(byte[] bytes) {
        int addr = bytes[0] & 0xFF;
        addr |= (bytes[1] & 0xFF) << 8;
        addr |= (bytes[2] & 0xFF) << 16;
        addr |= (bytes[3] & 0xFF) << 24;
        return addr;
    }

    //byte数组转long
    public static long bytesToLong(byte[] bytes) {
        long addr = bytes[0] & 0xFF;
        addr |= (long) (bytes[1] & 0xFF) << 8;
        addr |= (long) (bytes[2] & 0xFF) << 16;
        addr |= (long) (bytes[3] & 0xFF) << 24;
        addr |= (long) (bytes[4] & 0xFF) << 32;
        addr |= (long) (bytes[5] & 0xFF) << 40;
        addr |= (long) (bytes[6] & 0xFF) << 48;
        addr |= (long) (bytes[7] & 0xFF) << 56;
        return addr;
    }

    //byte数组转float
    public static float bytesToFloat(byte[] bytes) {
        int bits = bytesToInt(bytes);
        return Float.intBitsToFloat(bits);
    }

    //byte数组转double
    public static double bytesToDouble(byte[] bytes) {
        long bits = bytesToLong(bytes);
        return Double.longBitsToDouble(bits);
    }


    //short转byte数组
    public static byte[] shortToBytes(short value) {
        byte[] bytes = new byte[2];
        bytes[0] = (byte) (value & 0xFF);
        bytes[1] = (byte) ((value >> 8) & 0xFF);
        return bytes;
    }

    //char转byte数组
    public static byte[] charToBytes(char value) {
        return shortToBytes((short) value);
    }


    //int转byte数组
    public static byte[] intToBytes(int value) {
        byte[] bytes = new byte[4];
        bytes[0] = (byte) (value & 0xFF);
        bytes[1] = (byte) ((value >> 8) & 0xFF);
        bytes[2] = (byte) ((value >> 16) & 0xFF);
        bytes[3] = (byte) ((value >> 24) & 0xFF);
        return bytes;
    }

    //long转byte数组
    public static byte[] longToBytes(long value) {
        byte[] bytes = new byte[8];
        bytes[0] = (byte) (value & 0xFF);
        bytes[1] = (byte) ((value >> 8) & 0xFF);
        bytes[2] = (byte) ((value >> 16) & 0xFF);
        bytes[3] = (byte) ((value >> 24) & 0xFF);
        bytes[4] = (byte) ((value >> 32) & 0xFF);
        bytes[5] = (byte) ((value >> 40) & 0xFF);
        bytes[6] = (byte) ((value >> 48) & 0xFF);
        bytes[7] = (byte) ((value >> 56) & 0xFF);
        return bytes;
    }

    //float转byte数组
    public static byte[] floatToBytes(float value) {
        int bits = Float.floatToIntBits(value);
        return intToBytes(bits);
    }

    //double转byte数组
    public static byte[] doubleToBytes(double value) {
        long bits = Double.doubleToLongBits(value);
        return longToBytes(bits);
    }

}
