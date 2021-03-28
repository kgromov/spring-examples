package guru.springframework.repositories.jpa;

import org.hibernate.Criteria;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.SessionImpl;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;

public abstract class AbstractJpaRepository<T> {
    private static final String SELECT_ALL = "select e from %s e";
    private static final String SELECT_BY_DESCRIPTION = "SELECT e FROM %s e WHERE e.description = :description";

    @PersistenceContext
    private EntityManager entityManager;

    public Iterable<T> findAll() {
        String query = String.format(SELECT_ALL, getEntityClass().getSimpleName());
        return entityManager.createQuery(query, getEntityClass()).getResultList();
    }

    public Optional<T> findById(Long id) {
        return Optional.ofNullable(entityManager.find(getEntityClass(), id));
    }

    @Deprecated
    @SuppressWarnings("unchecked")
    public Optional<T> findByDescriptionCriteria(String description) {
        Criteria criteria = createCriteria().add(Restrictions.eq("description", description));
        return Optional.ofNullable((T) criteria.uniqueResult());
    }

    public Optional<T> findByDescriptionCriteriaBuilder(String description) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = criteriaBuilder.createQuery(getEntityClass());
        Root<T> entity = query.from(getEntityClass());
        query.select(entity).where(criteriaBuilder.equal(entity.get("description"), description));
        return Optional.ofNullable(entityManager.createQuery(query).getSingleResult());
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
        try {
            entityManager.unwrap(Session.class).saveOrUpdate(entity);
        } catch (NonUniqueObjectException e) {
            entityManager.merge(entity);
        }
    }

    public void saveAll(Iterable<T> entities) {
        Assert.notNull(entities, "The given Iterable of entities not be null!");
        entities.forEach(this::saveOrUpdate);
    }

    public void remove(T entity) {
        entityManager.remove(entity);
    }

    public void deleteById(Long id) {
        Optional<T> entity = findById(id);
        entity.ifPresent(this::remove);
    };

    protected boolean exists(T entity) {
        return ((SessionImpl) entityManager.unwrap(Session.class)).getPersistenceContext().getEntitiesByKey().containsValue(entity);
    }

    protected abstract Class<T> getEntityClass();
}
