package example.generate;

import io.d2a.eeee.annotation.annotations.Fill;
import io.d2a.eeee.annotation.annotations.Generate;
import io.d2a.eeee.annotation.annotations.Range;
import io.d2a.eeee.generate.random.RandomFactory;
import java.util.Arrays;

public class ArrayExample {

    public static void main(String[] args) throws Exception {
        final ArrayExample arrayExample = RandomFactory.generate(ArrayExample.class);
    }

    @Generate
    public ArrayExample(
        @Fill(10) @Range({1,3}) String[] array
    ) {
        System.out.println(Arrays.toString(array));
    }

}
