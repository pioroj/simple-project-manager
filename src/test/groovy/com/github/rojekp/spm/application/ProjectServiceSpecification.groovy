package com.github.rojekp.spm.application

import com.blogspot.toomuchcoding.spock.subjcollabs.Collaborator
import com.blogspot.toomuchcoding.spock.subjcollabs.Subject
import com.github.rojekp.spm.application.dto.project.ProjectDraftDTO
import com.github.rojekp.spm.domain.project.Project
import com.github.rojekp.spm.domain.project.ProjectFactory
import com.github.rojekp.spm.domain.project.ProjectRepository
import spock.lang.Specification

class ProjectServiceSpecification extends Specification {

    @Subject
    private ProjectService projectService

    @Collaborator
    private ProjectFactory projectFactory = Mock()

    @Collaborator
    private ProjectRepository projectRepository = Mock()

    def 'should create project with proper name'() {
        given:
        ProjectDraftDTO projectDraftDTO = new ProjectDraftDTO()
        projectDraftDTO.setName('Project 1')

        when:
        projectService.createProject(projectDraftDTO)

        then:
        1 * projectRepository.save(_)
        1 * projectFactory.createProjectDraft(_) >> prepareProject()
    }

    private static Project prepareProject() {
        return new Project('1', 'Project 1')
    }

}
