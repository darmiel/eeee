package io.d2a.eeee.generate;

import static org.junit.Assert.assertEquals;

import io.d2a.eeee.annotation.annotations.common.Range;
import io.d2a.eeee.annotation.annotations.generate.Generate;
import io.d2a.eeee.generate.random.RandomFactory;
import org.junit.Test;

public class MultipleGeneratorTest {

    public static class Target {
        private final String a;
        private final String b;
        private final int c;

        @Generate // default
        public Target(final String a, final boolean unused) {
            this.a = a;
            this.b = "default";
            this.c = 0;
        }

        @Generate("b")
        @Range({20, 20})
        public Target(final String a) {
            this.a = a;
            this.b = "b";
            this.c = -1;
        }
    }

    @Test
    public void multipleGenerator() throws Exception {
        final Target def = RandomFactory.generate(Target.class);
        assertEquals(def.b, "default");
        assertEquals(def.c, 0);

        final Target b = RandomFactory.generate(Target.class, "b");
        assertEquals(b.a.length(), 20);
        assertEquals(b.b, "b");
        assertEquals(b.c, -1);
    }

}
