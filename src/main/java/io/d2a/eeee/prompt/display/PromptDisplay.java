package io.d2a.eeee.prompt.display;

import io.d2a.eeee.annotation.provider.AnnotationProvider;

public interface PromptDisplay {

    String prompt(final AnnotationProvider provider, final Class<?> type, final String display);

}
