package org.springframework.data.jpa.example.repository;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import com.mysema.query.types.Path;

public class PropertyMatcher<T> extends TypeSafeMatcher<Object> {
    private Matcher<T> matcher;
    private Path<T> path;
    
    public PropertyMatcher(Path<T> path, Matcher<T> matcher) {
        this.path = path;
        this.matcher = matcher;
    }
    
    public boolean matchesSafely(Object object) {
        Object value = path.accept(new EvaluatingVisitor(), object);
        return matcher.matches(value);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void describeTo(Description description) {
        description.appendText("property{").appendText(path.toString()).appendText("}");
    }
    
}
