package backend.exercise.iptracer.persistence;

import java.util.List;

public interface IpDataDAO {
    List<IpDataEntity> findAll();
    void insertEmployee(IpDataEntity emp);
    void updateEmployee(IpDataEntity emp);
    void executeUpdateEmployee(IpDataEntity emp);
    public void deleteEmployee(IpDataEntity emp);
}
