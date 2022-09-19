package com.app.ticket.book.aspects;

import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;

import java.util.Map;

public class MDCTaskDecorator implements TaskDecorator {
    public static class MDCDecoratedRunnable implements Runnable {

        private final Runnable runnable;

        private final Map<String, String> contextMap;

        public MDCDecoratedRunnable(Runnable runnable) {
            super();
            this.runnable = runnable;
            this.contextMap = MDC.getCopyOfContextMap();
        }

        @Override
        public void run() {
            try {
                MDC.setContextMap(contextMap);
                runnable.run();
            } finally {
                MDC.clear();
            }
        }

    }

    @Override
    public Runnable decorate(Runnable runnable) {
        return new MDCDecoratedRunnable(runnable);
    }

}