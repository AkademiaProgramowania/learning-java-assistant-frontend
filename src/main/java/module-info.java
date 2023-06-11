module assistant.front.learningjavaassistantfrontend {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;


    opens akademia.assistant.front to javafx.fxml;
    exports akademia.assistant.front;
    exports akademia.assistant.front.controller;
    opens akademia.assistant.front.controller to javafx.fxml;
    exports akademia.assistant.front.model;
    opens akademia.assistant.front.model to javafx.fxml;
    exports akademia.assistant.front.repository;
    opens akademia.assistant.front.repository to javafx.fxml;
    exports akademia.assistant.front.exception;
    opens akademia.assistant.front.exception to javafx.fxml;
    exports akademia.assistant.front.service;
    opens akademia.assistant.front.service to javafx.fxml;
    exports akademia.assistant.front.view;
    opens akademia.assistant.front.view to javafx.fxml;
}