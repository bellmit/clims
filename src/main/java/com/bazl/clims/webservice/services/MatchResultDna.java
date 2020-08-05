
package com.bazl.clims.webservice.services;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>MatchResultDna complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="MatchResultDna"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="cardIda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="cardIdb" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="caseInvolved" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="crimeStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="crimeTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="fullNamea" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="fullNameb" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="groupid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="hcypStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="hcypTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="isAppend" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="isSampleBSource" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="matchResultString" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="matchcount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="matchid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="matchtime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="matchtime2" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="matchtype" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="operator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="panelnamea" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="panelnameb" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="panelnamec" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sameGender" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="samplea_case_type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="samplea_type_name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sampleaid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sampleaname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sampleb_case_type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sampleb_type_name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="samplebid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="samplebname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="samplecid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="samplecname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sourceCaseId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sourceCaseName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sourcesampletype" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="state" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="submitState" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="submitoperator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="submitorg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="submittime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="targetCaseId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="targetCaseName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="targetsampletype" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="totalMatchPossibility" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="transferTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="transfer_status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="transferor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="verifytime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MatchResultDna", namespace = "http://domain.lims.viathink.com", propOrder = {
    "cardIda",
    "cardIdb",
    "caseInvolved",
    "crimeStatus",
    "crimeTime",
    "fullNamea",
    "fullNameb",
    "groupid",
    "hcypStatus",
    "hcypTime",
    "isAppend",
    "isSampleBSource",
    "matchResultString",
    "matchcount",
    "matchid",
    "matchtime",
    "matchtime2",
    "matchtype",
    "operator",
    "panelnamea",
    "panelnameb",
    "panelnamec",
    "sameGender",
    "sampleaCaseType",
    "sampleaTypeName",
    "sampleaid",
    "sampleaname",
    "samplebCaseType",
    "samplebTypeName",
    "samplebid",
    "samplebname",
    "samplecid",
    "samplecname",
    "sourceCaseId",
    "sourceCaseName",
    "sourcesampletype",
    "state",
    "submitState",
    "submitoperator",
    "submitorg",
    "submittime",
    "targetCaseId",
    "targetCaseName",
    "targetsampletype",
    "totalMatchPossibility",
    "transferTime",
    "transferStatus",
    "transferor",
    "verifytime"
})
public class MatchResultDna {

    @XmlElementRef(name = "cardIda", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> cardIda;
    @XmlElementRef(name = "cardIdb", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> cardIdb;
    @XmlElementRef(name = "caseInvolved", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> caseInvolved;
    @XmlElementRef(name = "crimeStatus", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> crimeStatus;
    @XmlElementRef(name = "crimeTime", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> crimeTime;
    @XmlElementRef(name = "fullNamea", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> fullNamea;
    @XmlElementRef(name = "fullNameb", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> fullNameb;
    @XmlElementRef(name = "groupid", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> groupid;
    @XmlElementRef(name = "hcypStatus", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> hcypStatus;
    @XmlElementRef(name = "hcypTime", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> hcypTime;
    @XmlElementRef(name = "isAppend", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> isAppend;
    @XmlElementRef(name = "isSampleBSource", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> isSampleBSource;
    @XmlElementRef(name = "matchResultString", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> matchResultString;
    @XmlElementRef(name = "matchcount", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> matchcount;
    @XmlElementRef(name = "matchid", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> matchid;
    @XmlElementRef(name = "matchtime", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> matchtime;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar matchtime2;
    @XmlElementRef(name = "matchtype", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> matchtype;
    @XmlElementRef(name = "operator", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> operator;
    @XmlElementRef(name = "panelnamea", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> panelnamea;
    @XmlElementRef(name = "panelnameb", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> panelnameb;
    @XmlElementRef(name = "panelnamec", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> panelnamec;
    @XmlElementRef(name = "sameGender", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> sameGender;
    @XmlElementRef(name = "samplea_case_type", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> sampleaCaseType;
    @XmlElementRef(name = "samplea_type_name", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> sampleaTypeName;
    @XmlElementRef(name = "sampleaid", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> sampleaid;
    @XmlElementRef(name = "sampleaname", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> sampleaname;
    @XmlElementRef(name = "sampleb_case_type", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> samplebCaseType;
    @XmlElementRef(name = "sampleb_type_name", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> samplebTypeName;
    @XmlElementRef(name = "samplebid", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> samplebid;
    @XmlElementRef(name = "samplebname", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> samplebname;
    @XmlElementRef(name = "samplecid", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> samplecid;
    @XmlElementRef(name = "samplecname", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> samplecname;
    @XmlElementRef(name = "sourceCaseId", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> sourceCaseId;
    @XmlElementRef(name = "sourceCaseName", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> sourceCaseName;
    @XmlElementRef(name = "sourcesampletype", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> sourcesampletype;
    @XmlElementRef(name = "state", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> state;
    @XmlElementRef(name = "submitState", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> submitState;
    @XmlElementRef(name = "submitoperator", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> submitoperator;
    @XmlElementRef(name = "submitorg", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> submitorg;
    @XmlElementRef(name = "submittime", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> submittime;
    @XmlElementRef(name = "targetCaseId", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> targetCaseId;
    @XmlElementRef(name = "targetCaseName", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> targetCaseName;
    @XmlElementRef(name = "targetsampletype", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> targetsampletype;
    @XmlElementRef(name = "totalMatchPossibility", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<Double> totalMatchPossibility;
    @XmlElementRef(name = "transferTime", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> transferTime;
    @XmlElementRef(name = "transfer_status", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> transferStatus;
    @XmlElementRef(name = "transferor", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> transferor;
    @XmlElementRef(name = "verifytime", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> verifytime;

    /**
     * 获取cardIda属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCardIda() {
        return cardIda;
    }

    /**
     * 设置cardIda属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCardIda(JAXBElement<String> value) {
        this.cardIda = value;
    }

    /**
     * 获取cardIdb属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCardIdb() {
        return cardIdb;
    }

    /**
     * 设置cardIdb属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCardIdb(JAXBElement<String> value) {
        this.cardIdb = value;
    }

    /**
     * 获取caseInvolved属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getCaseInvolved() {
        return caseInvolved;
    }

    /**
     * 设置caseInvolved属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setCaseInvolved(JAXBElement<Integer> value) {
        this.caseInvolved = value;
    }

    /**
     * 获取crimeStatus属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCrimeStatus() {
        return crimeStatus;
    }

    /**
     * 设置crimeStatus属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCrimeStatus(JAXBElement<String> value) {
        this.crimeStatus = value;
    }

    /**
     * 获取crimeTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCrimeTime() {
        return crimeTime;
    }

    /**
     * 设置crimeTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCrimeTime(JAXBElement<String> value) {
        this.crimeTime = value;
    }

    /**
     * 获取fullNamea属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFullNamea() {
        return fullNamea;
    }

    /**
     * 设置fullNamea属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFullNamea(JAXBElement<String> value) {
        this.fullNamea = value;
    }

    /**
     * 获取fullNameb属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFullNameb() {
        return fullNameb;
    }

    /**
     * 设置fullNameb属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFullNameb(JAXBElement<String> value) {
        this.fullNameb = value;
    }

    /**
     * 获取groupid属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getGroupid() {
        return groupid;
    }

    /**
     * 设置groupid属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setGroupid(JAXBElement<String> value) {
        this.groupid = value;
    }

    /**
     * 获取hcypStatus属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getHcypStatus() {
        return hcypStatus;
    }

    /**
     * 设置hcypStatus属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setHcypStatus(JAXBElement<String> value) {
        this.hcypStatus = value;
    }

    /**
     * 获取hcypTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getHcypTime() {
        return hcypTime;
    }

    /**
     * 设置hcypTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setHcypTime(JAXBElement<String> value) {
        this.hcypTime = value;
    }

    /**
     * 获取isAppend属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIsAppend() {
        return isAppend;
    }

    /**
     * 设置isAppend属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIsAppend(JAXBElement<String> value) {
        this.isAppend = value;
    }

    /**
     * 获取isSampleBSource属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIsSampleBSource() {
        return isSampleBSource;
    }

    /**
     * 设置isSampleBSource属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIsSampleBSource(JAXBElement<String> value) {
        this.isSampleBSource = value;
    }

    /**
     * 获取matchResultString属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMatchResultString() {
        return matchResultString;
    }

    /**
     * 设置matchResultString属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMatchResultString(JAXBElement<String> value) {
        this.matchResultString = value;
    }

    /**
     * 获取matchcount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMatchcount() {
        return matchcount;
    }

    /**
     * 设置matchcount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMatchcount(JAXBElement<String> value) {
        this.matchcount = value;
    }

    /**
     * 获取matchid属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMatchid() {
        return matchid;
    }

    /**
     * 设置matchid属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMatchid(JAXBElement<String> value) {
        this.matchid = value;
    }

    /**
     * 获取matchtime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMatchtime() {
        return matchtime;
    }

    /**
     * 设置matchtime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMatchtime(JAXBElement<String> value) {
        this.matchtime = value;
    }

    /**
     * 获取matchtime2属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getMatchtime2() {
        return matchtime2;
    }

    /**
     * 设置matchtime2属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setMatchtime2(XMLGregorianCalendar value) {
        this.matchtime2 = value;
    }

    /**
     * 获取matchtype属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMatchtype() {
        return matchtype;
    }

    /**
     * 设置matchtype属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMatchtype(JAXBElement<String> value) {
        this.matchtype = value;
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
     * 获取panelnamea属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPanelnamea() {
        return panelnamea;
    }

    /**
     * 设置panelnamea属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPanelnamea(JAXBElement<String> value) {
        this.panelnamea = value;
    }

    /**
     * 获取panelnameb属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPanelnameb() {
        return panelnameb;
    }

    /**
     * 设置panelnameb属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPanelnameb(JAXBElement<String> value) {
        this.panelnameb = value;
    }

    /**
     * 获取panelnamec属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPanelnamec() {
        return panelnamec;
    }

    /**
     * 设置panelnamec属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPanelnamec(JAXBElement<String> value) {
        this.panelnamec = value;
    }

    /**
     * 获取sameGender属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSameGender() {
        return sameGender;
    }

    /**
     * 设置sameGender属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSameGender(JAXBElement<String> value) {
        this.sameGender = value;
    }

    /**
     * 获取sampleaCaseType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSampleaCaseType() {
        return sampleaCaseType;
    }

    /**
     * 设置sampleaCaseType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSampleaCaseType(JAXBElement<String> value) {
        this.sampleaCaseType = value;
    }

    /**
     * 获取sampleaTypeName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSampleaTypeName() {
        return sampleaTypeName;
    }

    /**
     * 设置sampleaTypeName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSampleaTypeName(JAXBElement<String> value) {
        this.sampleaTypeName = value;
    }

    /**
     * 获取sampleaid属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSampleaid() {
        return sampleaid;
    }

    /**
     * 设置sampleaid属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSampleaid(JAXBElement<String> value) {
        this.sampleaid = value;
    }

    /**
     * 获取sampleaname属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSampleaname() {
        return sampleaname;
    }

    /**
     * 设置sampleaname属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSampleaname(JAXBElement<String> value) {
        this.sampleaname = value;
    }

    /**
     * 获取samplebCaseType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSamplebCaseType() {
        return samplebCaseType;
    }

    /**
     * 设置samplebCaseType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSamplebCaseType(JAXBElement<String> value) {
        this.samplebCaseType = value;
    }

    /**
     * 获取samplebTypeName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSamplebTypeName() {
        return samplebTypeName;
    }

    /**
     * 设置samplebTypeName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSamplebTypeName(JAXBElement<String> value) {
        this.samplebTypeName = value;
    }

    /**
     * 获取samplebid属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSamplebid() {
        return samplebid;
    }

    /**
     * 设置samplebid属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSamplebid(JAXBElement<String> value) {
        this.samplebid = value;
    }

    /**
     * 获取samplebname属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSamplebname() {
        return samplebname;
    }

    /**
     * 设置samplebname属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSamplebname(JAXBElement<String> value) {
        this.samplebname = value;
    }

    /**
     * 获取samplecid属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSamplecid() {
        return samplecid;
    }

    /**
     * 设置samplecid属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSamplecid(JAXBElement<String> value) {
        this.samplecid = value;
    }

    /**
     * 获取samplecname属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSamplecname() {
        return samplecname;
    }

    /**
     * 设置samplecname属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSamplecname(JAXBElement<String> value) {
        this.samplecname = value;
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
     * 获取sourcesampletype属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSourcesampletype() {
        return sourcesampletype;
    }

    /**
     * 设置sourcesampletype属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSourcesampletype(JAXBElement<String> value) {
        this.sourcesampletype = value;
    }

    /**
     * 获取state属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getState() {
        return state;
    }

    /**
     * 设置state属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setState(JAXBElement<Integer> value) {
        this.state = value;
    }

    /**
     * 获取submitState属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getSubmitState() {
        return submitState;
    }

    /**
     * 设置submitState属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setSubmitState(JAXBElement<Integer> value) {
        this.submitState = value;
    }

    /**
     * 获取submitoperator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSubmitoperator() {
        return submitoperator;
    }

    /**
     * 设置submitoperator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSubmitoperator(JAXBElement<String> value) {
        this.submitoperator = value;
    }

    /**
     * 获取submitorg属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSubmitorg() {
        return submitorg;
    }

    /**
     * 设置submitorg属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSubmitorg(JAXBElement<String> value) {
        this.submitorg = value;
    }

    /**
     * 获取submittime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSubmittime() {
        return submittime;
    }

    /**
     * 设置submittime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSubmittime(JAXBElement<String> value) {
        this.submittime = value;
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
     * 获取targetsampletype属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTargetsampletype() {
        return targetsampletype;
    }

    /**
     * 设置targetsampletype属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTargetsampletype(JAXBElement<String> value) {
        this.targetsampletype = value;
    }

    /**
     * 获取totalMatchPossibility属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Double }{@code >}
     *     
     */
    public JAXBElement<Double> getTotalMatchPossibility() {
        return totalMatchPossibility;
    }

    /**
     * 设置totalMatchPossibility属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Double }{@code >}
     *     
     */
    public void setTotalMatchPossibility(JAXBElement<Double> value) {
        this.totalMatchPossibility = value;
    }

    /**
     * 获取transferTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTransferTime() {
        return transferTime;
    }

    /**
     * 设置transferTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTransferTime(JAXBElement<String> value) {
        this.transferTime = value;
    }

    /**
     * 获取transferStatus属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTransferStatus() {
        return transferStatus;
    }

    /**
     * 设置transferStatus属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTransferStatus(JAXBElement<String> value) {
        this.transferStatus = value;
    }

    /**
     * 获取transferor属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTransferor() {
        return transferor;
    }

    /**
     * 设置transferor属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTransferor(JAXBElement<String> value) {
        this.transferor = value;
    }

    /**
     * 获取verifytime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getVerifytime() {
        return verifytime;
    }

    /**
     * 设置verifytime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setVerifytime(JAXBElement<String> value) {
        this.verifytime = value;
    }

}
