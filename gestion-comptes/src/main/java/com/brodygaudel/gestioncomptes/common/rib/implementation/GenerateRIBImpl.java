package com.brodygaudel.gestioncomptes.common.rib.implementation;

import com.brodygaudel.gestioncomptes.common.rib.GenerateRIB;
import com.brodygaudel.gestioncomptes.query.entities.Compter;
import com.brodygaudel.gestioncomptes.query.repositories.CompterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class GenerateRIBImpl implements GenerateRIB {

    private static final String ID_GENERATED = "Id Generated as RIB";

    private final CompterRepository compterRepository;

    public GenerateRIBImpl(CompterRepository compterRepository) {
        this.compterRepository = compterRepository;
    }

    @Override
    public String autoGenerate() {
        log.info("In generateProductId()");
        try {
            List<Compter> compters = compterRepository.findAll();
            Compter compter;
            if(compters.isEmpty()) {
                compter = new Compter((long) 99999);
            }
            else {
                compter = compters.get(compters.size() - 1);
                compterRepository.deleteById(compter.getId());
            }
            log.info(ID_GENERATED);
            return generateForCustomer(compter);
        }catch(Exception e) {
            log.info("Id Not Generated: Exception :"+e);
            return null;
        }
    }

    private String generateForCustomer(Compter compter) {
        Long cpt = compter.getId()+1;
        Compter compt = new Compter(cpt);
        compterRepository.save(compt);
        String body = cpt.toString();
        return "2023"+body;
    }

}
