package es.riberadeltajo.botomnavigationviewsactivitypruebica.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private final MutableLiveData<String> mNombre;


    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
        mNombre = new MutableLiveData<>();
        mNombre.setValue("Pon aqui tu nombre");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<String> getNombre() {
        return mNombre;
    }

    public void setNombre (String s) {
        mNombre.setValue(s);
    }


}