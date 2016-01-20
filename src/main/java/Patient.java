import java.util.List;
import org.sql2o.*;

public class Patient {
  private int id;
  private String lastName;
  private String firstName;
  private int doctorId;
  private String birthdate;

  public int getId() {
    return id;
  }

  public String getLastName() {
    return lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getBirthdate() {
    return birthdate;
  }

  public int getDoctorId() {
    return doctorId;
  }

  public Patient(String lastName, String firstName, String birthdate, int doctorId) {
    this.lastName = lastName;
    this.firstName = firstName;
    this.birthdate = birthdate;
    this.doctorId = doctorId;
  }

  public static List<Patient> all() {
    String sql = "SELECT last_name AS lastName, first_name AS firstName, birthdate, doctor_id AS doctorId FROM patients";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Patient.class);
    }
  }

  @Override
  public boolean equals(Object otherPatient){
    if (!(otherPatient instanceof Patient)) {
      return false;
    } else {
      Patient newPatient = (Patient) otherPatient;
      return this.getId() == newPatient.getId() &&
             this.getLastName().equals(newPatient.getLastName()) &&
             this.getFirstName().equals(newPatient.getFirstName()) &&
             this.getBirthdate().equals(newPatient.getBirthdate()) &&
             this.getDoctorId() == newPatient.getDoctorId();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO patients (last_name, first_name, birthdate, doctor_id) VALUES (:lastName, :firstName, :birthdate, :doctorId)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("lastName", this.lastName)
        .addParameter("firstName", this.firstName)
        .addParameter("birthdate", this.birthdate)
        .addParameter("doctorId", this.doctorId)
        .executeUpdate()
        .getKey();
    }
  }

  public static Patient find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT id, last_name AS lastName, first_name AS firstName, birthdate, doctor_id AS doctorId FROM patients where id=:id";
    Patient patient = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Patient.class);
      return patient;
    }
  }
//
// public List<Task> getTasks(){
//   try(Connection con = DB.sql2o.open()) {
//     String sql = "SELECT * FROM tasks where categoryId=:id";
//     return con.createQuery(sql)
//       .addParameter("id", this.id)
//       .executeAndFetch(Task.class);
//   }
// }
//
}
