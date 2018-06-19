package nl.avthart.todo.app.configuration;

import nl.avthart.todo.app.graphql.Mutation;
import nl.avthart.todo.app.graphql.Query;
import nl.avthart.todo.app.query.task.TaskEntryRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GraphQLConfig {

    @Bean
    public Mutation mutation(CommandGateway gateway, TaskEntryRepository taskEntryRepository) {
        return new Mutation(gateway, taskEntryRepository);
    }

    @Bean
    public Query query(TaskEntryRepository taskEntryRepository) {
        return new Query(taskEntryRepository);
    }

}
