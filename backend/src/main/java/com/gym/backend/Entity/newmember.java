package com.gym.backend.Entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="member")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class newmember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable=false)
    private Long memberid;
    @Column(nullable = false)
    private Long adminid;
    @Column(nullable=false,unique=true)
    private String name;
    @Column(length = 10,nullable = false,unique=true)
    private Long mobile_no;
    @Column(nullable=false)
    private LocalDate membership_duration;
    @Column(nullable=false)
    private int membership_month;
    @Column(nullable=false)
    private LocalDate membership_Date;
}
