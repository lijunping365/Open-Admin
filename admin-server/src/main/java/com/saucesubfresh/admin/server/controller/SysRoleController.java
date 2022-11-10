package com.saucesubfresh.admin.server.controller;

import com.saucesubfresh.admin.common.vo.PageResult;
import com.saucesubfresh.admin.common.vo.Result;
import com.saucesubfresh.admin.server.dto.create.SysRoleCreateDTO;
import com.saucesubfresh.admin.server.dto.req.SysRoleReqDTO;
import com.saucesubfresh.admin.server.dto.resp.SysRoleRespDTO;
import com.saucesubfresh.admin.server.dto.update.SysRoleUpdateDTO;
import com.saucesubfresh.admin.server.service.SysRoleService;
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
public class SysRoleController {

  @Autowired
  private SysRoleService sysRoleService;

  @GetMapping("/list")
  public Result<List<SysRoleRespDTO>> list() {
    return Result.succeed(sysRoleService.selectAll());
  }

  @GetMapping("/page")
  public Result<PageResult<SysRoleRespDTO>> page(SysRoleReqDTO sysRoleReqDTO) {
    return Result.succeed(sysRoleService.selectPage(sysRoleReqDTO));
  }

  @PostMapping("/save")
  public Result<Void> save(@RequestBody @Valid SysRoleCreateDTO sysRoleCreateDTO) {
    sysRoleService.save(sysRoleCreateDTO);
    return Result.succeed();
  }

  @PutMapping("/update")
  public Result<Void> update(@RequestBody @Valid SysRoleUpdateDTO sysRoleUpdateDTO) {
    sysRoleService.update(sysRoleUpdateDTO);
    return Result.succeed();
  }

  @PutMapping("/delete/{id}")
  public Result<Void> delete(@PathVariable("id") Long id) {
    sysRoleService.delete(id);
    return Result.succeed();
  }

  /**
   * 给角色分配菜单
   */
  @PostMapping("/allocMenu")
  public Result<Void> allocMenu(@RequestBody @Valid SysRoleUpdateDTO sysRoleUpdateDTO) {
    sysRoleService.allocMenu(sysRoleUpdateDTO);
    return Result.succeed();
  }
}
