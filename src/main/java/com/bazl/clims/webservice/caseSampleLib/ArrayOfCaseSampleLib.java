
package com.bazl.clims.webservice.caseSampleLib;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ArrayOfCaseSampleLib complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ArrayOfCaseSampleLib"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CaseSampleLib" type="{http://casesamplelib.webservice.web.dna.todaysoft.com}CaseSampleLib" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfCaseSampleLib", propOrder = {
    "caseSampleLib"
})
public class ArrayOfCaseSampleLib {

    @XmlElement(name = "CaseSampleLib", nillable = true)
    protected List<CaseSampleLib> caseSampleLib;

    /**
     * Gets the value of the caseSampleLib property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the caseSampleLib property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCaseSampleLib().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CaseSampleLib }
     * 
     * 
     */
    public List<CaseSampleLib> getCaseSampleLib() {
        if (caseSampleLib == null) {
            caseSampleLib = new ArrayList<CaseSampleLib>();
        }
        return this.caseSampleLib;
    }

}
