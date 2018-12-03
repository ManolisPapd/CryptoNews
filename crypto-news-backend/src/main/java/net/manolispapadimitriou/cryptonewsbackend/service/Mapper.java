package net.manolispapadimitriou.cryptonewsbackend.service;

import net.manolispapadimitriou.cryptonewsbackend.model.Portfolio;
import net.manolispapadimitriou.cryptonewsbackend.model.User;
import net.manolispapadimitriou.cryptonewsbackend.repository.UserRepository;
import net.manolispapadimitriou.cryptonewsbackend.viewmodel.PortfolioViewModel;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Mapper {

    private UserRepository userRepository;

    public Mapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Portfolio convertToPortfolioEntity(PortfolioViewModel viewModel){

        User user = userRepository.findByUsername(viewModel.getUsername());

        if(user!=null) {

            var entity = new Portfolio(viewModel.getCurrency(),viewModel.getDateBought(), viewModel.getPriceBought(),user);

            return entity;
        }

        return null;


    }
}
