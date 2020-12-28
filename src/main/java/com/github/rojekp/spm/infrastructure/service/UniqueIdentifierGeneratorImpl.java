package com.github.rojekp.spm.infrastructure.service;

import com.github.rojekp.spm.domain.service.UniqueIdentifierGenerator;
import org.springframework.stereotype.Service;

import static java.util.UUID.randomUUID;

@Service
public class UniqueIdentifierGeneratorImpl implements UniqueIdentifierGenerator {

    @Override
    public String generateUniqueIdentifier() {
        return randomUUID().toString();
    }

}
