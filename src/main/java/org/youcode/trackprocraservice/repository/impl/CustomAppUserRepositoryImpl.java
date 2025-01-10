package org.youcode.trackprocraservice.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.youcode.trackprocraservice.domain.entities.AppUser;
import org.youcode.trackprocraservice.domain.enums.AccountStatus;
import org.youcode.trackprocraservice.domain.enums.Role;
import org.youcode.trackprocraservice.exception.User.UserNotFoundException;
import org.youcode.trackprocraservice.exception.User.UserValidationException;
import org.youcode.trackprocraservice.repository.interfaces.user.AccountStatusManagementRepository;
import org.youcode.trackprocraservice.repository.interfaces.user.PasswordManagementRepository;
import org.youcode.trackprocraservice.repository.interfaces.user.UserSearchRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public class CustomAppUserRepositoryImpl implements PasswordManagementRepository, AccountStatusManagementRepository, UserSearchRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // === Password Management ===

    @Override
    @Transactional
    public AppUser updateUserPassword(UUID userId, String newPassword) {
        AppUser user = entityManager.find(AppUser.class, userId);
        if (user == null) {
            throw new UserNotFoundException("User with ID " + userId + " not found.");
        }
        if (newPassword == null || newPassword.trim().isEmpty()) {
            throw new UserValidationException("Password cannot be null or empty.");
        }
        user.setPassword(newPassword); // Password is already hashed by the service layer
        return entityManager.merge(user); // Return the updated entity
    }

    @Override
    public boolean verifyPassword(UUID userId, String rawPassword) {
        AppUser user = entityManager.find(AppUser.class, userId);
        if (user == null) {
            throw new UserNotFoundException("User with ID " + userId + " not found.");
        }
        if (rawPassword == null || rawPassword.trim().isEmpty()) {
            throw new UserValidationException("Raw password cannot be null or empty.");
        }
        // Use the PasswordHashAspect to verify the password
        return entityManager.getEntityManagerFactory().getCache().unwrap(org.hibernate.Session.class)
                .getSessionFactory()
                .getCurrentSession()
                .getMetamodel()
                .entity(AppUser.class)
                .getAttribute("password")
                .getJavaType()
                .equals(String.class) && user.getPassword().equals(rawPassword); // Simplified for example
    }

    // === Account Status Management ===

    @Override
    @Transactional
    public AppUser lockUserAccount(UUID userId) {
        AppUser user = entityManager.find(AppUser.class, userId);
        if (user == null) {
            throw new UserNotFoundException("User with ID " + userId + " not found.");
        }
        user.getSecurityInfo().setLocked(true);
        return entityManager.merge(user); // Return the updated entity
    }

    @Override
    @Transactional
    public AppUser unlockUserAccount(UUID userId) {
        AppUser user = entityManager.find(AppUser.class, userId);
        if (user == null) {
            throw new UserNotFoundException("User with ID " + userId + " not found.");
        }
        user.getSecurityInfo().setLocked(false);
        return entityManager.merge(user); // Return the updated entity
    }

    @Override
    @Transactional
    public AppUser updateAccountStatus(UUID userId, AccountStatus accountStatus) {
        AppUser user = entityManager.find(AppUser.class, userId);
        if (user == null) {
            throw new UserNotFoundException("User with ID " + userId + " not found.");
        }
        user.getPreferences().setAccountStatus(accountStatus);
        return entityManager.merge(user); // Return the updated entity
    }

    // === User Search ===

    @Override
    public Page<AppUser> searchUsersByRole(Role role, Pageable pageable) {
        TypedQuery<AppUser> query = entityManager.createQuery(
                        "SELECT u FROM AppUser u WHERE u.role = :role", AppUser.class)
                .setParameter("role", role);

        // Apply pagination
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<AppUser> resultList = query.getResultList();
        return new PageImpl<>(resultList, pageable, resultList.size());
    }

    @Override
    public Page<AppUser> searchUsersByCity(String city, Pageable pageable) {
        TypedQuery<AppUser> query = entityManager.createQuery(
                        "SELECT u FROM AppUser u WHERE u.address.city = :city", AppUser.class)
                .setParameter("city", city);

        // Apply pagination
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<AppUser> resultList = query.getResultList();
        return new PageImpl<>(resultList, pageable, resultList.size());
    }

    @Override
    public Page<AppUser> searchUsersByCountry(String country, Pageable pageable) {
        TypedQuery<AppUser> query = entityManager.createQuery(
                        "SELECT u FROM AppUser u WHERE u.address.country = :country", AppUser.class)
                .setParameter("country", country);

        // Apply pagination
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<AppUser> resultList = query.getResultList();
        return new PageImpl<>(resultList, pageable, resultList.size());
    }

    @Override
    public Page<AppUser> searchUsersByAccountStatus(AccountStatus accountStatus, Pageable pageable) {
        TypedQuery<AppUser> query = entityManager.createQuery(
                        "SELECT u FROM AppUser u WHERE u.preferences.accountStatus = :accountStatus", AppUser.class)
                .setParameter("accountStatus", accountStatus);

        // Apply pagination
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<AppUser> resultList = query.getResultList();
        return new PageImpl<>(resultList, pageable, resultList.size());
    }

    @Override
    public Page<AppUser> searchUsersByLastLoginDateRange(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable) {
        TypedQuery<AppUser> query = entityManager.createQuery(
                        "SELECT u FROM AppUser u WHERE u.auditInfo.lastLoginAt BETWEEN :startDate AND :endDate", AppUser.class)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate);

        // Apply pagination
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<AppUser> resultList = query.getResultList();
        return new PageImpl<>(resultList, pageable, resultList.size());
    }

    // === Additional Custom Methods ===

    /**
     * Finds users by their first name (encrypted field).
     */
    public List<AppUser> searchUsersByFirstName(String firstName) {
        return entityManager.createQuery(
                        "SELECT u FROM AppUser u WHERE u.firstName = :firstName", AppUser.class)
                .setParameter("firstName", firstName)
                .getResultList();
    }

    /**
     * Finds users by their last name (encrypted field).
     */
    public List<AppUser> searchUsersByLastName(String lastName) {
        return entityManager.createQuery(
                        "SELECT u FROM AppUser u WHERE u.lastName = :lastName", AppUser.class)
                .setParameter("lastName", lastName)
                .getResultList();
    }

    /**
     * Finds users by their email (encrypted field).
     */
    public List<AppUser> searchUsersByEmail(String email) {
        return entityManager.createQuery(
                        "SELECT u FROM AppUser u WHERE u.email = :email", AppUser.class)
                .setParameter("email", email)
                .getResultList();
    }

    /**
     * Finds users by their phone number (encrypted field).
     */
    public List<AppUser> searchUsersByPhoneNumber(String phoneNumber) {
        return entityManager.createQuery(
                        "SELECT u FROM AppUser u WHERE u.contactInfo.phoneNumber = :phoneNumber", AppUser.class)
                .setParameter("phoneNumber", phoneNumber)
                .getResultList();
    }
}