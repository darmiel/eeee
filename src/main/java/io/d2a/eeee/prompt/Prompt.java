package io.d2a.eeee.prompt;

import io.d2a.eeee.annotation.provider.AnnotationProvider;

public interface Prompt {

    String prompt(final AnnotationProvider provider, final String prompt, final String def);

}
