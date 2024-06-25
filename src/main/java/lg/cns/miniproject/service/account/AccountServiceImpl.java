package lg.cns.miniproject.service.account;

import lg.cns.miniproject.dto.LoginDTO;
import lg.cns.miniproject.dto.RegisterDTO;
import lg.cns.miniproject.entity.Account;
import lg.cns.miniproject.exception.account.InvalidUsernamePasswordException;
import lg.cns.miniproject.exception.account.PasswordDoNotMatchException;
import lg.cns.miniproject.exception.account.UsernameExistedException;
import lg.cns.miniproject.modelMapper.Mapper;
import lg.cns.miniproject.repository.AccountRepository;
import lg.cns.miniproject.utils.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;
    private Mapper mapper;
    private Validation validation;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, Mapper mapper, Validation validation, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.mapper = mapper;
        this.validation = validation;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public LoginDTO login(LoginDTO loginDTO) {
        if (validation.checkEmptyOrNull(loginDTO.getUsername()) || validation.checkEmptyOrNull(loginDTO.getPassword())) {
            throw new InvalidUsernamePasswordException("Username or password cannot be empty");
        }
        Account account = accountRepository.findAccountByUsername(loginDTO.getUsername());
        if (account != null) {
            LoginDTO login = (LoginDTO) mapper.mapToDTO(account, LoginDTO.class);
            return login;
        }
        return null;
    }

    @Override
    public int register(RegisterDTO registerDTO) {
        if (!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())) {
            throw new PasswordDoNotMatchException("Password do not match");
        }
        if (accountRepository.findAccountByUsername(registerDTO.getUsername()) != null) {
            throw new UsernameExistedException("Username already existed");
        }
        Account account = (Account) mapper.mapToEntity(registerDTO, Account.class);
        account.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        int row_effected = accountRepository.createAccount(account);

        if (row_effected == 1) {
            account.setCreateBy(account.getId());
            account.setUpdateBy(account.getId());
            accountRepository.updateAccount(account);
            return row_effected;
        }
        return 0;
    }

}
