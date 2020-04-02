package org.launchcode.pao.Models.Data;

import org.launchcode.pao.Models.Cheese;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
@Repository
@Transactional
public interface PaoDao extends CrudRepository<Cheese, Integer> {
}
