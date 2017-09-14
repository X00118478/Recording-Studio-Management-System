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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "RECEIPT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Receipt.findAll", query = "SELECT r FROM Receipt r"),
    @NamedQuery(name = "Receipt.findByIdReceipt", query = "SELECT r FROM Receipt r WHERE r.idReceipt = :idReceipt"),
    @NamedQuery(name = "Receipt.findByTotalpay", query = "SELECT r FROM Receipt r WHERE r.totalpay = :totalpay"),
    @NamedQuery(name = "Receipt.findByPayed", query = "SELECT r FROM Receipt r WHERE r.payed = :payed")})
public class Receipt implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_RECEIPT")
    private int idReceipt;
    @Column(name = "TOTALPAY")
    private String totalpay;
    @Column(name = "PAYED")
    private String payed;
    @JoinTable(name = "REC_SERV", joinColumns = {
        @JoinColumn(name = "ID_RECEIPT", referencedColumnName = "ID_RECEIPT")}, inverseJoinColumns = {
        @JoinColumn(name = "SERVICE_ID", referencedColumnName = "SERVICE_ID")})
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Service> serviceList=new ArrayList<>();
    @JoinTable(name = "REC_TECH", joinColumns = {
        @JoinColumn(name = "ID_RECEIPT", referencedColumnName = "ID_RECEIPT")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_TECHNICIAN", referencedColumnName = "ID_TECHNICIAN")})
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Technician> technicianList=new ArrayList<>();
    @JoinTable(name = "REC_EQUIP", joinColumns = {
        @JoinColumn(name = "ID_RECEIPT", referencedColumnName = "ID_RECEIPT")}, inverseJoinColumns = {
        @JoinColumn(name = "EQUIP_ID", referencedColumnName = "EQUIP_ID")})
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Equipment> equipmentList=new ArrayList<>();
    @JoinColumn(name = "BAND_ID", referencedColumnName = "BAND_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private Band bandId;
    @JoinColumn(name = "ID_DATE", referencedColumnName = "ID_DATE")
    @ManyToOne(fetch = FetchType.EAGER)
    private CalDate idDate;
    @JoinColumn(name = "MANAGER_ID", referencedColumnName = "MA_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private Manager managerId;
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID_USER")
    @ManyToOne(fetch = FetchType.EAGER)
    private SUser idUser;

    public Receipt() {
    }

    public void addService(Service serv)
    {
      // 
        this.serviceList.add(serv);
          serv.getReceiptList().add(this);
       
    }
    
    public void addEquipment(Equipment equip)
    {
       equip.addReceipt(this);
      // System.out.print(equip);
        this.equipmentList.add(equip);
          
       
    }
    
    public void addTechnician(Technician tech)
    {
      //  
        this.technicianList.add(tech);
         tech.getReceiptList().add(this);
       
    }
    
    public Receipt(int idReceipt) {
        this.idReceipt = idReceipt;
    }

    public int getIdReceipt() {
        return idReceipt;
    }

    public void setIdReceipt(int idReceipt) {
        this.idReceipt = idReceipt;
    }

    public String getTotalpay() {
        return totalpay;
    }

    public void setTotalpay(String totalpay) {
        this.totalpay = totalpay;
    }

    public String getPayed() {
        return payed;
    }

    public void setPayed(String payed) {
        this.payed = payed;
    }

    @XmlTransient
    public List<Service> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<Service> serviceList) {
      this.serviceList.clear();
        for(Service e : serviceList)
        {
          addService(e);  
        }
    }

    @XmlTransient
    public List<Technician> getTechnicianList() {
        return technicianList;
    }

    public void setTechnicianList(List<Technician> technicianList) {
        this.technicianList.clear();
       for(Technician e : technicianList)
        {
         addTechnician(e);
        }
    }

    @XmlTransient
    public List<Equipment> getEquipmentList() {
        return equipmentList;
    }

    public void setEquipmentList(List<Equipment> equipmentList) {
        this.equipmentList.clear();
        for(Equipment e : equipmentList)
        {
         addEquipment(e);
        }
    }

    public Band getBandId() {
        return bandId;
    }

    public void setBandId(Band bandId) {
        this.bandId = bandId;
    }

    public CalDate getIdDate() {
        return idDate;
    }

    public void setIdDate(CalDate idDate) {
        this.idDate = idDate;
    }

    public Manager getManagerId() {
        return managerId;
    }

    public void setManagerId(Manager managerId) {
        this.managerId = managerId;
    }

    public SUser getIdUser() {
        return idUser;
    }

    public void setIdUser(SUser idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "Receipt{" + "idReceipt=" + idReceipt + ", totalpay=" + totalpay + ", payed=" + payed + ", serviceList=" + serviceList + ", technicianList=" + technicianList + ", equipmentList=" + equipmentList + ", bandId=" + bandId + ", idDate=" + idDate + ", managerId=" + managerId + ", idUser=" + idUser + '}';
    }

    

    
    
}
