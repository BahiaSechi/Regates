package regates.mvp.model;

import regates.mvp.LoginMain;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class LoginService {

    public String sayHello( String name ) {
        ResourceBundle bundle = LoginMain.getMessageBundle();
        if (name.isEmpty()) {
            return bundle.getString("welcome.unknown");
        } else {
            return MessageFormat.format(bundle.getString("welcome.named"), name);
        }
    }
}
