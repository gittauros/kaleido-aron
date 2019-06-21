package com.tauros.kaleido.aron.service;

import org.springframework.web.bind.annotation.RequestMapping;

public interface AronService {

    String putRedisObject();

    Object getRedisObject();
}
