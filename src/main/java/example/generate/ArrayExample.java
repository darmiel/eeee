package example.generate;

import io.d2a.eeee.annotation.annotations.Fill;
import io.d2a.eeee.annotation.annotations.Generate;
import io.d2a.eeee.annotation.annotations.Range;
import io.d2a.eeee.generate.random.RandomFactory;
import io.d2a.eeee.inject.Inject;
import io.d2a.eeee.inject.Injector;
import java.util.Arrays;

public class ArrayExample {

    @Generate
    public ArrayExample(
        @Fill(5) @Range({'A', 'H'}) char[] chars,
        @Inject final String name
    ) {
        System.out.println(Arrays.toString(chars) + " from " + name);
    }

    public static void main(String[] args) throws Exception {
        final Injector injector = new Injector().register(String.class, "Daniel");
        final ArrayExample[] arrayExamples = new ArrayExample[10];

        System.out.println("Injecting");
        RandomFactory.fillArrayRandom(arrayExamples, "", injector);
    }

}
