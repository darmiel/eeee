package io.d2a.eeee.prompt.wrappers;

import io.d2a.eeee.prompt.RawWrapper;
import io.d2a.eeee.prompt.WrapContext;
import java.util.Scanner;

public class ScannerWrapper implements RawWrapper<Scanner> {

    @Override
    public Scanner wrap(final WrapContext ctx) throws Exception {
        return ctx.getScanner();
    }

}
