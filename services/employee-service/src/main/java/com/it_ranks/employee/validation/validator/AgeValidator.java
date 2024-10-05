package com.it_ranks.employee.validation.validator;

import com.it_ranks.employee.dto.EmployeeRequestDTO;
import com.it_ranks.employee.validation.annotation.ValidAgeFromNationalId;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class AgeValidator implements ConstraintValidator<ValidAgeFromNationalId, EmployeeRequestDTO> {

    @Override
    public boolean isValid(EmployeeRequestDTO employee, ConstraintValidatorContext context) {
        String nationalId = employee.getNationalId();
        int expectedAge = employee.getAge();

        if (nationalId == null || nationalId.length() < 7) {
            return false;
        }

        String century = nationalId.charAt(0) == '2' ? "19" : "20";
        String year = century + nationalId.substring(1, 3);
        String month = nationalId.substring(3, 5);
        String day = nationalId.substring(5, 7);

        LocalDate birthDate;
        try {
            birthDate = LocalDate.parse(year + "-" + month + "-" + day, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (Exception e) {
            return false;
        }

        int calculatedAge = Period.between(birthDate, LocalDate.now()).getYears();

        return expectedAge == calculatedAge;
    }
}
