package net.manolispapadimitriou.cryptonewsbackend.repository;

import net.manolispapadimitriou.cryptonewsbackend.model.Portfolio;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PortfolioRepository extends CrudRepository<Portfolio,Integer> {
    public List<Portfolio> findByUserId(int user_id);
    public Portfolio findByUserIdAndCurrency(int user_id, String currency);
    public void deleteByUserIdAndCurrency(int user_id, String currency);

}
