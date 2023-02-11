module assistant.front.learningjavaassistantfrontend {
    requires javafx.controls;
    requires javafx.fxml;


    opens assistant.front.learningjavaassistantfrontend to javafx.fxml;
    exports assistant.front.learningjavaassistantfrontend;
}