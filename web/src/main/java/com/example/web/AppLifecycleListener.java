package com.example.web;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import java.util.logging.Logger;

@WebListener
public class AppLifecycleListener implements ServletContextListener {

    private static final Logger logger = Logger.getLogger(AppLifecycleListener.class.getName());

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("Application started: " + sce.getServletContext().getServletContextName());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("Application stopped: " + sce.getServletContext().getServletContextName());
    }
}
