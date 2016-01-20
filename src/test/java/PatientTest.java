import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Rule;
import java.sql.Date;
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
    Patient firstPatient = new Patient("Dostoevsky", "Fyodor", 1821-11-11, 1);
    Patient secondPatient = new Patient("Dostoevsky", "Fyodor", 1821-11-11, 1);
    assertTrue(firstPatient.equals(secondPatient));
  }

  // @Test
  // public void save_returnsTrueIfDescriptionsAretheSame() {
  //   Task myTask = new Task("Mow the lawn", 1);
  //   myTask.save();
  //   assertTrue(Task.all().get(0).equals(myTask));
  // }
  //
  // @Test
  // public void save_assignsIdToObject() {
  //   Task myTask = new Task("Mow the lawn", 1);
  //   myTask.save();
  //   Task savedTask = Task.all().get(0);
  //   assertEquals(myTask.getId(), savedTask.getId());
  // }
  //
  // @Test
  // public void find_findsTaskInDatabase_true() {
  //   Task myTask = new Task("Mow the lawn", 1);
  //   myTask.save();
  //   Task savedTask = Task.find(myTask.getId());
  //   assertTrue(myTask.equals(savedTask));
  // }
  //
  // @Test
  // public void save_savesCategoryIdIntoDB_true() {
  //   Category myCategory = new Category("Household chores");
  //   myCategory.save();
  //   Task myTask = new Task("Mow the lawn", myCategory.getId());
  //   myTask.save();
  //   Task savedTask = Task.find(myTask.getId());
  //   assertEquals(savedTask.getCategoryId(), myCategory.getId());
  // }
}
