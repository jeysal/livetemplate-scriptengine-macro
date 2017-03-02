package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.generic

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.Converter

import static com.github.jeysal.java.util.function.SupplierUtils.trying

/**
 * @author seckinger
 * @since 10/18/16
 */
trait InputStreamConverter<R> implements Converter<InputStream, R> {
    @Override
    R convert(final InputStream inputStream) {
        convert(trying(inputStream.&getText).get().orElse(null))
    }
}
