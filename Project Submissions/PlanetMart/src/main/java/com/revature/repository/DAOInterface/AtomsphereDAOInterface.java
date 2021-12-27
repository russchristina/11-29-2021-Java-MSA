package com.revature.repository.DAOInterface;

import com.revature.models.shop.AtmosphereComposition;
import com.revature.repository.Exception.NoPlanetFoundException;

import java.sql.SQLException;

public interface AtomsphereDAOInterface {

    AtmosphereComposition getAtmosphereCompositionByPlanetId(int planetId) throws SQLException, NoPlanetFoundException;
}
