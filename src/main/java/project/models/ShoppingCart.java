package project.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    //private List<Book> bookList;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

        /*
    public List<Book> getBookList(){
        return this.bookList;
    }



    public void setBookList(Book book){
        this.bookList.add(book);
    }

     */
}
