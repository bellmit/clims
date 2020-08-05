
package com.bazl.clims.webservice.basicInfo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ArrayOfWS_ResultProcessState complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ArrayOfWS_ResultProcessState"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="WS_ResultProcessState" type="{http://BasicInfo.webservice.web.dna.todaysoft.com}WS_ResultProcessState" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfWS_ResultProcessState", propOrder = {
    "wsResultProcessState"
})
public class ArrayOfWSResultProcessState {

    @XmlElement(name = "WS_ResultProcessState", nillable = true)
    protected List<WSResultProcessState> wsResultProcessState;

    /**
     * Gets the value of the wsResultProcessState property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wsResultProcessState property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWSResultProcessState().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WSResultProcessState }
     * 
     * 
     */
    public List<WSResultProcessState> getWSResultProcessState() {
        if (wsResultProcessState == null) {
            wsResultProcessState = new ArrayList<WSResultProcessState>();
        }
        return this.wsResultProcessState;
    }

}
