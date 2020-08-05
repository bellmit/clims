package com.bazl.clims.webservice.client.libMatch;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.0.11
 * 2019-09-10T16:38:47.007+08:00
 * Generated source version: 3.0.11
 * 
 */
@WebServiceClient(name = "libMatch", 
                  wsdlLocation = "http://127.0.0.1:8080/DnaWebservice/services/libMatch?wsdl",
                  targetNamespace = "http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com") 
public class LibMatch_Service extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com", "libMatch");
    public final static QName LibMatchHttpPort = new QName("http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com", "libMatchHttpPort");
    static {
        URL url = null;
        try {
            url = new URL("http://127.0.0.1:8080/DnaWebservice/services/libMatch?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(LibMatch_Service.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://127.0.0.1:8080/DnaWebservice/services/libMatch?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public LibMatch_Service(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public LibMatch_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public LibMatch_Service() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public LibMatch_Service(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public LibMatch_Service(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public LibMatch_Service(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    

    /**
     *
     * @return
     *     returns LibMatchPortType
     */
    @WebEndpoint(name = "libMatchHttpPort")
    public LibMatchPortType getLibMatchHttpPort() {
        return super.getPort(LibMatchHttpPort, LibMatchPortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns LibMatchPortType
     */
    @WebEndpoint(name = "libMatchHttpPort")
    public LibMatchPortType getLibMatchHttpPort(WebServiceFeature... features) {
        return super.getPort(LibMatchHttpPort, LibMatchPortType.class, features);
    }

}
