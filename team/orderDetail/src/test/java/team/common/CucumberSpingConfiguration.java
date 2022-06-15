package team.common;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import team.OrderDetailApplication;

@CucumberContextConfiguration
@SpringBootTest(classes = { OrderDetailApplication.class })
public class CucumberSpingConfiguration {}
