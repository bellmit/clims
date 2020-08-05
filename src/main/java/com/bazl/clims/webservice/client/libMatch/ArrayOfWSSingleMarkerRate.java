
package com.bazl.clims.webservice.client.libMatch;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ArrayOfWS_SingleMarkerRate complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ArrayOfWS_SingleMarkerRate"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="WS_SingleMarkerRate" type="{http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com}WS_SingleMarkerRate" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfWS_SingleMarkerRate", propOrder = {
    "wsSingleMarkerRate"
})
public class ArrayOfWSSingleMarkerRate {

    @XmlElement(name = "WS_SingleMarkerRate", nillable = true)
    protected List<WSSingleMarkerRate> wsSingleMarkerRate;

    /**
     * Gets the value of the wsSingleMarkerRate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wsSingleMarkerRate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWSSingleMarkerRate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WSSingleMarkerRate }
     * 
     * 
     */
    public List<WSSingleMarkerRate> getWSSingleMarkerRate() {
        if (wsSingleMarkerRate == null) {
            wsSingleMarkerRate = new ArrayList<WSSingleMarkerRate>();
        }
        return this.wsSingleMarkerRate;
    }

}
