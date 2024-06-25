package lg.cns.miniproject.utils;

import org.springframework.stereotype.Component;

@Component
public class Validation {

    public boolean checkEmptyOrNull(String str) {
        return str == null || str.trim().isEmpty();
    }
}
