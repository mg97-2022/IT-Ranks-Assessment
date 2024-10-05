package com.it_ranks.employee.controller;

import com.it_ranks.employee.dto.EmployeeDTO;
import com.it_ranks.employee.dto.EmployeeRequestDTO;
import com.it_ranks.employee.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDTO> addEmployee(
            @Valid @RequestBody EmployeeRequestDTO employeeRequestDTO) {
        return ResponseEntity.ok(employeeService.addEmployee(employeeRequestDTO));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getEmployees() {
        return ResponseEntity.ok(employeeService.getEmployees());
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long employeeId) {
        return ResponseEntity.ok(employeeService.getEmployeeById(employeeId));
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployee(
            @PathVariable Long employeeId, @Valid @RequestBody EmployeeRequestDTO employeeRequestDTO) {
        return ResponseEntity.ok(employeeService.updateEmployee(employeeId, employeeRequestDTO));
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long employeeId) {
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.noContent().build();
    }
}
