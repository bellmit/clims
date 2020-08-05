
package com.bazl.clims.webservice.caseSampleLib;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>SampleLib complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="SampleLib"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="labno" type="{http://casesamplelib.webservice.web.dna.todaysoft.com}ArrayOfString" minOccurs="0"/&gt;
 *         &lt;element name="maxSubmittime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="sampleType" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="sampleTypeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sampleid" type="{http://casesamplelib.webservice.web.dna.todaysoft.com}ArrayOfString" minOccurs="0"/&gt;
 *         &lt;element name="samplename" type="{http://casesamplelib.webservice.web.dna.todaysoft.com}ArrayOfString" minOccurs="0"/&gt;
 *         &lt;element name="submittime" type="{http://casesamplelib.webservice.web.dna.todaysoft.com}ArrayOfDateTime" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SampleLib", propOrder = {
    "labno",
    "maxSubmittime",
    "sampleType",
    "sampleTypeName",
    "sampleid",
    "samplename",
    "submittime"
})
public class SampleLib {

    @XmlElementRef(name = "labno", namespace = "http://casesamplelib.webservice.web.dna.todaysoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfString> labno;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar maxSubmittime;
    protected Integer sampleType;
    @XmlElementRef(name = "sampleTypeName", namespace = "http://casesamplelib.webservice.web.dna.todaysoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> sampleTypeName;
    @XmlElementRef(name = "sampleid", namespace = "http://casesamplelib.webservice.web.dna.todaysoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfString> sampleid;
    @XmlElementRef(name = "samplename", namespace = "http://casesamplelib.webservice.web.dna.todaysoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfString> samplename;
    @XmlElementRef(name = "submittime", namespace = "http://casesamplelib.webservice.web.dna.todaysoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfDateTime> submittime;

    /**
     * 获取labno属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfString }{@code >}
     *     
     */
    public JAXBElement<ArrayOfString> getLabno() {
        return labno;
    }

    /**
     * 设置labno属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfString }{@code >}
     *     
     */
    public void setLabno(JAXBElement<ArrayOfString> value) {
        this.labno = value;
    }

    /**
     * 获取maxSubmittime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getMaxSubmittime() {
        return maxSubmittime;
    }

    /**
     * 设置maxSubmittime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setMaxSubmittime(XMLGregorianCalendar value) {
        this.maxSubmittime = value;
    }

    /**
     * 获取sampleType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSampleType() {
        return sampleType;
    }

    /**
     * 设置sampleType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSampleType(Integer value) {
        this.sampleType = value;
    }

    /**
     * 获取sampleTypeName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSampleTypeName() {
        return sampleTypeName;
    }

    /**
     * 设置sampleTypeName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSampleTypeName(JAXBElement<String> value) {
        this.sampleTypeName = value;
    }

    /**
     * 获取sampleid属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfString }{@code >}
     *     
     */
    public JAXBElement<ArrayOfString> getSampleid() {
        return sampleid;
    }

    /**
     * 设置sampleid属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfString }{@code >}
     *     
     */
    public void setSampleid(JAXBElement<ArrayOfString> value) {
        this.sampleid = value;
    }

    /**
     * 获取samplename属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfString }{@code >}
     *     
     */
    public JAXBElement<ArrayOfString> getSamplename() {
        return samplename;
    }

    /**
     * 设置samplename属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfString }{@code >}
     *     
     */
    public void setSamplename(JAXBElement<ArrayOfString> value) {
        this.samplename = value;
    }

    /**
     * 获取submittime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfDateTime }{@code >}
     *     
     */
    public JAXBElement<ArrayOfDateTime> getSubmittime() {
        return submittime;
    }

    /**
     * 设置submittime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfDateTime }{@code >}
     *     
     */
    public void setSubmittime(JAXBElement<ArrayOfDateTime> value) {
        this.submittime = value;
    }

}
