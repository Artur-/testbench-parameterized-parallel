package org.vaadin.artur.tbparameterized;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.vaadin.artur.tbparameterized.runner.ParallelParameterized;

import com.vaadin.testbench.TestBenchTestCase;
import com.vaadin.testbench.elements.LabelElement;
import com.vaadin.testbench.parallel.Browser;
import com.vaadin.testbench.parallel.setup.LocalDriver;

@RunWith(ParallelParameterized.class)
public class ParameterizedTestBenchIT extends TestBenchTestCase {

    public static class Param {
        public Browser browser;
        public String name;

        public Param(Browser browser, String parameter) {
            super();
            this.browser = browser;
            this.name = parameter;
        }

        @Override
        public String toString() {
            return "Param [browser=" + browser + ", name=" + name + "]";
        }

    }

    @Parameters(name = "{0}")
    public static Collection<Param> getParams() {
        List<Param> params = new ArrayList<>();
        for (Browser b : new Browser[] { Browser.CHROME, Browser.FIREFOX }) {
            for (String param : new String[] { "John", "Guillermo",
                    "Dmitrii" }) {
                params.add(new Param(b, param));
            }
        }
        return params;
    }

    @Parameter
    public Param param;

    @Test
    public void assertLabel() {
        setDriver(LocalDriver
                .createDriver(param.browser.getDesiredCapabilities()));
        getDriver().get("http://localhost:8080/?name=" + param.name);
        Assert.assertEquals("Hello " + param.name,
                $(LabelElement.class).first().getText());
    }

    @After
    public void stopDriver() {
        if (getDriver() != null) {
            getDriver().quit();
        }

    }
}
