package nl.avthart.todo.app.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import nl.avthart.todo.app.domain.task.commands.CreateAuthorCommand;
import nl.avthart.todo.app.domain.task.commands.CreateTaskCommand;
import nl.avthart.todo.app.query.task.TaskEntry;
import nl.avthart.todo.app.query.task.TaskEntryRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.common.IdentifierFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Mutation implements GraphQLMutationResolver {

    private final IdentifierFactory identifierFactory = IdentifierFactory.getInstance();

    private final CommandGateway commandGateway;
    private final TaskEntryRepository taskEntryRepository;

    public Mutation(CommandGateway commandGateway, TaskEntryRepository taskEntryRepository) {
        this.commandGateway = commandGateway;
        this.taskEntryRepository = taskEntryRepository;
    }

    public TaskPayload createTask(String username, String title, String author) throws ExecutionException, InterruptedException {
        String autherId = identifierFactory.generateIdentifier();
        //commandGateway.send(new CreateAuthorCommand(autherId,author));
        CompletableFuture<String> future = commandGateway.send(new CreateTaskCommand(identifierFactory.generateIdentifier(), username, title));
        String result = future.get();

        TaskEntry e = taskEntryRepository.findOne(result);

        return new TaskPayload(new TaskType(e.getId(),e.getUsername(),e.getTitle(),e.isCompleted(),e.isStarred(),autherId));
    }
}