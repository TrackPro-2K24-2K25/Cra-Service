package org.youcode.trackprocraservice.domain.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import org.youcode.trackprocraservice.domain.enums.Status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Timesheet {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ElementCollection
    private List<Comments> comments;

    @ElementCollection
    private List<WorkPeriod> selectedPeriods;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Getter
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "timesheet_fichiers",
            joinColumns = { @JoinColumn(name = "timesheet_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "file_id", referencedColumnName = "id")}
    )
    private List<File> files = new ArrayList<>();







}
