package com.eclinic.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "patient", schema = "eclinic", catalog = "")
public class PatientEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "gender")
    private String gender;
    @Basic
    @Column(name = "weight")
    private Integer weight;
    @Basic
    @Column(name = "height")
    private Integer height;
    @Basic
    @Column(name = "diagnosis")
    private String diagnosis;
    @Basic
    @Column(name = "phone")
    private String phone;
    @Basic
    @Column(name = "insuranceCompanyId")
    private Integer insuranceCompanyId;
    @Basic
    @Column(name = "doctorId")
    private Integer doctorId;

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getInsuranceCompanyId() {
        return insuranceCompanyId;
    }

    public void setInsuranceCompanyId(Integer insuranceCompanyId) {
        this.insuranceCompanyId = insuranceCompanyId;
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
        PatientEntity that = (PatientEntity) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(gender, that.gender) && Objects.equals(weight, that.weight) && Objects.equals(height, that.height) && Objects.equals(diagnosis, that.diagnosis) && Objects.equals(phone, that.phone) && Objects.equals(insuranceCompanyId, that.insuranceCompanyId) && Objects.equals(doctorId, that.doctorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, gender, weight, height, diagnosis, phone, insuranceCompanyId, doctorId);
    }

    public static boolean addNewPatient(PatientEntity patient, boolean update) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("eclinic");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            if (update) {
                entityManager.merge(patient);
            } else {
                entityManager.persist(patient);
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

    public static List<PatientEntity> getAllPatients() {
        List<PatientEntity> patientEntities = new ArrayList<>();
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("eclinic");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createNativeQuery("SELECT * FROM patient;", PatientEntity.class);
            patientEntities = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
        return patientEntities;
    }

    public static List<PatientEntity> getAllPatientsForDoctor() {
        List<PatientEntity> patientEntities = new ArrayList<>();
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("eclinic");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createNativeQuery("SELECT * FROM patient;", PatientEntity.class);
            patientEntities = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
        return patientEntities;
    }

    public static List<PatientEntity> getAllPatients(String term) {
        List<PatientEntity> patientEntities = new ArrayList<>();
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("eclinic");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createNativeQuery("SELECT * FROM patient WHERE name LIKE '%" + term + "%'  ORDER BY INSTR(name,'" + term + "');", PatientEntity.class);
            patientEntities = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
        return patientEntities;
    }

    public InsuranceEntity getInsurance(){
        return InsuranceEntity.getInsuranceEntityById(getInsuranceCompanyId());
    }

    public UserEntity getDoctor(){
        return UserEntity.getUserById(getDoctorId()+"");
    }

    public static PatientEntity getPatientById(String id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("eclinic");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        PatientEntity patientEntity;
        try {
            Query query = entityManager.createNativeQuery("SELECT * FROM  patient WHERE id='" + id + "';", PatientEntity.class);
            patientEntity = (PatientEntity) query.getResultList().get(0);
        } catch (Exception exception) {
            return null;
        }finally {
            entityManager.close();
            entityManagerFactory.close();
        }
        return patientEntity;
    }

    public static boolean removePatient(PatientEntity patient) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("eclinic");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.remove(entityManager.contains(patient) ? patient : entityManager.merge(patient));
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

    public static List<PatientEntity> getAllPatientsByPhoneTerm(String phone) {
        List<PatientEntity> patientEntities = new ArrayList<>();
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("eclinic");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createNativeQuery("select * from patient where phone like '%"+phone+"%' order by instr(phone, '"+phone+"');", PatientEntity.class);
            patientEntities = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
        return patientEntities;
    }

    public static PatientEntity getPatientByPhone(String phone){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("eclinic");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        PatientEntity patientEntity;
        try {
            Query query = entityManager.createNativeQuery("SELECT * FROM  patient WHERE phone='" + phone + "';", PatientEntity.class);
            patientEntity = (PatientEntity) query.getResultList().get(0);
        } catch (Exception exception) {
            return null;
        }finally {
            entityManager.close();
            entityManagerFactory.close();
        }
        return patientEntity;
    }
}
