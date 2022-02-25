package io.d2a.eeee.nw.wrappers;

import io.d2a.eeee.nw.RawWrapper;
import io.d2a.eeee.nw.WrapContext;
import java.util.Scanner;

public class ScannerWrapper implements RawWrapper<Scanner> {

    @Override
    public Scanner wrap(final WrapContext ctx) throws Exception {
        return ctx.getScanner();
    }

}
