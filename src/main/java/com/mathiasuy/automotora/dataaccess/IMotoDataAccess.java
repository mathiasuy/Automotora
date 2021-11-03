package com.mathiasuy.automotora.dataaccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mathiasuy.automotora.model.Moto;

@Repository
public interface IMotoDataAccess extends JpaRepository<Moto, Long>  {

}
