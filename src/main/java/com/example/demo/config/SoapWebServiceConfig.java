package com.example.demo.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class SoapWebServiceConfig extends WsConfigurerAdapter {
    private static final String namespaceUri = "http://demo.example.com/soap";

    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext context) {
        MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
        messageDispatcherServlet.setApplicationContext(context);
        messageDispatcherServlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(messageDispatcherServlet, "/soapWS/*");
    }

    @Bean
    public XsdSchema userSchema() {
        return new SimpleXsdSchema(new ClassPathResource("xsd/users.xsd"));
    }

    @Bean(name = "wsdl")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema xsdSchema) {
        DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
        definition.setSchema(xsdSchema);
        definition.setLocationUri("/soapWS");
        definition.setPortTypeName("UserService");
        definition.setTargetNamespace(namespaceUri);
        return definition;
    }
}
