package DAO;
import Models.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public class CandidateDAO {
    @Autowired
    private HibernateTemplate hibernateTemplate;

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
        Candidate candidate = hibernateTemplate.get(Candidate.class, id);
        if (candidate != null) {
            hibernateTemplate.delete(candidate);
}
}
}