package com.interview.assignment.vehiclemaintenance.repository;

import com.interview.assignment.vehiclemaintenance.model.User;
import com.interview.assignment.vehiclemaintenance.model.VehicleOwnership;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByCellPhone(String cellPhone);

}
