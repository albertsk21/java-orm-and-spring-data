package entities;


import javax.persistence.*;

@Entity
@Table(name = "credit_card")
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "card_type")
    private String cardType;
    @Column(name = "expiration_month")
    private int expirationMonth;
    @Column(name = "expiration_year")
    private int expirationYear;
    @OneToOne(mappedBy = "creditCard")
    private User userCreditCard;

    public User getUserCreditCard() {
        return userCreditCard;
    }


    public CreditCard() {}

    public CreditCard(String cardType, int expirationMonth, int expirationYear) {
        this.cardType = cardType;
        this.expirationMonth = expirationMonth;
        this.expirationYear = expirationYear;
    }



    public void setId(int id) {
        this.id = id;
    }
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }
    public void setExpirationMonth(int expirationMonth) {
        this.expirationMonth = expirationMonth;
    }
    public void setExpirationYear(int expirationYear) {
        this.expirationYear = expirationYear;
    }


    public int getId() {
        return id;
    }
    public String getCardType() {
        return cardType;
    }
    public int getExpirationMonth() {
        return expirationMonth;
    }
    public int getExpirationYear() {
        return expirationYear;
    }
}
