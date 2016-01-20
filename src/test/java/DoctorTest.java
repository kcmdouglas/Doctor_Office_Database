import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class DoctorTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Doctor.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfNamesAretheSame() {
    Doctor firstDoctor = new Doctor("Dr. Lahiri", 1);
    Doctor secondDoctor = new Doctor("Dr. Lahiri", 1);
    assertTrue(firstDoctor.equals(secondDoctor));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Doctor newDoctor = new Doctor("Dr. Lahiri", 1);
    newDoctor.save();
    assertTrue(Doctor.all().get(0).equals(newDoctor));
  }

  @Test
  public void find_findDoctorInDatabase_true() {
    Doctor newDoctor = new Doctor("Dr. Lahiri", 1);
    newDoctor.save();
    Doctor savedDoctor = Doctor.find(newDoctor.getId());
    assertTrue(newDoctor.equals(savedDoctor));
  }

}
