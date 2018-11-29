package net.manolispapadimitriou.cryptonewsbackend.controllers;

import net.manolispapadimitriou.cryptonewsbackend.models.Portfolio;
import net.manolispapadimitriou.cryptonewsbackend.models.User;
import net.manolispapadimitriou.cryptonewsbackend.repositories.PortfolioRepository;
import net.manolispapadimitriou.cryptonewsbackend.repositories.UserRepository;
import net.manolispapadimitriou.cryptonewsbackend.services.Mapper;
import net.manolispapadimitriou.cryptonewsbackend.viewmodel.PortfolioViewModel;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.sound.sampled.Port;
import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/portfolio")
@CrossOrigin
public class PortfolioController {

    private PortfolioRepository portfolioRepository;
    private UserRepository userRepository;
    public Mapper mapper;

    public PortfolioController(PortfolioRepository portfolioRepository, UserRepository userRepository, Mapper mapper) {
        this.portfolioRepository = portfolioRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @GetMapping("/all")
    public List<Portfolio> all(){
        List<Portfolio> portfolioList = new ArrayList<>();

        this.portfolioRepository.findAll().forEach(portfolioList::add);

        return portfolioList;
    }

    //Create new currency by user
    @RequestMapping(value = "/currency", method = RequestMethod.POST)
    public Portfolio save(@RequestBody PortfolioViewModel currency, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            throw new ValidationException();
        }


        var portfolioEntity = mapper.convertToPortfolioEntity(currency);
        portfolioRepository.save(portfolioEntity);

        return portfolioEntity;
    }


    //Return all currencies by user
    @GetMapping("/currencies/{id}")
    public List<Portfolio> byId(@PathVariable String id){

        return portfolioRepository.findByUserId(Integer.parseInt(id));
    }


    //Delete currency
    @DeleteMapping("/delete/{id}/{currency}")
    @Transactional //important
    public void delete(@PathVariable("id") String id, @PathVariable("currency") String currency){
        portfolioRepository.deleteByUserIdAndCurrency(Integer.parseInt(id),currency);

    }

    //Currency update by price brought
    @RequestMapping(value = "/currency/price/{id}/{currency}/{priceBought}", method = RequestMethod.POST)
    public Portfolio updatePrice(@PathVariable("id") String id, @PathVariable("currency") String currency, @PathVariable("priceBought") String priceBought) {


        //Obtain the currency object
        var currObject = portfolioRepository.findByUserIdAndCurrency(Integer.parseInt(id), currency);

        //Update the new price_bought
        currObject.setPriceBought(priceBought);
        portfolioRepository.save(currObject);

        return currObject;
    }


    //Currency update by date brought
    @RequestMapping(value = "/currency/date/{id}/{currency}/{dateBought}", method = RequestMethod.POST)
    public Portfolio updateDate(@PathVariable("id") String id, @PathVariable("currency") String currency,
                                @PathVariable("dateBought") String dateBought) {


        //Obtain the currency object
        var currObject = portfolioRepository.findByUserIdAndCurrency(Integer.parseInt(id), currency);

        //Update the new price_bought
        currObject.setDateBought(dateBought);
        portfolioRepository.save(currObject);

        return currObject;
    }


}
