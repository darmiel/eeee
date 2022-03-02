package io.d2a.eeee.generate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import io.d2a.eeee.annotation.annotations.common.Range;
import io.d2a.eeee.annotation.annotations.generate.Fill;
import io.d2a.eeee.annotation.annotations.generate.Generate;
import io.d2a.eeee.generate.random.RandomFactory;
import java.util.Arrays;
import org.junit.Test;

public class SimpleGenerateTest {

    public static class Target {

        private final int a;
        private final int b;
        private final String c;
        private final int[] d;

        @Generate // default generator
        public Target(
            @Range({10, 20}) final int a,
            @Range({-10, -1}) final int b,
            @Range({10, 10}) final String c,
            @Fill(10) @Range({100, 1000}) final int[] d
        ) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
        }

        @Generate("types")
        public Target(
            final int[] a,
            final boolean b,
            final char c,
            final double d,
            final int e,
            final String f
        ) {
            this.a = 0;
            this.b = 0;
            this.c = "";
            this.d = new int[0];
        }

        @Override
        public String toString() {
            return "Target{" +
                "a=" + a +
                ", b=" + b +
                ", c='" + c + '\'' +
                ", d=" + Arrays.toString(d) +
                '}';
        }
    }

    @Test
    public void testGenerate() throws Exception {
        final Target target = RandomFactory.generate(Target.class);

        assertTrue(target.a >= 10 && target.a <= 20);
        assertTrue(target.b >= -10 && target.b <= -1);
        assertEquals(target.c.length(), 10);
        assertEquals(target.d.length, 10);
        for (final int i : target.d) {
            assertTrue(i >= 100 && i <= 1000);
        }
    }

    @Test
    public void testAllTypes() throws Exception {
        final Target target = RandomFactory.generate(Target.class, "types");
    }

}
