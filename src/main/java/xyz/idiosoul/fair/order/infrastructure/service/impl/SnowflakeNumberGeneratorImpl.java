package xyz.idiosoul.fair.order.infrastructure.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import xyz.downgoon.snowflake.Snowflake;
import xyz.idiosoul.fair.order.infrastructure.service.NumberGenerator;

/**
 * @author xinw
 */
@Service
public class SnowflakeNumberGeneratorImpl implements NumberGenerator {
    Snowflake snowflake;

    public SnowflakeNumberGeneratorImpl(@Value("${worker.id}") int workerId) {
        snowflake = new Snowflake(1, workerId);
    }

    @Override
    public long nextLongNumber() {
        return snowflake.nextId();
    }

}
