
package com.bazl.clims.webservice.caseSampleLib;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ArrayOfSampleNoDomain complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ArrayOfSampleNoDomain"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SampleNoDomain" type="{http://casesamplelib.webservice.web.dna.todaysoft.com}SampleNoDomain" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfSampleNoDomain", propOrder = {
    "sampleNoDomain"
})
public class ArrayOfSampleNoDomain {

    @XmlElement(name = "SampleNoDomain", nillable = true)
    protected List<SampleNoDomain> sampleNoDomain;

    /**
     * Gets the value of the sampleNoDomain property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sampleNoDomain property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSampleNoDomain().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SampleNoDomain }
     * 
     * 
     */
    public List<SampleNoDomain> getSampleNoDomain() {
        if (sampleNoDomain == null) {
            sampleNoDomain = new ArrayList<SampleNoDomain>();
        }
        return this.sampleNoDomain;
    }

}
