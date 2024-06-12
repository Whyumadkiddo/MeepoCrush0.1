module com.example.meepocrush {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.media;


    opens com.example.meepocrush to javafx.fxml;
    exports com.example.meepocrush;
}