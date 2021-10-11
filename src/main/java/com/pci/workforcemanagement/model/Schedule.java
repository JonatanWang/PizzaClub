package com.pci.workforcemanagement.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.sql.Date;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Getter
    @Setter
    private int contractTimeMinutes;

    @Getter
    @Setter
    private Date date;

    @Getter
    @Setter
    private boolean isFullDayAbsence;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String personId;

    @Getter
    @Setter
    @OneToMany(cascade = CascadeType.ALL)
    private List<Activity> activities;
}
