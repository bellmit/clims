
package com.bazl.clims.webservice.services;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>MatchSampleInfo complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="MatchSampleInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="barcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="caseDigest" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="caseHandleType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="caseId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="caseName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="caseOldSn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="caseSn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="delegateOrg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="isDelete" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="matchResultInfoId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="remarks" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sampleName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sampletype" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MatchSampleInfo", namespace = "http://domain.lims.viathink.com", propOrder = {
    "barcode",
    "caseDigest",
    "caseHandleType",
    "caseId",
    "caseName",
    "caseOldSn",
    "caseSn",
    "delegateOrg",
    "id",
    "isDelete",
    "matchResultInfoId",
    "remarks",
    "sampleName",
    "sampletype"
})
public class MatchSampleInfo {

    @XmlElementRef(name = "barcode", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> barcode;
    @XmlElementRef(name = "caseDigest", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> caseDigest;
    @XmlElementRef(name = "caseHandleType", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> caseHandleType;
    @XmlElementRef(name = "caseId", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> caseId;
    @XmlElementRef(name = "caseName", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> caseName;
    @XmlElementRef(name = "caseOldSn", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> caseOldSn;
    @XmlElementRef(name = "caseSn", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> caseSn;
    @XmlElementRef(name = "delegateOrg", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> delegateOrg;
    @XmlElementRef(name = "id", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> id;
    @XmlElementRef(name = "isDelete", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> isDelete;
    @XmlElementRef(name = "matchResultInfoId", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> matchResultInfoId;
    @XmlElementRef(name = "remarks", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> remarks;
    @XmlElementRef(name = "sampleName", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> sampleName;
    @XmlElementRef(name = "sampletype", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> sampletype;

    /**
     * 获取barcode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getBarcode() {
        return barcode;
    }

    /**
     * 设置barcode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setBarcode(JAXBElement<String> value) {
        this.barcode = value;
    }

    /**
     * 获取caseDigest属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCaseDigest() {
        return caseDigest;
    }

    /**
     * 设置caseDigest属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCaseDigest(JAXBElement<String> value) {
        this.caseDigest = value;
    }

    /**
     * 获取caseHandleType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCaseHandleType() {
        return caseHandleType;
    }

    /**
     * 设置caseHandleType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCaseHandleType(JAXBElement<String> value) {
        this.caseHandleType = value;
    }

    /**
     * 获取caseId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCaseId() {
        return caseId;
    }

    /**
     * 设置caseId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCaseId(JAXBElement<String> value) {
        this.caseId = value;
    }

    /**
     * 获取caseName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCaseName() {
        return caseName;
    }

    /**
     * 设置caseName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCaseName(JAXBElement<String> value) {
        this.caseName = value;
    }

    /**
     * 获取caseOldSn属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCaseOldSn() {
        return caseOldSn;
    }

    /**
     * 设置caseOldSn属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCaseOldSn(JAXBElement<String> value) {
        this.caseOldSn = value;
    }

    /**
     * 获取caseSn属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCaseSn() {
        return caseSn;
    }

    /**
     * 设置caseSn属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCaseSn(JAXBElement<String> value) {
        this.caseSn = value;
    }

    /**
     * 获取delegateOrg属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDelegateOrg() {
        return delegateOrg;
    }

    /**
     * 设置delegateOrg属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDelegateOrg(JAXBElement<String> value) {
        this.delegateOrg = value;
    }

    /**
     * 获取id属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getId() {
        return id;
    }

    /**
     * 设置id属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setId(JAXBElement<String> value) {
        this.id = value;
    }

    /**
     * 获取isDelete属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIsDelete() {
        return isDelete;
    }

    /**
     * 设置isDelete属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIsDelete(JAXBElement<String> value) {
        this.isDelete = value;
    }

    /**
     * 获取matchResultInfoId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMatchResultInfoId() {
        return matchResultInfoId;
    }

    /**
     * 设置matchResultInfoId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMatchResultInfoId(JAXBElement<String> value) {
        this.matchResultInfoId = value;
    }

    /**
     * 获取remarks属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRemarks() {
        return remarks;
    }

    /**
     * 设置remarks属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRemarks(JAXBElement<String> value) {
        this.remarks = value;
    }

    /**
     * 获取sampleName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSampleName() {
        return sampleName;
    }

    /**
     * 设置sampleName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSampleName(JAXBElement<String> value) {
        this.sampleName = value;
    }

    /**
     * 获取sampletype属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSampletype() {
        return sampletype;
    }

    /**
     * 设置sampletype属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSampletype(JAXBElement<String> value) {
        this.sampletype = value;
    }

}
