package nl.avthart.todo.app.query.task;

import nl.avthart.todo.app.domain.task.Author;
import nl.avthart.todo.app.domain.task.events.*;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author albert
 */
@Component
public class AuthorEntryUpdatingEventHandler {

	private final AuthorEntryRepository authorEntryRepository;

	@Autowired
	public AuthorEntryUpdatingEventHandler(AuthorEntryRepository taskEntryRepository) {
		this.authorEntryRepository = taskEntryRepository;
	}
	
	@EventHandler
	void on(AuthorCreatedEvent event) {
		AuthorEntry task = new AuthorEntry(event.getId(), event.getName());
		authorEntryRepository.save(task);
	}


}
