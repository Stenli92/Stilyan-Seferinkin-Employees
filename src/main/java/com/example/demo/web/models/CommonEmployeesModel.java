package com.example.demo.web.models;

public class CommonEmployeesModel {

    private Long employee1Id;
    private Long employee2Id;
    private Long days;

    public CommonEmployeesModel() {
    }

    public CommonEmployeesModel(Long employee1Id, Long employee2Id, Long days) {
        this.employee1Id = employee1Id;
        this.employee2Id = employee2Id;
        this.days = days;
    }

    public Long getEmployee1Id() {
        return employee1Id;
    }

    public void setEmployee1Id(Long employee1Id) {
        this.employee1Id = employee1Id;
    }

    public Long getEmployee2Id() {
        return employee2Id;
    }

    public void setEmployee2Id(Long employee2Id) {
        this.employee2Id = employee2Id;
    }

    public Long getDays() {
        return days;
    }

    public void setDays(Long days) {
        this.days = days;
    }
}
