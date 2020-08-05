
package com.bazl.clims.webservice.client.libMatch;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>ArrayOfWS_LibMatchResult complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ArrayOfWS_LibMatchResult"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="WS_LibMatchResult" type="{http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com}WS_LibMatchResult" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfWS_LibMatchResult", propOrder = {
    "wsLibMatchResult"
})
public class ArrayOfWSLibMatchResult {

    @XmlElement(name = "WS_LibMatchResult", nillable = true)
    protected List<WSLibMatchResult> wsLibMatchResult;

    /**
     * Gets the value of the wsLibMatchResult property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wsLibMatchResult property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWSLibMatchResult().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WSLibMatchResult }
     * 
     * 
     */
    public List<WSLibMatchResult> getWSLibMatchResult() {
        if (wsLibMatchResult == null) {
            wsLibMatchResult = new ArrayList<WSLibMatchResult>();
        }
        return this.wsLibMatchResult;
    }

}
