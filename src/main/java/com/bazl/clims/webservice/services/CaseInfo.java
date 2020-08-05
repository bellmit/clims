
package com.bazl.clims.webservice.services;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>CaseInfo complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="CaseInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="acceptAtEnd" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="acceptAtStart" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="acceptCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="acceptDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="acceptOpinion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="acceptOrgCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="acceptOrgName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="acceptOrgPhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="acceptPhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="acceptor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="acceptorAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="acceptorName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="appendSn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="associateSystemName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="associateSystemSn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="caseAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="caseAddressFullName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="caseBaseId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="caseDeadline" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="caseDigest" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="caseHandleType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="caseHandleTypeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="caseHarmLevel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="caseJzSn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="caseLevel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="caseName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="caseOldCreator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="caseOldDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="caseSn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="caseSolveStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="caseStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="caseStatusMark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="caseTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="caseType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="caseTypeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="caseXkSn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="caseinfoSource" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="comments" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="commissionCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="createAt" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="creator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="credentialNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="credentialNo2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="credentialType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="credentialType2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="delegateAtEnd" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="delegateAtStart" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="delegateBookSn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="delegateDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="delegateOrgAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="delegateOrgCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="delegateOrgFaxNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="delegateOrgFullName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="delegateOrgName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="delegateOrgPhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="delegateOrgPostNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="delegatePhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="delegatePhone2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="delegateSn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="delegateTimeOut" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="delegator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="delegator2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="delegatorAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="delegatorAddress2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="delegatorCard" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="delegatorCardId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="delegatorCardId2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="delegatorName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="delegatorName2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="externalInfoType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="finishAt" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="firstChecker" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="firstCheckerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="hasAppend" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="identifyBookExist" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="identifyBookExistStr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="identifyBookUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="identifyRequired" type="{http://services.webservice.lims.dna.com}ArrayOfString" minOccurs="0"/&gt;
 *         &lt;element name="identifyRequiredStr" type="{http://services.webservice.lims.dna.com}ArrayOfString" minOccurs="0"/&gt;
 *         &lt;element name="isCaseAppend" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="isSend" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="memeberInfo" type="{http://domain.lims.viathink.com}ArrayOfMemberInfo" minOccurs="0"/&gt;
 *         &lt;element name="memo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="oldSn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="position" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="position2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="preIdentifyInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="regDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="registed" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="rejectReason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sampleCnt" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="statusName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="storedSampleCnt" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="subCaseType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="urgent" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="userName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="xk_caseProperty" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="xk_caseType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CaseInfo", namespace = "http://domain.lims.viathink.com", propOrder = {
    "acceptAtEnd",
    "acceptAtStart",
    "acceptCode",
    "acceptDate",
    "acceptOpinion",
    "acceptOrgCode",
    "acceptOrgName",
    "acceptOrgPhone",
    "acceptPhone",
    "acceptor",
    "acceptorAddress",
    "acceptorName",
    "appendSn",
    "associateSystemName",
    "associateSystemSn",
    "caseAddress",
    "caseAddressFullName",
    "caseBaseId",
    "caseDeadline",
    "caseDigest",
    "caseHandleType",
    "caseHandleTypeName",
    "caseHarmLevel",
    "caseJzSn",
    "caseLevel",
    "caseName",
    "caseOldCreator",
    "caseOldDate",
    "caseSn",
    "caseSolveStatus",
    "caseStatus",
    "caseStatusMark",
    "caseTime",
    "caseType",
    "caseTypeName",
    "caseXkSn",
    "caseinfoSource",
    "comments",
    "commissionCode",
    "createAt",
    "creator",
    "credentialNo",
    "credentialNo2",
    "credentialType",
    "credentialType2",
    "delegateAtEnd",
    "delegateAtStart",
    "delegateBookSn",
    "delegateDate",
    "delegateOrgAddress",
    "delegateOrgCode",
    "delegateOrgFaxNo",
    "delegateOrgFullName",
    "delegateOrgName",
    "delegateOrgPhone",
    "delegateOrgPostNo",
    "delegatePhone",
    "delegatePhone2",
    "delegateSn",
    "delegateTimeOut",
    "delegator",
    "delegator2",
    "delegatorAddress",
    "delegatorAddress2",
    "delegatorCard",
    "delegatorCardId",
    "delegatorCardId2",
    "delegatorName",
    "delegatorName2",
    "externalInfoType",
    "finishAt",
    "firstChecker",
    "firstCheckerName",
    "hasAppend",
    "id",
    "identifyBookExist",
    "identifyBookExistStr",
    "identifyBookUrl",
    "identifyRequired",
    "identifyRequiredStr",
    "isCaseAppend",
    "isSend",
    "memeberInfo",
    "memo",
    "oldSn",
    "position",
    "position2",
    "preIdentifyInfo",
    "regDate",
    "registed",
    "rejectReason",
    "sampleCnt",
    "status",
    "statusName",
    "storedSampleCnt",
    "subCaseType",
    "urgent",
    "userName",
    "xkCaseProperty",
    "xkCaseType"
})
public class CaseInfo {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar acceptAtEnd;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar acceptAtStart;
    @XmlElementRef(name = "acceptCode", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> acceptCode;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar acceptDate;
    @XmlElementRef(name = "acceptOpinion", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> acceptOpinion;
    @XmlElementRef(name = "acceptOrgCode", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> acceptOrgCode;
    @XmlElementRef(name = "acceptOrgName", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> acceptOrgName;
    @XmlElementRef(name = "acceptOrgPhone", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> acceptOrgPhone;
    @XmlElementRef(name = "acceptPhone", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> acceptPhone;
    @XmlElementRef(name = "acceptor", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> acceptor;
    @XmlElementRef(name = "acceptorAddress", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> acceptorAddress;
    @XmlElementRef(name = "acceptorName", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> acceptorName;
    @XmlElementRef(name = "appendSn", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> appendSn;
    @XmlElementRef(name = "associateSystemName", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> associateSystemName;
    @XmlElementRef(name = "associateSystemSn", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> associateSystemSn;
    @XmlElementRef(name = "caseAddress", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> caseAddress;
    @XmlElementRef(name = "caseAddressFullName", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> caseAddressFullName;
    @XmlElementRef(name = "caseBaseId", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> caseBaseId;
    @XmlElementRef(name = "caseDeadline", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> caseDeadline;
    @XmlElementRef(name = "caseDigest", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> caseDigest;
    @XmlElementRef(name = "caseHandleType", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> caseHandleType;
    @XmlElementRef(name = "caseHandleTypeName", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> caseHandleTypeName;
    @XmlElementRef(name = "caseHarmLevel", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> caseHarmLevel;
    @XmlElementRef(name = "caseJzSn", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> caseJzSn;
    @XmlElementRef(name = "caseLevel", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> caseLevel;
    @XmlElementRef(name = "caseName", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> caseName;
    @XmlElementRef(name = "caseOldCreator", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> caseOldCreator;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar caseOldDate;
    @XmlElementRef(name = "caseSn", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> caseSn;
    @XmlElementRef(name = "caseSolveStatus", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> caseSolveStatus;
    @XmlElementRef(name = "caseStatus", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> caseStatus;
    @XmlElementRef(name = "caseStatusMark", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> caseStatusMark;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar caseTime;
    @XmlElementRef(name = "caseType", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> caseType;
    @XmlElementRef(name = "caseTypeName", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> caseTypeName;
    @XmlElementRef(name = "caseXkSn", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> caseXkSn;
    @XmlElementRef(name = "caseinfoSource", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> caseinfoSource;
    @XmlElementRef(name = "comments", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> comments;
    @XmlElementRef(name = "commissionCode", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> commissionCode;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createAt;
    @XmlElementRef(name = "creator", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> creator;
    @XmlElementRef(name = "credentialNo", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> credentialNo;
    @XmlElementRef(name = "credentialNo2", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> credentialNo2;
    @XmlElementRef(name = "credentialType", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> credentialType;
    @XmlElementRef(name = "credentialType2", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> credentialType2;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar delegateAtEnd;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar delegateAtStart;
    @XmlElementRef(name = "delegateBookSn", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> delegateBookSn;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar delegateDate;
    @XmlElementRef(name = "delegateOrgAddress", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> delegateOrgAddress;
    @XmlElementRef(name = "delegateOrgCode", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> delegateOrgCode;
    @XmlElementRef(name = "delegateOrgFaxNo", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> delegateOrgFaxNo;
    @XmlElementRef(name = "delegateOrgFullName", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> delegateOrgFullName;
    @XmlElementRef(name = "delegateOrgName", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> delegateOrgName;
    @XmlElementRef(name = "delegateOrgPhone", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> delegateOrgPhone;
    @XmlElementRef(name = "delegateOrgPostNo", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> delegateOrgPostNo;
    @XmlElementRef(name = "delegatePhone", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> delegatePhone;
    @XmlElementRef(name = "delegatePhone2", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> delegatePhone2;
    @XmlElementRef(name = "delegateSn", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> delegateSn;
    @XmlElementRef(name = "delegateTimeOut", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> delegateTimeOut;
    @XmlElementRef(name = "delegator", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> delegator;
    @XmlElementRef(name = "delegator2", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> delegator2;
    @XmlElementRef(name = "delegatorAddress", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> delegatorAddress;
    @XmlElementRef(name = "delegatorAddress2", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> delegatorAddress2;
    @XmlElementRef(name = "delegatorCard", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> delegatorCard;
    @XmlElementRef(name = "delegatorCardId", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> delegatorCardId;
    @XmlElementRef(name = "delegatorCardId2", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> delegatorCardId2;
    @XmlElementRef(name = "delegatorName", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> delegatorName;
    @XmlElementRef(name = "delegatorName2", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> delegatorName2;
    @XmlElementRef(name = "externalInfoType", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> externalInfoType;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar finishAt;
    @XmlElementRef(name = "firstChecker", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> firstChecker;
    @XmlElementRef(name = "firstCheckerName", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> firstCheckerName;
    protected Integer hasAppend;
    @XmlElementRef(name = "id", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> id;
    @XmlElementRef(name = "identifyBookExist", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> identifyBookExist;
    @XmlElementRef(name = "identifyBookExistStr", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> identifyBookExistStr;
    @XmlElementRef(name = "identifyBookUrl", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> identifyBookUrl;
    @XmlElementRef(name = "identifyRequired", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfString> identifyRequired;
    @XmlElementRef(name = "identifyRequiredStr", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfString> identifyRequiredStr;
    protected Integer isCaseAppend;
    protected Integer isSend;
    @XmlElementRef(name = "memeberInfo", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfMemberInfo> memeberInfo;
    @XmlElementRef(name = "memo", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> memo;
    @XmlElementRef(name = "oldSn", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> oldSn;
    @XmlElementRef(name = "position", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> position;
    @XmlElementRef(name = "position2", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> position2;
    @XmlElementRef(name = "preIdentifyInfo", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> preIdentifyInfo;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar regDate;
    @XmlElementRef(name = "registed", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> registed;
    @XmlElementRef(name = "rejectReason", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> rejectReason;
    protected Integer sampleCnt;
    @XmlElementRef(name = "status", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> status;
    @XmlElementRef(name = "statusName", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> statusName;
    protected Integer storedSampleCnt;
    @XmlElementRef(name = "subCaseType", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> subCaseType;
    protected Integer urgent;
    @XmlElementRef(name = "userName", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> userName;
    @XmlElementRef(name = "xk_caseProperty", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> xkCaseProperty;
    @XmlElementRef(name = "xk_caseType", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> xkCaseType;

    /**
     * 获取acceptAtEnd属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAcceptAtEnd() {
        return acceptAtEnd;
    }

    /**
     * 设置acceptAtEnd属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAcceptAtEnd(XMLGregorianCalendar value) {
        this.acceptAtEnd = value;
    }

    /**
     * 获取acceptAtStart属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAcceptAtStart() {
        return acceptAtStart;
    }

    /**
     * 设置acceptAtStart属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAcceptAtStart(XMLGregorianCalendar value) {
        this.acceptAtStart = value;
    }

    /**
     * 获取acceptCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAcceptCode() {
        return acceptCode;
    }

    /**
     * 设置acceptCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAcceptCode(JAXBElement<String> value) {
        this.acceptCode = value;
    }

    /**
     * 获取acceptDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAcceptDate() {
        return acceptDate;
    }

    /**
     * 设置acceptDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAcceptDate(XMLGregorianCalendar value) {
        this.acceptDate = value;
    }

    /**
     * 获取acceptOpinion属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAcceptOpinion() {
        return acceptOpinion;
    }

    /**
     * 设置acceptOpinion属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAcceptOpinion(JAXBElement<String> value) {
        this.acceptOpinion = value;
    }

    /**
     * 获取acceptOrgCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAcceptOrgCode() {
        return acceptOrgCode;
    }

    /**
     * 设置acceptOrgCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAcceptOrgCode(JAXBElement<String> value) {
        this.acceptOrgCode = value;
    }

    /**
     * 获取acceptOrgName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAcceptOrgName() {
        return acceptOrgName;
    }

    /**
     * 设置acceptOrgName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAcceptOrgName(JAXBElement<String> value) {
        this.acceptOrgName = value;
    }

    /**
     * 获取acceptOrgPhone属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAcceptOrgPhone() {
        return acceptOrgPhone;
    }

    /**
     * 设置acceptOrgPhone属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAcceptOrgPhone(JAXBElement<String> value) {
        this.acceptOrgPhone = value;
    }

    /**
     * 获取acceptPhone属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAcceptPhone() {
        return acceptPhone;
    }

    /**
     * 设置acceptPhone属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAcceptPhone(JAXBElement<String> value) {
        this.acceptPhone = value;
    }

    /**
     * 获取acceptor属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAcceptor() {
        return acceptor;
    }

    /**
     * 设置acceptor属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAcceptor(JAXBElement<String> value) {
        this.acceptor = value;
    }

    /**
     * 获取acceptorAddress属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAcceptorAddress() {
        return acceptorAddress;
    }

    /**
     * 设置acceptorAddress属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAcceptorAddress(JAXBElement<String> value) {
        this.acceptorAddress = value;
    }

    /**
     * 获取acceptorName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAcceptorName() {
        return acceptorName;
    }

    /**
     * 设置acceptorName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAcceptorName(JAXBElement<String> value) {
        this.acceptorName = value;
    }

    /**
     * 获取appendSn属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAppendSn() {
        return appendSn;
    }

    /**
     * 设置appendSn属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAppendSn(JAXBElement<String> value) {
        this.appendSn = value;
    }

    /**
     * 获取associateSystemName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAssociateSystemName() {
        return associateSystemName;
    }

    /**
     * 设置associateSystemName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAssociateSystemName(JAXBElement<String> value) {
        this.associateSystemName = value;
    }

    /**
     * 获取associateSystemSn属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAssociateSystemSn() {
        return associateSystemSn;
    }

    /**
     * 设置associateSystemSn属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAssociateSystemSn(JAXBElement<String> value) {
        this.associateSystemSn = value;
    }

    /**
     * 获取caseAddress属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCaseAddress() {
        return caseAddress;
    }

    /**
     * 设置caseAddress属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCaseAddress(JAXBElement<String> value) {
        this.caseAddress = value;
    }

    /**
     * 获取caseAddressFullName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCaseAddressFullName() {
        return caseAddressFullName;
    }

    /**
     * 设置caseAddressFullName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCaseAddressFullName(JAXBElement<String> value) {
        this.caseAddressFullName = value;
    }

    /**
     * 获取caseBaseId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCaseBaseId() {
        return caseBaseId;
    }

    /**
     * 设置caseBaseId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCaseBaseId(JAXBElement<String> value) {
        this.caseBaseId = value;
    }

    /**
     * 获取caseDeadline属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCaseDeadline() {
        return caseDeadline;
    }

    /**
     * 设置caseDeadline属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCaseDeadline(JAXBElement<String> value) {
        this.caseDeadline = value;
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
     * 获取caseHandleTypeName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCaseHandleTypeName() {
        return caseHandleTypeName;
    }

    /**
     * 设置caseHandleTypeName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCaseHandleTypeName(JAXBElement<String> value) {
        this.caseHandleTypeName = value;
    }

    /**
     * 获取caseHarmLevel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCaseHarmLevel() {
        return caseHarmLevel;
    }

    /**
     * 设置caseHarmLevel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCaseHarmLevel(JAXBElement<String> value) {
        this.caseHarmLevel = value;
    }

    /**
     * 获取caseJzSn属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCaseJzSn() {
        return caseJzSn;
    }

    /**
     * 设置caseJzSn属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCaseJzSn(JAXBElement<String> value) {
        this.caseJzSn = value;
    }

    /**
     * 获取caseLevel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCaseLevel() {
        return caseLevel;
    }

    /**
     * 设置caseLevel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCaseLevel(JAXBElement<String> value) {
        this.caseLevel = value;
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
     * 获取caseOldCreator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCaseOldCreator() {
        return caseOldCreator;
    }

    /**
     * 设置caseOldCreator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCaseOldCreator(JAXBElement<String> value) {
        this.caseOldCreator = value;
    }

    /**
     * 获取caseOldDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCaseOldDate() {
        return caseOldDate;
    }

    /**
     * 设置caseOldDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCaseOldDate(XMLGregorianCalendar value) {
        this.caseOldDate = value;
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
     * 获取caseSolveStatus属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCaseSolveStatus() {
        return caseSolveStatus;
    }

    /**
     * 设置caseSolveStatus属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCaseSolveStatus(JAXBElement<String> value) {
        this.caseSolveStatus = value;
    }

    /**
     * 获取caseStatus属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCaseStatus() {
        return caseStatus;
    }

    /**
     * 设置caseStatus属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCaseStatus(JAXBElement<String> value) {
        this.caseStatus = value;
    }

    /**
     * 获取caseStatusMark属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCaseStatusMark() {
        return caseStatusMark;
    }

    /**
     * 设置caseStatusMark属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCaseStatusMark(JAXBElement<String> value) {
        this.caseStatusMark = value;
    }

    /**
     * 获取caseTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCaseTime() {
        return caseTime;
    }

    /**
     * 设置caseTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCaseTime(XMLGregorianCalendar value) {
        this.caseTime = value;
    }

    /**
     * 获取caseType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCaseType() {
        return caseType;
    }

    /**
     * 设置caseType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCaseType(JAXBElement<String> value) {
        this.caseType = value;
    }

    /**
     * 获取caseTypeName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCaseTypeName() {
        return caseTypeName;
    }

    /**
     * 设置caseTypeName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCaseTypeName(JAXBElement<String> value) {
        this.caseTypeName = value;
    }

    /**
     * 获取caseXkSn属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCaseXkSn() {
        return caseXkSn;
    }

    /**
     * 设置caseXkSn属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCaseXkSn(JAXBElement<String> value) {
        this.caseXkSn = value;
    }

    /**
     * 获取caseinfoSource属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCaseinfoSource() {
        return caseinfoSource;
    }

    /**
     * 设置caseinfoSource属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCaseinfoSource(JAXBElement<String> value) {
        this.caseinfoSource = value;
    }

    /**
     * 获取comments属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getComments() {
        return comments;
    }

    /**
     * 设置comments属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setComments(JAXBElement<String> value) {
        this.comments = value;
    }

    /**
     * 获取commissionCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCommissionCode() {
        return commissionCode;
    }

    /**
     * 设置commissionCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCommissionCode(JAXBElement<String> value) {
        this.commissionCode = value;
    }

    /**
     * 获取createAt属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreateAt() {
        return createAt;
    }

    /**
     * 设置createAt属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreateAt(XMLGregorianCalendar value) {
        this.createAt = value;
    }

    /**
     * 获取creator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCreator() {
        return creator;
    }

    /**
     * 设置creator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCreator(JAXBElement<String> value) {
        this.creator = value;
    }

    /**
     * 获取credentialNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCredentialNo() {
        return credentialNo;
    }

    /**
     * 设置credentialNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCredentialNo(JAXBElement<String> value) {
        this.credentialNo = value;
    }

    /**
     * 获取credentialNo2属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCredentialNo2() {
        return credentialNo2;
    }

    /**
     * 设置credentialNo2属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCredentialNo2(JAXBElement<String> value) {
        this.credentialNo2 = value;
    }

    /**
     * 获取credentialType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCredentialType() {
        return credentialType;
    }

    /**
     * 设置credentialType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCredentialType(JAXBElement<String> value) {
        this.credentialType = value;
    }

    /**
     * 获取credentialType2属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCredentialType2() {
        return credentialType2;
    }

    /**
     * 设置credentialType2属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCredentialType2(JAXBElement<String> value) {
        this.credentialType2 = value;
    }

    /**
     * 获取delegateAtEnd属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDelegateAtEnd() {
        return delegateAtEnd;
    }

    /**
     * 设置delegateAtEnd属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDelegateAtEnd(XMLGregorianCalendar value) {
        this.delegateAtEnd = value;
    }

    /**
     * 获取delegateAtStart属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDelegateAtStart() {
        return delegateAtStart;
    }

    /**
     * 设置delegateAtStart属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDelegateAtStart(XMLGregorianCalendar value) {
        this.delegateAtStart = value;
    }

    /**
     * 获取delegateBookSn属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDelegateBookSn() {
        return delegateBookSn;
    }

    /**
     * 设置delegateBookSn属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDelegateBookSn(JAXBElement<String> value) {
        this.delegateBookSn = value;
    }

    /**
     * 获取delegateDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDelegateDate() {
        return delegateDate;
    }

    /**
     * 设置delegateDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDelegateDate(XMLGregorianCalendar value) {
        this.delegateDate = value;
    }

    /**
     * 获取delegateOrgAddress属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDelegateOrgAddress() {
        return delegateOrgAddress;
    }

    /**
     * 设置delegateOrgAddress属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDelegateOrgAddress(JAXBElement<String> value) {
        this.delegateOrgAddress = value;
    }

    /**
     * 获取delegateOrgCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDelegateOrgCode() {
        return delegateOrgCode;
    }

    /**
     * 设置delegateOrgCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDelegateOrgCode(JAXBElement<String> value) {
        this.delegateOrgCode = value;
    }

    /**
     * 获取delegateOrgFaxNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDelegateOrgFaxNo() {
        return delegateOrgFaxNo;
    }

    /**
     * 设置delegateOrgFaxNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDelegateOrgFaxNo(JAXBElement<String> value) {
        this.delegateOrgFaxNo = value;
    }

    /**
     * 获取delegateOrgFullName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDelegateOrgFullName() {
        return delegateOrgFullName;
    }

    /**
     * 设置delegateOrgFullName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDelegateOrgFullName(JAXBElement<String> value) {
        this.delegateOrgFullName = value;
    }

    /**
     * 获取delegateOrgName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDelegateOrgName() {
        return delegateOrgName;
    }

    /**
     * 设置delegateOrgName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDelegateOrgName(JAXBElement<String> value) {
        this.delegateOrgName = value;
    }

    /**
     * 获取delegateOrgPhone属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDelegateOrgPhone() {
        return delegateOrgPhone;
    }

    /**
     * 设置delegateOrgPhone属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDelegateOrgPhone(JAXBElement<String> value) {
        this.delegateOrgPhone = value;
    }

    /**
     * 获取delegateOrgPostNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDelegateOrgPostNo() {
        return delegateOrgPostNo;
    }

    /**
     * 设置delegateOrgPostNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDelegateOrgPostNo(JAXBElement<String> value) {
        this.delegateOrgPostNo = value;
    }

    /**
     * 获取delegatePhone属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDelegatePhone() {
        return delegatePhone;
    }

    /**
     * 设置delegatePhone属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDelegatePhone(JAXBElement<String> value) {
        this.delegatePhone = value;
    }

    /**
     * 获取delegatePhone2属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDelegatePhone2() {
        return delegatePhone2;
    }

    /**
     * 设置delegatePhone2属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDelegatePhone2(JAXBElement<String> value) {
        this.delegatePhone2 = value;
    }

    /**
     * 获取delegateSn属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDelegateSn() {
        return delegateSn;
    }

    /**
     * 设置delegateSn属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDelegateSn(JAXBElement<String> value) {
        this.delegateSn = value;
    }

    /**
     * 获取delegateTimeOut属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDelegateTimeOut() {
        return delegateTimeOut;
    }

    /**
     * 设置delegateTimeOut属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDelegateTimeOut(JAXBElement<String> value) {
        this.delegateTimeOut = value;
    }

    /**
     * 获取delegator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDelegator() {
        return delegator;
    }

    /**
     * 设置delegator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDelegator(JAXBElement<String> value) {
        this.delegator = value;
    }

    /**
     * 获取delegator2属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDelegator2() {
        return delegator2;
    }

    /**
     * 设置delegator2属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDelegator2(JAXBElement<String> value) {
        this.delegator2 = value;
    }

    /**
     * 获取delegatorAddress属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDelegatorAddress() {
        return delegatorAddress;
    }

    /**
     * 设置delegatorAddress属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDelegatorAddress(JAXBElement<String> value) {
        this.delegatorAddress = value;
    }

    /**
     * 获取delegatorAddress2属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDelegatorAddress2() {
        return delegatorAddress2;
    }

    /**
     * 设置delegatorAddress2属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDelegatorAddress2(JAXBElement<String> value) {
        this.delegatorAddress2 = value;
    }

    /**
     * 获取delegatorCard属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDelegatorCard() {
        return delegatorCard;
    }

    /**
     * 设置delegatorCard属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDelegatorCard(JAXBElement<String> value) {
        this.delegatorCard = value;
    }

    /**
     * 获取delegatorCardId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDelegatorCardId() {
        return delegatorCardId;
    }

    /**
     * 设置delegatorCardId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDelegatorCardId(JAXBElement<String> value) {
        this.delegatorCardId = value;
    }

    /**
     * 获取delegatorCardId2属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDelegatorCardId2() {
        return delegatorCardId2;
    }

    /**
     * 设置delegatorCardId2属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDelegatorCardId2(JAXBElement<String> value) {
        this.delegatorCardId2 = value;
    }

    /**
     * 获取delegatorName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDelegatorName() {
        return delegatorName;
    }

    /**
     * 设置delegatorName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDelegatorName(JAXBElement<String> value) {
        this.delegatorName = value;
    }

    /**
     * 获取delegatorName2属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDelegatorName2() {
        return delegatorName2;
    }

    /**
     * 设置delegatorName2属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDelegatorName2(JAXBElement<String> value) {
        this.delegatorName2 = value;
    }

    /**
     * 获取externalInfoType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getExternalInfoType() {
        return externalInfoType;
    }

    /**
     * 设置externalInfoType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setExternalInfoType(JAXBElement<String> value) {
        this.externalInfoType = value;
    }

    /**
     * 获取finishAt属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFinishAt() {
        return finishAt;
    }

    /**
     * 设置finishAt属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFinishAt(XMLGregorianCalendar value) {
        this.finishAt = value;
    }

    /**
     * 获取firstChecker属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFirstChecker() {
        return firstChecker;
    }

    /**
     * 设置firstChecker属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFirstChecker(JAXBElement<String> value) {
        this.firstChecker = value;
    }

    /**
     * 获取firstCheckerName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFirstCheckerName() {
        return firstCheckerName;
    }

    /**
     * 设置firstCheckerName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFirstCheckerName(JAXBElement<String> value) {
        this.firstCheckerName = value;
    }

    /**
     * 获取hasAppend属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getHasAppend() {
        return hasAppend;
    }

    /**
     * 设置hasAppend属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setHasAppend(Integer value) {
        this.hasAppend = value;
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
     * 获取identifyBookExist属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIdentifyBookExist() {
        return identifyBookExist;
    }

    /**
     * 设置identifyBookExist属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIdentifyBookExist(JAXBElement<String> value) {
        this.identifyBookExist = value;
    }

    /**
     * 获取identifyBookExistStr属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIdentifyBookExistStr() {
        return identifyBookExistStr;
    }

    /**
     * 设置identifyBookExistStr属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIdentifyBookExistStr(JAXBElement<String> value) {
        this.identifyBookExistStr = value;
    }

    /**
     * 获取identifyBookUrl属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIdentifyBookUrl() {
        return identifyBookUrl;
    }

    /**
     * 设置identifyBookUrl属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIdentifyBookUrl(JAXBElement<String> value) {
        this.identifyBookUrl = value;
    }

    /**
     * 获取identifyRequired属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfString }{@code >}
     *     
     */
    public JAXBElement<ArrayOfString> getIdentifyRequired() {
        return identifyRequired;
    }

    /**
     * 设置identifyRequired属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfString }{@code >}
     *     
     */
    public void setIdentifyRequired(JAXBElement<ArrayOfString> value) {
        this.identifyRequired = value;
    }

    /**
     * 获取identifyRequiredStr属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfString }{@code >}
     *     
     */
    public JAXBElement<ArrayOfString> getIdentifyRequiredStr() {
        return identifyRequiredStr;
    }

    /**
     * 设置identifyRequiredStr属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfString }{@code >}
     *     
     */
    public void setIdentifyRequiredStr(JAXBElement<ArrayOfString> value) {
        this.identifyRequiredStr = value;
    }

    /**
     * 获取isCaseAppend属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIsCaseAppend() {
        return isCaseAppend;
    }

    /**
     * 设置isCaseAppend属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIsCaseAppend(Integer value) {
        this.isCaseAppend = value;
    }

    /**
     * 获取isSend属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIsSend() {
        return isSend;
    }

    /**
     * 设置isSend属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIsSend(Integer value) {
        this.isSend = value;
    }

    /**
     * 获取memeberInfo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfMemberInfo }{@code >}
     *     
     */
    public JAXBElement<ArrayOfMemberInfo> getMemeberInfo() {
        return memeberInfo;
    }

    /**
     * 设置memeberInfo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfMemberInfo }{@code >}
     *     
     */
    public void setMemeberInfo(JAXBElement<ArrayOfMemberInfo> value) {
        this.memeberInfo = value;
    }

    /**
     * 获取memo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMemo() {
        return memo;
    }

    /**
     * 设置memo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMemo(JAXBElement<String> value) {
        this.memo = value;
    }

    /**
     * 获取oldSn属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOldSn() {
        return oldSn;
    }

    /**
     * 设置oldSn属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOldSn(JAXBElement<String> value) {
        this.oldSn = value;
    }

    /**
     * 获取position属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPosition() {
        return position;
    }

    /**
     * 设置position属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPosition(JAXBElement<String> value) {
        this.position = value;
    }

    /**
     * 获取position2属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPosition2() {
        return position2;
    }

    /**
     * 设置position2属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPosition2(JAXBElement<String> value) {
        this.position2 = value;
    }

    /**
     * 获取preIdentifyInfo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPreIdentifyInfo() {
        return preIdentifyInfo;
    }

    /**
     * 设置preIdentifyInfo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPreIdentifyInfo(JAXBElement<String> value) {
        this.preIdentifyInfo = value;
    }

    /**
     * 获取regDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRegDate() {
        return regDate;
    }

    /**
     * 设置regDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRegDate(XMLGregorianCalendar value) {
        this.regDate = value;
    }

    /**
     * 获取registed属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRegisted() {
        return registed;
    }

    /**
     * 设置registed属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRegisted(JAXBElement<String> value) {
        this.registed = value;
    }

    /**
     * 获取rejectReason属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRejectReason() {
        return rejectReason;
    }

    /**
     * 设置rejectReason属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRejectReason(JAXBElement<String> value) {
        this.rejectReason = value;
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
     * 获取statusName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getStatusName() {
        return statusName;
    }

    /**
     * 设置statusName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setStatusName(JAXBElement<String> value) {
        this.statusName = value;
    }

    /**
     * 获取storedSampleCnt属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getStoredSampleCnt() {
        return storedSampleCnt;
    }

    /**
     * 设置storedSampleCnt属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setStoredSampleCnt(Integer value) {
        this.storedSampleCnt = value;
    }

    /**
     * 获取subCaseType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSubCaseType() {
        return subCaseType;
    }

    /**
     * 设置subCaseType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSubCaseType(JAXBElement<String> value) {
        this.subCaseType = value;
    }

    /**
     * 获取urgent属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getUrgent() {
        return urgent;
    }

    /**
     * 设置urgent属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setUrgent(Integer value) {
        this.urgent = value;
    }

    /**
     * 获取userName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getUserName() {
        return userName;
    }

    /**
     * 设置userName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setUserName(JAXBElement<String> value) {
        this.userName = value;
    }

    /**
     * 获取xkCaseProperty属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getXkCaseProperty() {
        return xkCaseProperty;
    }

    /**
     * 设置xkCaseProperty属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setXkCaseProperty(JAXBElement<String> value) {
        this.xkCaseProperty = value;
    }

    /**
     * 获取xkCaseType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getXkCaseType() {
        return xkCaseType;
    }

    /**
     * 设置xkCaseType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setXkCaseType(JAXBElement<String> value) {
        this.xkCaseType = value;
    }

}
