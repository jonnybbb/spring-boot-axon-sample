package nl.avthart.todo.app.domain.task;

import nl.avthart.todo.app.domain.task.commands.CreateAuthorCommand;
import nl.avthart.todo.app.domain.task.commands.CreateTaskCommand;
import nl.avthart.todo.app.domain.task.events.AuthorCreatedEvent;
import nl.avthart.todo.app.domain.task.events.TaskCreatedEvent;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
public class Author {
    @AggregateIdentifier
    private String id;
    /**
     * Creates a new Task.
     *
     * @param command create Task
     */

    public Author(CreateAuthorCommand command) {
        apply(new AuthorCreatedEvent(command.getId(), command.getAuthor()));
    }

    Author() {
    }



    @EventSourcingHandler
    void on(AuthorCreatedEvent event) {
        this.id = event.getId();
    }
}
