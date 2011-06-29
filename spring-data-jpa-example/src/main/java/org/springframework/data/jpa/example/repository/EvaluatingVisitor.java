package org.springframework.data.jpa.example.repository;

import java.beans.PropertyDescriptor;

import org.springframework.beans.BeanUtils;
import org.springframework.util.ReflectionUtils;

import com.mysema.query.types.Constant;
import com.mysema.query.types.FactoryExpression;
import com.mysema.query.types.Operation;
import com.mysema.query.types.ParamExpression;
import com.mysema.query.types.Path;
import com.mysema.query.types.SubQueryExpression;
import com.mysema.query.types.TemplateExpression;
import com.mysema.query.types.Visitor;

public class EvaluatingVisitor implements Visitor<Object, Object> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Object visit(Constant<?> expr, Object context) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object visit(FactoryExpression<?> expr, Object context) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object visit(Operation<?> expr, Object context) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object visit(ParamExpression<?> expr, Object context) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object visit(Path<?> path, Object object) {
        // Retrieve the object that contains our property directly
        if(!path.getMetadata().isRoot()) {
            object = visit(path.getMetadata().getParent(), object);
        }

        Object result = null;
       
        switch(path.getMetadata().getPathType()) {
            case PROPERTY:
                // Access the property from public getter method
                String propertyName = path.getMetadata().getExpression().toString();
                PropertyDescriptor descriptor = BeanUtils.getPropertyDescriptor(object.getClass(), propertyName);
                result = ReflectionUtils.invokeMethod(descriptor.getReadMethod(), object);
                break;
            case VARIABLE:
                // Root element is already provided, do nothing
                result = object;
                break;
            default:
                throw new UnsupportedOperationException();
        }
        
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object visit(SubQueryExpression<?> expr, Object context) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object visit(TemplateExpression<?> expr, Object context) {
        // TODO Auto-generated method stub
        return null;
    }

}
