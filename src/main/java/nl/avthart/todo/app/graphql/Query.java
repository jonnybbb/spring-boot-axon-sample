package nl.avthart.todo.app.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import nl.avthart.todo.app.query.task.TaskEntry;
import nl.avthart.todo.app.query.task.TaskEntryRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class Query implements GraphQLQueryResolver {
    private TaskEntryRepository taskEntryRepository;

    public Query(TaskEntryRepository taskEntryRepository) {
        this.taskEntryRepository = taskEntryRepository;
    }

    public Iterable<TaskType> tasks(int count, int offset) {
        Page<TaskEntry> all = taskEntryRepository.findAll(new PageRequest(offset, count));
        return all.map(e -> new TaskType(e.getId(),e.getUsername(),e.getTitle(),e.isCompleted(),e.isStarred(),"testuuid"));
    }

    public String login(String username, String password) {
        return "a token";

    }

}