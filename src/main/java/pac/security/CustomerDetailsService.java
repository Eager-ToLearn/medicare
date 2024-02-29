package pac.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pac.model.Role;
import pac.model.User;
import pac.repository.UserRepo;

@Service
public class CustomerDetailsService implements UserDetailsService{
	
	 private UserRepo userRepo;

	    public CustomerDetailsService(UserRepo userRepo) {
	        this.userRepo = userRepo;
	    }

	    @Override
	    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	        User user = userRepo.findByEmail(email);

	        if (user != null) {
	            return new org.springframework.security.core.userdetails.User(user.getEmail(),
	                    user.getPassword(),
	                    mapRolesToAuthorities(user.getRoles()));
	        }else{
	            throw new UsernameNotFoundException("Invalid username or password.");
	        }
	    }

	    private Collection < ? extends GrantedAuthority> mapRolesToAuthorities(Collection <Role> roles) {
	        Collection < ? extends GrantedAuthority> mapRoles = roles.stream()
	                .map(role -> new SimpleGrantedAuthority(role.getName()))
	                .collect(Collectors.toList());
	        return mapRoles;
	    }
	
	

}
