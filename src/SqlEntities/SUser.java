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
import javax.persistence.ManyToOne;
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
@Table(name = "S_USER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SUser.findAll", query = "SELECT s FROM SUser s"),
    @NamedQuery(name = "SUser.findByIdUser", query = "SELECT s FROM SUser s WHERE s.idUser = :idUser"),
    @NamedQuery(name = "SUser.findByLogin", query = "SELECT s FROM SUser s WHERE s.login = :login"),
    @NamedQuery(name = "SUser.findByPassword", query = "SELECT s FROM SUser s WHERE s.password = :password")})
public class SUser implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_USER")
    private int idUser;
    @Column(name = "LOGIN")
    private String login;
    @Column(name = "PASSWORD")
    private String password;
    @JoinColumn(name = "ID_EMPLOYE", referencedColumnName = "ID_EMP")
    @ManyToOne(fetch = FetchType.EAGER)
    private Employe idEmploye;
    @OneToMany(mappedBy = "idUser", fetch = FetchType.EAGER)
    private List<Receipt> receiptList=new ArrayList<>();

    public SUser() {
    }

    public void addReceipt(Receipt receipt)
    {
        this.receiptList.add(receipt);
        receipt.setIdUser(this);
    }
    
    public SUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Employe getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(Employe idEmploye) {
        this.idEmploye = idEmploye;
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
        return "SqlEntities.SUser[ idUser=" + idUser + " ]";
    }
    
}
