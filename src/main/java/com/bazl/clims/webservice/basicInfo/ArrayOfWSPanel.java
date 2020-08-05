
package com.bazl.clims.webservice.basicInfo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ArrayOfWS_Panel complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ArrayOfWS_Panel"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="WS_Panel" type="{http://BasicInfo.webservice.web.dna.todaysoft.com}WS_Panel" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfWS_Panel", propOrder = {
    "wsPanel"
})
public class ArrayOfWSPanel {

    @XmlElement(name = "WS_Panel", nillable = true)
    protected List<WSPanel> wsPanel;

    /**
     * Gets the value of the wsPanel property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wsPanel property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWSPanel().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WSPanel }
     * 
     * 
     */
    public List<WSPanel> getWSPanel() {
        if (wsPanel == null) {
            wsPanel = new ArrayList<WSPanel>();
        }
        return this.wsPanel;
    }

}
