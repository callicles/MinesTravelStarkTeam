package org.springframework.samples.travel.infrastructure.persistence.mongo.user;

import static org.mockito.Mockito.when;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import org.fest.assertions.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.samples.travel.domain.model.user.User;

@RunWith(MockitoJUnitRunner.class)
public class MongoUserRepositoryTest {

    @Mock
    private MongoTemplate mongoTemplate;
    @InjectMocks
    private MongoUserRepository repository;

    @Test
    public void souldFindUserByUsername() {
        // Given
        String username = "username";
        User user = new User();
        when(mongoTemplate.findOne(query(where("username").is(username)), User.class)).thenReturn(user);

        // When & then
        Assertions.assertThat(repository.findByUsername(username)).isSameAs(user);
    }
}
