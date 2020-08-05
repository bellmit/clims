
package com.bazl.clims.webservice.basicInfo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>ArrayOfWS_SampleType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ArrayOfWS_SampleType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="WS_SampleType" type="{http://BasicInfo.webservice.web.dna.todaysoft.com}WS_SampleType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfWS_SampleType", propOrder = {
    "wsSampleType"
})
public class ArrayOfWSSampleType {

    @XmlElement(name = "WS_SampleType", nillable = true)
    protected List<WSSampleType> wsSampleType;

    /**
     * Gets the value of the wsSampleType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wsSampleType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWSSampleType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WSSampleType }
     * 
     * 
     */
    public List<WSSampleType> getWSSampleType() {
        if (wsSampleType == null) {
            wsSampleType = new ArrayList<WSSampleType>();
        }
        return this.wsSampleType;
    }

}
