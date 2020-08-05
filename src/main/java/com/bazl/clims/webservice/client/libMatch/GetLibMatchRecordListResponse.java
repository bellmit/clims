
package com.bazl.clims.webservice.client.libMatch;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="out" type="{http://submitMatchDefaultSetting.webservice.web.dna.todaysoft.com}WS_LibMatchResultAndTotalCount"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "out"
})
@XmlRootElement(name = "getLibMatchRecordListResponse")
public class GetLibMatchRecordListResponse {

    @XmlElement(required = true, nillable = true)
    protected WSLibMatchResultAndTotalCount out;

    /**
     * 获取out属性的值。
     * 
     * @return
     *     possible object is
     *     {@link WSLibMatchResultAndTotalCount }
     *     
     */
    public WSLibMatchResultAndTotalCount getOut() {
        return out;
    }

    /**
     * 设置out属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link WSLibMatchResultAndTotalCount }
     *     
     */
    public void setOut(WSLibMatchResultAndTotalCount value) {
        this.out = value;
    }

}
