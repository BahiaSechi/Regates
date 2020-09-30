/**package regates.mvp.model;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class LoginServiceTest {
    private static LoginService _service;
    @Parameter(value = 0)
    public String input;
    @Parameter(value = 1)
    public String output;

    @BeforeClass
    public static void oneTimeSetup() {
        Locale.setDefault(new Locale("fr", "FR"));
        _service = new LoginService();
    }

    @Parameters
    public static Collection<String[]> usernames() {
        return Arrays.asList(new String[][]{
                {"", "Bonjour inconnu !"},
                {"Test", "Bonjour Test"}});
    }

    @Test
    public void sayHelloTest() {
        assertEquals(output, _service.sayHello(input));
    }
}
*/