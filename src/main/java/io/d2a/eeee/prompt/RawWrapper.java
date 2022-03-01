package io.d2a.eeee.prompt;

public interface RawWrapper<T> extends Wrapper<T> {

    T wrap(final WrapContext ctx) throws Exception;

}
