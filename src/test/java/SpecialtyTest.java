import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class SpecialtyTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Specialty.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfNamesAretheSame() {
    Specialty firstSpecialty = new Specialty("Endocrinology");
    Specialty secondSpecialty = new Specialty("Endocrinology");
    assertTrue(firstSpecialty.equals(secondSpecialty));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Specialty newSpecialty = new Specialty("Endocrinology");
    newSpecialty.save();
    assertTrue(Specialty.all().get(0).equals(newSpecialty));
  }

  @Test
  public void find_findSpecialtyInDatabase_true() {
    Specialty newSpecialty = new Specialty("Endocrinology");
    newSpecialty.save();
    Specialty savedSpecialty = Specialty.find(newSpecialty.getId());
    assertTrue(newSpecialty.equals(savedSpecialty));
  }

}
