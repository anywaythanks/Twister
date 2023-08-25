package com.github.anywaythanks.twisterresource.repository.impl;

import com.github.anywaythanks.twisterresource.models.GeneralAccount;
import com.github.anywaythanks.twisterresource.models.Twist;
import com.github.anywaythanks.twisterresource.repository.ActualCaseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class HibernateActualCaseRepository implements ActualCaseRepository {
    @PersistenceContext
    private EntityManager em;

    public List<? extends Map.Entry<Long, Instant>> dates(GeneralAccount generalAccount, Long startId, Long endId) {
        var cb = em.getCriteriaBuilder();
        var queryDate = cb.createQuery(Object[].class);
        var twist = queryDate.from(Twist.class);
        var c = queryDate.multiselect(twist.get("twistCase").get("id"), cb.max(twist.get("createdOn")));
        var p = cb.and(cb.greaterThanOrEqualTo(twist.get("twistCase").get("id"), startId),
                cb.lessThanOrEqualTo(twist.get("twistCase").get("id"), endId));
        c.where(cb.and(cb.equal(twist.get("generalAccount"), generalAccount), p));
        c.groupBy(twist.get("twistCase"));
        c.orderBy(cb.desc(twist.get("twistCase").get("id")));
        return em.createQuery(c).getResultList().stream()
                .map(objects -> new AbstractMap.SimpleEntry<>((Long) objects[0], (Instant) objects[1])).toList();
    }
}
