
package com.bazl.clims.webservice.client.libMatch;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>WS_LibMatchResult complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="WS_LibMatchResult"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="caseMatched" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="genotypeA" type="{http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com}ArrayOfWS_MarkerTypes" minOccurs="0"/&gt;
 *         &lt;element name="genotypeB" type="{http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com}ArrayOfWS_MarkerTypes" minOccurs="0"/&gt;
 *         &lt;element name="genotypeC" type="{http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com}ArrayOfWS_MarkerTypes" minOccurs="0"/&gt;
 *         &lt;element name="isSampleBSource" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="matchCount" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="matchId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="matchType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="matchedTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="operator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="panelNameA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="panelNameB" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="panelNameC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sampleAId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sampleAName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sampleBId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sampleBName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sampleCId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sampleCName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="singleMarkerRates" type="{http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com}ArrayOfWS_SingleMarkerRate" minOccurs="0"/&gt;
 *         &lt;element name="sourceCaseId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sourceCaseName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sourceSampleType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="statusCode" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="submitOperA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="submitOperB" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="submitOperC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="submitOperator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="submitOrg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="submitTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="targetCaseId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="targetCaseName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="targetSampleType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="totalMatchPossibility" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="verifyTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WS_LibMatchResult", propOrder = {
    "caseMatched",
    "genotypeA",
    "genotypeB",
    "genotypeC",
    "isSampleBSource",
    "matchCount",
    "matchId",
    "matchType",
    "matchedTime",
    "operator",
    "panelNameA",
    "panelNameB",
    "panelNameC",
    "sampleAId",
    "sampleAName",
    "sampleBId",
    "sampleBName",
    "sampleCId",
    "sampleCName",
    "singleMarkerRates",
    "sourceCaseId",
    "sourceCaseName",
    "sourceSampleType",
    "status",
    "statusCode",
    "submitOperA",
    "submitOperB",
    "submitOperC",
    "submitOperator",
    "submitOrg",
    "submitTime",
    "targetCaseId",
    "targetCaseName",
    "targetSampleType",
    "totalMatchPossibility",
    "verifyTime"
})
public class WSLibMatchResult {

    protected Integer caseMatched;
    @XmlElementRef(name = "genotypeA", namespace = "http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfWSMarkerTypes> genotypeA;
    @XmlElementRef(name = "genotypeB", namespace = "http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfWSMarkerTypes> genotypeB;
    @XmlElementRef(name = "genotypeC", namespace = "http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfWSMarkerTypes> genotypeC;
    protected Integer isSampleBSource;
    protected Integer matchCount;
    @XmlElementRef(name = "matchId", namespace = "http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> matchId;
    @XmlElementRef(name = "matchType", namespace = "http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> matchType;
    @XmlElementRef(name = "matchedTime", namespace = "http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> matchedTime;
    @XmlElementRef(name = "operator", namespace = "http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> operator;
    @XmlElementRef(name = "panelNameA", namespace = "http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> panelNameA;
    @XmlElementRef(name = "panelNameB", namespace = "http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> panelNameB;
    @XmlElementRef(name = "panelNameC", namespace = "http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> panelNameC;
    @XmlElementRef(name = "sampleAId", namespace = "http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> sampleAId;
    @XmlElementRef(name = "sampleAName", namespace = "http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> sampleAName;
    @XmlElementRef(name = "sampleBId", namespace = "http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> sampleBId;
    @XmlElementRef(name = "sampleBName", namespace = "http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> sampleBName;
    @XmlElementRef(name = "sampleCId", namespace = "http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> sampleCId;
    @XmlElementRef(name = "sampleCName", namespace = "http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> sampleCName;
    @XmlElementRef(name = "singleMarkerRates", namespace = "http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfWSSingleMarkerRate> singleMarkerRates;
    @XmlElementRef(name = "sourceCaseId", namespace = "http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> sourceCaseId;
    @XmlElementRef(name = "sourceCaseName", namespace = "http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> sourceCaseName;
    @XmlElementRef(name = "sourceSampleType", namespace = "http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> sourceSampleType;
    @XmlElementRef(name = "status", namespace = "http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> status;
    protected Integer statusCode;
    @XmlElementRef(name = "submitOperA", namespace = "http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> submitOperA;
    @XmlElementRef(name = "submitOperB", namespace = "http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> submitOperB;
    @XmlElementRef(name = "submitOperC", namespace = "http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> submitOperC;
    @XmlElementRef(name = "submitOperator", namespace = "http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> submitOperator;
    @XmlElementRef(name = "submitOrg", namespace = "http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> submitOrg;
    @XmlElementRef(name = "submitTime", namespace = "http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> submitTime;
    @XmlElementRef(name = "targetCaseId", namespace = "http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> targetCaseId;
    @XmlElementRef(name = "targetCaseName", namespace = "http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> targetCaseName;
    @XmlElementRef(name = "targetSampleType", namespace = "http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> targetSampleType;
    protected Double totalMatchPossibility;
    @XmlElementRef(name = "verifyTime", namespace = "http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> verifyTime;

    /**
     * 获取caseMatched属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCaseMatched() {
        return caseMatched;
    }

    /**
     * 设置caseMatched属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCaseMatched(Integer value) {
        this.caseMatched = value;
    }

    /**
     * 获取genotypeA属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfWSMarkerTypes }{@code >}
     *     
     */
    public JAXBElement<ArrayOfWSMarkerTypes> getGenotypeA() {
        return genotypeA;
    }

    /**
     * 设置genotypeA属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfWSMarkerTypes }{@code >}
     *     
     */
    public void setGenotypeA(JAXBElement<ArrayOfWSMarkerTypes> value) {
        this.genotypeA = value;
    }

    /**
     * 获取genotypeB属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfWSMarkerTypes }{@code >}
     *     
     */
    public JAXBElement<ArrayOfWSMarkerTypes> getGenotypeB() {
        return genotypeB;
    }

    /**
     * 设置genotypeB属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfWSMarkerTypes }{@code >}
     *     
     */
    public void setGenotypeB(JAXBElement<ArrayOfWSMarkerTypes> value) {
        this.genotypeB = value;
    }

    /**
     * 获取genotypeC属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfWSMarkerTypes }{@code >}
     *     
     */
    public JAXBElement<ArrayOfWSMarkerTypes> getGenotypeC() {
        return genotypeC;
    }

    /**
     * 设置genotypeC属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfWSMarkerTypes }{@code >}
     *     
     */
    public void setGenotypeC(JAXBElement<ArrayOfWSMarkerTypes> value) {
        this.genotypeC = value;
    }

    /**
     * 获取isSampleBSource属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIsSampleBSource() {
        return isSampleBSource;
    }

    /**
     * 设置isSampleBSource属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIsSampleBSource(Integer value) {
        this.isSampleBSource = value;
    }

    /**
     * 获取matchCount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMatchCount() {
        return matchCount;
    }

    /**
     * 设置matchCount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMatchCount(Integer value) {
        this.matchCount = value;
    }

    /**
     * 获取matchId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMatchId() {
        return matchId;
    }

    /**
     * 设置matchId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMatchId(JAXBElement<String> value) {
        this.matchId = value;
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
     * 获取matchedTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMatchedTime() {
        return matchedTime;
    }

    /**
     * 设置matchedTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMatchedTime(JAXBElement<String> value) {
        this.matchedTime = value;
    }

    /**
     * 获取operator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOperator() {
        return operator;
    }

    /**
     * 设置operator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOperator(JAXBElement<String> value) {
        this.operator = value;
    }

    /**
     * 获取panelNameA属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPanelNameA() {
        return panelNameA;
    }

    /**
     * 设置panelNameA属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPanelNameA(JAXBElement<String> value) {
        this.panelNameA = value;
    }

    /**
     * 获取panelNameB属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPanelNameB() {
        return panelNameB;
    }

    /**
     * 设置panelNameB属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPanelNameB(JAXBElement<String> value) {
        this.panelNameB = value;
    }

    /**
     * 获取panelNameC属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPanelNameC() {
        return panelNameC;
    }

    /**
     * 设置panelNameC属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPanelNameC(JAXBElement<String> value) {
        this.panelNameC = value;
    }

    /**
     * 获取sampleAId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSampleAId() {
        return sampleAId;
    }

    /**
     * 设置sampleAId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSampleAId(JAXBElement<String> value) {
        this.sampleAId = value;
    }

    /**
     * 获取sampleAName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSampleAName() {
        return sampleAName;
    }

    /**
     * 设置sampleAName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSampleAName(JAXBElement<String> value) {
        this.sampleAName = value;
    }

    /**
     * 获取sampleBId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSampleBId() {
        return sampleBId;
    }

    /**
     * 设置sampleBId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSampleBId(JAXBElement<String> value) {
        this.sampleBId = value;
    }

    /**
     * 获取sampleBName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSampleBName() {
        return sampleBName;
    }

    /**
     * 设置sampleBName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSampleBName(JAXBElement<String> value) {
        this.sampleBName = value;
    }

    /**
     * 获取sampleCId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSampleCId() {
        return sampleCId;
    }

    /**
     * 设置sampleCId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSampleCId(JAXBElement<String> value) {
        this.sampleCId = value;
    }

    /**
     * 获取sampleCName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSampleCName() {
        return sampleCName;
    }

    /**
     * 设置sampleCName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSampleCName(JAXBElement<String> value) {
        this.sampleCName = value;
    }

    /**
     * 获取singleMarkerRates属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfWSSingleMarkerRate }{@code >}
     *     
     */
    public JAXBElement<ArrayOfWSSingleMarkerRate> getSingleMarkerRates() {
        return singleMarkerRates;
    }

    /**
     * 设置singleMarkerRates属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfWSSingleMarkerRate }{@code >}
     *     
     */
    public void setSingleMarkerRates(JAXBElement<ArrayOfWSSingleMarkerRate> value) {
        this.singleMarkerRates = value;
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
     * 获取sourceCaseName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSourceCaseName() {
        return sourceCaseName;
    }

    /**
     * 设置sourceCaseName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSourceCaseName(JAXBElement<String> value) {
        this.sourceCaseName = value;
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
     * 获取status属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getStatus() {
        return status;
    }

    /**
     * 设置status属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setStatus(JAXBElement<String> value) {
        this.status = value;
    }

    /**
     * 获取statusCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getStatusCode() {
        return statusCode;
    }

    /**
     * 设置statusCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setStatusCode(Integer value) {
        this.statusCode = value;
    }

    /**
     * 获取submitOperA属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSubmitOperA() {
        return submitOperA;
    }

    /**
     * 设置submitOperA属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSubmitOperA(JAXBElement<String> value) {
        this.submitOperA = value;
    }

    /**
     * 获取submitOperB属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSubmitOperB() {
        return submitOperB;
    }

    /**
     * 设置submitOperB属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSubmitOperB(JAXBElement<String> value) {
        this.submitOperB = value;
    }

    /**
     * 获取submitOperC属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSubmitOperC() {
        return submitOperC;
    }

    /**
     * 设置submitOperC属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSubmitOperC(JAXBElement<String> value) {
        this.submitOperC = value;
    }

    /**
     * 获取submitOperator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSubmitOperator() {
        return submitOperator;
    }

    /**
     * 设置submitOperator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSubmitOperator(JAXBElement<String> value) {
        this.submitOperator = value;
    }

    /**
     * 获取submitOrg属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSubmitOrg() {
        return submitOrg;
    }

    /**
     * 设置submitOrg属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSubmitOrg(JAXBElement<String> value) {
        this.submitOrg = value;
    }

    /**
     * 获取submitTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSubmitTime() {
        return submitTime;
    }

    /**
     * 设置submitTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSubmitTime(JAXBElement<String> value) {
        this.submitTime = value;
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
     * 获取targetCaseName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTargetCaseName() {
        return targetCaseName;
    }

    /**
     * 设置targetCaseName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTargetCaseName(JAXBElement<String> value) {
        this.targetCaseName = value;
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

    /**
     * 获取totalMatchPossibility属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTotalMatchPossibility() {
        return totalMatchPossibility;
    }

    /**
     * 设置totalMatchPossibility属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTotalMatchPossibility(Double value) {
        this.totalMatchPossibility = value;
    }

    /**
     * 获取verifyTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getVerifyTime() {
        return verifyTime;
    }

    /**
     * 设置verifyTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setVerifyTime(JAXBElement<String> value) {
        this.verifyTime = value;
    }

}
