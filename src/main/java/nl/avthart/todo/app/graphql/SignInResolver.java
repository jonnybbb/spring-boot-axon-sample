package nl.avthart.todo.app.graphql;

import com.coxautodev.graphql.tools.GraphQLResolver;
import nl.avthart.todo.app.domain.user.User;

public class SignInResolver implements GraphQLResolver<SignInPayload> {

    public User user(SignInPayload payload) {
        return payload.getUser();
    }
}