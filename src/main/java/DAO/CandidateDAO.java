package DAO;
import Models.Candidate;
import Models.Voter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;

@Repository
public class CandidateDAO {
    @Autowired
    private HibernateTemplate hibernateTemplate;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public void save(Candidate candidate) {
        hibernateTemplate.save(candidate);
    }

    public List<Candidate> getAll() {
        return hibernateTemplate.loadAll(Candidate.class);
    }

    public Candidate get(int id) {
        return hibernateTemplate.get(Candidate.class, id);
    }

    @Transactional
    public void update(Candidate candidate) {
        Candidate existingCandidate = hibernateTemplate.get(Candidate.class, candidate.getCandidateId());
        if (existingCandidate != null) {
            existingCandidate.setCandidateName(candidate.getCandidateName() != null ? candidate.getCandidateName() : existingCandidate.getCandidateName());
            
            hibernateTemplate.saveOrUpdate(existingCandidate);
        }
    }

    @Transactional
    public void delete(int id) {
        Candidate candidate = hibernateTemplate.load(Candidate.class, id);
        hibernateTemplate.delete(candidate);
    }



public List<Candidate> getCandidatesNotInPoll(int pollId) {
    String sql = "SELECT c.* FROM Candidate c WHERE c.candidateId NOT IN (SELECT po.opcandidateId FROM PollOption po WHERE po.oppollId = ?)";
    return jdbcTemplate.query(sql, new Object[] { pollId }, new BeanPropertyRowMapper<>(Candidate.class));
}

}