package net.manolispapadimitriou.cryptonewsbackend.services;

import net.manolispapadimitriou.cryptonewsbackend.models.Portfolio;
import net.manolispapadimitriou.cryptonewsbackend.models.User;
import net.manolispapadimitriou.cryptonewsbackend.repositories.UserRepository;
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

        Optional<User> userOptional = userRepository.findById(viewModel.getUser_id());

        if( userOptional.isPresent()) {
            User user1 = null;
            user1 = userOptional.get();
            var entity = new Portfolio(viewModel.getCurrency(),viewModel.getDateBought(), viewModel.getPriceBought(),user1);

            return entity;
        }

        return null;


    }
}
