//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0.5-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.14 at 02:18:54 PM EET 
//


package com.nixsolutions.wsdl;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WorkerStatusService complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WorkerStatusService">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="workerStatusId" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="workerStatusName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WorkerStatusService", propOrder = {
    "workerStatusId",
    "workerStatusName"
})
public class WorkerStatusService {

    @XmlElement(required = true)
    protected BigInteger workerStatusId;
    @XmlElement(required = true)
    protected String workerStatusName;

    /**
     * Gets the value of the workerStatusId property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getWorkerStatusId() {
        return workerStatusId;
    }

    /**
     * Sets the value of the workerStatusId property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setWorkerStatusId(BigInteger value) {
        this.workerStatusId = value;
    }

    /**
     * Gets the value of the workerStatusName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkerStatusName() {
        return workerStatusName;
    }

    /**
     * Sets the value of the workerStatusName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkerStatusName(String value) {
        this.workerStatusName = value;
    }

}
