package dev.swell.desafiodio.architecture;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Parent;

public class ViewManager {
    private static final ViewManager INSTANCE = new ViewManager();

    private final StringProperty currentView = new SimpleStringProperty("");

    private ViewManager() {
    }


    public static ViewManager getInstance() {
        return INSTANCE;
    }

    public StringProperty currentViewProperty() {
        return currentView;
    }

    private void _setView(String view) {
        if (currentView.get().equals(view)) return;
        currentView.setValue(view);
    }

    public static void setView(String view) {
        INSTANCE._setView(view);
    }


}
