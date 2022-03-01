package io.d2a.eeee.prompt.display;

import io.d2a.eeee.annotation.provider.AnnotationProvider;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StackPromptDisplay implements PromptDisplay {

    private final Component[] components;

    public StackPromptDisplay(final Component... components) {
        this.components = components;
    }

    @Override
    public String prompt(
        final AnnotationProvider provider,
        final Class<?> type,
        final String display
    ) {
        final List<String> res = new ArrayList<>();
        final ComponentContext ctx = new ComponentContext(provider, display, type);

        for (final Component component : this.components) {
            res.add(component.get(ctx));
        }

        res.removeIf(Objects::isNull);
        res.removeIf(String::isEmpty);

        return String.join(" ", res) + " > ";
    }

}
