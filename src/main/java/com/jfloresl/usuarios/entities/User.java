package com.jfloresl.usuarios.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.*;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.lang.NonNull;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Type(type="org.hibernate.type.UUIDCharType")
	@Column(name = "user_id", nullable = false, unique = true)
	private UUID id;
	@NonNull
	private String name;
	@NonNull
	private String email;
	@NonNull
	private String password;
	private LocalDate created;
	private LocalDate modified;
	private LocalDate last_login;
	private String token;
	private String isactive;
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Phone> phones = new ArrayList<>();

}
