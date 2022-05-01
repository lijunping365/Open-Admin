package com.saucesubfresh.admin.server.controller;

import com.saucesubfresh.admin.common.vo.PageResult;
import com.saucesubfresh.admin.common.vo.Result;
import com.saucesubfresh.admin.server.dto.create.SysResourceCreateDTO;
import com.saucesubfresh.admin.server.dto.req.SysResourceReqDTO;
import com.saucesubfresh.admin.server.dto.resp.SysResourceRespDTO;
import com.saucesubfresh.admin.server.dto.update.SysResourceUpdateDTO;
import com.saucesubfresh.admin.server.service.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * 菜单表
 *
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-11-30 11:44:56
 */
@Validated
@RestController
@RequestMapping("/resource")
public class SysResourceController {

  @Autowired
  private SysResourceService sysResourceService;

  @GetMapping("/list")
  public Result<List<SysResourceRespDTO>> list(@RequestParam(value = "total", required = false) Integer total) {
    return Result.succeed(sysResourceService.selectList(total));
  }

  @GetMapping("/page")
  public Result<PageResult<SysResourceRespDTO>> page(SysResourceReqDTO sysResourceReqDTO) {
    return Result.succeed(sysResourceService.selectPage(sysResourceReqDTO));
  }

  @GetMapping("/info/{id}")
  public Result<SysResourceRespDTO> info(@PathVariable("id") Long id) {
    return Result.succeed(sysResourceService.getById(id));
  }

  @PostMapping("/save")
  public Result<Boolean> save(@RequestBody @Valid SysResourceCreateDTO sysResourceCreateDTO) {
    return Result.succeed(sysResourceService.save(sysResourceCreateDTO));
  }

  @PutMapping("/update")
  public Result<Boolean> update(@RequestBody @Valid SysResourceUpdateDTO sysResourceUpdateDTO) {
    return Result.succeed(sysResourceService.updateById(sysResourceUpdateDTO));
  }

  @PutMapping("/delete/{id}")
  public Result<Boolean> delete(@PathVariable("id") Long id) {
    return Result.succeed(sysResourceService.deleteById(id));
  }

}
