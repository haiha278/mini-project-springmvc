package lg.cns.miniproject.service.account;

import lg.cns.miniproject.dto.LoginDTO;
import lg.cns.miniproject.dto.RegisterDTO;
import lg.cns.miniproject.entity.Account;
import lg.cns.miniproject.modelMapper.Mapper;
import lg.cns.miniproject.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;
    private Mapper mapper;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, Mapper mapper) {
        this.accountRepository = accountRepository;
        this.mapper = mapper;
    }

    @Override
    public LoginDTO login(String username) {
        Account account = accountRepository.findAccountByUsername(username);
        if (account != null) {
            LoginDTO loginDTO = (LoginDTO) mapper.mapToDTO(account, LoginDTO.class);
            return loginDTO;
        }
        return null;
    }

    @Override
    public int register(RegisterDTO registerDTO) {
        Account account = (Account) mapper.mapToEntity(registerDTO, Account.class);

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
