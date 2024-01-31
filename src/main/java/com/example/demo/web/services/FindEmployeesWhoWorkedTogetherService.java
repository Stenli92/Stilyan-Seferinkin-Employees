package com.example.demo.web.services;

import com.example.demo.domain.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.web.models.CommonEmployeesModel;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;

import java.time.LocalDate;
import java.util.*;

@Service
public class FindEmployeesWhoWorkedTogetherService {

    private  final EmployeeRepository repository;

    public FindEmployeesWhoWorkedTogetherService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public ArrayList<CommonEmployeesModel> findEmployeesWorkedTogether() {

        ArrayList<CommonEmployeesModel> commonEmployeesList = new ArrayList<CommonEmployeesModel>();

        List<Employee> employees = this.repository.findAll();

        for (int i = 0; i < employees.size(); i++) {
            for (int j = i + 1; j < employees.size(); j++) {
                Employee employee1 = employees.get(i);
                Employee employee2 = employees.get(j);

                // Check if the time intervals overlap
                if (isOverlap(employee1.getDateFrom(), employee1.getDateTo(),
                        employee2.getDateFrom(), employee2.getDateTo()
                        , employee1.getProjectID() , employee2.getProjectID()))  {

                    Long commonDays = findCommonDays(employee1.getDateFrom() , employee1.getDateTo() ,
                            employee2.getDateFrom() , employee2.getDateTo());

                    if (commonEmployeesList.isEmpty()){
                        CommonEmployeesModel commonEmployees = new CommonEmployeesModel(employee1.getEmpID() ,
                                employee2.getEmpID() , commonDays);

                        commonEmployeesList.add(commonEmployees);
                    } else {
                        if(commonEmployeesList.get(0).getDays() < commonDays){
                            CommonEmployeesModel commonEmployees = new CommonEmployeesModel(employee1.getEmpID() , employee2.getEmpID() , commonDays);

                            commonEmployeesList.clear();
                            commonEmployeesList.add(commonEmployees);
                        }

                        if (Objects.equals(commonEmployeesList.get(0).getDays(), commonDays)){
                            CommonEmployeesModel commonEmployees = new CommonEmployeesModel(employee1.getEmpID() , employee2.getEmpID() , commonDays);
                            commonEmployeesList.add(commonEmployees);
                        }
                    }
                }
            }
        }

        return commonEmployeesList;
    }

    // Helper method to check if time intervals overlap
    private static boolean isOverlap(LocalDate start1, LocalDate end1,
                                     LocalDate start2, LocalDate end2 ,
                                     Long projectId1 , Long projectId2) {
        return !start1.isAfter(end2) && !start2.isAfter(end1) && Objects.equals(projectId1, projectId2);
    }

    private static long findCommonDays(LocalDate start1, LocalDate end1, LocalDate start2, LocalDate end2) {
        // Find the latest start date and earliest end date
        LocalDate commonStartDate = start1.isAfter(start2) ? start1 : start2;
        LocalDate commonEndDate = end1.isBefore(end2) ? end1 : end2;

        // Check if there is an overlap in the periods
        if (commonStartDate.isAfter(commonEndDate)) {
            // There is no overlap, return 0
            return 0;
        } else {
            // Calculate the common days using ChronoUnit
            long days = ChronoUnit.DAYS.between(commonStartDate, commonEndDate) + 1;
            return days;
        }
    }

    public List<Long> findCommonProjects(Long employeeId1 , Long employeeId2){

        List<Employee> allById = this.repository.findAll();

        List<Long> ids = new ArrayList<>();

        for (int i = 0; i < allById.size() ; i++) {

            for (int j = 0; j < allById.size(); j++) {

                Employee employee1 = allById.get(i);
                Employee employee2 = allById.get(j);

                if(Objects.equals(employee1.getEmpID(), employeeId1) && Objects.equals(employee2.getEmpID(), employeeId2)){
                    if (Objects.equals(employee1.getProjectID(), employee2.getProjectID())){
                        ids.add(employee1.getProjectID());
                    }
                }
            }
        }
        return ids;
    }
}
