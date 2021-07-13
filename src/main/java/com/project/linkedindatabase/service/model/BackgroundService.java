package com.project.linkedindatabase.service.model;

import com.project.linkedindatabase.domain.Background;
import com.project.linkedindatabase.service.BaseService;

import java.util.List;

public interface BackgroundService extends BaseService<Background,Long> {

    public List<Background> findByProfileId(Long id) throws Exception;

    public void update(Background background) throws Exception;

    public void updateWithProfileId(Background object) throws Exception;

    public void deleteByIdAndProfileId(Background background) throws Exception;
}
