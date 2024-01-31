package com.example.demo.domain.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;


@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @Column(name= "EmpID")
    private Long EmpID;

    @Column(name= "ProjectID")
    private Long ProjectID;

    @Column(name= "DateFrom")
    private LocalDate DateFrom;
    @Column(name= "DateTo")
    private LocalDate DateTo;

    public Employee() {
    }

    public Employee(Long empID, Long projectID, LocalDate dateFrom, LocalDate dateTo) {
        EmpID = empID;
        ProjectID = projectID;
        DateFrom = dateFrom;
        DateTo = dateTo;
    }

    public Long getEmpID() {
        return EmpID;
    }

    public void setEmpID(Long empID) {
        EmpID = empID;
    }

    public Long getProjectID() {
        return ProjectID;
    }

    public void setProjectID(Long projectID) {
        ProjectID = projectID;
    }

    public LocalDate getDateFrom() {
        return DateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        DateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return DateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        DateTo = dateTo;
    }
}
