package project.persistence;
import org.springframework.data.repository.CrudRepository;
import project.models.Inventory;

public interface InventoryRepository extends CrudRepository<Inventory, Long> {

}
