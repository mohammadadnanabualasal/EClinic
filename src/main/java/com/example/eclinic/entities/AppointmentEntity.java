package com.example.eclinic.entities;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "appointment", schema = "eclinic", catalog = "")
public class AppointmentEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "patientId")
    private int patientId;
    @Basic
    @Column(name = "date")
    private Date date;
    @Basic
    @Column(name = "time")
    private Time time;
    @Basic
    @Column(name = "doctorId")
    private Integer doctorId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppointmentEntity that = (AppointmentEntity) o;
        return id == that.id && patientId == that.patientId && Objects.equals(date, that.date) && Objects.equals(time, that.time) && Objects.equals(doctorId, that.doctorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, patientId, date, time, doctorId);
    }

    public static boolean addNewAppointment(AppointmentEntity appointment, boolean update) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("eclinic");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            if (update) {
                entityManager.merge(appointment);
            } else {
                entityManager.persist(appointment);
            }
            transaction.commit();
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }finally {
            entityManager.close();
            entityManagerFactory.close();
        }
        return true;
    }

    public static List<AppointmentEntity> getAllAppointments() {
        List<AppointmentEntity> appointmentEntities = new ArrayList<>();
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("eclinic");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createNativeQuery("select * from appointment order by date;", AppointmentEntity.class);
            appointmentEntities = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
        return appointmentEntities;
    }

    public PatientEntity getPatient()
    {
        PatientEntity patient = PatientEntity.getPatientById(getPatientId()+"");
        return patient;
    }

    public UserEntity getDoctor()
    {
        UserEntity doctor = UserEntity.getUserById(getDoctorId()+"");
        return doctor;
    }

    public static AppointmentEntity getAppointmentById(int id) {
        try {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("eclinic");
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            AppointmentEntity appointment;
            Query query = entityManager.createNativeQuery("SELECT * FROM  appointment WHERE id='" + id + "';", AppointmentEntity.class);
            appointment = (AppointmentEntity) query.getResultList().get(0);
            entityManager.close();
            entityManagerFactory.close();
            return appointment;
        } catch (Exception exception) {
            return null;
        }
    }

    public static List<AppointmentEntity> getAllAppointmentsForDoctor(int doctorId) {
        try {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("eclinic");
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            List<AppointmentEntity> appointments;
            Query query = entityManager.createNativeQuery("SELECT * FROM  appointment WHERE doctorId='" + doctorId + "' order by date,time;", AppointmentEntity.class);
            appointments = query.getResultList();
            entityManager.close();
            entityManagerFactory.close();
            return appointments;
        } catch (Exception exception) {
            return null;
        }
    }

    public static void cancelAppointment(AppointmentEntity appointment)
    {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("eclinic");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.remove(entityManager.contains(appointment) ? appointment : entityManager.merge(appointment));
            transaction.commit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public static List<AppointmentEntity> getAppointmentsByDateAndDoctorId(Date date, int doctorId){
        try {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("eclinic");
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            List<AppointmentEntity> appointments;
            Query query;
            if (doctorId == -1) {
                query = entityManager.createNativeQuery("SELECT * FROM  appointment WHERE date='" + date + "' order by time;", AppointmentEntity.class);
            }else {
                query = entityManager.createNativeQuery("SELECT * FROM  appointment WHERE doctorId='" + doctorId + "' and date='" + date + "' order by time;", AppointmentEntity.class);
            }
            appointments = query.getResultList();
            entityManager.close();
            entityManagerFactory.close();
            return appointments;
        } catch (Exception exception) {
            return null;
        }
    }

    public static boolean isDoctorAvailableAt(int doctorId, Time time, Date date)
    {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("eclinic");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
        try {
            time = new Time(simpleDateFormat.parse(time.toString()).getTime());
            List<AppointmentEntity> appointments;
            Query query = entityManager.createNativeQuery("SELECT * FROM  appointment WHERE doctorId='"
                    + doctorId + "' and date ='"+date+"' and time > '"+time.toLocalTime().minusMinutes(30).toString()+"' and time < '"+
                    time.toLocalTime().plusMinutes(30).toString()+"';", AppointmentEntity.class);
            appointments = query.getResultList();
            if (appointments.size()>0)
            {
                return false;
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }finally {
            entityManager.close();
            entityManagerFactory.close();
        }
        return true;

    }

}
