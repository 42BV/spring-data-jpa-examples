package org.springframework.data.jpa.example.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.example.domain.QUser;
import org.springframework.data.jpa.example.domain.User;

import com.mysema.query.jpa.impl.JPAQuery;


/**
 * Implementation fo the custom repository functionality declared in
 * {@link UserRepositoryCustom} based on JPA. To use this implementation in
 * combination with Spring Data JPA you can either register it programatically:
 * 
 * <pre>
 * EntityManager em = ... // Obtain EntityManager
 * 
 * UserRepositoryCustom custom = new UserRepositoryImpl();
 * custom.setEntityManager(em);
 * 
 * RepositoryFactorySupport factory = new JpaRepositoryFactory(em);
 * UserRepository repository = factory.getRepository(UserRepository.class, custom);
 * </pre>
 * 
 * Using the Spring namespace the implementation will just get picked up due to
 * the classpath scanning for implementations with the {@code Impl} postfix.
 * 
 * <pre>
 * &lt;jpa:repositories base-package=&quot;com.acme.repository&quot; /&gt;
 * </pre>
 * 
 * If you need to manually configure the custom instance see
 * {@link UserRepositoryJdbcImpl} for an example.
 * 
 * @author Oliver Gierke
 */
class UserRepositoryImpl implements UserRepositoryCustom {
    
    @PersistenceContext
    private EntityManager em;

    /**
     * Configure the entity manager to be used.
     * 
     * @param em the {@link EntityManager} to set.
     */
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.data.jpa.sample.repository.UserRepositoryCustom#
     * myCustomBatchOperation()
     */
    public List<User> myCustomBatchOperation() {
        return em.createQuery("from User", User.class).getResultList();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> findGoldMembers() {
        QUser user = QUser.user;
        JPAQuery query = new JPAQuery(em);
        query.from(user).where(UserExpressions.goldMember());
        return query.list(user);
    }

}
