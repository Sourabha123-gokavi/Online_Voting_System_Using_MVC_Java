package DAO;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateTemplate;

import Models.Poll; // Assuming Poll is in the Models package.

@Component
public class PollDAO {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Transactional
    public void save(Poll poll) {
        this.hibernateTemplate.save(poll);
    }

    public Poll get(int id) {
        return this.hibernateTemplate.get(Poll.class, id);
    }

    public List<Poll> getAll() {
        return this.hibernateTemplate.loadAll(Poll.class);
    }

    @Transactional
    public void delete(int id) {
        Poll poll = hibernateTemplate.load(Poll.class, id);
        hibernateTemplate.delete(poll);
    }

    @Transactional
    public void update(Poll poll) {
        this.hibernateTemplate.update(poll);
    }

    @Transactional(readOnly = true)
    public List<Poll> getAllByRegion(String region) {
        String queryString = "from Poll where region = :region";
        Query<Poll> query = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(queryString,
                Poll.class);
        query.setParameter("region", region);
        return query.getResultList();
    }
}
