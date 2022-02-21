package example.generate;

import io.d2a.eeee.annotation.annotations.Fill;
import io.d2a.eeee.annotation.annotations.Generate;
import io.d2a.eeee.annotation.annotations.Range;
import io.d2a.eeee.generate.random.RandomFactory;
import java.util.Arrays;

public class ArrayExample {

    @Generate
    public ArrayExample(@Fill(5) @Range({'A', 'H'}) char[] chars) {
        System.out.println(Arrays.toString(chars));
    }

    public static void main(String[] args) throws Exception {
        final ArrayExample[] arrayExamples = new ArrayExample[10];
        RandomFactory.fillArrayRandom(arrayExamples);
    }

}
