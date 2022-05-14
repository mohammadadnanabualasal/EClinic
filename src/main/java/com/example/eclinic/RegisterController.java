package com.example.eclinic;

import com.example.eclinic.entities.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Collection;

@Controller
public class RegisterController {

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView LoginPage(HttpSession session)
    {
        if(session.getAttribute("user") != null)
        {
            return new ModelAndView("redirect:/home");
        }
        return  new ModelAndView("register");
    }

    @RequestMapping(value = "/doRegister",method = RequestMethod.POST)
    public ModelAndView createUser(HttpSession session, HttpServletRequest httpServletRequest
            , @RequestParam(name="name")String name
            ,@RequestParam(name = "email") String email
            , @RequestParam(name="password") String password
            ,@RequestParam(name = "confirmPassword") String confirmPassword){

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("eclinic");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createNativeQuery("SELECT * FROM user WHERE email = '"+email+"';");
        int users =  query.getResultList().size();
        entityManager.close();
        entityManagerFactory.close();
        UserEntity userEntity = new UserEntity();
        userEntity.setName(name);
        userEntity.setPassword(password);
        userEntity.setEmail(email);
        userEntity.setIsAdmin("NO");

        if (session.getAttribute("user") !=null){
            return new ModelAndView("redirect:/home");
        }
        else {
            if (!confirmPassword.equals(password)){
                return new ModelAndView("redirect:/register");
            }
            boolean isSaved = false;

            try {
                isSaved = userEntity.addNewUser(userEntity, false);
            }catch (Exception e){
                ModelAndView modelAndView = new ModelAndView("create");
                modelAndView.addObject("user", userEntity);
                return modelAndView;
            }
            if(isSaved)
            {
                session.setAttribute("user",userEntity);
                return new ModelAndView("redirect:/home");
            }else {
                return new ModelAndView("redirect:/register");
            }

        }
    }

}
