package com.crasoftinc.jmatrailers.controllers;

import com.crasoftinc.jmatrailers.data.TrailersEntity;
import com.crasoftinc.jmatrailers.exceptions.CustomGeneralException;
import com.crasoftinc.jmatrailers.exceptions.NotFoundRequestException;
import com.crasoftinc.jmatrailers.models.CreateTrailerModel;
import com.crasoftinc.jmatrailers.models.DefaultTrailerModel;
import com.crasoftinc.jmatrailers.models.UpdateTrailerModel;
import com.crasoftinc.jmatrailers.service.TrailersServiceImpl;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/trailers")
//@Api(value = "Trucks service fro doing CRUD operations ", tags = {"TRUCKS"})
public class TrailersController {
    private final TrailersServiceImpl trailersServiceImpl;

    public TrailersController(TrailersServiceImpl trailersServiceImpl) {
        this.trailersServiceImpl = trailersServiceImpl;
    }

    @GetMapping()
//    @ApiOperation(value = "Get all trucks with pagination", response = DefaultTrailerModel.class)
    public Page<TrailersEntity> getAllTrailers(@RequestParam(required = false) Integer page,
                                             @RequestParam(required = false) Integer size) {
        return  trailersServiceImpl.getAllTrailerInformation(page, size);
    }

    @GetMapping("/{truck_id}")
//    @ApiOperation(value = "Get truck details for given id", response = CreateTrailerModel.class)
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Successfully retrieved list"),
//            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
//            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
//            @ApiResponse(code = 404, message = "Requested Resource Not Found")
//    })
    public TrailersEntity getTrailerById(@PathVariable("truck_id") String truck_id) throws NotFoundRequestException {
        return trailersServiceImpl.getTrailerInformationById(truck_id);
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
//    @ApiOperation(value = "Making post request for creating trucks", response = CreateTrailerModel.class)
    public ResponseEntity<TrailersEntity> insertTrailerData(@Valid @RequestBody CreateTrailerModel createTrailerModel) throws CustomGeneralException {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        TrailersEntity trailersEntity = modelMapper.map(createTrailerModel, TrailersEntity.class);
        DefaultTrailerModel defaultTrailerModel = modelMapper.map(trailersEntity, DefaultTrailerModel.class);
        trailersServiceImpl.insertTrailerData(defaultTrailerModel);
        return ResponseEntity.status(HttpStatus.OK).body(trailersEntity);

    }

    @PutMapping(path = "/{id}")
//    @ApiOperation(value = "Update trucks info with given id", response = CreateTrailerModel.class)
    public ResponseEntity<TrailersEntity> updateTrailers(@PathVariable("id") String id, @Valid @RequestBody UpdateTrailerModel updateTrailerModel) throws CustomGeneralException {
        return trailersServiceImpl.updateTrailer(id, updateTrailerModel);
    }

    @DeleteMapping(path = "/{id}")
//    @ApiOperation(value = "Delete truck with given id ", response = CreateTrailerModel.class)
    public void deleteTrailers(@PathVariable String id) throws CustomGeneralException {
        trailersServiceImpl.deleteTrailer(id);
    }
}
