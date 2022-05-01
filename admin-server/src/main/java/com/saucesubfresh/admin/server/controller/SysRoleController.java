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
  public Result<List<SysRoleRespDTO>> list(@RequestParam(value = "total", required = false) Integer total) {
    return Result.succeed(sysRoleService.selectList(total));
  }

  @GetMapping("/page")
  public Result<PageResult<SysRoleRespDTO>> page(SysRoleReqDTO sysRoleReqDTO) {
    return Result.succeed(sysRoleService.selectPage(sysRoleReqDTO));
  }

  @PostMapping("/save")
  public Result<Boolean> save(@RequestBody @Valid SysRoleCreateDTO sysRoleCreateDTO) {
    return Result.succeed(sysRoleService.save(sysRoleCreateDTO));
  }

  @PutMapping("/update")
  public Result<Boolean> update(@RequestBody @Valid SysRoleUpdateDTO sysRoleUpdateDTO) {
    return Result.succeed(sysRoleService.update(sysRoleUpdateDTO));
  }

  @PutMapping("/delete/{id}")
  public Result<Boolean> delete(@PathVariable("id") Long id) {
    return Result.succeed(sysRoleService.delete(id));
  }

}
