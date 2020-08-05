package com.bazl.clims.webservice.client.submitInfo;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.0.11
 * 2019-09-10T16:37:31.391+08:00
 * Generated source version: 3.0.11
 * 
 */
@WebServiceClient(name = "submitInfo", 
                  wsdlLocation = "http://127.0.0.1:8080/DnaWebservice/services/submitInfo?wsdl",
                  targetNamespace = "http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com") 
public class SubmitInfo extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com", "submitInfo");
    public final static QName SubmitInfoHttpPort = new QName("http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com", "submitInfoHttpPort");
    static {
        URL url = null;
        try {
            url = new URL("http://127.0.0.1:8080/DnaWebservice/services/submitInfo?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(SubmitInfo.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://127.0.0.1:8080/DnaWebservice/services/submitInfo?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public SubmitInfo(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public SubmitInfo(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SubmitInfo() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public SubmitInfo(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public SubmitInfo(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public SubmitInfo(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    

    /**
     *
     * @return
     *     returns SubmitInfoPortType
     */
    @WebEndpoint(name = "submitInfoHttpPort")
    public SubmitInfoPortType getSubmitInfoHttpPort() {
        return super.getPort(SubmitInfoHttpPort, SubmitInfoPortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SubmitInfoPortType
     */
    @WebEndpoint(name = "submitInfoHttpPort")
    public SubmitInfoPortType getSubmitInfoHttpPort(WebServiceFeature... features) {
        return super.getPort(SubmitInfoHttpPort, SubmitInfoPortType.class, features);
    }

}
