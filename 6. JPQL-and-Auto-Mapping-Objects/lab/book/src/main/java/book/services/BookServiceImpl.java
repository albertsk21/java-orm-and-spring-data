package book.services;

import book.entities.AgeRestriction;
import book.entities.Author;
import book.entities.Book;
import book.entities.EditionType;
import book.repositories.BookRepository;
import book.services.interfaces.BookService;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void saveBook(Book book) {
        this.bookRepository.save(book);
    }
    @Override
    public void findBooksByAgeRestriction(String ageRestriction)  {

        List<Book> books = this.bookRepository.findBooksByAgeRestriction(extractAgeRestriction(ageRestriction));
        this.printBookTitle(books);

    }
    @Override
    public void findGOLDBooksLessThan5000Copies(){
        printBookTitle(this.goldBooksLessThan5000Copies());
    }
    @Override
    public void findByPrice5rHigherThan40(){
        List<Book> books = this.bookRepository.findByPrice5rHigherThan40();

        for (Book book : books) {
            System.out.printf("%s - $%.2f%n",book.getTitle(),book.getPrice());
        }

    }
    @Override
    public void findBookAreNoteReleased(int year){

        for (Book book : this.bookRepository.findBookAreNotReleased(year)) {

            System.out.println(book.getTitle());

        }

    }
    @Override
    public void findBooksByBeforeReleasedDate(String date){

        LocalDate localDate = extractDate(date);


        for (Book book:this.bookRepository.findBeforeDate(localDate)) {
            System.out.printf("%s %s %.2f%n",book.getTitle(),book.getEditionType(),book.getPrice());
        }



    }
    @Override
    public void findBooksByTitle(String contain){


     String lowerCase = contain.toLowerCase();
     StringBuilder firstLetterUpper = new StringBuilder(String.valueOf(lowerCase.charAt(0)).toUpperCase());
        for (int i = 1; i <lowerCase.length() ; i++) {
            firstLetterUpper.append(String.valueOf(lowerCase.charAt(i)));
        }
        for (Book book : this.bookRepository.findAll()) {
            if(book.getTitle().contains(lowerCase) || book.getTitle().contains(firstLetterUpper)){
                System.out.println(book.getTitle());
            }
        }
    }
    @Override
    public void findBooksWithAuthor(String letters){

        String lowerCase = letters.toLowerCase();
        StringBuilder firstLetterUpper = new StringBuilder(String.valueOf(lowerCase.charAt(0)).toUpperCase());
        for (int i = 1; i <lowerCase.length() ; i++) {
            firstLetterUpper.append(String.valueOf(lowerCase.charAt(i)));
        }
        for (Book book : this.bookRepository.findAll()) {

            Author author = book.getAuthor();

            if(author != null){

                if(author.getLastName().startsWith(firstLetterUpper.toString())){
                    System.out.printf("%s (%s %s)%n",book.getTitle(),book.getAuthor().getFirstName(),book.getAuthor().getLastName());
                }
            }
        }
    }
    @Override
    public void countBookByLengthTitle(int number){


        System.out.println(this.bookRepository.countBookByLengthTitle(number));
    }
    @Override
    public void findByTitle(String title){

        Book  book = this.bookRepository.findByTitle(title);

        System.out.printf("%s %s %.2f%n",book.getTitle(),book.getAgeRestriction(),book.getPrice());

    }

    @Override
    public void allBooksBeforeReleasedDate(String date, int copies){
        String[] info = date.split(" ");
        LocalDate localDate = LocalDate.of(Integer.parseInt(info[2]),this.convertMonthInNumber(info[1]),Integer.parseInt(info[0]));
        List<Book> books = this.bookRepository.findBeforeDate(localDate);
        this.bookRepository.increaseCopiesBeforeReleaseDate(copies,localDate);
        System.out.println(books.size() * copies);

    }


    @Override
    public void getSizeBooksFromAuthor(String firstName, String lastName){

        int size = this.bookRepository.getSizeOfBookFromAuthors(firstName,lastName);

        System.out.printf("Amanda Rice has written %d books%n",size);
    }

    private int convertMonthInNumber(String month){
        switch (month)
        {
            case "Jan":
                return 1;
            case "Feb":
                return 2;
            case "Mar":
                return 3;
            case "Apr":
                return 4;
            case "May":
                return 5;
            case "Jun":
                return 6;
            case "Jul":
                return 7;
            case "Aug":
                return 8;
            case "Sept":
                return 9;
            case "Oct":
                return 10;
            case "Nov":
                return 11;
            case "Dec":
                return 12;
            default:
                throw new IllegalArgumentException("No Month");
        }
    }
    private List<Book> goldBooksLessThan5000Copies(){
        List<Book> goldBooksList = this.bookRepository.findBooksByEditionType(this.extractEditionType("GOLD"));
        List<Book> outputBooks = new ArrayList<>();
        for (Book book :  goldBooksList ) {
            if(book.getCopies() < 5000){
                outputBooks.add(book);
            }
        }

        return outputBooks;
    }
    private EditionType extractEditionType(String type){

        switch (type.toUpperCase()){
            case "NORMAL":
                return EditionType.NORMAL;
            case "PROMO":
                return EditionType.PROMO;
            case "GOLD":
                return EditionType.GOLD;
            default:
                throw new IllegalArgumentException("EditionType NOT FOUND");
        }


    }
    private AgeRestriction extractAgeRestriction(String type){

        switch (type.toUpperCase()){
            case "MINOR":
                return AgeRestriction.MINOR;
            case "TEEN":
                return AgeRestriction.TEEN;
            case "ADULT":
                return AgeRestriction.ADULT;
            default:
                throw new IllegalArgumentException("AgeRestriction NOT FOUND");
        }


    }
    private void printBookTitle(List<Book> books){
        for (Book book : books) {
            System.out.println(book.getTitle());
        }
    }
    private LocalDate extractDate(String releasedDate){
        String[] data = releasedDate.split("-");
        return LocalDate.of(Integer.parseInt(data[2]),Integer.parseInt(data[1]),Integer.parseInt(data[0]));
    }

}
