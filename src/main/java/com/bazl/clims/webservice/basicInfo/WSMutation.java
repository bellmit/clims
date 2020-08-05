
package com.bazl.clims.webservice.basicInfo;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>WS_Mutation complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="WS_Mutation"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="muationName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="mutationId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WS_Mutation", propOrder = {
    "muationName",
    "mutationId"
})
public class WSMutation {

    @XmlElementRef(name = "muationName", namespace = "http://BasicInfo.webservice.web.dna.todaysoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> muationName;
    @XmlElementRef(name = "mutationId", namespace = "http://BasicInfo.webservice.web.dna.todaysoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> mutationId;

    /**
     * 获取muationName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMuationName() {
        return muationName;
    }

    /**
     * 设置muationName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMuationName(JAXBElement<String> value) {
        this.muationName = value;
    }

    /**
     * 获取mutationId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMutationId() {
        return mutationId;
    }

    /**
     * 设置mutationId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMutationId(JAXBElement<String> value) {
        this.mutationId = value;
    }

}
