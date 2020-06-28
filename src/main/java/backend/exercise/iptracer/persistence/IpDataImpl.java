package backend.exercise.iptracer.persistence;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IpDataImpl implements IpDataDAO {
    public IpDataImpl(NamedParameterJdbcTemplate template) {
        this.template = template;
    }
    NamedParameterJdbcTemplate template;

    @Override
    public List<IpDataEntity> findAll() {
        return null;
    }

    @Override
    public void insertEmployee(IpDataEntity emp) {
        // TODO
    }

    @Override
    public void updateEmployee(IpDataEntity emp) {

    }

    @Override
    public void executeUpdateEmployee(IpDataEntity emp) {

    }

    @Override
    public void deleteEmployee(IpDataEntity emp) {

    }
}
