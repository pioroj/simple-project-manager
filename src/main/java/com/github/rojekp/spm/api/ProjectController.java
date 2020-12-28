package com.github.rojekp.spm.api;

import com.github.rojekp.spm.application.ProjectService;
import com.github.rojekp.spm.application.dto.project.ProjectDraftDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/drafts")
    public void createProject(@RequestBody ProjectDraftDTO projectDraftDTO) {
        projectService.createProject(projectDraftDTO);
    }

}
