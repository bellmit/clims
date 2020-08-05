
package com.bazl.clims.webservice.services;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>DnaMatchInfo complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="DnaMatchInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="sourceBarcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sourceCaseBaseId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sourceCaseHandleType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sourceCaseId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sourceSampleType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="targetBarcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="targetCaseBaseId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="targetCaseHandleType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="targetCaseId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="targetSampleType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DnaMatchInfo", namespace = "http://domain.lims.viathink.com", propOrder = {
    "sourceBarcode",
    "sourceCaseBaseId",
    "sourceCaseHandleType",
    "sourceCaseId",
    "sourceSampleType",
    "targetBarcode",
    "targetCaseBaseId",
    "targetCaseHandleType",
    "targetCaseId",
    "targetSampleType"
})
public class DnaMatchInfo {

    @XmlElementRef(name = "sourceBarcode", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> sourceBarcode;
    @XmlElementRef(name = "sourceCaseBaseId", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> sourceCaseBaseId;
    @XmlElementRef(name = "sourceCaseHandleType", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> sourceCaseHandleType;
    @XmlElementRef(name = "sourceCaseId", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> sourceCaseId;
    @XmlElementRef(name = "sourceSampleType", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> sourceSampleType;
    @XmlElementRef(name = "targetBarcode", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> targetBarcode;
    @XmlElementRef(name = "targetCaseBaseId", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> targetCaseBaseId;
    @XmlElementRef(name = "targetCaseHandleType", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> targetCaseHandleType;
    @XmlElementRef(name = "targetCaseId", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> targetCaseId;
    @XmlElementRef(name = "targetSampleType", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> targetSampleType;

    /**
     * 获取sourceBarcode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSourceBarcode() {
        return sourceBarcode;
    }

    /**
     * 设置sourceBarcode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSourceBarcode(JAXBElement<String> value) {
        this.sourceBarcode = value;
    }

    /**
     * 获取sourceCaseBaseId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSourceCaseBaseId() {
        return sourceCaseBaseId;
    }

    /**
     * 设置sourceCaseBaseId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSourceCaseBaseId(JAXBElement<String> value) {
        this.sourceCaseBaseId = value;
    }

    /**
     * 获取sourceCaseHandleType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSourceCaseHandleType() {
        return sourceCaseHandleType;
    }

    /**
     * 设置sourceCaseHandleType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSourceCaseHandleType(JAXBElement<String> value) {
        this.sourceCaseHandleType = value;
    }

    /**
     * 获取sourceCaseId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSourceCaseId() {
        return sourceCaseId;
    }

    /**
     * 设置sourceCaseId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSourceCaseId(JAXBElement<String> value) {
        this.sourceCaseId = value;
    }

    /**
     * 获取sourceSampleType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSourceSampleType() {
        return sourceSampleType;
    }

    /**
     * 设置sourceSampleType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSourceSampleType(JAXBElement<String> value) {
        this.sourceSampleType = value;
    }

    /**
     * 获取targetBarcode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTargetBarcode() {
        return targetBarcode;
    }

    /**
     * 设置targetBarcode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTargetBarcode(JAXBElement<String> value) {
        this.targetBarcode = value;
    }

    /**
     * 获取targetCaseBaseId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTargetCaseBaseId() {
        return targetCaseBaseId;
    }

    /**
     * 设置targetCaseBaseId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTargetCaseBaseId(JAXBElement<String> value) {
        this.targetCaseBaseId = value;
    }

    /**
     * 获取targetCaseHandleType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTargetCaseHandleType() {
        return targetCaseHandleType;
    }

    /**
     * 设置targetCaseHandleType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTargetCaseHandleType(JAXBElement<String> value) {
        this.targetCaseHandleType = value;
    }

    /**
     * 获取targetCaseId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTargetCaseId() {
        return targetCaseId;
    }

    /**
     * 设置targetCaseId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTargetCaseId(JAXBElement<String> value) {
        this.targetCaseId = value;
    }

    /**
     * 获取targetSampleType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTargetSampleType() {
        return targetSampleType;
    }

    /**
     * 设置targetSampleType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTargetSampleType(JAXBElement<String> value) {
        this.targetSampleType = value;
    }

}
