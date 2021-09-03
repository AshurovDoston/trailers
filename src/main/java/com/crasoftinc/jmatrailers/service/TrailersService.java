package com.crasoftinc.jmatrailers.service;

import com.crasoftinc.jmatrailers.exceptions.CustomGeneralException;
import com.crasoftinc.jmatrailers.models.DefaultTrailerModel;

public interface TrailersService {
 DefaultTrailerModel insertTrailerData(DefaultTrailerModel defaultTrailerModel) throws CustomGeneralException;
}
