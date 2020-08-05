
package com.bazl.clims.webservice.basicInfo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ArrayOfWS_Mutation complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ArrayOfWS_Mutation"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="WS_Mutation" type="{http://BasicInfo.webservice.web.dna.todaysoft.com}WS_Mutation" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfWS_Mutation", propOrder = {
    "wsMutation"
})
public class ArrayOfWSMutation {

    @XmlElement(name = "WS_Mutation", nillable = true)
    protected List<WSMutation> wsMutation;

    /**
     * Gets the value of the wsMutation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wsMutation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWSMutation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WSMutation }
     * 
     * 
     */
    public List<WSMutation> getWSMutation() {
        if (wsMutation == null) {
            wsMutation = new ArrayList<WSMutation>();
        }
        return this.wsMutation;
    }

}
