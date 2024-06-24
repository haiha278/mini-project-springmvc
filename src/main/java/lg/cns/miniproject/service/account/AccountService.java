package lg.cns.miniproject.service.account;

import lg.cns.miniproject.dto.LoginDTO;
import lg.cns.miniproject.dto.RegisterDTO;

public interface AccountService {
    LoginDTO login(String username);

    int register(RegisterDTO registerDTO);
}
