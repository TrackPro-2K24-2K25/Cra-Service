package org.youcode.trackprocraservice.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.youcode.trackprocraservice.domain.enums.Status;

import java.time.LocalDate;
import java.util.*;

@Entity
@Getter
@Setter
@Table(name = "expense_reports")
public class ExpenseReport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)  // Use AUTO for UUID generation
    private UUID id;


    private String description;

    private Long travelAmount; // Translated column name
    private Long accommodationAmount; // Translated column name
    private Long mealAmount; // Translated column name
    private Long otherAmount;

    @Enumerated(EnumType.STRING)
    private Status status;

    // Calculate total amount
    @Transient
    public Long getTotalAmount() {
        return travelAmount + accommodationAmount + mealAmount + otherAmount;
    }

    @ManyToOne
    @JoinColumn(name = "mission_id")
    private Mission mission;

    @Column(name = "submission_date")
    private LocalDate submissionDate = LocalDate.now();

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "collaborator_id")
    private AppUser collaborateur;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "manager_id")
    private AppUser manager;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "expense_report_files",
            joinColumns = {
                    @JoinColumn(name = "expense_report_id", referencedColumnName = "id")  // Use "id" instead of "expense_report_id"
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "id", referencedColumnName = "id")
            }
    )
    private List<File> files = new ArrayList<>();




}

