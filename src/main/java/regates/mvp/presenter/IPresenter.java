package regates.mvp.presenter;

public interface IPresenter {
    String getInput();

    void setPresenter( LoginPresenter presenter );

    void setMessage( String text );
}
