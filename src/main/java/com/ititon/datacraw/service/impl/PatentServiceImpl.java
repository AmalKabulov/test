package com.ititon.datacraw.service.impl;

import com.ititon.datacraw.model.Patent;
import com.ititon.datacraw.repository.PatentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PatentServiceImpl {
    private PatentRepository patentRepository;

    @Autowired
    public PatentServiceImpl(PatentRepository patentRepository) {
        this.patentRepository = patentRepository;
    }


    public void saveAll(Collection<Patent> patents) {
        List<Patent> collect = patents.stream()
                .filter(p -> Objects.equals(null, patentRepository.findByPublicationNum(p.getPublicationNum())))
                .collect(Collectors.toList());

        patentRepository.saveAll(collect);
    }
}
