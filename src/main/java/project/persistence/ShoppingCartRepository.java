package project.persistence;

import org.springframework.data.repository.CrudRepository;
import project.models.ShoppingCart;

public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Integer> {

}
