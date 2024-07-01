package lg.cns.miniproject.security;

import lg.cns.miniproject.entity.Account;
import lg.cns.miniproject.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomAccountDetailService implements UserDetailsService {

    private AccountRepository accountRepository;

    @Autowired
    public CustomAccountDetailService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findAccountByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomAccountDetails(account);
    }
}
