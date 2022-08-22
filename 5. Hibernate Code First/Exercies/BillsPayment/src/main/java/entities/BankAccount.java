package entities;

import javax.persistence.*;

@Entity
@Table(name = "bank_accounts")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "bank_name")
    private String bankName;
    @Column(name = "SWIFT_code")
    private String SWIFTCode;
    @OneToOne(mappedBy = "bankAccount")
    private User user;


    public BankAccount() {}

    public BankAccount(String bankName, String SWIFTCode) {
        this.bankName = bankName;
        this.SWIFTCode = SWIFTCode;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    public void setSWIFTCode(String SWIFTCode) {
        this.SWIFTCode = SWIFTCode;
    }

    public int getId() {
        return id;
    }
    public String getBankName() {
        return bankName;
    }
    public String getSWIFTCode() {
        return SWIFTCode;
    }
}

