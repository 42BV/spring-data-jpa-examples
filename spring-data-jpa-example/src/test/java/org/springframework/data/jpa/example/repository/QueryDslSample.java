package org.springframework.data.jpa.example.repository;

import java.util.List;

import org.hamcrest.Matchers;
import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.example.domain.User;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:repository-context.xml")
@Transactional
public class QueryDslSample {

    @Autowired
    private UserRepository repository;
    
    
    @Test
    public void testFindByPredicate() {
        User user = new User();
        user.setUsername("username");
        user = repository.save(user);
        
        User shortName = new User();
        shortName.setUsername("x");
        shortName = repository.save(shortName);
        
        Iterable<User> users = repository.findAll(UserExpressions.usernameLongerThanSix());
        Assert.assertThat(users, Matchers.hasItem(user));
        Assert.assertThat(users, Matchers.not(Matchers.hasItem(shortName)));
    }
    
    @Test
    public void testFindByApi() {
        User user = new User();
        user.setUsername("username");
        user = repository.save(user);
        
        User goldMember = new User();
        goldMember.setUsername("goldie");
        // Gold members need to be >= 18
        goldMember.setAge(42);
        // Gold members have to be created atleast 1 year ago
        goldMember.setCreationDate(new LocalDate().minusYears(4));
        goldMember = repository.save(goldMember);
        
        List<User> goldMembers = repository.findGoldMembers();
        Assert.assertThat(goldMembers, Matchers.hasItem(goldMember));
    }
    
//    @Test
//    public void testFindFromCollection() {
//        User user = new User();
//        user.setUsername("someusername");
//
//        Path<String> usernamePath = QUser.user.username;
//        
//        assertEquals("someusername", usernamePath.accept(new EvaluatingVisitor(), user));
//        
//        Matcher<User> hasUsernameLongerThanSix = new ExpressionMatcher<User>(QUser.user, QUser.user.username.length().gt(6));
//        
//        Assert.assertThat(user, hasUsernameLongerThanSix);
//                
//        User shortName = new User();
//        shortName.setUsername("x");
//        
//        Assert.assertThat(shortName, Matchers.not(hasUsernameLongerThanSix));
//    }
    
}
