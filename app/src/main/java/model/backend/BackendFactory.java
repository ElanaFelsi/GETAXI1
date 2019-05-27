package model.backend;
import android.content.Context;

public final class BackendFactory {
    static Backend instance = null;

    public final static Backend getInstance(Context context) {

        if (instance == null)
            instance = new model.datasource.FireBaseDataBase();
        return instance;
    }
}