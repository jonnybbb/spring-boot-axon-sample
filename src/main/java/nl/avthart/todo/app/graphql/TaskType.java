package nl.avthart.todo.app.graphql;

import lombok.Value;

@Value
public class TaskType {

    private String id;

    private String username;

    private String title;

    private boolean completed;

    private boolean starred;

    private String authorId;

}
