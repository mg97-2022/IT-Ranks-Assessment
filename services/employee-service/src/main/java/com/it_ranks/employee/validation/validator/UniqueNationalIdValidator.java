package com.it_ranks.employee.validation.validator;

import com.it_ranks.employee.dao.EmployeeDAO;
import com.it_ranks.employee.validation.annotation.UniqueNationalId;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniqueNationalIdValidator implements ConstraintValidator<UniqueNationalId, String> {
    private final EmployeeDAO employeeDAO;

    @Override
    public boolean isValid(String nationalId, ConstraintValidatorContext context) {
        if (nationalId == null) return false;
        return employeeDAO.getEmployeesCountByNationalId(nationalId) == 0;
    }
}
