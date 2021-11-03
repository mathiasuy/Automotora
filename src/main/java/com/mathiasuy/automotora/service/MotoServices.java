package com.automotora.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.automotora.dataaccess.IMotoDataAccess;
import com.automotora.exceptions.ErrorType;
import com.automotora.exceptions.NotAllowedException;
import com.automotora.exceptions.NotFoundException;
import com.automotora.model.Auto;
import com.automotora.model.Moto;
import com.automotora.requests.MotoRequest;
import com.automotora.responses.MotoResponse;
import com.automotora.utils.Constants;


@Service
public class MotoServices extends CrudServices<MotoResponse, MotoRequest, Long> {	
	
	Logger logger = LoggerFactory.getLogger(MotoServices.class);
	
	@Autowired
	private IMotoDataAccess motoDataAccess;

    @Value("${varchar.modelo.largo}")
    private Integer largoModelo;

    @Value("${varchar.marca.largo}")
    private Integer largoMarca;

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
	public MotoResponse add(MotoRequest request) {
		control(request.getMarca(), request.getModelo());
		return iMapper.toResponse(motoDataAccess.save(iMapper.fromRequest(request)));
	}

	@Override
	public MotoResponse update(Long id, MotoRequest request) {
		Optional<Moto> autoInDb = motoDataAccess.findById(id);
		if (!autoInDb.isPresent()) {
            throw new NotFoundException(ErrorType.USER_ERROR, Constants.STR_NO_EXISTE, logger);			
		}
		Moto auto = iMapper.fromRequest(request);
		auto.setId(id);
		return iMapper.toResponse(motoDataAccess.saveAndFlush(auto));
	}

	@Override
	public void delete(Long id) {
		if (!motoDataAccess.existsById(id)) {
            throw new NotFoundException(ErrorType.USER_ERROR, Constants.STR_NO_EXISTE, logger);			
		}
		motoDataAccess.deleteById(id);	
	}

	@Override
	public List<MotoResponse> findAll() {
		return motoDataAccess.findAll().stream().map(moto -> iMapper.toResponse(moto)).collect(Collectors.toList());
	}

	@Override
	public MotoResponse findById(Long id) {
		return iMapper.toResponse(motoDataAccess.findById(id)
				.orElseThrow(() -> new NotFoundException(ErrorType.USER_ERROR, Constants.STR_NO_EXISTE, logger)));	
	}

}
