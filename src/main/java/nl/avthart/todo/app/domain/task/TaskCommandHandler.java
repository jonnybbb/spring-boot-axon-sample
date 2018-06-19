package nl.avthart.todo.app.domain.task;

import nl.avthart.todo.app.domain.task.commands.*;
import nl.avthart.todo.app.domain.task.events.TaskCompletedEvent;
import nl.avthart.todo.app.domain.task.events.TaskStarredEvent;
import nl.avthart.todo.app.domain.task.events.TaskTitleModifiedEvent;
import nl.avthart.todo.app.domain.task.events.TaskUnstarredEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.Repository;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

public class TaskCommandHandler {

    private Repository<Task> repository;
    private Repository<Author> authorRepository;

    public TaskCommandHandler(Repository<Task> repository, Repository<Author> authorRepository) {
        this.repository = repository;
        this.authorRepository = authorRepository;
    }


    @CommandHandler
    public String handleCreateAuthor(CreateAuthorCommand createAuthorCommand) throws Exception {
        String id = createAuthorCommand.getId();
        authorRepository.newInstance(() -> new Author(createAuthorCommand));
        return id;
    }

    @CommandHandler
    public String handleCreateUser(CreateTaskCommand command) throws Exception {
        String identifier = command.getId();
        repository.newInstance(() -> new Task(command));
        return identifier;
    }

    /**
     * Completes a Task.
     *
     * @param command complete Task
     */
    @CommandHandler
    void on(CompleteTaskCommand command) {
        apply(new TaskCompletedEvent(command.getId()));
    }

    /**
     * Stars a Task.
     *
     * @param command star Task
     */
    @CommandHandler
    void on(StarTaskCommand command) {
        apply(new TaskStarredEvent(command.getId()));
    }

    /**
     * Unstars a Task.
     *
     * @param command unstar Task
     */
    @CommandHandler
    void on(UnstarTaskCommand command) {
        apply(new TaskUnstarredEvent(command.getId()));
    }

    /**
     * Modifies a Task title.
     *
     * @param command modify Task title
     */
    @CommandHandler
    TaskTitleModifiedEvent on(ModifyTaskTitleCommand command) {
        //assertNotCompleted();
        TaskTitleModifiedEvent payload = new TaskTitleModifiedEvent(command.getId(), command.getTitle());
        apply(payload);
        return payload;
    }


}
