
package com.bazl.clims.webservice.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>ArrayOfMatchResultDna complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ArrayOfMatchResultDna"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="MatchResultDna" type="{http://domain.lims.viathink.com}MatchResultDna" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfMatchResultDna", namespace = "http://domain.lims.viathink.com", propOrder = {
    "matchResultDna"
})
public class ArrayOfMatchResultDna {

    @XmlElement(name = "MatchResultDna", nillable = true)
    protected List<MatchResultDna> matchResultDna;

    /**
     * Gets the value of the matchResultDna property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the matchResultDna property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMatchResultDna().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MatchResultDna }
     * 
     * 
     */
    public List<MatchResultDna> getMatchResultDna() {
        if (matchResultDna == null) {
            matchResultDna = new ArrayList<MatchResultDna>();
        }
        return this.matchResultDna;
    }

}
