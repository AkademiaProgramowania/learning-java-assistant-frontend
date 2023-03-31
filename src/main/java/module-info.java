module assistant.front.learningjavaassistantfrontend {
    requires javafx.controls;
    requires javafx.fxml;


    opens assistant.front.learningjavaassistantfrontend to javafx.fxml;
    exports assistant.front.learningjavaassistantfrontend;
    exports assistant.front.learningjavaassistantfrontend.controller;
    opens assistant.front.learningjavaassistantfrontend.controller to javafx.fxml;
    exports assistant.front.learningjavaassistantfrontend.model;
    opens assistant.front.learningjavaassistantfrontend.model to javafx.fxml;
    exports assistant.front.learningjavaassistantfrontend.repository;
    opens assistant.front.learningjavaassistantfrontend.repository to javafx.fxml;
    exports assistant.front.learningjavaassistantfrontend.exception;
    opens assistant.front.learningjavaassistantfrontend.exception to javafx.fxml;
    exports assistant.front.learningjavaassistantfrontend.service;
    opens assistant.front.learningjavaassistantfrontend.service to javafx.fxml;
    exports assistant.front.learningjavaassistantfrontend.view;
    opens assistant.front.learningjavaassistantfrontend.view to javafx.fxml;
}