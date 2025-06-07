module dev.swell.desafiodio {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires jakarta.persistence;
    requires static lombok;
    requires org.mapstruct;

    opens dev.swell.desafiodio to javafx.fxml;
    exports dev.swell.desafiodio;

    exports dev.swell.desafiodio.presentation.controller;
    opens dev.swell.desafiodio.presentation.controller to javafx.fxml;
}