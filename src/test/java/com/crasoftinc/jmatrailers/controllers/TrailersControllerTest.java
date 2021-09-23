package com.crasoftinc.jmatrailers.controllers;

import com.crasoftinc.jmatrailers.data.TrailersEntity;
import com.crasoftinc.jmatrailers.models.*;
import com.crasoftinc.jmatrailers.service.TrailersServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Objects;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
class TrailersControllerTest {

  @Autowired
  TrailersServiceImpl trailersService;

  @BeforeEach
  void insertTrailerData() {
    CreateTrailerModel trailer = new CreateTrailerModel("asdfgh", "jbhsc", true, "acbkwu476q2hkd",
        new LocationModel("Sscd", "jhbcsd"), new OwnerTypeModel(OwnerStatusModel.ROOT, "nksjdc"));
    trailersService.insertTrailerData(trailer);
  }

  @Test
  void getAllTrailers() {
    Assertions.assertFalse(trailersService.getAllTrailerInformation(null, null).isEmpty());
  }

  @Test
  void getTrailerById() {
    TrailersEntity trailerModel = trailersService.getTrailerInformationById("asdfgh");
    Assertions.assertEquals("jbhsc", trailerModel.getCompanyId());
  }

  @Test
  @DisplayName("Update Trailers")
  void updateTrailers() {
    TrailersEntity trailerModel = trailersService.getTrailerInformationById("asdfgh");
    trailerModel.setCompanyId("sldnfj");
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    UpdateTrailerModel updateTrailerModel = modelMapper.map(trailerModel, UpdateTrailerModel.class);
    ResponseEntity<TrailersEntity> updateTrailer =
        trailersService.updateTrailer("asdfgh", updateTrailerModel);
    Assertions
        .assertEquals("sldnfj", Objects.requireNonNull(updateTrailer.getBody()).getCompanyId());
  }

  @AfterEach
  void deleteTrailers() {
    trailersService.deleteTrailer("asdfgh");
  }
}