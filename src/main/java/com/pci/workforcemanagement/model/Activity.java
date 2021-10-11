package com.pci.workforcemanagement.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Getter
    @Setter
    private String color;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private Date start;

    @Getter
    @Setter
    private int minutes;
}
