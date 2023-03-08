package project.persistence;

import project.models.Inventory;
import org.springframework.data.repository.CrudRepository;

public interface InventoryRepository extends CrudRepository<Inventory, Long> {
}
