package andrioid.memory.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MapsUtils {

    public static List<MemoryMap> getMemoryMaps(String path) {
        List<MemoryMap> memoryMaps = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                memoryMaps.add(parseMemoryMap(line));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return memoryMaps;
    }


    private static MemoryMap parseMemoryMap(String line) {
        String[] split = line.split("\\s+");
        MemoryMap memoryMap = new MemoryMap();
        String[] address = split[0].split("-");
        memoryMap.setStart(Long.parseLong(address[0], 16));
        memoryMap.setEnd(Long.parseLong(address[1], 16));
        memoryMap.setChmod(split[1]);
        memoryMap.setOffset(Long.parseLong(split[2], 16));
        memoryMap.setDev(split[3]);
        memoryMap.setInode(split[4]);
        if (split.length > 5)
            memoryMap.setPathname(split[5]);
        return memoryMap;
    }
}
