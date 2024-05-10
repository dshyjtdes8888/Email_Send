
package com.example.emaideskui.wsdl;

import jakarta.xml.bind.annotation.*;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="statue" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "statue"
})
@XmlRootElement(name = "sendEmailResponse", namespace = "http://spring.io/guides/gs-producing-web-service")
public class SenderResponse {

    @XmlElement(namespace = "http://spring.io/guides/gs-producing-web-service", required = true)
    protected String statue;

    /**
     * 获取statue属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatue() {
        return statue;
    }

    /**
     * 设置statue属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatue(String value) {
        this.statue = value;
    }

}
