package lg.cns.miniproject.service.account;

import lg.cns.miniproject.dto.auth.LoginDTO;
import lg.cns.miniproject.dto.auth.RegisterDTO;

public interface AccountService {
    LoginDTO login(LoginDTO loginDTO);

    int register(RegisterDTO registerDTO);
}
