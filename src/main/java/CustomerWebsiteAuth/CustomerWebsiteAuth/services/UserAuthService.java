package CustomerWebsiteAuth.CustomerWebsiteAuth.services;
import CustomerWebsiteAuth.CustomerWebsiteAuth.models.UserPrincipal;
import CustomerWebsiteAuth.CustomerWebsiteAuth.repositories.UserPrincipalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.security.RolesAllowed;
import java.util.List;
public class UserAuthService implements UserDetailsService {

    @Autowired
    UserPrincipalRepo userPrincipalRepo;

    @Override
    public UserPrincipal loadUserByUsername(String username) throws UsernameNotFoundException {
        return userPrincipalRepo.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User not found with username or email : " + username)
        );
    }
}
