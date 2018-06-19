package nl.avthart.todo.app.query.task;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author albert
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode(of = { "id" })
public class AuthorEntry {

	@Id
	private String id;


	private String name;

}