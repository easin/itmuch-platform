
package com.itmuch.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>UserResult complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="UserResult"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://webservice.itmuch.com}WSResult"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="user" type="{http://webservice.itmuch.com}wsEntitySoap" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UserResult", propOrder = {
    "user"
})
public class UserResult
    extends WSResult
{

    protected WsEntitySoap user;

    /**
     * 获取user属性的值。
     * 
     * @return
     *     possible object is
     *     {@link WsEntitySoap }
     *     
     */
    public WsEntitySoap getUser() {
        return user;
    }

    /**
     * 设置user属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link WsEntitySoap }
     *     
     */
    public void setUser(WsEntitySoap value) {
        this.user = value;
    }

}
