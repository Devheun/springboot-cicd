package me.leesiheun.springbootdeveloper2.service;

import lombok.RequiredArgsConstructor;
import me.leesiheun.springbootdeveloper2.config.jwt.TokenProvider;
import me.leesiheun.springbootdeveloper2.domain.User;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class TokenService {
    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;

    public String createNewAccessToken(String refreshToken) {
        if (!tokenProvider.validToken(refreshToken)) {
           throw new IllegalArgumentException("Invalid refresh token");
        }

        Long userId = refreshTokenService.findByRefreshToken(refreshToken).getUserId();
        User user = userService.findById(userId);

        return tokenProvider.generateToken(user, Duration.ofHours(2));
    }
}
