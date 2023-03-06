package project.models;

public class bookStub {
    private Long id;
    private String bookName;

    public bookStub(Long id, String bookName){
        this.id = id;
        this.bookName = bookName;
    }

    public Long getId(){ return this.id;}
    public void setId(Long id){ this.id = id;}

    public String getBookName(){return this.bookName;}

    public void setBookName(String name){bookName = name;}
}
