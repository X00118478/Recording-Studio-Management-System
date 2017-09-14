/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otherClassys;

import SqlEntities.Receipt;

/**
 *
 * @author Daniel
 */
public class ReceitView {

    private String bandName;
    private String numberOfPeopel;
    private String managerName;
    private String managerSurname;
    private String managerContactNumber;
    private String payed;
    private String date;
    private String price;
    private String email;
    
    public void addData(Receipt re)
    {
        this.bandName=re.getBandId().getBandName();
        this.date=re.getIdDate().getBooked().toString();
        this.managerContactNumber=re.getManagerId().getContactnumber();
        this.managerName=re.getManagerId().getManagerFname();
        this.managerSurname=re.getManagerId().getManagerLname();
        this.numberOfPeopel=re.getBandId().getMemberNumber()+"";
        this.payed=re.getPayed();
        this.price=re.getTotalpay();
        this.email=re.getManagerId().getEmail();
    }
    
    
    public ReceitView(Receipt re) {
        this.bandName=re.getBandId().getBandName();
        this.date=re.getIdDate().getBooked().toString();
        this.managerContactNumber=re.getManagerId().getContactnumber();
        this.managerName=re.getManagerId().getManagerFname();
        this.managerSurname=re.getManagerId().getManagerLname();
        this.numberOfPeopel=re.getBandId().getMemberNumber()+"";
        this.payed=re.getPayed();
        this.price=re.getTotalpay();
        this.email=re.getManagerId().getEmail();
    }

    public String getEmail() {
        return email;
    }

    public String getBandName() {
        return bandName;
    }

    public String getNumberOfPeopel() {
        return numberOfPeopel;
    }

    public String getManagerName() {
        return managerName;
    }

    public String getManagerSurname() {
        return managerSurname;
    }

    public String getManagerContactNumber() {
        return managerContactNumber;
    }

    public String getPayed() {
        return payed;
    }

    public String getDate() {
        return date;
    }

    public String getPrice() {
        return price;
    }
    
    
    
    
}
