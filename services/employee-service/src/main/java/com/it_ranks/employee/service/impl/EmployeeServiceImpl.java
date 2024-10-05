package com.it_ranks.employee.service.impl;

import com.it_ranks.employee.dao.BranchDAO;
import com.it_ranks.employee.dao.EmployeeDAO;
import com.it_ranks.employee.entity.Branch;
import com.it_ranks.employee.entity.Employee;
import com.it_ranks.employee.dto.EmployeeDTO;
import com.it_ranks.employee.dto.EmployeeRequestDTO;
import com.it_ranks.employee.mapper.EmployeeMapper;
import com.it_ranks.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeDAO employeeDAO;
    private final BranchDAO branchDAO;
    private final EmployeeMapper employeeMapper;

    @Override
    public EmployeeDTO addEmployee(
            EmployeeRequestDTO employeeRequestDTO) {
        Branch branch = branchDAO.getBranchById(employeeRequestDTO.getBranchId());
        Employee employee = employeeMapper.toEntity(employeeRequestDTO);
        employee.setBranch(branch);
        Employee adedEmployee = employeeDAO.saveEmployee(employee);
        return employeeMapper.toDTO(adedEmployee);
    }

    @Override
    public List<EmployeeDTO> getEmployees() {
        List<Employee> allEmployees = employeeDAO.getAllEmployees();
        return allEmployees.stream().map(employeeMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO getEmployeeById(Long employeeId) {
        return employeeMapper.toDTO(employeeDAO.getEmployeeById(employeeId));
    }


    @Override
    public EmployeeDTO updateEmployee(Long employeeId, EmployeeRequestDTO updatedEmployeeRequestDTO) {
        Employee employee = employeeDAO.getEmployeeById(employeeId);
        employeeMapper.updateEmployeeMapper(employee, updatedEmployeeRequestDTO);
        Branch branch = branchDAO.getBranchById(updatedEmployeeRequestDTO.getBranchId());
        employee.setBranch(branch);
        Employee updatedEmployee = employeeDAO.updateEmployee(employee);
        return employeeMapper.toDTO(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        employeeDAO.deleteEmployee(employeeId);
    }
}
