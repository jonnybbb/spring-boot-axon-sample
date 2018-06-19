package nl.avthart.todo.app;

import com.oembedler.moon.graphql.boot.GraphQLServletProperties;
import graphql.execution.ExecutionStrategy;
import graphql.execution.instrumentation.Instrumentation;
import graphql.execution.preparsed.PreparsedDocumentProvider;
import graphql.servlet.*;
import nl.avthart.todo.app.auth.GraphQLEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;
import java.util.Map;

/**
 * Todo App using Axon and Spring Boot
 *
 * @author albert
 */
@SpringBootApplication
@EnableTransactionManagement
@EnableCaching
public class Application {

	@Autowired
	private GraphQLServletProperties graphQLServletProperties;

	@Autowired(required = false)
	private List<GraphQLServletListener> listeners;

	@Autowired(required = false)
	private Instrumentation instrumentation;

	@Autowired(required = false)
	private GraphQLErrorHandler errorHandler;

	@Autowired(required = false)
	private Map<String, ExecutionStrategy> executionStrategies;

	@Autowired(required = false)
	private GraphQLContextBuilder contextBuilder;

	@Autowired(required = false)
	private GraphQLRootObjectBuilder graphQLRootObjectBuilder;

	@Autowired(required = false)
	private ObjectMapperConfigurer objectMapperConfigurer;

	@Autowired(required = false)
	private PreparsedDocumentProvider preparsedDocumentProvider;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	@ConditionalOnMissingBean
	public GraphQLServlet graphQLServlet(GraphQLSchemaProvider schemaProvider, ExecutionStrategyProvider executionStrategyProvider) {
		return new GraphQLEndpoint(schemaProvider, executionStrategyProvider, objectMapperConfigurer, listeners, instrumentation, errorHandler, contextBuilder, graphQLRootObjectBuilder, preparsedDocumentProvider);
	}
}
