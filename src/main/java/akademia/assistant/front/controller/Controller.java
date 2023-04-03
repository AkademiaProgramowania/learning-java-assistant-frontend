package akademia.assistant.front.controller;

import akademia.assistant.front.service.Service;
import akademia.assistant.front.view.ViewFactory;

public abstract class Controller {
    Service service;
    ViewFactory viewFactory;

    Controller(Service service, ViewFactory viewFactory) {
        this.service = service;
        this.viewFactory = viewFactory;
    }

    public abstract String getFXMLName();

    public void closeWindow() {
        viewFactory.closeWindow(this);
    }
}
