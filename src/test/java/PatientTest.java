import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Rule;
import java.util.Date;

public class PatientTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Patient.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfDescriptionsAretheSame() {
    Patient firstPatient = new Patient("Dostoevsky", "Fyodor", "1821/11/11", 1);
    Patient secondPatient = new Patient("Dostoevsky", "Fyodor", "1821/11/11", 1);
    assertTrue(firstPatient.equals(secondPatient));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Patient newPatient = new Patient("Dostoevsky", "Fyodor", "1821/11/11", 1);
    newPatient.save();
    assertTrue(Patient.find(newPatient.getId()).equals(newPatient));
  }

  @Test
  public void save_assignsIdToObject() {
    Patient myPatient = new Patient("Tolstoy", "Lev", "1828/9/9", 1);
    myPatient.save();
    Patient savedPatient = Patient.find(myPatient.getId());
    assertEquals(myPatient.getId(), savedPatient.getId());
  }

  @Test
  public void find_findsPatientInDatabase_true() {
    Patient myPatient = new Patient("Tolstoy", "Lev", "1828/9/9", 1);
    myPatient.save();
    Patient savedPatient = Patient.find(myPatient.getId());
    assertTrue(myPatient.equals(savedPatient));
  }

  @Test
  public void save_savesDoctorIdIntoDB_true() {
    Doctor myDoctor = new Doctor("Dr. Lahiri", 3);
    myDoctor.save();
    Patient myPatient = new Patient("Pushkin", "Alexander", "1799/6/6", myDoctor.getId());
    myPatient.save();
    Patient savedPatient = Patient.find(myPatient.getId());
    assertEquals(savedPatient.getDoctorId(), myDoctor.getId());
  }
}
