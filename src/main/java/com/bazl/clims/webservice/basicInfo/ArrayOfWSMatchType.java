
package com.bazl.clims.webservice.basicInfo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>ArrayOfWS_MatchType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ArrayOfWS_MatchType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="WS_MatchType" type="{http://BasicInfo.webservice.web.dna.todaysoft.com}WS_MatchType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfWS_MatchType", propOrder = {
    "wsMatchType"
})
public class ArrayOfWSMatchType {

    @XmlElement(name = "WS_MatchType", nillable = true)
    protected List<WSMatchType> wsMatchType;

    /**
     * Gets the value of the wsMatchType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wsMatchType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWSMatchType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WSMatchType }
     * 
     * 
     */
    public List<WSMatchType> getWSMatchType() {
        if (wsMatchType == null) {
            wsMatchType = new ArrayList<WSMatchType>();
        }
        return this.wsMatchType;
    }

}
