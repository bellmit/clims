
package com.bazl.clims.webservice.services;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>SampleInfo complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="SampleInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="acceptStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="acceptStatusName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="appendSn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="bloodSamplePosition" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="boardCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="cabinetNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="carrier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="carrierCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="carrierName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="caseAppendSn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="caseBaseId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="caseHandleType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="caseId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="caseName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="caseSn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="collectPlace" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="columnNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="comments" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="deliverFrom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="deliverStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="deliverStatusName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="diffculty" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="drawerNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="expectedReceiver" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="extractDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="extractMethodCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="extractMethodName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="extractPosition" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="extractor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="extractorPhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="flag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="important" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="isCenterSE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="isDeleted" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="isFTA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="isSampleRepeat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="isStore" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="levelflag" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="moreDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="operateStepCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="operator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="operatorTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="packMethod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="pcrBoardCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="pcrBoardHoleCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="pcrable" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="picture" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="preBarcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="refMemberId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="rejectReason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="rn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sampleBarcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sampleDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sampleName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sampleNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sampleRelation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sampleStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sampleStatusMark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sampleTypeAddInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sampleTypeAddInfoStr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sampleTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sampleTypeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="serialNo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="slotNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="stepCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="testDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="testPurpose" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tester" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="urgent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="warn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="xkAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="xkDelegateSN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="xkNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SampleInfo", namespace = "http://domain.lims.viathink.com", propOrder = {
    "acceptStatus",
    "acceptStatusName",
    "appendSn",
    "bloodSamplePosition",
    "boardCode",
    "cabinetNo",
    "carrier",
    "carrierCode",
    "carrierName",
    "caseAppendSn",
    "caseBaseId",
    "caseHandleType",
    "caseId",
    "caseName",
    "caseSn",
    "collectPlace",
    "columnNo",
    "comments",
    "deliverFrom",
    "deliverStatus",
    "deliverStatusName",
    "diffculty",
    "drawerNo",
    "expectedReceiver",
    "extractDate",
    "extractMethodCode",
    "extractMethodName",
    "extractPosition",
    "extractor",
    "extractorPhone",
    "flag",
    "id",
    "important",
    "isCenterSE",
    "isDeleted",
    "isFTA",
    "isSampleRepeat",
    "isStore",
    "levelflag",
    "moreDesc",
    "operateStepCode",
    "operator",
    "operatorTime",
    "packMethod",
    "pcrBoardCode",
    "pcrBoardHoleCode",
    "pcrable",
    "picture",
    "preBarcode",
    "refMemberId",
    "rejectReason",
    "rn",
    "sampleBarcode",
    "sampleDescription",
    "sampleName",
    "sampleNo",
    "sampleRelation",
    "sampleStatus",
    "sampleStatusMark",
    "sampleTypeAddInfo",
    "sampleTypeAddInfoStr",
    "sampleTypeCode",
    "sampleTypeName",
    "serialNo",
    "slotNo",
    "stepCode",
    "testDesc",
    "testPurpose",
    "tester",
    "urgent",
    "warn",
    "xkAddress",
    "xkDelegateSN",
    "xkNumber"
})
public class SampleInfo {

    @XmlElementRef(name = "acceptStatus", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> acceptStatus;
    @XmlElementRef(name = "acceptStatusName", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> acceptStatusName;
    @XmlElementRef(name = "appendSn", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> appendSn;
    @XmlElementRef(name = "bloodSamplePosition", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> bloodSamplePosition;
    @XmlElementRef(name = "boardCode", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> boardCode;
    @XmlElementRef(name = "cabinetNo", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> cabinetNo;
    @XmlElementRef(name = "carrier", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> carrier;
    @XmlElementRef(name = "carrierCode", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> carrierCode;
    @XmlElementRef(name = "carrierName", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> carrierName;
    @XmlElementRef(name = "caseAppendSn", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> caseAppendSn;
    @XmlElementRef(name = "caseBaseId", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> caseBaseId;
    @XmlElementRef(name = "caseHandleType", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> caseHandleType;
    @XmlElementRef(name = "caseId", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> caseId;
    @XmlElementRef(name = "caseName", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> caseName;
    @XmlElementRef(name = "caseSn", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> caseSn;
    @XmlElementRef(name = "collectPlace", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> collectPlace;
    @XmlElementRef(name = "columnNo", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> columnNo;
    @XmlElementRef(name = "comments", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> comments;
    @XmlElementRef(name = "deliverFrom", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> deliverFrom;
    @XmlElementRef(name = "deliverStatus", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> deliverStatus;
    @XmlElementRef(name = "deliverStatusName", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> deliverStatusName;
    @XmlElementRef(name = "diffculty", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> diffculty;
    @XmlElementRef(name = "drawerNo", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> drawerNo;
    @XmlElementRef(name = "expectedReceiver", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> expectedReceiver;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar extractDate;
    @XmlElementRef(name = "extractMethodCode", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> extractMethodCode;
    @XmlElementRef(name = "extractMethodName", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> extractMethodName;
    @XmlElementRef(name = "extractPosition", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> extractPosition;
    @XmlElementRef(name = "extractor", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> extractor;
    @XmlElementRef(name = "extractorPhone", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> extractorPhone;
    @XmlElementRef(name = "flag", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> flag;
    @XmlElementRef(name = "id", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> id;
    @XmlElementRef(name = "important", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> important;
    @XmlElementRef(name = "isCenterSE", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> isCenterSE;
    @XmlElementRef(name = "isDeleted", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> isDeleted;
    @XmlElementRef(name = "isFTA", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> isFTA;
    @XmlElementRef(name = "isSampleRepeat", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> isSampleRepeat;
    @XmlElementRef(name = "isStore", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> isStore;
    protected Integer levelflag;
    @XmlElementRef(name = "moreDesc", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> moreDesc;
    @XmlElementRef(name = "operateStepCode", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> operateStepCode;
    @XmlElementRef(name = "operator", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> operator;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar operatorTime;
    @XmlElementRef(name = "packMethod", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> packMethod;
    @XmlElementRef(name = "pcrBoardCode", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> pcrBoardCode;
    @XmlElementRef(name = "pcrBoardHoleCode", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> pcrBoardHoleCode;
    @XmlElementRef(name = "pcrable", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> pcrable;
    @XmlElementRef(name = "picture", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> picture;
    @XmlElementRef(name = "preBarcode", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> preBarcode;
    @XmlElementRef(name = "refMemberId", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> refMemberId;
    @XmlElementRef(name = "rejectReason", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> rejectReason;
    @XmlElementRef(name = "rn", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> rn;
    @XmlElementRef(name = "sampleBarcode", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> sampleBarcode;
    @XmlElementRef(name = "sampleDescription", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> sampleDescription;
    @XmlElementRef(name = "sampleName", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> sampleName;
    @XmlElementRef(name = "sampleNo", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> sampleNo;
    @XmlElementRef(name = "sampleRelation", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> sampleRelation;
    @XmlElementRef(name = "sampleStatus", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> sampleStatus;
    @XmlElementRef(name = "sampleStatusMark", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> sampleStatusMark;
    @XmlElementRef(name = "sampleTypeAddInfo", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> sampleTypeAddInfo;
    @XmlElementRef(name = "sampleTypeAddInfoStr", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> sampleTypeAddInfoStr;
    @XmlElementRef(name = "sampleTypeCode", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> sampleTypeCode;
    @XmlElementRef(name = "sampleTypeName", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> sampleTypeName;
    protected Integer serialNo;
    @XmlElementRef(name = "slotNo", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> slotNo;
    @XmlElementRef(name = "stepCode", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> stepCode;
    @XmlElementRef(name = "testDesc", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> testDesc;
    @XmlElementRef(name = "testPurpose", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> testPurpose;
    @XmlElementRef(name = "tester", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> tester;
    @XmlElementRef(name = "urgent", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> urgent;
    @XmlElementRef(name = "warn", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> warn;
    @XmlElementRef(name = "xkAddress", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> xkAddress;
    @XmlElementRef(name = "xkDelegateSN", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> xkDelegateSN;
    @XmlElementRef(name = "xkNumber", namespace = "http://domain.lims.viathink.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> xkNumber;

    /**
     * 获取acceptStatus属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAcceptStatus() {
        return acceptStatus;
    }

    /**
     * 设置acceptStatus属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAcceptStatus(JAXBElement<String> value) {
        this.acceptStatus = value;
    }

    /**
     * 获取acceptStatusName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAcceptStatusName() {
        return acceptStatusName;
    }

    /**
     * 设置acceptStatusName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAcceptStatusName(JAXBElement<String> value) {
        this.acceptStatusName = value;
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
     * 获取bloodSamplePosition属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getBloodSamplePosition() {
        return bloodSamplePosition;
    }

    /**
     * 设置bloodSamplePosition属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setBloodSamplePosition(JAXBElement<String> value) {
        this.bloodSamplePosition = value;
    }

    /**
     * 获取boardCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getBoardCode() {
        return boardCode;
    }

    /**
     * 设置boardCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setBoardCode(JAXBElement<String> value) {
        this.boardCode = value;
    }

    /**
     * 获取cabinetNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCabinetNo() {
        return cabinetNo;
    }

    /**
     * 设置cabinetNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCabinetNo(JAXBElement<String> value) {
        this.cabinetNo = value;
    }

    /**
     * 获取carrier属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCarrier() {
        return carrier;
    }

    /**
     * 设置carrier属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCarrier(JAXBElement<String> value) {
        this.carrier = value;
    }

    /**
     * 获取carrierCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCarrierCode() {
        return carrierCode;
    }

    /**
     * 设置carrierCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCarrierCode(JAXBElement<String> value) {
        this.carrierCode = value;
    }

    /**
     * 获取carrierName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCarrierName() {
        return carrierName;
    }

    /**
     * 设置carrierName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCarrierName(JAXBElement<String> value) {
        this.carrierName = value;
    }

    /**
     * 获取caseAppendSn属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCaseAppendSn() {
        return caseAppendSn;
    }

    /**
     * 设置caseAppendSn属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCaseAppendSn(JAXBElement<String> value) {
        this.caseAppendSn = value;
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
     * 获取collectPlace属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCollectPlace() {
        return collectPlace;
    }

    /**
     * 设置collectPlace属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCollectPlace(JAXBElement<String> value) {
        this.collectPlace = value;
    }

    /**
     * 获取columnNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getColumnNo() {
        return columnNo;
    }

    /**
     * 设置columnNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setColumnNo(JAXBElement<String> value) {
        this.columnNo = value;
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
     * 获取deliverFrom属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDeliverFrom() {
        return deliverFrom;
    }

    /**
     * 设置deliverFrom属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDeliverFrom(JAXBElement<String> value) {
        this.deliverFrom = value;
    }

    /**
     * 获取deliverStatus属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDeliverStatus() {
        return deliverStatus;
    }

    /**
     * 设置deliverStatus属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDeliverStatus(JAXBElement<String> value) {
        this.deliverStatus = value;
    }

    /**
     * 获取deliverStatusName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDeliverStatusName() {
        return deliverStatusName;
    }

    /**
     * 设置deliverStatusName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDeliverStatusName(JAXBElement<String> value) {
        this.deliverStatusName = value;
    }

    /**
     * 获取diffculty属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDiffculty() {
        return diffculty;
    }

    /**
     * 设置diffculty属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDiffculty(JAXBElement<String> value) {
        this.diffculty = value;
    }

    /**
     * 获取drawerNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDrawerNo() {
        return drawerNo;
    }

    /**
     * 设置drawerNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDrawerNo(JAXBElement<String> value) {
        this.drawerNo = value;
    }

    /**
     * 获取expectedReceiver属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getExpectedReceiver() {
        return expectedReceiver;
    }

    /**
     * 设置expectedReceiver属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setExpectedReceiver(JAXBElement<String> value) {
        this.expectedReceiver = value;
    }

    /**
     * 获取extractDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getExtractDate() {
        return extractDate;
    }

    /**
     * 设置extractDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setExtractDate(XMLGregorianCalendar value) {
        this.extractDate = value;
    }

    /**
     * 获取extractMethodCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getExtractMethodCode() {
        return extractMethodCode;
    }

    /**
     * 设置extractMethodCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setExtractMethodCode(JAXBElement<String> value) {
        this.extractMethodCode = value;
    }

    /**
     * 获取extractMethodName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getExtractMethodName() {
        return extractMethodName;
    }

    /**
     * 设置extractMethodName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setExtractMethodName(JAXBElement<String> value) {
        this.extractMethodName = value;
    }

    /**
     * 获取extractPosition属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getExtractPosition() {
        return extractPosition;
    }

    /**
     * 设置extractPosition属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setExtractPosition(JAXBElement<String> value) {
        this.extractPosition = value;
    }

    /**
     * 获取extractor属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getExtractor() {
        return extractor;
    }

    /**
     * 设置extractor属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setExtractor(JAXBElement<String> value) {
        this.extractor = value;
    }

    /**
     * 获取extractorPhone属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getExtractorPhone() {
        return extractorPhone;
    }

    /**
     * 设置extractorPhone属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setExtractorPhone(JAXBElement<String> value) {
        this.extractorPhone = value;
    }

    /**
     * 获取flag属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFlag() {
        return flag;
    }

    /**
     * 设置flag属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFlag(JAXBElement<String> value) {
        this.flag = value;
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
     * 获取important属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getImportant() {
        return important;
    }

    /**
     * 设置important属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setImportant(JAXBElement<String> value) {
        this.important = value;
    }

    /**
     * 获取isCenterSE属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIsCenterSE() {
        return isCenterSE;
    }

    /**
     * 设置isCenterSE属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIsCenterSE(JAXBElement<String> value) {
        this.isCenterSE = value;
    }

    /**
     * 获取isDeleted属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIsDeleted() {
        return isDeleted;
    }

    /**
     * 设置isDeleted属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIsDeleted(JAXBElement<String> value) {
        this.isDeleted = value;
    }

    /**
     * 获取isFTA属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIsFTA() {
        return isFTA;
    }

    /**
     * 设置isFTA属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIsFTA(JAXBElement<String> value) {
        this.isFTA = value;
    }

    /**
     * 获取isSampleRepeat属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIsSampleRepeat() {
        return isSampleRepeat;
    }

    /**
     * 设置isSampleRepeat属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIsSampleRepeat(JAXBElement<String> value) {
        this.isSampleRepeat = value;
    }

    /**
     * 获取isStore属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIsStore() {
        return isStore;
    }

    /**
     * 设置isStore属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIsStore(JAXBElement<String> value) {
        this.isStore = value;
    }

    /**
     * 获取levelflag属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getLevelflag() {
        return levelflag;
    }

    /**
     * 设置levelflag属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setLevelflag(Integer value) {
        this.levelflag = value;
    }

    /**
     * 获取moreDesc属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMoreDesc() {
        return moreDesc;
    }

    /**
     * 设置moreDesc属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMoreDesc(JAXBElement<String> value) {
        this.moreDesc = value;
    }

    /**
     * 获取operateStepCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOperateStepCode() {
        return operateStepCode;
    }

    /**
     * 设置operateStepCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOperateStepCode(JAXBElement<String> value) {
        this.operateStepCode = value;
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
     * 获取operatorTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOperatorTime() {
        return operatorTime;
    }

    /**
     * 设置operatorTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOperatorTime(XMLGregorianCalendar value) {
        this.operatorTime = value;
    }

    /**
     * 获取packMethod属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPackMethod() {
        return packMethod;
    }

    /**
     * 设置packMethod属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPackMethod(JAXBElement<String> value) {
        this.packMethod = value;
    }

    /**
     * 获取pcrBoardCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPcrBoardCode() {
        return pcrBoardCode;
    }

    /**
     * 设置pcrBoardCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPcrBoardCode(JAXBElement<String> value) {
        this.pcrBoardCode = value;
    }

    /**
     * 获取pcrBoardHoleCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPcrBoardHoleCode() {
        return pcrBoardHoleCode;
    }

    /**
     * 设置pcrBoardHoleCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPcrBoardHoleCode(JAXBElement<String> value) {
        this.pcrBoardHoleCode = value;
    }

    /**
     * 获取pcrable属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPcrable() {
        return pcrable;
    }

    /**
     * 设置pcrable属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPcrable(JAXBElement<String> value) {
        this.pcrable = value;
    }

    /**
     * 获取picture属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPicture() {
        return picture;
    }

    /**
     * 设置picture属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPicture(JAXBElement<String> value) {
        this.picture = value;
    }

    /**
     * 获取preBarcode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPreBarcode() {
        return preBarcode;
    }

    /**
     * 设置preBarcode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPreBarcode(JAXBElement<String> value) {
        this.preBarcode = value;
    }

    /**
     * 获取refMemberId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRefMemberId() {
        return refMemberId;
    }

    /**
     * 设置refMemberId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRefMemberId(JAXBElement<String> value) {
        this.refMemberId = value;
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
     * 获取rn属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRn() {
        return rn;
    }

    /**
     * 设置rn属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRn(JAXBElement<String> value) {
        this.rn = value;
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
     * 获取sampleDescription属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSampleDescription() {
        return sampleDescription;
    }

    /**
     * 设置sampleDescription属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSampleDescription(JAXBElement<String> value) {
        this.sampleDescription = value;
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
     * 获取sampleNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSampleNo() {
        return sampleNo;
    }

    /**
     * 设置sampleNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSampleNo(JAXBElement<String> value) {
        this.sampleNo = value;
    }

    /**
     * 获取sampleRelation属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSampleRelation() {
        return sampleRelation;
    }

    /**
     * 设置sampleRelation属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSampleRelation(JAXBElement<String> value) {
        this.sampleRelation = value;
    }

    /**
     * 获取sampleStatus属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSampleStatus() {
        return sampleStatus;
    }

    /**
     * 设置sampleStatus属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSampleStatus(JAXBElement<String> value) {
        this.sampleStatus = value;
    }

    /**
     * 获取sampleStatusMark属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSampleStatusMark() {
        return sampleStatusMark;
    }

    /**
     * 设置sampleStatusMark属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSampleStatusMark(JAXBElement<String> value) {
        this.sampleStatusMark = value;
    }

    /**
     * 获取sampleTypeAddInfo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSampleTypeAddInfo() {
        return sampleTypeAddInfo;
    }

    /**
     * 设置sampleTypeAddInfo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSampleTypeAddInfo(JAXBElement<String> value) {
        this.sampleTypeAddInfo = value;
    }

    /**
     * 获取sampleTypeAddInfoStr属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSampleTypeAddInfoStr() {
        return sampleTypeAddInfoStr;
    }

    /**
     * 设置sampleTypeAddInfoStr属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSampleTypeAddInfoStr(JAXBElement<String> value) {
        this.sampleTypeAddInfoStr = value;
    }

    /**
     * 获取sampleTypeCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSampleTypeCode() {
        return sampleTypeCode;
    }

    /**
     * 设置sampleTypeCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSampleTypeCode(JAXBElement<String> value) {
        this.sampleTypeCode = value;
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
     * 获取serialNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSerialNo() {
        return serialNo;
    }

    /**
     * 设置serialNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSerialNo(Integer value) {
        this.serialNo = value;
    }

    /**
     * 获取slotNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSlotNo() {
        return slotNo;
    }

    /**
     * 设置slotNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSlotNo(JAXBElement<String> value) {
        this.slotNo = value;
    }

    /**
     * 获取stepCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getStepCode() {
        return stepCode;
    }

    /**
     * 设置stepCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setStepCode(JAXBElement<String> value) {
        this.stepCode = value;
    }

    /**
     * 获取testDesc属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTestDesc() {
        return testDesc;
    }

    /**
     * 设置testDesc属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTestDesc(JAXBElement<String> value) {
        this.testDesc = value;
    }

    /**
     * 获取testPurpose属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTestPurpose() {
        return testPurpose;
    }

    /**
     * 设置testPurpose属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTestPurpose(JAXBElement<String> value) {
        this.testPurpose = value;
    }

    /**
     * 获取tester属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTester() {
        return tester;
    }

    /**
     * 设置tester属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTester(JAXBElement<String> value) {
        this.tester = value;
    }

    /**
     * 获取urgent属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getUrgent() {
        return urgent;
    }

    /**
     * 设置urgent属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setUrgent(JAXBElement<String> value) {
        this.urgent = value;
    }

    /**
     * 获取warn属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getWarn() {
        return warn;
    }

    /**
     * 设置warn属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setWarn(JAXBElement<String> value) {
        this.warn = value;
    }

    /**
     * 获取xkAddress属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getXkAddress() {
        return xkAddress;
    }

    /**
     * 设置xkAddress属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setXkAddress(JAXBElement<String> value) {
        this.xkAddress = value;
    }

    /**
     * 获取xkDelegateSN属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getXkDelegateSN() {
        return xkDelegateSN;
    }

    /**
     * 设置xkDelegateSN属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setXkDelegateSN(JAXBElement<String> value) {
        this.xkDelegateSN = value;
    }

    /**
     * 获取xkNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getXkNumber() {
        return xkNumber;
    }

    /**
     * 设置xkNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setXkNumber(JAXBElement<String> value) {
        this.xkNumber = value;
    }

}
