/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aaa.project.model;

import java.util.HashMap;
import java.util.Map;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author sean.morris
 */
public class HUB {

    private HashMap<String, String> interfaces = new HashMap<String, String>();

    private final StringProperty type;
    private final StringProperty name;
    private final StringProperty os;
    private final StringProperty version;
    private final StringProperty source;
    private final StringProperty ethernet0;
    private final StringProperty ethernet1;
    private final StringProperty ethernet2;
    private final StringProperty subnet;
    private final StringProperty netmask;
    private final StringProperty inf;

    /**
     * Default constructor.
     */
    public HUB() {
        this(null, null);
    }

    /**
     * Constructor with some initial data.
     *
     * @param firstName
     * @param lastName
     */
    public HUB(String type, String name) {
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
        this.subnet = new SimpleStringProperty("192.168.20.1");
        this.netmask = new SimpleStringProperty("255.555.255.0");
        this.inf = new SimpleStringProperty("-");
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

    public String getInterfaces() {
        // iterates through the interface hash map and prints each combination
        // of keys and values to the command line. Ex: eth0 192.168.0.1
        String ethoString = "";
        System.out.println("Interface(s):");
        for (Map.Entry<String, String> entry : interfaces.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            ethoString += key.toString() + ": " + value.toString() + "\n";
            System.out.println("\t" + key + " " + value);
        }
        return ethoString;
    }

    public HashMap<String, String> getInterfaceHashMap() {
        return interfaces;
    }

    public void setInterfaces(String key, String value) {
        interfaces.put(key, value);
    }

    public String getSubnet() {
        return subnet.get();
    }

    public void setSubnet(String ethernet2) {
        this.subnet.set(ethernet2);
    }

    public StringProperty getSubnetProperty() {
        return subnet;
    }

    public String getNetmask() {
        return netmask.get();
    }

    public void setNetmask(String netmask) {
        this.netmask.set(netmask);
    }

    public StringProperty getNetmaskProperty() {
        return netmask;
    }

    public String getInf() {
        return inf.get();
    }

    public void setInf(String inf) {
        this.inf.set(inf);
    }

    public StringProperty getInfProperty() {
        return inf;
    }

}
