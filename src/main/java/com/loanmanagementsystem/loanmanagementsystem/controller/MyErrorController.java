package com.loanmanagementsystem.loanmanagementsystem.controller;
import com.loanmanagementsystem.loanmanagementsystem.config.UserInfoUserDetails;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyErrorController implements ErrorController {
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request,@AuthenticationPrincipal UserInfoUserDetails userDetails) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String user = userDetails.getAuthorities().toString();

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.FORBIDDEN.value() && user.equals("[STAFF]")) {
                return "error-403";
            } else if (statusCode == HttpStatus.FORBIDDEN.value() && user.equals("[NORMAL]")) {
                return "error-403-normal";

            } else if(statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error-404";
            }
        }
        return "error";
    }
}
