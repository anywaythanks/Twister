package com.github.anywaythanks.twisterresource.repository;

import com.github.anywaythanks.twisterresource.models.Case;
import com.github.anywaythanks.twisterresource.models.CaseSlot;
import com.github.anywaythanks.twisterresource.models.GeneralAccount;
import com.github.anywaythanks.twisterresource.models.Twist;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.Predicate;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class HibernateCaseRepository {
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

    public void detach(Case dCase) {
        em.detach(dCase);
    }

    public void detach(CaseSlot<?> slot) {
        em.detach(slot);
    }
}
