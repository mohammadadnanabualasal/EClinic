package com.example.eclinic;

import com.example.eclinic.entities.AppointmentEntity;
import com.example.eclinic.entities.InsuranceEntity;
import com.example.eclinic.entities.PatientEntity;
import com.example.eclinic.entities.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SecretaryController {

    @RequestMapping(value = "/showPatients", method = RequestMethod.GET)
    public ModelAndView showPatients(HttpSession session) {
        if (havePermission(session)) {
            List<PatientEntity> patients = PatientEntity.getAllPatients();
            ModelAndView modelAndView = new ModelAndView("showPatients");

            modelAndView.addObject("patients", patients);
            return modelAndView;
        } else {
            return new ModelAndView("redirect:/home");
        }
    }

    @RequestMapping(value = "/addNewPatient", method = RequestMethod.GET)
    public ModelAndView addNewPatientGet(HttpSession session) {
        if (havePermission(session)) {
            ModelAndView modelAndView = new ModelAndView("addNewPatient");
            modelAndView.addObject("companies", InsuranceEntity.getAllInsuranceEntities());
            modelAndView.addObject("doctors", UserEntity.getAllDoctors());
            return modelAndView;

        } else {
            return new ModelAndView("redirect:/login");
        }
    }

    @RequestMapping(value = "/addNewPatient", method = RequestMethod.POST)
    public ModelAndView addNewPatient(HttpSession session
            , @RequestParam(name = "name") String name
            , @RequestParam(name = "gender") String gender
            , @RequestParam(name = "weight") int weight
            , @RequestParam(name = "height") int height
            , @RequestParam(name = "diagnosis") String diagnosis
            , @RequestParam(name = "insuranceCompanyId", defaultValue = "0") int insuranceCompanyId
            , @RequestParam(name = "phone") String phone
            , @RequestParam(name = "doctorId", defaultValue = "0") int doctorId) {
        if (havePermission(session)) {
            PatientEntity patientEntity = new PatientEntity();
            patientEntity.setName(name);
            patientEntity.setGender(gender);
            patientEntity.setHeight(height);
            patientEntity.setWeight(weight);
            patientEntity.setPhone(phone);
            patientEntity.setDiagnosis(diagnosis);
            patientEntity.setInsuranceCompanyId(insuranceCompanyId);
            patientEntity.setDoctorId(doctorId);
            boolean success;
            try {
                success = PatientEntity.addNewPatient(patientEntity, false);
                if (success) {
                    return new ModelAndView("redirect:/addNewAppointment");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new ModelAndView("redirect:/addNewPatient");
        } else {
            return new ModelAndView("redirect:/home");
        }
    }

    @RequestMapping(value = "/removePatient/{patientId}")
    public ModelAndView removePatient(HttpSession session, @PathVariable("patientId") String patientId) {
        if (havePermission(session)) {
            PatientEntity.removePatient(PatientEntity.getPatientById(patientId));
            return new ModelAndView("redirect:/showPatients");
        }
        return new ModelAndView("redirect:/home");
    }

    @RequestMapping(value = "/editPatient/{patientId}", method = RequestMethod.GET)
    public ModelAndView editPatient(HttpSession session, @PathVariable("patientId") String patientId) {

        if (havePermission(session)) {
            ModelAndView modelAndView = new ModelAndView("editPatient");
            modelAndView.addObject("insurances", InsuranceEntity.getAllInsuranceEntities());
            modelAndView.addObject("doctors", UserEntity.getAllDoctors());
            modelAndView.addObject("patient", PatientEntity.getPatientById(patientId));
            return modelAndView;
        }
        return new ModelAndView("redirect:/home");
    }

    @RequestMapping(value = "/updatePatient/{patientId}", method = RequestMethod.POST)
    public ModelAndView editPatientPost(HttpSession session
            , @PathVariable(name = "patientId") int patientId
            , @RequestParam(name = "name") String name
            , @RequestParam(name = "gender") String gender
            , @RequestParam(name = "weight") int weight
            , @RequestParam(name = "height") int height
            , @RequestParam(name = "diagnosis") String diagnosis
            , @RequestParam(name = "insuranceCompanyId", defaultValue = "0") int insuranceCompanyId
            , @RequestParam(name = "phone") String phone
            , @RequestParam(name = "doctorId", defaultValue = "0") int doctorId
    ) {

        if (havePermission(session)) {
            ModelAndView modelAndView = new ModelAndView("redirect:/showPatients");
            PatientEntity patientEntity = PatientEntity.getPatientById(patientId + "");
            patientEntity.setName(name);
            patientEntity.setGender(gender);
            patientEntity.setPhone(phone);
            patientEntity.setDiagnosis(diagnosis);
            patientEntity.setDoctorId(doctorId);
            patientEntity.setInsuranceCompanyId(insuranceCompanyId);
            patientEntity.setWeight(weight);
            patientEntity.setHeight(height);
            PatientEntity.addNewPatient(patientEntity, true);
            return modelAndView;
        }
        return new ModelAndView("redirect:/home");
    }


    @RequestMapping(value = "/showInsuranceCompanies", method = RequestMethod.GET)
    public ModelAndView showInsuranceCompanies(HttpSession session) {

        if (havePermission(session)) {
            List<InsuranceEntity> insuranceEntities = InsuranceEntity.getAllInsuranceEntities();
            ModelAndView modelAndView = new ModelAndView("showInsuranceCompanies");
            modelAndView.addObject("insuranceEntities", insuranceEntities);
            return modelAndView;
        } else {
            return new ModelAndView("redirect:/home");
        }
    }

    @RequestMapping(value = "/addNewInsuranceCompany", method = RequestMethod.GET)
    public ModelAndView addNewInsuranceCompany(HttpSession session){
        if (havePermission(session)) {
            return new ModelAndView("addNewInsuranceCompany");
        }else {
            return new ModelAndView("redirect:/login");
        }
    }

    @RequestMapping(value = "/addNewInsuranceCompany", method = RequestMethod.POST)
    public ModelAndView addNewInsuranceCompany(HttpSession session
            , @RequestParam(name = "name") String name
            , @RequestParam(name = "address") String address
            , @RequestParam(name = "accreditationNumber") String accreditationNumber
            , @RequestParam(name = "sessionPrice") String sessionPrice
            , @RequestParam(name = "fax") String fax
            , @RequestParam(name = "keyPersonPhone") String keyPersonPhone
            , @RequestParam(name = "keyPersonName") String keyPersonName
            , @RequestParam(name = "phone") String phone) {
        if (havePermission(session)) {
            InsuranceEntity insurance = new InsuranceEntity();
            insurance.setName(name);
            insurance.setAddress(address);
            insurance.setAccreditationNumber(accreditationNumber);
            insurance.setSessionPrice(Double.parseDouble(sessionPrice));
            insurance.setFax(fax);
            insurance.setKeyPersonName(keyPersonName);
            insurance.setKeyPersonPhone(keyPersonPhone);
            insurance.setPhone(phone);
            boolean success;
            try {
                success = InsuranceEntity.addNewInsuranceEntity(insurance, false);
                if (success) {
                    return new ModelAndView("redirect:/addNewInsuranceCompany");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new ModelAndView("redirect:/showInsuranceCompanies");
        } else {
            return new ModelAndView("redirect:/home");
        }
    }

    @RequestMapping(value = "/removeInsuranceCompany/{insuranceCompany}")
    public ModelAndView removeInsuranceCompany(HttpSession session, @PathVariable("insuranceCompany") int insuranceCompany) {
        if (havePermission(session)) {
            InsuranceEntity.removeInsuranceEntity(InsuranceEntity.getInsuranceEntityById(insuranceCompany));
            return new ModelAndView("redirect:/showInsuranceCompanies");
        }
        return new ModelAndView("redirect:/home");
    }

    @RequestMapping(value = "/editInsurance/{insuranceId}", method = RequestMethod.GET)
    public ModelAndView editInsurance(HttpSession session, @PathVariable("insuranceId") int insuranceId)
    {
        if (havePermission(session)) {
            ModelAndView modelAndView = new ModelAndView("editInsurance");
            modelAndView.addObject("insurance", InsuranceEntity.getInsuranceEntityById(insuranceId));
            return modelAndView;
        }
        return new ModelAndView("redirect:/home");
    }

    @RequestMapping(value = "/updateInsurance/{insuranceId}", method = RequestMethod.POST)
    public ModelAndView editUserPost(HttpSession session
            , @PathVariable(name = "insuranceId") int insuranceId
            , @RequestParam(name = "name") String name
            , @RequestParam(name = "address") String address
            , @RequestParam(name = "accreditationNumber") String accreditationNumber
            , @RequestParam(name = "sessionPrice") String sessionPrice
            , @RequestParam(name = "fax") String fax
            , @RequestParam(name = "keyPersonPhone") String keyPersonPhone
            , @RequestParam(name = "keyPersonName") String keyPersonName
            , @RequestParam(name = "phone") String phone)
    {

        if (havePermission(session)) {
            ModelAndView modelAndView = new ModelAndView("redirect:/showInsuranceCompanies");
            InsuranceEntity insurance =InsuranceEntity.getInsuranceEntityById(insuranceId);
            insurance.setName(name);
            insurance.setAddress(address);
            insurance.setAccreditationNumber(accreditationNumber);
            insurance.setSessionPrice(Double.parseDouble(sessionPrice));
            insurance.setFax(fax);
            insurance.setKeyPersonName(keyPersonName);
            insurance.setKeyPersonPhone(keyPersonPhone);
            insurance.setPhone(phone);
            InsuranceEntity.addNewInsuranceEntity(insurance, true);
            return modelAndView;
        }
        return new ModelAndView("redirect:/login");
    }

    @RequestMapping(value = "/showAppointments", method = RequestMethod.GET)
    public ModelAndView showAppointments(HttpSession session) {

        if (havePermission(session)) {
            List<AppointmentEntity> appointmentEntities = AppointmentEntity.getAllAppointments();
            ModelAndView modelAndView = new ModelAndView("showAppointments");

            modelAndView.addObject("appointments", appointmentEntities);
            return modelAndView;
        } else {
            return new ModelAndView("redirect:/home");
        }
    }

    @RequestMapping(value = "/addNewAppointment", method = RequestMethod.GET)
    public ModelAndView addNewAppointmentPage(HttpSession session, @RequestParam(value = "error", defaultValue = "") String error) {

        if (havePermission(session)) {
            List<PatientEntity> patientEntities = PatientEntity.getAllPatients();
            List<UserEntity> doctors = UserEntity.getAllDoctors();
            ModelAndView modelAndView = new ModelAndView("addNewAppointment");
            modelAndView.addObject("patients", patientEntities);
            modelAndView.addObject("doctors", doctors);
            modelAndView.addObject("error", error);
            return modelAndView;
        } else {
            return new ModelAndView("redirect:/home");
        }
    }

    @RequestMapping(value = "/addNewAppointment", method = RequestMethod.POST)
    public ModelAndView addNewAppointment(HttpSession session
            , @RequestParam(name = "patient") int patientId
            , @RequestParam(name = "doctor", defaultValue = "0") int doctorId
            , @RequestParam(name = "date") String date
            , @RequestParam(name = "time") String time
            ) {
        if (havePermission(session)) {
            AppointmentEntity appointmentEntity = new AppointmentEntity();
            SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm");
            long ms;
            try {
                ms = timeFormat.parse(time).getTime();
                appointmentEntity.setTime(new Time(ms));
                appointmentEntity.setDate(Date.valueOf(date));
            } catch (ParseException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            appointmentEntity.setDoctorId(doctorId);
            appointmentEntity.setPatientId(patientId);
            if(AppointmentEntity.isDoctorAvailableAt(doctorId, new Time(ms), Date.valueOf(date))) {
                AppointmentEntity.addNewAppointment(appointmentEntity, false);
            }else {
                return new ModelAndView("redirect:/addNewAppointment?error=Doctor "+UserEntity.getUserById(doctorId+"").getName()+" is not available at this time.");
            }
            return new ModelAndView("redirect:/showAppointments");

        } else {
            return new ModelAndView("redirect:/login");
        }
    }

    private boolean havePermission(HttpSession session) {
        UserEntity userEntity = (UserEntity) session.getAttribute("user");
        return (userEntity != null && (userEntity.getPermission().equals("doctor") || userEntity.getPermission().equals("secretary")));
    }

    @RequestMapping(value = "/editAppointment/{id}", method = RequestMethod.GET)
    public ModelAndView editAppointment(HttpSession session, @PathVariable("id") int appointmentId)
    {
        if (havePermission(session)) {
            ModelAndView modelAndView = new ModelAndView("editAppointment");
            modelAndView.addObject("appointment", AppointmentEntity.getAppointmentById(appointmentId));
            modelAndView.addObject("doctors", UserEntity.getAllDoctors());
            modelAndView.addObject("patients", PatientEntity.getAllPatients());
            return modelAndView;
        }
        return new ModelAndView("redirect:/home");
    }

    @RequestMapping(value = "/updateAppointment/{id}",  method = RequestMethod.POST)
    public ModelAndView updateAppointment(HttpSession session
            , @PathVariable("id") int appointmentId
            , @RequestParam(name = "patient") int patientId
            , @RequestParam(name = "doctor") int doctorId
            , @RequestParam(name = "date") String date
            , @RequestParam(name = "time") String time
    )
    {
        if (havePermission(session)) {
            AppointmentEntity appointmentEntity = AppointmentEntity.getAppointmentById(appointmentId);
            SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm");
            long ms;
            try {
                ms = timeFormat.parse(time).getTime();
                appointmentEntity.setTime(new Time(ms));
                appointmentEntity.setDate(Date.valueOf(date));
            } catch (ParseException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            appointmentEntity.setDoctorId(doctorId);
            appointmentEntity.setPatientId(patientId);
            AppointmentEntity.addNewAppointment(appointmentEntity, true);
            return new ModelAndView("redirect:/showAppointments");
        }
        return new ModelAndView("redirect:/login");
    }

    @RequestMapping(value = "/cancelAppointment/{id}")
    public ModelAndView cancelAppointment(HttpSession session, @PathVariable(value = "id") int id)
    {
        if (havePermission(session)) {
            AppointmentEntity.cancelAppointment(AppointmentEntity.getAppointmentById(id));
            return new ModelAndView("redirect:/showAppointments");
        }
        return new ModelAndView("redirect:/login");
    }

    @RequestMapping(value = "/patientSearch")
    public @ResponseBody List<String> patientSearch(HttpSession session, @RequestParam(value = "term") String term) {
        List<String> list = new ArrayList<>();
        if (havePermission(session) && !term.isEmpty()) {
            String patientsHtml = "";
            List<PatientEntity> patientEntities = PatientEntity.getAllPatientsByPhoneTerm(term);
            if (patientEntities.isEmpty()) {
                return list;
            }
            for (PatientEntity patient : patientEntities
            ) {
                patientsHtml = patientsHtml + "<option value=\""+patient.getId()+"\">" + patient.getPhone() + " (" + patient.getName() + ")" + "</option>";

            }
            list.add("<select name=\"patient\" id=\"patient\" class=\"form-select form-select-lg mb-3\" required>" + patientsHtml + "</select>");
        }
        return list;
    }
    @RequestMapping(value = "/calendar")
    public ModelAndView calendar(HttpSession session, @RequestParam(value = "date", defaultValue = "") String date)
    {
        if (havePermission(session)){
            Date theDate = new Date(System.currentTimeMillis());
            ModelAndView modelAndView = new ModelAndView("calendar");
            if (!date.equals("")){
                theDate = Date.valueOf(date);
            }
            modelAndView.addObject("date", theDate);
            String appointments = "";
            List<AppointmentEntity> appointmentEntities = AppointmentEntity.getAppointmentsByDateAndDoctorId(theDate, -1);
            if (!appointmentEntities.isEmpty()){
                for (AppointmentEntity appointment: appointmentEntities
                ) {
                    appointments = appointments + getAppointmentCard(appointment) + "<br/>";
                }
            }else {
                appointments = appointments + "no appointments today.";
            }
            modelAndView.addObject("appointments", appointments);
            modelAndView.addObject("doctors", UserEntity.getAllDoctors());
            return modelAndView;
        }
        return new ModelAndView("redirect:/login");
    }

    @RequestMapping(value = "/getAppointmentsForDate")
    public @ResponseBody String getAppointmentsForDate(HttpSession session, @RequestParam(value = "date") Date date, @RequestParam(value = "doctor") int doctorId)
    {
        String response = "";
        if (havePermission(session)){
            List<AppointmentEntity> appointmentEntities = AppointmentEntity.getAppointmentsByDateAndDoctorId(date, doctorId);
            if (appointmentEntities!= null && !appointmentEntities.isEmpty()){
                for (AppointmentEntity appointment: appointmentEntities
                     ) {
                    response = response + getAppointmentCard(appointment) + "<br/>";
                }
            }else {
                response = response + "no appointments.";
            }
        }
        return response;
    }

    public String getAppointmentCard(AppointmentEntity appointment)
    {
        return "<div class=\"card\">\n" +
                "                <div   class=\"card-header\" style=\"display: block;font-size: smaller;\">\n" +
                "                    <div class=\"row\">\n" +
                "                        <div class=\"col-md-6 left-alignment\"></div>\n" +
                "                        <div class=\"col-md-6 right-alignment\">\n" +
                "                            <a href=\"/cancelAppointment/"+appointment.getId()+"\">cancel</a> / <a href=\"/editAppointment/"+appointment.getId()+"\">Edit</a>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "                <div class=\"user-card-info\">\n" +
                "                    <div class=\"alignment\">\n" +
                "                        <p style=\"margin: 10px\">\n" +
                "                            <span style=\"display: block\"><b>Patient:</b> "+appointment.getPatient().getName()+"</span>\n" +
                "                            <span style=\"display: block\"><b>Diagnosis:</b> "+appointment.getPatient().getDiagnosis()+"</span>\n" +
                "                            <span style=\"display: block\"><b>Gender:</b> "+appointment.getPatient().getGender()+"</span>\n" +
                "                            <span style=\"display: block\"><b>Patient Phone:</b> "+appointment.getPatient().getPhone()+"</span>\n" +
                "                            <span style=\"display: block\"><b>Doctor:</b> "+appointment.getDoctor().getName()+"</span>\n" +
                "                            <span style=\"display: block\"><b>Doctor Phone:</b> "+appointment.getDoctor().getPhone()+"</span>\n" +
                "                            <span style=\"display: block\"><b>Date:</b> "+appointment.getDate().toString()+"</span>\n" +
                "                            <span style=\"display: block\"><b>Time:</b> "+appointment.getTime().toString()+"</span>\n" +
                "                        </p>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div>";
    }
}
