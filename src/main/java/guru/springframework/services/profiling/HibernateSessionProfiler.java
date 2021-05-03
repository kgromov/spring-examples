package guru.springframework.services.profiling;


import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.hibernate.Session;
import org.hibernate.engine.spi.SessionImplementor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Map;

@Aspect
@Component
@RequiredArgsConstructor
@ConditionalOnBean(MemoryAnalyzer.class)
public class HibernateSessionProfiler {
    private final MemoryAnalyzer memoryAnalyzer;
    @PersistenceContext
    private EntityManager entityManager;

    @Around("@annotation(org.springframework.transaction.annotation.Transactional)")
//    @Around("execution(@org.springframework.transaction.annotation.Transactional * *(..))")
    public Object profile(ProceedingJoinPoint joinPoint) throws Throwable {
        Session s = entityManager.unwrap(Session.class);
        SessionImplementor sessionImpl = (SessionImplementor) s;
        org.hibernate.engine.spi.PersistenceContext context = sessionImpl.getPersistenceContext();
        Map entities = context.getEntitiesByKey();
        Object result = joinPoint.proceed(joinPoint.getArgs());
        memoryAnalyzer.printObjectsMemoryUsage(entities);
        return result;
    }

}
