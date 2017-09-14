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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Daniel
 */
@Entity
@Table(name = "MANAGER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Manager.findAll", query = "SELECT m FROM Manager m"),
    @NamedQuery(name = "Manager.findByMaId", query = "SELECT m FROM Manager m WHERE m.maId = :maId"),
    @NamedQuery(name = "Manager.findByManagerFname", query = "SELECT m FROM Manager m WHERE m.managerFname = :managerFname"),
    @NamedQuery(name = "Manager.findByManagerLname", query = "SELECT m FROM Manager m WHERE m.managerLname = :managerLname"),
    @NamedQuery(name = "Manager.findByAddres", query = "SELECT m FROM Manager m WHERE m.addres = :addres"),
    @NamedQuery(name = "Manager.findByEmail", query = "SELECT m FROM Manager m WHERE m.email = :email"),
    @NamedQuery(name = "Manager.findByContactnumber", query = "SELECT m FROM Manager m WHERE m.contactnumber = :contactnumber")})
public class Manager implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "MA_ID")
    private int maId;
    @Column(name = "MANAGER_FNAME")
    private String managerFname;
    @Column(name = "MANAGER_LNAME")
    private String managerLname;
    @Column(name = "ADDRES")
    private String addres;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "CONTACTNUMBER")
    private String contactnumber;
    @OneToMany(mappedBy = "managerId", fetch = FetchType.EAGER)
    private List<Receipt> receiptList=new ArrayList<>();
    @OneToMany(mappedBy = "managerId", fetch = FetchType.EAGER)
    private List<Band> bandList=new ArrayList<>();

    
    //add Recepie
    public void addReceipt(Receipt re)
    {
        this.receiptList.add(re);
        re.setManagerId(this);
    }
    
    //Add band to manager
    public void addBand(Band band)
    {
        this.bandList.add(band);
        band.setManagerId(this);
    }
     
    
    public Manager() {
    }

    public Manager(int maId) {
        this.maId = maId;
    }

    public int getMaId() {
        return maId;
    }

    public void setMaId(int maId) {
        this.maId = maId;
    }

    public String getManagerFname() {
        return managerFname;
    }

    public void setManagerFname(String managerFname) {
        this.managerFname = managerFname;
    }

    public String getManagerLname() {
        return managerLname;
    }

    public void setManagerLname(String managerLname) {
        this.managerLname = managerLname;
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactnumber() {
        return contactnumber;
    }

    public void setContactnumber(String contactnumber) {
        this.contactnumber = contactnumber;
    }

    @XmlTransient
    public List<Receipt> getReceiptList() {
        return receiptList;
    }

    public void setReceiptList(List<Receipt> receiptList) {
        this.receiptList = receiptList;
    }

    @XmlTransient
    public List<Band> getBandList() {
        return bandList;
    }

    public void setBandList(List<Band> bandList) {
        this.bandList = bandList;
    }

    

    @Override
    public String toString() {
        return "SqlEntities.Manager[ maId=" + maId + " ]";
    }
    
}
