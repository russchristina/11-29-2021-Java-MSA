package com.revature.repository.DAOInterface;

import com.revature.models.accounts.CustomerAccount;
import com.revature.models.shop.Inventory;
import com.revature.models.shop.Life;
import com.revature.models.users.User;
import com.revature.service.exceptions.EmptyInputException;

import java.sql.SQLException;
import java.util.List;

public interface LifeDAOInterface {

    List<Life> getAllLife();

    Life getLifeById(int id);

    void updateLifeName(int lifeId, String name) throws EmptyInputException;
    void updateLifePopulation(int lifeId, int population);
    void updateLifeTechnologyLevel(int lifeId, int technologyLevel);

    void deleteLifeById(int id);
    void addLife(String name, int population, int technologyLevel, int planetId) throws EmptyInputException, SQLException;
}
