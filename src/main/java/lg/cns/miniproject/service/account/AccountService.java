package lg.cns.miniproject.service.account;

import lg.cns.miniproject.dto.LoginDTO;

public interface AccountService {
    LoginDTO login(String username);
}
