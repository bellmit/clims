
package com.bazl.clims.webservice.client.libMatch;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>WS_SingleMarkerRate complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="WS_SingleMarkerRate"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="markerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="markerRate" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="matchMode" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WS_SingleMarkerRate", propOrder = {
    "markerName",
    "markerRate",
    "matchMode"
})
public class WSSingleMarkerRate {

    @XmlElementRef(name = "markerName", namespace = "http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> markerName;
    protected Double markerRate;
    protected Integer matchMode;

    /**
     * 获取markerName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMarkerName() {
        return markerName;
    }

    /**
     * 设置markerName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMarkerName(JAXBElement<String> value) {
        this.markerName = value;
    }

    /**
     * 获取markerRate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMarkerRate() {
        return markerRate;
    }

    /**
     * 设置markerRate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMarkerRate(Double value) {
        this.markerRate = value;
    }

    /**
     * 获取matchMode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMatchMode() {
        return matchMode;
    }

    /**
     * 设置matchMode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMatchMode(Integer value) {
        this.matchMode = value;
    }

}
