package lu.waterhackers.map.repository;

import lu.waterhackers.map.model.Sample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SampleRepository extends JpaRepository<Sample, Long> {
}
