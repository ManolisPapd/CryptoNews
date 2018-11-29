package net.manolispapadimitriou.cryptonewsbackend.repositories;

import net.manolispapadimitriou.cryptonewsbackend.models.Portfolio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PortfolioRepository extends CrudRepository<Portfolio,Integer> {
    public List<Portfolio> findByUserId(int user_id);
    public Portfolio findByUserIdAndCurrency(int user_id, String currency);
    public void deleteByUserIdAndCurrency(int user_id, String currency);

}
