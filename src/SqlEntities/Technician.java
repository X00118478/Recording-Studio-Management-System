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
@Table(name = "TECHNICIAN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Technician.findAll", query = "SELECT t FROM Technician t"),
    @NamedQuery(name = "Technician.findByIdTechnician", query = "SELECT t FROM Technician t WHERE t.idTechnician = :idTechnician"),
    @NamedQuery(name = "Technician.findByTechnicianjob", query = "SELECT t FROM Technician t WHERE t.technicianjob = :technicianjob"),
    @NamedQuery(name = "Technician.findByCost", query = "SELECT t FROM Technician t WHERE t.cost = :cost")})
public class Technician implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_TECHNICIAN")
    private int idTechnician;
    @Column(name = "TECHNICIANJOB")
    private String technicianjob;
    @Column(name = "COST")
    private String cost;
    @ManyToMany(mappedBy = "technicianList", fetch = FetchType.EAGER)
    private List<Receipt> receiptList= new ArrayList<Receipt>();
    @JoinColumn(name = "ID_EMPLOYE", referencedColumnName = "ID_EMP")
    @ManyToOne(fetch = FetchType.EAGER)
    private Employe idEmploye;

    public Technician() {
    }

    public void addReceipt(Receipt re)
    {
        this.receiptList.add(re);
        re.getTechnicianList().add(this);
    }
    
    public Technician(int idTechnician) {
        this.idTechnician = idTechnician;
    }

    public int getIdTechnician() {
        return idTechnician;
    }

    public void setIdTechnician(int idTechnician) {
        this.idTechnician = idTechnician;
    }

    public String getTechnicianjob() {
        return technicianjob;
    }

    public void setTechnicianjob(String technicianjob) {
        this.technicianjob = technicianjob;
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

    public Employe getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(Employe idEmploye) {
        this.idEmploye = idEmploye;
    }

    

    @Override
    public String toString() {
        return "SqlEntities.Technician[ idTechnician=" + idTechnician + " ]";
    }
    
}
