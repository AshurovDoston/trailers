package com.crasoftinc.jmatrailers.service;

import com.crasoftinc.jmatrailers.data.TrailersEntity;
import com.crasoftinc.jmatrailers.exceptions.CustomGeneralException;
import com.crasoftinc.jmatrailers.exceptions.NotFoundRequestException;
import com.crasoftinc.jmatrailers.models.DefaultTrailerModel;
import com.crasoftinc.jmatrailers.models.UpdateTrailerModel;
import com.crasoftinc.jmatrailers.repository.TrailersRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TrailersServiceImpl implements TrailersService{

    private final TrailersRepository trailersRepository;

    public TrailersServiceImpl(TrailersRepository trailersRepository) {
        this.trailersRepository = trailersRepository;
    }

    public DefaultTrailerModel insertTrailerData(DefaultTrailerModel defaultTrailerModel) throws CustomGeneralException {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        TrailersEntity entity = modelMapper.map(defaultTrailerModel, TrailersEntity.class);
        trailersRepository.insert(entity);
        return modelMapper.map(entity, DefaultTrailerModel.class);
    }

//    public TrailersEntity insertTrailerData(CreateTrailerModel trailer) {
//        return trailersRepository.insert(trailer);
//    }

    public Page<TrailersEntity> getAllTrailerInformation(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page != null ? page : 0, size != null ? size : 10);
        return trailersRepository.findAll(pageable);
    }

    public TrailersEntity getTrailerInformationById(String id) throws NotFoundRequestException {
        return trailersRepository.findById(id).orElseThrow(() -> new NotFoundRequestException("trailer not found fot this id:" + id, "404"));
    }

    public ResponseEntity<TrailersEntity> updateTrailer(String id, UpdateTrailerModel trailer) throws CustomGeneralException {
        var res = trailersRepository.findById(id);
        if (res.isPresent()) {
            TrailersEntity trailerValues = res.get();
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//            trailerValues.setIs_assigned(trailer.getIs_assigned());
            trailerValues.setCompany_id(trailer.getCompany_id());
//            trailerValues.setDriver_id(trailer.getDriver_id());
            trailerValues.setLocation(trailer.getLocation());
            trailerValues.setOwner_type(trailer.getOwner_type());
            trailerValues.setNotes(trailer.getNotes());
            TrailersEntity entity = trailersRepository.save(trailerValues);
            return ResponseEntity.status(HttpStatus.OK).body(entity);
        } else {
            throw new NotFoundRequestException("There is not trailer with this id:"+id, "404");
        }

    }

    public void deleteTrailer(String id) throws CustomGeneralException {
        var res = trailersRepository.findById(id);
        if (res.isPresent()) {
            trailersRepository.deleteById(id);
        } else {
            throw new NotFoundRequestException("We have no trailer with this id:"+ id,"404");
        }
    }
}
