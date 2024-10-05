package com.it_ranks.employee.service;

import com.it_ranks.employee.dto.EmployeeDTO;
import com.it_ranks.employee.dto.EmployeeRequestDTO;

import java.util.List;

public interface EmployeeService {
    EmployeeDTO addEmployee(EmployeeRequestDTO employeeRequestDTO);

    List<EmployeeDTO> getEmployees();

    EmployeeDTO getEmployeeById(Long employeeId);

    EmployeeDTO updateEmployee(Long employeeId, EmployeeRequestDTO updatedEmployeeRequestDTO);

    void deleteEmployee(Long employeeId);
}
