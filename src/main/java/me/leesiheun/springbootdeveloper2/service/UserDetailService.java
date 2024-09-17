package me.leesiheun.springbootdeveloper2.service;

import lombok.RequiredArgsConstructor;
import me.leesiheun.springbootdeveloper2.domain.User;
import me.leesiheun.springbootdeveloper2.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
// 스프링 시큐리티에서 사용자 정보를 가져오는 인터페이스
public class UserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    // 사용자 이름 (email)으로 사용자의 정보를 가져오는 메서드
    // 필수로 구현해야하는 loadUserByUsername() 메서드를 오버라이딩해서 사용자 정보를 가져오는 로직 작성
    @Override
    public User loadUserByUsername(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException(email));
    }
}
