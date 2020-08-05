
package com.bazl.clims.webservice.caseSampleLib;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.bazl.clims.webservice.caseSampleLib package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SampleLibLabno_QNAME = new QName("http://casesamplelib.webservice.web.dna.todaysoft.com", "labno");
    private final static QName _SampleLibSampleTypeName_QNAME = new QName("http://casesamplelib.webservice.web.dna.todaysoft.com", "sampleTypeName");
    private final static QName _SampleLibSampleid_QNAME = new QName("http://casesamplelib.webservice.web.dna.todaysoft.com", "sampleid");
    private final static QName _SampleLibSamplename_QNAME = new QName("http://casesamplelib.webservice.web.dna.todaysoft.com", "samplename");
    private final static QName _SampleLibSubmittime_QNAME = new QName("http://casesamplelib.webservice.web.dna.todaysoft.com", "submittime");
    private final static QName _SampleNoDomainSampleId_QNAME = new QName("http://casesamplelib.webservice.web.dna.todaysoft.com", "sampleId");
    private final static QName _SampleNoDomainSampleNo_QNAME = new QName("http://casesamplelib.webservice.web.dna.todaysoft.com", "sampleNo");
    private final static QName _CaseSampleLibCaseID_QNAME = new QName("http://casesamplelib.webservice.web.dna.todaysoft.com", "caseID");
    private final static QName _CaseSampleLibCaseName_QNAME = new QName("http://casesamplelib.webservice.web.dna.todaysoft.com", "caseName");
    private final static QName _CaseSampleLibSampleLibList_QNAME = new QName("http://casesamplelib.webservice.web.dna.todaysoft.com", "sampleLibList");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.bazl.clims.webservice.caseSampleLib
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetSamples }
     * 
     */
    public GetSamples createGetSamples() {
        return new GetSamples();
    }

    /**
     * Create an instance of {@link GetSamplesResponse }
     * 
     */
    public GetSamplesResponse createGetSamplesResponse() {
        return new GetSamplesResponse();
    }

    /**
     * Create an instance of {@link ArrayOfSampleNoDomain }
     * 
     */
    public ArrayOfSampleNoDomain createArrayOfSampleNoDomain() {
        return new ArrayOfSampleNoDomain();
    }

    /**
     * Create an instance of {@link GetStoreSampleCntByCaseID }
     * 
     */
    public GetStoreSampleCntByCaseID createGetStoreSampleCntByCaseID() {
        return new GetStoreSampleCntByCaseID();
    }

    /**
     * Create an instance of {@link GetStoreSampleCntByCaseIDResponse }
     * 
     */
    public GetStoreSampleCntByCaseIDResponse createGetStoreSampleCntByCaseIDResponse() {
        return new GetStoreSampleCntByCaseIDResponse();
    }

    /**
     * Create an instance of {@link GetStoredSampleCnt }
     * 
     */
    public GetStoredSampleCnt createGetStoredSampleCnt() {
        return new GetStoredSampleCnt();
    }

    /**
     * Create an instance of {@link GetStoredSampleCntResponse }
     * 
     */
    public GetStoredSampleCntResponse createGetStoredSampleCntResponse() {
        return new GetStoredSampleCntResponse();
    }

    /**
     * Create an instance of {@link ArrayOfCaseSampleLib }
     * 
     */
    public ArrayOfCaseSampleLib createArrayOfCaseSampleLib() {
        return new ArrayOfCaseSampleLib();
    }

    /**
     * Create an instance of {@link UpdateSampleState }
     * 
     */
    public UpdateSampleState createUpdateSampleState() {
        return new UpdateSampleState();
    }

    /**
     * Create an instance of {@link ArrayOfString }
     * 
     */
    public ArrayOfString createArrayOfString() {
        return new ArrayOfString();
    }

    /**
     * Create an instance of {@link UpdateSampleStateResponse }
     * 
     */
    public UpdateSampleStateResponse createUpdateSampleStateResponse() {
        return new UpdateSampleStateResponse();
    }

    /**
     * Create an instance of {@link GetCaseSampleListFromLibByCaseID }
     * 
     */
    public GetCaseSampleListFromLibByCaseID createGetCaseSampleListFromLibByCaseID() {
        return new GetCaseSampleListFromLibByCaseID();
    }

    /**
     * Create an instance of {@link GetCaseSampleListFromLibByCaseIDResponse }
     * 
     */
    public GetCaseSampleListFromLibByCaseIDResponse createGetCaseSampleListFromLibByCaseIDResponse() {
        return new GetCaseSampleListFromLibByCaseIDResponse();
    }

    /**
     * Create an instance of {@link CaseSampleLib }
     * 
     */
    public CaseSampleLib createCaseSampleLib() {
        return new CaseSampleLib();
    }

    /**
     * Create an instance of {@link SampleNoDomain }
     * 
     */
    public SampleNoDomain createSampleNoDomain() {
        return new SampleNoDomain();
    }

    /**
     * Create an instance of {@link ArrayOfSampleLib }
     * 
     */
    public ArrayOfSampleLib createArrayOfSampleLib() {
        return new ArrayOfSampleLib();
    }

    /**
     * Create an instance of {@link SampleLib }
     * 
     */
    public SampleLib createSampleLib() {
        return new SampleLib();
    }

    /**
     * Create an instance of {@link ArrayOfDateTime }
     * 
     */
    public ArrayOfDateTime createArrayOfDateTime() {
        return new ArrayOfDateTime();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfString }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://casesamplelib.webservice.web.dna.todaysoft.com", name = "labno", scope = SampleLib.class)
    public JAXBElement<ArrayOfString> createSampleLibLabno(ArrayOfString value) {
        return new JAXBElement<ArrayOfString>(_SampleLibLabno_QNAME, ArrayOfString.class, SampleLib.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://casesamplelib.webservice.web.dna.todaysoft.com", name = "sampleTypeName", scope = SampleLib.class)
    public JAXBElement<String> createSampleLibSampleTypeName(String value) {
        return new JAXBElement<String>(_SampleLibSampleTypeName_QNAME, String.class, SampleLib.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfString }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://casesamplelib.webservice.web.dna.todaysoft.com", name = "sampleid", scope = SampleLib.class)
    public JAXBElement<ArrayOfString> createSampleLibSampleid(ArrayOfString value) {
        return new JAXBElement<ArrayOfString>(_SampleLibSampleid_QNAME, ArrayOfString.class, SampleLib.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfString }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://casesamplelib.webservice.web.dna.todaysoft.com", name = "samplename", scope = SampleLib.class)
    public JAXBElement<ArrayOfString> createSampleLibSamplename(ArrayOfString value) {
        return new JAXBElement<ArrayOfString>(_SampleLibSamplename_QNAME, ArrayOfString.class, SampleLib.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfDateTime }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://casesamplelib.webservice.web.dna.todaysoft.com", name = "submittime", scope = SampleLib.class)
    public JAXBElement<ArrayOfDateTime> createSampleLibSubmittime(ArrayOfDateTime value) {
        return new JAXBElement<ArrayOfDateTime>(_SampleLibSubmittime_QNAME, ArrayOfDateTime.class, SampleLib.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://casesamplelib.webservice.web.dna.todaysoft.com", name = "sampleId", scope = SampleNoDomain.class)
    public JAXBElement<String> createSampleNoDomainSampleId(String value) {
        return new JAXBElement<String>(_SampleNoDomainSampleId_QNAME, String.class, SampleNoDomain.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://casesamplelib.webservice.web.dna.todaysoft.com", name = "sampleNo", scope = SampleNoDomain.class)
    public JAXBElement<String> createSampleNoDomainSampleNo(String value) {
        return new JAXBElement<String>(_SampleNoDomainSampleNo_QNAME, String.class, SampleNoDomain.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://casesamplelib.webservice.web.dna.todaysoft.com", name = "caseID", scope = CaseSampleLib.class)
    public JAXBElement<String> createCaseSampleLibCaseID(String value) {
        return new JAXBElement<String>(_CaseSampleLibCaseID_QNAME, String.class, CaseSampleLib.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://casesamplelib.webservice.web.dna.todaysoft.com", name = "caseName", scope = CaseSampleLib.class)
    public JAXBElement<String> createCaseSampleLibCaseName(String value) {
        return new JAXBElement<String>(_CaseSampleLibCaseName_QNAME, String.class, CaseSampleLib.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://casesamplelib.webservice.web.dna.todaysoft.com", name = "labno", scope = CaseSampleLib.class)
    public JAXBElement<String> createCaseSampleLibLabno(String value) {
        return new JAXBElement<String>(_SampleLibLabno_QNAME, String.class, CaseSampleLib.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfSampleLib }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://casesamplelib.webservice.web.dna.todaysoft.com", name = "sampleLibList", scope = CaseSampleLib.class)
    public JAXBElement<ArrayOfSampleLib> createCaseSampleLibSampleLibList(ArrayOfSampleLib value) {
        return new JAXBElement<ArrayOfSampleLib>(_CaseSampleLibSampleLibList_QNAME, ArrayOfSampleLib.class, CaseSampleLib.class, value);
    }

}
