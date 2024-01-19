package com.medico.app.service;

import com.medico.app.repository.ApiAppelEventRepository;
import org.springframework.stereotype.Service;

@Service
public class ApiAppelEventService {
    private final ApiAppelEventRepository apiAppelEventRepository;

    public ApiAppelEventService(ApiAppelEventRepository apiAppelEventRepository) {
        this.apiAppelEventRepository = apiAppelEventRepository;
    }


}
