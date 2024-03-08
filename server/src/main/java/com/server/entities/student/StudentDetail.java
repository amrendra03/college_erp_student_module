package com.server.entities.student;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

@Data
@Entity
public class StudentDetail implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    @NotBlank(message = "Name must not be blank")
    private String name;

    private String score;

    @Column(unique = true)
    @NotBlank(message = "Email must not be blank")
    @Email(message = "Invalid email format")
    private String email;

    private String password;

    @NotBlank(message = "Phone must not be blank")
    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
    private String phone;

    @NotNull(message = "Date of Birth must not be null")
    @Past(message = "Date of Birth must be in the past")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date DOB;

    @NotBlank(message = "ROLE of User must define")
    private String role;

    @Column(unique = true)
    private String rollNo;

    @Column(unique = true)
    private String enrollmentNo;

    private Date registrationDate;


    @OneToMany(mappedBy = "studentDetail", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentCourseRegistration> studentCourseRegistrations;

    @OneToMany(mappedBy = "studentDetail", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentStatus> statuses;

    // Constructor to generate rollNo and enrollmentNo
    public StudentDetail() {
        this.registrationDate = new Date();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.email;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
