package nvb.dev.officeservice.repository;

import nvb.dev.officeservice.dao.entity.OfficeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OfficeRepository extends MongoRepository<OfficeEntity, String> {

    Optional<OfficeEntity> findByOfficeName(String officeName);

}
