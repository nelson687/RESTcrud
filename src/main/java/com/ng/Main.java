//package com.ng;
//
//import com.google.inject.Guice;
//import com.google.inject.Injector;
//import com.google.inject.servlet.GuiceServletContextListener;
//import com.google.inject.servlet.ServletModule;
//import com.ng.dao.DaoImpl;
//
//import javax.xml.ws.Service;
//
//public class Main extends GuiceServletContextListener {
//
//
//    public static Injector injector;
//    @Override
//    protected Injector getInjector() {
//        System.out.println("Getting injector");
//        injector = Guice.createInjector(new ServletModule() {
//            // Configure your IOC
//            @Override
//            protected void configureServlets() {
//                bind(DaoImpl.class);
//            }
//        });
//        return injector;
//    }
//}