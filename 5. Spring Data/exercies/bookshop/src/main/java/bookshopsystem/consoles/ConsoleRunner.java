package bookshopsystem.consoles;

import bookshopsystem.entities.Author;
import bookshopsystem.entities.Book;
import bookshopsystem.entities.Category;
import bookshopsystem.services.interfaces.AuthorService;
import bookshopsystem.services.interfaces.BookService;
import bookshopsystem.services.interfaces.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;


@Component
public class ConsoleRunner implements CommandLineRunner {

    private AuthorService authorService;
    private CategoryService categoryService;
    private BookService bookService;


    public ConsoleRunner(AuthorService authorService, CategoryService categoryService, BookService bookService) {
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws IOException, ParseException {




        List<Book> books = this.bookService.getBooksByAuthor("George","Powell");


        for (Book book: books) {


            System.out.println(book.getTitle());
        }






    }




    public void seedBook() throws IOException, ParseException {

        BufferedReader booksReader = new BufferedReader(new FileReader("C:\\Intelji projects\\Java ORM and Spring Data\\5. Spring Data\\exercies\\bookshop\\src\\main\\java\\bookshopsystem\\files\\books.txt"));
        String line = booksReader.readLine();

        List<String> editionTypeList = List.of("NORMAL","PROMO","GOLD");
        List<String> ageRegistrationList = List.of("MINOR","TEEN","ADULT");

        List<Author> authors = this.authorService.getAllAuthors();
        List<Category> categories = this.categoryService.getAllCategories();
        while((line = booksReader.readLine()) != null) {
           String[] data = line.split("\\s+");
           Random randomAuthor = new Random();

           int authorIndex = randomAuthor.nextInt(authors.size());
           Author author = authors.get(authorIndex);

           int bookTypeId = Integer.parseInt(data[0]);
           String editionType = editionTypeList.get(bookTypeId);

           SimpleDateFormat formatter = new SimpleDateFormat("d/M/yyyy");
           Date releaseDate = formatter.parse(data[1]);

           int copies = Integer.parseInt(data[2]);
           BigDecimal price = new BigDecimal(data[3]);

           String ageRegistration = ageRegistrationList.get(Integer.parseInt(data[4]));

           StringBuilder titleBuilder = new StringBuilder();

            for (int i = 5; i < data.length; i++) {
                titleBuilder.append(data[i]).append(" ");
            }

            titleBuilder.delete(titleBuilder.lastIndexOf(" "), titleBuilder.lastIndexOf(" "));
            String title = titleBuilder.toString();

            Book book  = new Book(title,editionType,price,copies,releaseDate,ageRegistration,author);
            Random randomCategory = new Random();

            int indexCategory = randomCategory.nextInt(categories.size());
            Category category = categories.get(indexCategory);

            book.addCategory(category);
            this.bookService.saveBook(book);
        }
    }
    public void seedAuthors() throws IOException {


        File file =  new File("C:\\Intelji projects\\Java ORM and Spring Data\\5. Spring Data\\exercies\\bookshop\\src\\main\\java\\bookshopsystem\\files\\authors.txt") ;
        BufferedReader booksReader = new BufferedReader(new FileReader(file));
        String line = booksReader.readLine();



        while((line = booksReader.readLine()) != null) {
            String[] data = line.split("\\s+");
            String firstName = data[0];
            String lastName = data[1];

            Author author =  new Author(firstName,lastName);
            this.authorService.saveAuthor(author);

        }
    }
    public void seedCategories() throws IOException {
        File file =  new File("C:\\Intelji projects\\Java ORM and Spring Data\\5. Spring Data\\exercies\\bookshop\\src\\main\\java\\bookshopsystem\\files\\categories.txt") ;
        BufferedReader categoryReader = new BufferedReader(new FileReader(file));
        String line = categoryReader.readLine();


        while ((line = categoryReader.readLine()) != null){


            String[] data = line.split("\\s+");
            String categoryName = data[0];


            if(!(categoryName.trim().isEmpty())){
                Category category = new Category(categoryName);
                this.categoryService.saveCategory(category);
            }
        }





    }

}
