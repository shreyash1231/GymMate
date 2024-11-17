package com.gym.backend.Dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gym.backend.Entity.Account;

import jakarta.transaction.Transactional;

@Repository
public interface Accountdata extends JpaRepository<Account,Long> {
    @Query(value="select * from account where adminid=:userid and amount_remain>:amountremain",nativeQuery=true)
    List<Account> checkexpirybyAmount(@Param("userid")Long username,@Param("amountremain") Long amountremain);

    @Query(value="select * from account where memid=:id and adminid=:userid",nativeQuery=true)
    Account getdatabyid(@Param("id")Long id,@Param("userid") Long username);

    @Modifying
    @Transactional
    @Query(value="delete from account where memid=:id and adminid=:userid",nativeQuery=true)
    void deleteByID(@Param("id")Long id, @Param("userid")Long username);

    @Query(value="Select * from account where adminid=:userid",nativeQuery=true)
    List<Account> data(@Param("userid")Long username);

    @Modifying
    @Transactional
    @Query(value="update account set amount_paid=:amountpaid, amount_remain=:amountremain, to_Tal=:total where memid=:id and adminid=:userid", nativeQuery = true)
    void updatedata(@Param("amountpaid") Long amountPaid,@Param("amountremain") Long amountRemain,@Param("total") Long total,@Param("id") Long id,@Param("userid") Long username);
}


