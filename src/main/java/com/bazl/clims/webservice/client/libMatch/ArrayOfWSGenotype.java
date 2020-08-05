
package com.bazl.clims.webservice.client.libMatch;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ArrayOfWS_Genotype complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ArrayOfWS_Genotype"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="WS_Genotype" type="{http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com}WS_Genotype" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfWS_Genotype", propOrder = {
    "wsGenotype"
})
public class ArrayOfWSGenotype {

    @XmlElement(name = "WS_Genotype", nillable = true)
    protected List<WSGenotype> wsGenotype;

    /**
     * Gets the value of the wsGenotype property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wsGenotype property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWSGenotype().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WSGenotype }
     * 
     * 
     */
    public List<WSGenotype> getWSGenotype() {
        if (wsGenotype == null) {
            wsGenotype = new ArrayList<WSGenotype>();
        }
        return this.wsGenotype;
    }

}
