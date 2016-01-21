import java.util.List;
import org.sql2o.*;

public class Doctor {
  private int id;
  private String name;
  private int specialtyId;

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getSpecialtyId() {
    return specialtyId;
  }

  public Doctor(String name, int specialtyId) {
    this.name = name;
    this.specialtyId = specialtyId;
  }

  public String getSpecialtyField() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT name, specialty_id AS specialtyId FROM specialties WHERE id = :specialtyId";
      Specialty specialty = con.createQuery(sql)
        .addParameter("specialtyId", this.specialtyId)
        .executeAndFetchFirst(Specialty.class);
      return specialty.getField();
    }
  }


  public static List<Doctor> all() {
    String sql = "SELECT name, specialty_id AS specialtyId FROM doctors";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Doctor.class);
    }
  }

  @Override
  public boolean equals(Object otherDoctor){
    if (!(otherDoctor instanceof Doctor)) {
      return false;
    } else {
      Doctor newDoctor = (Doctor) otherDoctor;
      return this.getName().equals(newDoctor.getName());
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO doctors(name, specialty_id) VALUES (:name, :specialty_id)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("specialty_id", this.specialtyId)
        .executeUpdate()
        .getKey();
    }
  }

  public static Doctor find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM doctors where id=:id";
    Doctor doctor = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Doctor.class);
      return doctor;
    }
  }

  public List<Patient> getPatients(){
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM patients where doctor_id=:id";
      return con.createQuery(sql)
        .addParameter("id", this.id)
        .executeAndFetch(Patient.class);
    }
  }

}
