import com.revature.database.exceptions.FailedToCreateAccountException;
import com.revature.database.exceptions.IncorrectAccountCredentialsException;
import com.revature.service.UserLoginHandler;
import com.revature.service.exceptions.EmptyInputException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class UserLoginHandlerTest {

    public UserLoginHandler loginBuilder(String username, String password){
        return new UserLoginHandler(username, password);
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
    void spaceInputUsernameStringExceptionTest(){
        Assertions.assertThrows(EmptyInputException.class, () -> loginBuilder("   ", "pass"), "An empty string username will throw an exception");
    }

    @Test
    void emptyInputUsernameStringExceptionTest(){
        Assertions.assertThrows(EmptyInputException.class, () -> loginBuilder("", "pass"), "An empty string username will throw an exception");
    }

    @Test
    void spaceInputPasswordStringExceptionTest(){
        Assertions.assertThrows(EmptyInputException.class, () -> loginBuilder("user", "    "), "An empty string password will throw an exception");
    }

    @Test
    void emptyInputPasswordStringExceptionTest(){
        Assertions.assertThrows(EmptyInputException.class, () -> loginBuilder("user", ""), "An empty string password will throw an exception");
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
        UserLoginHandler userLoginHandler = new UserLoginHandler();
        Assertions.assertThrows(FailedToCreateAccountException.class, () -> userLoginHandler.createAccount("user1","password"));
    }

    @Test
    void successfulAccountCreationTest(){
        UserLoginHandler userLoginHandler = new UserLoginHandler();
        try {
            Assertions.assertTrue(userLoginHandler.createAccount("newUser", "pass"));
        } catch (FailedToCreateAccountException e) {
            e.printStackTrace();
        }
    }



}
