package com.example.demo.web.models;

import jakarta.persistence.Column;

import java.time.LocalDate;

public class EmployeeModel {

    private String EmpID;
    private String ProjectID;
    private String DateFrom;
    private String DateTo;

    public EmployeeModel(String empID, String projectID, String dateFrom, String dateTo) {
        EmpID = empID;
        ProjectID = projectID;
        DateFrom = dateFrom;
        DateTo = dateTo;
    }

    public String getEmpID() {
        return EmpID;
    }

    public void setEmpID(String empID) {
        EmpID = empID;
    }

    public String getProjectID() {
        return ProjectID;
    }

    public void setProjectID(String projectID) {
        ProjectID = projectID;
    }

    public String getDateFrom() {
        return DateFrom;
    }

    public void setDateFrom(String dateFrom) {
        DateFrom = dateFrom;
    }

    public String getDateTo() {
        return DateTo;
    }

    public void setDateTo(String dateTo) {
        DateTo = dateTo;
    }
}
