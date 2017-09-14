/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otherClassys;


import SqlEntities.*;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Daniel
 */
public class FindIds {

    private static EntityManagerFactory emf;
    private static EntityManager em;
    private int lastValue;

    private Query Band_id;
    private Query calDate_id;
    private Query Employe_id;
    private Query Equipment_id;
    private Query Manager_id;
    private Query Receipt;
    private Query SUser_id;
    private Query Services_id;
    private Query Technician_id;
    private Query checkDate;

    public FindIds() {
        this.emf = Persistence.createEntityManagerFactory("RecordingStudio1PU");
        this.em = emf.createEntityManager();
        this.lastValue = 0;
        if (!em.getTransaction().isActive()) {//if transaction is not active basic error check
            em.getTransaction().begin();

            //queris
            Band_id = em.createQuery("SELECT b FROM Band b");//Find All Bands
            calDate_id = em.createQuery("SELECT c FROM CalDate c");//Find all CalDate
            Employe_id = em.createQuery("SELECT e FROM Employe e");//Find all Employe 
            Equipment_id = em.createQuery("SELECT e FROM Equipment e");//Find All equipmpen
            Manager_id = em.createQuery("SELECT m FROM Manager m");//Find all manager
            Receipt = em.createQuery("SELECT r FROM Receipt r");//Find All Recepie 
            SUser_id = em.createQuery("SELECT s FROM SUser s");// Find all users
            Services_id = em.createQuery("SELECT s FROM Service s");// find all Services
            Technician_id = em.createQuery("SELECT t FROM Technician t");//Find All Technicians
          
        }
    }

    //Checks the last band id 
    public int checkLastBandId() {
        lastValue = 0;
        List<Band> bands = Band_id.getResultList();

        for (Band a : bands) {
            if (a.getBandId() > lastValue)//loop between users ids to find the highest
            {
                lastValue = a.getBandId();

            }
        }

        return lastValue + 1;
    }

    //check the last calDate id 
    public int checkLastCalDateId() {
        lastValue = 0;
        List<CalDate> cal = this.calDate_id.getResultList();
        for (CalDate a : cal) {
            if (a.getIdDate() > lastValue)//loop between users ids to find the highest
            {
                lastValue = a.getIdDate();

            }
        }
        return lastValue + 1;
    }

    //check the last employe id
    public int checkLastEmployeId() {
        lastValue = 0;
        List<Employe> em = this.Employe_id.getResultList();
        for (Employe a : em) {
            if (a.getIdEmp() > lastValue)//loop between users ids to find the highest
            {
                lastValue = a.getIdEmp();

            }
        }
        return lastValue + 1;
    }

    //check the lat equipment id
    public int checkLastEquipmentId() {
        lastValue = 0;
        List<Equipment> equip = this.Equipment_id.getResultList();
        for (Equipment a : equip) {
            if (a.getEquipId() > lastValue)//loop between users ids to find the highest
            {
                lastValue = a.getEquipId();

            }
        }
        return lastValue + 1;
    }

    // check the last manager id
    public int checkLastManagerId() {
        lastValue = 0;
        List<Manager> maneger = this.Manager_id.getResultList();
        for (Manager a : maneger) {
            if (a.getMaId() > lastValue)//loop between users ids to find the highest
            {
                lastValue = a.getMaId();

            }
        }
        return lastValue + 1;
    }
   
    //Check the last Recepie id
    public int checkLastReceiptId() {
        lastValue = 0;
        List<Receipt> rece = this.Receipt.getResultList();
        for (Receipt a : rece) {
            if (a.getIdReceipt() > lastValue)//loop between users ids to find the highest
            {
                lastValue = a.getIdReceipt();

            }
        }
        return lastValue + 1;
    }

    // check the last SUser id
    public int checkLastSUserId() {
        lastValue = 0;
        List<SUser> us = this.SUser_id.getResultList();
        for (SUser a : us) {
            if (a.getIdUser() > lastValue)//loop between users ids to find the highest
            {
                lastValue = a.getIdUser();

            }
        }
        return lastValue + 1;
    }

    //Check the last Service id
    public int checkLastServiceId() {
        lastValue = 0;
        List<Service> ser = this.Services_id.getResultList();
        for (Service a : ser) {
            if (a.getServiceId() > lastValue)//loop between users ids to find the highest
            {
                lastValue = a.getServiceId();

            }
        }
        return lastValue + 1;
    }

    //Check the last Technician id
    public int checkLastTechnicianId() {
        lastValue = 0;
        List<Technician> tech = this.Technician_id.getResultList();
        for (Technician a : tech) {
            if (a.getIdTechnician() > lastValue)//loop between users ids to find the highest
            {
                lastValue = a.getIdTechnician();

            }
        }
        return lastValue + 1;
    }
    
   
   
      
    
}
