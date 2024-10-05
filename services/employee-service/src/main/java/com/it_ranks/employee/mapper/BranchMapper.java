package com.it_ranks.employee.mapper;

import com.it_ranks.employee.entity.Branch;
import com.it_ranks.employee.dto.BranchDTO;
import org.springframework.stereotype.Component;

@Component
public class BranchMapper {
    public BranchDTO toDTO(Branch branch) {
        if (branch == null) return null;
        return BranchDTO.builder().id(branch.getId()).name(branch.getName()).build();
    }
}
