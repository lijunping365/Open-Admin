package com.saucesubfresh.admin.server.controller;

import com.saucesubfresh.admin.common.vo.PageResult;
import com.saucesubfresh.admin.common.vo.Result;
import com.saucesubfresh.admin.server.dto.create.SysRegionCreateDTO;
import com.saucesubfresh.admin.server.dto.req.SysRegionReqDTO;
import com.saucesubfresh.admin.server.dto.resp.SysRegionRespDTO;
import com.saucesubfresh.admin.server.dto.update.SysRegionUpdateDTO;
import com.saucesubfresh.admin.server.service.SysRegionService;
import com.saucesubfresh.admin.server.vo.SysRegionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * 行政区划表
 *
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-11-03 17:14:00
 */
@Validated
@RestController
@RequestMapping("/region")
public class SysRegionController {

  @Autowired
  private SysRegionService sysRegionService;

  @GetMapping("/page")
  public Result<PageResult<SysRegionRespDTO>> page(SysRegionReqDTO sysRegionReqDTO) {
    return Result.succeed(sysRegionService.selectPage(sysRegionReqDTO));
  }

  /**
   * 批量查询列表
   * @param ids
   * @return
   */
  @GetMapping("/listByIds")
  public Result<List<SysRegionRespDTO>> selectListByIds(@RequestParam("ids") List<Long> ids) {
    return Result.succeed(sysRegionService.selectListByIds(ids));
  }

  /**
   * 省市区树结构数据
   * @param deep
   * @return
   */
  @GetMapping("/tree")
  public Result<List<SysRegionVO>> tree(@RequestParam(required = false, defaultValue = "3") Integer deep) {
    return Result.succeed(sysRegionService.listWithTree(deep));
  }

  /**
   * 三级联动查询列表
   * @param pid
   * @param level
   * @return
   */
  @GetMapping("/list")
  public Result<List<SysRegionRespDTO>> list(@RequestParam(required = false, defaultValue = "0") Long pid, @RequestParam(required = false, defaultValue = "1") Integer level) {
    return Result.succeed(sysRegionService.selectList(pid, level));
  }

  @GetMapping("/info/{id}")
  public Result<SysRegionRespDTO> info(@PathVariable("id") Long id) {
    return Result.succeed(sysRegionService.getById(id));
  }

  @PostMapping("/save")
  public Result<Boolean> save(@RequestBody @Valid SysRegionCreateDTO sysRegionCreateDTO) {
    return Result.succeed(sysRegionService.save(sysRegionCreateDTO));
  }

  @PutMapping("/update")
  public Result<Boolean> update(@RequestBody @Valid SysRegionUpdateDTO sysRegionUpdateDTO) {
    return Result.succeed(sysRegionService.updateById(sysRegionUpdateDTO));
  }

  @PutMapping("/delete/{id}")
  public Result<Boolean> delete(@PathVariable("id") Long id) {
    return Result.succeed(sysRegionService.deleteById(id));
  }

}
