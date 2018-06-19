package nl.avthart.todo.app.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.google.common.collect.Lists;
import nl.avthart.todo.app.domain.user.query.UserEntity;
import nl.avthart.todo.app.domain.user.query.UserQueryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public class UserQuery  implements GraphQLQueryResolver {

    private UserQueryRepository repository;

    public Iterable<UserType> users(int count, int offset) {
        Page<UserEntity> all = repository.findAll(new PageRequest(offset, count));
        return Lists.newArrayList();
    }

}
