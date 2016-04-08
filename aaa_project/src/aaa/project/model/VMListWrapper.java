/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aaa.project.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Helper class to wrap a list of vm's. This is used for saving the list of
 * vm's to XML.
 * 
* @author Sean Morris
 */
@XmlRootElement(name = "vm")
public class VMListWrapper {

    private List<VM> vm;

    @XmlElement(name = "vm")
    public List<VM> getVM() {
        System.out.println("VMListWrapper: getVM()");
        System.out.println("output (vm):");
        System.out.println(vm);
        return vm;
    }

    public void setVM(List<VM> vm) {
        this.vm = vm;
    }
}
