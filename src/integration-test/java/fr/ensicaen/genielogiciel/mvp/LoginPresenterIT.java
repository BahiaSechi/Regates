package fr.ensicaen.genielogiciel.mvp;

import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import fr.ensicaen.genielogiciel.mvp.model.LoginModel;
import fr.ensicaen.genielogiciel.mvp.model.LoginService;
import fr.ensicaen.genielogiciel.mvp.presenter.IPresenter;
import fr.ensicaen.genielogiciel.mvp.presenter.LoginPresenter;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.mockito.MockitoAnnotations;

@RunWith(Parameterized.class)
public final class LoginPresenterIT {

    @Mock
    private IPresenter _view;
    private final LoginService _loginService = spy(new LoginService());
    private final LoginModel _model = spy(new LoginModel());

    @Parameterized.Parameter(value = 0)
    public String input;
    @Parameterized.Parameter(value = 1)
    public String output;

    @Parameterized.Parameters
    public static Collection<String[]> usernames() {
        return Arrays.asList(new String[][]{
                {"", "Bonjour inconnu !"},
                {"Toto", "Bonjour Toto"}});
    }

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
        Locale.setDefault(new Locale("fr", "FR"));
    }

    @Test
    public void should_return_correct_login_message_when_a_name_is_given_or_not() {
        // given
        LoginPresenter presenter = new LoginPresenter(_loginService);
        presenter.setModel(_model);
        presenter.setView(_view);

        // then
        assertThat(_model.getMessage(), nullValue());
        when(_view.getInput()).thenReturn(input);

        // when
        presenter.sayHello();

        // then
        assertThat(_model.getMessage(), is(output));
        verify(_loginService, times(1)).sayHello(input);
        verify(_model, times(1)).setMessage(output);
    }
}
