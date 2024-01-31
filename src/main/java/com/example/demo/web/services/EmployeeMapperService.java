package com.example.demo.web.services;

import com.example.demo.domain.entity.Employee;
import com.example.demo.web.models.EmployeeModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeMapperService {

    private final ModelMapper modelMapper;

    @Autowired
    public EmployeeMapperService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Employee mapToEmployee(EmployeeModel employeeModel, Class<Employee> employeeClass) {
        return modelMapper.map(employeeModel, Employee.class);
    }

}
