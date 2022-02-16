package io.d2a.eeee.inject;

import java.util.HashMap;

public class BiMap<X, Y, Z> extends HashMap<String, Z> {

    private String buildKey(final X x, final Y y) {
        final StringBuilder bob = new StringBuilder();
        if (x != null) {
            bob.append(x).append("::");
        }
        if (y != null) {
            bob.append(y);
        }
        return bob.toString();
    }

    public Z put(final X keyA, final Y keyB, final Z value) {
        return super.put(buildKey(keyA, keyB), value);
    }

    public Z get(final X keyA, final Y keyB) {
        return super.get(this.buildKey(keyA, keyB));
    }

    public boolean containsKey(final X keyA, final Y keyB) {
        return super.containsKey(this.buildKey(keyA, keyB));
    }
}
