package umb.carp.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import umb.carp.user.User;


@Repository
public interface UserDao extends CrudRepository<User, Integer>{

}
