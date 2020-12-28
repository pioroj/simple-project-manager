package com.github.rojekp.spm.application;

import com.github.rojekp.spm.application.dto.project.ProjectDraftDTO;
import com.github.rojekp.spm.domain.project.Project;
import com.github.rojekp.spm.domain.project.ProjectFactory;
import com.github.rojekp.spm.domain.project.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectFactory projectFactory;
    private final ProjectRepository projectRepository;

    public void createProject(ProjectDraftDTO projectDraftDTO) {
        Project project = projectFactory.createProjectDraft(projectDraftDTO.getName());
        projectRepository.save(project);
    }

}
