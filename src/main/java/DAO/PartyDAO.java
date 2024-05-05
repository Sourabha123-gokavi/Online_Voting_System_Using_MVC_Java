package DAO;

import Models.Party;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Component
public class PartyDAO {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public void save(Party party) {
        hibernateTemplate.save(party);
    }

    @Transactional(readOnly = true)
    public List<Party> getAll() {
        return hibernateTemplate.loadAll(Party.class);
    }

    public Party get(int id) {
        return hibernateTemplate.get(Party.class, id);
    }

    @Transactional
    public void delete(int id) {
        Party party = hibernateTemplate.load(Party.class, id);
        hibernateTemplate.delete(party);
    }

    public List<Party> getPartiesNotInPoll(int pollId) {
        String sql = "SELECT p.* FROM Party p WHERE p.partyId NOT IN (SELECT po.oppartyId FROM PollOption po WHERE po.oppollId = ?)";
        return jdbcTemplate.query(sql, new Object[] { pollId }, new BeanPropertyRowMapper<>(Party.class));
    }

}
