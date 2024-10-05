package com.it_ranks.employee.mapper;

import com.it_ranks.employee.entity.Employee;
import com.it_ranks.employee.dto.EmployeeDTO;
import com.it_ranks.employee.dto.EmployeeRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeMapper {
    private final BranchMapper branchMapper;

    public Employee toEntity(EmployeeRequestDTO employeeRequestDTO) {
        if (employeeRequestDTO == null) return null;
        return Employee.builder().name(employeeRequestDTO.getName()).age(employeeRequestDTO.getAge())
                       .nationalId(employeeRequestDTO.getNationalId()).build();
    }

    public EmployeeDTO toDTO(Employee employee) {
        if (employee == null) return null;
        return EmployeeDTO.builder().id(employee.getId()).name(employee.getName()).age(employee.getAge())
                          .nationalId(employee.getNationalId()).branch(branchMapper.toDTO(employee.getBranch()))
                          .build();
    }

    public void updateEmployeeMapper(Employee dbEmployee, EmployeeRequestDTO updatedEmployeeRequestDTO) {
        if (dbEmployee == null || updatedEmployeeRequestDTO == null) return;
        dbEmployee.setAge(updatedEmployeeRequestDTO.getAge());
        dbEmployee.setName(updatedEmployeeRequestDTO.getName());
        dbEmployee.setNationalId(updatedEmployeeRequestDTO.getNationalId());
    }
}
