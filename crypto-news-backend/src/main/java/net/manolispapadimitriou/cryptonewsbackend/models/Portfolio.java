package net.manolispapadimitriou.cryptonewsbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Entity
@Table(name = "PORTFOLIO",
        uniqueConstraints = { @UniqueConstraint(columnNames = { "id" , "user_id"}) })
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String currency;

    @Column(name = "date_bought")
    private String dateBought;

    @Column(name = "price_bought")
    private String priceBought;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")

    private User user;

    public Portfolio() {
    }


    public Portfolio(String currency, String dateBought, String priceBought, User user) {
        this.currency = currency;
        this.dateBought = dateBought;
        this.priceBought = priceBought;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDateBought() {
        return dateBought;
    }

    public void setDateBought(String dateBought) {
        this.dateBought = dateBought;
    }

    public String getPriceBought() {
        return priceBought;
    }

    public void setPriceBought(String priceBought) {
        this.priceBought = priceBought;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
