package org.example.carpark.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.example.carpark.controller.command.Command;
import org.example.carpark.controller.validator.RegExp;
import org.example.carpark.service.exception.ServiceException;
import utils.AttributesHolder;
import utils.ErrorMessages;

import java.io.IOException;

public class FrontController extends HttpServlet {
    public static final String REDIRECT = "redirect";

    private static final Logger logger = Logger.getLogger(FrontController.class);

    CommandHolder commandHolder;

    @Override
    public void init() {
        commandHolder = new CommandHolder();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandKey = getMethod(request) + CommandHolder.DELIMITER + getPath(request);
        logger.debug("Шукаємо команду за ключем: " + commandKey);

        Command command = commandHolder.getCommand(commandKey);
        logger.debug("Знайдено команду: " + command.getClass().getSimpleName());

        checkIfErrorIsPresent(request);

        executeCommand(request, response, command);
    }

    private void executeCommand(HttpServletRequest request, HttpServletResponse response, Command command) throws IOException {
        String error;
        try {
            String path = command.execute(request, response);
            logger.info("Команда повернула шлях: " + path);

            if (!isRedirected(path)) {
                request.getRequestDispatcher(path).forward(request, response);
            } else {
                request.removeAttribute(AttributesHolder.ERROR_MESSAGE);
            }
            return;

        } catch (ServiceException e) {
            error = e.getMessage();
            request.getSession().setAttribute(AttributesHolder.ERROR_MESSAGE, error);
            logger.error("Помилка сервісу: " + error, e);
        } catch (Exception e) {
            error = ErrorMessages.SERVICE_ERROR;
            logger.error("Критична системна помилка", e);
            request.getSession().setAttribute(AttributesHolder.ERROR_MESSAGE, ErrorMessages.SERVICE_ERROR);
        }

        String regex = "/" + RegExp.NUMBER;
        response.sendRedirect(request.getRequestURI().replaceAll(regex, "").replaceAll("/delete", "") + "?" + AttributesHolder.ERROR_MESSAGE + "=" + error);
        logger.error("Перенаправлення через помилку: " + AttributesHolder.ERROR_MESSAGE);
    }

    private void checkIfErrorIsPresent(HttpServletRequest request) {
        request.setAttribute(AttributesHolder.ERROR_MESSAGE, request.getParameter(AttributesHolder.ERROR_MESSAGE));
    }

    private boolean isRedirected(String path) {
        return REDIRECT.equals(path);
    }

    private String getPath(HttpServletRequest request) {
        String path = request.getRequestURI();
        logger.debug("Оригінальний URI: " + path);
        return path.replaceAll(RegExp.NUMBER, "");
    }

    private String getMethod(HttpServletRequest request) {
        return request.getMethod().toUpperCase();
    }
}
