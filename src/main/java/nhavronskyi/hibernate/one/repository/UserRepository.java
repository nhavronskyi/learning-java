package nhavronskyi.hibernate.one.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nhavronskyi.hibernate.one.model.User;
import nhavronskyi.hibernate.one.model.UserStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static nhavronskyi.hibernate.one.utils.MapUtils.getValue;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UserRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    public List<User> selectAllUsers() {
        return entityManager.createQuery(getCriteriaQuery())
            .getResultList();
    }

    @Transactional
    public Map<User, UserStatus> addUser(User user) {
        try {
            entityManager.persist(user);
            return Map.of(user, UserStatus.CREATED);
        } catch (Exception e) {
            log.error("Error adding user", e);
            return Map.of(user, UserStatus.ERROR);
        }
    }

    @Transactional
    public Map<User, UserStatus> deleteUser(Long id) {
        Map<User, UserStatus> userStatusMap = getUserStatusMap(id);
        if (getValue(userStatusMap.values()).equals(UserStatus.NO_SUCH_USER)) {
            return userStatusMap;
        }

        User user = getValue(userStatusMap.keySet());
        try {
            entityManager.remove(user);
            return Map.of(user, UserStatus.DELETED);
        } catch (Exception e) {
            log.error("error while updating user", e);
            return Map.of(user, UserStatus.ERROR);
        }
    }

    @Transactional
    public Map<User, UserStatus> updateUser(User user) {
        Map<User, UserStatus> userStatusMap = getUserStatusMap(user.getId());
        if (getValue(userStatusMap.values()).equals(UserStatus.NO_SUCH_USER)) {
            return userStatusMap;
        }

        try {
            entityManager.merge(user);
            return Map.of(user, UserStatus.UPDATED);
        } catch (Exception e) {
            log.error("error while updating user", e);
            return Map.of(user, UserStatus.ERROR);
        }
    }

    public Map<User, UserStatus> getUserStatusMap(Long id) {
        return Optional.ofNullable(entityManager.find(User.class, id))
            .map(u -> Map.of(u, UserStatus.FOUND))
            .orElse(Map.of(new User(id, ""), UserStatus.NO_SUCH_USER));
    }

    private CriteriaQuery<User> getCriteriaQuery() {
        CriteriaQuery<User> query = entityManager.getCriteriaBuilder().createQuery(User.class);
        query.from(User.class);
        return query;
    }
}
