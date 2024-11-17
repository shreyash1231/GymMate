package com.gym.backend.Controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.gym.backend.Entity.Account;
import com.gym.backend.Entity.Admin;
import com.gym.backend.Entity.Auth;
import com.gym.backend.Entity.Joinentity;
import com.gym.backend.Entity.newmember;
import com.gym.backend.Service.emailauth;
import com.gym.backend.Service.emailauthentication;
import com.gym.backend.Service.otpgenerator;
import com.gym.backend.Service.servicemem;

import jakarta.servlet.http.HttpSession;
 

@RestController

public class memberCon {
     @Autowired
    private servicemem service;

    @Autowired
    private emailauth ema;
   
    @Autowired
    private otpgenerator otpgen;

    @Autowired
    private emailauthentication eauth;
    
    private void checkAuthentication(HttpSession session) {
        if (session.getAttribute("username") == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not authenticated.");
        }
    }
    @GetMapping("/otp/{otp}")
    public String checkotp(@PathVariable("otp") String otp,HttpSession session)
    {
        String num = (String)session.getAttribute("otpgeneration");
        Admin admin =(Admin)session.getAttribute("Data");
        if(num.equals(otp))
        {
            session.invalidate();
            service.admindata(admin);
        }
        else
        {
            return "Invalid OTP";
        }    
        return "Data inserted successfully";
    }

    @PostMapping("/admin")
    public String admindata(@RequestBody Admin admin,HttpSession session)
    {
        Admin adm=service.checkadmindata(admin);
        if(adm!=null)
        {
            checkAuthentication(session);
        }
        else
        {
           Boolean a = eauth.isValidEmail(admin.getEmail());
           if(a==true)
           {
            String em = admin.getEmail();
            String no = otpgen.generateOTP(6); 
            final String subject = "OTP Confirmation";
            ema.sendSimpleEmail(em,subject , no);
            session.setAttribute("otpgeneration", no);
            session.setAttribute("Data",admin);
           }
           else
           {
             return "Valid email";
           }
        }
        return "OTP sent to " + admin.getEmail();
    }
    @PostMapping("/newmember")
    public String memberdata(@RequestBody Joinentity join,HttpSession session)
    {
        checkAuthentication(session);
        Long username = (Long) session.getAttribute("username");
        Account acc = join.getAccount();
        newmember newmem = join.getNewmember();
        acc.setAdminid(username);
        newmem.setAdminid(username);
        LocalDate a = newmem.getMembership_Date();
        int t = newmem.getMembership_month();
        LocalDate endDate = a.plusMonths(t);
        newmem.setMembership_duration(endDate);
        service.memberdata(acc,newmem);
        return "Data inserted successfully";
    }
    @PostMapping("/Authentication")
    public String verification(@RequestBody Auth auth,HttpSession session)
    {
        String s="";
        Admin adm = service.verification(auth);
        if(adm ==null)
        {
            s="Invalid Email or Password";
        }
        else
        {
            session.setAttribute("username", adm.getAdminid());
            s="Data Found Successfully";
        }
        return s;
    }
    @GetMapping("/allmembersdata")
    public List<String[]> data(HttpSession session)
    {
        checkAuthentication(session);
        Long username = (Long) session.getAttribute("username"); 
        return service.data(username);
    }
    @GetMapping("/checkexpirybyDate")
    public List<newmember> checkexpirybyDate(HttpSession session)
    {
        checkAuthentication(session);
        Long username = (Long) session.getAttribute("username"); 
        return service.checkexpirybyDate(username);
    }
    @GetMapping("/checkexpirybyAmount")
    public List<Account> checkexpirybyAmount(HttpSession session)
    {
        checkAuthentication(session);
        Long username = (Long) session.getAttribute("username");
        return service.checkexpirybyAmount(username);
    }
    @PutMapping("/updatedata/{id}")
    public String updatedata(@PathVariable("id")Long id,@RequestBody Joinentity join,HttpSession session )
    {
        checkAuthentication(session);
        Long username = (Long) session.getAttribute("username");
        String s ="";
        Account account = service.getdatabyid(id,username);
        newmember newMember = service.getmemberdatabyid(id,username);
        if(account!=null&&newMember!=null)
        {
            Account acc = join.getAccount();
            newmember newmem =join.getNewmember();
            if(account.getAmountRemain()==0)
            {
                account.setAmountPaid(acc.getAmountPaid());
                account.setToTal(acc.getToTal());
                account.setAmountRemain(acc.getToTal()-acc.getAmountPaid());
                LocalDate a = newmem.getMembership_Date();
                int t = newmem.getMembership_month();
                LocalDate endDate = a.plusMonths(t);
                newMember.setMembership_duration(endDate);
            }
            else
            {
                account.setAmountPaid(account.getAmountPaid()+acc.getAmountPaid());
                account.setAmountRemain(account.getToTal()-account.getAmountPaid());
            }
            service.updatedata(account,newmem,id,username);
            s="Data Updated Successfully";
        }
        return s;
    }
    @DeleteMapping("/removemember/{id}")
    public String removemember(@PathVariable("id") Long id,HttpSession session)
    {
        checkAuthentication(session);
        Long username = (Long) session.getAttribute("username");
        service.removemember(id,username);
        return "Data Removed Successfully";
    }
}
