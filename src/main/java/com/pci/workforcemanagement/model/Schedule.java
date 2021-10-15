package com.pci.workforcemanagement.model;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
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
    @Nullable
    private List<Activity> activities;
}
