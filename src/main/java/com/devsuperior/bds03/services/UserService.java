package com.devsuperior.bds03.services;

import com.devsuperior.bds03.dto.RoleDTO;
import com.devsuperior.bds03.dto.UserDTO;
import com.devsuperior.bds03.dto.UserInsertDTO;
import com.devsuperior.bds03.entities.Role;
import com.devsuperior.bds03.entities.User;
import com.devsuperior.bds03.repositories.RoleRepository;
import com.devsuperior.bds03.repositories.UserRepository;
import com.devsuperior.bds03.services.exceptions.ResourcesNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Transactional(readOnly = true)
	public Page<UserDTO> findAll(Pageable pageable) {
		Page<User> page = repository.findAll(pageable);
		return page.map(p -> new UserDTO(p));
	}

	@Transactional
	public UserDTO insert(UserInsertDTO dto) {
		User entity = new User();
		entity.setEmail(dto.getEmail());
		entity.setPassword(passwordEncoder.encode(dto.getPassword()));

		entity.getRoles().clear();
		for (RoleDTO r : dto.getRoles()) {
				try {
					Role role = roleRepository.getOne(r.getId());
					entity.getRoles().add(role);
				}
				catch (EntityNotFoundException e) {
					throw new ResourcesNotFoundException("Role id: " + r.getId() + "not found");
				}
		}

		entity = repository.save(entity);
		return new UserDTO(entity);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			User user = repository.findByEmail(username);
			return user;
		}
		catch (UsernameNotFoundException e){
			throw new UsernameNotFoundException("User não encontrado: " + username);
		}
	}
}
