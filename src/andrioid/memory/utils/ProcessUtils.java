package andrioid.memory.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ProcessUtils {

    public static int getPid(String packageName) {
        try {
            Process process = Runtime.getRuntime().exec("ps");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains(packageName)) {
                    return Integer.parseInt(line.split("\\s+")[1]);
                }
            }
            process.destroy();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}
