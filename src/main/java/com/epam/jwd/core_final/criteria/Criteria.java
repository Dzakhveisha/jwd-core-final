package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.BaseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Should be a builder for {@link BaseEntity} fields
 */
public abstract class Criteria<T extends BaseEntity> {

    protected List<Predicate<T>> predicates;

    public Criteria() {
        this.predicates = new ArrayList<>();
    }

    public int getCountOfPredicates(){
        return predicates.size();
    }

    public Predicate<T> build() {
        return (t -> {
            boolean result = true;
            for (Predicate<T> predicate: predicates) {
                result &= predicate.test(t);
            }
            return result;
        });
    }
    public Criteria<T> searchByName(String name){
        this.predicates.add(t -> t.getName() == name);
        return this;
    }

    public Criteria<T> searchByID(long id){
        this.predicates.add(t -> t.getId() == id);
        return this;
    }
}
