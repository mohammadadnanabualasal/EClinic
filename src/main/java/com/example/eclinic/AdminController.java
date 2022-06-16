package com.example.eclinic;

import com.example.eclinic.entities.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AdminController {

    @RequestMapping(value = "/showUsers", method = RequestMethod.GET)
    public ModelAndView showAdmins(HttpSession session) {

        if (session.getAttribute("user") != null && ((UserEntity) session.getAttribute("user")).getPermission().equals("admin"))
        {
            List<UserEntity> users = UserEntity.getAllUsers();
            ModelAndView modelAndView = new ModelAndView("showUsers");

            modelAndView.addObject("users", users);
            return modelAndView;
        } else {
            return new ModelAndView("redirect:/home");
        }
    }

    @RequestMapping(value = "/addNewUser", method = RequestMethod.POST)
    public ModelAndView addNewUserPost(HttpSession session
            , @RequestParam(name = "name") String name
            , @RequestParam(name = "email") String email
            , @RequestParam(name = "password") String password
            , @RequestParam(name = "confirmPassword") String confirmPassword
            , @RequestParam(name = "phone") String phone
            , @RequestParam(name = "permission", defaultValue = "") String permission)
    {
        if (session.getAttribute("user") != null && ((UserEntity) session.getAttribute("user")).getPermission().equals("admin"))
        {
            if (!confirmPassword.equals(password)) {
                return new ModelAndView("redirect:/addNewUser?error=the password and confirm password fields are not matched!");
            }
            if (UserEntity.getUserByEmail(email) != null) {
                return new ModelAndView("redirect:/addNewUser?error=the email is already used!");
            }
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("eclinic");
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            Query query = entityManager.createNativeQuery("SELECT * FROM user WHERE email = '" + email + "';");
            int users = query.getResultList().size();
            entityManager.close();
            entityManagerFactory.close();
            UserEntity userEntity = new UserEntity();
            userEntity.setName(name);
            userEntity.setPassword(password);
            userEntity.setEmail(email);
            userEntity.setPhone(phone);
            userEntity.setPermission(permission);
            boolean success;
            try {
                if (users == 0) {
                    success = userEntity.addNewUser(userEntity, false);
                    if (success) {
                        return new ModelAndView("redirect:/addNewUser");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new ModelAndView("redirect:/addNewUser?error=something went wrong!");
        } else
        {
            return new ModelAndView("redirect:/home");
        }
    }

    @RequestMapping(value = "/addNewUser", method = RequestMethod.GET)
    public ModelAndView addNewUser(HttpSession session, @RequestParam(value = "error", defaultValue = "") String error)
    {
        if (session.getAttribute("user") != null && ((UserEntity) session.getAttribute("user")).getPermission().equals("admin"))
        {
            ModelAndView modelAndView = new ModelAndView("addNewUser");
            modelAndView.addObject("error", error);
            return modelAndView;
        }else
        {
            return new ModelAndView("redirect:/login");
        }
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView adminPage(HttpSession session)
    {
        if (session.getAttribute("user") != null && ((UserEntity) session.getAttribute("user")).getPermission().equals("admin")) {
            return new ModelAndView("admin");
        }else {
            return new ModelAndView("redirect:/error");
        }
    }

    @RequestMapping(value = "/removeUser/{userId}")
    public ModelAndView removeUser(HttpSession session, @PathVariable("userId") String userId) {

        if (session.getAttribute("user") != null && ((UserEntity) session.getAttribute("user")).getPermission().equals("admin")) {
            UserEntity.removeUser(UserEntity.getUserById(userId));
            return new ModelAndView("redirect:/showUsers");
        }
        return new ModelAndView("redirect:/home");
    }

    @RequestMapping(value = "/editUser/{userId}", method = RequestMethod.GET)
    public ModelAndView editUser(HttpSession session, @PathVariable("userId") String userId, @RequestParam(value = "error", defaultValue = "") String error) {

        if (session.getAttribute("user") != null && ((UserEntity) session.getAttribute("user")).getPermission().equals("admin")) {
            ModelAndView modelAndView = new ModelAndView("editUser");
            modelAndView.addObject("error", error);
            modelAndView.addObject("user", UserEntity.getUserById(userId));
            return modelAndView;
        }
        return new ModelAndView("redirect:/home");
    }

    @RequestMapping(value = "/updateUser/{userId}", method = RequestMethod.POST)
    public ModelAndView editUserPost(HttpSession session
            , @PathVariable("userId") String id
            , @RequestParam("name") String name
            , @RequestParam("phone") String phone
            , @RequestParam("email") String email
            , @RequestParam("password") String password
            , @RequestParam("permission") String permission
            , @RequestParam("confirmPassword") String confirmPassword
    ) {

        if (session.getAttribute("user") != null && ((UserEntity) session.getAttribute("user")).getPermission().equals("admin")) {
            ModelAndView modelAndView = new ModelAndView("redirect:/showUsers");
            if (!confirmPassword.equals(password)) {
                return new ModelAndView("redirect:/editUser/"+id+"?error=the password and confirm password fields are not matched!");
            }
            if (UserEntity.getUserByEmail(email) != null && !id.equals(UserEntity.getUserByEmail(email).getId()+"")) {
                return new ModelAndView("redirect:/editUser/"+id+"?error=the email is already used!");
            }
            UserEntity userEntity = UserEntity.getUserById(id);
            userEntity.setName(name);
            userEntity.setPassword(password);
            userEntity.setEmail(email);
            userEntity.setPhone(phone);
            userEntity.setPermission(permission);
            boolean status = UserEntity.addNewUser(userEntity, true);
            if (!status){
                return new ModelAndView("redirect:/editUser/"+id+"?error=something went wrong!");
            }
            return modelAndView;
        }
        return new ModelAndView("redirect:/home");
    }
}
