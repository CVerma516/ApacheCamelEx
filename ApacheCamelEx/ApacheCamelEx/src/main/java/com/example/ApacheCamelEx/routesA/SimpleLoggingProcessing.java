package com.example.ApacheCamelEx.routesA;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleLoggingProcessing implements org.apache.camel.Processor {

    private Logger logger= LoggerFactory.getLogger(SimpleLoggingProcessing.class);
    @Override
    public void process(Exchange exchange) throws Exception {
        logger.info("SimpleLoggingProcessing {}", exchange.getMessage().getBody());
    }
}
