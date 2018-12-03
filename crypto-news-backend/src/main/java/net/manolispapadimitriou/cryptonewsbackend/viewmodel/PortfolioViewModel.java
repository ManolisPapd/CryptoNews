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
    private String username;

    public PortfolioViewModel() {
    }

    public PortfolioViewModel(@NotNull String currency, @NotNull String dateBought, @NotNull String priceBought, @NotNull String username) {
        this.currency = currency;
        this.dateBought = dateBought;
        this.priceBought = priceBought;
        this.username = username;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
