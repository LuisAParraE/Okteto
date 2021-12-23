package com.example.styleoverflow.styleoverflow.appuser;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.Collections;

public class test {
// not in use
    public test(AppUserRepository appUserRepository, String firstName) {
        this.appUserRepository = appUserRepository;
        this.firstName = firstName;
        boolean isValid = false;
        if (isValid){
            throw new IllegalStateException("email not valid");
        }
    }




    public Collection<? extends GrantedAuthority> getAuthority(){
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority("Role");
        return Collections.singletonList(authority);
    }

    private final AppUserRepository appUserRepository;

    private final String firstName;

    public UserDetails loadUserByUsername(String email)
        throws UsernameNotFoundException{
        return appUserRepository.findByUsername(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        ""
                ));
    }

}
