package com.it_ranks.employee.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BranchDTO {
    private int id;
    private String name;
}
