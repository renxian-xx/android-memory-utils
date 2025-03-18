package andrioid.memory.utils;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryOperator {

    private int pid; //pid
    private String mapsPath; //maps文件路径
    private String memoryPath; //mem文件路径
    private Map<MemoryRange, List<MemoryMap>> maps; //内存范围集合

    public MemoryOperator(String packageName) {
        init(packageName);
    }

    private void init(String packageName) {
        this.pid = ProcessUtils.getPid(packageName);
        this.mapsPath = "/proc/" + pid + "/maps";
        this.memoryPath = "/proc/" + pid + "/mem";


        initMaps();
    }

    private void initMaps() {
        this.maps = new HashMap<>();
        this.maps.put(MemoryRange.Jh, new ArrayList<>());
        this.maps.put(MemoryRange.Ch, new ArrayList<>());
        this.maps.put(MemoryRange.Ca, new ArrayList<>());
        this.maps.put(MemoryRange.Cd, new ArrayList<>());
        this.maps.put(MemoryRange.Cb, new ArrayList<>());
        this.maps.put(MemoryRange.PS, new ArrayList<>());
        this.maps.put(MemoryRange.A, new ArrayList<>());
        this.maps.put(MemoryRange.J, new ArrayList<>());
        this.maps.put(MemoryRange.S, new ArrayList<>());
        this.maps.put(MemoryRange.As, new ArrayList<>());
        this.maps.put(MemoryRange.V, new ArrayList<>());
        this.maps.put(MemoryRange.O, new ArrayList<>());
        this.maps.put(MemoryRange.B, new ArrayList<>());
        this.maps.put(MemoryRange.Xs, new ArrayList<>());
        this.maps.put(MemoryRange.Xa, new ArrayList<>());

        List<MemoryMap> memoryMaps = MapsUtils.getMemoryMaps(this.mapsPath);

        for (MemoryMap m : memoryMaps) {
            String chmod = m.getChmod();
            String pathname = m.getPathname();
            if (pathname == null && chmod.startsWith("rw")) {
                this.maps.get(MemoryRange.A).add(m);
            } else if (pathname != null && pathname.equals("[anon:libc_malloc]") && chmod.startsWith("rw")) {
                System.out.println(m);
                this.maps.get(MemoryRange.Ca).add(m);
            }
        }
    }

    public <T extends Number> List<Result<T>> search(T value, DataType dataType, MemoryRange memoryRange) {
        List<Result<T>> results = new ArrayList<>();
        for (MemoryMap memoryMap : maps.get(memoryRange)) {
            long start = memoryMap.getStart();
            long end = memoryMap.getEnd();
            List<Result<T>> list = search(value, dataType, start, end);
            results.addAll(list);
        }
        return results;
    }

    public <T extends Number> List<Result<T>> search(T value, DataType dataType, long start, long end) {
        int typeSize = dataType.getValue();
        List<Result<T>> results = new ArrayList<>();
        try (RandomAccessFile reader = getMemoryFile()) {
            byte[] data = new byte[typeSize];
            byte[] mem = new byte[(int) (end - start)];

            reader.seek(start);
            int len = reader.read(mem);

            for (int i = 0; i < len; i += typeSize) {
                System.arraycopy(mem, i, data, 0, typeSize);
                T t = DataTypeUtils.bytesToNumber(data, dataType);
                if (t != null && t.equals(value)) {
                    results.add(new Result<>(start + i, t, dataType));
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return results;
    }

    public <T extends Number> void write(long address, T value, DataType dataType) {
        try (RandomAccessFile writer = getMemoryFile()) {
            byte[] data = DataTypeUtils.numberToBytes(value, dataType);
            writer.seek(address);
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public <T extends Number> T read(long address, DataType dataType) {
        try (RandomAccessFile reader = getMemoryFile()) {
            byte[] data = new byte[dataType.getValue()];
            reader.seek(address);
            reader.read(data);
            return DataTypeUtils.bytesToNumber(data, dataType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private RandomAccessFile getMemoryFile() {
        try {
            return new RandomAccessFile(memoryPath, "rw");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int getPid() {
        return pid;
    }

    public Map<MemoryRange, List<MemoryMap>> getMaps() {
        return maps;
    }
}
