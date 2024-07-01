package lg.cns.miniproject.utils;

import lg.cns.miniproject.dto.employee.AddEmployeeDTO;
import lg.cns.miniproject.dto.employee.EmployeeInfomationDTO;
import lg.cns.miniproject.dto.employee.EmployeeListDTO;
import lg.cns.miniproject.dto.employee.UpdateEmployeeDTO;
import lg.cns.miniproject.exception.employee.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class Validation {

    public boolean checkEmptyOrNull(String str) {
        return str == null || str.trim().isEmpty();
    }

    public boolean validateUsernameFormat(String username) {
        return username.matches("^[A-Za-z0-9]+$");
    }

    public boolean validateNameFormat(String name) {
        if (checkEmptyOrNull(name) || name.length() > 255) {
            return false;
        }
        return true;
    }

    public boolean validatePhoneFormat(String phone) {
        return phone.matches("^0\\d{9}$");
    }

    public boolean validateStartDate(LocalDate startDate) {
        return startDate.isEqual(LocalDate.now()) || startDate.isAfter(LocalDate.now());
    }

    public boolean validateBirthday(LocalDate birthday) {
        return birthday.isAfter(LocalDate.now());
    }

    public boolean validateEmailFormat(String email) {
        return email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }

    public boolean checkPhoneExistForUpdateEmployee(String phone, List<EmployeeInfomationDTO> employeeList) {
        for (EmployeeInfomationDTO employeeInfomationDTO : employeeList) {
            if (employeeInfomationDTO.getPhoneNumber().equals(phone)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkPhoneExistForAddEmployee(String phone, List<EmployeeListDTO> employeeList) {
        for (EmployeeListDTO employeeListDTO : employeeList) {
            if (employeeListDTO.getPhoneNumber().equals(phone)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkEmailExistForUpdateEmployee(String email, List<EmployeeInfomationDTO> employeeList) {
        for (EmployeeInfomationDTO employeeInfomationDTO : employeeList) {
            if (employeeInfomationDTO.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkEmailExistForAddEmployee(String email, List<EmployeeListDTO> employeeList) {
        for (EmployeeListDTO employeeListDTO : employeeList) {
            if (employeeListDTO.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public void validateDataForAddEmployee(AddEmployeeDTO addEmployeeDTO, List<EmployeeListDTO> employeeList) {
        if (!validateNameFormat(addEmployeeDTO.getName())) {
            throw new NameFormatInvalidException("Name is invalid");
        }
        if (!validatePhoneFormat(addEmployeeDTO.getPhoneNumber())) {
            throw new PhoneFormatInvalidException("Phone is invalid");
        }
        if (checkPhoneExistForAddEmployee(addEmployeeDTO.getPhoneNumber(), employeeList)) {
            throw new PhoneExistException("Phone Number is existed");
        }
        if (validateStartDate(addEmployeeDTO.getStartDate())) {
            throw new InvalidDateException("Invalid Date");
        }
        if (validateBirthday(addEmployeeDTO.getDob())) {
            throw new InvalidDateException("Invalid Date");
        }
        if (!validateEmailFormat(addEmployeeDTO.getEmail())) {
            throw new InvalidEmailFormatException("Invalid Email Format");
        }
        if (checkEmailExistForAddEmployee(addEmployeeDTO.getEmail(), employeeList)) {
            throw new EmailExistException("Email is existed");
        }
    }

    public void validateDataForUpdateEmployee(UpdateEmployeeDTO updateEmployeeDTO, List<EmployeeInfomationDTO> employeeList) {
        if (!validateNameFormat(updateEmployeeDTO.getName())) {
            throw new NameFormatInvalidException("Name is invalid");
        }
        if (!validatePhoneFormat(updateEmployeeDTO.getPhoneNumber())) {
            throw new PhoneFormatInvalidException("Phone is invalid");
        }
        if (checkPhoneExistForUpdateEmployee(updateEmployeeDTO.getPhoneNumber(), employeeList)) {
            throw new PhoneExistException("Phone Number is existed");
        }
        if (validateStartDate(updateEmployeeDTO.getStartDate())) {
            throw new InvalidDateException("Invalid Date");
        }
        if (validateBirthday(updateEmployeeDTO.getDob())) {
            throw new InvalidDateException("Invalid Date");
        }
        if (!validateEmailFormat(updateEmployeeDTO.getEmail())) {
            throw new InvalidEmailFormatException("Invalid Email Format");
        }
        if (checkEmailExistForUpdateEmployee(updateEmployeeDTO.getEmail(), employeeList)) {
            throw new EmailExistException("Email is existed");
        }
    }
}
