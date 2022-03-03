package io.d2a.eeee.generate;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
    SimpleGenerateTest.class,
    MultipleGeneratorTest.class,
    InterfaceTest.class
})
public class GeneratorTests {

}
