
package com.bazl.clims.webservice.client.libMatch;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>WS_LibMatchSourceSetting complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="WS_LibMatchSourceSetting"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="additionOptions" type="{http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com}ArrayOfWS_AdditionOption" minOccurs="0"/&gt;
 *         &lt;element name="maxAge" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="minAge" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="sex" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="sourceSampleType" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="targetSampleType" type="{http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com}ArrayOfInt" minOccurs="0"/&gt;
 *         &lt;element name="wsMatchTypeOption" type="{http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com}ArrayOfWS_MatchTypeSetting" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WS_LibMatchSourceSetting", propOrder = {
    "additionOptions",
    "maxAge",
    "minAge",
    "sex",
    "sourceSampleType",
    "targetSampleType",
    "wsMatchTypeOption"
})
public class WSLibMatchSourceSetting {

    @XmlElementRef(name = "additionOptions", namespace = "http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfWSAdditionOption> additionOptions;
    protected Integer maxAge;
    protected Integer minAge;
    protected Integer sex;
    protected Integer sourceSampleType;
    @XmlElementRef(name = "targetSampleType", namespace = "http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfInt> targetSampleType;
    @XmlElementRef(name = "wsMatchTypeOption", namespace = "http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfWSMatchTypeSetting> wsMatchTypeOption;

    /**
     * 获取additionOptions属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfWSAdditionOption }{@code >}
     *     
     */
    public JAXBElement<ArrayOfWSAdditionOption> getAdditionOptions() {
        return additionOptions;
    }

    /**
     * 设置additionOptions属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfWSAdditionOption }{@code >}
     *     
     */
    public void setAdditionOptions(JAXBElement<ArrayOfWSAdditionOption> value) {
        this.additionOptions = value;
    }

    /**
     * 获取maxAge属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMaxAge() {
        return maxAge;
    }

    /**
     * 设置maxAge属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMaxAge(Integer value) {
        this.maxAge = value;
    }

    /**
     * 获取minAge属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMinAge() {
        return minAge;
    }

    /**
     * 设置minAge属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMinAge(Integer value) {
        this.minAge = value;
    }

    /**
     * 获取sex属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置sex属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSex(Integer value) {
        this.sex = value;
    }

    /**
     * 获取sourceSampleType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSourceSampleType() {
        return sourceSampleType;
    }

    /**
     * 设置sourceSampleType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSourceSampleType(Integer value) {
        this.sourceSampleType = value;
    }

    /**
     * 获取targetSampleType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfInt }{@code >}
     *     
     */
    public JAXBElement<ArrayOfInt> getTargetSampleType() {
        return targetSampleType;
    }

    /**
     * 设置targetSampleType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfInt }{@code >}
     *     
     */
    public void setTargetSampleType(JAXBElement<ArrayOfInt> value) {
        this.targetSampleType = value;
    }

    /**
     * 获取wsMatchTypeOption属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfWSMatchTypeSetting }{@code >}
     *     
     */
    public JAXBElement<ArrayOfWSMatchTypeSetting> getWsMatchTypeOption() {
        return wsMatchTypeOption;
    }

    /**
     * 设置wsMatchTypeOption属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfWSMatchTypeSetting }{@code >}
     *     
     */
    public void setWsMatchTypeOption(JAXBElement<ArrayOfWSMatchTypeSetting> value) {
        this.wsMatchTypeOption = value;
    }

}
