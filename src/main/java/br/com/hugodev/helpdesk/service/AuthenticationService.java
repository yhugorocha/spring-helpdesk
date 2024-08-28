package br.com.hugodev.helpdesk.service;

import br.com.hugodev.helpdesk.Util.JwtUtil;
import br.com.hugodev.helpdesk.dto.LoginRequestDto;
import br.com.hugodev.helpdesk.dto.LoginResponseDto;
import br.com.hugodev.helpdesk.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationService implements UserDetailsService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;

    public LoginResponseDto login(LoginRequestDto login){
        String username = login.username();
        String password = login.password();

        Authentication authentication = this.authentication(username, password);
        String token = jwtUtil.createToken(authentication);

        return new LoginResponseDto(username, "User loged sucessfuly !", token, true);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found with username: "+ username));
    }

    private Authentication authentication(String username, String password) {
        UserDetails userDetails = this.loadUserByUsername(username);

        if (userDetails == null){
            throw new BadCredentialsException("Invalid username or password");
        }

        if(!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException("Invalid password");
        }

        return new UsernamePasswordAuthenticationToken(username, userDetails.getPassword(), userDetails.getAuthorities());
    }
}
