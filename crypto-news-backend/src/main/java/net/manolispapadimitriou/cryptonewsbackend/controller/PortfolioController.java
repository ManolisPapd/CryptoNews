package net.manolispapadimitriou.cryptonewsbackend.controller;

import net.manolispapadimitriou.cryptonewsbackend.model.CustomResponse;
import net.manolispapadimitriou.cryptonewsbackend.model.Portfolio;
import net.manolispapadimitriou.cryptonewsbackend.model.User;
import net.manolispapadimitriou.cryptonewsbackend.repository.PortfolioRepository;
import net.manolispapadimitriou.cryptonewsbackend.repository.UserRepository;
import net.manolispapadimitriou.cryptonewsbackend.service.Mapper;
import net.manolispapadimitriou.cryptonewsbackend.viewmodel.PortfolioViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/portfolio")
@CrossOrigin(origins = "http://localhost:4200")
public class PortfolioController {

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private UserRepository userRepository;

    public Mapper mapper;

    public PortfolioController(Mapper mapper) {
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
    public CustomResponse save(@RequestBody PortfolioViewModel currency){

        var portfolioEntity = mapper.convertToPortfolioEntity(currency);
        portfolioRepository.save(portfolioEntity);

        CustomResponse customResponse = new CustomResponse(200,"currency added to the database");

        return customResponse;
    }


    //Return all currencies by user
    @GetMapping("/currencies/{username}")
    public List<Portfolio> byId(@PathVariable String username){

        User user = userRepository.findByUsername(username);
        return portfolioRepository.findByUserId(user.getId());
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
