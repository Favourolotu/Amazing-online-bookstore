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
    @Column(unique = true)
    private String username;
    private String password;
    private String roles;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shoppingCart_id", referencedColumnName = "id")
    private ShoppingCart shoppingCart;


    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private List<Book> purchasedBooks = new ArrayList<Book>();

    public User() {
        this.roles = "USER";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
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
        boolean bookPreviouslyPurchased = false;
        int index = 0;
        for (Book b: purchasedBooks){
            if (b.getISBN() == book.getISBN()) bookPreviouslyPurchased = true;
            index ++;
        }

        if (bookPreviouslyPurchased){
            Book bookToUpdate = purchasedBooks.remove(index-1);
            bookToUpdate.setStock(bookToUpdate.getStock() + book.getStock());
            purchasedBooks.add(bookToUpdate);

        } else {
            this.purchasedBooks.add(book);
        }

    }
}
