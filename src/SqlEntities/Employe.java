/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SqlEntities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Daniel
 */
@Entity
@Table(name = "EMPLOYE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employe.findAll", query = "SELECT e FROM Employe e"),
    @NamedQuery(name = "Employe.findByIdEmp", query = "SELECT e FROM Employe e WHERE e.idEmp = :idEmp"),
    @NamedQuery(name = "Employe.findByEmFirname", query = "SELECT e FROM Employe e WHERE e.emFirname = :emFirname"),
    @NamedQuery(name = "Employe.findByEmLastname", query = "SELECT e FROM Employe e WHERE e.emLastname = :emLastname"),
    @NamedQuery(name = "Employe.findByEmAddress", query = "SELECT e FROM Employe e WHERE e.emAddress = :emAddress"),
    @NamedQuery(name = "Employe.findByEmContactnumber", query = "SELECT e FROM Employe e WHERE e.emContactnumber = :emContactnumber"),
    @NamedQuery(name = "Employe.findByEmTimepartTime", query = "SELECT e FROM Employe e WHERE e.emTimepartTime = :emTimepartTime"),
    @NamedQuery(name = "Employe.findByEmWages", query = "SELECT e FROM Employe e WHERE e.emWages = :emWages"),
    @NamedQuery(name = "Employe.findByEmPremisions", query = "SELECT e FROM Employe e WHERE e.emPremisions = :emPremisions"),
    @NamedQuery(name = "Employe.findByEmail", query = "SELECT e FROM Employe e WHERE e.email = :email"),
    @NamedQuery(name = "Employe.findByAccountCreatedOn", query = "SELECT e FROM Employe e WHERE e.accountCreatedOn = :accountCreatedOn")})
public class Employe implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_EMP")
    private int idEmp;
    @Column(name = "EM_FIRNAME")
    private String emFirname;
    @Column(name = "EM_LASTNAME")
    private String emLastname;
    @Column(name = "EM_ADDRESS")
    private String emAddress;
    @Column(name = "EM_CONTACTNUMBER")
    private String emContactnumber;
    @Column(name = "EM_TIMEPART_TIME")
    private String emTimepartTime;
    @Column(name = "EM_WAGES")
    private String emWages;
    @Column(name = "EM_PREMISIONS")
    private String emPremisions;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "ACCOUNT_CREATED_ON")
    @Temporal(TemporalType.TIMESTAMP)
    private Date accountCreatedOn;
    @OneToMany(mappedBy = "idEmploye", fetch = FetchType.EAGER)
    private List<SUser> sUserList=new ArrayList<>();
    @OneToMany(mappedBy = "idEmploye", fetch = FetchType.EAGER)
    private List<Technician> technicianList=new ArrayList<>();

    public Employe() {
    }

    public Employe(int idEmp) {
        this.idEmp = idEmp;
    }

    public int getIdEmp() {
        return idEmp;
    }

    public void setIdEmp(int idEmp) {
        this.idEmp = idEmp;
    }

    public String getEmFirname() {
        return emFirname;
    }

    public void setEmFirname(String emFirname) {
        this.emFirname = emFirname;
    }

    public String getEmLastname() {
        return emLastname;
    }

    public void setEmLastname(String emLastname) {
        this.emLastname = emLastname;
    }

    public String getEmAddress() {
        return emAddress;
    }

    public void setEmAddress(String emAddress) {
        this.emAddress = emAddress;
    }

    public String getEmContactnumber() {
        return emContactnumber;
    }

    public void setEmContactnumber(String emContactnumber) {
        this.emContactnumber = emContactnumber;
    }

    public String getEmTimepartTime() {
        return emTimepartTime;
    }

    public void setEmTimepartTime(String emTimepartTime) {
        this.emTimepartTime = emTimepartTime;
    }

    public String getEmWages() {
        return emWages;
    }

    public void setEmWages(String emWages) {
        this.emWages = emWages;
    }

    public String getEmPremisions() {
        return emPremisions;
    }

    public void setEmPremisions(String emPremisions) {
        this.emPremisions = emPremisions;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getAccountCreatedOn() {
        return accountCreatedOn;
    }

    public void setAccountCreatedOn(Date accountCreatedOn) {
        this.accountCreatedOn = accountCreatedOn;
    }

    @XmlTransient
    public List<SUser> getSUserList() {
        return sUserList;
    }

    public void setSUserList(List<SUser> sUserList) {
        this.sUserList = sUserList;
    }

    @XmlTransient
    public List<Technician> getTechnicianList() {
        return technicianList;
    }

    public void setTechnicianList(List<Technician> technicianList) {
        this.technicianList = technicianList;
    }

    //add user to employe
    public void addUser(SUser us)
    {
        this.sUserList.add(us);
        us.setIdEmploye(this);
    }
    //add technician to emplye
    public void addTechnician(Technician tech)
    {
        this.technicianList.add(tech);
        tech.setIdEmploye(this);
    }

    @Override
    public String toString() {
        return "Employe{" + "idEmp=" + idEmp + ", emFirname=" + emFirname + ", emLastname=" + emLastname + ", emAddress=" + emAddress + ", emContactnumber=" + emContactnumber + ", emTimepartTime=" + emTimepartTime + ", emWages=" + emWages + ", emPremisions=" + emPremisions + ", email=" + email + ", accountCreatedOn=" + accountCreatedOn + ", sUserList=" + sUserList + ", technicianList=" + technicianList + '}';
    }
    

   
}
