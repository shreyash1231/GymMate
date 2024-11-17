package com.gym.backend.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.backend.Dao.Accountdata;
import com.gym.backend.Dao.Admindatatable;
import com.gym.backend.Dao.newmemberdata;
import com.gym.backend.Entity.Account;
import com.gym.backend.Entity.Admin;
import com.gym.backend.Entity.Auth;
import com.gym.backend.Entity.newmember;


@Service
public class servicemem {

   @Autowired
   private Admindatatable admindata;

   @Autowired
   private Accountdata accountdata;

   @Autowired 
   private newmemberdata newMEMBER;

   public void admindata(Admin admin) {
      admindata.save(admin);
   }
   public void memberdata(Account account,newmember newmem) {
      accountdata.save(account);
      newMEMBER.save(newmem);
   }
   public List<String[]> data(Long username) {
      List<Account> acc1 = accountdata.data(username);
      List<newmember> newm1 = newMEMBER.data(username);
      List<String[]> combinedData = new ArrayList<>();
         if (acc1 != null) {
            for(Account account : acc1) {
               String[] s1 = {
                  account.getMemid().toString(),
                  account.getAdminid().toString(),
                  account.getAmountPaid().toString(),
                  account.getAmountRemain().toString(),
                  account.getToTal().toString()
               };
              combinedData.add(s1);
            }
         }
      if (newm1 != null) {
          for (newmember newMember : newm1) {
              String[] s2 = {
                  newMember.getMemberid().toString(),
                  newMember.getAdminid().toString(),
                  newMember.getName().toString(),
                  newMember.getMobile_no().toString(),
                  newMember.getMembership_duration().toString(),
                  Integer.toString(newMember.getMembership_month()),
                  newMember.getMembership_Date().toString()
               };
              combinedData.add(s2);
            }
         }
      return combinedData;
   }
   public List<newmember> checkexpirybyDate(Long username) {
     return newMEMBER.checkexpirybyDate(username);
   }
   public List<Account> checkexpirybyAmount(Long username) {
      long amountremain = 0;
      return accountdata.checkexpirybyAmount(username,amountremain);
   }
   public Account getdatabyid(Long id, Long username) {
      return accountdata.getdatabyid(id,username);
   }
   public void updatedata(Account account, newmember newmem,Long id,Long username) {
      Long amountPaid = account.getAmountPaid();
      Long amountRemain = account.getAmountRemain();
      Long toTal = account.getToTal();
      LocalDate membership_duration = newmem.getMembership_duration();
      int membership_month=newmem.getMembership_month();
      LocalDate membership_Date=newmem.getMembership_Date();
      accountdata.updatedata(amountPaid,amountRemain,toTal,id,username);
      newMEMBER.updatedate(membership_Date,membership_month,membership_duration,id,username);
   }
   public void removemember(Long id,Long username) {
      accountdata.deleteByID(id,username);
      newMEMBER.deletebyID(id,username);
   }
   public Admin verification(Auth auth) {
      String email = auth.getEmail();
      String password = auth.getPassword();
      return admindata.verification(email,password);
   }
   public newmember getmemberdatabyid(Long id, Long username) {
      return newMEMBER.getmemberdatabyid(id,username);
   }
   public Admin checkadmindata(Admin admin) {
      String email = admin.getEmail();
      String password = admin.getPassword();
      return admindata.verification(email,password);
   }
}




















