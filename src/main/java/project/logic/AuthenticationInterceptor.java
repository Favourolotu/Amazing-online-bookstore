package project.logic;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import project.models.Owner;
import project.models.User;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getServletPath();
        String[] pathParts = path.split("/");

        if (pathParts.length > 1 && pathParts[1].equals("owner")) {
            // If the path is for an owner, validate owner credentials
            Long ownerId = Long.parseLong(pathParts[2]);
            Owner loggedInOwner = (Owner) request.getSession().getAttribute("loggedInOwner");
            if (loggedInOwner != null && loggedInOwner.getId().equals(ownerId)) {
                return true;
            } else {
                response.sendRedirect("/");
                return false;
            }
        } else if (pathParts.length > 1 && pathParts[1].equals("user")) {
            // If the path is for a user, validate user credentials
            Long userId = Long.parseLong(pathParts[2]);
            User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
            if (loggedInUser != null && loggedInUser.getId().equals(userId)) {
                return true;
            } else {
                response.sendRedirect("/");
                return false;
            }
        } else {
            return true;
        }
    }


}

