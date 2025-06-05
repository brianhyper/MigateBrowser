module com.migate.migatebrowser {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.prefs;
    requires javafx.web;
    requires java.desktop;
    requires javafx.swing;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.google.gson;

    opens com.migate.browser to javafx.fxml;
    exports com.migate.browser;

    opens com.migate to javafx.fxml;
    exports com.migate;
}