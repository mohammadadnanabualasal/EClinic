package com.eclinic.entities;

import javax.persistence.*;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "user", schema = "eclinic", catalog = "")
public class UserEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "permission")
    private String permission;
    @Basic
    @Column(name = "address")
    private String address;
    @Basic
    @Column(name = "phone")
    private String phone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(email, that.email) && Objects.equals(password, that.password) && Objects.equals(permission, that.permission) && Objects.equals(address, that.address) && Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password, permission, address, phone);
    }


    public static UserEntity getUserByEmail(String email) {
        try {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("eclinic");
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            UserEntity user;
            Query query = entityManager.createNativeQuery("SELECT * FROM  user WHERE email='" + email + "';", UserEntity.class);
            user = (UserEntity) query.getResultList().get(0);
            entityManager.close();
            entityManagerFactory.close();
            return user;
        } catch (Exception exception) {
            return null;
        }
    }

    public static UserEntity getUserById(String id) {
        try {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("eclinic");
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            UserEntity user;
            Query query = entityManager.createNativeQuery("SELECT * FROM  user WHERE id='" + id + "';", UserEntity.class);
            user = (UserEntity) query.getResultList().get(0);
            entityManager.close();
            entityManagerFactory.close();
            return user;
        } catch (Exception exception) {
            return null;
        }
    }

    public static boolean addNewUser(UserEntity user, boolean update) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("eclinic");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            if (update) {
                entityManager.merge(user);
            } else {
                entityManager.persist(user);
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

    public static List<UserEntity> getAllUsers() {
        List<UserEntity> userEntities = new ArrayList<>();
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("eclinic");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createNativeQuery("SELECT * FROM user;", UserEntity.class);
            userEntities = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
        return userEntities;
    }

    public static List<UserEntity> getAllDoctors() {
        List<UserEntity> userEntities = new ArrayList<>();
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("eclinic");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createNativeQuery("SELECT * FROM user where permission='doctor';", UserEntity.class);
            userEntities = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
        return userEntities;
    }



    public static boolean removeUser(UserEntity user) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("eclinic");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
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

    public boolean haveReservationAt(int time, String date){
        String stringTime = "";
        if ((time/2) < 10){
            stringTime = "0"+stringTime+(time/2);
        }else {
            stringTime = stringTime+(time/2);
        }
        if (!(time%2 == 0)){
            stringTime = stringTime + ":30";
        }else {
            stringTime = stringTime + ":00";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("eclinic");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Time from = new Time(sdf.parse(stringTime).getTime());
            LocalTime start = from.toLocalTime();
            LocalTime end = start.plusMinutes(30);
            Query query = entityManager.createNativeQuery("SELECT * FROM appointment where doctorId='"+getId()+"' and time >= '"
                    +start.toString()+"' and time < '"+(end.toString().equals("00:00")?"24:00":end.toString())+"' and date ='"+date+"';", AppointmentEntity.class);
            int count = query.getResultList().size();
            if (count > 0){
                return true;
            }else {
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public AppointmentEntity getReservationAt(int time, String date){
        String stringTime = "";
        if ((time/2) < 10){
            stringTime = "0"+stringTime+(time/2);
        }else {
            stringTime = stringTime+(time/2);
        }
        if (!(time%2 == 0)){
            stringTime = stringTime + ":30";
        }else {
            stringTime = stringTime + ":00";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("eclinic");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Time from = new Time(sdf.parse(stringTime).getTime());
            LocalTime start = from.toLocalTime();
            LocalTime end = start.plusMinutes(30);
            Query query = entityManager.createNativeQuery("SELECT * FROM appointment where doctorId='"+getId()+"' and time >= '"
                    +start.toString()+"' and time < '"+(end.toString().equals("00:00")?"24:00":end.toString())+"' and date ='"+date+"';", AppointmentEntity.class);
            AppointmentEntity appointment = null;
            if ( query.getResultList().size()>0){
            appointment = (AppointmentEntity) query.getResultList().get(0);
            }
            return appointment;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
