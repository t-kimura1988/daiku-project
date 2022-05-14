package daiku.app.app.controller;

import daiku.app.app.controller.input.processHistory.ProcessHistoryCreateParam;
import daiku.app.app.controller.input.processHistory.ProcessHistoryDetailParam;
import daiku.app.app.controller.input.processHistory.ProcessHistorySearchParam;
import daiku.app.app.controller.input.processHistory.ProcessHistoryUpdateCommentParam;
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
    public void create(
            @RequestBody ProcessHistoryCreateParam param,
            @AuthenticationPrincipal GoenUserDetails user
    ) throws GoenNotFoundException {
        processHistoryService.create(param.toService(user.account().getId()));
    }

    @PostMapping("/update/comment")
    public void updateComment(
            @RequestBody ProcessHistoryUpdateCommentParam param,
            @AuthenticationPrincipal GoenUserDetails user
            ) {
        processHistoryService.updateComment(param.toService(user.account().getId()));
    }
}
