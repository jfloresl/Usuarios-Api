package com.jfloresl.usuarios.service;

import java.time.LocalDate;
import java.util.*;

import com.jfloresl.usuarios.entities.Phone;
import com.jfloresl.usuarios.entities.dto.LoginDto;
import com.jfloresl.usuarios.entities.dto.RegisterDto;
import com.jfloresl.usuarios.entities.dto.RespDto;
import com.jfloresl.usuarios.entities.dto.UserDto;
import com.jfloresl.usuarios.repository.PhoneRepository;
import com.jfloresl.usuarios.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.function.ServerRequest.Headers;

import com.jfloresl.usuarios.entities.User;
import com.jfloresl.usuarios.handler.ResponseHandler;
import com.jfloresl.usuarios.repository.UserRepository;
import com.jfloresl.usuarios.utils.Constantes;
import com.jfloresl.usuarios.utils.UserUtils;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	private PhoneRepository phoneRepository;
	private final JwtUtil jwtUtil;

	public UserService(UserRepository userRepository, PhoneRepository phoneRepository, JwtUtil jwtUtil) {
		this.userRepository = userRepository;
		this.phoneRepository = phoneRepository;
		this.jwtUtil = jwtUtil;
	}

	/**
	 * @param user
	 * @param headers
	 * @return
	 */
	public Boolean userValido(User user,Headers headers) {
		if(null!=user || null!=headers) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * @param email
	 * @return
	 */
	public Boolean existEmail(String email) {
		return userRepository.findByEmail(email) == null;
	}

	public String parametersRegister(RegisterDto user) {
		if(UserUtils.isNullOrEmpty(user.getName())){
			return Constantes.userInvalid;
		}
		if(UserUtils.isNullOrEmpty(user.getPhones())) {
			return Constantes.phoneInvalid;
		}
		if(!emailFormat(user.getEmail())) {
			return Constantes.emailInvalid;
		}
		if(!existEmail(user.getEmail())) {
			return Constantes.emailExistente;
		}
		//System.out.println(user.getPassword());
		if(!checkPasswordFormat(user.getPassword())) {
			System.out.println("password invalida");
			return Constantes.passwordInvalid;
		}
		return "0";
	}


	/**
	 * @param password
	 * @return
	 */
	public boolean checkPasswordFormat(String password) {
		String regex = Constantes.passwordFormat;
        return password.matches(regex);
	}

	/**
	 * @param email
	 * @return
	 */
	public boolean emailFormat(String email) {
		String regex = Constantes.emailFormat;
        return email.matches(regex);
	}



	/**
	 * @return
	 */

	public ResponseEntity<Object> findAll(Map<String, String> id) {
		if(UserUtils.isNullOrEmpty(id.get("id")) || !id.get("id").equals("all")) {
	    	return ResponseHandler.generateResponse(Constantes.idInvalid, HttpStatus.BAD_REQUEST);
		}
		List<User> lista=userRepository.findAll();

		if(lista.isEmpty()) {
	    	return ResponseHandler.generateResponse(Constantes.userEmpty, HttpStatus.ACCEPTED);
		}
		List<UserDto> listaUserDtos=new ArrayList<>();
		for(User u:lista) {
			UserDto userDto = new UserDto();
			userDto.setId(u.getId());
			userDto.setName(u.getName());
			userDto.setEmail(u.getEmail());
			userDto.setPassword(u.getPassword());
			userDto.setCreated(u.getCreated());
			userDto.setModified(u.getModified());
			userDto.setLast_login(u.getLast_login());
			userDto.setToken(u.getToken());
			userDto.setIsActive(u.getIsActive());
			List<Phone> phones = this.phoneRepository.findByUserId(u.getId());
			userDto.setPhones(phones);
			System.out.println("telefonos de usuario: "+u.getId()+" phones: "+phones);
			u.setPassword("*********");
			u.setToken("******************");
			listaUserDtos.add(userDto);
		}

    	return ResponseHandler.generateResponse(listaUserDtos, HttpStatus.OK);
	}


	public ResponseEntity<Object> registerUser(RegisterDto registerDto) {
		String validUser=parametersRegister(registerDto);
		if (!validUser.equals("0")) {
			return ResponseHandler.generateResponse(validUser, HttpStatus.BAD_REQUEST);
		}
		User user = new User();
		user.setName(registerDto.getName());
		user.setEmail(registerDto.getEmail());
		user.setPassword(registerDto.getPassword());
		//user.setPassword(PasswordHasher.hashPassword(user.getPassword()));
		user.setCreated(LocalDate.now());
		user.setModified(LocalDate.now());
		user.setLast_login(LocalDate.now());
		user.setIsActive("1");
		String jwt =this.jwtUtil.create(registerDto.getEmail());
		user.setToken(jwt);

		try{
			User user1=userRepository.save(user);
			RespDto respDto=new RespDto();
			respDto.setId(user1.getId());
			respDto.setEmail(user1.getEmail());
			respDto.setPassword(user1.getPassword());
			respDto.setCreated(user1.getCreated());
			respDto.setModified(user1.getModified());
			respDto.setLast_login(user1.getLast_login());
			respDto.setToken(user1.getToken());
			respDto.setIsActive(user1.getIsActive());
			System.out.println("registerDto.getPhones(): "+registerDto.getPhones());
			try{
				for (Phone p:registerDto.getPhones()) {
					System.out.println("fono en el register: "+p);
					p.setUserId(user1.getId());
					this.phoneRepository.save(p);
				}
			}catch (Exception e){
				System.out.println("error al guardar un telefono");
			}

			return ResponseHandler.generateResponse(respDto, HttpStatus.OK);
		}catch (Exception e){
			return ResponseHandler.generateResponse(Constantes.userSaveError, HttpStatus.BAD_REQUEST);
		}

	}

	public ResponseEntity<Object> login(LoginDto loginDto) {
		String validUser=parametersLogin(loginDto);
		if (!validUser.equals("0")) {
			return ResponseHandler.generateResponse(validUser, HttpStatus.BAD_REQUEST);
		}
		Optional<User> user = Optional.ofNullable(userRepository.findByEmail(loginDto.getEmail()));
		if(user.isPresent() && user.get().getPassword().equals(loginDto.getPassword())) {
		///verificar token valido sino generar nuevo
			if(jwtUtil.isValid(user.get().getToken())) {
				return ResponseHandler.generateResponse(user.get().getToken(), HttpStatus.OK);
			}else{
				return ResponseHandler.generateResponse(jwtUtil.create(user.get().getEmail()), HttpStatus.OK);
			}
		}
		return ResponseHandler.generateResponse(Constantes.userNotFound, HttpStatus.NOT_FOUND);
	}

	public String parametersLogin(LoginDto loginDto) {
		if(!UserUtils.isNullOrEmpty(loginDto.getEmail()) ||!UserUtils.isNullOrEmpty(loginDto.getPassword())){
			if(!emailFormat(loginDto.getEmail())) {
				return Constantes.emailInvalid;
			}
		}else {
			return Constantes.credentialsInvalid;
		}
		return "0";
	}
}
