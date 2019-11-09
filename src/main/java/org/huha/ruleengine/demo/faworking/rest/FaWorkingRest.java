package org.huha.ruleengine.demo.faworking.rest;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.huha.ruleengine.config.EsService;
import org.huha.ruleengine.demo.faworking.dto.FaWorkingDTO;
import org.huha.ruleengine.demo.faworking.service.FaWorkingService;
import org.huha.ruleengine.demo.faworking.vo.FaWorkingRequest;
import org.huha.ruleengine.demo.faworking.vo.FaWorkingVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 工作信息-申请库
 *
 * @author WangKun
 * @date 2019-02-12 11:14
 **/
@Api(tags = {"fa-工作信息"}, description = "工作信息-申请库")
@RestController
@Scope("prototype")
@RequestMapping(value = "/api/faWorking")
public class FaWorkingRest {

    @Autowired
    private FaWorkingService service;
    @Autowired
    private EsService esService;

    /**
     * Description
     * <br> 根据进件编号申请库查询工作信息
     *
     * @param applicationNumber 进件编号
     * @date 2019-04-01
     * @author lichengqiang
     **/
    @ApiOperation(value = "根据进件编号申请库查询工作信息", notes = "根据进件编号申请库查询工作信息")
    @RequestMapping(value = "/getWorkByAppNum/v1/{applicationNumber}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getWorkByAppNum(@ApiParam(value = "进件编号", required = true) @PathVariable("applicationNumber") String applicationNumber) throws Exception {
        List<FaWorkingVO> faWorkingVOList = new ArrayList<>();
        List<FaWorkingDTO> faWorkingDTO = service.queryFaApplicationByAppNum(applicationNumber);
        faWorkingDTO.forEach(v -> {
            FaWorkingVO faWorkingVO = new FaWorkingVO();
            BeanUtils.copyProperties(v, faWorkingVO);
            faWorkingVOList.add(faWorkingVO);

        });
        return new ResponseEntity<>(faWorkingVOList, HttpStatus.OK);
    }

    @ApiOperation(value = "查询工作信息", notes = "根据进件编号申请库查询工作信息")
    @RequestMapping(value = "/getWorkByAppNum/v1", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getWorkByAppNum(int pageNum, int pageSize) throws Exception {
        List<FaWorkingVO> faWorkingVOList = new ArrayList<>();
        PageHelper.startPage(pageNum, pageSize);
        List<FaWorkingDTO> list = service.searchByPaging();
        list.forEach(v -> {
            FaWorkingVO faWorkingVO = new FaWorkingVO();
            BeanUtils.copyProperties(v, faWorkingVO);
            faWorkingVOList.add(faWorkingVO);

        });
        PageInfo pageInfo = new PageInfo(list);
        return new ResponseEntity<>(pageInfo, HttpStatus.OK);
    }

    @ApiOperation(value = "查询工作信息", notes = "根据进件编号申请库查询工作信息")
    @RequestMapping(value = "/search/v1", method = RequestMethod.GET)
    public ResponseEntity search(FaWorkingRequest request, int pageNum, int pageSize) throws Exception {
        List<FaWorkingDTO> list = service.search(request,pageNum, pageSize);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
