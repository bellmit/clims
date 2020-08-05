
package com.bazl.clims.webservice.client.libMatch;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ArrayOfWS_AdditionOption complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ArrayOfWS_AdditionOption"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="WS_AdditionOption" type="{http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com}WS_AdditionOption" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfWS_AdditionOption", propOrder = {
    "wsAdditionOption"
})
public class ArrayOfWSAdditionOption {

    @XmlElement(name = "WS_AdditionOption", nillable = true)
    protected List<WSAdditionOption> wsAdditionOption;

    /**
     * Gets the value of the wsAdditionOption property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wsAdditionOption property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWSAdditionOption().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WSAdditionOption }
     * 
     * 
     */
    public List<WSAdditionOption> getWSAdditionOption() {
        if (wsAdditionOption == null) {
            wsAdditionOption = new ArrayList<WSAdditionOption>();
        }
        return this.wsAdditionOption;
    }

}
