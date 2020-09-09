package xyz.idiosoul.fair.order.infrastructure.service;

import xyz.idiosoul.fair.order.infrastructure.constant.DictLabel;
import xyz.idiosoul.fair.order.infrastructure.dto.DictAddDTO;
import xyz.idiosoul.fair.order.infrastructure.model.Dict;

import java.util.List;

/**
 * @author xinw
 */
public interface DictService {
    void add(long adminId, DictLabel dictLabel, DictAddDTO dictAddDTO);

    String getText(DictLabel dictLabel, Integer code);

    Dict get(DictLabel dictLabel, Integer code);

    List<Dict> list(DictLabel dictLabel);
}
