package net.chrisrichardson.customersandorders;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(CustomersAndOrdersMain.class)
public class EndToEndTestConfiguration {

}
