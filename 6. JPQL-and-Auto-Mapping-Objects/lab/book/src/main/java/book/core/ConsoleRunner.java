package book.core;

import book.constants.GlobalConstants;
import book.entities.*;
import book.services.interfaces.AuthorService;
import book.services.interfaces.BookService;
import book.services.interfaces.CategoryService;
import book.utils.FileUtil;
import book.utils.FileUtilImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;


@Component
public class ConsoleRunner implements CommandLineRunner {
    private AuthorService authorService;
    private BookService bookService;
    private CategoryService categoryService;

    public ConsoleRunner(AuthorService authorService, BookService bookService, CategoryService categoryService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) {

        this.bookService.getSizeBooksFromAuthor("Amanda","Rice");

    }



    public void insertCategories() throws IOException {
        FileUtil fileUtil = new FileUtilImpl();
        String[] data = fileUtil.readFileContent(GlobalConstants.CATEGORIES_FILE_PATH);

        for (String nameCategory :data) {
            Category category = new Category(nameCategory);
            this.categoryService.saveCategory(category);
        }
    }
    public void insertAuthors() throws IOException {
        FileUtil fileUtil = new FileUtilImpl();

        String[] data = fileUtil.readFileContent(GlobalConstants.AUTHORS_FILE_PATH);


        for (String nameAuthors : data) {

            String firstName = nameAuthors.split(" ")[0];
            String lastName = nameAuthors.split(" ")[1];
            Author author =  new Author(firstName,lastName);

            this.authorService.saveAuthor(author);
        }


    }


    public void insertBooks() throws IOException {

        FileUtil fileUtil = new FileUtilImpl();
        String[] infoBooks = fileUtil.readFileContent(GlobalConstants.BOOKS_FILE_PATH);


        Random random = new Random();
        for (String infoBook : infoBooks) {

            String[] tokens = infoBook.split(" ");

            int index = Integer.parseInt(tokens[0]);

            EditionType editionType = findEditionTypeById(index);
            String date = tokens[1];
            String year = date.split("/")[2];
            String month = date.split("/")[1];
            String day = date.split("/")[0];
            LocalDate releaseDate = LocalDate.of(Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(day));

            int copies = Integer.parseInt(tokens[2]);
            BigDecimal price =  new BigDecimal(tokens[3]);
            AgeRestriction ageRestriction =  findAgeRestrictionById(Integer.parseInt(tokens[4]));
            String title = convertAllWordsInAString(Arrays.stream(tokens).skip(5).toArray(String[]::new));



            int categorySize = this.categoryService.categoriesSize();
            int randomIdCategory = random.nextInt(categorySize);
            Category category = this.categoryService.findById((long) randomIdCategory);

            int authorsSize = this.authorService.sizeAuthors();
            int randomIdAuthor = random.nextInt(authorsSize);
            Author author = this.authorService.findById((long) randomIdAuthor);



            Book book =  new Book(title,editionType,price,copies,releaseDate,ageRestriction,author);
            book.addCategory(category);
            this.bookService.saveBook(book);

        }
    }


    private AgeRestriction findAgeRestrictionById(int index){

        switch (index){
            case 0:
            return AgeRestriction.MINOR;
            case 1:
            return AgeRestriction.TEEN;
            case 2:
            return AgeRestriction.ADULT;
            default:
                throw new IllegalArgumentException("ERROR 404 AgeRestriction NOT FOUND");
        }

    }
    private EditionType findEditionTypeById(int index){

        switch (index){
            case 0:
            return EditionType.NORMAL;
            case 1:
            return EditionType.PROMO;
            case 2:
            return EditionType.GOLD;
            default:
                throw new IllegalArgumentException("EditionType NOT FOUND");
        }

    }
    private String convertAllWordsInAString(String[] data){
        StringBuilder output =  new StringBuilder();
        int counter = 0;
        for (String word : data) {
            if (counter == data.length - 1) {
                output.append(String.format("%s", word));
            } else {
                output.append(String.format("%s ", word));
            }
            counter++;
        }
        return output.toString();
    }



}
