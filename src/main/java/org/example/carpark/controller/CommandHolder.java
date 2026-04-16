package org.example.carpark.controller;

import org.example.carpark.controller.command.*;
import utils.PagesHolder;
import utils.PathsHolder;

import java.util.HashMap;
import java.util.Map;

public class CommandHolder {
    public static final String DELIMITER = ":";
    private static final String GET = "GET" + DELIMITER;
    private static final String POST = "POST" + DELIMITER;

    private final Map<String, Command> commands = new HashMap<>();

    public CommandHolder() {
        initCommands();
    }

    private void initCommands() {

        commands.put(GET + PathsHolder.CARS, new GetCars());
        commands.put(GET + PathsHolder.ADD_CAR, new GetCar());
        commands.put(GET + PathsHolder.EDIT_CAR, new GetCar());


        commands.put(POST + PathsHolder.ADD_CAR, new AddCar());
        commands.put(POST + PathsHolder.EDIT_CAR, new EditCar());
        commands.put(POST + PathsHolder.DELETE_CAR, new DeleteCar());
    }

    Command getCommand(String commandKey) {
        return commands.getOrDefault(commandKey, (req, resp) -> PagesHolder.PAGE_NOT_FOUND);
    }
}
