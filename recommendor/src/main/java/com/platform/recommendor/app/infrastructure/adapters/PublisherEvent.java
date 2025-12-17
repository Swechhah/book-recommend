package com.platform.recommendor.app.infrastructure.adapters;

import com.platform.recommendor.app.domain.ports.out.DomainPublisherEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class PublisherEvent implements DomainPublisherEvent {

    private final ApplicationEventPublisher publisher;

    PublisherEvent(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void publish(Object event) {

        publisher.publishEvent(event);
    }
}
