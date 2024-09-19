package com.fontys.fitquest.business;

import fontys.sem3.school.domain.CreateCountryRequest;
import fontys.sem3.school.domain.CreateCountryResponse;

public interface CreateUserUseCase {
    CreateCountryResponse createCountry(CreateCountryRequest request);
}
