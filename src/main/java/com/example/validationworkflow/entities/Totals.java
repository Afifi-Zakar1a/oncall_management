package com.example.validationworkflow.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Totals {
    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int totalAchievedThisYear;
    private int totalContract;
    private int totalAchievedLastYear;


    public Totals(int totalAchievedThisYear, int totalContract, int totalAchievedLastYear) {
        this.totalAchievedThisYear = totalAchievedThisYear;
        this.totalContract = totalContract;
        this.totalAchievedLastYear = totalAchievedLastYear;
    }
}
