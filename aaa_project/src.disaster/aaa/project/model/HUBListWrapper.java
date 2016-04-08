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
 * Helper class to wrap a list of persons. This is used for saving the list of
 * persons to XML.
 * 
* @author Marco Jakob
 */
@XmlRootElement(name = "hub")
public class HUBListWrapper {

    private List<HUB> hub;

    @XmlElement(name = "hub")
    public List<HUB> getHUB() {
        return hub;
    }

    public void setHUB(List<HUB> hub) {
        this.hub = hub;
    }
}
