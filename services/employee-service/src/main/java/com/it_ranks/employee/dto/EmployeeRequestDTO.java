package com.it_ranks.employee.dto;

import com.it_ranks.employee.validation.annotation.ValidAgeFromNationalId;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@ValidAgeFromNationalId
public class EmployeeRequestDTO {
    @NotBlank(message = "{employee.name.required}")
    @Pattern(regexp = "^[A-Z ]+$", message = "{employee.name.uppercase}")
    private String name;

    @NotBlank(message = "{employee.nationalId.required}")
//    @UniqueNationalId
    @Size(min = 14, max = 14, message = "{employee.nationalId.length}")
    private String nationalId;

    @NotNull(message = "{employee.age.required}")
    @Min(value = 18, message = "{employee.age.min}")
    private int age;

    @NotNull(message = "{employee.branchId.required}")
    @Positive(message = "{employee.branchId.positive}")
    private int branchId;
}
