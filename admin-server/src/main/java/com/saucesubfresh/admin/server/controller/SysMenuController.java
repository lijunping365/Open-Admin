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
  public Result<PageResult<SysMenuRespDTO>> page(SysMenuReqDTO sysMenuReqDTO) {
    return Result.succeed(sysMenuService.selectPage(sysMenuReqDTO));
  }

  @GetMapping("/info/{id}")
  public Result<SysMenuRespDTO> info(@PathVariable("id") Long id) {
    return Result.succeed(sysMenuService.getItem(id));
  }

  @PostMapping("/save")
  public Result<Void> save(@RequestBody @Valid SysMenuCreateDTO sysMenuCreateDTO) {
    sysMenuService.save(sysMenuCreateDTO);
    return Result.succeed();
  }

  @PutMapping("/update")
  public Result<Void> update(@RequestBody @Valid SysMenuUpdateDTO sysMenuUpdateDTO) {
    sysMenuService.update(sysMenuUpdateDTO);
    return Result.succeed();
  }

  @PutMapping("/delete/{id}")
  public Result<Void> delete(@PathVariable("id") Long id) {
    sysMenuService.delete(id);
    return Result.succeed();
  }

  @GetMapping("/tree")
  public Result<List<MenuTreeVO>> getMenuTree() {
    return Result.succeed(sysMenuService.getMenuTree());
  }

}
