import com.revature.database.exceptions.FailedToCreateAccountException;
import com.revature.database.exceptions.IncorrectAccountCredentialsException;
import com.revature.service.Login;
import com.revature.service.exceptions.EmptyStringInputException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class LoginTest {

    public Login loginBuilder(String username, String password){
        return new Login(username, password);
    }

    @Test
    void userInputConversionForUsernameTest(){
        Assertions.assertTrue(loginBuilder("user", "pass" ).getUsername() instanceof String, "The class method should always return a String");
    }

    @Test
    void userInputConversionForPasswordTest(){
        Assertions.assertTrue(loginBuilder("user", "pass" ).getPassword() instanceof String, "The class method should always return a String");
    }

    @Test
    void emptyInputUsernameStringExceptionTest(){
        Assertions.assertThrows(EmptyStringInputException.class, () -> loginBuilder(" ", "pass"), "An empty string username will throw an exception");
    }

    @Test
    void emptyInputPasswordStringExceptionTest(){
        Assertions.assertThrows(EmptyStringInputException.class, () -> loginBuilder("user", " "), "An empty string password will throw an exception");
    }

    @Test
    void incorrectCredentialExceptionTest(){
        Assertions.assertThrows(IncorrectAccountCredentialsException.class, () -> loginBuilder("not", "real").authenticateAccountCredentials(),
                "An incorrect username or password will throw an exception");
    }

    @Test
    void correctCredentialLoginTest(){
        Assertions.assertTrue(loginBuilder("user1", "pass1").authenticateAccountCredentials());
    }

    @Test
    void failedToCreateAccountExceptionTest(){
        Login login = new Login();
        Assertions.assertThrows(FailedToCreateAccountException.class, () -> login.createAccount("user1","password"));
    }

    @Test
    void successfulAccountCreationTest(){
        Login login = new Login();
        try {
            Assertions.assertTrue(login.createAccount("newUser", "pass"));
        } catch (FailedToCreateAccountException e) {
            e.printStackTrace();
        }
    }


}
