package com.crasoftinc.jmatrailers.service;

import com.crasoftinc.jmatrailers.data.TrailersEntity;
import com.crasoftinc.jmatrailers.exceptions.CustomGeneralException;
import com.crasoftinc.jmatrailers.exceptions.NotFoundRequestException;
import com.crasoftinc.jmatrailers.models.CreateTrailerModel;
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
public class TrailersServiceImpl {

  private final TrailersRepository trailersRepository;

  public TrailersServiceImpl(TrailersRepository trailersRepository) {
    this.trailersRepository = trailersRepository;
  }

  public CreateTrailerModel insertTrailerData(CreateTrailerModel createTrailerModel)
      throws CustomGeneralException {
    return trailersRepository.insert(createTrailerModel);
  }

  //    public TrailersEntity insertTrailerData(CreateTrailerModel trailer) {
  //        return trailersRepository.insert(trailer);
  //    }

  public Page<TrailersEntity> getAllTrailerInformation(Integer page, Integer size) {
    Pageable pageable = PageRequest.of(page != null ? page : 0, size != null ? size : 10);
    return trailersRepository.findAll(pageable);
  }

  public TrailersEntity getTrailerInformationById(String id) throws NotFoundRequestException {
    return trailersRepository.findById(id).orElseThrow(
        () -> new NotFoundRequestException("trailer not found fot this id:" + id, "404"));
  }

  public ResponseEntity<TrailersEntity> updateTrailer(String id, UpdateTrailerModel trailer)
      throws CustomGeneralException {
    var res = trailersRepository.findById(id);
    if (res.isPresent()) {
      TrailersEntity trailerValues = res.get();
      ModelMapper modelMapper = new ModelMapper();
      modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
      trailerValues.setAssigned(trailer.isAssigned());
      trailerValues.setCompanyId(trailer.getCompanyId());
      trailerValues.setVin(trailer.getVin());
      trailerValues.setDriverId(trailer.getDriverId());
      trailerValues.setLocation(trailer.getLocation());
      trailerValues.setOwnerType(trailer.getOwnerType());
      trailerValues.setNotes(trailer.getNotes());
      TrailersEntity entity = trailersRepository.save(trailerValues);
      return ResponseEntity.status(HttpStatus.OK).body(entity);
    } else {
      throw new NotFoundRequestException("There is not trailer with this id:" + id, "404");
    }

  }

  public void deleteTrailer(String id) throws CustomGeneralException {
    var res = trailersRepository.findById(id);
    if (res.isPresent()) {
      trailersRepository.deleteById(id);
    } else {
      throw new NotFoundRequestException("We have no trailer with this id:" + id, "404");
    }
  }
}
