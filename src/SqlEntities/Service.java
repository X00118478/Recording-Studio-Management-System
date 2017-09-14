/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SqlEntities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Daniel
 */
@Entity
@Table(name = "SERVICE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Service.findAll", query = "SELECT s FROM Service s"),
    @NamedQuery(name = "Service.findByServiceId", query = "SELECT s FROM Service s WHERE s.serviceId = :serviceId"),
    @NamedQuery(name = "Service.findByTypeofService", query = "SELECT s FROM Service s WHERE s.typeofService = :typeofService"),
    @NamedQuery(name = "Service.findByCost", query = "SELECT s FROM Service s WHERE s.cost = :cost")})
public class Service implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "SERVICE_ID")
    private int serviceId;
    @Column(name = "TYPEOF_SERVICE")
    private String typeofService;
    @Column(name = "COST")
    private String cost;
    @ManyToMany(mappedBy = "serviceList", fetch = FetchType.EAGER)
    private List<Receipt> receiptList=new ArrayList<>();

    public Service() {
    }

    
    public void addReceipt(Receipt receipt)
    {
        this.receiptList.add(receipt);
        receipt.getServiceList().add(this);
    }
    
    public Service(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getTypeofService() {
        return typeofService;
    }

    public void setTypeofService(String typeofService) {
        this.typeofService = typeofService;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    @XmlTransient
    public List<Receipt> getReceiptList() {
        return receiptList;
    }

    public void setReceiptList(List<Receipt> receiptList) {
        this.receiptList = receiptList;
    }


    @Override
    public String toString() {
        return "SqlEntities.Service[ serviceId=" + serviceId + " ]";
    }
    
}
