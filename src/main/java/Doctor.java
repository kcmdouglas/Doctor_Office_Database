import java.util.List;
import org.sql2o.*;

public class Doctor {
  private int id;
  private String name;
  private int specialty_id;

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getSpecialtyId() {
    return specialty_id;
  }

  public Doctor(String name, int specialty_id) {
    this.name = name;
    this.specialty_id = specialty_id;
  }

  // public String getSpecialtyField() {
  // try(Connection con = DB.sql2o.open()) {
  //   String sql = "SELECT * FROM specialties WHERE id = :specialtyId";
  //   Specialty specialty = con.createQuery(sql)
  //     .addParameter("specialtyId", this.specialtyId)
  //     .executeAndFetchFirst(Specialty.class);
  //   return specialty.getField();
  // }

  public static List<Doctor> all() {
    String sql = "SELECT * FROM doctors";
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
        .addParameter("specialty_id", this.specialty_id)
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

  // public List<Task> getTasks(){
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "SELECT * FROM tasks where categoryId=:id";
  //     return con.createQuery(sql)
  //       .addParameter("id", this.id)
  //       .executeAndFetch(Task.class);
  //   }
  // }

}
