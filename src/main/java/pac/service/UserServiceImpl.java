package pac.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;

import pac.dto.UserDTO;
import pac.model.Role;
import pac.model.User;
import pac.repository.RoleRepo;
import pac.repository.UserRepo;

public class UserServiceImpl implements UserService{
	
	private UserRepo userRepo;
	private RoleRepo roleRepo;
	private PasswordEncoder passwordEncoder; 
	
	
	public UserServiceImpl(UserRepo userRepo, RoleRepo roleRepo, PasswordEncoder passwordEncoder)
	{
		this.userRepo = userRepo;
		this.roleRepo = roleRepo;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void saveUser(UserDTO userDTO) {
		// TODO Auto-generated method stub
		
		User user = new User();
        user.setName(userDTO.getFirstName() + " " + userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
		
	}

	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		
		
		 return userRepo.findByEmail(email);
	}

	@Override
	public List<UserDTO> findAllUsers() {
		
		List<User> users = userRepo.findAll();
		
		return users.stream().map((user) -> convertEntityToDTO(user)).collect(Collectors.toList());
//		 List<User> users = userRepo.findAll();
//	        return users.stream().map((user) -> convertEntityToDto(user))
//	                .collect(Collectors.toList());

	}
	
	private UserDTO convertEntityToDTO(User user) {
		
		UserDTO userDTO = new UserDTO();
		String[] name = user.getName().split(" ");
		userDTO.setFirstName(name[0]);
		userDTO.setLastName(name[1]);
		userDTO.setEmail(user.getEmail());
		
		return userDTO;
	}
	
	private Role checkRoleExist()
	{
		Role role = new Role();
		role.setName("ROLE_ADMIN");
		
		return roleRepo.save(role);
	}
	

}
