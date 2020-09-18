package guru.springframework.repositories;

import guru.springframework.services.profiling.Profiling;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Profiling(timeunit = TimeUnit.MICROSECONDS)
public abstract class AbstractRepository<T> {
    private static final String SELECT_ALL = "select e from %s e";
    private static final String SELECT_BY_DESCRIPTION = "SELECT e FROM %s e WHERE e.description = :description";

    @PersistenceContext
    private EntityManager entityManager;

    public Iterable<T> findAll() {
        String query = String.format(SELECT_ALL, getEntityClass().getSimpleName());
        return entityManager.createQuery(query, getEntityClass()).getResultList();
    }

    public T findById(Long id) {
        return entityManager.find(getEntityClass(), id);
    }

    @Deprecated
    @SuppressWarnings("unchecked")
    public Optional<T> findByDescriptionCriteria(String description) {
        Criteria criteria = createCriteria().add(Restrictions.eq("description", description));
        return Optional.ofNullable((T) criteria.uniqueResult());
    }

    public Optional<T> findByDescriptionCriteriaBuilder(String description) {
        entityManager.getCriteriaBuilder().createQuery();
        return Optional.empty();
    }

    public Optional<T> findByDescription(String description) {
        String query = String.format(SELECT_BY_DESCRIPTION, getEntityClass().getSimpleName());
        TypedQuery<T> typedQuery = entityManager.createQuery(query, getEntityClass());
        typedQuery.setParameter("description", description);
        return Optional.ofNullable(typedQuery.getSingleResult());
    }

    protected Criteria createCriteria() {
        return entityManager.unwrap(Session.class).createCriteria(getEntityClass());
    }

    public void saveOrUpdate(T entity) {
        entityManager.persist(entity);
    }

    public void remove(T entity) {
       entityManager.remove(entity);
    }

    protected abstract Class<T> getEntityClass();
}
