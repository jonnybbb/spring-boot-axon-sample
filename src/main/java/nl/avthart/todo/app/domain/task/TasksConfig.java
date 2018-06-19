/*
 * Copyright (c) 2010-2016. Axon Framework
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package nl.avthart.todo.app.domain.task;

import org.axonframework.commandhandling.model.Repository;
import org.axonframework.common.caching.Cache;
import org.axonframework.eventsourcing.*;
import org.axonframework.eventsourcing.eventstore.EventStore;

import org.axonframework.spring.eventsourcing.SpringPrototypeAggregateFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.ShellProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class TasksConfig {

    @Autowired
    private EventStore eventStore;

    @Autowired
    private Snapshotter snapshotter;

    @Autowired
    private Cache cache;

    @Bean
    @Scope("prototype")
    public Task user() {
        return new Task();
    }

    @Bean
    public AggregateFactory<Task> taskAggregateFactory() {
        SpringPrototypeAggregateFactory<Task> aggregateFactory = new SpringPrototypeAggregateFactory<>();
        aggregateFactory.setPrototypeBeanName("task");

        return aggregateFactory;
    }

    @Bean
    public AggregateFactory<Author> authorAggregateFactory() {
        SpringPrototypeAggregateFactory<Author> aggregateFactory = new SpringPrototypeAggregateFactory<>();
        aggregateFactory.setPrototypeBeanName("task");

        return aggregateFactory;
    }

    @Bean
    public Repository<Task> taskRepository() {
        EventCountSnapshotTriggerDefinition snapshotTriggerDefinition = new EventCountSnapshotTriggerDefinition(
                snapshotter,
                50);

        CachingEventSourcingRepository<Task> repository = new CachingEventSourcingRepository<>(taskAggregateFactory(),
                                                                                               eventStore,
                                                                                               cache,
                                                                                               snapshotTriggerDefinition);

        return repository;
        //return new EventSourcingRepository<Task>(taskAggregateFactory(),eventStore,snapshotTriggerDefinition);
    }

    @Bean
    public Repository<Author> authorRepository() {
        EventCountSnapshotTriggerDefinition snapshotTriggerDefinition = new EventCountSnapshotTriggerDefinition(
                snapshotter,
                50);

        return new EventSourcingRepository<Author>(authorAggregateFactory(), eventStore, snapshotTriggerDefinition);
    }
    @Bean
    public TaskCommandHandler taskCommandHandler() {
        TaskCommandHandler userCommandHandler = new TaskCommandHandler(taskRepository(), authorRepository());
        return userCommandHandler;
    }
}