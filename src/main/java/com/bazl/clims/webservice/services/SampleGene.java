
package com.bazl.clims.webservice.services;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>SampleGene complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="SampleGene"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="auditStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="auditor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="case_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="gene_type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="imagePath" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="is_mix" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="markerAllelesElements" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="panel_name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sampleAppriase" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sample_barcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sample_bord_barcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="update_at" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="well" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SampleGene", namespace = "http://domain.lims.viathink.com", propOrder = {
    "auditStatus",
    "auditor",
    "caseId",
    "geneType",
    "id",
    "imagePath",
    "isMix",
    "markerAllelesElements",
    "panelName",
    "sampleAppriase",
    "sampleBarcode",
    "sampleBordBarcode",
    "updateAt",
    "well"
})
public class SampleGene {

    @XmlElementRef(name = "auditStatus", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> auditStatus;
    @XmlElementRef(name = "auditor", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> auditor;
    @XmlElementRef(name = "case_id", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> caseId;
    @XmlElementRef(name = "gene_type", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> geneType;
    @XmlElementRef(name = "id", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> id;
    @XmlElementRef(name = "imagePath", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> imagePath;
    @XmlElementRef(name = "is_mix", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> isMix;
    @XmlElementRef(name = "markerAllelesElements", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> markerAllelesElements;
    @XmlElementRef(name = "panel_name", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> panelName;
    @XmlElementRef(name = "sampleAppriase", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> sampleAppriase;
    @XmlElementRef(name = "sample_barcode", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> sampleBarcode;
    @XmlElementRef(name = "sample_bord_barcode", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> sampleBordBarcode;
    @XmlElement(name = "update_at")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar updateAt;
    @XmlElementRef(name = "well", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> well;

    /**
     * 获取auditStatus属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAuditStatus() {
        return auditStatus;
    }

    /**
     * 设置auditStatus属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAuditStatus(JAXBElement<String> value) {
        this.auditStatus = value;
    }

    /**
     * 获取auditor属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAuditor() {
        return auditor;
    }

    /**
     * 设置auditor属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAuditor(JAXBElement<String> value) {
        this.auditor = value;
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
     * 获取geneType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getGeneType() {
        return geneType;
    }

    /**
     * 设置geneType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setGeneType(JAXBElement<String> value) {
        this.geneType = value;
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
     * 获取imagePath属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getImagePath() {
        return imagePath;
    }

    /**
     * 设置imagePath属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setImagePath(JAXBElement<String> value) {
        this.imagePath = value;
    }

    /**
     * 获取isMix属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getIsMix() {
        return isMix;
    }

    /**
     * 设置isMix属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setIsMix(JAXBElement<Integer> value) {
        this.isMix = value;
    }

    /**
     * 获取markerAllelesElements属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMarkerAllelesElements() {
        return markerAllelesElements;
    }

    /**
     * 设置markerAllelesElements属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMarkerAllelesElements(JAXBElement<String> value) {
        this.markerAllelesElements = value;
    }

    /**
     * 获取panelName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPanelName() {
        return panelName;
    }

    /**
     * 设置panelName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPanelName(JAXBElement<String> value) {
        this.panelName = value;
    }

    /**
     * 获取sampleAppriase属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSampleAppriase() {
        return sampleAppriase;
    }

    /**
     * 设置sampleAppriase属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSampleAppriase(JAXBElement<String> value) {
        this.sampleAppriase = value;
    }

    /**
     * 获取sampleBarcode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSampleBarcode() {
        return sampleBarcode;
    }

    /**
     * 设置sampleBarcode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSampleBarcode(JAXBElement<String> value) {
        this.sampleBarcode = value;
    }

    /**
     * 获取sampleBordBarcode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSampleBordBarcode() {
        return sampleBordBarcode;
    }

    /**
     * 设置sampleBordBarcode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSampleBordBarcode(JAXBElement<String> value) {
        this.sampleBordBarcode = value;
    }

    /**
     * 获取updateAt属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUpdateAt() {
        return updateAt;
    }

    /**
     * 设置updateAt属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUpdateAt(XMLGregorianCalendar value) {
        this.updateAt = value;
    }

    /**
     * 获取well属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getWell() {
        return well;
    }

    /**
     * 设置well属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setWell(JAXBElement<String> value) {
        this.well = value;
    }

}
