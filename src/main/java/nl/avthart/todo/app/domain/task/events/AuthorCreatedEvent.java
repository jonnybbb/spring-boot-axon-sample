package nl.avthart.todo.app.domain.task.events;

import lombok.Value;

/**
 * @author albert
 */
@Value
public class AuthorCreatedEvent {

	private final String id;
	private final String name;


}
