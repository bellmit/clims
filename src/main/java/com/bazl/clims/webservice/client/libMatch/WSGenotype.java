
package com.bazl.clims.webservice.client.libMatch;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>WS_Genotype complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="WS_Genotype"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="contributorNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="dataType" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="genotypeString" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="markerTypes" type="{http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com}ArrayOfWS_MarkerTypes" minOccurs="0"/&gt;
 *         &lt;element name="panelName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sampleID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WS_Genotype", propOrder = {
    "contributorNum",
    "dataType",
    "genotypeString",
    "markerTypes",
    "panelName",
    "sampleID"
})
public class WSGenotype {

    protected Integer contributorNum;
    protected Integer dataType;
    @XmlElementRef(name = "genotypeString", namespace = "http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> genotypeString;
    @XmlElementRef(name = "markerTypes", namespace = "http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfWSMarkerTypes> markerTypes;
    @XmlElementRef(name = "panelName", namespace = "http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> panelName;
    @XmlElementRef(name = "sampleID", namespace = "http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> sampleID;

    /**
     * 获取contributorNum属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getContributorNum() {
        return contributorNum;
    }

    /**
     * 设置contributorNum属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setContributorNum(Integer value) {
        this.contributorNum = value;
    }

    /**
     * 获取dataType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDataType() {
        return dataType;
    }

    /**
     * 设置dataType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDataType(Integer value) {
        this.dataType = value;
    }

    /**
     * 获取genotypeString属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getGenotypeString() {
        return genotypeString;
    }

    /**
     * 设置genotypeString属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setGenotypeString(JAXBElement<String> value) {
        this.genotypeString = value;
    }

    /**
     * 获取markerTypes属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfWSMarkerTypes }{@code >}
     *     
     */
    public JAXBElement<ArrayOfWSMarkerTypes> getMarkerTypes() {
        return markerTypes;
    }

    /**
     * 设置markerTypes属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfWSMarkerTypes }{@code >}
     *     
     */
    public void setMarkerTypes(JAXBElement<ArrayOfWSMarkerTypes> value) {
        this.markerTypes = value;
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
     * 获取sampleID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSampleID() {
        return sampleID;
    }

    /**
     * 设置sampleID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSampleID(JAXBElement<String> value) {
        this.sampleID = value;
    }

}
