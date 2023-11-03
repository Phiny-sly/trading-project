package com.amalitech.tradingproject.service;

import com.amalitech.tradingproject.dto.AuthDto;
import com.amalitech.tradingproject.payload.AuthPayload;

public interface AuthService {
    AuthDto login(AuthPayload authPayload);
}
