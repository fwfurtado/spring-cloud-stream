package com.github.fehwilinando.apigateway.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static com.netflix.zuul.context.RequestContext.getCurrentContext;

public class LogFilter {


    @Configuration
    static class LogPreFilter extends ZuulFilter {

        @Override
        public String filterType() {
            return "pre";
        }

        @Override
        public int filterOrder() {
            return 1;
        }

        @Override
        public boolean shouldFilter() {

            RequestContext context = getCurrentContext();

            return isMobile(context);

        }

        @Override
        public Object run() throws ZuulException {

            getCurrentContext().set("startTime", Instant.now());

            return null;
        }
    }

    private static boolean isMobile(RequestContext context) {
        return Optional.ofNullable(context.getRequest().getHeader("source"))
                    .filter("mobile"::equals)
                        .isPresent();
    }

    @Configuration
    static class LogPostFilter extends ZuulFilter {

        @Override
        public String filterType() {
            return "post";
        }

        @Override
        public int filterOrder() {
            return 1;
        }

        @Override
        public boolean shouldFilter() {

            RequestContext context = getCurrentContext();

            return isMobile(context);
        }

        @Override
        public Object run() throws ZuulException {

            Instant start = (Instant) getCurrentContext().get("startTime");

            System.out.println("[Log] [Elapse time] " + Duration.between(start, Instant.now()).getNano() + " ns" );

            return null;
        }
    }
}
