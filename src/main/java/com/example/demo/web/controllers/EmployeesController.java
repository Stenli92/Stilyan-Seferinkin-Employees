package com.example.demo.web.controllers;

import com.example.demo.web.models.CommonEmployeesModel;
import org.springframework.ui.Model;
import com.example.demo.domain.entity.Employee;
import com.example.demo.web.services.FindEmployeesWhoWorkedTogetherService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Controller
public class EmployeesController {

    private FindEmployeesWhoWorkedTogetherService employeesWhoWorkedTogetherService;


    public EmployeesController(FindEmployeesWhoWorkedTogetherService employeesWhoWorkedTogetherService) {
        this.employeesWhoWorkedTogetherService = employeesWhoWorkedTogetherService;
    }

    @GetMapping("/employees")
    public String  index(Model model){

        ArrayList<CommonEmployeesModel> employeesWorkedTogether = employeesWhoWorkedTogetherService.findEmployeesWorkedTogether();

        List<Long> commonProjects = employeesWhoWorkedTogetherService.findCommonProjects(employeesWorkedTogether.get(0).getEmployee1Id(), employeesWorkedTogether.get(1).getEmployee2Id());

        StringBuilder sb = new StringBuilder();

        for (Long commonProject : commonProjects) {
            sb.append(commonProject).append(", ");
        }


        model.addAttribute("employees" , employeesWorkedTogether);
        model.addAttribute("commonProjects" , sb.toString());

        return new String("/employees.html");
    }
}
