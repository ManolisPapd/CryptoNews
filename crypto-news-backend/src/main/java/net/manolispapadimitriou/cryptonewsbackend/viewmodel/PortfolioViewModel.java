package net.manolispapadimitriou.cryptonewsbackend.viewmodel;

import javax.validation.constraints.NotNull;

public class PortfolioViewModel {
    private int id;

    @NotNull
    private String currency;

    @NotNull
    private String dateBought;

    @NotNull
    private String priceBought;

    @NotNull
    private int user_id;

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

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
