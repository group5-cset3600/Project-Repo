/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aaa.project.model;

import java.io.File;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author sean.morris
 */
public class FileParser {

    public String singleMatcher(String regex, String input) {
        // Simple matcher that uses a specified regex and if found
        // in the specified input string returns the capture group
        String output = null;
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);
        if (m.find()) {
            output = m.group(1);
        }
        return output;
    }

    public void ethMatcher(String vm, String regex, String input) {
        // Matcher that finds each instance of eth# for a specified vm.
        // Populates the specified vm's interfaces hash map
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);
        TreeMap<String, String> interfaces = new TreeMap<String, String>();
        while (true) {
            if (m.find()) {
                interfaces.put(m.group(1), m.group(2));
            } else {
                break;
            }
        }
        Data.vmMap.get(vm).setInterfaces(interfaces);
    }

    public void infMatcher(String hub, String regex, String input) {
        // hub interface matcher first finds the line in the specified hub
        // that corresponds with the inf's then iterates through each
        // inf and adds them to the hubs inf arraylist
        Pattern pline = Pattern.compile(regex);
        Matcher mline = pline.matcher(input);
        if (mline.find()) {
            Pattern innerPatt = Pattern.compile("(\\w+\\.\\w+)");
            Matcher innerMatch = innerPatt.matcher(mline.group(1));
            while (true) {
                if (innerMatch.find()) {
                    Data.hubMap.get(hub).addInf(innerMatch.group(1));
                } else {
                    break;
                }
            }
        }
    }

    public FileParser(File selectedFile) {
        String file = null;

        // if specified config file (represented as a string) is not empty
        // attempt to parse it
        if (selectedFile != null) {
            file = aaa.project.Main.readFile(selectedFile);
        }

        // parses the file for each node or vm
        Pattern p = Pattern.compile(Data.nodePattern, Pattern.DOTALL);
        Matcher m = p.matcher(file);
        while (true) {
            // loop that is valid until we run out of nodes to parse
            if (m.find()) {
                /*
                 * if the file contains for example: vm vmName { ... }
                 */
                if (m.group(1).equals("vm")) {
                    // if the node is a vm
                    // create a new vm object
                    Data.vmMap.put(m.group(2), new VM());
                    // parse and set the vm's name
                    Data.vmMap.get(m.group(2)).setName(m.group(2));
                    // parse and set the vm's os
                    Data.vmMap.get(m.group(2)).setOs(singleMatcher(Data.osPattern, m.group(3)));
                    // parse and set the vm's ver
                    //Data.vmMap.get(m.group(2)).setVersion(Double.parseDouble(singleMatcher(Data.verPattern, m.group(3))));
                    Data.vmMap.get(m.group(2)).setVersion(singleMatcher(Data.verPattern, m.group(3)));
                    // parse and set the vm's src
                    Data.vmMap.get(m.group(2)).setSource(singleMatcher(Data.srcPattern, m.group(3)));
                    // parse and set the vm's eth(s)
                    ethMatcher(m.group(2), Data.ethPattern, m.group(3));

                    // Prints out parsed data to cli for debugging purposes
                    System.out.println("Found vm:");
                    System.out.println("name\t=\t" + Data.vmMap.get(m.group(2)).getName());
                    System.out.println("os\t=\t" + Data.vmMap.get(m.group(2)).getOs());
                    System.out.println("ver\t=\t" + Data.vmMap.get(m.group(2)).getVersion());
                    System.out.println("src\t=\t" + Data.vmMap.get(m.group(2)).getSource());
//                    for (Map.Entry<String, String> entry : Data.vmMap.get(m.group(2)).getInterfaces().entrySet()) {
                    for (Map.Entry<String, String> entry : Data.vmMap.get(m.group(2)).getInterfaces().entrySet()) {
//                        System.out.println(entry.getKey() + "\t=\t" + entry.getKey());
                        System.out.println(entry.getKey() + "\t=\t" + entry.getValue());
                    }
                    Data.vmMap.get(m.group(2)).getInterfaces();
                    System.out.println("---------------------------------");
                } else if (m.group(1).equals("hub")) {
                    // if the node is a hub
                    // create a new hub object
                    Data.hubMap.put(m.group(2), new HUB());
                    // parse and set the hubs name
                    Data.hubMap.get(m.group(2)).setName(m.group(2));
                    // parse and set the hubs subnet
                    Data.hubMap.get(m.group(2)).setSubnet(singleMatcher(Data.subnetPattern, m.group(3)));
                    // parse and set the hubs netmask
                    Data.hubMap.get(m.group(2)).setNetmask(singleMatcher(Data.netmaskPattern, m.group(3)));
                    // parse and set the hubs inf(s)
                    infMatcher(m.group(2), Data.infPattern, m.group(3));

                    // Prints out parsed data to cli for debugging purposes
                    System.out.println("Found hub:");
                    System.out.println("name\t=\t" + Data.hubMap.get(m.group(2)).getName());
                    System.out.println("subnet\t=\t" + Data.hubMap.get(m.group(2)).getSubnet());
                    System.out.println("netmask\t=\t" + Data.hubMap.get(m.group(2)).getNetmask());
                    System.out.println("inf(s)\t=\t");
                    for (String inf : Data.hubMap.get(m.group(2)).getInfs()) {
                        System.out.println("\t\t" + inf);
                    }
                    System.out.println("---------------------------------");
                }
            } else {
                // we ran out of things to parse to break out of the loop
                break;
            }
        }

    }
}
