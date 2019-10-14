package com.xy_asia.monitor.metrics;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import org.springframework.stereotype.Component;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MainMetricsBean {

    private AtomicInteger hlsExceptionOrder;

    private AtomicInteger soneExceptionOrder;

    private AtomicInteger hlsLongExceptionOrder;

    private AtomicInteger soneLongExceptionOrder;

    private AtomicInteger shaxianExceptionOrder;

    public MainMetricsBean(MeterRegistry registry) {

        hlsExceptionOrder = registry.gauge("xy_asia.sone.hls_exception_order", Tags.of("gauge","exception_order"),
                new AtomicInteger(0));

        soneExceptionOrder = registry.gauge("xy_asia.sone.sone_exception_order", Tags.of("gauge","exception_order"),
                new AtomicInteger(0));

        shaxianExceptionOrder = registry.gauge("xy_asia.shaxian.shaxian_exception_order", Tags.of("gauge","exception_order"),
                new AtomicInteger(0));


        hlsLongExceptionOrder = registry.gauge("xy_asia.sone.hls_long_exception_order", Tags.of("gauge","long_exception_order"),
                new AtomicInteger(0));

        soneLongExceptionOrder = registry.gauge("xy_asia.sone.sone_long_exception_order", Tags.of("gauge","long_exception_order"),
                new AtomicInteger(0));


    }


    public void setHlsExceptionOrder(Integer hlsExceptionOrder) {
        this.hlsExceptionOrder.getAndSet(hlsExceptionOrder);
    }

    public void setSoneExceptionOrder(Integer soneExceptionOrder) {
        this.soneExceptionOrder.getAndSet(soneExceptionOrder);
    }

    public void setHlsLongExceptionOrder(Integer hlsLongExceptionOrder) {
        this.hlsLongExceptionOrder.getAndSet(hlsLongExceptionOrder);
    }

    public void setSoneLongExceptionOrder(Integer soneLongExceptionOrder) {
        this.soneLongExceptionOrder.getAndSet(soneLongExceptionOrder);
    }

    public void setShaxianExceptionOrder(Integer shaxianExceptionOrder) {
        this.shaxianExceptionOrder.getAndSet(shaxianExceptionOrder);
    }
}