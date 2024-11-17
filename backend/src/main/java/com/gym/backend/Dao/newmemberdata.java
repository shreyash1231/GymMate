package com.gym.backend.Dao;

import org.springframework.stereotype.Repository;

import com.gym.backend.Entity.newmember;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
public interface newmemberdata extends JpaRepository<newmember,Long> {

    @Query(value="select * from member where memberid=:id and adminid=:userid",nativeQuery=true)
    newmember getmemberdatabyid(@Param("id")Long id,@Param("userid") Long username);
    

    @Modifying
    @Transactional
    @Query(value="delete from member where memberid=:id and adminid=:userid",nativeQuery=true)
    void deletebyID(@Param("id")Long id, @Param("userid")Long username);

    @Query(value="Select * from member where adminid=:userid",nativeQuery=true)
    List<newmember> data(@Param("userid") Long username);

    @Query(value="Select * from member where adminid=:userid and membership_duration=CURDATE()",nativeQuery=true)
    List<newmember> checkexpirybyDate(@Param("userid")Long username);



    @Modifying
    @Transactional
    @Query(value ="update member set membership_Date=:membership_date, membership_month=:membership_Month, membership_duration=:membership_Duration where memberid=:id and adminid=:userid", nativeQuery=true)
    void updatedate(@Param("membership_date") LocalDate membershipDate,@Param("membership_Month") int membershipMonth,@Param("membership_Duration") LocalDate membershipDuration,@Param("id") Long id,@Param("userid") Long username);
}    