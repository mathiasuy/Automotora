package com.mathiasuy.automotora.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mathiasuy.automotora.dataaccess.IAutoDataAccess;
import com.mathiasuy.automotora.exceptions.ErrorType;
import com.mathiasuy.automotora.exceptions.NotAllowedException;
import com.mathiasuy.automotora.exceptions.NotFoundException;
import com.mathiasuy.automotora.model.Auto;
import com.mathiasuy.automotora.requests.AutoRequest;
import com.mathiasuy.automotora.responses.AutoResponse;
import com.mathiasuy.automotora.utils.Constants;


@Service
public class AutoServices extends CrudServices<AutoResponse, AutoRequest, 	Long> {	
	
	Logger logger = LoggerFactory.getLogger(AutoServices.class);


    @Value("${varchar.modelo.largo}")
    private Integer largoModelo;

    @Value("${varchar.marca.largo}")
    private Integer largoMarca;

    @Autowired
    private IAutoDataAccess autoDataAccess;
    
    private boolean control(String marca, String modelo) {
        if (modelo.length() > largoModelo){
            throw new NotAllowedException(ErrorType.USER_ERROR, String.format(Constants.STR_LARGO_MODELO_SUPERADO,largoModelo), logger);
        }
        if (marca.length() > largoMarca){
            throw new NotAllowedException(ErrorType.USER_ERROR, String.format(Constants.STR_LARGO_MARCA_SUPERADO,largoMarca), logger);
        }
        return true;
    }

	@Override
	public AutoResponse add(AutoRequest request) {
		control(request.getMarca(), request.getModelo());
		return iMapper.toResponse(autoDataAccess.save(iMapper.fromRequest(request)));
	}

	@Override
	public AutoResponse update(Long id, AutoRequest request) {
		Optional<Auto> autoInDb = autoDataAccess.findById(id);
		if (!autoInDb.isPresent()) {
            throw new NotFoundException(ErrorType.USER_ERROR, Constants.STR_NO_EXISTE, logger);			
		}
		Auto auto = iMapper.fromRequest(request);
		auto.setId(id);
		return iMapper.toResponse(autoDataAccess.saveAndFlush(auto));
	}

	@Override
	public void delete(Long id) {
		if (!autoDataAccess.existsById(id)) {
            throw new NotFoundException(ErrorType.USER_ERROR, Constants.STR_NO_EXISTE, logger);			
		}
		autoDataAccess.deleteById(id);	
	}

	@Override
	public List<AutoResponse> findAll() {
		return autoDataAccess.findAll().stream().map(moto -> iMapper.toResponse(moto)).collect(Collectors.toList());
	}

	@Override
	public AutoResponse findById(Long id) {
		return iMapper.toResponse(autoDataAccess.findById(id)
				.orElseThrow(() -> new NotFoundException(ErrorType.USER_ERROR, Constants.STR_NO_EXISTE, logger)));	
	}

}
