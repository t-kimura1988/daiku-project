package daiku.app.app.controller;

import daiku.app.app.controller.input.groups.CreateGroups;
import daiku.app.app.controller.input.groups.UpdateGroups;
import daiku.app.app.controller.input.process.ProcessCreateParam;
import daiku.app.app.controller.input.process.ProcessDateUpdateParam;
import daiku.app.app.controller.input.process.ProcessDetailParam;
import daiku.app.app.controller.input.process.ProcessSearchParam;
import daiku.app.app.service.ProcessService;
import daiku.domain.exception.GoenNotFoundException;
import daiku.domain.infra.entity.GoenUserDetails;
import daiku.domain.infra.entity.TProcesses;
import daiku.domain.infra.model.res.ProcessSearchModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/process")
public class ProcessController {
    @Autowired
    ProcessService processService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<ProcessSearchModel> list(
            ProcessSearchParam param,
            @AuthenticationPrincipal GoenUserDetails user) throws GoenNotFoundException {
        return processService.search(param.toService(user.account().getId())).toResponse();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public TProcesses create(@RequestBody @Validated(CreateGroups.class) ProcessCreateParam param,
                             @AuthenticationPrincipal GoenUserDetails user) throws GoenNotFoundException {
        return processService.create(param.toService(user.account().getId()));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public TProcesses update(@RequestBody @Validated(UpdateGroups.class) ProcessCreateParam param,
                       @AuthenticationPrincipal GoenUserDetails user) throws GoenNotFoundException {
        return processService.update(param.toUpdateService(user.account().getId()));
    }

    @RequestMapping(value = "/update/process-date", method = RequestMethod.POST)
    public ProcessSearchModel updateProcessDate(@RequestBody @Validated ProcessDateUpdateParam param,
                                               @AuthenticationPrincipal GoenUserDetails user) throws GoenNotFoundException {
        return processService.updateProcessDate(param.toService(user.account().getId()));
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public ProcessSearchModel detail(
            ProcessDetailParam param,
            @AuthenticationPrincipal GoenUserDetails user
    ) throws GoenNotFoundException {
        return processService.detail(param.toService(user.account().getId())).toResponse();
    }
}
