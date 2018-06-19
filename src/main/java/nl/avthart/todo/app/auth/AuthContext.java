package nl.avthart.todo.app.auth;

import graphql.servlet.GraphQLContext;
import nl.avthart.todo.app.domain.user.query.UserEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class AuthContext extends GraphQLContext {

    private final UserEntity user;

    public AuthContext(UserEntity user, Optional<HttpServletRequest> request, Optional<HttpServletResponse> response) {
        super(request, response);
        this.user = user;
    }

    public UserEntity getUser() {
        return user;
    }
}