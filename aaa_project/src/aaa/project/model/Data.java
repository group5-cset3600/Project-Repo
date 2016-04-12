package aaa.project.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class Data {

    // Regex used to parse various patterns of the config file
    static final String nodePattern = "(\\w+)\\s(\\w+)\\s\\{(?<=\\{)(.*?)(?=\\})";
    static final String osPattern = "os\\s?:\\s?(\\w+)";
    static final String verPattern = "ver\\s?:\\s?\"(\\d+(?:\\.?)\\d+)\"";
    static final String srcPattern = "src\\s?:\\s?\"((?:\\/\\w+)+)\"";//src\s?:\s?\"((?:\/\w+)+)\"
    static final String ethPattern = "(eth\\d)\\s?:\\s?\"((?:\\d{0,3}\\.?)+)\""; //(eth\d)\s?:\s?\"((?:\d{0,3}\.?)+)\"
    static final String subnetPattern = "subnet\\s?:\\s?\"((?:\\d{0,3}\\.?)+)\"";
    static final String netmaskPattern = "netmask\\s?:\\s?\"((?:\\d{0,3}\\.?)+)\"";
    static final String infPattern = "inf\\s?:\\s?(.*)";

    //Coordinates for node positions and size
    public static int nodeLength = 100;
    public static int nodeWidth = 100;
    public static int hubPosX = 50;
    public static int hubPosY = 50;
    public static int vmPosX = 200;
    public static int vmPosY = 50;
    // Hash maps containing vm's and hubs
    public static LinkedHashMap<String, VM> vmMap = new LinkedHashMap<String, VM>();
    public static LinkedHashMap<String, HUB> hubMap = new LinkedHashMap<String, HUB>();

    /*public static LinkedHashMap<String, VM> replaceVMKey(LinkedHashMap<String, VM> map, String oldKey, String newKey) {
        LinkedHashMap<String, VM> temp = map;
        LinkedHashMap<String, VM> newMap = new LinkedHashMap<String, VM>();
        VM oldValue = temp.get(oldKey);
        for (Map.Entry<String, VM> entry : temp.entrySet()) {
            if (entry.getKey().equals(oldKey)) {
                newMap.put(newKey, oldValue);
            } else {
                newMap.put(entry.getKey(), entry.getValue());
            }
        }
        return newMap;
    }

    public static LinkedHashMap<String, HUB> replaceHUBKey(LinkedHashMap<String, HUB> map, String oldKey, String newKey) {
        LinkedHashMap<String, HUB> temp = map;
        LinkedHashMap<String, HUB> newMap = new LinkedHashMap<String, HUB>();
        HUB oldValue = temp.get(oldKey);
        for (Map.Entry<String, HUB> entry : temp.entrySet()) {
            if (entry.getKey().equals(oldKey)) {
                newMap.put(newKey, oldValue);
            } else {
                newMap.put(entry.getKey(), entry.getValue());
            }
        }
        return newMap;
    }*/
}
