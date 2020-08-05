
package com.bazl.clims.webservice.basicInfo;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.bazl.clims.webservice.basicInfo package. 
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

    private final static QName _WSMutationMuationName_QNAME = new QName("http://BasicInfo.webservice.web.dna.todaysoft.com", "muationName");
    private final static QName _WSMutationMutationId_QNAME = new QName("http://BasicInfo.webservice.web.dna.todaysoft.com", "mutationId");
    private final static QName _WSResultProcessStateStateName_QNAME = new QName("http://BasicInfo.webservice.web.dna.todaysoft.com", "stateName");
    private final static QName _WSMatchTypeMatchTypeName_QNAME = new QName("http://BasicInfo.webservice.web.dna.todaysoft.com", "matchTypeName");
    private final static QName _WSMatchTypeRelativeType_QNAME = new QName("http://BasicInfo.webservice.web.dna.todaysoft.com", "relativeType");
    private final static QName _WSPopulationPopulationID_QNAME = new QName("http://BasicInfo.webservice.web.dna.todaysoft.com", "populationID");
    private final static QName _WSPopulationPopulationname_QNAME = new QName("http://BasicInfo.webservice.web.dna.todaysoft.com", "populationname");
    private final static QName _WSPanelPanelID_QNAME = new QName("http://BasicInfo.webservice.web.dna.todaysoft.com", "panelID");
    private final static QName _WSPanelPanelName_QNAME = new QName("http://BasicInfo.webservice.web.dna.todaysoft.com", "panelName");
    private final static QName _WSSampleTypeSampleTypeName_QNAME = new QName("http://BasicInfo.webservice.web.dna.todaysoft.com", "sampleTypeName");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.bazl.clims.webservice.basicInfo
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetSampleTypeList }
     * 
     */
    public GetSampleTypeList createGetSampleTypeList() {
        return new GetSampleTypeList();
    }

    /**
     * Create an instance of {@link GetSampleTypeListResponse }
     * 
     */
    public GetSampleTypeListResponse createGetSampleTypeListResponse() {
        return new GetSampleTypeListResponse();
    }

    /**
     * Create an instance of {@link ArrayOfWSSampleType }
     * 
     */
    public ArrayOfWSSampleType createArrayOfWSSampleType() {
        return new ArrayOfWSSampleType();
    }

    /**
     * Create an instance of {@link GetPanelList }
     * 
     */
    public GetPanelList createGetPanelList() {
        return new GetPanelList();
    }

    /**
     * Create an instance of {@link GetPanelListResponse }
     * 
     */
    public GetPanelListResponse createGetPanelListResponse() {
        return new GetPanelListResponse();
    }

    /**
     * Create an instance of {@link ArrayOfWSPanel }
     * 
     */
    public ArrayOfWSPanel createArrayOfWSPanel() {
        return new ArrayOfWSPanel();
    }

    /**
     * Create an instance of {@link GetPopulationList }
     * 
     */
    public GetPopulationList createGetPopulationList() {
        return new GetPopulationList();
    }

    /**
     * Create an instance of {@link GetPopulationListResponse }
     * 
     */
    public GetPopulationListResponse createGetPopulationListResponse() {
        return new GetPopulationListResponse();
    }

    /**
     * Create an instance of {@link ArrayOfWSPopulation }
     * 
     */
    public ArrayOfWSPopulation createArrayOfWSPopulation() {
        return new ArrayOfWSPopulation();
    }

    /**
     * Create an instance of {@link GetPE }
     * 
     */
    public GetPE createGetPE() {
        return new GetPE();
    }

    /**
     * Create an instance of {@link GetPEResponse }
     * 
     */
    public GetPEResponse createGetPEResponse() {
        return new GetPEResponse();
    }

    /**
     * Create an instance of {@link GetMatchTypeList }
     * 
     */
    public GetMatchTypeList createGetMatchTypeList() {
        return new GetMatchTypeList();
    }

    /**
     * Create an instance of {@link GetMatchTypeListResponse }
     * 
     */
    public GetMatchTypeListResponse createGetMatchTypeListResponse() {
        return new GetMatchTypeListResponse();
    }

    /**
     * Create an instance of {@link ArrayOfWSMatchType }
     * 
     */
    public ArrayOfWSMatchType createArrayOfWSMatchType() {
        return new ArrayOfWSMatchType();
    }

    /**
     * Create an instance of {@link GetResultProcessState }
     * 
     */
    public GetResultProcessState createGetResultProcessState() {
        return new GetResultProcessState();
    }

    /**
     * Create an instance of {@link GetResultProcessStateResponse }
     * 
     */
    public GetResultProcessStateResponse createGetResultProcessStateResponse() {
        return new GetResultProcessStateResponse();
    }

    /**
     * Create an instance of {@link ArrayOfWSResultProcessState }
     * 
     */
    public ArrayOfWSResultProcessState createArrayOfWSResultProcessState() {
        return new ArrayOfWSResultProcessState();
    }

    /**
     * Create an instance of {@link GetMarkersOfPanel }
     * 
     */
    public GetMarkersOfPanel createGetMarkersOfPanel() {
        return new GetMarkersOfPanel();
    }

    /**
     * Create an instance of {@link GetMarkersOfPanelResponse }
     * 
     */
    public GetMarkersOfPanelResponse createGetMarkersOfPanelResponse() {
        return new GetMarkersOfPanelResponse();
    }

    /**
     * Create an instance of {@link ArrayOfString }
     * 
     */
    public ArrayOfString createArrayOfString() {
        return new ArrayOfString();
    }

    /**
     * Create an instance of {@link GetPopulation }
     * 
     */
    public GetPopulation createGetPopulation() {
        return new GetPopulation();
    }

    /**
     * Create an instance of {@link GetPopulationResponse }
     * 
     */
    public GetPopulationResponse createGetPopulationResponse() {
        return new GetPopulationResponse();
    }

    /**
     * Create an instance of {@link WSPopPopulation }
     * 
     */
    public WSPopPopulation createWSPopPopulation() {
        return new WSPopPopulation();
    }

    /**
     * Create an instance of {@link GetMutationList }
     * 
     */
    public GetMutationList createGetMutationList() {
        return new GetMutationList();
    }

    /**
     * Create an instance of {@link GetMutationListResponse }
     * 
     */
    public GetMutationListResponse createGetMutationListResponse() {
        return new GetMutationListResponse();
    }

    /**
     * Create an instance of {@link ArrayOfWSMutation }
     * 
     */
    public ArrayOfWSMutation createArrayOfWSMutation() {
        return new ArrayOfWSMutation();
    }

    /**
     * Create an instance of {@link WSSampleType }
     * 
     */
    public WSSampleType createWSSampleType() {
        return new WSSampleType();
    }

    /**
     * Create an instance of {@link WSPanel }
     * 
     */
    public WSPanel createWSPanel() {
        return new WSPanel();
    }

    /**
     * Create an instance of {@link WSPopulation }
     * 
     */
    public WSPopulation createWSPopulation() {
        return new WSPopulation();
    }

    /**
     * Create an instance of {@link WSMatchType }
     * 
     */
    public WSMatchType createWSMatchType() {
        return new WSMatchType();
    }

    /**
     * Create an instance of {@link WSResultProcessState }
     * 
     */
    public WSResultProcessState createWSResultProcessState() {
        return new WSResultProcessState();
    }

    /**
     * Create an instance of {@link WSMutation }
     * 
     */
    public WSMutation createWSMutation() {
        return new WSMutation();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://BasicInfo.webservice.web.dna.todaysoft.com", name = "muationName", scope = WSMutation.class)
    public JAXBElement<String> createWSMutationMuationName(String value) {
        return new JAXBElement<String>(_WSMutationMuationName_QNAME, String.class, WSMutation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://BasicInfo.webservice.web.dna.todaysoft.com", name = "mutationId", scope = WSMutation.class)
    public JAXBElement<String> createWSMutationMutationId(String value) {
        return new JAXBElement<String>(_WSMutationMutationId_QNAME, String.class, WSMutation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://BasicInfo.webservice.web.dna.todaysoft.com", name = "stateName", scope = WSResultProcessState.class)
    public JAXBElement<String> createWSResultProcessStateStateName(String value) {
        return new JAXBElement<String>(_WSResultProcessStateStateName_QNAME, String.class, WSResultProcessState.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://BasicInfo.webservice.web.dna.todaysoft.com", name = "matchTypeName", scope = WSMatchType.class)
    public JAXBElement<String> createWSMatchTypeMatchTypeName(String value) {
        return new JAXBElement<String>(_WSMatchTypeMatchTypeName_QNAME, String.class, WSMatchType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://BasicInfo.webservice.web.dna.todaysoft.com", name = "relativeType", scope = WSMatchType.class)
    public JAXBElement<String> createWSMatchTypeRelativeType(String value) {
        return new JAXBElement<String>(_WSMatchTypeRelativeType_QNAME, String.class, WSMatchType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://BasicInfo.webservice.web.dna.todaysoft.com", name = "populationID", scope = WSPopulation.class)
    public JAXBElement<String> createWSPopulationPopulationID(String value) {
        return new JAXBElement<String>(_WSPopulationPopulationID_QNAME, String.class, WSPopulation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://BasicInfo.webservice.web.dna.todaysoft.com", name = "populationname", scope = WSPopulation.class)
    public JAXBElement<String> createWSPopulationPopulationname(String value) {
        return new JAXBElement<String>(_WSPopulationPopulationname_QNAME, String.class, WSPopulation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://BasicInfo.webservice.web.dna.todaysoft.com", name = "panelID", scope = WSPanel.class)
    public JAXBElement<String> createWSPanelPanelID(String value) {
        return new JAXBElement<String>(_WSPanelPanelID_QNAME, String.class, WSPanel.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://BasicInfo.webservice.web.dna.todaysoft.com", name = "panelName", scope = WSPanel.class)
    public JAXBElement<String> createWSPanelPanelName(String value) {
        return new JAXBElement<String>(_WSPanelPanelName_QNAME, String.class, WSPanel.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://BasicInfo.webservice.web.dna.todaysoft.com", name = "relativeType", scope = WSSampleType.class)
    public JAXBElement<String> createWSSampleTypeRelativeType(String value) {
        return new JAXBElement<String>(_WSMatchTypeRelativeType_QNAME, String.class, WSSampleType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://BasicInfo.webservice.web.dna.todaysoft.com", name = "sampleTypeName", scope = WSSampleType.class)
    public JAXBElement<String> createWSSampleTypeSampleTypeName(String value) {
        return new JAXBElement<String>(_WSSampleTypeSampleTypeName_QNAME, String.class, WSSampleType.class, value);
    }

}
