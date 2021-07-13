package com.project.linkedindatabase.service.model.accomplishment;

import com.project.linkedindatabase.domain.Background;
import com.project.linkedindatabase.domain.accomplishment.Accomplishment;
import com.project.linkedindatabase.service.BaseService;

import java.util.List;

public interface AccomplishmentService extends BaseService<Accomplishment,Long> {
    List<Accomplishment> findByProfileId(Long id) throws Exception;

    void update(Accomplishment accomplishment) throws Exception;

    public void updateWithProfileId(Accomplishment accomplishment) throws Exception ;

    public void deleteByIdAndProfileId(Accomplishment accomplishment) throws Exception ;
}
