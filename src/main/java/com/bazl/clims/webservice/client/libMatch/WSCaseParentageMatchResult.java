
package com.bazl.clims.webservice.client.libMatch;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>WS_CaseParentageMatchResult complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="WS_CaseParentageMatchResult"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="isSuccessful" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="matchCount" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="singleMarkers" type="{http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com}ArrayOfWS_SingleMarkerRate" minOccurs="0"/&gt;
 *         &lt;element name="totalPossibility" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WS_CaseParentageMatchResult", propOrder = {
    "isSuccessful",
    "matchCount",
    "singleMarkers",
    "totalPossibility"
})
public class WSCaseParentageMatchResult {

    protected Integer isSuccessful;
    protected Integer matchCount;
    @XmlElementRef(name = "singleMarkers", namespace = "http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfWSSingleMarkerRate> singleMarkers;
    protected Double totalPossibility;

    /**
     * 获取isSuccessful属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIsSuccessful() {
        return isSuccessful;
    }

    /**
     * 设置isSuccessful属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIsSuccessful(Integer value) {
        this.isSuccessful = value;
    }

    /**
     * 获取matchCount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMatchCount() {
        return matchCount;
    }

    /**
     * 设置matchCount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMatchCount(Integer value) {
        this.matchCount = value;
    }

    /**
     * 获取singleMarkers属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfWSSingleMarkerRate }{@code >}
     *     
     */
    public JAXBElement<ArrayOfWSSingleMarkerRate> getSingleMarkers() {
        return singleMarkers;
    }

    /**
     * 设置singleMarkers属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfWSSingleMarkerRate }{@code >}
     *     
     */
    public void setSingleMarkers(JAXBElement<ArrayOfWSSingleMarkerRate> value) {
        this.singleMarkers = value;
    }

    /**
     * 获取totalPossibility属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTotalPossibility() {
        return totalPossibility;
    }

    /**
     * 设置totalPossibility属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTotalPossibility(Double value) {
        this.totalPossibility = value;
    }

}
