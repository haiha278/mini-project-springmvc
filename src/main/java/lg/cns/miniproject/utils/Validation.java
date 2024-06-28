package lg.cns.miniproject.utils;

import lg.cns.miniproject.dto.employee.EmployeeInfomationDTO;
import lg.cns.miniproject.dto.employee.EmployeeListDTO;
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
}
