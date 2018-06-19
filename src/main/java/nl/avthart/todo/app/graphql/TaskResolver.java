package nl.avthart.todo.app.graphql;

import com.coxautodev.graphql.tools.GraphQLResolver;
import nl.avthart.todo.app.query.task.AuthorEntry;
import nl.avthart.todo.app.query.task.AuthorEntryRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TaskResolver implements GraphQLResolver<TaskType> {

    private AuthorEntryRepository repository;

    public TaskResolver(AuthorEntryRepository repository) {
        this.repository = repository;
    }

    public AuthorType getAuthor(TaskType taskType) {
      //  AuthorEntry authorEntry = repository.findOne(taskType.getAuthorId());
        return new AuthorType(UUID.randomUUID().toString(),"test author");
    }

}
