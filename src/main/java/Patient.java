import java.util.List;
import org.sql2o.*;
import java.sql.Date;
import java.util.Date;

public class Patient {
  private int id;
  private String lastName;
  private String firstName;
  private Date birthdate;
  private int doctorId;

  public int getId() {
    return id;
  }

  public String getLastName() {
    return lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public Date getBirthdate() {
    return birthdate;
  }

  public int getDoctorId() {
    return doctorId;
  }

  public Patient(String lastName, String firstName, Date birthdate, int doctorId) {
    this.lastName = lastName;
    this.firstName = firstName;
    this.birthdate = birthdate;
    this.doctorId = doctorId;
  }

  public static List<Patient> all() {
    String sql = "SELECT (last_name, first_name, birthdate, doctor_id) FROM patients";
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
      return this.getLastName().equals(newPatient.getLastName());
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO patients(last_name, first_name, birthdate, doctor_id) VALUES (:lastName, :firstName, :birthdate, :doctorId)";
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
      String sql = "SELECT * FROM patients where id=:id";
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
