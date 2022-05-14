package com.example.eclinic;

import com.example.eclinic.entities.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView LoginPage(HttpSession session)
    {
        if(session.getAttribute("user") != null)
        {
            return new ModelAndView("redirect:/home");
        }
        return  new ModelAndView("login");
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpSession session)
    {
        session.removeAttribute("user");
        return  new ModelAndView("redirect:/home");
    }

    @RequestMapping(value = "/doLogin", method = RequestMethod.GET)
    public ModelAndView doLoginAction(HttpSession session, @RequestParam(name = "email") String email, @RequestParam(name = "password") String password)
    {
        if(session.getAttribute("user") != null)
        {
            return new ModelAndView("redirect:/home");
        }else {
            if (UserEntity.getUserByEmail(email) != null && UserEntity.getUserByEmail(email).getPassword().equals(password))
            {
                session.setAttribute("user", UserEntity.getUserByEmail(email));
                return new ModelAndView("redirect:/home");
            }else {
                return new ModelAndView("redirect:/login");
            }
        }
    }
}
