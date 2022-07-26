package daiku.app.app.controller;

import daiku.app.app.controller.input.processHistory.*;
import daiku.app.app.service.ProcessHistoryService;
import daiku.domain.exception.GoenNotFoundException;
import daiku.domain.infra.entity.GoenUserDetails;
import daiku.domain.infra.model.res.ProcessHistorySearchModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/process-history")
public class ProcessHistoryController {
    @Autowired
    ProcessHistoryService processHistoryService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<ProcessHistorySearchModel> list(
            ProcessHistorySearchParam param,
            @AuthenticationPrincipal GoenUserDetails user) throws GoenNotFoundException {
        return processHistoryService.search(param.toService(user.account().getId())).toResponse();
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public ProcessHistorySearchModel detail(
            ProcessHistoryDetailParam param,
            @AuthenticationPrincipal GoenUserDetails user) throws GoenNotFoundException {
        return processHistoryService.detail(param.toService(user.account().getId())).toResponse();
    }

    @PostMapping("/create")
    public ProcessHistorySearchModel create(
            @RequestBody ProcessHistoryCreateParam param,
            @AuthenticationPrincipal GoenUserDetails user
    ) throws GoenNotFoundException {
        return processHistoryService.create(param.toService(user.account().getId()));
    }

    @PostMapping("/update/comment")
    public ProcessHistorySearchModel updateComment(
            @RequestBody ProcessHistoryUpdateCommentParam param,
            @AuthenticationPrincipal GoenUserDetails user
            ) throws GoenNotFoundException{
        return processHistoryService.updateComment(param.toService(user.account().getId()));
    }

    @PostMapping("/update/status")
    public ProcessHistorySearchModel updateStatus(
            @RequestBody ProcessHistoryUpdateStatusParam param,
            @AuthenticationPrincipal GoenUserDetails user
    ) throws GoenNotFoundException {
        return processHistoryService.updateStatus(param.toService(user.account().getId()));
    }
}
