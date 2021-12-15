import com.revature.database.DummyData;
import com.revature.database.UserCredentialsDao;
import com.revature.database.exceptions.DuplicateUsernameException;
import com.revature.database.exceptions.EmptyUserCredentialDataException;
import com.revature.database.exceptions.IncorrectAccountCredentialsException;
import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserCredentialsDaoTest {

    private UserCredentialsDao userCredentialsDao;
    private String existingUsername;
    private String existingPassword;


    @BeforeAll
    public void InstantiateClass(){
        this.userCredentialsDao = new UserCredentialsDao();
        this.existingUsername = DummyData.usernames.iterator().next();
        this.existingPassword = DummyData.passwords.get(0);
    }
    
    @Test
    void userCredentialSuccessTest() {
        try {
            Assertions.assertTrue(userCredentialsDao.userCredentialCheck(existingUsername, existingPassword));
        } catch (IncorrectAccountCredentialsException | EmptyUserCredentialDataException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    void incorrectUsernameAccountExceptionTest() {
        Assertions.assertThrows(IncorrectAccountCredentialsException.class,
                () -> userCredentialsDao.userCredentialCheck("notARealUsername", existingPassword));
    }

    @Test
    void incorrectPasswordAccountExceptionTest() {
        Assertions.assertThrows(IncorrectAccountCredentialsException.class,
                () -> userCredentialsDao.userCredentialCheck(existingUsername, "notARealPassword"));
    }

    @Test
    void usernameDataCheckEmptyDatabaseExceptionNotThrownTest() {
        Assertions.assertDoesNotThrow(() -> userCredentialsDao.usernameCheck(existingUsername));
    }

    @Test
    void passwordDataCheckEmptyDatabaseExceptionNotThrownTest() {
        Assertions.assertDoesNotThrow(() -> userCredentialsDao.passwordCheck(existingPassword));
    }

    @Test
    void usernameDuplicateExceptionTest() {
        Assertions.assertThrows(DuplicateUsernameException.class,
                () -> userCredentialsDao.usernameDuplicateCheck(existingUsername, existingPassword));
    }

    @Test
    void addNewAccountSuccessTest() {
        Assertions.assertTrue(userCredentialsDao.addNewAccount("newUser", "newPass"));
    }
}
