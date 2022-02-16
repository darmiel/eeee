package io.d2a.eeee.wrapper;

import io.d2a.eeee.annotations.AnnotationProvider;
import java.util.Scanner;

public interface Wrapper<T> {

    T wrap(
        final Scanner scanner,
        final String prompt,
        final AnnotationProvider provider
    );

}
