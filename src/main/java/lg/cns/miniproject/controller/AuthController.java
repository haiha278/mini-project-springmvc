package lg.cns.miniproject.controller;

import lg.cns.miniproject.dto.auth.LoginDTO;
import lg.cns.miniproject.dto.auth.RegisterDTO;
import lg.cns.miniproject.exception.account.InvalidUsernamePasswordException;
import lg.cns.miniproject.exception.account.PasswordDoNotMatchException;
import lg.cns.miniproject.exception.account.UsernameExistedException;
import lg.cns.miniproject.exception.account.UsernameInvalidFormatException;
import lg.cns.miniproject.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AccountService accountService;

    @Autowired
    public AuthController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }
//
//    @PostMapping("/login")
//    public String login(@ModelAttribute("loginDTO") LoginDTO loginDTO, Model model) {
//        try {
//            LoginDTO login = accountService.login(loginDTO);
//            if (login != null) {
//                model.addAttribute("noti", "Congratulations!");
//                model.addAttribute("message", "Login successful.");
//            } else {
//                model.addAttribute("noti", "Opps!");
//                model.addAttribute("message", "The username or password is incorrect.");
//            }
//            return "login";
//        } catch (InvalidUsernamePasswordException invalidUsernamePasswordException) {
//            model.addAttribute("errorMessage", invalidUsernamePasswordException.getMessage());
//        }
//        return "login";
//    }

//    @PostMapping("/login")
//    public String login(@ModelAttribute("loginDTO") LoginDTO loginDTO, Model model, RedirectAttributes redirectAttributes) {
//        try {
//            // Xác thực người dùng
//            Authentication auth = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
//            SecurityContextHolder.getContext().setAuthentication(auth);
//
//            // Xác thực thành công, thêm thông điệp vào RedirectAttributes
//            redirectAttributes.addFlashAttribute("noti", "Congratulations!");
//            redirectAttributes.addFlashAttribute("message", "Login successful.");
//            return "redirect:/login"; // Redirect để hiển thị thông điệp
//        } catch (AuthenticationException e) {
//            model.addAttribute("errorMessage", "Invalid username or password");
//            return "login";
//        }
//    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("registerDTO") RegisterDTO registerDTO, Model model) {
        try {
            int row_effected = accountService.register(registerDTO);
            if (row_effected == 1) {
                model.addAttribute("noti", "Congratulations!");
                model.addAttribute("message", "Your account has been succesfully created");
                return "register";
            }
        } catch (UsernameExistedException usernameExistedException) {
            model.addAttribute("errorMessage", usernameExistedException.getMessage());
        } catch (PasswordDoNotMatchException passwordDoNotMatchException) {
            model.addAttribute("errorMessage", passwordDoNotMatchException.getMessage());
        } catch (UsernameInvalidFormatException usernameInvalidFormatException) {
            model.addAttribute("errorMessage", usernameInvalidFormatException.getMessage());
        }
        return "register";
    }
}
