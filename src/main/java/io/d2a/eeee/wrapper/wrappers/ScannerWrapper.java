package io.d2a.eeee.wrapper.wrappers;

import io.d2a.eeee.annotations.provider.AnnotationProvider;
import io.d2a.eeee.wrapper.Wrapper;
import java.util.Scanner;

public class ScannerWrapper implements Wrapper<Scanner> {

    @Override
    public Scanner wrap(
        final Scanner scanner,
        final String prompt,
        final AnnotationProvider provider
    ) {
        return scanner;
    }

}
