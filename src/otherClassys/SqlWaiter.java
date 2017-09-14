/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otherClassys;


import SqlEntities.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
public class SqlWaiter {

    private static EntityManagerFactory emf;
    private static EntityManager em;
    private Calendar c2;
    private FindIds findId;
    //varibles
    private SUser curent_user;
    private List<Receipt> rec;

    public SUser getCurent_user() {
        return curent_user;
    }

    
    
    public SqlWaiter() {

        this.c2 = new GregorianCalendar();
        this.emf = Persistence.createEntityManagerFactory("RecordingStudio1PU");
        this.em = emf.createEntityManager();
        this.rec = new ArrayList<>();
        this.findId = new FindIds();
        if (!em.getTransaction().isActive()) {//if transaction is not active basic error check
            em.getTransaction().begin();
        }
    }

    public void closeDBConection()
    {
        this.em.close();
        this.emf.close();
    }
    
    public void openDBConection()
    {
        this.emf= Persistence.createEntityManagerFactory("RecordingStudio1PU");
        this.em = emf.createEntityManager();
        
    }

    @Override
    public String toString() {
        return "SqlWaiter{" + "curent_user=" + curent_user + '}';
    }
    
    
    
    //------------------------------------------------
    // Methods to Add
    //-----------------------------------------------
    //Add Band
    public void addband(String bandName, int memberNumber, String musicGeners, Manager mane) {
        if (!em.getTransaction().isActive()) {//if transaction is not active basic error check
            em.getTransaction().begin();
        }
        Date b = c2.getTime(); //Gets curent date and time
        //method to find id of band

        Band band = new Band();
        band.setBandId(findId.checkLastBandId());
        band.setBandName(bandName);
        band.setMemberNumber(memberNumber);
        band.setMusicGeners(musicGeners);
        band.setAccountCreatedOn(b);
        band.setManagerId(mane);//Band need to have managet if the band want to exist
        em.persist(band);
        em.getTransaction().commit();

    }

    //add Calendar Date
    public CalDate addCalendarDate(Date date, String note) {
         if (!em.getTransaction().isActive()) {//if transaction is not active basic error check
            em.getTransaction().begin();
        }
        if(!checkIfBanBookedOnDay(date)){
            System.out.println("Day is not booked yet");
        CalDate cDate = new CalDate();
        cDate.setIdDate(findId.checkLastCalDateId());
        cDate.setBooked(date);
        cDate.setLogin(this.curent_user.getLogin());//What user maked a note
        cDate.setNote(note);
        em.persist(cDate);
        em.getTransaction().commit();
        return cDate;}
        return null;

    }

    //add Employee
    public void addEmploye(String emFirname, String emLastname, String emAddress,
            String emContactnumber, String emTimepartTime, String emWages, String emPremisions,
            Date accountCreatedOn, String email) {
         if (!em.getTransaction().isActive()) {//if transaction is not active basic error check
            em.getTransaction().begin();
        }
        Employe employee = new Employe();
        employee.setIdEmp(findId.checkLastEmployeId());
        employee.setEmFirname(emFirname);
        employee.setEmLastname(emLastname);
        employee.setEmAddress(emAddress);
        employee.setEmContactnumber(emContactnumber);
        employee.setEmTimepartTime(emTimepartTime);
        employee.setEmWages(emWages);
        employee.setEmPremisions(emPremisions);
        employee.setAccountCreatedOn(accountCreatedOn);
        employee.setEmail(email);
      //  employee.addUser(user);
        em.persist(employee);
        em.getTransaction().commit();

    }

    
    
    

    //add Equipment
    public void addEEquipment(String nameEq, String stateCondition, String cost) {
         if (!em.getTransaction().isActive()) {//if transaction is not active basic error check
            em.getTransaction().begin();
        }
        Equipment equip = new Equipment();
        equip.setEquipId(findId.checkLastEquipmentId());
        equip.setNameEq(nameEq);
        equip.setStateCondition(stateCondition);
        equip.setCost(cost);
        em.persist(equip);
        em.getTransaction().commit();
    }

    //add Manager
    public void addManager(String managerFname, String managerLname, String addres, String contactnumber,String email) {
         if (!em.getTransaction().isActive()) {//if transaction is not active basic error check
            em.getTransaction().begin();
        }
        Manager manager = new Manager();
        manager.setMaId(findId.checkLastManagerId());
        manager.setManagerFname(managerFname);
        manager.setManagerLname(managerLname);
        manager.setAddres(addres);
        manager.setContactnumber(contactnumber);
        manager.setEmail(email);
        em.persist(manager);
        em.getTransaction().commit();
    }

    //add RecConect


    //add Recepie

    public void addRecepie(String totalpay, String payed, Band bandId, CalDate idDate, Manager managerId,
            SUser idUser,List<Equipment> eq , List<Service> ser,List<Technician> tech) {
         if (!em.getTransaction().isActive()) {//if transaction is not active basic error check
            em.getTransaction().begin();
        }
        Receipt recepie = new Receipt();
        recepie.setIdReceipt(findId.checkLastReceiptId());
        recepie.setTotalpay(totalpay);
        recepie.setPayed(payed);
        recepie.setBandId(bandId);
        recepie.setIdDate(idDate);
        recepie.setManagerId(managerId);
        recepie.setIdUser(idUser);
        recepie.setEquipmentList(eq);
        recepie.setServiceList(ser);
        recepie.setTechnicianList(tech);
      //  System.out.println(recepie.toString());
        em.persist(recepie);
        em.getTransaction().commit();
    }

    
    //add SUser
    public void addSUser(String login, String password, Employe idEmploye) {
         if (!em.getTransaction().isActive()) {//if transaction is not active basic error check
            em.getTransaction().begin();
        }
        SUser sUser = new SUser();
        sUser.setIdUser(findId.checkLastSUserId());
        sUser.setLogin(login);
        sUser.setPassword(password);
        sUser.setIdEmploye(idEmploye);
        em.persist(sUser);
        em.getTransaction().commit();
    }

    //Add Service

    public void addService(String typeofService, String cost) {
         if (!em.getTransaction().isActive()) {//if transaction is not active basic error check
            em.getTransaction().begin();
        }
        Service service = new Service();
        service.setServiceId(findId.checkLastServiceId());
        service.setTypeofService(typeofService);
        service.setCost(cost);
        em.persist(service);
        em.getTransaction().commit();
    }

    //add Technician
    public void addTechnician(String typeofService, String cost, Employe emp) {
         if (!em.getTransaction().isActive()) {//if transaction is not active basic error check
            em.getTransaction().begin();
        }
        Technician tech = new Technician();
        tech.setIdTechnician(findId.checkLastTechnicianId());
        tech.setTechnicianjob(typeofService);
        tech.setCost(cost);
        tech.setIdEmploye(emp);//Technician need an employee 
        
        em.persist(tech);
        em.getTransaction().commit();
    }

    //------------------------------------------------
    // Methods to Update
    //-----------------------------------------------
    //Update Band
    public void updateBand(int id, String bandName, int numberPeople, String geners, Manager manager) {
        if (!em.getTransaction().isActive()) {//if transaction is not active basic error check
            em.getTransaction().begin();
        }
        Query band = em.createQuery("SELECT b FROM Band b WHERE b.bandId =" + id);//Find All Bands
        try {
            Band b = (Band) band.getSingleResult();
            b.setBandName(bandName);
            b.setManagerId(manager);
            b.setMemberNumber(numberPeople);
            b.setMusicGeners(geners);
            em.merge(b);
            em.getTransaction().commit();
        } catch (NoResultException e) {
            System.out.print("Uppdate band Error " + e);
        }
    }

    //Update Calendar Date

    public void updateCalendar(int id, String note, Date booked) {
        if (!em.getTransaction().isActive()) {//if transaction is not active basic error check
            em.getTransaction().begin();
        }
        Query calendar = em.createQuery("SELECT c FROM CalDate c WHERE c.idDate = " + id);//Find All Bands
        try {
            CalDate cd = (CalDate) calendar.getSingleResult();
            cd.setNote(note);
            cd.setBooked(booked);
            em.merge(cd);
            em.getTransaction().commit();
        } catch (NoResultException e) {
            System.out.print("Uppdate Callendar Error " + e);
        }
    }

    //Update Employe

    public void updateEmploye(int id, String firstName, String lastName, String addres, String contactNumber, String time, String wage, String premision,String email) {
        if (!em.getTransaction().isActive()) {//if transaction is not active basic error check
            em.getTransaction().begin();
        }
        Query employee = em.createQuery("SELECT e FROM Employe e WHERE e.idEmp =" + id);//Find All Bands
        try {
            Employe emp = (Employe) employee.getSingleResult();
            emp.setEmFirname(firstName);
            emp.setEmLastname(lastName);
            emp.setEmAddress(addres);
            emp.setEmContactnumber(contactNumber);
            emp.setEmTimepartTime(time);
            emp.setEmWages(wage);
            emp.setEmail(email);
            emp.setEmPremisions(premision);
            em.merge(emp);
            em.getTransaction().commit();
        } catch (NoResultException e) {

        }
    }

    //Update Equipment

    public void updateEquipment(int id, String eqName, String condytion, String cost) {
        if (!em.getTransaction().isActive()) {//if transaction is not active basic error check
            em.getTransaction().begin();
        }
        Query equipment = em.createQuery("SELECT e FROM Equipment e WHERE e.equipId =" + id);//Find All Bands
        try {
            Equipment eq = (Equipment) equipment.getSingleResult();
            eq.setNameEq(eqName);
            eq.setStateCondition(condytion);
            eq.setCost(cost);
            em.merge(eq);
            em.getTransaction().commit();
        } catch (NoResultException e) {

        }
    }

    //Update Manager

    public void updateManager(int id, String managerFName, String managerLName, String addres, String contactNumber) {
        if (!em.getTransaction().isActive()) {//if transaction is not active basic error check
            em.getTransaction().begin();
        }
        Query manager = em.createQuery("SELECT m FROM Manager m WHERE m.maId = " + id);//Find All Bands
        try {
            Manager mana = (Manager) manager.getSingleResult();
            mana.setAddres(addres);
            mana.setManagerFname(managerFName);
            mana.setManagerLname(managerLName);
            mana.setContactnumber(contactNumber);
            em.merge(mana);
            em.getTransaction().commit();
        } catch (NoResultException e) {

        }
    }

    //Update RecConect
//    public void updateRecConect(int id)
//    {
//        Query Band_id = em.createQuery("SELECT b FROM Band b");//Find All Bands
//         try{
//         
//         }
//         catch(NoResultException e)
//         {
//             
//         }
//    }
    //Update Recepie

    public void updateRecepie(int id, String totalPay, String pay, Band bandId, Manager managerId, CalDate dateId, SUser userId,List<Service> servList,List<Equipment> equList,List<Technician> techList) {
        if (!em.getTransaction().isActive()) {//if transaction is not active basic error check
            em.getTransaction().begin();
        }
        Query recepie = em.createQuery("SELECT r FROM Receipt r WHERE r.idReceipt =" + id);//Find All Bands
        try {
            Receipt re = (Receipt) recepie.getSingleResult();
            re.setPayed(pay);
            re.setTotalpay(totalPay);
            re.setBandId(bandId);
            re.setIdDate(dateId);
            re.setIdUser(userId);
            re.setManagerId(managerId);
           re.setEquipmentList(equList);
           re.setServiceList(servList);
           re.setTechnicianList(techList);
            em.merge(re);
            em.getTransaction().commit();
        } catch (NoResultException e) {

        }
    }

    //Update SUser

    public void updateUser(int id, String login, String password, Employe employeId) {
        if (!em.getTransaction().isActive()) {//if transaction is not active basic error check
            em.getTransaction().begin();
        }
        Query user = em.createQuery("SELECT s FROM SUser s WHERE s.idUser=" + id);//Find All Bands
        try {
            SUser us = (SUser) user.getSingleResult();
            us.setIdEmploye(employeId);
            us.setLogin(login);
            us.setPassword(password);
            em.merge(us);
            em.getTransaction().commit();
        } catch (NoResultException e) {

        }
    }

    //Update Service

    public void updateService(int id, String typeOfService, String cost) {
        if (!em.getTransaction().isActive()) {//if transaction is not active basic error check
            em.getTransaction().begin();
        }
        Query service = em.createQuery("SELECT s FROM Service s WHERE s.serviceId =" + id);//Find All Bands
        try {
            Service se = (Service) service.getSingleResult();
            se.setCost(cost);
            se.setTypeofService(typeOfService);
            em.merge(se);
            em.getTransaction().commit();
        } catch (NoResultException e) {

        }
    }

    //Update Technician

    public void updateTechnician(int id, String technicianJob, Employe idEmploye, String cost) {
        if (!em.getTransaction().isActive()) {//if transaction is not active basic error check
            em.getTransaction().begin();
        }
        Query technician = em.createQuery("SELECT t FROM Technician t WHERE t.idTechnician = " + id);//Find All Bands
        try {
            Technician tech = (Technician) technician.getSingleResult();
            tech.setCost(cost);
            tech.setTechnicianjob(technicianJob);
            tech.setIdEmploye(idEmploye);
            em.merge(tech);
            em.getTransaction().commit();
        } catch (NoResultException e) {

        }
    }
    //-----------------------------------------------
    // Methods to Delete 
    //-----------------------------------------------
    
     //Delete Band
    public void deleteBand(int id) {
        if (!em.getTransaction().isActive()) {//if transaction is not active basic error check
            em.getTransaction().begin();
        }
        //Band bd = em.find(Band.class, id);
        Query band = em.createQuery("Select b FROM Band b WHERE b.bandId ="+id);
        
        em.remove(band.getSingleResult());
        em.getTransaction().commit();
    }
    
    //Delete Calendar Date
    public void deleteCalendarDate(int id) {
        if (!em.getTransaction().isActive()) {//if transaction is not active basic error check
            em.getTransaction().begin();
        }
      //  CalDate cd = em.find(CalDate.class, id);
        Query calendar = em.createQuery("Select c FROM CalDate c WHERE c.idDate = "+id);
        
        em.remove(calendar.getSingleResult());
        em.getTransaction().commit();
    }
    
    //Delete Employe
    public void deleteEmployee(int id) {
        if (!em.getTransaction().isActive()) {//if transaction is not active basic error check
            em.getTransaction().begin();
        }
        //Employe emp = em.find(Employe.class, id);
        Query employe = em.createQuery("Select e FROM Employe e WHERE e.idEmp ="+id);
        
        em.remove(employe.getSingleResult());
        em.getTransaction().commit();
    }
    
    //Delete Equipment
    public void deleteEquipment(int id) {
        if (!em.getTransaction().isActive()) {//if transaction is not active basic error check
            em.getTransaction().begin();
        }
        //Equipment equ = em.find(Equipment.class, id);
        Query equipment = em.createQuery("Select e FROM Equipment e WHERE e.equipId ="+id);
        
        em.remove(equipment.getSingleResult());
        em.getTransaction().commit();
    }
    
    //Delete Manager
    public void deleteManager(int id) {
        if (!em.getTransaction().isActive()) {//if transaction is not active basic error check
            em.getTransaction().begin();
        }
        //Manager m = em.find(Manager.class, id);
        Query manager = em.createQuery("Select m FROM Manager m WHERE m.maId = "+id);
        Manager singleResult =(Manager) manager.getSingleResult();
       
        em.remove(singleResult);
        em.getTransaction().commit();
    }
    
    
    //Delete Recepie
    public void deleteRecepie(int id) {
        if (!em.getTransaction().isActive()) {//if transaction is not active basic error check
            em.getTransaction().begin();
        }
        //Receipt r = em.find(Receipt.class, id);
        Query recepie = em.createQuery("SELECT r FROM Receipt r WHERE r.idReceipt ="+id);
        Receipt singleResult =(Receipt) recepie.getSingleResult();
        em.remove(singleResult.getIdDate());
        em.remove(singleResult);
        em.getTransaction().commit();
    }
    
    //Delete SUser
    public void deleteSUser(int id) {
        if (!em.getTransaction().isActive()) {//if transaction is not active basic error check
            em.getTransaction().begin();
        }
        //SUser su = em.find(SUser.class, id);
        Query user = em.createQuery("Select s FROM SUser s WHERE s.idUser="+id);
        
        em.remove(user.getSingleResult());
        em.getTransaction().commit();
    }
    
    //Delete Service
    public void deleteService(int id) {
        if (!em.getTransaction().isActive()) {//if transaction is not active basic error check
            em.getTransaction().begin();
        }
        //Service ser = em.find(Service.class, id);
        Query service = em.createQuery("Select s FROM Service s WHERE s.serviceId ="+id);
        
        em.remove(service.getSingleResult());
        em.getTransaction().commit();
    }
    
    //Delete Technician
    public void deleteTechnician(int id) {
        if (!em.getTransaction().isActive()) {//if transaction is not active basic error check
            em.getTransaction().begin();
        }
        //Technician tech = em.find(Technician.class, id);
        Query technician = em.createQuery("Select t FROM Technician t WHERE t.idTechnician = "+id);
        
        em.remove(technician.getSingleResult());
        em.getTransaction().commit();
    }


    
    //-----------------------------------
    //Other Methods
    //-----------------------------------
    
    //Login 
    public boolean login(String login, String password) {
        Query sUser = em.createQuery("SELECT s FROM SUser s WHERE s.login ='" + login + "'");//Find user By the 
        try {

            SUser user = (SUser) sUser.getSingleResult();
            if (user.getPassword().equals(password)) {
                this.curent_user = user;
                return true;
            } else {
                return false;
            }
        } catch (NoResultException e) {
            return false;
        }

    }

    //------------------------------------------------
    // Read 
    //-----------------------------------------------
    //All Recepie
    public List<Receipt> getAllRecepies() {
        Query recepie = em.createQuery("SELECT r FROM Receipt r");//Find All Recepie 
        List<Receipt> re = recepie.getResultList();
        return re;
    }

    

    public Manager getManagerByID(int id)
    {
        Query res = em.createQuery("SELECT m FROM Manager m WHERE m.maId ="+id);
        return (Manager) res.getSingleResult();
    }
    
    public List<Manager> getAllManagers()
    {
         Query res = em.createQuery("SELECT m FROM Manager m");
         return  res.getResultList();
    }
    
    public Employe getEmpByID(int id)
    {
        Query res = em.createQuery("SELECT e FROM Employe e WHERE e.idEmp ="+id);
         return (Employe) res.getSingleResult();
    }
    
    public Equipment getEquipByID(int id)
    {
        Query res = em.createQuery("SELECT e FROM Equipment e WHERE e.equipId ="+id);
         return (Equipment) res.getSingleResult();
    }
    
    public Service getServiceByID(int id)
    {
        Query res = em.createQuery("SELECT s FROM Service s WHERE s.serviceId ="+id);
         return (Service) res.getSingleResult();
    }
    
    public Technician getTechnByID(int id)
    {
        Query res = em.createQuery("SELECT t FROM Technician t WHERE t.idTechnician = "+id);
         return (Technician) res.getSingleResult();
    }
    
    public List<Employe> getAllEmplList()
    {
         Query res = em.createQuery("SELECT e FROM Employe e");
         return  res.getResultList();
    }
    
    public List<Technician> getAllTechnicians()
    {
        Query res = em.createQuery("SELECT t FROM Technician t");
         return  res.getResultList();
    }
    
    public List<Service> getAllServices()
    {
        Query res = em.createQuery("SELECT s FROM Service s");
         return  res.getResultList();
    }
    
    public List<Equipment> getAllEquipment()
    {
        Query res = em.createQuery("SELECT e FROM Equipment e");
         return  res.getResultList();
    }
    
    public Employe getEmployeeByName(String name)
    {
          Query res = em.createQuery("SELECT e FROM Employe e WHERE e.emFirname = '"+name+"'");
         return (Employe)  res.getSingleResult();
       
    }
    
      public Band getBandById(int id)
    {
          Query res = em.createQuery("SELECT b FROM Band b WHERE b.bandId ="+id);
         return (Band)  res.getSingleResult();
       
    }
    
      public List<Band> getAllBands()
    {
          Query res = em.createQuery("SELECT b FROM Band b");
         return res.getResultList();
       
    } 
      
      public Receipt getReceiptById(int id)
    {
          Query res = em.createQuery("SELECT r FROM Receipt r WHERE r.idReceipt = "+id);
         return (Receipt) res.getSingleResult();
       
    }  
    
   public CalDate getCaleDateByid(int id)
    {
          Query res = em.createQuery("SELECT c FROM CalDate c WHERE c.idDate ="+id);
         return (CalDate)  res.getSingleResult();
       
    }
      
    public List<CalDate> getAllCallDate()
    {
          Query res = em.createQuery("SELECT c FROM CalDate c");
         return   res.getResultList();
       
    }
    
    public List<SUser> getAllUsers()
    {
          Query res = em.createQuery("SELECT s FROM SUser s");
         return   res.getResultList();
       
    }
    public SUser getUserByLogin(String login)
    {
          Query res = em.createQuery("SELECT s FROM SUser s WHERE s.login = '"+login+"'");
         return (SUser)  res.getSingleResult();
       
    }
    
    
     public boolean checkIfBanBookedOnDay(Date date) {
        List<CalDate> allCallDate = this.getAllCallDate();
        int compareTo = date.compareTo(date);
        for(CalDate e : allCallDate)
        {
            if(compareTo==e.getBooked().compareTo(date))
            {
                return true;
            }
        }
        return false;
    }
   
     public String checkNoteForTheDay(Date date)
     {
         List<CalDate> allCallDate = this.getAllCallDate();
        int compareTo = date.compareTo(date);
        for(CalDate e : allCallDate)
        {
            if(compareTo==e.getBooked().compareTo(date))
            {
                return e.getNote()+"\n note made by user :"+e.getLogin();
            }
        }
         return "Ther is no Note on this Day";
     }
     
     public boolean checkIfLoginExist(String login)
     {
          try {
              SUser userByLogin = this.getUserByLogin(login);
              return true;
            
        } catch (NoResultException e) {
            return false;
        }
        
                 
     }
     
     
}
