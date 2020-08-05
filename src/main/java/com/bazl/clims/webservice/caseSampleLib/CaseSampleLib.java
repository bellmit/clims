
package com.bazl.clims.webservice.caseSampleLib;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>CaseSampleLib complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="CaseSampleLib"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="caseID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="caseName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="labno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sampleCnt" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="sampleLibList" type="{http://casesamplelib.webservice.web.dna.todaysoft.com}ArrayOfSampleLib" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CaseSampleLib", propOrder = {
    "caseID",
    "caseName",
    "labno",
    "sampleCnt",
    "sampleLibList"
})
public class CaseSampleLib {

    @XmlElementRef(name = "caseID", namespace = "http://casesamplelib.webservice.web.dna.todaysoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> caseID;
    @XmlElementRef(name = "caseName", namespace = "http://casesamplelib.webservice.web.dna.todaysoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> caseName;
    @XmlElementRef(name = "labno", namespace = "http://casesamplelib.webservice.web.dna.todaysoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> labno;
    protected Integer sampleCnt;
    @XmlElementRef(name = "sampleLibList", namespace = "http://casesamplelib.webservice.web.dna.todaysoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfSampleLib> sampleLibList;

    /**
     * 获取caseID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCaseID() {
        return caseID;
    }

    /**
     * 设置caseID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCaseID(JAXBElement<String> value) {
        this.caseID = value;
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
     * 获取labno属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getLabno() {
        return labno;
    }

    /**
     * 设置labno属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setLabno(JAXBElement<String> value) {
        this.labno = value;
    }

    /**
     * 获取sampleCnt属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSampleCnt() {
        return sampleCnt;
    }

    /**
     * 设置sampleCnt属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSampleCnt(Integer value) {
        this.sampleCnt = value;
    }

    /**
     * 获取sampleLibList属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfSampleLib }{@code >}
     *     
     */
    public JAXBElement<ArrayOfSampleLib> getSampleLibList() {
        return sampleLibList;
    }

    /**
     * 设置sampleLibList属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfSampleLib }{@code >}
     *     
     */
    public void setSampleLibList(JAXBElement<ArrayOfSampleLib> value) {
        this.sampleLibList = value;
    }

}
