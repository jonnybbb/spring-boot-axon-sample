package nl.avthart.todo.app.domain.task.commands;

import lombok.Value;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

@Value
public class CreateAuthorCommand {
    private String id;
    private String author;
}
