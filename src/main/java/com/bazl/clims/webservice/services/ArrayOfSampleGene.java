
package com.bazl.clims.webservice.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>ArrayOfSampleGene complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ArrayOfSampleGene"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SampleGene" type="{http://domain.lims.viathink.com}SampleGene" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfSampleGene", namespace = "http://domain.lims.viathink.com", propOrder = {
    "sampleGene"
})
public class ArrayOfSampleGene {

    @XmlElement(name = "SampleGene", nillable = true)
    protected List<SampleGene> sampleGene;

    /**
     * Gets the value of the sampleGene property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sampleGene property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSampleGene().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SampleGene }
     * 
     * 
     */
    public List<SampleGene> getSampleGene() {
        if (sampleGene == null) {
            sampleGene = new ArrayList<SampleGene>();
        }
        return this.sampleGene;
    }

}
