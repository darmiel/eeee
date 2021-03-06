package example.generate;

import io.d2a.eeee.annotation.annotations.common.Range;
import io.d2a.eeee.annotation.annotations.generate.Fill;
import io.d2a.eeee.annotation.annotations.generate.FillRange;
import io.d2a.eeee.annotation.annotations.generate.Generate;
import io.d2a.eeee.generate.random.RandomFactory;
import io.d2a.eeee.inject.Inject;
import io.d2a.eeee.inject.Injector;
import java.util.Arrays;

public class ArrayExample {

    @Generate
    public ArrayExample(
        @FillRange({1, 10}) @Range({'A', 'H'}) char[] chars,
        @Fill(5) @Range({'A', 'H'}) char[] chars5,
        @Inject final String name
    ) {
        assert chars5.length == 5;
        assert chars.length >= 1 && chars.length <= 10;

        System.out.println(Arrays.toString(chars) + " from " + name);
    }

    public static void main(String[] args) throws Exception {
        final Injector injector = new Injector().register(String.class, "Daniel");

        final ArrayExample[] arrayExamples = new ArrayExample[20];
        RandomFactory.fill(arrayExamples, "", injector);
    }

}
