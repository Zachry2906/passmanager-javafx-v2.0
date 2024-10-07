module id.dojo.account {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.compiler;
    requires java.net.http;
    requires com.google.gson;
    requires com.fasterxml.jackson.databind;


    opens id.dojo.account to javafx.fxml, com.google.gson;
    exports id.dojo.account;
}