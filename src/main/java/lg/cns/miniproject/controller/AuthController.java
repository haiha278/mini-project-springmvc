package lg.cns.miniproject.controller;

import lg.cns.miniproject.dto.LoginDTO;
import lg.cns.miniproject.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private AccountService accountService;

    @Autowired
    public AuthController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("loginDTO") LoginDTO loginDTO, Model model) {
        LoginDTO login = accountService.login(loginDTO.getUsername());
        if (login != null && login.getPassword().equals(loginDTO.getPassword())) {
            model.addAttribute("message", "Login successful.");
        } else {
            model.addAttribute("message", "The username or password is incorrect.");
        }
        return "login";
    }
}
