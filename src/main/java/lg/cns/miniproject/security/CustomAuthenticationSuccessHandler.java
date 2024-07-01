package lg.cns.miniproject.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        // Thêm thông điệp vào session
        request.getSession().setAttribute("noti", "Congratulations!");
        request.getSession().setAttribute("message", "Login successful.");

        // Chuyển hướng về trang login để hiển thị thông điệp
        getRedirectStrategy().sendRedirect(request, response, "/login");
    }
}
