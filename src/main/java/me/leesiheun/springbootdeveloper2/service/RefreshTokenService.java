package me.leesiheun.springbootdeveloper2.service;

import lombok.RequiredArgsConstructor;
import me.leesiheun.springbootdeveloper2.domain.RefreshToken;
import me.leesiheun.springbootdeveloper2.repository.RefreshTokenRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken findByRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected token"));
    }
}
