package regates.mvp.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoginModelTest {
    @Test
    public void should_get_message_when_set_message() throws Exception {
        LoginModel loginModel = new LoginModel();
        final String testString = "Toto";
        loginModel.setMessage(testString);
        assertEquals(testString, loginModel.getMessage());
    }
}