package DAO;

import Models.Voter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class VoterDAO {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public void save(Voter voter) {
        hibernateTemplate.save(voter);
    }

    @Transactional(readOnly = true)
    public List<Voter> getAll() {
        return hibernateTemplate.loadAll(Voter.class);
    }

    public Voter get(int id) {
        return hibernateTemplate.get(Voter.class, id);
    }

    @Transactional
    public void delete(int id) {
        Voter voter = hibernateTemplate.load(Voter.class, id);
        hibernateTemplate.delete(voter);
    }

    @Transactional
    public void update(Voter updatedVoter) {
        Voter existingVoter = hibernateTemplate.get(Voter.class, updatedVoter.getVoterId());
        if (existingVoter != null) {
            existingVoter.setVoterName(
                    updatedVoter.getVoterName() != null ? updatedVoter.getVoterName() : existingVoter.getVoterName());
            existingVoter.setPassword(
                    updatedVoter.getPassword() != null ? updatedVoter.getPassword() : existingVoter.getPassword());
            hibernateTemplate.saveOrUpdate(existingVoter);
        }
    }

    public Voter findByUsername(String username) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM voter WHERE voterName = ?",
                    new Object[] { username },
                    new RowMapper<Voter>() {
                        @Override
                        public Voter mapRow(ResultSet rs, int rowNum) throws SQLException {
                            Voter voter = new Voter();
                            voter.setVoterId(rs.getInt("voterId"));
                            voter.setVoterName(rs.getString("voterName"));
                            voter.setPassword(rs.getString("password")); // Assuming password is securely hashed
                            return voter;
                        }
                    });
        } catch (EmptyResultDataAccessException e) {
            return null; // No user found
        }
    }

    // This method should be removed or modified to not handle password directly
    // Keeping for reference, should not be used for password checks
    public Integer getVoterId(String username, String password) {
        Voter voter = findByUsername(username);
        if (voter != null && BCrypt.checkpw(password, voter.getPassword())) {
            return voter.getVoterId();
        }
        return null; // Return null if no user found or passwords do not match
    }
}
