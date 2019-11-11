package com.contact.contactFinderOne.security;

//import org.springframework.data.repository.CrudRepository;
import com.contact.contactFinderOne.entities.DAOUser;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface UserDaoRepository extends JpaRepository<DAOUser, Integer> {
	
	DAOUser findById(String id);
        
        @Query("select u from DAOUser u where u.codeConfirmation like :cc")
	public DAOUser findByCodeConfirmation(@Param("cc") String code);
        
}