package io.d2a.eeee.inject;

import java.lang.reflect.Parameter;

public interface InjectParameterSupplier {

    Object supply(final Parameter parameter) throws Exception;

}
