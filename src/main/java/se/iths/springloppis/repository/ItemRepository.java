package se.iths.springloppis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.iths.springloppis.entity.ItemEntity;

import java.util.List;
import java.util.OptionalLong;

@Repository
public interface ItemRepository extends CrudRepository<ItemEntity, Long> {

}
