package nvb.dev.officeservice.repository;

import nvb.dev.officeservice.dao.entity.OfficeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeRepository extends MongoRepository<OfficeEntity, String> {
}
