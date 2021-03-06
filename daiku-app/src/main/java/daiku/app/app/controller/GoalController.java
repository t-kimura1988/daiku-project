package daiku.app.app.controller;

import daiku.app.app.controller.input.goal.*;
import daiku.app.app.controller.input.groups.CreateGroups;
import daiku.app.app.controller.input.groups.UpdateGroups;
import daiku.app.app.controller.output.GoalArchiveDetailResponse;
import daiku.app.app.service.GoalService;
import daiku.domain.exception.GoenIntegrityException;
import daiku.domain.exception.GoenNotFoundException;
import daiku.domain.infra.entity.GoenUserDetails;
import daiku.domain.infra.entity.TGoalArchive;
import daiku.domain.infra.model.res.GoalArchiveSearchModel;
import daiku.domain.infra.model.res.GoalSearchModel;
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
@RequestMapping("api/goal")
public class GoalController {

    @Autowired
    private GoalService goalService;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<GoalSearchModel> search(GoalSearchParameter param, @AuthenticationPrincipal GoenUserDetails user) {
        log.info("goal controller start");
        return goalService.search(param.toService(user.account().getId())).toResponse();
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public GoalSearchModel detail(GoalDetailParameter param,
                                  @AuthenticationPrincipal GoenUserDetails user) {
        return goalService.detail(param.toService(user.account().getId())).toResponse();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void create(@RequestBody @Validated(CreateGroups.class) GoalCreateParam param, @AuthenticationPrincipal GoenUserDetails user) {
        goalService.create(param.toCreateService(user.account().getId()));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void update(
            @RequestBody @Validated(UpdateGroups.class) GoalCreateParam param,
            @AuthenticationPrincipal GoenUserDetails user) throws GoenNotFoundException {
        goalService.update(param.toUpdateService(user.account().getId()));
    }

    @RequestMapping(value = "/create/archive", method = RequestMethod.POST)
    public void archive(
            @RequestBody @Validated(CreateGroups.class) GoalArchiveCreateParam param,
            @AuthenticationPrincipal GoenUserDetails user) throws GoenNotFoundException, GoenIntegrityException {
        goalService.archive(param.toArchiveService(user.account().getId()));
    }

    @RequestMapping(value = "/update/archive/display", method = RequestMethod.GET)
    public TGoalArchive archiveUpdate(
            GoalArchiveDetailParameter param,
            @AuthenticationPrincipal GoenUserDetails user) throws GoenNotFoundException, GoenIntegrityException {
        return goalService.archiveUpdateDisp(param.toArchiveUpdateDipsService()).toResponse();
    }

    @RequestMapping(value = "/update/archive", method = RequestMethod.POST)
    public void archiveUpdateDisp(
            @RequestBody @Validated(UpdateGroups.class) GoalArchiveCreateParam param,
            @AuthenticationPrincipal GoenUserDetails user) throws GoenNotFoundException, GoenIntegrityException {
        goalService.archiveUpdate(param.toArchiveUpdateService());
    }

    @RequestMapping(value = "/archive/search", method = RequestMethod.GET)
    public List<GoalArchiveSearchModel> archiveSearch(GoalArchiveSearchParameter param, @AuthenticationPrincipal GoenUserDetails user) {
        return goalService.archiveSearch(param.toService(user.account().getId())).toResponse();
    }

    @RequestMapping(value = "/archive/detail", method = RequestMethod.GET)
    public GoalArchiveDetailResponse archiveDetail(GoalArchiveDetailParameter param, @AuthenticationPrincipal GoenUserDetails user) throws GoenNotFoundException {
        return goalService.archiveDetail(param.toService(user.account().getId())).toResponse();
    }

    @RequestMapping(value = "/update/archive/updating-flg", method = RequestMethod.POST)
    public GoalSearchModel archiveUpdatingFlg(
            @RequestBody @Validated(UpdateGroups.class) GoalArchiveUpdatingFlgParameter param,
            @AuthenticationPrincipal GoenUserDetails user) throws GoenNotFoundException, GoenIntegrityException {
        return goalService.archiveUpdatingFlg(param.toService());
    }
}
