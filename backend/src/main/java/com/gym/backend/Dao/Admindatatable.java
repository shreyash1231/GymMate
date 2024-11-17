package com.gym.backend.Dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gym.backend.Entity.Admin;


@Repository
public interface Admindatatable extends JpaRepository<Admin,Long> {
    @Query(value="select * from Admin where email=:email and password=:pass",nativeQuery=true)
    Admin verification(@Param("email") String email , @Param("pass") String password);
} 


