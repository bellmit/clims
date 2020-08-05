
package com.bazl.clims.webservice.client.libMatch;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>WS_LibMatchResultAndTotalCount complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="WS_LibMatchResultAndTotalCount"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="libMatchResults" type="{http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com}ArrayOfWS_LibMatchResult" minOccurs="0"/&gt;
 *         &lt;element name="totalMatchResultCount" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WS_LibMatchResultAndTotalCount", propOrder = {
    "libMatchResults",
    "totalMatchResultCount"
})
public class WSLibMatchResultAndTotalCount {

    @XmlElementRef(name = "libMatchResults", namespace = "http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfWSLibMatchResult> libMatchResults;
    protected Integer totalMatchResultCount;

    /**
     * 获取libMatchResults属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfWSLibMatchResult }{@code >}
     *     
     */
    public JAXBElement<ArrayOfWSLibMatchResult> getLibMatchResults() {
        return libMatchResults;
    }

    /**
     * 设置libMatchResults属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfWSLibMatchResult }{@code >}
     *     
     */
    public void setLibMatchResults(JAXBElement<ArrayOfWSLibMatchResult> value) {
        this.libMatchResults = value;
    }

    /**
     * 获取totalMatchResultCount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTotalMatchResultCount() {
        return totalMatchResultCount;
    }

    /**
     * 设置totalMatchResultCount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTotalMatchResultCount(Integer value) {
        this.totalMatchResultCount = value;
    }

}
