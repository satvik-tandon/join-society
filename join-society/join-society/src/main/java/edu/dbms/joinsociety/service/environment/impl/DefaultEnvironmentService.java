package edu.dbms.joinsociety.service.environment.impl;

import edu.dbms.joinsociety.service.environment.EnvironmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class DefaultEnvironmentService implements EnvironmentService {
    @Autowired
    private Environment environment;

    @Override
    public Object getPropertyValue(final String key) {
        return environment.getProperty(key);
    }
}
