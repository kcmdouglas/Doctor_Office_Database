import java.util.List;
import org.sql2o.*;

public class Specialty {
  private int id;
  private String field;

  public int getId() {
    return id;
  }

  public String getField() {
    return field;
  }

  public Specialty(String field) {
    this.field = field;
  }

  // public String getSpecialtyField() {
  // try(Connection con = DB.sql2o.open()) {
  //   String sql = "SELECT * FROM specialties WHERE id = :specialtyId";
  //   Specialty specialty = con.createQuery(sql)
  //     .addParameter("specialtyId", this.specialtyId)
  //     .executeAndFetchFirst(Specialty.class);
  //   return specialty.getField();
  // }

  public static List<Specialty> all() {
    String sql = "SELECT * FROM specialties";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Specialty.class);
    }
  }

  @Override
  public boolean equals(Object otherSpecialty){
    if (!(otherSpecialty instanceof Specialty)) {
      return false;
    } else {
      Specialty newSpecialty = (Specialty) otherSpecialty;
      return this.getField().equals(newSpecialty.getField());
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO specialties(field) VALUES (:field)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("field", this.field)
        .executeUpdate()
        .getKey();
    }
  }

  public static Specialty find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM specialties where id=:id";
    Specialty specialty = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Specialty.class);
      return specialty;
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
