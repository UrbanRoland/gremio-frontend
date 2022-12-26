package com.gremio.repository;

import com.gremio.model.Task;
import com.gremio.model.TaskPage;
import com.gremio.model.TaskSearchCriteria;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class TaskCriteriaRepository {

    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public TaskCriteriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<Task> findAllWithFilters(TaskPage taskPage, TaskSearchCriteria taskSearchCriteria){
        CriteriaQuery<Task> criteriaQuery = criteriaBuilder.createQuery(Task.class);
        Root<Task> taskRoot = criteriaQuery.from(Task.class);
        Predicate predicate = getPredicate(taskSearchCriteria, taskRoot);
        criteriaQuery.where(predicate);
        setOrder(taskPage, criteriaQuery, taskRoot);

        TypedQuery<Task> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(taskPage.getPageNumber() * taskPage.getPageSize());
        typedQuery.setMaxResults(taskPage.getPageSize());

        Pageable pageable = getPageable(taskPage);

        long taskCount = getTaskCount(predicate);

        return new PageImpl<>(typedQuery.getResultList(), pageable, taskCount);
    }

    private long getTaskCount(Predicate predicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Task> countRoot = countQuery.from(Task.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        return  entityManager.createQuery(countQuery).getSingleResult();
    }

    private Pageable getPageable(TaskPage taskPage) {
        Sort sort = Sort.by(taskPage.getSortDirection(), taskPage.getSortBy());
        return PageRequest.of(taskPage.getPageNumber(), taskPage.getPageSize(), sort);

    }

    private void setOrder(TaskPage taskPage, CriteriaQuery<Task> criteriaQuery, Root<Task> taskRoot) {
        if(taskPage.getSortDirection().equals((Sort.Direction.ASC))){
            criteriaQuery.orderBy(criteriaBuilder.asc(taskRoot.get(taskPage.getSortBy())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.desc(taskRoot.get(taskPage.getSortBy())));
        }

    }

    private Predicate getPredicate(TaskSearchCriteria taskSearchCriteria, Root<Task> taskRoot) {
        List<Predicate> predicates = new ArrayList<>();
        if(Objects.nonNull(taskSearchCriteria.getName())){
            predicates.add(
                    criteriaBuilder.like(taskRoot.get("name"), "%" + taskSearchCriteria.getName() + "%")
            );
        }

        if(Objects.nonNull(taskSearchCriteria.getDescription())){
            predicates.add(
                    criteriaBuilder.like(taskRoot.get("description"), "%" + taskSearchCriteria.getDescription() + "%")
            );
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
