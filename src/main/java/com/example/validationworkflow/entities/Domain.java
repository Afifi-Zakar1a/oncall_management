package com.example.validationworkflow.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Domain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String domainName;
    private int achievedLastYear ;
    private int contract;
    private int achievedThisYear;
    @OneToOne
    private Totals totals;

    public Domain(Long id, String domainName, int achievedLastYear, int contract, int achievedThisYear) {
        this.id = id;
        this.domainName = domainName;
        this.achievedLastYear = achievedLastYear;
        this.contract = contract;
        this.achievedThisYear = achievedThisYear;
    }
}

