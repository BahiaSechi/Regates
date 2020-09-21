package regates.mvp.vue;

import regates.mvp.presenter.IPresenter;
import regates.mvp.presenter.LoginPresenter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public final class LoginView implements IPresenter {
    private LoginPresenter _presenter;
    @FXML
    private TextField _input;
    @FXML
    private Label _message;

    @Override
    public void setPresenter( LoginPresenter presenter ) {
        _presenter = presenter;
    }

    @Override
    public String getInput() {
        return _input.getText();
    }

    @Override
    public void setMessage( String text ) {
        _message.setText(text);
    }

    @FXML
    private void handleSayHello( ActionEvent event ) {
        // Delegate to the controller.
        // Do not send Javafx components.
        _presenter.sayHello();
    }
}
