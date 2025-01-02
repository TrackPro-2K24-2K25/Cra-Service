package org.youcode.trackprocraservice.domain.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.youcode.trackprocraservice.domain.embeddables.Comments;
import org.youcode.trackprocraservice.domain.embeddables.WorkPeriod;
import org.youcode.trackprocraservice.domain.enums.Status;

import java.time.YearMonth;
import java.util.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
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

    @ManyToOne(cascade = CascadeType.PERSIST)  // Or appropriate cascade type
    @JoinColumn(name = "collaborator_id")
    private AppUser collaborateur;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "manager_id")
    private AppUser manager;

    @Getter
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "timesheet_fichiers",
            joinColumns = { @JoinColumn(name = "timesheet_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "file_id", referencedColumnName = "id")}
    )
    private List<File> files = new ArrayList<>();

    @ElementCollection
    @MapKeyColumn(name = "day")
    @Column(name = "value")
    private Map<String, String> valeur_jour = new HashMap<>();

    public Timesheet() {
        initializeDayValuesForMonth();
    }

    private void initializeDayValuesForMonth() {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        YearMonth yearMonth = YearMonth.of(year, month);
        int daysInMonth = yearMonth.lengthOfMonth();


        for (int day = 1; day <= daysInMonth; day++) {
            valeur_jour.put("day" + day, "");
        }
    }







}
