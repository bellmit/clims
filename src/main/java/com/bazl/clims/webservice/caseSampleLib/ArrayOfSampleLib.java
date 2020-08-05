
package com.bazl.clims.webservice.caseSampleLib;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ArrayOfSampleLib complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ArrayOfSampleLib"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SampleLib" type="{http://casesamplelib.webservice.web.dna.todaysoft.com}SampleLib" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfSampleLib", propOrder = {
    "sampleLib"
})
public class ArrayOfSampleLib {

    @XmlElement(name = "SampleLib", nillable = true)
    protected List<SampleLib> sampleLib;

    /**
     * Gets the value of the sampleLib property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sampleLib property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSampleLib().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SampleLib }
     * 
     * 
     */
    public List<SampleLib> getSampleLib() {
        if (sampleLib == null) {
            sampleLib = new ArrayList<SampleLib>();
        }
        return this.sampleLib;
    }

}
