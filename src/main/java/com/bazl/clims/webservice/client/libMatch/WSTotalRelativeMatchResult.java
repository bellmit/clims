
package com.bazl.clims.webservice.client.libMatch;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>WS_TotalRelativeMatchResult complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="WS_TotalRelativeMatchResult"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CBarcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FBarcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FResult" type="{http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com}WS_CaseParentageMatchResult" minOccurs="0"/&gt;
 *         &lt;element name="MBarcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MResult" type="{http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com}WS_CaseParentageMatchResult" minOccurs="0"/&gt;
 *         &lt;element name="matchMode" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="result" type="{http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com}WS_CaseParentageMatchResult" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WS_TotalRelativeMatchResult", propOrder = {
    "cBarcode",
    "fBarcode",
    "fResult",
    "mBarcode",
    "mResult",
    "matchMode",
    "result"
})
public class WSTotalRelativeMatchResult {

    @XmlElementRef(name = "CBarcode", namespace = "http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> cBarcode;
    @XmlElementRef(name = "FBarcode", namespace = "http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> fBarcode;
    @XmlElementRef(name = "FResult", namespace = "http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<WSCaseParentageMatchResult> fResult;
    @XmlElementRef(name = "MBarcode", namespace = "http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> mBarcode;
    @XmlElementRef(name = "MResult", namespace = "http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<WSCaseParentageMatchResult> mResult;
    protected Integer matchMode;
    @XmlElementRef(name = "result", namespace = "http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<WSCaseParentageMatchResult> result;

    /**
     * 获取cBarcode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCBarcode() {
        return cBarcode;
    }

    /**
     * 设置cBarcode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCBarcode(JAXBElement<String> value) {
        this.cBarcode = value;
    }

    /**
     * 获取fBarcode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFBarcode() {
        return fBarcode;
    }

    /**
     * 设置fBarcode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFBarcode(JAXBElement<String> value) {
        this.fBarcode = value;
    }

    /**
     * 获取fResult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link WSCaseParentageMatchResult }{@code >}
     *     
     */
    public JAXBElement<WSCaseParentageMatchResult> getFResult() {
        return fResult;
    }

    /**
     * 设置fResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link WSCaseParentageMatchResult }{@code >}
     *     
     */
    public void setFResult(JAXBElement<WSCaseParentageMatchResult> value) {
        this.fResult = value;
    }

    /**
     * 获取mBarcode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMBarcode() {
        return mBarcode;
    }

    /**
     * 设置mBarcode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMBarcode(JAXBElement<String> value) {
        this.mBarcode = value;
    }

    /**
     * 获取mResult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link WSCaseParentageMatchResult }{@code >}
     *     
     */
    public JAXBElement<WSCaseParentageMatchResult> getMResult() {
        return mResult;
    }

    /**
     * 设置mResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link WSCaseParentageMatchResult }{@code >}
     *     
     */
    public void setMResult(JAXBElement<WSCaseParentageMatchResult> value) {
        this.mResult = value;
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

    /**
     * 获取result属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link WSCaseParentageMatchResult }{@code >}
     *     
     */
    public JAXBElement<WSCaseParentageMatchResult> getResult() {
        return result;
    }

    /**
     * 设置result属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link WSCaseParentageMatchResult }{@code >}
     *     
     */
    public void setResult(JAXBElement<WSCaseParentageMatchResult> value) {
        this.result = value;
    }

}
