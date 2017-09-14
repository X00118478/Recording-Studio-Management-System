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
@Table(name = "EQUIPMENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Equipment.findAll", query = "SELECT e FROM Equipment e"),
    @NamedQuery(name = "Equipment.findByEquipId", query = "SELECT e FROM Equipment e WHERE e.equipId = :equipId"),
    @NamedQuery(name = "Equipment.findByNameEq", query = "SELECT e FROM Equipment e WHERE e.nameEq = :nameEq"),
    @NamedQuery(name = "Equipment.findByStateCondition", query = "SELECT e FROM Equipment e WHERE e.stateCondition = :stateCondition"),
    @NamedQuery(name = "Equipment.findByCost", query = "SELECT e FROM Equipment e WHERE e.cost = :cost")})
public class Equipment implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "EQUIP_ID")
    private int equipId;
    @Column(name = "NAME_EQ")
    private String nameEq;
    @Column(name = "STATE_CONDITION")
    private String stateCondition;
    @Column(name = "COST")
    private String cost;
    @ManyToMany(mappedBy = "equipmentList", fetch = FetchType.EAGER)
    private List<Receipt> receiptList=new ArrayList<>();

    public Equipment() {
    }

    public Equipment(int equipId) {
        this.equipId = equipId;
    }

    //add recepie to the equipment
    public void addReceipt(Receipt re)
    {
        this.receiptList.add(re);
//        re.addEquipment(this);
    }
    
    public int getEquipId() {
        return equipId;
    }

    public void setEquipId(int equipId) {
        this.equipId = equipId;
    }

    public String getNameEq() {
        return nameEq;
    }

    public void setNameEq(String nameEq) {
        this.nameEq = nameEq;
    }

    public String getStateCondition() {
        return stateCondition;
    }

    public void setStateCondition(String stateCondition) {
        this.stateCondition = stateCondition;
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
        return "Equipment{" + "equipId=" + equipId + ", nameEq=" + nameEq + ", stateCondition=" + stateCondition + ", cost=" + cost + ", receiptList=" + receiptList + '}';
    }

    

    
    
}
