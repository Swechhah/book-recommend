package com.platform.recommendor.app.domain.ports.out;

public interface DomainPublisherEvent {
    void publish(Object event);

}
