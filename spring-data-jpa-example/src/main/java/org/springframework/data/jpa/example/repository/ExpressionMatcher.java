package org.springframework.data.jpa.example.repository;

import java.util.Collections;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import com.mysema.query.collections.ColQueryImpl;
import com.mysema.query.types.EntityPath;
import com.mysema.query.types.Predicate;

public class ExpressionMatcher<T> extends TypeSafeMatcher<T> {
    private final EntityPath<T> path;
    private final Predicate predicate;
    
    public ExpressionMatcher(EntityPath<T> path, Predicate predicate) {
        this.path = path;
        this.predicate = predicate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean matchesSafely(T object) {
        return new ColQueryImpl()
            .from(path, Collections.singletonList(object))
            .where(predicate)
            .exists();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void describeTo(Description description) {
        description.appendText("expression{").appendText(predicate.toString()).appendText("}");
    }

}
