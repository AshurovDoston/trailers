package com.crasoftinc.jmatrailers.controllers;

import com.crasoftinc.jmatrailers.data.TrailersEntity;
import com.crasoftinc.jmatrailers.exceptions.CustomGeneralException;
import com.crasoftinc.jmatrailers.exceptions.NotFoundRequestException;
import com.crasoftinc.jmatrailers.models.CreateTrailerModel;
import com.crasoftinc.jmatrailers.models.UpdateTrailerModel;
import com.crasoftinc.jmatrailers.service.TrailersServiceImpl;
import javax.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/trailers")
//@Api(value = "Trucks service fro doing CRUD operations ", tags = {"TRUCKS"})
public class TrailersController {
  private final TrailersServiceImpl trailersServiceImpl;

  public TrailersController(TrailersServiceImpl trailersServiceImpl) {
    this.trailersServiceImpl = trailersServiceImpl;
  }

  @GetMapping()
  //    @ApiOperation(value = "Get all trucks with pagination", response = CreateTrailerModel.class)
  public Page<TrailersEntity> getAllTrailers(@RequestParam(required = false) Integer page,
                                             @RequestParam(required = false) Integer size) {
    return trailersServiceImpl.getAllTrailerInformation(page, size);
  }

  @GetMapping("/{id}")
  //    @ApiOperation(value = "Get truck details for given id", response = CreateTrailerModel.class)
  //    @ApiResponses(value = {
  //            @ApiResponse(code = 200, message = "Successfully retrieved list"),
  //            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
  //            @ApiResponse(code = 403, message =
  //            "Accessing the resource you were trying to reach is forbidden"),
  //            @ApiResponse(code = 404, message = "Requested Resource Not Found")
  //    })
  public TrailersEntity getTrailerById(@PathVariable("id") String id)
      throws NotFoundRequestException {
    return trailersServiceImpl.getTrailerInformationById(id);
  }

  @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {
      MediaType.APPLICATION_JSON_VALUE})
  //    @ApiOperation(value = "Making post request for creating trucks", response =
  //    CreateTrailerModel.class)
  public CreateTrailerModel insertTrailerData(
      @Valid @RequestBody CreateTrailerModel createTrailerModel) throws CustomGeneralException {
//            ModelMapper modelMapper = new ModelMapper();
//            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//            TrailersEntity trailersEntity = modelMapper.map(createTrailerModel,
//            TrailersEntity.class);
//            DefaultTrailerModel defaultTrailerModel = modelMapper.map(trailersEntity,
//            DefaultTrailerModel.class);
    return trailersServiceImpl.insertTrailerData(createTrailerModel);
    //        return ResponseEntity.status(HttpStatus.OK).body(trailersEntity);
  }

  @PutMapping(path = "/{id}")
  //    @ApiOperation(value = "Update trucks info with given id", response =
  //    CreateTrailerModel.class)
  public ResponseEntity<TrailersEntity> updateTrailers(@PathVariable("id") String id,
                                                       @Valid @RequestBody
                                                           UpdateTrailerModel updateTrailerModel)
      throws CustomGeneralException {
    return trailersServiceImpl.updateTrailer(id, updateTrailerModel);
  }

  @DeleteMapping(path = "/{id}")
  //    @ApiOperation(value = "Delete truck with given id ", response = CreateTrailerModel.class)
  public void deleteTrailers(@PathVariable String id) throws CustomGeneralException {
    trailersServiceImpl.deleteTrailer(id);
  }
}
