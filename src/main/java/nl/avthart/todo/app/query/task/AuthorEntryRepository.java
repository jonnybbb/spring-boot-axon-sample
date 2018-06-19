package nl.avthart.todo.app.query.task;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author albert
 */
public interface AuthorEntryRepository extends JpaRepository<AuthorEntry, String> {

}
