package com.example.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Indexed;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Document(collection = "students")
@Entity(name="student")
public class UserFCD {

	@Id
	private String id;

	private String username;
	//@DBRef
	//private List<ProfileImage> imagePath = new ArrayList<>();
	//private ProfileImage imagePath;
	
	private String firstname;

	private String lastname;

	private String password;

	private String city;

	private String state;
	//@Indexed(unique = true)
	private String email;

	private String address;

	private String contact;
	private String gender;

//	@ElementCollection(fetch=FetchType.EAGER)
//     @CollectionTable(name="roles_tb1",joinColumns =@JoinColumn(referencedColumnName = "id"))
//	private Set<Role> roles;

	private String roles;

	// private String token;

	private String otp;

	// Api response field
	// @Transient
//	private String code;

	// private String message;
	@Transient
	private String description;



	// private String complainCode;

	// private String name;
//	@Transient
//	private boolean isEnabled = true;
//@Transient
//	public boolean isPresent() {
//		// TODO Auto-generated method stub
//		return false;
//	}

}