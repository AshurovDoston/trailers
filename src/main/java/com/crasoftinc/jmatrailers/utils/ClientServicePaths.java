package com.crasoftinc.jmatrailers.utils;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

public enum ClientServicePaths {

  USERS_SERVICE,
  MEDIA_SERVICE,
  TRAILERS_SERVICE,
  TRUCKS_SERVICE;


  @Component
  public static class ServicesPathPropertyResolver{
    @Autowired
    private Environment environment;

    @PostConstruct
    public void postConstruct() {
      ClientServicePaths.USERS_SERVICE.setPath(environment.getProperty("jma.services.users-service.path"));
      ClientServicePaths.MEDIA_SERVICE.setPath(environment.getProperty("jma.services.media-service.path"));
      ClientServicePaths.TRAILERS_SERVICE.setPath(environment.getProperty("jma.services.trucks-service.path"));
      ClientServicePaths.TRUCKS_SERVICE.setPath(environment.getProperty("jma.services.trailers-service.path"));
    }
  }

  private String path;

  public ClientServicePaths setPath(String path) {
    this.path = path;
    return this;
  }

  public String getPath() {
    return path;
  }

}
