//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0.5-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.14 at 02:18:54 PM EET 
//


package com.nixsolutions.service.wsdl;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WorkerSpecializationService complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WorkerSpecializationService">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="workerSpecializationId" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="workerSpecializationName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WorkerSpecializationService", propOrder = {
    "workerSpecializationId",
    "workerSpecializationName"
})
public class WorkerSpecializationService {

    @XmlElement(required = true)
    protected BigInteger workerSpecializationId;
    @XmlElement(required = true)
    protected String workerSpecializationName;

    /**
     * Gets the value of the workerSpecializationId property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getWorkerSpecializationId() {
        return workerSpecializationId;
    }

    /**
     * Sets the value of the workerSpecializationId property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setWorkerSpecializationId(BigInteger value) {
        this.workerSpecializationId = value;
    }

    /**
     * Gets the value of the workerSpecializationName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkerSpecializationName() {
        return workerSpecializationName;
    }

    /**
     * Sets the value of the workerSpecializationName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkerSpecializationName(String value) {
        this.workerSpecializationName = value;
    }

}
