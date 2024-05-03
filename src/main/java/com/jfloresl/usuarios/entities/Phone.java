package com.jfloresl.usuarios.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.lang.NonNull;

import java.util.UUID;

/**
 * @author jfloresl
 *
 */
@Hidden
@Entity
@Table(name="phones")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Phone {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "phone_id",nullable = false, unique = true)
    private Long id;
	@NonNull
	private String number;
	@NonNull
	private String citycode;
	@NonNull
	private String countrycode;
	@Type(type="org.hibernate.type.UUIDCharType")
	private UUID userId;
}
