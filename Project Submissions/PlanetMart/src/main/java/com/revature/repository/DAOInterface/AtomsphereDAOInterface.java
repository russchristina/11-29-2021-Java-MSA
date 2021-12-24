package com.revature.repository.DAOInterface;

import com.revature.models.shop.AtmosphereComposition;
import com.revature.models.shop.Inventory;

import java.util.List;
import java.util.Map;

public interface AtomsphereDAOInterface {

    AtmosphereComposition getAtmosphereCompositionByPlanetId(int planetId);
}
