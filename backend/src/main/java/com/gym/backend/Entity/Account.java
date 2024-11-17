package com.gym.backend.Entity;

import org.hibernate.annotations.JoinColumnOrFormula;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="account")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(nullable=false)
    private Long memid;
    @Column(nullable=false)
    private Long adminid;
    @Column(nullable=false)
    private Long amountPaid;
    @Column(nullable=false,name = "amountRemain")
    private Long amountRemain;
    @Column(nullable=false)
    private Long toTal;
}
