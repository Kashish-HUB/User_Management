package com.example.SpringProject;

import com.example.SpringProject.Model.User;
import com.example.SpringProject.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

@Autowired
    private UserRepository userRepository;
@Autowired
private TestEntityManager entityManager;
private User user1;
private User user2;
private User user3;
@BeforeEach
    void setUp() {
    userRepository.deleteAllInBatch();
    user1 = new User("Alice Wonderland", "alice@example.com");
    user2 = new User("Bob The Builder", "bob@example.com");
    user3 = new User("Kashish","kashish@gmail.com");
    entityManager.persist(user1);
    entityManager.persist(user2);
    entityManager.flush();
    entityManager.clear();
}
@Test
void testFindByEmailFound(){
    Optional<User> foundUser = userRepository.findByEmail(user1.getEmail());
    assertThat(foundUser).isPresent();
    assertThat(foundUser.get().getName()).isEqualTo(user1.getName());
    assertThat(foundUser.get().getEmail()).isEqualTo(user1.getEmail());
    assertThat(foundUser.get().getId()).isEqualTo(user1.getId());
}
@Test
void testFindByEmailNotFound(){
    Optional<User> notFoundUser = userRepository.findByEmail("nonexsistent@mail.com");
    assertThat(notFoundUser).isNotPresent();
}
@Test
void testSaveUser(){
    User newUser = new User("Charlie Chaplin","charlie@example.com");
    User savedUser = userRepository.save(newUser);
    assertThat(savedUser).isNotNull();
    assertThat(savedUser.getName()).isEqualTo("Charlie Chaplin");
    assertThat(savedUser.getEmail()).isEqualTo("charlie@example.com");
}
@Test
    void testUpdateUser(){
    String newName = "Manoj Reddy";
    String newEmail = "manojreddy@gmail.com";
    user3.setName(newName);
    user3.setEmail(newEmail);
    User updatedUser = userRepository.save(user3);
    Optional<User> foundUser = userRepository.findById(updatedUser.getId());
    assertThat(foundUser).isPresent();
    assertThat(foundUser.get().getName()).isEqualTo(newName);
    assertThat(foundUser.get().getEmail()).isEqualTo(newEmail);
    assertThat(foundUser.get().getId()).isEqualTo(updatedUser.getId());
}
@Test
    void testDeleteUser() {
        User savedUser = userRepository.save(user3);
        userRepository.deleteById(savedUser.getId());
        Optional<User> deletedUser = userRepository.findById(savedUser.getId());
        assertThat(deletedUser).isNotPresent();
    }
}
