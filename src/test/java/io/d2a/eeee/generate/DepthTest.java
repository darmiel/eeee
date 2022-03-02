package io.d2a.eeee.generate;

import static org.junit.Assert.assertNotNull;

import io.d2a.eeee.annotation.annotations.common.Range;
import io.d2a.eeee.annotation.annotations.generate.Depth;
import io.d2a.eeee.annotation.annotations.generate.Generate;
import io.d2a.eeee.generate.random.RandomFactory;
import org.junit.Test;

public class DepthTest {

    public static class Target {
        private final int i;
        private final Target next;

        @Generate("soe")
        public Target(
            @Range({1, 2}) final int i,
            @Generate("soe") final Target next
        ) {
            this.i = i;
            this.next = next;
        }

        @Generate("depth")
        public Target(
            @Generate("depth") @Depth(2) final Target next,
            @Range({1, 2}) final int i
        ) {
            this.i = i;
            this.next = next;
        }

        @Override
        public String toString() {
            return "Target{" +
                "i=" + i +
                ", next=" + next +
                '}';
        }
    }

    @Test(expected = StackOverflowError.class)
    public void testWithoutDepth() throws Exception {
        RandomFactory.generate(Target.class, "soe");
    }

    @Test
    public void testWithDepth() throws Exception {
        final Target target = RandomFactory.generate(Target.class, "depth");
        assertNotNull(target.next); // depth 1
        assertNotNull(target.next.next); // depth 2
    }

}
