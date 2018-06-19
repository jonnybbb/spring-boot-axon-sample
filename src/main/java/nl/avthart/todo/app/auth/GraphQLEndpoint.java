package nl.avthart.todo.app.auth;

import graphql.execution.ExecutionStrategy;
import graphql.execution.instrumentation.Instrumentation;
import graphql.execution.preparsed.PreparsedDocumentProvider;
import graphql.schema.GraphQLSchema;
import graphql.servlet.*;
import nl.avthart.todo.app.domain.user.query.UserEntity;
import nl.avthart.todo.app.domain.user.query.UserQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@WebServlet(urlPatterns = "/graphql")
public class GraphQLEndpoint extends SimpleGraphQLServlet {


    @Autowired
    private UserQueryRepository userRepository;


    public GraphQLEndpoint(GraphQLSchema schema) {
        this(schema, new DefaultExecutionStrategyProvider());
    }

    public GraphQLEndpoint(GraphQLSchema schema, ExecutionStrategy executionStrategy) {
        this(schema, new DefaultExecutionStrategyProvider(executionStrategy));
    }

    public GraphQLEndpoint(GraphQLSchema schema, ExecutionStrategyProvider executionStrategyProvider) {
        this(schema, executionStrategyProvider, null, null, null, null, null, null, null);
    }

    public GraphQLEndpoint(final GraphQLSchema schema, ExecutionStrategyProvider executionStrategyProvider, ObjectMapperConfigurer objectMapperConfigurer, List<GraphQLServletListener> listeners, Instrumentation instrumentation, GraphQLErrorHandler errorHandler, GraphQLContextBuilder contextBuilder, GraphQLRootObjectBuilder rootObjectBuilder, PreparsedDocumentProvider preparsedDocumentProvider) {
        this(new DefaultGraphQLSchemaProvider(schema), executionStrategyProvider, objectMapperConfigurer, listeners, instrumentation, errorHandler, contextBuilder, rootObjectBuilder, preparsedDocumentProvider);
    }

    public GraphQLEndpoint(GraphQLSchemaProvider schemaProvider, ExecutionStrategyProvider executionStrategyProvider, ObjectMapperConfigurer objectMapperConfigurer, List<GraphQLServletListener> listeners, Instrumentation instrumentation, GraphQLErrorHandler errorHandler, GraphQLContextBuilder contextBuilder, GraphQLRootObjectBuilder rootObjectBuilder, PreparsedDocumentProvider preparsedDocumentProvider) {
        super(schemaProvider, executionStrategyProvider, objectMapperConfigurer, listeners, instrumentation, errorHandler, contextBuilder, rootObjectBuilder, preparsedDocumentProvider);

    }

    @Override
    protected GraphQLContext createContext(Optional<HttpServletRequest> request, Optional<HttpServletResponse> response) {
        UserEntity user = request
                .map(req -> req.getHeader("Authorization"))
                .filter(id -> !id.isEmpty())
                .map(id -> id.replace("Bearer ", ""))
                .map(userRepository::findByUsername)
                .orElse(null);

        if (user == null) {
            throw new RuntimeException("Not found");
        }

        return new AuthContext(user, request, response);
    }


}