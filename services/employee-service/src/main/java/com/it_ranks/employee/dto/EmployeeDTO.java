package com.it_ranks.employee.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeDTO {
    private long id;
    private String name;
    private String nationalId;
    private int age;
    private BranchDTO branch;
}
