package com.eun.common.security.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
public class User {

  private Long id;

  private String username;

  private String email;

  private String password;

  private Set<Role> roles = new HashSet<>();

}
