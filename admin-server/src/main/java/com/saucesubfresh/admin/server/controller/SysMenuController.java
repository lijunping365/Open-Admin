package com.saucesubfresh.admin.server.controller;

import com.saucesubfresh.admin.common.vo.PageResult;
import com.saucesubfresh.admin.common.vo.Result;
import com.saucesubfresh.admin.server.dto.create.SysMenuCreateDTO;
import com.saucesubfresh.admin.server.dto.req.SysMenuReqDTO;
import com.saucesubfresh.admin.server.dto.resp.SysMenuRespDTO;
import com.saucesubfresh.admin.server.dto.update.SysMenuUpdateDTO;
import com.saucesubfresh.admin.server.service.SysMenuService;
import com.saucesubfresh.admin.server.vo.MenuTreeVO;
import com.saucesubfresh.starter.security.context.UserSecurityContextHolder;
import lombok.extern.slf4j.Slf4j;
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
 * @date 2020-08-23 09:27:19
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/menu")
public class SysMenuController {
  @Autowired
  private SysMenuService sysMenuService;

  @GetMapping("/page")
  public Result<PageResult<SysMenuRespDTO>> list(SysMenuReqDTO sysMenuReqDTO) {
    return Result.succeed(sysMenuService.selectPage(sysMenuReqDTO));
  }

  @GetMapping("/info/{id}")
  public Result<SysMenuRespDTO> info(@PathVariable("id") Long id) {
    return Result.succeed(sysMenuService.getItem(id));
  }

  @PostMapping("/save")
  public Result<Boolean> save(@RequestBody @Valid SysMenuCreateDTO sysMenuCreateDTO) {
    return Result.succeed(sysMenuService.save(sysMenuCreateDTO));
  }

  @PutMapping("/update")
  public Result<Boolean> update(@RequestBody @Valid SysMenuUpdateDTO sysMenuUpdateDTO) {
    return Result.succeed(sysMenuService.update(sysMenuUpdateDTO));
  }

  @PutMapping("/delete/{id}")
  public Result<Boolean> delete(@PathVariable("id") Long id) {
    return Result.succeed(sysMenuService.delete(id));
  }

  @GetMapping("/tree")
  public Result<List<MenuTreeVO>> getMenuTree() {
    return Result.succeed(sysMenuService.getMenuTree(UserSecurityContextHolder.getUserId()));
  }

}
