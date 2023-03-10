# SYSC4806 Project - Amazing Online Bookstore [![Build Status](https://github.com/Favourolotu/Amazing-online-bookstore/actions/workflows/maven.yml/badge.svg?branch=master)](https://github.com/Favourolotu/Amazing-online-bookstore/actions)
The application allows bookstore owners to upload and edit book information, such as ISBN, picture, description, author, publisher, and inventory. Users can search for books in the bookstore, browse through them, and sort/filter based on the above information. They can also put the selected books in their shopping cart and proceed to checkout.

## ORM Diagram
![alt text](https://github.com/favourolotu/amazing-online-bookstore/blob/master/ObjectRelationalMapping.png?raw=true)

## Class Diagram
![alt text](https://github.com/favourolotu/amazing-online-bookstore/blob/master/class_diagram.png?raw=true)


## Technologies Used
- Java Spring Framework
- Thymeleaf template engine
- H2 database

## How to Run
1. Clone the Repo using any Java IDE
2. Navigate to BookstoreApplication.java and click run
3. The initial entrypont of the web application is "/startPage"
4. Once Spring has intiialized, type in "localhost:8080/startPage" into a browser
5. From here, you can access the web application

## Future Improvments
- Adding a repository for keeping track of the books puchased by a user (Currently just displaying books purchased)
- Linking the shopping cart to the current user
- Adding Css styling to the views
- Unifying the naming conventions of the html templates

## This project was worked on by:
- Chase Scott
- Favour Olutu
- Joseph Anyia

