package app.SpringBoot;

import app.SpringBoot.model.Role;
import app.SpringBoot.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.HashSet;

@Repository
@Transactional
public class Start {

    private final PasswordEncoder passwordEncoder;

    @PersistenceContext
    private EntityManager entityManager;

    public Start(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    public void save() {
        Role roleAdmin = new Role();
        roleAdmin.setName("ROLE_ADMIN");
        Role roleUser = new Role();
        roleUser.setName("ROLE_USER");
        entityManager.persist(roleUser);
        entityManager.persist(roleAdmin);


        User user = new User();
        user.setPassword(passwordEncoder.encode("admin"));
        user.setEmail("admin");
        user.setEnabled(true);
        user.setFirstName("Александр");
        user.setLastName("Петров");
        HashSet<Role> role = new HashSet<>();
        role.add(roleAdmin);
        role.add(roleUser);

        user.setRoles(role);
        entityManager.persist(user);

        User user2 = new User();
        user2.setPassword(passwordEncoder.encode("user"));
        user2.setEmail("user");
        user2.setEnabled(true);
        user2.setFirstName("Игорь");
        user2.setLastName("Андреев");
        HashSet<Role> role2 = new HashSet<>();
        role2.add(roleUser);

        user2.setRoles(role2);
        entityManager.persist(user2);

    }

}
