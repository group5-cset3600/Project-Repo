/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aaa.project.model;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author sean.morris
 */
public class VM {

//    private HashMap<String, String> interfaces = new HashMap<String, String>();
    private final StringProperty type;
    private final StringProperty name;
    private final StringProperty os;
    private final StringProperty version;
    private final StringProperty source;
    private final StringProperty ethernet0;
    private final StringProperty ethernet1;
    private final StringProperty ethernet2;
    private TreeMap<String, String> interfaces = new TreeMap<String, String>();
//    private final StringProperty subnet;
//    private final StringProperty netmask;
//    private final StringProperty inf;

    /**
     * Default constructor.
     */
    public VM() {
        this(null, null);
    }

    /**
     * Constructor with some initial data.
     *
     * @param type
     * @param name
     */
    public VM(String type, String name) {
        this.type = new SimpleStringProperty(type);
        this.name = new SimpleStringProperty(name);

        // Some initial dummy data, just for convenient testing.
        //this.os = new SimpleStringProperty(os);
        this.os = new SimpleStringProperty("Linux");
        this.version = new SimpleStringProperty("some street");
        this.source = new SimpleStringProperty("/srv/VMLibrary/JeOS");
        this.ethernet0 = new SimpleStringProperty("192.168.40.1");
        this.ethernet1 = new SimpleStringProperty("192.168.30.1");
        this.ethernet2 = new SimpleStringProperty("192.168.20.1");
//        this.interfaces = new SimpleStringProperty("--");
//        this.subnet = new SimpleStringProperty("192.168.20.1");
//        this.netmask = new SimpleStringProperty("255.555.255.0");
//        this.inf = new SimpleStringProperty("-");
    }

    public String getType() {
        return type.get();
    }

    public void setType(String name) {
        this.type.set(name);
    }

    public StringProperty typeProperty() {
        return type;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getOs() {
        return os.get();
    }

    public void setOs(String os) {
        this.os.set(os);
    }

    public StringProperty getOsProperty() {
        return os;
    }

    public TreeMap<String, String> getInterfaces() {
        return this.interfaces;
    }

    public void setInterfaces(TreeMap<String, String> interfaces) {
        this.interfaces = interfaces;
    }

    public void addInterface(String key, String value) {
        this.interfaces.put(key, value);
    }

//    public String getInterfaces() {
//        // iterates through the interface hash map and prints each combination
//        // of keys and values to the command line. Ex: eth0 192.168.0.1
//        String ethoString = "";
//        System.out.println("Interface(s):");
//        for (Map.Entry<String, String> entry : interfaces.entrySet()) {
//            String key = entry.getKey();
//            String value = entry.getValue();
//            ethoString += key.toString() + ": " + value.toString() + "\n";
//            System.out.println("\t" + key + " " + value);
//        }
//        return ethoString;
//    }
//    public HashMap<String, String> getInterfaceHashMap() {
//        return interfaces;
//    }
//
//    public void setInterfaces(String key, String value) {
//        interfaces.put(key, value);
//    }
    public String getVersion() {
        return version.get();
    }

    public void setVersion(String version) {
        this.version.set(version);
    }

    public StringProperty getVersionProperty() {
        return version;
    }

    public String getSource() {
        return source.get();
    }

    public void setSource(String source) {
        this.source.set(source);
    }

    public StringProperty getSourceProperty() {
        return source;
    }

    public String getEthernet0() {
        return ethernet0.get();
    }

    public void setEthernet0(String ethernet0) {
        this.ethernet0.set(ethernet0);
    }

    public StringProperty getEthernet0Property() {
        return ethernet0;
    }

    public String getEthernet1() {
        return ethernet1.get();
    }

    public void setEthernet1(String ethernet1) {
        this.ethernet1.set(ethernet1);
    }

    public StringProperty getEthernet1Property() {
        return ethernet1;
    }

    public String getEthernet2() {
        return ethernet2.get();
    }

    public void setEthernet2(String ethernet2) {
        this.ethernet2.set(ethernet2);
    }

    public StringProperty getEthernet2Property() {
        return ethernet2;
    }

}
