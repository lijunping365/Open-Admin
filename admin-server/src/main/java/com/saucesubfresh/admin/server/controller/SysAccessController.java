package com.saucesubfresh.admin.server.controller;

import com.saucesubfresh.admin.common.vo.PageResult;
import com.saucesubfresh.admin.common.vo.Result;
import com.saucesubfresh.admin.server.dto.create.SysAccessCreateDTO;
import com.saucesubfresh.admin.server.dto.req.SysAccessReqDTO;
import com.saucesubfresh.admin.server.dto.resp.SysAccessRespDTO;
import com.saucesubfresh.admin.server.dto.update.SysAccessUpdateDTO;
import com.saucesubfresh.admin.server.service.SysAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * 后台用户角色管理
 *
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-08-23 09:27:19
 */
@Validated
@RestController
@RequestMapping("/role")
public class SysAccessController {

  @Autowired
  private SysAccessService sysAccessService;

  @GetMapping("/list")
  public Result<List<SysAccessRespDTO>> list() {
    return Result.succeed(sysAccessService.selectAll());
  }

  @GetMapping("/page")
  public Result<PageResult<SysAccessRespDTO>> page(SysAccessReqDTO sysAccessReqDTO) {
    return Result.succeed(sysAccessService.selectPage(sysAccessReqDTO));
  }

  @PostMapping("/save")
  public Result<Void> save(@RequestBody @Valid SysAccessCreateDTO sysAccessCreateDTO) {
    sysAccessService.save(sysAccessCreateDTO);
    return Result.succeed();
  }

  @PutMapping("/update")
  public Result<Void> update(@RequestBody @Valid SysAccessUpdateDTO sysAccessUpdateDTO) {
    sysAccessService.update(sysAccessUpdateDTO);
    return Result.succeed();
  }

  @PutMapping("/delete/{id}")
  public Result<Void> delete(@PathVariable("id") Long id) {
    sysAccessService.delete(id);
    return Result.succeed();
  }

  /**
   * 给角色分配权限
   */
  @PostMapping("/allocAccess")
  public Result<Void> allocAccess(@RequestBody @Valid SysAccessUpdateDTO sysAccessUpdateDTO) {
    sysAccessService.allocAccess(sysAccessUpdateDTO);
    return Result.succeed();
  }
}
