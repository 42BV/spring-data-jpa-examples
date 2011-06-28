package org.springframework.data.jpa.example.repository;

import org.joda.time.LocalDate;
import org.springframework.data.jpa.example.domain.QUser;

import com.mysema.query.types.expr.BooleanExpression;

class UserExpressions {

    public static BooleanExpression goldMember() {
        return adult().and(createdAtleastOneYearAgo());
    }
    
    public static BooleanExpression adult() {
        return QUser.user.age.goe(18);
    }
    
    public static BooleanExpression createdAtleastOneYearAgo() {
        return QUser.user.creationDate.loe(new LocalDate().minusYears(1));
    }
    
    public static BooleanExpression differentFrontThanLastName() {
        return QUser.user.firstname.eq(QUser.user.lastname).not();
    }
    
    public static BooleanExpression usernameLongerThanSix() {
        return QUser.user.username.length().gt(6);
    }
    
}
