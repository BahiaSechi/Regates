package regates.mvp.presenter;

import regates.mvp.model.LoginModel;
import regates.mvp.model.LoginService;

public final class LoginPresenter {
    private LoginModel _model;
    private IPresenter _view;
    private final LoginService _loginService;

    public LoginPresenter() {
        _loginService = new LoginService();
    }

    public LoginPresenter( LoginService loginService ) {
        _loginService = loginService;
    }

    public void setModel( LoginModel model ) {
        _model = model;
    }

    public void setView( IPresenter view ) {
        _view = view;
    }

    public void sayHello() {
        String input = _view.getInput();
        String message = _loginService.sayHello(input);
        _model.setMessage(message);
        _view.setMessage(message);
    }
}
