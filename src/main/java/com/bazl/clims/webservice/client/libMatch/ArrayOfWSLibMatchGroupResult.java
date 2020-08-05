
package com.bazl.clims.webservice.client.libMatch;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ArrayOfWS_LibMatchGroupResult complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ArrayOfWS_LibMatchGroupResult"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="WS_LibMatchGroupResult" type="{http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com}WS_LibMatchGroupResult" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfWS_LibMatchGroupResult", propOrder = {
    "wsLibMatchGroupResult"
})
public class ArrayOfWSLibMatchGroupResult {

    @XmlElement(name = "WS_LibMatchGroupResult", nillable = true)
    protected List<WSLibMatchGroupResult> wsLibMatchGroupResult;

    /**
     * Gets the value of the wsLibMatchGroupResult property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wsLibMatchGroupResult property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWSLibMatchGroupResult().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WSLibMatchGroupResult }
     * 
     * 
     */
    public List<WSLibMatchGroupResult> getWSLibMatchGroupResult() {
        if (wsLibMatchGroupResult == null) {
            wsLibMatchGroupResult = new ArrayList<WSLibMatchGroupResult>();
        }
        return this.wsLibMatchGroupResult;
    }

}
