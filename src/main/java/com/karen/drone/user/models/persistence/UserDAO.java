package com.karen.drone.user.models.persistence;

import com.karen.drone.comment.model.persistence.CommentDAO;
import com.karen.drone.submission.model.persistence.SubmissionDAO;
import com.karen.drone.user.models.components.UserRole;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author Daniel Incicau, daniel.incicau@busymachines.com
 * @since 2019-05-17
 */
@Entity
@Table(name = "users")
public class UserDAO {

    @Id
    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRole role;

    @Column(name = "created_at")
    private Date createdAt;

    @OneToMany(mappedBy = "postedBy", fetch=FetchType.LAZY, cascade = CascadeType.DETACH)
    @OrderBy("posted_at desc")
    private List<CommentDAO> comments;

    @OneToMany(mappedBy = "submittedBy", fetch=FetchType.LAZY, cascade = CascadeType.DETACH)
    @OrderBy("submitted_at desc")
    private List<SubmissionDAO> submissions;

    public UserDAO() {}

    public UserDAO(UUID userId, String email, String password, String name, UserRole role, Date createdAt, List<CommentDAO> comments, List<SubmissionDAO> submissions) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
        this.createdAt = createdAt;
        this.comments = comments;
        this.submissions = submissions;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<CommentDAO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDAO> comments) {
        this.comments = comments;
    }

    public List<SubmissionDAO> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(List<SubmissionDAO> submissions) {
        this.submissions = submissions;
    }
}
