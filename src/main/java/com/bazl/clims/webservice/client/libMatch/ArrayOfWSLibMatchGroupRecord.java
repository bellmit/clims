
package com.bazl.clims.webservice.client.libMatch;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ArrayOfWS_LibMatchGroupRecord complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ArrayOfWS_LibMatchGroupRecord"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="WS_LibMatchGroupRecord" type="{http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com}WS_LibMatchGroupRecord" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfWS_LibMatchGroupRecord", propOrder = {
    "wsLibMatchGroupRecord"
})
public class ArrayOfWSLibMatchGroupRecord {

    @XmlElement(name = "WS_LibMatchGroupRecord", nillable = true)
    protected List<WSLibMatchGroupRecord> wsLibMatchGroupRecord;

    /**
     * Gets the value of the wsLibMatchGroupRecord property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wsLibMatchGroupRecord property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWSLibMatchGroupRecord().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WSLibMatchGroupRecord }
     * 
     * 
     */
    public List<WSLibMatchGroupRecord> getWSLibMatchGroupRecord() {
        if (wsLibMatchGroupRecord == null) {
            wsLibMatchGroupRecord = new ArrayList<WSLibMatchGroupRecord>();
        }
        return this.wsLibMatchGroupRecord;
    }

}
