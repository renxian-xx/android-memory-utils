package andrioid.memory.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Test {

    public static <T> List<T> copy(List<T> list, int[] indexes) {
        List<T> result = new ArrayList<>();
        for (int i : indexes) {
            result.add(list.get(i));
        }
        return result;
    }

    public static void main(String[] args) {
        try {
            MemoryOperator operator = new MemoryOperator("org.yjmobile.zmxy");
            Map<MemoryRange, List<MemoryMap>> maps = operator.getMaps();
            List<MemoryMap> ca = maps.get(MemoryRange.Ca);
//            operator.write(0xb9000000L, 1, DataType.DWord);
            maps.put(MemoryRange.Ca, copy(ca, new int[]{1}));
            System.out.println(operator.search(1000, DataType.DWord, MemoryRange.Ca).size());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
