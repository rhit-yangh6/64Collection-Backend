package com.example.backend_64collection.cars.service;

import com.example.backend_64collection.cars.dto.GeneralInfoDto;
import com.example.backend_64collection.cars.dto.StatsInfoDto;

public interface IGeneralService {

    GeneralInfoDto getGeneralInfo();

    StatsInfoDto getStatistics();
}
