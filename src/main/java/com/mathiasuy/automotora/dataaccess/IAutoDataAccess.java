package com.mathiasuy.automotora.dataaccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mathiasuy.automotora.model.Auto;

@Repository
public interface IAutoDataAccess extends JpaRepository<Auto, Long>  {

}
