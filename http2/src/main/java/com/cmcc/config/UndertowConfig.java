package com.cmcc.config;

import io.undertow.Undertow;
import io.undertow.UndertowOptions;
import org.springframework.boot.context.embedded.undertow.UndertowBuilderCustomizer;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zmcc on 17/4/26.
 */
public class UndertowConfig implements UndertowBuilderCustomizer {


    @Override
    public void customize(Undertow.Builder builder) {
        builder.setServerOption(UndertowOptions.ENABLE_HTTP2, true);
    }
}
