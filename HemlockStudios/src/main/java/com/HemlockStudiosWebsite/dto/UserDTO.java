package com.HemlockStudiosWebsite.dto;


    public class UserDTO {
       
            private Integer id;
            private String email;
            private String username;
            private boolean accountNonExpired;
            private boolean accountNonLocked;
            private boolean credentialsNonExpired;
        
            // Constructor
            public UserDTO(Integer id, String email, String username, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired) {
                this.id = id;
                this.email = email;
                this.username = username;
                this.accountNonExpired = accountNonExpired;
                this.accountNonLocked = accountNonLocked;
                this.credentialsNonExpired = credentialsNonExpired;
            }
        
            public boolean isAccountNonExpired() {
                return accountNonExpired;
            }
        
            public void setAccountNonExpired(boolean accountNonExpired) {
                this.accountNonExpired = accountNonExpired;
            }
        
            public boolean isAccountNonLocked() {
                return accountNonLocked;
            }
        
            public void setAccountNonLocked(boolean accountNonLocked) {
                this.accountNonLocked = accountNonLocked;
            }
        
            public boolean isCredentialsNonExpired() {
                return credentialsNonExpired;
            }
        
            public void setCredentialsNonExpired(boolean credentialsNonExpired) {
                this.credentialsNonExpired = credentialsNonExpired;
            }

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }
        }
    

