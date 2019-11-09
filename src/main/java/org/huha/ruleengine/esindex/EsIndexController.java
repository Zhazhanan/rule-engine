package org.huha.ruleengine.esindex;

import org.huha.ruleengine.config.EsService;
import org.huha.ruleengine.base.ResponseMsg;
import org.huha.ruleengine.esindex.vo.ElasticDataVo;
import org.huha.ruleengine.esindex.vo.IndexVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Description
 * <br> Created by WangKun on 2019/10/31
 * <br>
 **/
@RestController
public class EsIndexController {
    @Autowired
    private EsService esService;

    @GetMapping("/elastic/exist/{index}")
    public ResponseMsg<Boolean> indexExist(@PathVariable(value = "index") String index) throws Exception {
        boolean indexExist = esService.indexExist(index);
        return new ResponseMsg<>(indexExist);
    }

    @PostMapping("/elastic/create")
    public ResponseMsg createIndex(@RequestBody IndexVO indexVO) throws Exception {
        esService.createIndex(indexVO);
        return new ResponseMsg<>();
    }

    @DeleteMapping("/elastic/delete/{index}")
    public ResponseMsg deleteIndex(@PathVariable(value = "index") String index) throws Exception {
        esService.deleteIndex(index);
        return new ResponseMsg<>();
    }

    @GetMapping("/elastic/open")
    public ResponseMsg openIndex(String... index) throws Exception {
        esService.openIndex(index);
        return new ResponseMsg<>();
    }

    @GetMapping("/elastic/close")
    public ResponseMsg closeIndex(String... index) throws Exception {
        esService.closeIndex(index);
        return new ResponseMsg<>();
    }

    @PostMapping("/elastic/add")
    public ResponseMsg add(@RequestBody ElasticDataVo elasticDataVo) throws Exception {
        esService.insertOrUpdateOne(elasticDataVo.getIdxName(), elasticDataVo.getElasticEntity());
        return new ResponseMsg<>();
    }

}
