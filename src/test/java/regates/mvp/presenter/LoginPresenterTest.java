package regates.mvp.presenter;

import java.util.Arrays;
import java.util.Collection;

import regates.mvp.model.LoginModel;
import regates.mvp.model.LoginService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.mockito.MockitoAnnotations;

@RunWith(Parameterized.class)
public class LoginPresenterTest {

    @Parameterized.Parameter(value = 0)
    public String input;
    @Parameterized.Parameter(value = 1)
    public String output;

    @Mock
    private IPresenter _view;
    @Mock
    private LoginModel _model;
    @Mock
    private LoginService _loginService;

    @Parameterized.Parameters
    public static Collection<String[]> usernames() {
        return Arrays.asList(new String[][]{
                {"", "Bonjour inconnu !"},
                {"Test", "Bonjour Test"}});
    }

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void should_return_correct_message_when_given_a_name() {
        // given
        LoginPresenter presenter = new LoginPresenter(_loginService);
        presenter.setModel(_model);
        presenter.setView(_view);
        when(_view.getInput()).thenReturn(input);
        when(_loginService.sayHello(input)).thenReturn(output);

        // when
        presenter.sayHello();

        // then
        verify(_loginService, times(1)).sayHello(input);
        verify(_model, times(1)).setMessage(output);
    }
}
