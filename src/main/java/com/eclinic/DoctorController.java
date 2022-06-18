package com.eclinic;

import com.eclinic.entities.AppointmentEntity;
import com.eclinic.entities.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class DoctorController {

    private boolean havePermission(HttpSession session) {
        UserEntity userEntity = (UserEntity) session.getAttribute("user");
        return (userEntity != null && (userEntity.getPermission().equals("doctor")));
    }

    @RequestMapping(value = "/myAppointments", method = RequestMethod.GET)
    public ModelAndView myAppointments(HttpSession session) {

        if (havePermission(session)) {
            List<AppointmentEntity> appointmentEntities = AppointmentEntity.getAllAppointmentsForDoctor(((UserEntity)session.getAttribute("user")).getId());
            ModelAndView modelAndView = new ModelAndView("myAppointments");
            modelAndView.addObject("appointments", appointmentEntities);
            modelAndView.addObject("doctor", session.getAttribute("user"));
            return modelAndView;
        } else {
            return new ModelAndView("redirect:/home");
        }
    }


}
