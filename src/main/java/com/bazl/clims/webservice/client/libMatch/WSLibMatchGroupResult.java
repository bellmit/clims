
package com.bazl.clims.webservice.client.libMatch;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>WS_LibMatchGroupResult complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="WS_LibMatchGroupResult"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="groupId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="lastMatchTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="matchCnt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="matchType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="rowCnt" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="sampleList" type="{http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com}ArrayOfWS_LibMatchGroupRecord" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WS_LibMatchGroupResult", propOrder = {
    "groupId",
    "lastMatchTime",
    "matchCnt",
    "matchType",
    "rowCnt",
    "sampleList"
})
public class WSLibMatchGroupResult {

    @XmlElementRef(name = "groupId", namespace = "http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> groupId;
    @XmlElementRef(name = "lastMatchTime", namespace = "http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> lastMatchTime;
    @XmlElementRef(name = "matchCnt", namespace = "http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> matchCnt;
    @XmlElementRef(name = "matchType", namespace = "http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> matchType;
    protected Integer rowCnt;
    @XmlElementRef(name = "sampleList", namespace = "http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfWSLibMatchGroupRecord> sampleList;

    /**
     * 获取groupId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getGroupId() {
        return groupId;
    }

    /**
     * 设置groupId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setGroupId(JAXBElement<String> value) {
        this.groupId = value;
    }

    /**
     * 获取lastMatchTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getLastMatchTime() {
        return lastMatchTime;
    }

    /**
     * 设置lastMatchTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setLastMatchTime(JAXBElement<String> value) {
        this.lastMatchTime = value;
    }

    /**
     * 获取matchCnt属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMatchCnt() {
        return matchCnt;
    }

    /**
     * 设置matchCnt属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMatchCnt(JAXBElement<String> value) {
        this.matchCnt = value;
    }

    /**
     * 获取matchType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMatchType() {
        return matchType;
    }

    /**
     * 设置matchType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMatchType(JAXBElement<String> value) {
        this.matchType = value;
    }

    /**
     * 获取rowCnt属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRowCnt() {
        return rowCnt;
    }

    /**
     * 设置rowCnt属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRowCnt(Integer value) {
        this.rowCnt = value;
    }

    /**
     * 获取sampleList属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfWSLibMatchGroupRecord }{@code >}
     *     
     */
    public JAXBElement<ArrayOfWSLibMatchGroupRecord> getSampleList() {
        return sampleList;
    }

    /**
     * 设置sampleList属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfWSLibMatchGroupRecord }{@code >}
     *     
     */
    public void setSampleList(JAXBElement<ArrayOfWSLibMatchGroupRecord> value) {
        this.sampleList = value;
    }

}
