package com.example.demo.web.controllers;

import com.example.demo.domain.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.web.models.EmployeeModel;
import com.example.demo.web.services.CSVService;
import com.example.demo.web.services.EmployeeMapperService;
import com.example.demo.web.services.FindEmployeesWhoWorkedTogetherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Controller
public class HomeController {


    @Autowired
    CSVService fileService;

    private final EmployeeRepository repository;
    private final EmployeeMapperService employeeMapperService;

    public HomeController(EmployeeRepository repository, EmployeeMapperService employeeMapperService) {
        this.repository = repository;
        this.employeeMapperService = employeeMapperService;
    }

    @GetMapping("/")
    public String  index(){
        return "/index.html";
    }


    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) throws Exception {

        List<List<String>> employees = fileService.readCsvData(file);

        System.out.println(employees);
        employees.stream().skip(1).map(e -> new EmployeeModel(e.get(0).trim(), e.get(1).trim(), e.get(2).trim(), e.get(3).trim()))
                .forEach(employeeModel -> {

                    if (!Objects.equals(employeeModel.getDateTo(), "NULL")){
                        this.repository.save(employeeMapperService.mapToEmployee(employeeModel , Employee.class));
                    }else {
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        LocalDateTime now = LocalDateTime.now();
                        employeeModel.setDateTo(dtf.format(now));
                        this.repository.save(employeeMapperService.mapToEmployee(employeeModel , Employee.class));
                    }
                });
        return "redirect:/employees";
    }
}
