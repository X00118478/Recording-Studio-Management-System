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
@Table(name = "CAL_DATE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CalDate.findAll", query = "SELECT c FROM CalDate c"),
    @NamedQuery(name = "CalDate.findByIdDate", query = "SELECT c FROM CalDate c WHERE c.idDate = :idDate"),
    @NamedQuery(name = "CalDate.findByNote", query = "SELECT c FROM CalDate c WHERE c.note = :note"),
    @NamedQuery(name = "CalDate.findByLogin", query = "SELECT c FROM CalDate c WHERE c.login = :login"),
    @NamedQuery(name = "CalDate.findByBooked", query = "SELECT c FROM CalDate c WHERE c.booked = :booked")})
public class CalDate implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_DATE")
    private int idDate;
    @Column(name = "NOTE")
    private String note;
    @Column(name = "LOGIN")
    private String login;
    @Column(name = "BOOKED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date booked;
    @OneToMany(mappedBy = "idDate", fetch = FetchType.EAGER)
    private List<Receipt> receiptList=new ArrayList<>();

    public CalDate() {
    }

    //method do map the recepie whit the Date in the calendar
    public void addReceipt(Receipt receipt)
    {
        this.receiptList.add(receipt);
        receipt.setIdDate(this);
    }
    
    public CalDate(int idDate) {
        this.idDate = idDate;
    }

    public int getIdDate() {
        return idDate;
    }

    public void setIdDate(int idDate) {
        this.idDate = idDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Date getBooked() {
        return booked;
    }

    public void setBooked(Date booked) {
        this.booked = booked;
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
        return "SqlEntities.CalDate[ idDate=" + idDate + " ]";
    }
    
}
