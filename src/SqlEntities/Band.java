/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SqlEntities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "BAND")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Band.findAll", query = "SELECT b FROM Band b"),
    @NamedQuery(name = "Band.findByBandId", query = "SELECT b FROM Band b WHERE b.bandId = :bandId"),
    @NamedQuery(name = "Band.findByBandName", query = "SELECT b FROM Band b WHERE b.bandName = :bandName"),
    @NamedQuery(name = "Band.findByMemberNumber", query = "SELECT b FROM Band b WHERE b.memberNumber = :memberNumber"),
    @NamedQuery(name = "Band.findByMusicGeners", query = "SELECT b FROM Band b WHERE b.musicGeners = :musicGeners"),
    @NamedQuery(name = "Band.findByAccountCreatedOn", query = "SELECT b FROM Band b WHERE b.accountCreatedOn = :accountCreatedOn")})
public class Band implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "BAND_ID")
    private int bandId;
    @Column(name = "BAND_NAME")
    private String bandName;
    @Column(name = "MEMBER_NUMBER")
    private int memberNumber;
    @Column(name = "MUSIC_GENERS")
    private String musicGeners;
    @Column(name = "ACCOUNT_CREATED_ON")
    @Temporal(TemporalType.TIMESTAMP)
    private Date accountCreatedOn;
    @OneToMany(mappedBy = "bandId", fetch = FetchType.EAGER)
    private List<Receipt> receiptList=new ArrayList<Receipt>();//Arledy defined
    @JoinColumn(name = "MANAGER_ID", referencedColumnName = "MA_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private Manager managerId;

    public Band() {
    }

    public void addReceipt(Receipt re)
    {
        this.receiptList.add(re);
        re.setBandId(this);//add to created recepie a user
    }
    
    public Band(int bandId) {
        this.bandId = bandId;
    }

    public int getBandId() {
        return bandId;
    }

    public void setBandId(int bandId) {
        this.bandId = bandId;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public int getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(int memberNumber) {
        this.memberNumber = memberNumber;
    }

    public String getMusicGeners() {
        return musicGeners;
    }

    public void setMusicGeners(String musicGeners) {
        this.musicGeners = musicGeners;
    }

    public Date getAccountCreatedOn() {
        return accountCreatedOn;
    }

    public void setAccountCreatedOn(Date accountCreatedOn) {
        this.accountCreatedOn = accountCreatedOn;
    }

    @XmlTransient
    public List<Receipt> getReceiptList() {
        return receiptList;
    }

    public void setReceiptList(List<Receipt> receiptList) {
        this.receiptList = receiptList;
    }

    public Manager getManagerId() {
        return managerId;
    }

    public void setManagerId(Manager managerId) {
        this.managerId = managerId;
    }

    

    @Override
    public String toString() {
        return "SqlEntities.Band[ bandId=" + bandId + " ]";
    }
    
}
