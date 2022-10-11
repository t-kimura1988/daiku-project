package daiku.app.controller;

import daiku.app.controller.input.groups.CreateGroups;
import daiku.app.controller.input.story.StoryBodyUpdateParameter;
import daiku.app.controller.input.story.StoryEditParameter;
import daiku.app.service.StoryService;
import daiku.domain.entity.GoenUserDetails;
import daiku.domain.exception.GoenNotFoundException;
import daiku.domain.model.res.IdeaSearchModel;
import daiku.domain.model.res.StorySearchModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/story")
public class StoryController {

    @Autowired
    private StoryService storyService;

    @PostMapping(value = "/create")
    public StorySearchModel create(
            @RequestBody @Validated(CreateGroups.class) StoryEditParameter param,
            @AuthenticationPrincipal GoenUserDetails user) throws GoenNotFoundException{
        return storyService.create(param.toService(user.account().getId())).toRes();
    }

    @PostMapping(value = "/update-story-body")
    public IdeaSearchModel updateBody(
            @RequestBody @Validated StoryBodyUpdateParameter param,
            @AuthenticationPrincipal GoenUserDetails user) throws GoenNotFoundException {
        return storyService.updateBody(param.toService(user.account().getId())).toRes();
    }

}
