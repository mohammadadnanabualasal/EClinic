package com.eclinic;

import com.eclinic.entities.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView LoginPage(HttpSession session, @RequestParam(value = "error", defaultValue = "") String error)
    {
        if(session.getAttribute("user") != null)
        {
            return new ModelAndView("redirect:/home");
        }
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("error", error);
        return  modelAndView;
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
        } else {
            if (UserEntity.getUserByEmail(email) == null) {
                return new ModelAndView("redirect:/login?error=the email is not exist!");
            }
            if (!UserEntity.getUserByEmail(email).getPassword().equals(password)) {
                return new ModelAndView("redirect:/login?error=the password is not correct!");
            }

            session.setAttribute("user", UserEntity.getUserByEmail(email));
            return new ModelAndView("redirect:/home");

        }
    }
}
