package pac.service;

import java.util.List;

import pac.dto.UserDTO;
import pac.model.User;

public interface UserService {
	
	 void saveUser(UserDTO userDTO);

	    User findByEmail(String email);

	    List<UserDTO> findAllUsers();

}
