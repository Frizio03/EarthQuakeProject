open module ftvp.earthquakeapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires okhttp3;
    requires com.fasterxml.jackson.databind;
    requires java.desktop;

/*
    opens ftvp.earthquakeapp to javafx.fxml;
    exports ftvp.earthquakeapp;
    exports ftvp.earthquakeapp.controller;
    opens ftvp.earthquakeapp.controller to javafx.fxml;*/
}