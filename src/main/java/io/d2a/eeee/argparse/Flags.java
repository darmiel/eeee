package io.d2a.eeee.argparse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Flags {

    public static final Set<Flag<?>> FLAGS = new HashSet<>();

    private static Map<String, String> parseKeyValues(final String[] args) {
        final List<String> plain = new ArrayList<>();
        final Map<String, String> values = new HashMap<>();

        for (int i = 0; i < args.length; i++) {
            final String arg = args[i];

            if (arg.startsWith("--")) {
                final String s = arg.substring(2);

                final String key;
                final String value;

                if (s.contains("=")) {
                    final String[] split = s.split("=");
                    key = split[0];
                    value = split[1];
                } else {
                    key = s;
                    if (i < args.length - 1) {
                        value = args[i + 1];
                    } else {
                        value = null;
                    }
                }
                values.put(key, value);
                continue;
            }

            plain.add(arg);
        }
        return values;
    }

    public static void parse(final String[] args) {
        final Map<String, String> values = parseKeyValues(args);

        for (final Flag<?> flag : FLAGS) {
            final String val = values.get(flag.key);
            if (val == null) {
                continue;
            }

            try {
                flag.updateValue(val);
            } catch (Exception e) {
                System.out.printf("[🥊] cannot wrap '%s': %s%n", val, e.getMessage());
            }
        }
    }

}
