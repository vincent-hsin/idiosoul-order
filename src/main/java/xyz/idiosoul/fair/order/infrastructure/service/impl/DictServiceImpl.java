package xyz.idiosoul.fair.order.infrastructure.service.impl;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import xyz.idiosoul.fair.order.infrastructure.constant.DictLabel;
import xyz.idiosoul.fair.order.infrastructure.dto.DictAddDTO;
import xyz.idiosoul.fair.order.infrastructure.model.Dict;
import xyz.idiosoul.fair.order.infrastructure.repository.DictRepository;
import xyz.idiosoul.fair.order.infrastructure.service.DictService;

import java.util.List;

/**
 * @author xinw
 */
@Service
public class DictServiceImpl implements DictService {
    private DictRepository dictRepository;

    public DictServiceImpl(DictRepository dictRepository) {
        this.dictRepository = dictRepository;
    }

    @Override
    public void add(long adminId, DictLabel dictLabel, DictAddDTO dictAddDTO) {
        dictRepository.findByLabelAndTextAndDeleteAtIsNull(dictLabel.toString(), dictAddDTO.getText()).ifPresent(dict -> {
            throw new DuplicateKeyException("字典名重复");
        });
        Dict dict = new Dict(dictLabel.toString(), dictAddDTO.getCode(), dictAddDTO.getText(), dictAddDTO.getSeq(), dictAddDTO.getRemark());
        dictRepository.save(dict);
        // TODO v2.13.0 log
    }

    @Override
    public String getText(DictLabel dictLabel, Integer code) {
        Dict dict = get(dictLabel, code);
        return dict.getText();
    }

    @Override
    public Dict get(DictLabel dictLabel, Integer code) {
        return dictRepository.findByLabelAndCodeAndDeleteAtIsNull(dictLabel.name(), code).orElseThrow(() -> new RuntimeException(String.format("%s不存在code[%d]", dictLabel.getDesc(), code)));
    }

    @Override
    public List<Dict> list(DictLabel label) {
        return dictRepository.findAllByLabelAndDeleteAtIsNull(label.name());
    }
}
