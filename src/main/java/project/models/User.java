package project.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userName;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shoppingCart_id", referencedColumnName = "id")
    private ShoppingCart shoppingCart;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private List<Book> purchasedBooks = new ArrayList<Book>();
    public User() {
        this.shoppingCart = new ShoppingCart();
    }

    public String getUserName(){
        return userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public ShoppingCart getShoppingCart(){
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart){
        this.shoppingCart = shoppingCart;
    }


    public List<Book> getPurchasedBooks(){
        return this.purchasedBooks;
    }



    public void setPurchasedBook(Book book){
        this.purchasedBooks.add(book);
    }

}
