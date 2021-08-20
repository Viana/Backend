package com.api;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(format = { "pretty", "html:target/cucumber-html/Resultado", "json:target/cucumber-json/Resultado.json",
		"junit:target/resultados/TEST-Resultado.xml" }, features = { "src/test/java/com/api/cuc/feature" })
public class RunTest {

}