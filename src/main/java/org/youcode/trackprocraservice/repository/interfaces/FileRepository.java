package org.youcode.trackprocraservice.repository.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.youcode.trackprocraservice.domain.entities.File;

import java.util.UUID;

@Repository
public interface FileRepository extends JpaRepository<File, UUID> {

    // Find files by name (case-insensitive) with pagination
    Page<File> findByNameContainingIgnoreCase(String name, Pageable pageable);

    // Find files by content type with pagination
    Page<File> findByContentType(String contentType, Pageable pageable);

    // Find files by URL with pagination
    Page<File> findByUrl(String url, Pageable pageable);

    // Find files associated with a specific timesheet with pagination
    @Query("SELECT f FROM File f JOIN f.timesheets t WHERE t.id = :timesheetId")
    Page<File> findByTimesheetId(@Param("timesheetId") UUID timesheetId, Pageable pageable);

    // Find files associated with a specific expense report with pagination
    @Query("SELECT f FROM File f JOIN f.expenseReports e WHERE e.id = :expenseReportId")
    Page<File> findByExpenseReportId(@Param("expenseReportId") UUID expenseReportId, Pageable pageable);

    // Check if a file exists by its name
    boolean existsByName(String name);

    // Delete all files associated with a specific timesheet
    @Modifying
    @Query("DELETE FROM File f WHERE f IN (SELECT f FROM File f JOIN f.timesheets t WHERE t.id = :timesheetId)")
    boolean deleteByTimesheetId(@Param("timesheetId") UUID timesheetId);

    // Delete all files associated with a specific expense report
    @Modifying
    @Query("DELETE FROM File f WHERE f IN (SELECT f FROM File f JOIN f.expenseReports e WHERE e.id = :expenseReportId)")
    boolean deleteByExpenseReportId(@Param("expenseReportId") UUID expenseReportId);
}